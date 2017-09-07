package com.david.others;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class TestJMX {
    public static void main(String[] args) throws MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, InterruptedException {
        MBeanServer server = ManagementFactory.getPlatformMBeanServer();
        ObjectName helloName = new ObjectName("jmxBean:name=hello");
        //create mbean and register mbean
        server.registerMBean(new Hello(), helloName);
        Thread.sleep(60*60*1000);
    }

    public static interface HelloMBean {
        public String getName();

        public void setName(String name);

        public String getAge();

        public void setAge(String age);

        public void helloWorld();

        public void helloWorld(String str);

        public void getTelephone();
    }

    /**
     * 该类名称必须与实现的接口的前缀保持一致（即MBean前面的名称)
     */
    public static class Hello implements HelloMBean {
        private String name;

        private String age;

        public void getTelephone() {
            System.out.println("get Telephone");
        }

        public void helloWorld() {
            System.out.println("hello world");
        }

        public void helloWorld(String str) {
            System.out.println("helloWorld:" + str);
        }

        public String getName() {
            System.out.println("get name 123");
            return name;
        }

        public void setName(String name) {
            System.out.println("set name 123");
            this.name = name;
        }

        public String getAge() {
            System.out.println("get age 123");
            return age;
        }

        public void setAge(String age) {
            System.out.println("set age 123");
            this.age = age;
        }
    }

}
