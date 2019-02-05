package mao.shu.util.split;

import javax.servlet.http.HttpServletRequest;

public class SplitPageUtils {
	private HttpServletRequest request ;
	public SplitPageUtils(HttpServletRequest request) {
		this.request = request ;
	}
	public Integer getCurrentPage() {
		int currentPage = 1 ;
		try {
			currentPage = Integer.parseInt(this.request.getParameter("cp")) ;
		} catch (Exception e) {}
		return currentPage ;
	}
	public Integer getLineSize() {
		int lineSize = 10 ; 
		try {
			lineSize = Integer.parseInt(this.request.getParameter("ls")) ;
		} catch (Exception e) {}
		return lineSize ;
	}
	public String getColumn() {
		String column = this.request.getParameter("col") ;
		return column ;
	}
	public String getKeyWord() {
		String keyWord = this.request.getParameter("kw") ;
		return keyWord ; 
	}
}
