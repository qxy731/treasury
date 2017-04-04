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
	 * @param qx Q/X -定量/定性；
	 * @param ecm E/C/M-基础指标/复合指标/手工指标；
	 * @param slio S/L/I/O-存款/贷款/中间业务/其他
	 * @param pahfx P/A/H/F/X-时点余额/日均余额/户数/发生额/效益指标
	 * @return
	 */
	private IKeyGenerator keyGenerator;
	private String tableName;
	public TarCodeUtils(IKeyGenerator keyGenerator,String tableName){
		this.keyGenerator=keyGenerator;
		this.tableName=tableName;
	}
	public String gerneratedKey(String qx,String ecm,String slio,String pahfx){
		StringBuilder sb=new StringBuilder(START);
		if(qx!=null){
			sb.append("-").append(qx);
		}
		if(ecm!=null){
			sb.append("-").append(ecm);
		}
		if(slio!=null){
			sb.append("-").append(slio);
		}
		if(pahfx!=null){
			sb.append("-").append(pahfx);
		}
		if(sb.charAt(sb.length()-1)=='-'){
		    sb.append(getSeq());
		}else{
		  sb.append("-"+getSeq());
		}
		return sb.toString();
	}
	public String gerneratedKey(String qx,String slio,String pahfx){
		return gerneratedKey(qx, null, slio, pahfx);
	}
	public String gerneratedKey(String qx,String pahfx){
		return gerneratedKey(qx,null,pahfx);
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