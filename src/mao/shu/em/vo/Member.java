package mao.shu.em.vo;

import java.io.Serializable;

public class Member implements Serializable {
    private String mid;
    private String password;
    private String name;
    private Integer sflag;

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSflag() {
        return sflag;
    }

    public void setSflag(Integer sflag) {
        this.sflag = sflag;
    }
}
