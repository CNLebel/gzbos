package com.gyf.bos.web.realm;

import com.gyf.bos.dao.IFunctionDao;
import com.gyf.bos.dao.IUserDao;
import com.gyf.bos.model.Function;
import com.gyf.bos.model.User;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BOSRealm extends AuthorizingRealm{
    /**
     * 权限-与角色权关
     * @param principal
     * @return
     */
    @Autowired
    private IFunctionDao functionDao;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        //拿到用户
        User loginUser = (User) principal.getPrimaryPrincipal();

        //根据用户ID查权限
        List<Function> functions =  null;

        //admin超级管理员
        if(loginUser.getUsername().equals("admin")){
            functions = functionDao.findAll();
        }else{
            functions = functionDao.findListByUserId(loginUser.getId());
        }

        //往shiro添加权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        for (Function function : functions){
            info.addStringPermission(function.getCode());
        }

        //info.addRole("staff");
        return info;
    }

    /**
     * 登录认证
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Autowired
    private IUserDao userDao;
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("AuthenticationInfo.....");

        //查询数据操作放在realm
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        //1.获取用户名
        String username = upToken.getUsername();

        //2.根据名字查询用户
        User dbUser = userDao.findByUsername(username);

        if(dbUser != null){

            /**
             * Object principal, 把数据库查询的对象
             * Object credentials,证书：写密码自动验证，查询出来的密码
             * String realmName,当前的类名
             */
            //返回AuthenticationInfo对象,【这个对象会自动验证密码】
            SimpleAuthenticationInfo info =
                    new SimpleAuthenticationInfo(dbUser,
                            dbUser.getPassword(),
                            this.getClass().getSimpleName());

            return info;

        }


        return null;
    }
}
