package com.zlikun.jee;

import org.junit.Assert;
import org.junit.Test;
import org.nustaq.serialization.FSTConfiguration;
import org.nustaq.serialization.FSTObjectInput;
import org.nustaq.serialization.FSTObjectOutput;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @auther zlikun <zlikun-dev@hotmail.com>
 * @date 2017/5/19 13:39
 */
public class SerializationTest {

    private static FSTConfiguration conf = FSTConfiguration.createDefaultConfiguration();

    @Test
    public void test() throws Exception {
        // 准备数据
        Data data = DataFactory.get() ;

        // 执行序列化
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream() ;
        FSTObjectOutput out = conf.getObjectOutput(outputStream);
        out.writeObject(data ,Data.class);
        // DON'T out.close() when using factory method;
        out.flush();
        byte [] bytes = outputStream.toByteArray() ;
        outputStream.close();
        // 执行断言
        Assert.assertNotNull(bytes);
        Assert.assertEquals(188 ,bytes.length);
        System.out.println(new String(bytes));

        // 执行反序列化
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes) ;
        FSTObjectInput in = conf.getObjectInput(inputStream) ;
        Object obj = in.readObject(Data.class) ;
        // DON'T: in.close(); here prevents reuse and will result in an exception
        inputStream.close();
        // 执行断言
        Assert.assertNotNull(obj);
        Assert.assertTrue(obj instanceof Data);
        Assert.assertNull(((Data) obj).getEmail());     // 使用 transient 关键字修饰的字段将不参与序列化/反序列化
        Assert.assertEquals(data.getSalary() ,((Data) obj).getSalary());
        Assert.assertEquals(data.getGender() ,((Data) obj).getGender());
        Assert.assertEquals(data.getSignature() ,((Data) obj).getSignature());
        System.out.println(obj);

    }

}
