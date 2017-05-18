package com.zlikun.jee;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Date;

/**
 * @auther zlikun <zlikun-dev@hotmail.com>
 * @date 2017/5/18 19:25
 */
public class Car implements Externalizable {

    private Brand brand ;
    private Integer speed ;
    private String color ;
    private Date productionDate ;

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(this.brand);
        out.writeInt(speed);
        out.writeBytes(color);
        out.writeObject(productionDate);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.brand = (Brand) in.readObject();
//        this.speed = in.readInt() ;   // 使speed属性不反序列化
        this.color = in.readLine() ;
        this.productionDate = (Date) in.readObject();
    }

    @Override
    public String toString() {
        return String.format("brand = %s ,speed = %d ,color = %s ,productionDate = %s"
                ,this.brand ,this.speed ,this.color ,this.productionDate) ;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getProductionDate() {
        return productionDate;
    }

    public void setProductionDate(Date productionDate) {
        this.productionDate = productionDate;
    }
}
