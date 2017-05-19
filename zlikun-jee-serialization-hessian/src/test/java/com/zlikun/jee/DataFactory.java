package com.zlikun.jee;

import java.util.Date;

/**
 * @auther zlikun <zlikun-dev@hotmail.com>
 * @date 2017/5/18 19:53
 */
public class DataFactory {

    /**
     * 获取Data实例
     * @return
     */
    public static final Data get() {

        Data data = new Data() ;
        data.setBirthday(new Date());
        data.setGender(Gender.FEMALE);
        data.setId(10000L);
        data.setMobile("12100000000");
        data.setEmail("manager@zlikun.com");
        data.setName("Ross");
        data.setSalary(12000.00F);
        data.setSignature("林花谢了春红 / 太匆匆 / 无奈朝来寒雨 / 晚来风 / 胭脂泪 / 相留醉 / 几时重 / 自是人生长恨 / 水长东");

        return data ;
    }

}
