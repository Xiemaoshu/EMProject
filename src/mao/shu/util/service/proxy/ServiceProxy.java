package mao.shu.util.service.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import mao.shu.util.dbc.DatabaseConnection;

public class ServiceProxy implements InvocationHandler {
	private Object target; // 真实对象
	/**
	 * 返回接口的代理对象
	 * @param target 要被代理的业务层对象
	 * @return
	 */
	public Object bind(Object target) {
		this.target = target;
		return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), this);
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object retObj = null ;
		try {
			if (method.getName().startsWith("add") || 
					method.getName().startsWith("edit") || 
					method.getName().startsWith("remove")) {	// 需要进行事务的控制处理
				DatabaseConnection.get().setAutoCommit(false);
				try {
					retObj = method.invoke(this.target, args) ;
					DatabaseConnection.get().commit();
				} catch (Exception e) {
					DatabaseConnection.get().rollback();
					throw e ;
				}
			} else {
				retObj = method.invoke(this.target, args) ;
			}
		} catch (Exception e) {
			throw e ;
		} finally {
			DatabaseConnection.close(); // 不管如何一定要进行数据库的关闭
		} 
		return retObj ;
	}
}
