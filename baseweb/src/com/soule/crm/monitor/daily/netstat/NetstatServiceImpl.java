package com.soule.crm.monitor.daily.netstat;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hyperic.sigar.NetInterfaceConfig;
import org.hyperic.sigar.NetInterfaceStat;
import org.hyperic.sigar.Sigar;
import org.hyperic.sigar.SigarException;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;

/**
 * 网络状况I/O监控业务操作
 */
@Service
public class NetstatServiceImpl implements INetstatService {

    private static HashMap<String,Long> lastData = new HashMap<String,Long>();
    private final static Log logger = LogFactory.getLog(NetstatServiceImpl.class);

    /**
     * 网卡信息查询
     */
    public NetstatHwinfoOut hwinfo(NetstatHwinfoIn in) throws ServiceException {
        NetstatHwinfoOut out = new NetstatHwinfoOut();
        Sigar sigar = new Sigar();
        ArrayList<NetstatNetPo> output = new ArrayList<NetstatNetPo>();
        out.setNet(output);
        try {
            String[] netIfs = sigar.getNetInterfaceList();
            for (int x = 0; x < netIfs.length; x++) {
                NetstatNetPo nnp = new NetstatNetPo();
                fillInfo(nnp, sigar, netIfs[x]);
                output.add(nnp);
            }
            out.getResultHead().setTotal(netIfs.length);
        } catch (SigarException e) {
            logger.error("SERVICE", e);
        }
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }

    private void fillInfo(NetstatNetPo nnp, Sigar sigar, String name) {
        try {
            nnp.setHwId(name);
            NetInterfaceConfig config = sigar.getNetInterfaceConfig(name);
            nnp.setAddress(config.getAddress());
            nnp.setDesc(config.getDescription());
            nnp.setHwAddr(config.getHwaddr());
            nnp.setNetmask(config.getNetmask());
            NetInterfaceStat stat = sigar.getNetInterfaceStat(name);
            nnp.setRxBytes(stat.getRxBytes());
            nnp.setRxErrors(stat.getRxErrors());
            nnp.setTxBytes(stat.getTxBytes());
            nnp.setTxErrors(stat.getTxErrors());
            nnp.setSpeed(stat.getSpeed());
            nnp.setType(config.getType());
        } catch (Exception e) {
            logger.error("SERVICE", e);
        }
    }

    /**
     * 网卡实时信息查询
     */
    public NetstatFlowInfoOut flowInfo(NetstatFlowInfoIn in) throws ServiceException {
        NetstatFlowInfoOut out = new NetstatFlowInfoOut();
        Sigar sigar = new Sigar();
        try {
            NetstatStatPo nsp = new NetstatStatPo();
            fillInfo(nsp, sigar, in.getHwId());
            out.setStat(nsp);
        } catch (SigarException e) {
            logger.error("SERVICE", e);
        }
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }

    private void fillInfo(NetstatStatPo nsp, Sigar sigar, String name) throws SigarException {
        long endTime = System.currentTimeMillis();
        nsp.setCurrent(endTime);
        try {
            if (name == null) {
                return;
            }
            NetInterfaceStat stat = sigar.getNetInterfaceStat(name);
            Long lastTime = lastData.get("lastIime-" + name);
            Long rxBytesStart = lastData.get("rxBytesStart-" + name);
            Long txBytesStart = lastData.get("txBytesStart-" + name);
            if (lastTime == null) {
                lastTime = endTime;
            }
            if (rxBytesStart == null ) {
                rxBytesStart = stat.getRxBytes();
            }
            if (txBytesStart == null ) {
                txBytesStart = stat.getTxBytes();
            }
            long rxBytesEnd = stat.getRxBytes();
            long txBytesEnd = stat.getTxBytes();

            long rxbps = (rxBytesEnd - rxBytesStart) * 8 / (endTime - lastTime + 1) * 1000;
            long txbps = (txBytesEnd - txBytesStart) * 8 / (endTime - lastTime + 1) * 1000;
            nsp.setRxBps(rxbps);
            nsp.setTxBps(txbps);

            lastData.put("lastIime-" + name, endTime);
            lastData.put("rxBytesStart-" + name, rxBytesEnd);
            lastData.put("txBytesStart-" + name, txBytesEnd);
        } catch (Exception e) {
            logger.error("SERVICE", e);
        }
    }
}
