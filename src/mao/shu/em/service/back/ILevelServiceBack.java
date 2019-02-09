package mao.shu.em.service.back;

import mao.shu.em.vo.Level;

public interface ILevelServiceBack {
    /**
     * 该方法根据一个等级id查询出一个完整的等级信息
     * @param lid 等级id
     * @return 一个完整的工资等级数据
     * @throws Exception
     */
    public Level get(Integer lid)throws Exception;
}
