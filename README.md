# zlikun-jee-serialization
Java序列化库学习，包含：Java Serialization 、Hessian 、Protobuf等

#### Java原生序列化

- java.ioSerializable

```
# 不影响序列化
1、注释不影响序列化结果(实际编译为字节码时，注释已被丢弃)
2、字段顺序、Getter/Setter顺序不影响序列化结果(实际编译为字节码时，字段顺序与编译后的文件中字段顺序无关)
3、类文件格式、排版等不影响序列化结果(原因同上)
4、删除Getter/Setter，不影响序列化
5、添加方法(包含重载Getter/Setter)，不影响序列化

# 影响序列化
1、字段类型变更，影响序列化结果
2、增加、减少字段，影响序列化结果
3、修改包名，影响序列化结果

# 其它说明
1、添加方法、字段，serialVersionUID值会发生变化，导致反序列化出错，可以通过指定serialVersionUID为固定值解决，如：1L
2、减少方法、字段，与添加有同样问题，解决方法也相同
3、高版本类不能向低版本反序列化，如：1.7 -> 1.6 (Error:java: 无效的源版本： 1.7)，反之则没问题
4、transient 关键字修饰过的字段不参与序列化
5、序列化会忽略静态变量，即序列化不保存静态变量的状态
```

- java.io.Externalizable
```
# 通过Externalizable实现序列化、反序列化的好处是可以精确控制类的序列化行为
```

#### [Hessian](http://hessian.caucho.com/)序列化


#### [Protobuf](https://github.com/google/protobuf/)序列化


#### 其它序列化类库
- [Kryo](https://github.com/EsotericSoftware/kryo)
- [FST](https://github.com/RuedigerMoeller/fast-serialization)
    - <http://ruedigermoeller.github.io/fast-serialization/>
- [Protostuff]()http://www.protostuff.io/)
