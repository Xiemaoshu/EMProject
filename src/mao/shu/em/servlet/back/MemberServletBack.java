package mao.shu.em.servlet.back;

import mao.shu.em.service.back.IMemberServiceBack;
import mao.shu.em.service.back.impl.MemberServiceBackImpl;
import mao.shu.em.servlet.abs.EMServlet;
import mao.shu.util.factory.ServiceFactory;

import javax.servlet.annotation.WebServlet;
import java.util.Map;

@WebServlet("/pages/back/member/MemberServletBack/*")
public class MemberServletBack extends EMServlet {

    public String list(){
        try {
            if(super.auth("member:list")){
                IMemberServiceBack memberServiceBack = ServiceFactory.getInstance(MemberServiceBackImpl.class);
                Map<String,Object> map = memberServiceBack.list(super.getMid());
                super.setRequestAttribute("allMembers",map.get("allMembers"));
            }else{
               super.setErrors("auth","auth.failure.msg");
               return "error.page";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "error.page";

        }
        return "member.list.page";
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
