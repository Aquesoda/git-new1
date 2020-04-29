package com.trainning.mysites.Interceptor;

import com.trainning.mysites.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class checkUserInterceptor implements HandlerInterceptor {
    //不要拦截的页面
    String[] res = {".js",".css",".html",".htm","/login","error",".jpg",".gif",".png"};


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        for (String s : res) {
            if (path.endsWith(s)){
                //扫描到上面的页面 就直接跳过去
                return HandlerInterceptor.super.preHandle(request, response, handler);
            }
        }
        HttpSession session = request.getSession(); //获得会话对象
        User user = (User) session.getAttribute("user");//获得会话范围内的user对象
        if (user==null||user.getUid()==null||user.getUid()<=0){
            response.sendRedirect("/login");
            return false;
        }
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
