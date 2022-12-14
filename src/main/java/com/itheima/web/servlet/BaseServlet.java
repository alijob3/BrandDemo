package com.itheima.web.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

//替换httpServlet
public class BaseServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取请求路径
        String uri = req.getRequestURI();//短路径  /虚拟/brand/selectAll
        //获取最后一段路径、方法名
        int index=uri.lastIndexOf('/');
        String methodName = uri.substring(index + 1);
        //执行方法
        Class<? extends BaseServlet> aClass = this.getClass();
        try {
            Method method = aClass.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (Exception e) {
            e.printStackTrace();

        }


    }
}
