package com.soule.app.pfm.tm.report.bankfundsflow;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletInputStream;
import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.json.annotations.JSON;
import org.springframework.beans.factory.annotation.Autowired;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceException;
import com.soule.base.service.ServiceResult;

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
    private List<ReportTargetPo> betweenTreasuryFundsSourceList;//国库之间资金流动情况统计
    private List<TreasuryIncomePo> treasuryIncomList;//大连市国库收支统计

    private BankFundsFlowReportQueryIn queryIn;
    
    public void doInit() {
    }
    
    public String query() {
        try{
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
        	queryIn.setDataDate(dataDate.replace("-", ""));
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
    	Map retMap = new HashMap();
    	String dataDate = request.getParameter("dataDate");
    	String unitId = request.getParameter("unitId");
    	String unitName = request.getParameter("unitName");
    	
    	queryIn = new BankFundsFlowReportQueryIn();
    	queryIn.setDataDate(dataDate);
    	queryIn.setUnitId(unitId);
    	
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			retMap = bankFundsFlowReportService.export(queryIn);
			retMap.put("unitName", unitName);
			retMap.put("dataDate", dataDate);
			fileName = "与商业银行之间资金流动情况统计表-"+unitName+"-"+dataDate.replaceAll("-", "");
			BetweenBankFlowReport.Data2Excel(retMap, os);
	        //wb.write(os);
	        byte[] content = os.toByteArray();
	        InputStream is = new ByteArrayInputStream(content);
	        // 设置response参数，可以打开下载页面
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename="
	            + new String((fileName + ".xls").getBytes(), "utf-8"));
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
		}
    	
    }
    
    
    public void export4(){
    	//BankFundsFlowReportQueryOut result;
   // 	FileOutputStream fout =BankFundsFlowReportQueryOut.class.get 
    	//ServletOutputStream fout = new ServletOutputStream() ;
    	
        String fileName ="";
    	Map retMap = new HashMap();
    	String dataDate = request.getParameter("dataDate");
    	String unitId = request.getParameter("unitId");
    	queryIn = new BankFundsFlowReportQueryIn();
    	queryIn.setDataDate(dataDate);
    	queryIn.setUnitId(unitId);
    	
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			retMap = bankFundsFlowReportService.export4(queryIn);
			String unitName =(String) retMap.get("UNIT_NAME");
			fileName = "国库会计分析其他数据统计表"+unitId+"-"+dataDate.replaceAll("-", "");
			AccountingAnalysisOther.Data2Excel(retMap, os);
	        //wb.write(os);
	        byte[] content = os.toByteArray();
	        InputStream is = new ByteArrayInputStream(content);
	        // 设置response参数，可以打开下载页面
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename="
	            + new String((fileName + ".xls").getBytes(), "utf-8"));
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
		}
    	
    }
    
    
    public String query3() {
        try{
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
    	Map retMap = new HashMap();
    	String dataDate = request.getParameter("dataDate");
    	String unitId = request.getParameter("unitId");
    	String unitName = request.getParameter("unitName");
    	queryIn = new BankFundsFlowReportQueryIn();
    	queryIn.setDataDate(dataDate.replaceAll("-", ""));
    	queryIn.setUnitId(unitId);
    	
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			result = bankFundsFlowReportService.query2(queryIn);
			retMap.put("dataDate", dataDate);
			fileName = "大连市国库收支统计表-"+"-"+dataDate.replaceAll("-", "");
			Map map = new HashMap();
			map.put("treasuryIncomeList", result.getTreasuryIncomList());
			map.put("dataDate", dataDate.replaceAll("-", ""));
			TreasuryIncomeReport.Data2Excel(map, os);
	        //wb.write(os);
	        byte[] content = os.toByteArray();
	        InputStream is = new ByteArrayInputStream(content);
	        // 设置response参数，可以打开下载页面
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename="
	            + new String((fileName + ".xls").getBytes(), "utf-8"));
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
		}
    	
    }

    
    public void export3(){
    	BankFundsFlowReportQueryOut result;
   // 	FileOutputStream fout =BankFundsFlowReportQueryOut.class.get 
    	//ServletOutputStream fout = new ServletOutputStream() ;
    	
        String fileName ="";
    	Map retMap = new HashMap();
    	String dataDate = request.getParameter("dataDate");
    	String unitId = request.getParameter("unitId");
    	String unitName = request.getParameter("unitName");
    	queryIn = new BankFundsFlowReportQueryIn();
    	queryIn.setDataDate(dataDate);
    	queryIn.setUnitId(unitId);
    	
		try {
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			result = bankFundsFlowReportService.query3(queryIn);
			retMap.put("unitName", unitName);
			retMap.put("dataDate", dataDate);
			fileName = "与其他国库之间资金流动情况统计表-"+unitName+"-"+dataDate.replaceAll("-", "");
			Map map =ReportTargetPo2Map.po2Map(result.getBetweenTreasuryFundsSourceList());
			BetweenTreasuryFundsReport.Data2Excel(map, os);
	        //wb.write(os);
	        byte[] content = os.toByteArray();
	        InputStream is = new ByteArrayInputStream(content);
	        // 设置response参数，可以打开下载页面
	        response.reset();
	        response.setContentType("application/vnd.ms-excel;charset=utf-8");
	        response.setHeader("Content-Disposition", "attachment;filename="
	            + new String((fileName + ".xls").getBytes(), "utf-8"));
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