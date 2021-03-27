package com.csl.controller.shopping;

import com.csl.enums.YesOrNo;
import com.csl.pojo.shopping.Carousel;
import com.csl.pojo.shopping.Category;
import com.csl.pojo.vo.shopping.CategoryVO;
import com.csl.service.shopping.IndexService;
import com.csl.utils.ResultObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.print.DocFlavor;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@Validated
@RequestMapping("/index")
public class IndexController {

    @Autowired
    private IndexService indexService;

    //首页轮播图，不需要分页，根据数据库is_show字段显示轮播图，sort字段排序。
    @GetMapping("/carousel")
    public ResultObject getCarousel(){
        List<Carousel> carousels = indexService.queryCarousels(YesOrNo.YES.type);
        return ResultObject.success(carousels);
    }

    //查询商品分类，一级分类
    @GetMapping("/rootCategory")
    public ResultObject getRootCategory(){
        List<Category> categories = indexService.queryRootCategory();
        return ResultObject.success(categories);
    }

    //根据用户选择的一级分类，加载二级和三级分类
    //一个二级分类对应很多三级分类，返回的是一个数组，数组每个对象是一个二级分类和它下面的三级分类（List）
    //自连接查询
    @GetMapping("/subCategory")
    public ResultObject getSubCategory(
            @RequestParam(required = false) @NotNull(message = "rootCatId不能为空") Integer rootCatId){
        List<CategoryVO> categoryVOList = indexService.querySubCatogory(rootCatId);
        return ResultObject.success(categoryVOList);
    }
}
