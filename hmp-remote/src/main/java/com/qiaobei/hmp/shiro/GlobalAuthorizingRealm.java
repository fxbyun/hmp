package com.qiaobei.hmp.shiro;

import com.alibaba.druid.util.StringUtils;
import com.qiaobei.hmp.modules.entity.RemoteUser;
import com.qiaobei.hmp.modules.service.RemotePermissionService;
import com.qiaobei.hmp.modules.service.RemoteRoleService;
import com.qiaobei.hmp.modules.service.RemoteUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springside.modules.utils.Encodes;

import java.util.List;


//@Service("globalAuthorizingRealm")
public class GlobalAuthorizingRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalAuthorizingRealm.class);

    @Autowired
    private RemoteUserService remoteUserService;

    @Autowired
    private RemoteRoleService remoteRoleService;

    @Autowired
    private RemotePermissionService remotePermissionService;


    /**
     * 登陆认证（登录调用的验证，身份的验证）
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        //登录名是用户的手机号码
        String userName = upToken.getUsername();

        if (StringUtils.isEmpty(userName)) {
            LOGGER.warn("系统登录手机号码为空！");
            throw new AccountException("登录账户手机号码为空！");
        }

        //错误信息
        String errorMsg;
        //根据用户名加载记录
        RemoteUser user;
        try {
            user = remoteUserService.queryUserByUserName(userName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationException("用户查找异常失败！");
        }

        if (null == user) {
            errorMsg = userName + "是新用户，请先进行注册！";
            LOGGER.warn(errorMsg);
            throw new UnknownAccountException(errorMsg);
        } else if (user.getStatus() == RemoteUser.Status.NOPASS) {
            errorMsg = userName + "该用户还未通过审核，请耐心等待！";
            LOGGER.warn(errorMsg);
            throw new LockedAccountException(errorMsg);

        } else if (user.getStatus() == RemoteUser.Status.DISABLE) {
            errorMsg = userName + "该用户已被禁用！";
            LOGGER.warn(errorMsg);
            throw new DisabledAccountException(errorMsg);
        }
        byte[] salt = Encodes.decodeHex(user.getSalt());
        return new SimpleAuthenticationInfo(user, user.getPassword(), ByteSource.Util.bytes(salt), getName());
        //return new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),getName());
    }


    /**
     * 角色授予、权限授予
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) principals.fromRealm(getName()).iterator().next();
        RemoteUser user = null;
        try {
            user = remoteUserService.queryUserByUserName(userName);
        } catch (Exception e) {
            LOGGER.warn("用户查找异常！");
            throw new RuntimeException();
        }

        if (null == user) {
            return null;
        }

        //添加session
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.setAttribute("userInfo", user);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        List<String> roles = Lists.newArrayList();

        try {
            //为用户添加角色
            user.getRoleList().forEach(role -> roles.add(role.getName()));
            info.addRoles(roles);

            //为用户添加权限
            List<String> permissions = Lists.newArrayList();

            user.getRoleList().forEach(remoteRole -> {
                remoteRole.getPermissionList().forEach(permission -> permissions.add(permission.getCode()));
            });
            info.addStringPermissions(permissions);

        } catch (Exception e) {
            LOGGER.warn("用户角色授权失败！");
            throw new RuntimeException("Fetch roles and permissions for user[" + userName + "] failed: ", e);
        }

        return info;
    }


}
