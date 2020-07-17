package com.dailywear.base_structure.controller;


import com.dailywear.base_structure.annotation.LogAppender;
import com.dailywear.base_structure.common.entity.User;
import com.dailywear.base_structure.service.user.IUserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author luoming123
 * @since 2020-07-09
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userServiceImpl;

    @LogAppender
    @GetMapping("getUser/{id}")
    @ApiOperation("根据id更新用户的接口")
    public User GetUser(@ApiParam(name = "id", value = "用户ID", required = true) @PathVariable int id){
        User user = userServiceImpl.selectById(id);
        return user;
    }
}

