package com.csl.service.shopping;

import com.csl.pojo.shopping.Carousel;
import com.csl.pojo.shopping.Category;
import com.csl.pojo.vo.shopping.CategoryVO;

import java.util.List;

public interface IndexService {
    //获取轮播图
    public List<Carousel> queryCarousels(Integer is_show);

    //获取商品根级分类（一级分类）
    public List<Category> queryRootCategory();

    //根据一级分类id查询而子分类信息（二级和三级）
    public List<CategoryVO> querySubCatogory(Integer rootCatId);
}
