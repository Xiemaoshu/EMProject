package mao.shu.em.servlet.back;

import mao.shu.em.service.back.IMemberServiceBack;
import mao.shu.em.service.back.impl.MemberServiceBackImpl;
import mao.shu.em.servlet.abs.EMServlet;
import mao.shu.em.vo.Member;
import mao.shu.util.EncryptUtil;
import mao.shu.util.factory.ServiceFactory;

import javax.servlet.annotation.WebServlet;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@WebServlet("/pages/back/member/MemberServletBack/*")
public class MemberServletBack extends EMServlet {
    private Member member = new Member();
    public Member getMember(){
        return this.member;
    }

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

    /**
     * 进行管理员添加时的准备操作,取出所有的角色信息
     * <p>需要注意的是在管理员登录时,已经将登录的管理员具有的所有角色都保存到了 request的attribute属性返回之中</p>
     * <p>所以此处的所有的角色设置到request的属性范围之中时,名称为"addPre_allRoles"</p>
     * @return 返回要跳转的页面,有DispatcherServlet程序类完成页面跳转
     */
    public String addPre(){
        if(super.admin()){
            IMemberServiceBack memberServiceBack = ServiceFactory.getInstance(MemberServiceBackImpl.class);
            try {
                Map<String,Object> map = memberServiceBack.addPre(super.getMid());
                super.setRequestAttribute("addPre_allRoles",map.get("allRoles"));
                return "member.add.page";
            } catch (Exception e) {
                e.printStackTrace();
                return "error.page";
            }
        }else{
            super.setErrors("auth","auth.failure.msg");
            return "error.page";
        }
    }

    public void checkMid(){
        if(super.admin()) {
            //得到ajax异步请求的mid参数
            String addMid = super.request.getParameter("mid");
            IMemberServiceBack memberServiceBack = ServiceFactory.getInstance(MemberServiceBackImpl.class);
            try {
                Member member = memberServiceBack.checkMid(super.getMid(),addMid);
                super.printData(member==null);

            } catch (Exception e) {
                e.printStackTrace();
                super.printData("false");
            }
        }else{
            super.printData("false");
        }
    }

    public String add(){
        if(super.admin()){
            //取得页面提交的所有角色id
            String[] rids = super.request.getParameterValues("rid");
            Set<Integer> ridsSet = new HashSet<Integer>();
            try {
                for (int i = 0; i < rids.length; i++) {
                    ridsSet.add(Integer.parseInt(rids[i]));
                }
            }catch(Exception e){}
            //进行密码加密处理
            this.member.setPassword(EncryptUtil.getPassword(this.member.getPassword()));
            IMemberServiceBack memberServiceBack = ServiceFactory.getInstance(MemberServiceBackImpl.class);

            try {
                if(memberServiceBack.add(super.getMid(),this.member,ridsSet)){
                    super.setUrlAndMsg("member.add.servlet","vo.add.success.msg");
                }else{
                    super.setUrlAndMsg("member.add.servlet","vo.add.failure.msg");
                }
            } catch (Exception e) {
                super.setUrlAndMsg("member.add.servlet","vo.add.failure.msg");
                e.printStackTrace();
            }
        }else{
            super.setUrlAndMsg("error.page","auth.failure.msg");
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
        return "管理员";
    }
}
