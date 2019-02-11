package mao.shu.em.service.back;

import mao.shu.em.vo.Elog;
import mao.shu.em.vo.Emp;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IEmpServiceBack {
    /**
     * 进行雇员增加时的准备信息提取操作,将增加雇员所需要的信息以一个Map集合的形式返回.
     * @return 返回的信息中包含如下的内容 </br>
     * 1. key=allUnderDepts     value = 包含所有未满员的部门信息,使用IDeptDAP.findUnders()方法完成</br>
     * 2. key = allLevels()     value = 所有工资等级的数据,使用ILevelDAO.findAll()方法完成
     * @throws Exception
     */
    public Map<String,Object> addPre(String mid)throws Exception;


    /**
     * 该方法用于做雇员编辑操作的准备工作
     * @param mid 操作雇员的mid
     * @param empno 修改雇员的雇员编号
     * @return 返回一个mao集合,该集合中有雇员编辑页面中所需要的数据,返回的结果会有以下几种数值
     * 1. key= allDepts value=所有的部门集合
     * 2. key= allLevels value=所有的等级集合
     * 3. key = emp     value=新修改的雇员信息
     * @throws Exception
     */
    public Map<String,Object> editPre(String mid,Integer empno)throws Exception;


    /**
     * 该方法进行雇员数据修改的操作,修改的同时需要添加一条日志信息
     * @param editEmp 修改的雇员信息
     * @param elog 日志信息
     * @return 修改成功返回true, 失败返回false
     * @throws Exception
     */
    public boolean edit(Emp editEmp,Elog elog)throws Exception;

    /**
     * 进行添加一名雇员信息的操作,该操作会进行如下几步<br/>
     * 1. 对当前操作的用户进行判断,判断当前用户是否具备有添加雇员的权限<br/>
     * 2. 判断要添加的部门是否有空余位置<br/>
     * 3. 判断添加的工资是否在所处的等级范围中<br/>
     * 4. 对新雇员的信息进行保存<br/>
     * 5. 保存成功之后,对所在的部门的当前人数进行+1操作<br/>
     * 6. 要进行相关日志的保存操作
     * @param vo 要添加的雇员信息,以及包含添加该雇员的用户的mid
     * @param elog 增加新雇员的同时增加一个新的雇员日志信息
     * @return 成功返回true,失败返回false
     * @throws Exception
     */
    public boolean add(Emp vo, Elog elog)throws Exception;

    /**
     * 该方法进行雇员emp表的分页操作,需要进行以下操作
     * 1. 进行权限验证,判断操作用户是否有 emp:list 权限
     * @param flag 用户在职情况,flag=1 表示列出所有在职的人员,flag=0 表示列出所有离职人员
     * @param currentPage 当前所在页
     * @param mid 当前操作用户id
     * @param lineSize 每页显示个数
     * @param column 模糊查询列
     * @param keyword 模糊查询关键字 如果没有column和keyword 表示默认查询所有数据
     * @return 返回以下的内容
     *  1. allEmps = 所有雇员集合
     *  2. allCount = 所有雇员人数
     * @throws Exception
     */
    public Map<String,Object> listByFlag(String mid,Integer flag,Integer currentPage,Integer lineSize,String column,String keyword)throws Exception;

    /**
     * 进行多个雇员的离职处理,雇员离职只是将雇员的flag标识符设置为0.
     * 每进行一次雇员离职,都需要保存一个日志信息,该日志会保存雇员离职的时间和操作员等等信息
     * 雇员离职成功之后,还需要将雇员所在的部门人数-1
     * @param mid
     * @param empnos
     * @return
     * @throws Exception
     */
    public boolean removeEmp(String mid, Set<Integer> empnos)throws Exception;

    /**
     * 进行一个雇员的详细信息的取出操作,
     * @param mid 操作员的mid,需要进行判断该操作员是否有对应"emp:list"权限
     * @param empno 查看的雇员编号
     * @return 返回的map集合中包含一下内容
     * 1. key=emp value=该雇员的基本信息
     * 2. key=dept value= 该雇员所在的部门的信息
     * 3. key=level value= 该雇员的工资等级的详细信息
     * 4. key=allRoles value= 该雇员的所有操作日志信息
     * @throws Exception
     */
    public Map<String,Object> getDetails(String mid,Integer empno)throws Exception;
}
