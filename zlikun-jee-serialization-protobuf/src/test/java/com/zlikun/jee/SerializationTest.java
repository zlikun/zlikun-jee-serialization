package com.zlikun.jee;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * @auther zlikun <zlikun-dev@hotmail.com>
 * @date 2017/5/19 10:50
 */
public class SerializationTest {

    @Test
    public void test() throws IOException {

        // 准备数据
        PersonStructure.Person.Builder builder = PersonStructure.Person.newBuilder();
        builder.setId(10000);
        builder.setName("zlikun");
        builder.setEmail("managerg@zlikun.com");
        builder.setGender(PersonStructure.Person.Gender.MALE) ;     // .proto文件中描述为 required 的字段，必须设置非空值
        builder.addFriends("Kevin");
        builder.addFriends("Ross");
        builder.addFriends("Jinx");
        PersonStructure.Person person = builder.build();

        // 执行序列化
        ByteArrayOutputStream out = new ByteArrayOutputStream() ;
        person.writeTo(out);
        byte [] bytes = out.toByteArray() ;
        out.close();
        // 执行断言
        Assert.assertNotNull(bytes);
        Assert.assertEquals(53 ,bytes.length);
        System.out.println(new String(bytes));

        // 执行反序列化
        ByteArrayInputStream in = new ByteArrayInputStream(bytes) ;
        PersonStructure.Person person2 = PersonStructure.Person.parseFrom(in) ;
        // 执行断言
        Assert.assertNotNull(person2);
        Assert.assertEquals(person.getEmail() ,person2.getEmail());
        Assert.assertEquals(person.getFriends(1) ,person2.getFriends(1));
    }

}
