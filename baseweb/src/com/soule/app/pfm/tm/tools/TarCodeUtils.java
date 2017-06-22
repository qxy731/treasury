package com.soule.app.pfm.tm.tools;

import com.soule.base.service.ServiceException;
import com.soule.base.service.keygen.IKeyGenerator;

public class TarCodeUtils {
	private static final String START="T";
    private static final String fore="0000";
    private static final String three="000";
    private static final String tow="00";
    private static final String one="0";
	/**
	 * 
	 * @param ecm E/C-基础指标/衍生指标；
	 * @param slio F/S/P/A/O-国库资金流动指标/国库资金安全性指标/国库资金收益性指标/国库会计核算效率指标/其他
	 * @return
	 */
	private IKeyGenerator keyGenerator;
	private String tableName;
	public TarCodeUtils(IKeyGenerator keyGenerator,String tableName){
		this.keyGenerator=keyGenerator;
		this.tableName=tableName;
	}
	public String gerneratedKey(String ecm,String slio){
		StringBuilder sb=new StringBuilder(START);
		if(ecm!=null){
			sb.append("-").append(ecm);
		}
		if(slio!=null){
			sb.append("-").append(slio);
		}
		if(sb.charAt(sb.length()-1)=='-'){
		    sb.append(getSeq());
		}else{
		  sb.append("-"+getSeq());
		}
		return sb.toString();
	}
	
	private String getSeq(){
		try {
			long res=keyGenerator.getSeqence(tableName);
			if(res<10){
				return fore+res;
			}else if(res>=10&&res<100){
				return three+res;
			}else if(res>=100&&res<1000){
				return tow+res;
			}else if(res>=1000&&res<10000){
				return one+res;
			}else{
				return String.valueOf(res);
			}
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		return "";
	}
}