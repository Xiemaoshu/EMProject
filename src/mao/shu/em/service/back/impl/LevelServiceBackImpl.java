package mao.shu.em.service.back.impl;

import mao.shu.em.dao.ILevelDAO;
import mao.shu.em.dao.impl.LevelDAOImpl;
import mao.shu.em.service.abs.AbstractService;
import mao.shu.em.service.back.ILevelServiceBack;
import mao.shu.em.vo.Level;
import mao.shu.util.factory.DAOFactory;

public class LevelServiceBackImpl extends AbstractService implements ILevelServiceBack {
    @Override
    public Level get(Integer lid) throws Exception {
        ILevelDAO levelDAO = DAOFactory.getInstance(LevelDAOImpl.class);
        return levelDAO.findById(lid);
    }
}
