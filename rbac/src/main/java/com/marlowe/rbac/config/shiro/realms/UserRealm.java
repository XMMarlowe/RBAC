package com.marlowe.rbac.config.shiro.realms;


import com.auth0.jwt.exceptions.JWTDecodeException;
import com.marlowe.rbac.config.shiro.jwt.JWTToken;
import com.marlowe.rbac.entity.User;
import com.marlowe.rbac.service.IUserService;
import com.marlowe.rbac.utils.JWTUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @auther yincaiTA
 * @date 2021/3/16 11:27
 * @description 用户Realm
 */
@Component
public class UserRealm extends AuthorizingRealm {

    @Autowired
    private IUserService userService;

    @Autowired
    private JWTUtils jwtUtils;

    /**
     * shiro默认采用 UsernamePasswordToken进行处理
     * 而现在我们只处理 JWTToken类型的 token
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 授权(为了方便 直接在一张表中描述了角色和权限)
     *
     * @param principals 身份信息
     * @return AuthorizationInfo授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        // 获取从认证过程保留的 user 对象
//        User user = (User) principals.getPrimaryPrincipal();
//
//        // 获取角色和权限信息
//        String[] roles = user.getRoles().split(",");
//        String[] permissions = user.getPermissions().split(",");
//
//        // 创建授权信息对象
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
//
//        // 添加角色和权限字符串
//        authorizationInfo.addRoles(Arrays.asList(roles));
//        authorizationInfo.addStringPermissions(Arrays.asList(permissions));

//        return authorizationInfo;
        return null;
    }

    /**
     * 认证(因为是已经登陆的用户才会有token 所以不用进行密码验证 并且 shiro也是跳过login的)
     *
     * @param auth 认证信息
     * @return AuthenticationInfo 认证信息
     * @throws AuthenticationException 认证异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        // 处理的是 JWTToken, .getPrincipal() 和 .getCredentials() 都是返回token字符串
        String token = (String) auth.getPrincipal();

        String username;
        try {
            // 从令牌中获取username值  这里可能发生解码异常
            username = this.jwtUtils.getUsername(token);

            // token不包含username信息 || token值中的密钥 是胡乱编造
            if (username == null || !this.jwtUtils.verify(token, username, this.jwtUtils.getSecret())) {
                // 因为token过期了 verify会直接判断false, 所以不能在之后判断是否过期  isExpire也有可能发生解码异常
                if (this.jwtUtils.isExpire(token)) {
                    throw new ExpiredCredentialsException("token过期，请重新登入！");
                }
                // 校验未通过
                throw new IncorrectCredentialsException("token值异常(2)!!!");
            }

        } catch (JWTDecodeException | IllegalArgumentException e) {
            // token的3部分缺失 / 根本解不了码
            e.printStackTrace();
            throw new IncorrectCredentialsException("token值异常(1)!!!!");
        } catch (AuthenticationException e) {
            // 过期/值异常 等
            e.printStackTrace();
            throw new IncorrectCredentialsException(e.getMessage());
        }

        // 数据库查询用户并返回
        User user = this.userService.findUserByUsername(username);
        if (user == null) {
            throw new UnknownAccountException("账号不存在!");
        }

        // 通过认证 直接将user传递给授权过程 反正都通过了认证了 就不需要再在授权过程再走一遍 token->username->user 的过程了
        return new SimpleAuthenticationInfo(user, token, this.getName());
    }

}
