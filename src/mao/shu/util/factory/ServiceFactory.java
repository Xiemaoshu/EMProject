package mao.shu.util.factory;

import mao.shu.util.service.proxy.ServiceProxy;

public class ServiceFactory {
	@SuppressWarnings("unchecked")
	public static <T> T getInstance(Class<T> cls) {
		try {
			return (T) new ServiceProxy().bind(cls.newInstance());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null; 
	}
}
