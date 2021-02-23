package com.csl.impl.shopping;

import com.csl.mapper.shopping.IndexMapperCustom;
import com.csl.pojo.shopping.Carousel;
import com.csl.pojo.shopping.Category;
import com.csl.pojo.vo.shopping.CategoryVO;
import com.csl.service.shopping.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    IndexMapperCustom indexMapperCustom;

    @Override
    public List<Carousel> queryCarousels(Integer is_show) {
        return indexMapperCustom.queryCarousels(is_show);
    }

    @Override
    public List<Category> queryRootCategory() {
        return indexMapperCustom.queryRootCategory();
    }

    @Override
    public List<CategoryVO> querySubCatogory(Integer rootCatId){
        return indexMapperCustom.querySubCatogory(rootCatId);
    }

}
