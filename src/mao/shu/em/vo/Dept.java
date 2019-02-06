package mao.shu.em.vo;

import java.io.Serializable;

public class Dept implements Serializable {
    private Integer deptno;
    private String dname;
    private Integer maxnum;
    private Integer currnum;

    public Integer getDeptno() {
        return deptno;
    }

    public void setDeptno(Integer deptno) {
        this.deptno = deptno;
    }

    public String getDname() {
        return dname;
    }

    public void setDname(String dname) {
        this.dname = dname;
    }

    public Integer getMaxnum() {
        return maxnum;
    }

    public void setMaxnum(Integer maxnum) {
        this.maxnum = maxnum;
    }

    public Integer getCurrnum() {
        return currnum;
    }

    public void setCurrnum(Integer currnum) {
        this.currnum = currnum;
    }
}
