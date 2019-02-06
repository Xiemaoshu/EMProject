package mao.shu.em.service.abs;

import mao.shu.em.dao.IActionDAO;
import mao.shu.em.dao.ILevelDAO;
import mao.shu.em.dao.impl.LevelDAOImpl;
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
        IActionDAO actionDAO = DAOFactory.getInstance(IActionDAO.class);
        return actionDAO.exists(mid,actionFlag);
    }
}
