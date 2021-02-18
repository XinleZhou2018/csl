package com.csl.controller;

import com.csl.objects.HttpException;
import com.csl.pojo.Match;
import com.csl.service.MatchService;
import com.csl.utils.ResultObject;
import com.github.pagehelper.PageInfo;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController //@RestController注解中包含了@ResponseBody注解
@RequestMapping("/match")
@Validated
public class MatchController {

    @Autowired
    private MatchService matchService;

    @RequestMapping("/hello")
    public String hello(){
        throw new HttpException(10001, 400);
//        return "";
    }

    @GetMapping("/matches")
    public ResultObject getMatches(
            @RequestParam(required = false) @NotNull(message = "page不能为空") @Min(value = 1, message = "page大于1") Integer page,
            @RequestParam(required = false) @NotNull(message = "pageSize不能为空") @Min(value = 1, message = "pageCount大于1") Integer pageSize) {
       PageInfo pageInfo = matchService.queryMatches(page, pageSize);
       return ResultObject.success(pageInfo);
    }
}
