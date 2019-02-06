package mao.shu.em.vo;

import java.io.Serializable;

public class Level implements Serializable {
    private Integer lid;
    private Double losal;
    private Double hisal;
    private String title;
    private String flag;

    public Integer getLid() {
        return lid;
    }

    public void setLid(Integer lid) {
        this.lid = lid;
    }

    public Double getLosal() {
        return losal;
    }

    public void setLosal(Double losal) {
        this.losal = losal;
    }

    public Double getHisal() {
        return hisal;
    }

    public void setHisal(Double hisal) {
        this.hisal = hisal;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
