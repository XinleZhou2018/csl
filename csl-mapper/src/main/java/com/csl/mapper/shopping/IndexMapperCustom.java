package com.csl.mapper.shopping;

import com.csl.pojo.shopping.Carousel;
import com.csl.pojo.shopping.Category;
import com.csl.pojo.vo.shopping.CategoryVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IndexMapperCustom {
    public List<Carousel> queryCarousels(@Param("is_show") Integer is_show);

    public List<Category> queryRootCategory();

    public List<CategoryVO> querySubCatogory(@Param("rootCatId") Integer rootCatId);
}
