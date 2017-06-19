package com.soule.crm.comm;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.app.sys.userinfo.UserInfoPositionPo;
import com.soule.base.po.EnumItem;
import com.soule.base.po.EnumType;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ILogonUserInfo;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.EnumTypeUtil;
import com.soule.crm.comm.*;
@Service
public class UserManager implements IUserManager {

	/*@Override
	public String getStaffId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUnitId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getDataScope() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStaffName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUnitName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getCurrRole() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getCurrDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getExportCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getCustNameByCustNo(String custNo, String custType) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getRoleStr() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isCustMgr() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCustMgrAdmin() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isFunction(String roleId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getParamValue(String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getParamValueByEnumKey(String key, String code) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<EnumItem> getParamListByEnumKey(String key) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getStaffBranchOrg(String unitId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getStaffByCust(String custNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getRemindStaff(String unitId, String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> getRemindStaff(String roleId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isTotalBank(String roleId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Boolean isHaveCustChange(String custNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Boolean hasManagerInfo(String custNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HashMap<String, String> getCurrValue() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getOrglst(String unitlst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getFirstOrgInfo(String unitlist) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFirstOrg(String unitlst) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getPaDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBelongUnitId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBelongUnitName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAuditId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object isManager() {
		// TODO Auto-generated method stub
		return null;
	}*/

	 private ILogonUserInfo logonVO;

	    @Autowired
	    private IDefaultService sDefault;

	    public UserManager() {
	    }

	    @Override
	    public String getStaffId() {
	        initLogonVo();
	        return logonVO.getUsername();
	    }

	    private void initLogonVo() {
	        //if (logonVO == null) 
	        {
	            try {
	                logonVO = AppUtils.getLogonUserInfo();
	            } catch (Exception e) {
	            }
	        }
	    }

	    
	    @Override
	    public String getUnitId() {
	        initLogonVo();
	        return logonVO.getOperUnitId();
	    }
	    
	  /*  @Override
	    public String getBelongUnitId() {
	        initLogonVo();
	        return logonVO.getBelongUnitId();
	    }
	    
	    @Override
	    public String getBelongUnitName() {
	        initLogonVo();
	        return logonVO.getBelongUnitName();
	    }

	    @Override
	    public String getAuditId() {
	        initLogonVo();
	        return logonVO.getAuditId();
	    }
	    
	    @Override
	    public String getDataScope() {
	    	initLogonVo();
	        return logonVO.getDataScope();
	    }*/

	     
	    @Override
	    public String getStaffName() {
	        initLogonVo();
	        return logonVO.getUser().getUserName();
	    }

	    @Override
	    public String getUnitName() {
	        initLogonVo();
	        return logonVO.getOperUnitName();
	    }

	    
	  /*  @Override
	    public ILogonUserInfo getLogonUserInfo() {
	        initLogonVo();
	        return logonVO;
	    }*/

	    
	     
	    @Override
	    public String getCurrRole() {
	        initLogonVo();
	        return logonVO.getRoleId();
	    }

	    @Override
	    public Date getCurrDate() {
	        try {
	            Date date;
	            date = (Date) sDefault.getIbatisMediator().findById("Common.getCurrDate", null);
	            return date;
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return null;
	        }
	    }

	    
	     
	    @Override
	    public int getExportCount() {
	        try {
	            int cnt = 0;
	            cnt = (Integer) sDefault.getIbatisMediator().findById("Common.getExportCount", null);
	            return cnt;
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return 0;
	        }
	    }

	    
	     
	    @Override
	    public String getCustNameByCustNo(String custNo, String custType) {
	        try {
	            Object value;
	            value = sDefault.getIbatisMediator().findById("Common.findCustNameByEntNo", custNo);
	            if (value != null) {
	                return value.toString();
	            } else {
	                return null;
	            }
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return null;
	        }
	    }

	    
	     
	    @Override
	    public String getRoleStr() {
	        String roleStr = "";
	        List<UserInfoPositionPo> positionList = logonVO.getPositionPo();
	        for (UserInfoPositionPo posiVO : positionList) {
	            if (this.getUnitId().equals(posiVO.getOperUnitid())) {
	                roleStr = roleStr + "'" + posiVO.getRoleId() + "',";
	            }
	        }
	        if (roleStr.length() > 0) {
	            roleStr = roleStr.substring(0, roleStr.length() - 1);
	        }
	        return roleStr;
	    }

