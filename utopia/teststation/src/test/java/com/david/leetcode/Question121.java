package com.david.leetcode;

/**
 * 给定一个数组，它的第 i 个元素是一支给定股票第 i 天的价格。
 * <p>
 * 如果你最多只允许完成一笔交易（即买入和卖出一支股票一次），设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 注意：你不能在买入股票前卖出股票。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */


public class Question121 {
    public static void main(String[] args) {
        int[] prices = new int[]{7,1,5,3,6,4};
        System.out.println(new Solution2().maxProfit(prices));
        System.out.println(new Solution2().maxProfit_k_1(prices));
    }

    /**
     * TODO 通用动态规划 定时复习
     * https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/yi-ge-fang-fa-tuan-mie-6-dao-gu-piao-wen-ti-by-l-3/
     * i：天数
     * k：交易次数
     * 0/1：不持有/持有
     * dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
     *               max(   选择 rest  ,             选择 sell      )
     *
     * 解释：今天我没有持有股票，有两种可能：
     * 要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
     * 要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。
     *
     * dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
     *               max(   选择 rest  ,           选择 buy         )
     *
     * 解释：今天我持有着股票，有两种可能：
     * 要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
     * 要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。
     *
     */
    public static class Solution2 {
        public int maxProfit(int[] prices) {
            int n = prices.length;
            int[][] dp = new int[n][2];
            for (int i = 0; i < n; i++) {
                //处理边界情况
                if (i - 1 == -1) {
                    dp[i][0] = 0;
                    // 解释：
                    //   dp[i][0]
                    // = max(dp[-1][0], dp[-1][1] + prices[i])
                    // = max(0, -infinity + prices[i]) = 0
                    dp[i][1] = -prices[i];
                    //解释：
                    //   dp[i][1]
                    // = max(dp[-1][1], dp[-1][0] - prices[i])
                    // = max(-infinity, 0 - prices[i])
                    // = -prices[i]
                    continue;
                }

//                 套用公式 k=1退化成二维数组

//                 dp[i][1][0] = max(dp[i-1][1][0], dp[i-1][1][1] + prices[i])
//                 dp[i][1][1] = max(dp[i-1][1][1], dp[i-1][0][0] - prices[i])
//                 = max(dp[i-1][1][1], -prices[i])
//                 解释：k = 0 的 base case，所以 dp[i-1][0][0] = 0。
//
//                 现在发现 k 都是 1，不会改变，即 k 对状态转移已经没有影响了。
//                 可以进行进一步化简去掉所有 k：
//                 dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
//                 dp[i][1] = max(dp[i-1][1], -prices[i])
                dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1] + prices[i]);
                dp[i][1] = Math.max(dp[i-1][1], -prices[i]);
            }
            return dp[n - 1][0];
        }
        // 简化到如下
        // ||
        // ||
        // V
        // k == 1
        int maxProfit_k_1(int[] prices) {
            int n = prices.length;
            // base case: dp[-1][0] = 0, dp[-1][1] = -infinity
            int dp_i_0 = 0, dp_i_1 = Integer.MIN_VALUE;
            for (int i = 0; i < n; i++) {
                // dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])
                dp_i_0 = Math.max(dp_i_0, dp_i_1 + prices[i]);
                // dp[i][1] = max(dp[i-1][1], -prices[i])
                dp_i_1 = Math.max(dp_i_1, -prices[i]);
            }
            return dp_i_0;
        }
    }

    /**
     * 最大收益:
     * 1.最大值-最小值
     * 2.最大值index 在最小值后面
     */
    public static class Solution1 {
        public int maxProfit(int[] prices) {
            int min = Integer.MAX_VALUE;
            int profit = 0;
            for (int price : prices) {
                //找出当前最小值
                if (min > price) {
                    min = price;
                }
                int currProfit = price - min;
                if (currProfit > profit) {
                    profit = currProfit;
                }
            }
            return profit;
        }
    }
}
