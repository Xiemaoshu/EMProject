package mao.shu.em.servlet;

import mao.shu.em.service.back.IMemberServiceBack;
import mao.shu.em.service.back.impl.MemberServiceBackImpl;
import mao.shu.em.vo.Member;
import mao.shu.util.EncryptUtil;
import mao.shu.util.MD5Code;
import mao.shu.util.factory.ServiceFactory;
import mao.shu.util.servlet.DispatcherServlet;

import javax.servlet.annotation.WebServlet;
import java.util.Map;

@WebServlet("/MemberLoginServlet/*")
public class MemberLoginServlet extends DispatcherServlet {
    private Member member = new Member();
    public Member getMember(){
        return this.member;
    }
    public String login(){
        //进行加密处理
        this.member.setPassword(EncryptUtil.getPassword(this.member.getPassword()));
        IMemberServiceBack memberServiceBack = ServiceFactory.getInstance(MemberServiceBackImpl.class);
        try {
            Map<String, Object> loginResult = memberServiceBack.login(this.member);
            boolean loginFlag = (boolean) loginResult.get("flag");
            if(loginFlag){
                super.setUrlAndMsg("index.page","login.success.msg");
                super.setSessionAttribute("name",loginResult.get("name"));
                super.setSessionAttribute("sflag",loginResult.get("sflag"));
            }else{
                super.setUrlAndMsg("login.page","login.failure.msg");

            }
        }catch (Exception e){
            e.printStackTrace();
        }
            return "forward.page";
    }
    @Override
    public String getDefaultColumn() {
        return null;
    }

    @Override
    public String getUploadDir() {
        return null;
    }

    @Override
    public String getType() {
        return null;
    }
}
