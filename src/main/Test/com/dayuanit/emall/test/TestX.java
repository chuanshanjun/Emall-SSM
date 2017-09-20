package com.dayuanit.emall.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestX {
    Person person;

    public static void main(String[] args) throws InterruptedException {
        List<Person> list = new ArrayList<Person>(10);

        Person chenDongDond = new Person("陈东东");
        list.add(chenDongDond);

        Person xueHongChao = new Person("薛红超");
        list.add(xueHongChao);

        Person zhengGuangJing = new Person("郑光景");
        list.add(zhengGuangJing);

        Person zhuZi = new Person("柱子");
        list.add(zhuZi);

        Person cHengGuangYu = new Person("程光宇");
        list.add(cHengGuangYu);

        Person yangXuHong = new Person("杨旭红");
        list.add(yangXuHong);

        Person zhaoHong = new Person("赵红");
        list.add(zhaoHong);

        Person geYanYan = new Person("葛沿炎");
        list.add(geYanYan);

        Person caiJiangBin = new Person("菜江斌");
        list.add(caiJiangBin);

        Person wangYongHui = new Person("王永慧");
        list.add(wangYongHui);

        for (int j = 10; j > 0; j--) {
                int x = (int) (Math.random() * j);
                Person person = list.get(x);
                System.out.println(person.getName());
//                Thread.sleep(10000);
                list.remove(x);
        }

        String str = new String("abc");
        String str1 = new String("abc");
        System.out.println(str.equals(str1));
    }

}

