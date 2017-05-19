package com.zlikun.jee;

import com.caucho.hessian.io.BeanSerializerFactory;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;
import com.caucho.hessian.io.SerializerFactory;
import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * 通过SerializerFactory设置allowNonSerializable属性为true，可以实现未实现Serializable接口类序列化/反序列化
 * @auther zlikun <zlikun-dev@hotmail.com>
 * @date 2017/5/19 09:27
 */
public class HessianSerializationNonSerializableTest {

    @Test
    public void test() throws IOException, ClassNotFoundException {
        // 准备数据
        Data data = DataFactory.get() ;

        SerializerFactory sf = BeanSerializerFactory.createDefault() ;
        sf.setAllowNonSerializable(true);

        // 执行序列化
        ByteArrayOutputStream out = new ByteArrayOutputStream() ;
        HessianOutput hessianOutput = new HessianOutput(out) ;
        hessianOutput.setSerializerFactory(sf);
        hessianOutput.writeObject(data);
        hessianOutput.close();
        byte [] bytes = out.toByteArray() ;
        out.close();
        // 测试断言
        Assert.assertNotNull(bytes);
        Assert.assertEquals(311 ,bytes.length);
        System.out.println(new String(bytes));

        // 执行反序列化
        HessianInput hessianInput = new HessianInput(new ByteArrayInputStream(bytes)) ;
        hessianInput.setSerializerFactory(sf);
        Object obj = hessianInput.readObject() ;
        hessianInput.close();
        // 测试断言
        Assert.assertNotNull(obj);
        Assert.assertTrue(obj instanceof Data);
        Assert.assertNull(((Data) obj).getEmail());     // 使用 transient 关键字修饰的字段将不参与序列化/反序列化
        Assert.assertEquals(data.getSalary() ,((Data) obj).getSalary());
        Assert.assertEquals(data.getGender() ,((Data) obj).getGender());
        Assert.assertEquals(data.getSignature() ,((Data) obj).getSignature());
        System.out.println(obj);

    }

}
