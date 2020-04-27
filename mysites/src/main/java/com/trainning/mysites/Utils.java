package com.trainning.mysites;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class Utils {
    public String itod(Integer idate){

        if (idate == null) return "";
        Date d = new Date();
        d.setTime(idate*1000L);//setTime()方法参数是毫秒，参数idate是整型的乘以1000后溢出
       // DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        //定义日期格式化器
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(d);
    }

}
