package mao.shu.em.service.abs;

import mao.shu.em.dao.IActionDAO;
import mao.shu.em.dao.ILevelDAO;
import mao.shu.em.dao.IMemberDAO;
import mao.shu.em.dao.impl.ActionDAOImpl;
import mao.shu.em.dao.impl.LevelDAOImpl;
import mao.shu.em.dao.impl.MemberDAOImpl;
import mao.shu.em.vo.Member;
import mao.shu.util.factory.DAOFactory;

public abstract class AbstractService {
    /**
     * 该方法进行对指定的用户判断是否具有指定的权限,使用IActionDAO.exist()方法完成
     * @param mid 用户的id
     * @param actionFlag 权限的flag字段
     * @return 如果具有返回true,否则返回false
     * @throws Exception
     */
    public boolean auth(String mid,String actionFlag)throws Exception{
        IActionDAO actionDAO = DAOFactory.getInstance(ActionDAOImpl.class);
        return actionDAO.exists(mid,actionFlag);
    }

    /**
     * 判断一个管理员是否为超级管理员,依靠Member中的sflag字段判断
     * <p>如果sflag=1表示为超级管理员,如果sflag=0表示为普通管理员</p>
     * @param mid 管理员的id
     * @return
     * @throws Exception
     */
    public boolean admin(String mid)throws Exception{
        IMemberDAO memberDAO = DAOFactory.getInstance(MemberDAOImpl.class);
        Member member = memberDAO.findById(mid);
        if(member.getSflag().equals(1)){
            return true;
        }else{
            return false;
        }
    }
}
