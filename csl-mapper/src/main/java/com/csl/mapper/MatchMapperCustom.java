package com.csl.mapper;

import com.csl.my.mapper.MyMapper;
import com.csl.pojo.Match;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatchMapperCustom extends MyMapper<Match> {
    public List<Match> queryMatches();
}
