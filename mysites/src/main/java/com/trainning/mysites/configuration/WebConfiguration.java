package com.trainning.mysites.configuration;


import com.trainning.mysites.Interceptor.checkUserInterceptor;
import com.trainning.mysites.Utils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    //把拦截器作为Bean让spring管理，以防止Spring无法找到拦截器
    @Bean
    public checkUserInterceptor checkUserInterceptor(){
        return new checkUserInterceptor();
    }

    //添加拦截器的配置 注册自定义的拦截器
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(checkUserInterceptor());
    }

    @Bean
    public Utils utils(){
        return new Utils();
    }


    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addFormatter(new Formatter<LocalDate>() {
            /**
             * 解析器，将字符串转换成日期
             * @param s 格式为：2019-01-02的字符串
             * @param locale    本地化数据
             * @return  日期
             * @throws ParseException 如果提供的text格式不正确抛出异常
             */
            @Override
            public LocalDate parse(String s, Locale locale) throws ParseException {
                return LocalDate.parse(s);//调用parse方法把字符串解析成日期对象
            }

            /**
             * //将日期转换成字符串
             * @param localDate 日期数据
             * @param locale    本地化数据，就是当地时间
             * @return
             */
            @Override
            public String print(LocalDate localDate, Locale locale) {

                //定义一个格式化器对象
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd", locale);

                //调用格式化器的format方法把日期转换成字符串
                return dtf.format(localDate);
            }
        });
    }
}
