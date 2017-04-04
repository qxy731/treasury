package com.soule.app.pfm.tm;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.soule.app.pfm.tm.qtydef.QtyDefServiceImpl;
import com.soule.app.pfm.tm.tools.TarCodeUtils;
import com.soule.base.service.keygen.IKeyGenerator;
import com.soule.comm.file.IValueHandler;
import com.soule.comm.file.config.Field;
public class TarQtyGenkey implements IValueHandler {
	@Autowired
    private IKeyGenerator keyg;
   
    public String special(Map context, List<Field> list, String[] values, int i) {
        TarCodeUtils tarUtils=new TarCodeUtils(keyg,QtyDefServiceImpl.TABLE_NAME);
        String primaryKey=tarUtils.gerneratedKey("X", getEcm(values[8]),null,getPahfx(values[9]));
        return primaryKey;
    }
    private String getEcm(String value){
        if(BaseTar.TAR_TYPE_BASE.equals(value)){
            return "E";
        }else if(BaseTar.TAR_TYPE_HANDLE.equals(value)){
            return "M";
        }else if(BaseTar.TAR_TYPE_MIX.equals(value)){
            return "C";
        }return "";
    }
    private String getPahfx(String value){
        if(BaseTar.IND_TYPE_HOURS_MONEY.equals(value)){
            return QtyDefServiceImpl.IND_TYPE_HOURS_MONEY_P;
        }else if(BaseTar.IND_TYPE_DAY_MONEY.equals(value)){
            return QtyDefServiceImpl.IND_TYPE_DAY_MONEY_A;
        }else if(BaseTar.IND_TYPE_FUSHU.equals(value)){
            return QtyDefServiceImpl.IND_TYPE_FUSHU_H;
        }else if(BaseTar.IND_TYPE_GET_MONEY.equals(value)){
            return QtyDefServiceImpl.IND_TYPE_GET_MONEY_F;
        }else if(BaseTar.IND_TYPE_USE_TAR.equals(value)){
            return QtyDefServiceImpl.IND_TYPE_USE_TAR_X;
        }
        return "";
    }

}
