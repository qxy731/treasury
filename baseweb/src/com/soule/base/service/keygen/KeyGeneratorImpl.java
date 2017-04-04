package com.soule.base.service.keygen;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.app.table.SysKeygenPo;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.comm.ParamConstants;
import com.soule.comm.enu.YesNoFlag;
import com.soule.comm.id.UUIDGenerator;

@Service("keyGen")
public class KeyGeneratorImpl implements IKeyGenerator {

    private HashMap<String,MyKey> seqs = new HashMap<String,MyKey>();
    private String Ibatis_TableName = "SysKeygen";
    @Autowired
    private IDefaultService sDefault;

    public String getUUIDKey() {
        return UUIDGenerator.generate(this);
    }

    public String getUUIDKey(Object o) {
        return UUIDGenerator.generate(o);
    }

    public synchronized Long getSeqence(String seqType) throws ServiceException {
        if (seqType == null) {
            seqType = "_DEFAULT_";
        }
        MyKey seq = seqs.get(seqType);
        if (seq == null) {
            SysKeygenPo pk = new SysKeygenPo();
            pk.setTableName(seqType);
            pk = (SysKeygenPo) sDefault.getRecordByKey(Ibatis_TableName, pk);
            if (pk == null) {
                seq = create(seqType);
            }
            else {
                seq = new MyKey(0,1);
                refetch(seq,seqType);
            }
            seqs.put(seqType, seq);
        }

        if (seq.val + 1 <= seq.max) {
            seq.val ++;
        }
        else{
            refetch(seq,seqType);
            seq.val ++;
        }
        return seq.val;
    }

    private MyKey create(String seqType) throws ServiceException {
        SysKeygenPo pk = new SysKeygenPo();
        pk.setSeqLen(ParamConstants.SEQ_LEN);
        pk.setSeqVal(new Long(ParamConstants.SEQ_LEN));
        pk.setTableName(seqType);
        pk.setModifing(YesNoFlag.NO.getValue());
        Boolean sb = sDefault.saveRecord(Ibatis_TableName, pk);
        if (!sb.booleanValue()) {
            throw new ServiceException(MsgConstants.E0006,seqType);
        }
        return new MyKey(0,ParamConstants.SEQ_LEN);
    }

    private void refetch(MyKey seq, String seqType) throws ServiceException {
        SysKeygenPo pk = new SysKeygenPo();
        pk.setTableName(seqType);
        pk.setModifing(YesNoFlag.YES.getValue());
        for (int i = 0; i < 20; i++) {
            Boolean ret = sDefault.updateRecord(Ibatis_TableName, pk);
            if (ret != null && ret.booleanValue()) {
                SysKeygenPo po = (SysKeygenPo) sDefault.getRecordByKey(Ibatis_TableName, pk);// 重新获取
                po.setSeqVal(po.getSeqVal() + po.getSeqLen());
                po.setModifing(YesNoFlag.NO.getValue());
                Boolean u = sDefault.updateRecord(Ibatis_TableName, po);
                if (!u.booleanValue()) {
                    throw new ServiceException(MsgConstants.E0006, seqType);
                }
                seq.max = po.getSeqVal();
                seq.val = po.getSeqVal() - po.getSeqLen();
                return;
            }
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
            }
        }
        throw new ServiceException(MsgConstants.E0006, seqType);
    }

    class MyKey {
        public MyKey(long i,long m) {
            val = i;
            max = m;
        }
        public long val;
        public long max;
    }
}
