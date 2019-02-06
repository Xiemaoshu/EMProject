package mao.shu.util.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/CodeCheckServlet")
public class CodeCheckServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //得到用户表单提交的验证码
        String input_code = req.getParameter("code");
        //得到KaptchaServlet 生成的验证码
        String servlet_code = (String) req.getSession().getAttribute("rand");

        if(input_code != null) {
            resp.getWriter().print(input_code.equalsIgnoreCase(servlet_code));
        }else{
            resp.getWriter().print("false");
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       this.doPost(req,resp);
    }


}
