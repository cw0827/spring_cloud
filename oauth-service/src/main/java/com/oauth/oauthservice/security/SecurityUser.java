package com.oauth.oauthservice.security;

import com.oauth.oauthservice.model.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * @Author 李号东
 * @Description 用户身份权限认证类 登陆身份认证
 * @Date 13:29 2019-03-16
 * @Param
 * @return
 **/
public class SecurityUser implements UserDetails {
    private static final long serialVersionUID = 1L;

    private Integer id;
    private String username;
    private String password;
    private List<Role> role;
    private Date lastPasswordResetDate;

    public SecurityUser(Integer id, String username, String password, List<Role> role ) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public SecurityUser(String username, String password, List<Role> role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public SecurityUser(Integer id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }


    //返回分配给用户的角色列表
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Role role : role) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    //账户是否未过期,过期无法验证
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //指定用户是否解锁,锁定的用户无法进行身份验证
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //指示是否已过期的用户的凭据(密码),过期的凭据防止认证
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否可用 ,禁用的用户不能身份验证
    @Override
    public boolean isEnabled() {
        return true;
    }


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRole() {
        return role;
    }

    public void setRole(List<Role> role) {
        this.role = role;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }
}
