package com.soule.app.pfm.tm.report.bankfundsflow;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceException;
import com.soule.base.service.ServiceResult;
import com.soule.comm.tools.DateFormatCalendar;

/**
 * 人员维护模块表现层处理类
 */
@Namespace("/report")
public class BankFundsFlowReportAction extends BaseAction {
    private static final long serialVersionUID = 1295775504760653068L;
    
    @Autowired
    private IBankFundsFlowReportService bankFundsFlowReportService;
    
    private List<BankFundsFlowPo> bankFundsFlowList;//资金流向分类
    private List<ReportTargetPo> treasuryFundsNatureList;//资金性质分类
    private List<ReportTargetPo> treasuryFundsSourceList;//资金来源分类
    
    private List<ReportTargetPo> accountingAnalysisOtherList;//国库会计分析其他数据统计表
    private List<TreasuryIncomePo> treasuryIncomList;//大连市国库收支统计

    private BankFundsFlowReportQueryIn queryIn;
    
    public void doInit() {
    }
    
    public String query() {
        try{
        	String dataDate = queryIn.getDataDate();
        	DateFormatCalendar.getInstance(dataDate.replace("-", "")+"01");
        	dataDate = DateFormatCalendar.getMonthEndDate();
        	
        	queryIn.setDataDate(dataDate);
        	
            BankFundsFlowReportQueryOut result = bankFundsFlowReportService.query(queryIn);
            ServiceResult head = result.getResultHead();
            bankFundsFlowList = result.getBankFundsFlowList();
            if(bankFundsFlowList == null)bankFundsFlowList = new ArrayList<BankFundsFlowPo>();
            treasuryFundsNatureList = result.getTreasuryFundsNatureList();
            if(treasuryFundsNatureList == null)treasuryFundsNatureList = new ArrayList<ReportTargetPo>();
            treasuryFundsSourceList = result.getTreasuryFundsSourceList();
            if(treasuryFundsSourceList == null)treasuryFundsSourceList = new ArrayList<ReportTargetPo>();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    
    public String query2() {
        try{
        	String dataDate = queryIn.getDataDate();
        	DateFormatCalendar.getInstance(dataDate.replace("-", "")+"01");
        	dataDate = DateFormatCalendar.getMonthEndDate();
        	
        	queryIn.setDataDate(dataDate);
            BankFundsFlowReportQueryOut result = bankFundsFlowReportService.query2(queryIn);
            ServiceResult head = result.getResultHead();
            treasuryIncomList = result.getTreasuryIncomList();
            if(treasuryIncomList == null)treasuryIncomList = new ArrayList<TreasuryIncomePo>();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    
    public String query4() {
        try{
        	
        	String dataDate = queryIn.getDataDate();
        	DateFormatCalendar.getInstance(dataDate.replace("-", "")+"01");
        	dataDate = DateFormatCalendar.getMonthEndDate();
        	
        	queryIn.setDataDate(dataDate);
            BankFundsFlowReportQueryOut result = bankFundsFlowReportService.query4(queryIn);
            ServiceResult head = result.getResultHead();
            accountingAnalysisOtherList = result.getAccountingAnalysisOtherList();
            if(accountingAnalysisOtherList == null)accountingAnalysisOtherList = new ArrayList<ReportTargetPo>();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    
    public void export(){
    	
        String fileName ="";
    	Map<String,Object> retMap = new HashMap<String,Object>();
    	String dataDate = request.getParameter("dataDate");
    	String unitId = request.getParameter("unitId");
    	String unitName = request.getParameter("unitName");
    	Map<String,Object> map = new HashMap<String,Object>();
		try {
			
			retMap.put("dataDate", dataDate);
			fileName = "与商业银行之间资金流动情况统计表-"+unitName+"-"+dataDate;
	    	DateFormatCalendar.getInstance(dataDate.replace("-", "")+"01");
	    	dataDate = DateFormatCalendar.getMonthEndDate();
	    	queryIn = new BankFundsFlowReportQueryIn();
	    	queryIn.setDataDate(dataDate.replaceAll("-", ""));
	    	queryIn.setUnitId(unitId);
	    	
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			map = bankFundsFlowReportService.export(queryIn);
			map.put("unitName", unitName);
			map.put("dataDate", retMap.get("dataDate"));
			BetweenBankFlowReport.Data2Excel(map, os);
	        //wb.write(os);
	        byte[] content = os.toByteArray();
	        InputStream is = new ByteArrayInputStream(content);
	        // 设置response参数，可以打开下载页面
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename="
	            + new String( fileName.getBytes( "gb2312" ), "ISO8859-1" )+ ".xls" + "\"");
	        ServletOutputStream out = response.getOutputStream();
	        BufferedInputStream bis = null;
	        BufferedOutputStream bos = null;
		   
	        try {
	          bis = new BufferedInputStream(is);
	          bos = new BufferedOutputStream(out);
	          byte[] buff = new byte[1024];
	          int bytesRead;
	          // Simple read/write loop.
	          while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	            bos.write(buff, 0, bytesRead);
	          }
	        } catch (Exception e) {
	          // TODO: handle exception
	          e.printStackTrace();
	        } finally {
	          if (bis != null)
	            bis.close();
	          if (bos != null)
	            bos.close();
	        }
		        
		} catch (ServiceException e) {
			handleError(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    }
    
    
    public void export4(){
    	//BankFundsFlowReportQueryOut result;
   // 	FileOutputStream fout =BankFundsFlowReportQueryOut.class.get 
    	//ServletOutputStream fout = new ServletOutputStream() ;
    	
        String fileName ="";
    	Map<String,Object> retMap = new HashMap<String,Object>();
    	String dataDate = request.getParameter("dataDate");
    	String unitId = request.getParameter("unitId");
    	String unitName = request.getParameter("unitName");
    	Map<String,Object> map = new HashMap<String,Object>();
		try {
			
			map.put("dataDate", dataDate);
			
			fileName = "国库会计分析其他数据统计表-"+unitName+"-"+dataDate;
	    	DateFormatCalendar.getInstance(dataDate.replace("-", "")+"01");
	    	dataDate = DateFormatCalendar.getMonthEndDate();
	    	queryIn = new BankFundsFlowReportQueryIn();
	    	queryIn.setDataDate(dataDate.replaceAll("-", ""));
	    	queryIn.setUnitId(unitId);
	    	
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			retMap = bankFundsFlowReportService.export4(queryIn);
			retMap.put("unitName", unitName);
			retMap.put("dataDate",map.get("dataDate"));
			
			AccountingAnalysisOther.Data2Excel(retMap, os);
	        //wb.write(os);
	        byte[] content = os.toByteArray();
	        InputStream is = new ByteArrayInputStream(content);
	        // 设置response参数，可以打开下载页面
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename="
	            + new String( fileName.getBytes( "gb2312" ), "ISO8859-1" )+ ".xls" + "\"");
	        ServletOutputStream out = response.getOutputStream();
	        BufferedInputStream bis = null;
	        BufferedOutputStream bos = null;
		   
	        try {
	          bis = new BufferedInputStream(is);
	          bos = new BufferedOutputStream(out);
	          byte[] buff = new byte[1024];
	          int bytesRead;
	          // Simple read/write loop.
	          while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	            bos.write(buff, 0, bytesRead);
	          }
	        } catch (Exception e) {
	          // TODO: handle exception
	          e.printStackTrace();
	        } finally {
	          if (bis != null)
	            bis.close();
	          if (bos != null)
	            bos.close();
	        }
		        
		} catch (ServiceException e) {
			handleError(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    }
    
    
    public String query3() {
        try{
        	
        	String dataDate = queryIn.getDataDate();
        	DateFormatCalendar.getInstance(dataDate.replace("-", "")+"01");
        	dataDate = DateFormatCalendar.getMonthEndDate();
        	
        	queryIn.setDataDate(dataDate);
            BankFundsFlowReportQueryOut result = bankFundsFlowReportService.query3(queryIn);
            ServiceResult head = result.getResultHead();
            accountingAnalysisOtherList = result.getAccountingAnalysisOtherList();
            if(accountingAnalysisOtherList == null)accountingAnalysisOtherList = new ArrayList<ReportTargetPo>();
            this.setRetCode(head.getRetCode());
            this.setRetMsg(head.getRetMsg());
        }catch(Exception e) {
            handleError(e);
        }
        return JSON;
    }
    
    public void export2(){
    	BankFundsFlowReportQueryOut result;
   // 	FileOutputStream fout =BankFundsFlowReportQueryOut.class.get 
    	//ServletOutputStream fout = new ServletOutputStream() ;
    	
        String fileName ="";
    	String dataDate = request.getParameter("dataDate");
    	Map<String,Object> map = new HashMap<String,Object>();
		try {
			map.put("dataDate", dataDate);
			fileName = "大连市国库收支统计表-"+dataDate;
	    	DateFormatCalendar.getInstance(dataDate.replace("-", "")+"01");
	    	dataDate = DateFormatCalendar.getMonthEndDate();
	    	queryIn = new BankFundsFlowReportQueryIn();
	    	queryIn.setDataDate(dataDate.replaceAll("-", ""));
	    	
	    	
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			result = bankFundsFlowReportService.query2(queryIn);
			
			
			
			map.put("treasuryIncomeList", result.getTreasuryIncomList());
			
			TreasuryIncomeReport.Data2Excel(map, os);
	        //wb.write(os);
	        byte[] content = os.toByteArray();
	        InputStream is = new ByteArrayInputStream(content);
	        // 设置response参数，可以打开下载页面
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=UTF-8");
	        response.setHeader("Content-Disposition", "attachment;filename="
	            +new String( fileName.getBytes( "gb2312" ), "ISO8859-1" )+ ".xls" + "\"");
	        ServletOutputStream out = response.getOutputStream();
	        BufferedInputStream bis = null;
	        BufferedOutputStream bos = null;
		   
	        try {
	          bis = new BufferedInputStream(is);
	          bos = new BufferedOutputStream(out);
	          byte[] buff = new byte[1024];
	          int bytesRead;
	          // Simple read/write loop.
	          while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	            bos.write(buff, 0, bytesRead);
	          }
	        } catch (Exception e) {
	          // TODO: handle exception
	          e.printStackTrace();
	        } finally {
	          if (bis != null)
	            bis.close();
	          if (bos != null)
	            bos.close();
	        }
		        
		} catch (ServiceException e) {
			handleError(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    }

    
    public void export3(){
    	BankFundsFlowReportQueryOut result;
   // 	FileOutputStream fout =BankFundsFlowReportQueryOut.class.get 
    	//ServletOutputStream fout = new ServletOutputStream() ;
    	
        String fileName ="";
    	Map<String,Object> retMap = new HashMap<String,Object>();
    	String dataDate = request.getParameter("dataDate");
    	String unitId = request.getParameter("unitId");
    	String unitName = request.getParameter("unitName");
    	Map<String,Object> map = new HashMap<String,Object>();
    	
		try {
			
			retMap.put("dataDate", dataDate);
			fileName = "与其他国库之间资金流动情况统计表-"+unitName+"-"+dataDate;
	    	DateFormatCalendar.getInstance(dataDate.replace("-", "")+"01");
	    	dataDate = DateFormatCalendar.getMonthEndDate();
	    	queryIn = new BankFundsFlowReportQueryIn();
	    	queryIn.setDataDate(dataDate.replaceAll("-", ""));
	    	queryIn.setUnitId(unitId);
	    	
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			result = bankFundsFlowReportService.query3(queryIn);
			
			
			map = ReportTargetPo2Map.po2Map(result.getBetweenTreasuryFundsSourceList());
			map.put("unitName", unitName);
			map.put("dataDate", retMap.get("dataDate"));
			BetweenTreasuryFundsReport.Data2Excel(map, os);
	        //wb.write(os);
	        byte[] content = os.toByteArray();
	        InputStream is = new ByteArrayInputStream(content);
	        // 设置response参数，可以打开下载页面
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename="
	            + new String( fileName.getBytes( "gb2312" ), "ISO8859-1" )+ ".xls" + "\"");
	        ServletOutputStream out = response.getOutputStream();
	        BufferedInputStream bis = null;
	        BufferedOutputStream bos = null;
		   
	        try {
	          bis = new BufferedInputStream(is);
	          bos = new BufferedOutputStream(out);
	          byte[] buff = new byte[1024];
	          int bytesRead;
	          // Simple read/write loop.
	          while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
	            bos.write(buff, 0, bytesRead);
	          }
	        } catch (Exception e) {
	          // TODO: handle exception
	          e.printStackTrace();
	        } finally {
	          if (bis != null)
	            bis.close();
	          if (bos != null)
	            bos.close();
	        }
		        
		} catch (ServiceException e) {
			handleError(e);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	
    }
    

    /**
     * 查询报表
     */
    @JSON(serialize=false)
    public BankFundsFlowReportQueryIn getQueryIn() {
        return this.queryIn;
    }
    /**
     * 查询报表
     */
    public void setQueryIn(BankFundsFlowReportQueryIn in) {
        this.queryIn = in;
    }

	public List<BankFundsFlowPo> getBankFundsFlowList() {
		return bankFundsFlowList;
	}

	public void setBankFundsFlowList(List<BankFundsFlowPo> bankFundsFlowList) {
		this.bankFundsFlowList = bankFundsFlowList;
	}

	public List<ReportTargetPo> getTreasuryFundsNatureList() {
		return treasuryFundsNatureList;
	}

	public void setTreasuryFundsNatureList(List<ReportTargetPo> treasuryFundsNatureList) {
		this.treasuryFundsNatureList = treasuryFundsNatureList;
	}

	public List<ReportTargetPo> getTreasuryFundsSourceList() {
		return treasuryFundsSourceList;
	}

	public void setTreasuryFundsSourceList(List<ReportTargetPo> treasuryFundsSourceList) {
		this.treasuryFundsSourceList = treasuryFundsSourceList;
	}

	public List<ReportTargetPo> getAccountingAnalysisOtherList() {
		return accountingAnalysisOtherList;
	}

	public void setAccountingAnalysisOtherList(List<ReportTargetPo> accountingAnalysisOtherList) {
		this.accountingAnalysisOtherList = accountingAnalysisOtherList;
	}

	public List<TreasuryIncomePo> getTreasuryIncomList() {
		return treasuryIncomList;
	}

	public void setTreasuryIncomList(List<TreasuryIncomePo> treasuryIncomList) {
		this.treasuryIncomList = treasuryIncomList;
	}
	
}