package mao.shu.util.bean;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;

import mao.shu.util.StringUtils;

/**
 * 本类的功能是根据当前的servlet对象以及参数的内容实现属性的设置。
 * 前提：操作VO对象一定在Servlet类中实例化完成，并且提供有相应的getter方法。
 * @author mldn
 */
public class BeanValueUtil {
	private Object servletObject ;	// 传入的Servlet对象
	private String attributeName ;	// 属性的名字，带有“.”
	private String attributeValue ;	// 属性的内容，通过外部的request.getParameter()接收的
	/**
	 * 传入所需要的对象
	 * @param servletObject 触发此操作的Servlet对象
	 * @param attributeName 传入的参数名称
	 * @param attributeValue 传入的参数内容
	 */
	public BeanValueUtil(Object servletObject, String attributeName, String attributeValue) {
		this.servletObject = servletObject;
		this.attributeName = attributeName.trim();
		this.attributeValue = attributeValue.trim();
	}
	/**
	 * 实现属性内容的设置处理，将参数的内容设置给指定VO类的属性
	 */
	public void setObjectValue() {	// 实现内容的设置
		Object currentObject = null ;	// 描述的是当前的VO类对象
		if (this.attributeName.contains(".")) {	// 如果包含有：“.”则认为此时的内容可以处理
			String result [] = this.attributeName.split("\\.") ;	// 按照“.”拆分
			try {
				Method getMethod = this.servletObject.getClass().getMethod("get" + StringUtils.initcap(result[0])) ;
				currentObject = getMethod.invoke(this.servletObject) ;
				if (result.length == 2) {	// 单级控制
					Field field = currentObject.getClass().getDeclaredField(result[1]) ;	// 取得指定属性的对象
					Method setMethod = currentObject.getClass().getMethod("set" + StringUtils.initcap(result[1]), field.getType()) ;
					setMethod.invoke(currentObject, this.convertValue(field.getType().getSimpleName())) ;	// 实现了setter调用
				} else {	// 多级控制
					for (int x = 1 ; x < result.length - 1 ; x ++) {
						Field subField = currentObject.getClass().getDeclaredField(result[x]) ;
						Method getSubMethod = currentObject.getClass().getMethod("get" + StringUtils.initcap(result[x])) ; 
						if (getSubMethod.invoke(currentObject) == null) {	// 表示类中的对象还未被初始化
							currentObject = this.objectNewInstance(currentObject, result[x], subField) ;
						} else {
							currentObject = getSubMethod.invoke(currentObject) ;
						}
					}
					Field attField = currentObject.getClass().getDeclaredField(result[result.length - 1]) ;
					Method attMethod = currentObject.getClass().getMethod("set" + StringUtils.initcap(result[result.length - 1]), attField.getType()) ;
					attMethod.invoke(currentObject, this.convertValue(attField.getType().getSimpleName())) ;
				}
			} catch (Exception e) {
				// e.printStackTrace();
			}
		}
	}
	/**
	 * 反射调用setter方法实现对象的实例化控制
	 * @param currentObject 当前的对象
	 * @param attr 属性
	 * @param field 对象所属的类型，只有知道类型才可以反射实例化对象
	 * @return 一个实例化好的对象
	 */
	private Object objectNewInstance(Object currentObject, String attr, Field field) throws Exception {
		Object newObject = field.getType().newInstance(); // 反射实例化对象
		Method setMethod = currentObject.getClass().getMethod("set" + StringUtils.initcap(attr), field.getType());
		setMethod.invoke(currentObject, newObject);
		return newObject;
	}

	/** 
	 * 因为传入的数据都是String，所以需要将其变为指定的类型
	 * @return
	 */
	private Object convertValue(String type) {
		if ("int".equals(type) || "Integer".equals(type)) {
			return Integer.parseInt(this.attributeValue) ;	// 将内容变为整数
		} 
		if ("double".equalsIgnoreCase(type)) {
			return Double.parseDouble(this.attributeValue) ;
		}
		if ("date".equalsIgnoreCase(type)) {
			try {
				if (this.attributeValue.matches("\\d{4}-\\d{2}-\\d{2}")) {
					return new SimpleDateFormat("yyyy-MM-dd").parse(this.attributeValue);
				}
				if (this.attributeValue.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")) {
					return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this.attributeValue);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null ;
		}
		return this.attributeValue ;
	}
	
}
