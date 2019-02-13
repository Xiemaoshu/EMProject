package mao.shu.em.service.back;

import mao.shu.em.vo.Member;

import java.util.Map;

public interface IMemberServiceBack {
    /**
     * 实现用户登录的方法,该方法主要完成一下功能<br>
     *     1. 实现登录检测,调用 IMemberDAP.findById()方法<br>
     *     2. 获取用户的所有角色信息<br>
     *     3. 获取所有的用户权限信息<br>
     * @param vo 包含一个试图登录的用户
     * @return 该方法将所有的信息以Map集合形式返回,map集合中会拥有以下内容
     *     1. key = flag value= true | false; 标志着用户登录成功或失败的标记<br>
     *     2. key = sflag value = 0 | 1 判断当前用户是否为超级管理员
     *     3. key = name  value = 用户的真是姓名
     *     4. key = allRoles value = set集合 保存用户所有的角色数据
     *     5. key = allActions value =set集合 保存用户所有的权限信息
     * @throws Exception
     */
    public Map<String,Object> login(Member vo)throws Exception;

    /**
     * 该方法进行所有管理员的列出操作
     * @param mid 登录用户的id,用于判断权限"member:list"
     * @return
     * @throws Exception
     */
    public Map<String,Object> list(String mid)throws Exception;
}
