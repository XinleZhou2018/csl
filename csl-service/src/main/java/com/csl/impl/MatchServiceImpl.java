package com.csl.impl;

import com.csl.mapper.MatchMapperCustom;
import com.csl.pojo.Match;
import com.csl.service.MatchService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MatchServiceImpl implements MatchService {

    @Autowired
    private MatchMapperCustom matchMapperCustom;

    @Override
    public String test() {
        double a = 1 / 0;
        return "test";
    }

    @Override
    public PageInfo queryMatches(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);

        List<Match> matches = matchMapperCustom.queryMatches();
        PageInfo pageInfo = new PageInfo(matches);
        System.out.println("+++++++++++++");
        //PageInfo{pageNum=3, pageSize=3, size=1, startRow=7, endRow=7, total=7, pages=3, list=Page{count=true, pageNum=3, pageSize=3, startRow=6, endRow=9, total=7, pages=3, reasonable=false, pageSizeZero=false}[com.csl.pojo.Match@5b3d44d], prePage=2, nextPage=0, isFirstPage=false, isLastPage=true, hasPreviousPage=true, hasNextPage=false, navigatePages=8, navigateFirstPage=1, navigateLastPage=3, navigatepageNums=[1, 2, 3]}
        System.out.println(pageInfo);
        System.out.println(pageInfo.getList());
        return pageInfo;
    }
}
