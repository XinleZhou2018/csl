package com.csl.service;

import com.csl.pojo.Match;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface MatchService {
    public String test();

    public PageInfo queryMatches(Integer page, Integer pageSize);
}
