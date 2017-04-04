package com.soule.app.sys.bizline.blass;

import java.io.Serializable;
import java.util.List;

import com.soule.base.service.ServiceInput;

import net.sf.json.JSONArray;

/**
 * 输出参数业务线人员分配:新增业务线中人员
 */
public class BlassInsertIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private List<BlassBizassPo> bizass;

    public List<BlassBizassPo> getBizass() {
        return bizass;
    }

    public void setBizass(List<BlassBizassPo> input) {
        this.bizass = input;
    }
    public String getBizassStr() {
        return "";
    }

    public void setBizassStr(String input) {
        JSONArray jsonArray = JSONArray.fromObject(input);
        this.bizass = (List<BlassBizassPo>) JSONArray.toList(jsonArray, BlassBizassPo.class); 
    }


    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}