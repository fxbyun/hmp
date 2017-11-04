package com.qiaobei.hmp.service;

import com.qiaobei.hmp.modules.entity.Patient;
import com.qiaobei.hmp.support.Constants;
import org.apache.shiro.authc.*;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springside.modules.utils.Encodes;

import javax.annotation.PostConstruct;

public class PatientRealm extends AuthorizingRealm {

    protected PatientService patientService;

    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws
            AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String account = token.getUsername();
        Patient p = patientService.getPatientByUid(account);
        if (p != null) {
            byte[] salt = Encodes.decodeHex(p.getSalt());
            return new SimpleAuthenticationInfo(p, p.getPassword(),
                    ByteSource.Util.bytes(salt), account);
        }
        return null;
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        Patient patient = (Patient) principals.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("Patient");
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
