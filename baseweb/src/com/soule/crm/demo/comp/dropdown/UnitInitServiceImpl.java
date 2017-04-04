package com.soule.crm.demo.comp.dropdown;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import net.sourceforge.pinyin4j.PinyinHelper;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.app.sys.unit.UnitPo;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
/**
 * 机构名称拼音初始化
 */
public class UnitInitServiceImpl implements IUnitInitService {

    private final static Log logger = LogFactory.getLog(IUnitInitService.class);
    private String SYS_ENUM_LOAD = "init.queryAllUnits";
    @Autowired
    private IDefaultService service;
    private List<UnitPo> units = null;
    
    /**
     * 把所有机构名称拼音导入到内存中
     */
    public void load() throws ServiceException, DbAccessException {
        long start = System.currentTimeMillis();
        units = service.getIbatisMediator().find(SYS_ENUM_LOAD, new HashMap());
        if (units != null) {
            for (UnitPo po : units) {
                String py = convertPy(po.getUnitName());
                po.setExt5(py);
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("使用时间" + (end-start));
        logger.info("机构名称拼音装载完成(" +units.size()+")");
    }

    private String convertPy(String unitName) {
        if (unitName == null) {
            return "";
        }
        char[] chs = unitName.toCharArray();
        StringBuilder sb = new StringBuilder();
        HashSet hs=new HashSet();
        for (int i = 0; i < chs.length ;i++) {
            String[] pys = PinyinHelper.toHanyuPinyinStringArray(chs[i]);
            if (pys!=null){
                hs.clear();
                for (int k = 0 ; k < pys.length ; k++) {
                    if (!hs.contains(pys[k].charAt(0))) {
                        sb.append(pys[k].charAt(0));
                        hs.add(pys[k].charAt(0));
                    }
                }
            }
            else {
                sb.append(chs[i]);
            }
            sb.append('|');
        }
        return sb.toString().toUpperCase();
    }

    public List<UnitPo> getAllUnits() {
        return units;
    }
}
