package com.david.gupao.serial;

import java.io.*;

public class UserDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        User user = new User();
        user.setName("david");
        user.setSex("male");
        System.out.println(user);


        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ObjectOutputStream oos = new ObjectOutputStream(baos);
        oos.writeObject(user);

        ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
        ObjectInputStream ois = new ObjectInputStream(bais);
        User u1 = (User) ois.readObject();
        System.out.println(u1);
    }
}
