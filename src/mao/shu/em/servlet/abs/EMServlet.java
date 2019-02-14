package mao.shu.em.servlet.abs;

import mao.shu.em.dao.IMemberDAO;
import mao.shu.em.dao.impl.MemberDAOImpl;
import mao.shu.em.vo.Member;
import mao.shu.util.factory.DAOFactory;
import mao.shu.util.servlet.DispatcherServlet;

import java.sql.SQLException;
import java.util.Set;

public abstract class EMServlet extends DispatcherServlet {
    public boolean auth(String actionFlag)throws Exception{
        Set<String> allActions = (Set<String>)super.getSession().getAttribute("allActions");
        return allActions.contains(actionFlag);
    }

    /**
     * 获取当前操作用户的 id
     * @return
     */
    public String getMid(){
        return (String) super.getSession().getAttribute("mid");
    }

    /**
     * 判断操作用户是否为超级用户
     * @return
     */
    public boolean admin(){
        String mid = this.getMid();
        IMemberDAO memberDAO = DAOFactory.getInstance(MemberDAOImpl.class);
        try {
            Member member = memberDAO.findById(mid);
                return member.getSflag().equals(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


}
