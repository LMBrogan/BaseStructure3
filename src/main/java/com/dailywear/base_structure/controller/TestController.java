package com.dailywear.base_structure.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Api(tags = "测试相关接口")
public class TestController {

    @RequestMapping("test")
    @ApiOperation("测试系统test接口")
    public Map<String, Object> test(){
        Map<String, Object> resultMap = new HashMap<>(16);
        resultMap.put("flag",0);
        resultMap.put("data","data");
        return resultMap;
    }
}
