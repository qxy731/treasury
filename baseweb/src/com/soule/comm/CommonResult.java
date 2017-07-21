package com.soule.comm;

import java.io.Serializable;

import com.soule.comm.tools.AppUtils;

/**
 * 通用数据传输对象
 * @author peter
 *
 * @param <T>
 */
public class CommonResult<T> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	/** 异常编码  **/
	public static final int CODE_EXCEPTION = 0;

	/** 正常编码  **/
	public static final int CODE_NORMAL = 1;

    /**
     * 返回数据
     */
	private T data;

	private String retCode;
    /**
     * 返回消息
     */
	private String retMsg;
	
	public CommonResult(){
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public void setMessageByMsgKey(String key){		
		setRetCode(key);
		setRetMsg(AppUtils.getMessage(key));
	}
	
	public boolean isSuccess() {
        return (retCode != null && retCode.length() > 0 && retCode.charAt(0) == 'I');
    }
	
}

