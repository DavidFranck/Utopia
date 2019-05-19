package com.david;

import com.david.others.Person;
import org.junit.Test;


/**
 * Created by David
 * on 2017/1/22
 */
public class TestStation {
    public static final String HTTP = "http://";
    public static final String HTTPS = "https://";

    public String replaceMacro(String url) {
        if (url.contains("price=")) {
            url = url.replace(url.substring(url.lastIndexOf("price=")), "price=" + Math.random() * 10);
        }
        return url;
    }

    @Test
    public void test2() {
        int i = 0;
        while (true) {
            System.out.println(i++);
        }
    }

    @Test
    public void testStr2Double() {
        double v = str2doubel("123.34");
        System.out.println(v);
    }

    public static double str2doubel(String str) {
        double num = 0;
        double num2 = 0;
        int idForDot = str.indexOf('.');
        boolean isNeg = false;
        String st;
        int start = 0;
        int end = str.length();

        if (idForDot != -1) {
            st = str.substring(0, idForDot);
            for (int i = str.length() - 1; i >= idForDot + 1; i--) {
                num2 = (num2 + str.charAt(i) - '0') / 10;
            }
        } else {
            st = str;
        }

        if (st.charAt(0) == '-') {
            isNeg = true;
            start++;
        } else if (st.charAt(0) == '+') {
            start++;
        }

        for (int i = start; i < st.length(); i++) {
            if (st.charAt(i) == ',') {
                continue;
            }
            num *= 10;
            num += st.charAt(i) - '0';
        }

        num = num + num2;
        if (isNeg) {
            num = -1 * num;
        }
        return num;
    }

    @Test
    public void test1() {

        String url = "http://60.205.84.68/adImp?requestid=c9f323bf-b87b-4aa1-8bdd-6262d8c9539b&adgroupid=6&netid=004&netname=adsview&devicetype=HIGHEND_PHONE&os=android&connectiontype=WIFI&material_id=1175&adid=NA&idfa=NA&android_id=NA&android_id_md5=NA&android_id_sha1=7c222fb2927d828af22f592134e8932480637c0d&imei=NA&imei_md5=NA&imei_sha1=NA&deviceID=7c222fb2927d828af22f592134e8932480637c0d&remote_addr=119.57.32.71&cur_adv=RMB&cur_adx=RMB&price=%%WIN_PRICE%%";
//        String url = "=";
//        if(url.contains("price=")){
//            System.out.println(url.substring(url.lastIndexOf("price=")));
//            url=url.replace(url.substring(url.lastIndexOf("price=")),"price="+ Math.random()*10);
//        }
//
//        System.out.println(url);
        System.out.println(url.startsWith(HTTPS));
        System.out.println(url.startsWith(HTTP));
//        System.out.println(replaceMacro(url));
    }
}
