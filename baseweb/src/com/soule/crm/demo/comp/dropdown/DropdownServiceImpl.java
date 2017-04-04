package com.soule.crm.demo.comp.dropdown;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.app.sys.unit.UnitPo;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;
/**
 * 机构下拉框业务操作
 */
@Service
public class DropdownServiceImpl implements IDropdownService {

    private final static Log logger = LogFactory.getLog(DropdownServiceImpl.class);
    @Autowired
    private IUnitInitService service;
    private int listcount = 20;
    
    public DropdownListOut list(DropdownListIn in)
        throws ServiceException {
        DropdownListOut out = new DropdownListOut();
        ArrayList<DropdownDataPo> output = new ArrayList<DropdownDataPo>();

        List<UnitPo> units = service.getAllUnits();
        String py = in.getSimple();
        int c = 0;
        if (py != null && py.length() >= 2) {
            if (containZh(py)) { //以中文模糊查询
                for (UnitPo unit:units) {
                    if (c <listcount && unit.getUnitName() != null && unit.getUnitName().indexOf(py) >=0){
                        DropdownDataPo ddp = new DropdownDataPo();
                        ddp.setPinyin(unit.getExt5());
                        ddp.setUnitId(unit.getUnitId());
                        ddp.setUnitName(unit.getUnitName());
                        output.add(ddp);
                        c ++;
                    }
                }
            }
            else { //以拼音模糊查询
                for (UnitPo unit:units) {
                    if (c <listcount && filterByPy(py,unit)){
                        DropdownDataPo ddp = new DropdownDataPo();
                        ddp.setPinyin(unit.getExt5());
                        ddp.setUnitId(unit.getUnitId());
                        ddp.setUnitName(unit.getUnitName());
                        output.add(ddp);
                        c ++;
                    }
                }
            }
        }
        out.setData(output);
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }

    private boolean filterByPy(String py, UnitPo unit) {
        String unitPy = unit.getExt5();
        int idx = 0;
        boolean ok = false;
        py = py.toUpperCase();
        do {
            int i = unitPy.indexOf(py.charAt(0),idx);
            if (i < 0) {
                break;
            }
            idx = i+1;
            for (int k = 1 ; k < py.length() ; k++) {
                char ch = py.charAt(k);
                int nsidx = unitPy.indexOf('|',idx);
                int neidx = unitPy.indexOf('|',nsidx+1);
                if (nsidx < neidx && unitPy.substring(nsidx,neidx).indexOf(ch) >=0 ) {
                    ok = true;
                    idx = neidx;
                }
                else {
                    ok =  false;
                    break;
                }
            }
            if (ok) {
                break;
            }
        } while (true);
        return ok;
    }

    private boolean containZh(String py) {
        char[] chs = py.toCharArray();
        for (int i = 0 ; i < chs.length ; i++) {
            if ( chs[i] > 255) {
                return true;
            }
        }
        return false;
    }

    public int getListcount() {
        return listcount;
    }

    public void setListcount(int listcount) {
        this.listcount = listcount;
    }

    public static void main(String[] args) {
        DropdownServiceImpl sdi = new DropdownServiceImpl();
        UnitPo ff = new UnitPo();
        ff.setExt5("S|L|L|Z|XH|");
        boolean b  = sdi.filterByPy("lzx",ff);
        System.out.println(b);
    }
}
