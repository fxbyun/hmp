package com.qiaobei.hmp.security;

import com.qiaobei.hmp.modules.entity.MobileUser;
import com.qiaobei.hmp.modules.exception.NotOpenIdException;
import com.qiaobei.hmp.service.MobileUserService;
import com.qiaobei.hmp.support.Constants;
import com.qiaobei.hmp.support.UserStaticEnum;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2016/8/24 0024.
 */
public class MobileUserRealm extends AuthorizingRealm {

    @Autowired
    private HttpServletRequest request;

    protected MobileUserService mobileUserService;
    public void setMobileUserService(MobileUserService mobileUserService){
        this.mobileUserService=mobileUserService;
    }

    /**
     * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addRole("mobileUser");
        return info;
    }

    /**
     * 认证回调函数,登录时调用.
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken authToken=(UsernamePasswordToken)token;
        //得到身份
        String mobile = authToken.getUsername();
        //得到凭证
        String code=String.valueOf(authToken.getPassword());
        //得到验证码
        String authCode=(String) request.getSession().getAttribute(Constants.AUTH_CODE);
        //得到微信的openId(用户自动登录部分)
        String openId =(String) SecuritySupport.getSubject().getSession().getAttribute("openid");
        if(mobile.equals("YJZ")&&code.equals("YJZ")){
            if(StringUtils.isEmpty(openId)){
                throw new NotOpenIdException("没有获取到openId");
            }
            MobileUser user = mobileUserService.findMobileUserByOpenId(openId);
            if(user==null){
                throw new ExpiredCredentialsException("该用户是第一次登录，跳转到登录页面");
            }

            /*这里token.getCredentials()是什么*/
            return new SimpleAuthenticationInfo(user, token.getCredentials(), getName());
        }

        if(authCode==null){
            throw new ExpiredCredentialsException("无验证码或者验证码已失效");
        }
        MobileUser user=null;
        if(authCode.equals(code)){
            MobileUser userData = mobileUserService.findMobileUser(mobile);
            if (userData == null) {
                //将该用户存入数据库中
                System.out.println("用户不存在");
                user = new MobileUser();
                //将OpenId存到数据库中，下一次登录就可以自动登录
                if(!StringUtils.isEmpty(openId)){
                    user.setOpenId(openId);
                }

                user.setMobile(mobile);

                mobileUserService.addMobileUser(user);
                user=mobileUserService.findMobileUser(user.getMobile());
                return new SimpleAuthenticationInfo(user, token.getCredentials(), UserStaticEnum.NEW.getName());
            }else{
                return new SimpleAuthenticationInfo(userData, token.getCredentials(), UserStaticEnum.OLD.getName());
            }
        }else{
            //验证码错误
            throw new IncorrectCredentialsException("验证码错误");
        }
    }
}
