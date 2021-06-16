package com.chiyu.domin;

import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@XmlRootElement(name="DorLog")
public class DorLogVO implements Serializable {
    private String gate_no;
    private String gate_name;
    private String in;
    private String out;
    private String datetime;

    public DorLogVO() {
    }

    public DorLogVO(String gate_no, String gate_name, String in, String out, String datetime) {
        this.gate_no = gate_no;
        this.gate_name = gate_name;
        this.in = in;
        this.out = out;
        this.datetime = datetime;
    }

    /**
     * 获取
     * @return gate_no
     */
    public String getGate_no() {
        return gate_no;
    }

    /**
     * 设置
     * @param gate_no
     */
    public void setGate_no(String gate_no) {
        this.gate_no = gate_no;
    }

    /**
     * 获取
     * @return gate_name
     */
    public String getGate_name() {
        return gate_name;
    }

    /**
     * 设置
     * @param gate_name
     */
    public void setGate_name(String gate_name) {
        this.gate_name = gate_name;
    }

    /**
     * 获取
     * @return in
     */
    public String getIn() {
        return in;
    }

    /**
     * 设置
     * @param in
     */
    public void setIn(String in) {
        this.in = in;
    }

    /**
     * 获取
     * @return out
     */
    public String getOut() {
        return out;
    }

    /**
     * 设置
     * @param out
     */
    public void setOut(String out) {
        this.out = out;
    }

    /**
     * 获取
     * @return datetime
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * 设置
     * @param datetime
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String toString() {
        return "DorLogVO{gate_no = " + gate_no + ", gate_name = " + gate_name + ", in = " + in + ", out = " + out + ", datetime = " + datetime + "}";
    }
}
