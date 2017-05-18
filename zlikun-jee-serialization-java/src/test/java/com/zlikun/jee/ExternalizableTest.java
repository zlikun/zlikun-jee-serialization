package com.zlikun.jee;

import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.util.Date;

/**
 * @auther zlikun <zlikun-dev@hotmail.com>
 * @date 2017/5/18 19:30
 */
public class ExternalizableTest {

    @Test
    public void test() throws IOException, ClassNotFoundException {

        // 准备数据
        Car car = new Car() ;
        car.setBrand(Brand.Benz);
        car.setColor("red");
        car.setProductionDate(new Date());
        car.setSpeed(120);

        // 测试序列化
        ByteArrayOutputStream baos = new ByteArrayOutputStream() ;
        ObjectOutputStream out = new ObjectOutputStream(baos) ;
        out.writeObject(car);
        byte [] bytes = baos.toByteArray() ;
        out.close();
        baos.close();

        // 测试反序列化
        ByteArrayInputStream bais = new ByteArrayInputStream(bytes) ;
        ObjectInputStream in = new ObjectInputStream(bais) ;
        Object obj = in.readObject() ;
        in.close();
        bais.close();

        // 断言
        Assert.assertNotNull(bytes);
        Assert.assertEquals(164 ,bytes.length);
        System.out.println(new String(bytes));

        Assert.assertNotNull(obj);
        Assert.assertTrue(obj instanceof Car);
        Assert.assertNull(((Car) obj).getSpeed());
        Assert.assertEquals(car.getBrand() ,((Car) obj).getBrand());
        Assert.assertEquals(car.getProductionDate() ,((Car) obj).getProductionDate());
        System.out.println(obj);
    }

}
