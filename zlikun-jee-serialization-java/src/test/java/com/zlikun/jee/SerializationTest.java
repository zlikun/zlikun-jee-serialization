package com.zlikun.jee;

import org.apache.commons.lang3.SerializationUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.Date;

/**
 * @auther zlikun <zlikun-dev@hotmail.com>
 * @date 2017/5/18 18:34
 */
public class SerializationTest {

    /**
     * 测试序列化、反序列化，结论：
     *      # 不影响序列化
     *      1、注释不影响序列化结果(实际编译为字节码时，注释已被丢弃)
     *      2、字段顺序、Getter/Setter顺序不影响序列化结果(实际编译为字节码时，字段顺序与编译后的文件中字段顺序无关)
     *      3、类文件格式、排版等不影响序列化结果(原因同上)
     *      4、删除Getter/Setter，不影响序列化
     *      5、添加方法(包含重载Getter/Setter)，不影响序列化
     *
     *      # 影响序列化
     *      1、字段类型变更，影响序列化结果
     *      2、增加、减少字段，影响序列化结果
     *      3、修改包名，影响序列化结果
     * @throws IOException
     * @throws ClassNotFoundException
     */
    @Test
    public void test() throws IOException, ClassNotFoundException {
        // 准备数据
        Data data = DataFactory.get() ;

        // 执行序列化
        byte [] bytes = serialization(data) ;
        Assert.assertNotNull(bytes);
        Assert.assertEquals(604 ,bytes.length);
        System.out.println(new String(bytes));

        // 执行反序列化
        Object obj = deserialization(bytes) ;
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
    private byte[] serialization(Object obj) throws IOException {

        // 字节数组输出流
        ByteArrayOutputStream baos = new ByteArrayOutputStream() ;

        // 对象输出流
        ObjectOutputStream out = new ObjectOutputStream(baos) ;
        out.writeObject(obj);
        byte [] bytes = baos.toByteArray() ;

        out.close();
        baos.close();

        return bytes ;
    }

    /**
     * 反序列化
     * @param bytes
     * @return
     */
    private Object deserialization(byte [] bytes) throws IOException, ClassNotFoundException {

        ByteArrayInputStream bais = new ByteArrayInputStream(bytes) ;
        ObjectInputStream in = new ObjectInputStream(bais) ;

        Object obj = in.readObject() ;

        in.close();
        bais.close();

        return obj ;
    }

}
