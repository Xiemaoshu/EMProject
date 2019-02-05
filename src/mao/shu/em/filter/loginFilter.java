package mao.shu.em.filter;
import mao.shu.util.ResourceUtils;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * @author Xiemaoshu
 * @date : 2019年2月5日 21:05:49
 * 该类主要用于对/pages/下的所有页面,做一个用户是否登录过滤,,如果用户未登录,则跳转到login.jsp页面中
 *
 */
@WebFilter("/pages/*")
public class loginFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        if(session.getAttribute("mid")!=null){
            filterChain.doFilter(servletRequest,servletResponse);
        }else{
            String msg = ResourceUtils.get("Messages","unlogin.msg");
            String loginPage = ResourceUtils.get("Pages","login.page");
            String forwardPage = ResourceUtils.get("Pages","forward.page");
            servletRequest.setAttribute("url",loginPage);
            servletRequest.setAttribute("msg",msg);
            servletRequest.getRequestDispatcher(forwardPage).forward(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
