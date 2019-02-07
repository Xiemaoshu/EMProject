package mao.shu.em.servlet.abs;

import mao.shu.util.servlet.DispatcherServlet;

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


}
