package com.oauth.oauthservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oauth.oauthservice.model.Role;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author lihaodong
 * @since 2019-03-14
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> findRolesByUserId(@Param("userId") Integer userId);
}