	    @Override
	    public boolean isCustMgr() {
	        String role = null;// logonVO.getRoleIdString();
	        
	         if(null!=role&&role.contains("custmgr")&&!role.contains("custmgradmin" )){ return true; }
	         
	        if (null != role) {
	            String[] roleStr = role.split(",");
	            if (roleStr.length == 1 && roleStr[0].equals("custmgr")) {
	                return true;
	            }
	        }
	        return false;
	    }

	    
	    @Override
		public String getDataScope() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public HashMap<String, String> getCurrValue() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getOrglst(String unitlst) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String[] getFirstOrgInfo(String unitlist) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getBelongUnitId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getBelongUnitName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getAuditId() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
	    public boolean isCustMgrAdmin() {
	        String role = null;// logonVO.getRoleIdString();
	        if (null != role && role.contains("custmgr") && !role.contains("custmgradmin")) {
	            return false;
	        }
	        return true;
	    }

	    
	     
	    @Override
	    public boolean isFunction(String roleId) {
	        List<UserInfoPositionPo> positionList = logonVO.getPositionPo();
	        boolean flag = false;
	        for (UserInfoPositionPo posiVO : positionList) {
	            if (this.getUnitId().equals(posiVO.getOperUnitid())) {
	                if (posiVO.getRoleId().equals(roleId)) {
	                    flag = true;
	                }
	            }
	        }
	        return flag;
	    }

	     
	    @Override
	    public Object getParamValue(String code) {
	        try {
	            Object obj = sDefault.getIbatisMediator().findById("Common.querySysParamValue", code);
	            return obj;
	        } catch (Exception ex) {
	            ex.printStackTrace();
	        }
	        return null;
	    }

	     
	    @Override
	    public Object getParamValueByEnumKey(String key, String code) {
	        EnumType et = (EnumType) EnumTypeUtil.getEnumType(key);
	        List itemList = et.getItems();
	        List<EnumItem> list = (List<EnumItem>) itemList;
	        for (EnumItem item : list) {
	            if (item.getKey().equals(code)) {
	                return item.getValue();
	            }
	        }
	        return null;
	    }

	     
	    @Override
	    public List<EnumItem> getParamListByEnumKey(String key) {
	        EnumType et = (EnumType) EnumTypeUtil.getEnumType(key);
	        List itemList = et.getItems();
	        List<EnumItem> list = (List<EnumItem>) itemList;
	        return list;
	    }

	    
	     
	    @Override
	    public String getStaffBranchOrg(String unitId) {
	        try {
	            Map map = new HashMap();
	            map.put("unitId", unitId);
	            String unitlst = (String) sDefault.getIbatisMediator().find("Common.getStaffBranchOrg", map).get(0);
	            String[] unitarr = unitlst.split("\\|");
	            if (unitarr.length >= 2) {
	                return unitarr[1];
	            } else if (unitarr.length == 1) {
	                return unitarr[0];
	            }
	            return "";
	        } catch (Exception ex) {
	            return null;
	        }
	    }

	     
	    @Override
	    public List<String> getStaffByCust(String custNo) {
	        try {
	            return sDefault.getIbatisMediator().find("Common.getStaffByCust", custNo);
	        } catch (Exception ex) {
	            return null;
	        }
	    }

	    
	     
	    @Override
	    public List<String> getRemindStaff(String unitId, String roleId) {
	        try {
	            Map map = new HashMap();
	            map.put("unitId", unitId);
	            map.put("roleId", roleId);
	            return sDefault.getIbatisMediator().find("Common.getRemindStaff", map);
	        } catch (Exception ex) {
	            return null;
	        }
	    }

	     
	    @Override
	    public List<String> getRemindStaff(String roleId) {
	        try {
	            Map map = new HashMap();
	            map.put("roleId", roleId);
	            return sDefault.getIbatisMediator().find("Common.getRemindStaffTwo", map);
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return null;
	        }
	    }

	     
	    @Override
	    public boolean isTotalBank(String roleId) {
	        try {
	            String desc = sDefault.getIbatisMediator().find("Common.isTotalBank", roleId).get(0).toString();
	            char roleManager[] = desc.toCharArray();
	            if (roleManager[1] == '1') {
	                return true;
	            } else {
	                return false;
	            }
	        } catch (Exception ex) {
	            return false;
	        }
	    }

	     
	    @Override
	    public Boolean isHaveCustChange(String custNo) {
	        try {
	            List list = sDefault.getIbatisMediator().find("Common.queryCustChangeInfo", custNo);
	            if (list.size() > 0) {
	                return true;
	            } else {
	                return false;
	            }
	        } catch (Exception ex) {
	            return false;
	        }

	    }

	    
	     
