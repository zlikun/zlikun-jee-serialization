package com.zlikun.jee;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Date;

/**
 * 序列化到文件
 * @auther zlikun <zlikun-dev@hotmail.com>
 * @date 2017/5/18 18:34
 */
public class SerializationToFileTest {

    private String path = "target/data.log" ;

    @Test
    public void test() throws IOException, ClassNotFoundException {
        // 准备数据
        Data data = DataFactory.get() ;

        // 执行序列化
        serialization(data) ;

        // 执行反序列化
        Object obj = deserialization() ;
        Assert.assertNotNull(obj);
        Assert.assertTrue(obj instanceof Data);
        Assert.assertNull(((Data) obj).getEmail());     // 使用 transient 关键字修饰的字段将不参与序列化/反序列化
        Assert.assertEquals(data.getSalary() ,((Data) obj).getSalary());
        Assert.assertEquals(data.getGender() ,((Data) obj).getGender());
        Assert.assertEquals(data.getSignature() ,((Data) obj).getSignature());
        System.out.println(obj);

    }

    /**
     * 序列化
     * @param obj
     * @return
     * @throws IOException
     */
    private void serialization(Object obj) throws IOException {

        // 字节数组输出流
        FileOutputStream fos = new FileOutputStream(path) ;

        // 对象输出流
        ObjectOutputStream out = new ObjectOutputStream(fos) ;
        out.writeObject(obj);

        out.close();
        fos.close();
    }

    /**
     * 反序列化
     * @return
     */
    private Object deserialization() throws IOException, ClassNotFoundException {

        FileInputStream fis = new FileInputStream(path) ;
        ObjectInputStream in = new ObjectInputStream(fis) ;

        Object obj = in.readObject() ;

        in.close();
        fis.close();

        return obj ;
    }

}
