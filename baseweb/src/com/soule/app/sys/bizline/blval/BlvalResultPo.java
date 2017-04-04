package com.soule.app.sys.bizline.blval;

import java.io.Serializable;

/**
 * 参数传递判断业务线关系数据的类
 */
public class BlvalResultPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer bizMapCount;

    /**
     * @return 业务线关系数量
     */
    public Integer getBizMapCount() {
        return bizMapCount;
    }

    /**
     * @param bizMapCount 业务线关系数量
     */
    public void setBizMapCount(Integer bizMapCount) {
        this.bizMapCount = bizMapCount;
    }
}