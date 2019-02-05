package mao.shu.util;

public class StringUtils {
	/**
	 * 实现字符串的首字母大写处理
	 * @param str
	 * @return
	 */
	public static String initcap(String str) {
		if (str == null || "".equals(str)) {
			return str ;
		}
		if (str.length() == 1) {
			return str.toUpperCase() ; 
		}
		return str.substring(0,1).toUpperCase() + str.substring(1) ;
	}
} 