	    @Override
	    public Boolean hasManagerInfo(String custNo) {
	        try {
	            Map map = new HashMap();
	            map.put("custNo", custNo);
	            map.put("staffId", this.getStaffId());
	            List list = sDefault.getIbatisMediator().find("Common.queryCustManagerInfo", map);
	            if (list.size() > 0) {
	                return true;
	            } else {
	                return false;
	            }
	        } catch (Exception ex) {
	            return false;
	        }
	    }

	     
	   /* @Override
	    public HashMap<String, String> getCurrValue() {
	        List<CurrencyPo> list = new ArrayList<CurrencyPo>();
	        HashMap<String, String> currMap = new HashMap<String, String>();
	        try {
	            list = sDefault.getIbatisMediator().find("pub.currency.getCurrencyInfo", null);
	            for (CurrencyPo po : list) {
	                currMap.put(po.getCurrCode(), po.getCurrName());
	            }
	        } catch (DbAccessException e) {
	            e.printStackTrace();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return currMap;
	    }*/
	     
	   /* @Override
	    public String getOrglst(String unitlst) {
	        try {
	            String sqls = "";
	            unitlst = (null == unitlst || "".equals(unitlst)) ? logonVO.getDataScope() : unitlst;
	            String[] unitarr = unitlst.split(",");
	            if (unitarr.length >= 2) {
	                for (String str : unitarr) {
	                    sqls += ",'" + str + "'";
	                }
	                sqls = " IN(" + sqls.substring(1) + ") ";
	            } else {
	                sqls = " ='" + unitarr[0] + "'";
	            }
	            return sqls;
	        } catch (Exception ex) {
	            return null;
	        }
	    }*/
	     
	    @Override
	    public Date getPaDate() {
	        try {
	            Date date;
	            date = (Date) sDefault.getIbatisMediator().findById(
	                    "Common.getPaDate", null);
	            return date;
	        } catch (Exception ex) {
	            ex.printStackTrace();
	            return null;
	        }
	    }

	     
	/*    @Override
	    public String[] getFirstOrgInfo(String unitlist) {
	         try {
	        	 String orglist = "";
	        	 String sqls[] = new String[2];
	        	 unitlist = (null == unitlist || "".equals(unitlist)) ? logonVO.getDataScope() : unitlist;
	        	 //String[] unitarr = unitlist.split(",");
	        	 orglist = unitlist.replace(",", "','");
	        	 List<OrgListPo> ret = sDefault.getIbatisMediator().find("Common.getOrgList", orglist);
	        	 sqls[0] = ret.get(0).getUnitId();
	        	 sqls[1] = ret.get(0).getUnitName();
	        	 return sqls;
	         } catch (Exception ex) {
	        	 return null;
	         }
	    }*/

	    
	     
	    @Override
	    public String getFirstOrg(String unitlst) {
	        // try {
	        // String sqls = "";
	        // unitlst = (null == unitlst || "".equals(unitlst)) ? logonVO
	        // .getDataScope() : unitlst;
	        // String[] unitarr = unitlst.split(",");
	        // sqls = unitarr[0];
	        // return sqls;
	        // } catch (Exception ex) {
	        return null;
	        // }
	    }
	    
	    public boolean isCustManager() {
	    	String roleId = this.getCurrRole();
	    	if (roleId.indexOf("CUST_MGR") > 0) {
	    		return true;
	    	}
	    	return false;
	    }
	    
	    public boolean isCustManagerMajor() {
	    	String roleId = this.getCurrRole();
	    	if (roleId.indexOf("CUST_MMGR") >= 0) {
	    		return true;
	    	}
	    	return false;
	    }
	    
	    public boolean isBranchMgr() {
	    	String roleId = this.getCurrRole();
	    	if (roleId.indexOf("BRANCH_MGR") >= 0) {
	    		return true;
	    	}
	    	return false;
	    }
	    
	    public boolean isNodeMgr() {
	    	String roleId = this.getCurrRole();
	    	if (roleId.indexOf("NODE_MGR") >= 0) {
	    		return true;
	    	}
	    	return false;
	    }
	    
	    public String isManager() {
	    	if (isCustManager()) {
	    		return "1";
	    	} else if (isCustManagerMajor()) {
	    		return "2";
	    	} else if (isBranchMgr()) {
	    		return "3";
	    	} else if (isNodeMgr()) {
	    		return "4";
	    	}
	    	return "0";
	    }
}
