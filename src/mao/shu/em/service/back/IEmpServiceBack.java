package mao.shu.em.service.back;

import mao.shu.em.vo.Emp;

import java.util.Map;

public interface IEmpServiceBack {
    /**
     * 进行雇员增加时的准备信息提取操作,将增加雇员所需要的信息以一个Map集合的形式返回.
     * @return 返回的信息中包含如下的内容 </br>
     * 1. key=allUnderDepts     value = 包含所有未满员的部门信息,使用IDeptDAP.findUnders()方法完成</br>
     * 2. key = allLevels()     value = 所有工资等级的数据,使用ILevelDAO.findAll()方法完成
     * @throws Exception
     */
    public Map<String,Object> addPre()throws Exception;

    /**
     * 进行添加一名雇员信息的操作,该操作会进行如下几步<br/>
     * 1. 对当前操作的用户进行判断,判断当前用户是否具备有添加雇员的权限<br/>
     * 2. 判断要添加的部门是否有空余位置<br/>
     * 3. 判断添加的工资是否在所处的等级范围中<br/>
     * 4. 对新雇员的信息进行保存<br/>
     * 5. 保存成功之后,对所在的部门的当前人数进行+1操作<br/>
     * 6. 要进行相关日志的保存操作
     * @param vo 要添加的雇员信息,以及包含添加该雇员的用户的mid
     * @return 成功返回true,失败返回false
     * @throws Exception
     */
    public boolean add(Emp vo)throws Exception;
}
