package com.mikasa.shiro;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

public class ShiroDbRealm extends AuthorizingRealm {
	
	/*@Resource
	private UserMapper userMapper;*/
	
	/**
	 * 登录用户认证
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token){
		//获取基于用户名和密码的令牌  
        //实际上这个authcToken是从LoginController里面currentUser.login(token)传过来的  
        //两个token的引用都是一样的,本例中是org.apache.shiro.authc.UsernamePasswordToken@33799a1e  
        UsernamePasswordToken usertoken = (UsernamePasswordToken)token;  
        System.out.println("验证当前Subject时获取到token为" + ReflectionToStringBuilder.toString(token, ToStringStyle.MULTI_LINE_STYLE));
        /*Map<String,Object> user = userMapper.getUserByEmail(usertoken.getUsername());
		try {
			if (!user.isEmpty()) {
				AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(user.get("email"),user.get("password"),this.getName());
				this.setSession("currentUser", user.get("name"));
				return authcInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnknownAccountException();
		} 
		return null;*/
        //此处无需比对,比对的逻辑Shiro会做,我们只需返回一个和令牌相关的正确的验证信息  
        //说白了就是第一个参数填登录用户名,第二个参数填合法的登录密码(可以是从数据库中取到的,本例中为了演示就硬编码了)  
        //这样一来,在随后的登录页面上就只有这里指定的用户和密码才能通过验证  
        if("admin".equals(usertoken.getUsername())){  
            AuthenticationInfo authcInfo = new SimpleAuthenticationInfo("admin", "mryxMRYX!QA", this.getName());  
            this.setSession("currentUser", "管理员");  
            return authcInfo;  
        } 
        //没有返回登录用户名对应的SimpleAuthenticationInfo对象时,就会在LoginController中抛出UnknownAccountException异常  
        return null;
	}
	
	/**
	 * 权限认证
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals){
        String currentUsername = (String)principals.fromRealm(this.getName()).iterator().next(); 
        /*List<Map<String,Object>> list = userMapper.getRoleByEmail(currentUsername);
        List<String> roleList = new ArrayList<String>();  
        List<String> permissionList = new ArrayList<String>();  
        //从数据库中获取当前登录用户的详细信息  
		try {
			if (!list.isEmpty()) {
				for (int i = 0; i < list.size(); i++) {
					Map<String, Object> role = list.get(i);
					roleList.add(role.get("name").toString());
					permissionList.add(role.get("permissions").toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new UnauthorizedException();
		}
		//为当前用户设置角色和权限  
		SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
		simpleAuthorInfo.addRoles(roleList);
		simpleAuthorInfo.addStringPermissions(permissionList);
		return simpleAuthorInfo;*/
      
        SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();  
        //实际中可能会像上面注释的那样从数据库取得  
        if(null!=currentUsername && "admin".equals(currentUsername)){  
            //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色    
            simpleAuthorInfo.addRole("admin"); 
            //添加权限  
            simpleAuthorInfo.addStringPermission("admin:manage");  
            System.out.println("已为用户[admin]赋予了[admin]角色和[admin:manage]权限");  
            return simpleAuthorInfo;  
        } 
        //若该方法什么都不做直接返回null的话,就会导致任何用户访问/admin/listUser.jsp时都会自动跳转到unauthorizedUrl指定的地址  
        //详见applicationContext.xml中的<bean id="shiroFilter">的配置  
        return null;  
	}
	
	/** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */  
    private void setSession(Object key, Object value){  
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){  
            Session session = currentUser.getSession();  
            System.out.println("Session默认超时时间为[" + session.getTimeout() + "]毫秒");  
            if(null != session){  
                session.setAttribute(key, value);  
            }  
        }  
    }

}
