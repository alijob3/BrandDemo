package com.itheima.service;

import com.itheima.pojo.Brand;
import com.itheima.pojo.pageBean;

import java.util.List;

public interface BrandService {
    List<Brand> selectAll();
    void add(Brand brand);
    void update(Brand brand);
//    Brand selectById(int id);
    void delete(int id);
    void deleteByIds(int []ids);
    pageBean<Brand> selectByPage(int currentPage,int pageSize);
    pageBean<Brand> selectByPageAndCondition(int currentPage,int pageSize,Brand brand);


}
