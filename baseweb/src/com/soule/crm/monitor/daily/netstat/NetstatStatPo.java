package com.soule.crm.monitor.daily.netstat;

import java.io.Serializable;

/**
 * 参数传递流量信息的类
 */
public class NetstatStatPo implements Serializable {

    private static final long serialVersionUID = 1L;


    private Long current;
    private Long rxBps;
    private Long txBps;

    /**
     * @return 状态时间
     */
    public Long getCurrent() {
        return current;
    }

    /**
     * @param current 状态时间
     */
    public void setCurrent(Long current) {
        this.current = current;
    }
    /**
     * @return 接受速率
     */
    public Long getRxBps() {
        return rxBps;
    }

    /**
     * @param rxBps 接受速率
     */
    public void setRxBps(Long rxBps) {
        this.rxBps = rxBps;
    }
    /**
     * @return 发送速率
     */
    public Long getTxBps() {
        return txBps;
    }

    /**
     * @param txBps 发送速率
     */
    public void setTxBps(Long txBps) {
        this.txBps = txBps;
    }
}