package com.david.gupao.proxy;

public class AliPayment implements Payment {

    @Override
    public void doPay(Double amount) {
        System.out.println("alipay paying" + amount);
    }
}
