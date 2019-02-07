package mao.shu.util.vaildator;

import java.util.HashMap;
import java.util.Map;

import mao.shu.util.ResourceUtils;
import mao.shu.util.servlet.DispatcherServlet;

public class Validation {
	private Validation() {}
	/**
	 * 进行数据的验证操作处理，如果要是有错误出现（即，不满足验证规则，那么会将错误信息保存在Map集合里面）<br>
	 * Map集合会保存两个内容：<br>
	 * <li>key = 参数的名称；</li>
	 * <li>value = 错误的完整信息；</li>
	 * @param servlet 因为整个的处理之中需要进行Pages、Messages、getXxx()的参数操作
	 * @return
	 */
	public static Map<String,String> validate(DispatcherServlet servlet) {
		Map<String,String> errors = new HashMap<String,String>() ;
		String ruleKey = servlet.getClass().getSimpleName() + "." + servlet.getStatus() + ".rules" ;
		try {
			String rule = ResourceUtils.get("Validations", ruleKey) ;
			if (rule != null) {	// 可以应用规则
				String result [] = rule.split("\\|") ;
				for (int x = 0 ; x < result.length ; x ++) {	// 开始取出每一个规则进行检测
					String temp [] = result[x].split(":") ;
					String val = servlet.getStringParameter(temp[0]) ;
					switch(temp[1]) {
						case "string" : {
							if (!Validation.validateEmpty(val)) {	// 验证失败
								errors.put(temp[0], servlet.getMessageValue("validation.string.msg")) ;
							}
							break ;
						}
						case "int" : {
							if (!Validation.validateInt(val)) {	// 验证失败
								errors.put(temp[0], servlet.getMessageValue("validation.int.msg")) ;
							}
							break ;
						}
						case "double" : {
							if (!Validation.validateDouble(val)) {	// 验证失败
								errors.put(temp[0], servlet.getMessageValue("validation.double.msg")) ;
							}
							break ;
						}
						case "date" : {
							if (!Validation.validateDate(val)) {	// 验证失败
								errors.put(temp[0], servlet.getMessageValue("validation.date.msg")) ;
							}
							break ;
						}
						case "datetime" : {
							if (!Validation.validateDatetime(val)) {	// 验证失败
								errors.put(temp[0], servlet.getMessageValue("validation.datetime.msg")) ;
							}
							break ;
						}
						case "temp" :{
							break;
						}
						default : {//其它类型,表示验证的是验证码.
							if(!Validation.validateEmpty(val)){
								errors.put(temp[0],servlet.getMessageValue("validation.string.msg"));
							}else{//如果此时有验证码
								//得到Servlet生成的验证码
								String rand = (String) servlet.getSession().getAttribute("rand");
								//和用户提交的验证码内容进行判断
								if(rand==null){
									errors.put(temp[0],servlet.getMessageValue("validation.rand.msg"));
								}else if(!rand.equalsIgnoreCase(val)){
									errors.put(temp[0],servlet.getMessageValue("validation.rand.msg"));
								}
							}
						}
					}
				}
			}
		} catch (Exception e) {	// 出现了错误，就表示没有规则
			e.printStackTrace();
		}
		return errors ;
	}
	/**
	 * 验证数据是否是日期
	 * @param val
	 * @return
	 */
	public static boolean validateDatetime(String val) {
		if (validateEmpty(val)) {
			return val.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}") ;
		}
		return false ;
	}
	/**
	 * 验证数据是否是日期
	 * @param val
	 * @return
	 */
	public static boolean validateDate(String val) {
		if (validateEmpty(val)) {
			return val.matches("\\d{4}-\\d{2}-\\d{2}") ;
		}
		return false ;
	}
	/**
	 * 验证数据是否是小数
	 * @param val
	 * @return
	 */
	public static boolean validateDouble(String val) {
		if (validateEmpty(val)) {
			return val.matches("\\d+(\\.\\d+)?") ;
		}
		return false ;
	}
	/**
	 * 验证数据是否是整数
	 * @param val
	 * @return
	 */
	public static boolean validateInt(String val) {
		if (validateEmpty(val)) {
			return val.matches("\\d+") ;
		}
		return false ;
	}
	/**
	 * 验证指定的数据是否为null或者是“""”
	 * @return 如果是空字符串则返回false，否则返回true
	 */
	public static boolean validateEmpty(String val) {
		if (val == null || "".equals(val)) {
			return false ;
		}
		return true ;
	}
}
