package com.zlikun.jee;

import org.junit.Assert;
import org.junit.Test;

/**
 * commons-lang3 / spring-beans 库下序列化工具类
 * @auther zlikun <zlikun-dev@hotmail.com>
 * @date 2017/5/18 19:49
 */
public class SerializationUtilsTest {


    @Test
    public void commons() {

        // 准备数据
        Data data = DataFactory.get() ;

        // 序列化
        byte [] bytes = org.apache.commons.lang3.SerializationUtils.serialize(data) ;

        // 反序列化
        Object obj = org.apache.commons.lang3.SerializationUtils.deserialize(bytes) ;

        // 断言
        Assert.assertNotNull(bytes);
        Assert.assertEquals(604 ,bytes.length);
        System.out.println(new String(bytes));

        Assert.assertNotNull(obj);
        Assert.assertTrue(obj instanceof Data);
        Assert.assertNull(((Data) obj).getEmail());     // 使用 transient 关键字修饰的字段将不参与序列化/反序列化
        Assert.assertEquals(data.getSalary() ,((Data) obj).getSalary());
        Assert.assertEquals(data.getGender() ,((Data) obj).getGender());
        Assert.assertEquals(data.getSignature() ,((Data) obj).getSignature());
        System.out.println(obj);

    }

    @Test
    public void spring() {

        // 准备数据
        Data data = DataFactory.get() ;

        // 序列化
        byte [] bytes = org.springframework.util.SerializationUtils.serialize(data) ;

        // 反序列化
        Object obj = org.springframework.util.SerializationUtils.deserialize(bytes) ;

        // 断言
        Assert.assertNotNull(bytes);
        Assert.assertEquals(604 ,bytes.length);
        System.out.println(new String(bytes));

        Assert.assertNotNull(obj);
        Assert.assertTrue(obj instanceof Data);
        Assert.assertNull(((Data) obj).getEmail());     // 使用 transient 关键字修饰的字段将不参与序列化/反序列化
        Assert.assertEquals(data.getSalary() ,((Data) obj).getSalary());
        Assert.assertEquals(data.getGender() ,((Data) obj).getGender());
        Assert.assertEquals(data.getSignature() ,((Data) obj).getSignature());
        System.out.println(obj);

    }

}
