package com.soule.app.sys.funcauth;

import java.io.Serializable;

import com.soule.base.service.ServiceInput;

/**
 * 输出参数功能权限资源维护:新增功能权限资源
 */
public class FuncAuthInsertIn implements Serializable {

    private static final long serialVersionUID = 1L;
    private ServiceInput inputHead = new ServiceInput();

    private FuncAuthRecordPo news;

    public FuncAuthRecordPo getNews()  {
        return news;
    }

    public void setNews(FuncAuthRecordPo input) {
        this.news = input;
    }

    public ServiceInput getInputHead() {
        return inputHead;
    }

    public void setInputHead(ServiceInput inputHead) {
        this.inputHead = inputHead;
    }

}