package mao.shu.util;

import java.util.ResourceBundle;

public class ResourceUtils {
	/**
	 * 可以根据指定的资源名称和指定的key取得对应的value内容 
	 * @param baseName
	 * @param key
	 * @return
	 */
	public static String get(String baseName, String key) {
		return ResourceBundle.getBundle(baseName).getString(key) ;

	}
}
