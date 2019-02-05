package mao.shu.util.servlet;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.SmartUpload;

import com.sun.deploy.net.HttpRequest;
import mao.shu.util.bean.BeanValueUtil;
import mao.shu.util.split.SplitPageUtils;
import mao.shu.util.vaildator.Validation;

@SuppressWarnings("serial")
public abstract class DispatcherServlet extends HttpServlet {
	protected HttpServletRequest request; // 定义request对象
	protected HttpServletResponse response; // 定义reponse对象
	private ResourceBundle pageResource; // 读取Pages.properties对象
	private ResourceBundle messageResource; // 读取Messages.properties是对象
	private SmartUpload smart;

	@Override
	public void init() throws ServletException {
		this.pageResource = ResourceBundle.getBundle("Pages", Locale.getDefault());
		this.messageResource = ResourceBundle.getBundle("Messages", Locale.getDefault());
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.request = request;
		this.response = response;
		String urlPage = "error.page";
		String status = this.getStatus();
		try {
			if (status != null) { // 此时有一个状态码，好区分不同的用户请求
				String contentType = request.getContentType();
				if (contentType != null && contentType.contains("multipart/form-data")) {
					try {
						this.smart = new SmartUpload();
						this.smart.initialize(super.getServletConfig(), this.request, this.response);
						this.smart.upload();
					} catch (Exception e) {
					}
				}
				Map<String, String> errors = Validation.validate(this);
				if (errors.size() == 0) { // 没有错误
					this.parameterHandle(); // 处理所有的提交参数
					try {
						Method statusMethod = this.getClass().getMethod(status);
						urlPage = statusMethod.invoke(this).toString();
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else { // 回到指定的错误页上
					request.setAttribute("errors", errors);
					urlPage = this.getClass().getSimpleName() + "." + status + ".error.page";
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher(this.getPageValue(urlPage)).forward(request, response);
	}

	/**
	 * 取得当前的业务调用的参数
	 * 
	 * @return
	 */
	public String getStatus() {
		String uri = request.getRequestURI(); // 取得URI，内容：/DispatcherProject/EmpServlet/edit
		String status = uri.substring(uri.lastIndexOf("/") + 1);
		return status;
	}


	public HttpSession getSession(){
		return this.request.getSession();
	}
	public void setSessionAttribute(String name,Object value){
		this.request.getSession().setAttribute(name,value);
	}
	public HttpServletRequest getRequest(){
		return this.request;
	}
	public void setRequestAttribute(String name,Object value){
		this.request.setAttribute(name,value);
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doGet(request, response);
	}

	/**
	 * 取得指定参数对应的内容，不关心表单是否被封装
	 * 
	 * @param paramName
	 * @return
	 */
	public String getStringParameter(String paramName) {
		String contentType = request.getContentType();
		if (contentType != null && contentType.contains("multipart/form-data")) {
			return this.smart.getRequest().getParameter(paramName);
		} else {
			return this.request.getParameter(paramName);
		}
	}

	/**
	 * 取得指定参数并且将其变为int型数据返回
	 * 
	 * @param paramName
	 * @return
	 */
	public int getIntParameter(String paramName) {
		return Integer.parseInt(this.getStringParameter(paramName));
	}

	/**
	 * 取得指定参数并且将其变为int型数据返回
	 * 
	 * @param paramName
	 * @return
	 */
	public double getDoubleParameter(String paramName) {
		return Double.parseDouble(this.getStringParameter(paramName));
	}

	/**
	 * 取得指定参数并且将其变为Date型数据返回
	 * 
	 * @param paramName
	 * @return
	 */
	public Date getDateParameter(String paramName) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse(this.getStringParameter(paramName));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 取得指定参数并且将其变为Date类型
	 * 
	 * @param paramName
	 * @return
	 */
	public Date getDatetimeParameter(String paramName) {
		try {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(this.getStringParameter(paramName));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 实现所有请求参数与VO类转换的处理操作
	 */
	private void parameterHandle() {
		String contentType = request.getContentType();
		if (contentType != null && contentType.contains("multipart/form-data")) { // 表单封装
			// 1、处理SmartUpload对象实例化问题
			try {
				Enumeration<String> enu = this.smart.getRequest().getParameterNames();
				while (enu.hasMoreElements()) { // 取得全部的参数名称
					String paramName = enu.nextElement();
					String paramValue = this.smart.getRequest().getParameter(paramName);
					new BeanValueUtil(this, paramName, paramValue).setObjectValue();
				}
			} catch (Exception e) {
			}
		} else {
			// 1、需要取得全部的请求参数名称
			Enumeration<String> enu = this.request.getParameterNames();
			while (enu.hasMoreElements()) { // 取得全部的参数名称
				String paramName = enu.nextElement();
				String paramValue = this.request.getParameter(paramName);
				new BeanValueUtil(this, paramName, paramValue).setObjectValue();
			}
		}
	}

	/**
	 * 设置业务操作完成之后的跳转路径与提示信息的Key
	 * 
	 * @param pageKey
	 *            对应的Pages.properties文件中指定的key信息
	 * @param messageKey
	 *            对应的Messages.properties文件中指定的key信息
	 */
	public void setUrlAndMsg(String pageKey, String messageKey) {
		this.request.setAttribute("url", this.getPageValue(pageKey));
		if (this.getType() == null || "".equals(this.getType())) {
			this.request.setAttribute("msg", this.getMessageValue(messageKey));
		} else { // 将之前设置的每一个Servlet中的占位符进行了替换
			this.request.setAttribute("msg", MessageFormat.format(this.getMessageValue(messageKey), this.getType()));
		}
	}

	/**
	 * 取得Pages.properties文件中指定的key对应的value内容
	 * 
	 * @param pageKey
	 *            要读取的资源文件的key信息
	 * @return
	 */
	public String getPageValue(String pageKey) {
		try {
			return this.pageResource.getString(pageKey);
		}catch(Exception e){}
		return this.pageResource.getString("error.page");
	}
	/**
	 * 创建新的上传文件名称
	 * @return
	 */
	public String createSingleFileName() {
		return UUID.randomUUID() + "." + this.smart.getFiles().getFile(0).getFileExt() ;
	}
	/**
	 * 进行文件的保存处理
	 */
	public boolean saveUploadFile(String fileName) {
		try {
			if (this.getUploadDir() == null || "".equals(this.getUploadDir())) {
				this.smart.getFiles().getFile(0).saveAs(super.getServletContext().getRealPath("/") + fileName);
			} else {
				String filePath = super.getServletContext().getRealPath("/") + this.getUploadDir() + fileName ;
				File file  = new File(filePath) ;
				if (!file.getParentFile().exists()) {	// 保存目录不存在
					file.getParentFile().mkdirs() ;
				}
				this.smart.getFiles().getFile(0).saveAs(filePath); 
			}
			return true ;
		} catch (Exception e) {
			return false ;
		} 
	}
	/**
	 * 判断是否有上传文件
	 * @return
	 */
	public boolean isUploadFile() {	// 是否存在有上传文件
		if (this.smart == null) {
			return false ;
		}
		try {
			if (this.smart.getFiles().getSize() > 0) {
				return true ;
			}
		} catch (IOException e) {
			return false ; 
		}
		return false ; 
	}

	/**
	 * 取得Messages.properties文件中指定的key对应的value内容
	 * 
	 * @param messageKey
	 *            要读取的资源文件的key信息
	 * @return
	 */
	public String getMessageValue(String messageKey) {
		return this.messageResource.getString(messageKey);
	}
	/**
	 * 将分页所需要的参数都使用request属性传递
	 * @param urlKey 分页执行要使用到url
	 * @param allRecorders 总的记录数
	 * @param spu 分页的相关参数
	 */
	public void setSplitPage(String urlKey,int allRecorders,SplitPageUtils spu) {
		this.request.setAttribute("url", this.getPageValue(urlKey));
		this.request.setAttribute("allRecorders", allRecorders);
		this.request.setAttribute("currentPage", spu.getCurrentPage()); 
		this.request.setAttribute("lineSize", spu.getLineSize()); 
		this.request.setAttribute("column", spu.getColumn()); 
		this.request.setAttribute("keyWord", spu.getKeyWord());  
		this.request.setAttribute("columnData", this.getDefaultColumn());  
	}
	/**
	 * 设置所有分页的候选列，格式安按照“标签:列名称|标签:列名称|”
	 * @return
	 */
	public abstract String getDefaultColumn() ;
	/**
	 * 取得上传文件保存目录
	 * @return
	 */
	public abstract String getUploadDir() ;

	/**
	 * 取得每一个子类的具体的操作类型，以作为消息的填充使用
	 * 
	 * @return 返回每一个模块的名称
	 */
	public abstract String getType();
}
