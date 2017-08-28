package com.david.others;

import org.junit.Before;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class TestStation {
    Unsafe unsafe;
    @Before
    public void before() throws Exception {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
         unsafe = (Unsafe) theUnsafe.get(null);
    }

    //wrong
    @Test
    public void testUnsafe() {
        Unsafe unsafe = Unsafe.getUnsafe();
        unsafe.compareAndSwapInt(0, 0, 0, 0);
    }

    @Test
    public void testAllocateInstance() throws Exception {
        Person person = (Person) unsafe.allocateInstance(Person.class);
        person.setAge(100);
        System.out.println(person);
    }
    @Test
    public void testCopyPass(){
        String password = "password";
        String fake = password.replaceAll(".","?");
        System.out.println(password);
        System.out.println(fake);

    }
}
