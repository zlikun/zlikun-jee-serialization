package com.zlikun.jee;

import com.zlikun.jee.Gender;

import java.io.Serializable;
import java.util.Date;

/**
 * 测试用实体类
 * @auther zlikun <zlikun-dev@hotmail.com>
 * @date 2017/5/18 18:31
 */
public class Data implements Serializable {

    private Long id ;
    private String name ;
    private String mobile ;
    private transient String email ;
    private Gender gender ;
    private Date birthday ;
    private String signature ;
    private Float salary ;

    @Override
    public String toString() {
        return String.format("id = %d ,name = %s ,mobile = %s ,email = %s ,gender = %s ,birthday = %s ,signature = %s ,salary = %.2f"
                ,this.id ,this.name ,this.mobile ,this.email ,this.gender ,this.birthday ,this.signature ,this.salary) ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public Float getSalary() {
        return salary;
    }

    public void setSalary(Float salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
