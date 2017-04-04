package com.soule.app.sys.bizline.bltype;

import java.io.Serializable;

/**
 * 参数传递判断业务线类型数据的类
 */
public class BltypeResultPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private Integer bizValueCount;
    private Integer bizMapCount;

    /**
     * @return 业务线类型值数量
     */
    public Integer getBizValueCount() {
        return bizValueCount;
    }

    /**
     * @param bizValueCount 业务线类型值数量
     */
    public void setBizValueCount(Integer bizValueCount) {
        this.bizValueCount = bizValueCount;
    }
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