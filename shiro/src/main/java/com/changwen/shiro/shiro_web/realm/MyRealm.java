package com.changwen.shiro.shiro_web.realm;

import java.sql.Connection;

import com.changwen.shiro.shiro_web.dao.UserDao;
import com.changwen.shiro.shiro_web.entity.User;
import com.changwen.shiro.shiro_web.util.DbUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

// 自定义的reaml
public class MyRealm extends AuthorizingRealm{
	private UserDao userDao=new UserDao();
	private DbUtil dbUtil=new DbUtil();
	/**
	 * 先验证当前登录的用户
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		// 获取当前用户名
		String userName=(String)token.getPrincipal();
		Connection con=null;
		try{
			con=dbUtil.getCon();
			User user=userDao.getByUserName(con, userName);
			if(user!=null){
				AuthenticationInfo authcInfo=new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),"xx");
				return authcInfo;
			}else{
				return null;
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 为当前登录的用户授予角色和权限
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName=(String)principals.getPrimaryPrincipal();
		SimpleAuthorizationInfo authorizationInfo=new SimpleAuthorizationInfo();
		Connection con=null;
		try{
			con=dbUtil.getCon();
			authorizationInfo.setRoles(userDao.getRoles(con,userName));
			authorizationInfo.setStringPermissions(userDao.getPermissions(con,userName));
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return authorizationInfo;
	}

}
