package com.dailywear.base_structure.service.user.impl;

import com.dailywear.base_structure.common.entity.User;
import com.dailywear.base_structure.mapper.user.UserMapper;
import com.dailywear.base_structure.service.user.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author luoming123
 * @since 2020-07-09
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
