package com.oauth.oauthservice.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oauth.oauthservice.mapper.RoleMapper;
import com.oauth.oauthservice.model.Role;
import com.oauth.oauthservice.service.IRoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 角色服务实现类
 * @author lihaodong
 * @since 2019-03-14
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMapper roleMapper;

    @Override
    public List<Role> findRolesByUserId(Integer userId) {
        return roleMapper.findRolesByUserId(userId);
    }


}
