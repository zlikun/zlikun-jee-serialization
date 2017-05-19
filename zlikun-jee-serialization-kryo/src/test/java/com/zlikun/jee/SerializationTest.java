package com.zlikun.jee;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import com.esotericsoftware.kryo.io.Output;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @auther zlikun <zlikun-dev@hotmail.com>
 * @date 2017/5/19 13:39
 */
public class SerializationTest {

    @Test
    public void test() throws IOException, ClassNotFoundException {
        // 准备数据
        Data data = DataFactory.get() ;

        Kryo kryo = new Kryo();
        // 执行序列化
        ByteArrayOutputStream out = new ByteArrayOutputStream() ;
        Output output = new Output(out) ;
        kryo.writeObject(output ,data);
        output.close();
        byte [] bytes = out.toByteArray() ;
        // 执行断言
        Assert.assertNotNull(bytes);
        Assert.assertEquals(175 ,bytes.length);
        System.out.println(new String(bytes));

        // 执行反序列化
        Input input = new Input(new ByteArrayInputStream(bytes)) ;
        Data obj = kryo.readObject(input ,Data.class) ;
        // 执行断言
        Assert.assertNotNull(obj);
        Assert.assertNull(obj.getEmail());     // 使用 transient 关键字修饰的字段将不参与序列化/反序列化
        Assert.assertEquals(data.getSalary() ,obj.getSalary());
        Assert.assertEquals(data.getGender() ,obj.getGender());
        Assert.assertEquals(data.getSignature() ,obj.getSignature());
        System.out.println(obj);

    }

}
