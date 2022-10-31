package com.itheima.web.servlet;

import com.alibaba.fastjson.JSON;
import com.itheima.pojo.Brand;
import com.itheima.pojo.pageBean;
import com.itheima.service.BrandService;
import com.itheima.service.impl.BrandServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@WebServlet("/brand/*")
public class BrandServlet extends BaseServlet{
    private BrandService brandService= new BrandServiceImpl();

    public void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Brand> brands=brandService.selectAll();
        String jsonString= JSON.toJSONString(brands);
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(jsonString);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        BufferedReader reader = request.getReader();
        String s = reader.readLine();
        Brand brand = JSON.parseObject(s, Brand.class);
        brandService.add(brand);
        response.getWriter().write("success");
    }
    public void delete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String s=request.getReader().readLine();
        int id= JSON.parseObject(s,Integer.class);
        brandService.delete(id);
        response.getWriter().write("success");
    }
    public void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String s = request.getReader().readLine();
        Brand brand = JSON.parseObject(s, Brand.class);
        brandService.update(brand);
        response.getWriter().write("success");
    }

    public void deleteByIds(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String s=request.getReader().readLine();
        int []ids=JSON.parseObject(s, int[].class);
        brandService.deleteByIds(ids);
        response.getWriter().write("success");
    }

    public void selectByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取当前页码和每页条数，通过request
        String _currentPage=request.getParameter("currentPage");
        String _pageSize=request.getParameter("pageSize");
        int currentPage=Integer.parseInt(_currentPage);
        int pageSize=Integer.parseInt(_pageSize);
        pageBean<Brand> pageBean=brandService.selectByPage(currentPage,pageSize);
        String jsonString= JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(jsonString);
    }

    public void selectByPageAndCondition(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //获取当前页码和每页条数，通过request
        //获取查询条件brand，使用JSON
        String s = request.getReader().readLine();
        Brand brand=JSON.parseObject(s,Brand.class);
        System.out.println(brand);
        String _currentPage=request.getParameter("currentPage");
        String _pageSize=request.getParameter("pageSize");
        int currentPage=Integer.parseInt(_currentPage);
        int pageSize=Integer.parseInt(_pageSize);
        pageBean<Brand> pageBean=brandService.selectByPageAndCondition(currentPage,pageSize,brand);
        String jsonString= JSON.toJSONString(pageBean);
        response.setContentType("text/json;charset=UTF-8");
        response.getWriter().write(jsonString);
    }



}
