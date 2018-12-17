package com.david.gupao.proxy;

import com.google.common.io.Files;
import net.sf.cglib.core.DebuggingClassWriter;
import org.junit.Test;
import sun.misc.ProxyGenerator;

import java.io.File;
import java.io.IOException;

public class PaymentTest {


    @Test
    public void testJdkProxy() throws IOException {
        AliPayment aliPayment = new AliPayment();
        Payment proxy = (Payment) new JdkProxy(aliPayment).getInstance();
        proxy.doPay(101D);
        byte[] bytes = ProxyGenerator.generateProxyClass("$Proxy0", AliPayment.class.getInterfaces());

        String path = "JdkProxy.class";
        Files.asByteSink(new File(path)).write(bytes);
    }

    @Test
    public void testCglibProxy() {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"code");

        AliPayment aliPayment = new AliPayment();
        AliPayment proxy1 = (AliPayment) new CglibProxy().getInstance(aliPayment);
        proxy1.doPay(100D);
    }
}
