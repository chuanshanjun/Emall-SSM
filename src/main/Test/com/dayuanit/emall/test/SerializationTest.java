package com.dayuanit.emall.test;

import java.io.*;

public class SerializationTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Dog dog1 = new Dog();
        dog1.setName("wc");
        dog1.setColor("yellow");

        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("/test.txt"));
        out.writeObject(dog1);
        out.flush();
        out.close();

        ObjectInputStream in = new ObjectInputStream(new FileInputStream("/test.txt"));
        Dog dog2= (Dog)in.readObject();
        System.out.println(dog2.getColor());
        System.out.println(dog2.getName());
        System.out.println(dog1 == dog2);
    }
}

/**
 * 序列化一个对象需要这个对象实现序列化的接口
 */
class Dog implements Serializable{
    private String name;
    private String color;

    public Dog() {

    }

    public Dog(String name, String coloe) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
