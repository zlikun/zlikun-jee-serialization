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
```
# 注意事项
1、Hessian2Output在使用时，必须在获取字节数据前关闭，否则得到的字节数组长度为0，HessianOutput则无此问题(尽量都使用该用法)
2、Hessian2Output序列化的字节数组长度比HessianOutput略长，说见测试用例
3、Hessian序列化与Java原生序列化相同部分：需要实现Serializable接口、transient关键字不参与序列化/反序列化
4、通过SerializerFactory设置allowNonSerializable属性为true，可以实现未实现Serializable接口类序列化/反序列化
```

#### [Protobuf](https://github.com/google/protobuf/)序列化

- 参考资料(需要翻墙)
    - <https://developers.google.com/protocol-buffers/>
    - <https://developers.google.com/protocol-buffers/docs/overview>
    - <https://developers.google.com/protocol-buffers/docs/proto>
    - <https://developers.google.com/protocol-buffers/docs/proto3>
    - <https://developers.google.com/protocol-buffers/docs/reference/proto2-spec>
    - <https://developers.google.com/protocol-buffers/docs/reference/proto3-spec>
    - <https://developers.google.com/protocol-buffers/docs/javatutorial>
    - <https://developers.google.com/protocol-buffers/docs/reference/java-generated>
    - <https://developers.google.com/protocol-buffers/docs/reference/java/>
    - <https://developers.google.com/protocol-buffers/docs/encoding>

- 使用说明

```
# 下载相关组件：https://github.com/google/protobuf/releases
# https://github.com/google/protobuf/releases/download/v3.3.0/protoc-3.3.0-win32.zip
# https://github.com/google/protobuf/releases/download/v3.3.0/protoc-3.3.0-linux-x86_64.zip
# https://github.com/google/protobuf/releases/download/v3.3.0/protobuf-java-3.3.0.tar.gz

# protoc-3.3.0-win32.zip 中包含 protoc.exe ，是protobuf代码生成工具

# 1、定义protobuf数据结构，需要编写一个.proto文件，如下示例
# PersonStructure.proto (注意文件名不要与内部定义的message名相同)
syntax = "proto2";

package com.zlikun.jee;

message Person {
	
	// ID (必需)
	required int32 id = 1;
	
	// 姓名 (必需)
	required string name = 2;
	
	// email (可选)
	optional string email = 3;

	// 朋友 (集合)
	repeated string friends = 4;

	required Gender gender = 5;

	enum Gender {
		MALE = 0;
		FEMALE = 1;
	}
}

# 2、使用protoc.exe生成Java代码
$ protoc --version
libprotoc 3.3.0
$ protoc -I=F:\temporary\protobuf --java_out=F:\temporary\protobuf\java F:\temporary\protobuf\PersonStructure.proto

# 3、测试序列化、反序列化

# 4、Maven整合
# https://github.com/sergei-ivanov/maven-protoc-plugin
# https://www.xolstice.org/protobuf-maven-plugin/
# https://www.xolstice.org/protobuf-maven-plugin/usage.html
# https://www.xolstice.org/protobuf-maven-plugin/examples/protobuf-toolchain.html
# pom.xml
<plugin>
    <groupId>org.xolstice.maven.plugins</groupId>
    <artifactId>protobuf-maven-plugin</artifactId>
    <version>0.5.0</version>
    <configuration>
        <protocExecutable>${project.basedir}/src/main/resources/protoc.exe</protocExecutable>
        <outputDirectory>${project.basedir}/src/main/java</outputDirectory>
    </configuration>
    <executions>
        <execution>
            <goals>
                <goal>compile</goal>
                <goal>test-compile</goal>
            </goals>
        </execution>
    </executions>
</plugin>

$ mvn clean protobuf:compile
```

#### 其它序列化类库
- [Kryo](https://github.com/EsotericSoftware/kryo)
- [FST](https://github.com/RuedigerMoeller/fast-serialization)
    - <https://github.com/RuedigerMoeller/fast-serialization/wiki/Serialization>
    - <http://ruedigermoeller.github.io/fast-serialization/>
- [Protostuff](http://www.protostuff.io/)
