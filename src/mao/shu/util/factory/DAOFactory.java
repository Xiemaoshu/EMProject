package mao.shu.util.factory;

public class DAOFactory {
	public static <T> T getInstance(Class<T> cls) {
		try {
			return cls.newInstance() ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ; 
	} 
}
