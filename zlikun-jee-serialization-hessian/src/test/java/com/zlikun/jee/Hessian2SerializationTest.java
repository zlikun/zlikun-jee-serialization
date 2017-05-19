package com.zlikun.jee;

import com.caucho.hessian.io.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @auther zlikun <zlikun-dev@hotmail.com>
 * @date 2017/5/19 09:41
 */
public class Hessian2SerializationTest {

    @Test
    public void test() throws IOException, ClassNotFoundException {
        // 准备数据
        Data data = DataFactory.get() ;

        // 执行序列化
        ByteArrayOutputStream out = new ByteArrayOutputStream() ;
        Hessian2Output hessian2Output = new Hessian2Output(out) ;
        hessian2Output.writeObject(data);
        hessian2Output.close();
        // 获取字节数组前，必须先关闭Hessian2Output，否则取得字节数组长度为0(原因暂不清楚)
        byte [] bytes = out.toByteArray() ;
        out.close();
        // 测试断言
        Assert.assertNotNull(bytes);
        Assert.assertEquals(319 ,bytes.length);     // 比HessianOutput序列化的字节数组要长(319 > 311)
        System.out.println(new String(bytes));

        // 执行反序列化
        Hessian2Input hessian2Input = new Hessian2Input(new ByteArrayInputStream(bytes)) ;
        Object obj = hessian2Input.readObject() ;
        hessian2Input.close();
        // 测试断言
        Assert.assertNotNull(obj);
        Assert.assertTrue(obj instanceof Data);
        Assert.assertNull(((Data) obj).getEmail());         // 使用 transient 关键字修饰的字段将不参与序列化/反序列化
        Assert.assertEquals(data.getSalary() ,((Data) obj).getSalary());
        Assert.assertEquals(data.getGender() ,((Data) obj).getGender());
        Assert.assertEquals(data.getSignature() ,((Data) obj).getSignature());
        System.out.println(obj);

    }

}
