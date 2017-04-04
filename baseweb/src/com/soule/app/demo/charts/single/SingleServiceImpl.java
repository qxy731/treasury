package com.soule.app.demo.charts.single;

import java.text.DecimalFormat;
import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;
/**
 * 基本图形业务操作
 */
@Service
public class SingleServiceImpl implements ISingleService {

    private final static Log logger = LogFactory.getLog(SingleServiceImpl.class);
    // 数据库通用操作
    /**
     */
    public SingleDisplayOut display(SingleDisplayIn in)
        throws ServiceException {
        SingleDisplayOut out = new SingleDisplayOut();
        ArrayList<SingleDataPo> list = new ArrayList<SingleDataPo>();
        //模拟数据
        DecimalFormat df = new DecimalFormat("#0.00");
        for (int i = 0 ; i < 10; i++){
            SingleDataPo one = new SingleDataPo();
            double d = ( 0.2 + Math.random()) *10000;
            one.setAmt(Double.valueOf(df.format(d)));
            one.setCustNo(in.getCustNo());
            one.setMonth(i+1);
            list.add(one);
        }
        out.setData(list);
        out.getResultHead().setTotal(10);
        AppUtils.setResult(out, MsgConstants.I0000);
        logger.debug(out.getResultHead().getRetCode());
        return out;
    }

}
