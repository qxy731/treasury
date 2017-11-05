package com.soule.app.pfm.tm.report.bankfundsflow;

import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

public class BetweenBankFlowReport {
	
	public static void Data2Excel(Map<String,Object> map,ByteArrayOutputStream fout){
		
		 HSSFWorkbook workbook = new HSSFWorkbook();   
	        //创建sheet页  
	        HSSFSheet sheet = workbook.createSheet("与商业银行之间资金流动情况统计表");  
	        
	        String unitName =(String)map.get("unitName");
	        String dataDate =(String)map.get("dataDate");
	        
	        List<BankFundsFlowPo> bankFundsFlowList = (ArrayList<BankFundsFlowPo>)map.get("bankFundsFlowList");
	        List<ReportTargetPo> treasuryFundsNatureList = (ArrayList<ReportTargetPo>)map.get("treasuryFundsNatureList");
	        List<ReportTargetPo> treasuryFundsSourceList = (ArrayList<ReportTargetPo>)map.get("treasuryFundsSourceList");
	        
	        sheet.setColumnWidth(0, 45*256);
	        sheet.setColumnWidth(1, 35*256);
	        sheet.setColumnWidth(2, 35*256);
	        sheet.setColumnWidth(3, 35*256);
	        sheet.setColumnWidth(4, 35*256);
	        sheet.setColumnWidth(5, 35*256);
	        
	        CellStyle style = workbook.createCellStyle();  
	        CellStyle style1 = workbook.createCellStyle();//第一行表格样式
	        CellStyle style2 = workbook.createCellStyle();//第二行表格样式
	        CellStyle style3 = workbook.createCellStyle();//第三行表格样式
	        CellStyle style4 = workbook.createCellStyle();//合并单元格表格样式
	        CellStyle style5 = workbook.createCellStyle();//合并单元格表格样式
	        
	        
	        
    
	        //设置表头字体
	        
	        HSSFFont font = workbook.createFont();    
	        font.setFontName("黑体");    
	        font.setFontHeightInPoints((short)16);//设置字体大小    
	        style1.setFont(font);//选择需要用到的字体格式
	        
	        
	        
	        style.setBorderBottom(CellStyle.BORDER_MEDIUM); 
	        style.setBorderLeft(CellStyle.BORDER_MEDIUM);  
	        style.setBorderRight(CellStyle.BORDER_MEDIUM);  
	        style.setBorderTop(CellStyle.BORDER_MEDIUM);  
	        
	        
	        //第一行 表格样式
	        style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style1.setBorderBottom(CellStyle.BORDER_MEDIUM); 
	        style1.setBorderLeft(CellStyle.BORDER_MEDIUM);  
	        style1.setBorderRight(CellStyle.BORDER_MEDIUM);  
	        style1.setBorderTop(CellStyle.BORDER_MEDIUM); 
	        style1.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
	        style1.setFillPattern(CellStyle.SOLID_FOREGROUND);

	        //第二行 前两列表格样式
	        style2.setAlignment(HSSFCellStyle.ALIGN_LEFT);
	        style2.setBorderBottom(CellStyle.BORDER_MEDIUM); 
	        style2.setBorderLeft(CellStyle.BORDER_MEDIUM);  
	        style2.setBorderRight(CellStyle.BORDER_MEDIUM);  
	        style2.setBorderTop(CellStyle.BORDER_MEDIUM);  
	        style2.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
	        style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        
	        
	        
	        //第二行后一列 表格样式
	        style3.setAlignment(HSSFCellStyle.ALIGN_RIGHT);
	        style3.setBorderBottom(CellStyle.BORDER_MEDIUM);
	        style3.setBorderLeft(CellStyle.BORDER_MEDIUM);  
	        style3.setBorderRight(CellStyle.BORDER_MEDIUM);  
	        style3.setBorderTop(CellStyle.BORDER_MEDIUM);  
	        style3.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
	        style3.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        
	        style4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style4.setBorderBottom(CellStyle.BORDER_MEDIUM); 
	        style4.setBorderLeft(CellStyle.BORDER_MEDIUM);  
	        style4.setBorderRight(CellStyle.BORDER_MEDIUM);  
	        style4.setBorderTop(CellStyle.BORDER_MEDIUM); 
	        style4.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
	        style4.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        //合并单元格表格样式
	        style5.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style5.setBorderBottom(CellStyle.BORDER_MEDIUM); 
	        style5.setBorderLeft(CellStyle.BORDER_MEDIUM);  
	        style5.setBorderRight(CellStyle.BORDER_MEDIUM);  
	        style5.setBorderTop(CellStyle.BORDER_MEDIUM);  
	        style5.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直  
	        //创建单元格  
	        HSSFRow row1 = sheet.createRow(1);   
	        HSSFCell c10 = row1.createCell(0);   
	        c10.setCellValue(new HSSFRichTextString("与商业银行之间资金流动情况统计表"));
	        HSSFCell c11 = row1.createCell(1);   
	        c11.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c12 = row1.createCell(2);   
	        c12.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c13 = row1.createCell(3);   
	        c13.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c14 = row1.createCell(4);   
	        c14.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c15 = row1.createCell(5);   
	        c15.setCellValue(new HSSFRichTextString("")); 
	        
	        HSSFRow row2 = sheet.createRow(2);   
	        HSSFCell c20 = row2.createCell(0);   
	        c20.setCellValue(new HSSFRichTextString("填报单位："+unitName));
	        HSSFCell c21 = row2.createCell(1);   
	        c21.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c22 = row2.createCell(2);   
	        c22.setCellValue(new HSSFRichTextString("业务期间： "+dataDate)); 
	        HSSFCell c23 = row2.createCell(3);   
	        c23.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c24 = row2.createCell(4);   
	        c24.setCellValue(new HSSFRichTextString("单位：万元")); 
	        HSSFCell c25 = row2.createCell(5);   
	        c25.setCellValue(new HSSFRichTextString("")); 
	        
	        HSSFRow row3 = sheet.createRow(3);   
	        HSSFCell c30 = row3.createCell(0);   
	        c30.setCellValue(new HSSFRichTextString("一、按资金流向分类"));
	        HSSFCell c31 = row3.createCell(1);   
	        c31.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c32 = row3.createCell(2);   
	        c32.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c33 = row3.createCell(3);   
	        c33.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c34 = row3.createCell(4);   
	        c34.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c35 = row3.createCell(5);   
	        c35.setCellValue(new HSSFRichTextString("")); 
	        
	        
	        
	        HSSFRow row4 = sheet.createRow(4);   
	        HSSFCell c40 = row4.createCell(0);   
	        c40.setCellValue(new HSSFRichTextString("机构类别"));
	        HSSFCell c41 = row4.createCell(1);   
	        c41.setCellValue(new HSSFRichTextString("从商业银行流入国库")); 
	        HSSFCell c42 = row4.createCell(2);   
	        c42.setCellValue(new HSSFRichTextString("其中：来自专户")); 
	        HSSFCell c43 = row4.createCell(3);   
	        c43.setCellValue(new HSSFRichTextString("从国库流向商业银行")); 
	        HSSFCell c44 = row4.createCell(4);   
	        c44.setCellValue(new HSSFRichTextString("其中：流向专户")); 
	        HSSFCell c45 = row4.createCell(5);   
	        c45.setCellValue(new HSSFRichTextString("净流入（流出")); 
	       
	        
	        
	       
	        
	        //提取资金流向数据
	        //
	        BankFundsFlowPo gydxsyPo = new BankFundsFlowPo();//国有大型商业银行
	        BankFundsFlowPo gsPo = new BankFundsFlowPo();//工商银行
	        BankFundsFlowPo nyPo = new BankFundsFlowPo();//农业银行
	        BankFundsFlowPo zgPo = new BankFundsFlowPo();//中国银行
	        BankFundsFlowPo jsPo = new BankFundsFlowPo();//建设银行
	        BankFundsFlowPo jtPo = new BankFundsFlowPo();//交通银行
	        BankFundsFlowPo gfzsyPo = new BankFundsFlowPo();//股份制商业银行
	        BankFundsFlowPo cssyPo = new BankFundsFlowPo();//城市商业化银行
	        BankFundsFlowPo ncsyjgPo = new BankFundsFlowPo();//农村商业机构
	        BankFundsFlowPo yzcxPo = new BankFundsFlowPo();//邮政储蓄银行
	        BankFundsFlowPo zcxPo = new BankFundsFlowPo();//政策性银行
	        BankFundsFlowPo qtjgPo = new BankFundsFlowPo();//其他机构
	        BankFundsFlowPo hjPo = new BankFundsFlowPo();//合计
		     // 100005	邮政储蓄银行
		     // 100000	工商银行
		     // 100004	建设银行
		     // 100003	交通银行
		     // 100002	中国银行
		     // 100001	农业银行
		     // 100007	政策性银行
		     // 100018	股份制商业银行
		     // 100028	城市商业银行
		     // 100030	农村金融机构
		     // 100050	其他机构

	        
	        for(int i=0;i<bankFundsFlowList.size();i++){
	        	BankFundsFlowPo po = bankFundsFlowList.get(i);
	        	if("100000".equals(po.getCustOrgNo())){
	        		gsPo = po;
	        	}
	        	if("100004".equals(po.getCustOrgNo())){
	        		jsPo = po;
	        	}
	        	if("100003".equals(po.getCustOrgNo())){
	        		jtPo = po;
	        	}
	        	if("100005".equals(po.getCustOrgNo())){
	        		yzcxPo = po;
	        	}
	        	if("100002".equals(po.getCustOrgNo())){
	        		zgPo = po;
	        	}
	        	if("100001".equals(po.getCustOrgNo())){
	        		nyPo = po;
	        	}
	        	if("100007".equals(po.getCustOrgNo())){
	        		zcxPo = po;
	        	}
	        	if("100018".equals(po.getCustOrgNo())){
	        		gfzsyPo = po;
	        	}
	        	if("100028".equals(po.getCustOrgNo())){
	        		cssyPo = po;
	        	}
	        	if("100030".equals(po.getCustOrgNo())){
	        		ncsyjgPo = po;
	        	}
	        	if("100050".equals(po.getCustOrgNo())){
	        		qtjgPo = po;
	        	}
	        	//国有大型商业银行
	        	if("100055".equals(po.getCustOrgNo())){
	        		gydxsyPo = po;
	        	}
	        	
	        	if("CustOrgNoSum".equals(po.getCustOrgNo())){
	        		hjPo = po;
	        	}
	        	
	        	
	        }
	        
	        HSSFRow row5 = sheet.createRow(5);   
	        HSSFCell c50 = row5.createCell(0);   
	        c50.setCellValue(new HSSFRichTextString("国有大型商业银行"));
	        HSSFCell c51 = row5.createCell(1);   
	        c51.setCellValue(new HSSFRichTextString(null ==gydxsyPo.getBankAllInflow()?"0":gydxsyPo.getBankAllInflow().toString())); 
	        HSSFCell c52 = row5.createCell(2);   
	        c52.setCellValue(new HSSFRichTextString(null ==gydxsyPo.getBankSpecialInflow()?"0":gydxsyPo.getBankSpecialInflow().toString())); 
	        HSSFCell c53 = row5.createCell(3);   
	        c53.setCellValue(new HSSFRichTextString(null ==gydxsyPo.getBankAllOutflow()?"0":gydxsyPo.getBankAllOutflow().toString())); 
	        HSSFCell c54 = row5.createCell(4);   
	        c54.setCellValue(new HSSFRichTextString(null ==gydxsyPo.getBankSpecialOutflow()?"0":gydxsyPo.getBankSpecialOutflow().toString())); 
	        HSSFCell c55 = row5.createCell(5);   
	        c55.setCellValue(new HSSFRichTextString(null ==gydxsyPo.getBankAllNetFlow()?"0":gydxsyPo.getBankAllNetFlow().toString())); 
	        
	        HSSFRow row6 = sheet.createRow(6);   
	        HSSFCell c60 = row6.createCell(0);   
	        c60.setCellValue(new HSSFRichTextString("其中：工商银行"));
	        HSSFCell c61 = row6.createCell(1);   
	        c61.setCellValue(new HSSFRichTextString(null ==gsPo.getBankAllInflow()?"0":gsPo.getBankAllInflow().toString())); 
	        HSSFCell c62 = row6.createCell(2);   
	        c62.setCellValue(new HSSFRichTextString(null ==gsPo.getBankSpecialInflow()?"0":gsPo.getBankSpecialInflow().toString())); 
	        HSSFCell c63 = row6.createCell(3);   
	        c63.setCellValue(new HSSFRichTextString(null ==gsPo.getBankAllOutflow()?"0":gsPo.getBankAllOutflow().toString())); 
	        HSSFCell c64 = row6.createCell(4);   
	        c64.setCellValue(new HSSFRichTextString(null ==gsPo.getBankSpecialOutflow()?"0":gsPo.getBankSpecialOutflow().toString())); 
	        HSSFCell c65 = row6.createCell(5);   
	        c65.setCellValue(new HSSFRichTextString(null ==gsPo.getBankAllNetFlow()?"0":gsPo.getBankAllNetFlow().toString())); 
	        
	        
	        HSSFRow row7 = sheet.createRow(7);   
	        HSSFCell c70 = row7.createCell(0);   
	        c70.setCellValue(new HSSFRichTextString("农业银行"));
	        HSSFCell c71 = row7.createCell(1);   
	        c71.setCellValue(new HSSFRichTextString(null ==nyPo.getBankAllInflow()?"0":nyPo.getBankAllInflow().toString())); 
	        HSSFCell c72 = row7.createCell(2);   
	        c72.setCellValue(new HSSFRichTextString(null ==nyPo.getBankSpecialInflow()?"0":nyPo.getBankSpecialInflow().toString())); 
	        HSSFCell c73 = row7.createCell(3);   
	        c73.setCellValue(new HSSFRichTextString(null ==nyPo.getBankAllOutflow()?"0":nyPo.getBankAllOutflow().toString())); 
	        HSSFCell c74 = row7.createCell(4);   
	        c74.setCellValue(new HSSFRichTextString(null ==nyPo.getBankSpecialOutflow()?"0":nyPo.getBankSpecialOutflow().toString())); 
	        HSSFCell c75 = row7.createCell(5);   
	        c75.setCellValue(new HSSFRichTextString(null ==nyPo.getBankAllNetFlow()?"0":nyPo.getBankAllNetFlow().toString())); 
	        
	        
	        HSSFRow row8 = sheet.createRow(8);   
	        HSSFCell c80 = row8.createCell(0);   
	        c80.setCellValue(new HSSFRichTextString("中国银行"));
	        HSSFCell c81 = row8.createCell(1);   
	        c81.setCellValue(new HSSFRichTextString(null ==zgPo.getBankAllInflow()?"0":zgPo.getBankAllInflow().toString())); 
	        HSSFCell c82 = row8.createCell(2);   
	        c82.setCellValue(new HSSFRichTextString(null ==zgPo.getBankSpecialInflow()?"0":zgPo.getBankSpecialInflow().toString())); 
	        HSSFCell c83 = row8.createCell(3);   
	        c83.setCellValue(new HSSFRichTextString(null ==zgPo.getBankAllOutflow()?"0":zgPo.getBankAllOutflow().toString())); 
	        HSSFCell c84 = row8.createCell(4);   
	        c84.setCellValue(new HSSFRichTextString(null ==zgPo.getBankSpecialOutflow()?"0":zgPo.getBankSpecialOutflow().toString())); 
	        HSSFCell c85 = row8.createCell(5);   
	        c85.setCellValue(new HSSFRichTextString(null ==zgPo.getBankAllNetFlow()?"0":zgPo.getBankAllNetFlow().toString())); 
	        
	        HSSFRow row9 = sheet.createRow(9);   
	        HSSFCell c90 = row9.createCell(0);   
	        c90.setCellValue(new HSSFRichTextString("建设银行"));
	        HSSFCell c91 = row9.createCell(1);   
	        c91.setCellValue(new HSSFRichTextString(null ==jsPo.getBankAllInflow()?"0":jsPo.getBankAllInflow().toString())); 
	        HSSFCell c92 = row9.createCell(2);   
	        c92.setCellValue(new HSSFRichTextString(null ==jsPo.getBankSpecialInflow()?"0":jsPo.getBankSpecialInflow().toString())); 
	        HSSFCell c93 = row9.createCell(3);   
	        c93.setCellValue(new HSSFRichTextString(null ==jsPo.getBankAllOutflow()?"0":jsPo.getBankAllOutflow().toString())); 
	        HSSFCell c94 = row9.createCell(4);   
	        c94.setCellValue(new HSSFRichTextString(null ==jsPo.getBankSpecialOutflow()?"0":jsPo.getBankSpecialOutflow().toString())); 
	        HSSFCell c95 = row9.createCell(5);   
	        c95.setCellValue(new HSSFRichTextString(null ==jsPo.getBankAllNetFlow()?"0":jsPo.getBankAllNetFlow().toString())); 
	        
	        HSSFRow row10 = sheet.createRow(10);   
	        HSSFCell c100 = row10.createCell(0);   
	        c100.setCellValue(new HSSFRichTextString("交通银行"));
	        HSSFCell c101 = row10.createCell(1);   
	        c101.setCellValue(new HSSFRichTextString(null ==jtPo.getBankAllInflow()?"0":jtPo.getBankAllInflow().toString())); 
	        HSSFCell c102 = row10.createCell(2);   
	        c102.setCellValue(new HSSFRichTextString(null ==jtPo.getBankSpecialInflow()?"0":jtPo.getBankSpecialInflow().toString())); 
	        HSSFCell c103 = row10.createCell(3);   
	        c103.setCellValue(new HSSFRichTextString(null ==jtPo.getBankAllOutflow()?"0":jtPo.getBankAllOutflow().toString())); 
	        HSSFCell c104 = row10.createCell(4);   
	        c104.setCellValue(new HSSFRichTextString(null ==jtPo.getBankSpecialOutflow()?"0":jtPo.getBankSpecialOutflow().toString())); 
	        HSSFCell c105 = row10.createCell(5);   
	        c105.setCellValue(new HSSFRichTextString(null ==jtPo.getBankAllNetFlow()?"0":jtPo.getBankAllNetFlow().toString())); 
	        
	        HSSFRow row11 = sheet.createRow(11);   
	        HSSFCell c110 = row11.createCell(0);   
	        c110.setCellValue(new HSSFRichTextString("股份制商业银行"));
	        HSSFCell c111 = row11.createCell(1);   
	        c111.setCellValue(new HSSFRichTextString(null ==gfzsyPo.getBankAllInflow()?"0":gfzsyPo.getBankAllInflow().toString())); 
	        HSSFCell c112 = row11.createCell(2);   
	        c112.setCellValue(new HSSFRichTextString(null ==gfzsyPo.getBankSpecialInflow()?"0":gfzsyPo.getBankSpecialInflow().toString())); 
	        HSSFCell c113 = row11.createCell(3);   
	        c113.setCellValue(new HSSFRichTextString(null ==gfzsyPo.getBankAllOutflow()?"0":gfzsyPo.getBankAllOutflow().toString())); 
	        HSSFCell c114 = row11.createCell(4);   
	        c114.setCellValue(new HSSFRichTextString(null ==gfzsyPo.getBankSpecialOutflow()?"0":gfzsyPo.getBankSpecialOutflow().toString())); 
	        HSSFCell c115 = row11.createCell(5);   
	        c115.setCellValue(new HSSFRichTextString(null ==gfzsyPo.getBankAllNetFlow()?"0":gfzsyPo.getBankAllNetFlow().toString())); 
	        
	        HSSFRow row12 = sheet.createRow(12);   
	        HSSFCell c120 = row12.createCell(0);   
	        c120.setCellValue(new HSSFRichTextString("城市商业银行"));
	        HSSFCell c121 = row12.createCell(1);   
	        c121.setCellValue(new HSSFRichTextString(null ==cssyPo.getBankAllInflow()?"0":cssyPo.getBankAllInflow().toString())); 
	        HSSFCell c122 = row12.createCell(2);   
	        c122.setCellValue(new HSSFRichTextString(null ==cssyPo.getBankSpecialInflow()?"0":cssyPo.getBankSpecialInflow().toString())); 
	        HSSFCell c123 = row12.createCell(3);   
	        c123.setCellValue(new HSSFRichTextString(null ==cssyPo.getBankAllOutflow()?"0":cssyPo.getBankAllOutflow().toString())); 
	        HSSFCell c124 = row12.createCell(4);   
	        c124.setCellValue(new HSSFRichTextString(null ==cssyPo.getBankSpecialOutflow()?"0":cssyPo.getBankSpecialOutflow().toString())); 
	        HSSFCell c125 = row12.createCell(5);   
	        c125.setCellValue(new HSSFRichTextString(null ==cssyPo.getBankAllNetFlow()?"0":cssyPo.getBankAllNetFlow().toString())); 
	        
	        HSSFRow row13 = sheet.createRow(13);   
	        HSSFCell c130 = row13.createCell(0);   
	        c130.setCellValue(new HSSFRichTextString("农村金融机构"));
	        HSSFCell c131 = row13.createCell(1);   
	        c131.setCellValue(new HSSFRichTextString(null ==ncsyjgPo.getBankAllInflow()?"0":ncsyjgPo.getBankAllInflow().toString())); 
	        HSSFCell c132 = row13.createCell(2);   
	        c132.setCellValue(new HSSFRichTextString(null ==ncsyjgPo.getBankSpecialInflow()?"0":ncsyjgPo.getBankSpecialInflow().toString())); 
	        HSSFCell c133 = row13.createCell(3);   
	        c133.setCellValue(new HSSFRichTextString(null ==ncsyjgPo.getBankAllOutflow()?"0":ncsyjgPo.getBankAllOutflow().toString())); 
	        HSSFCell c134 = row13.createCell(4);   
	        c134.setCellValue(new HSSFRichTextString(null ==ncsyjgPo.getBankSpecialOutflow()?"0":ncsyjgPo.getBankSpecialOutflow().toString())); 
	        HSSFCell c135 = row13.createCell(5);   
	        c135.setCellValue(new HSSFRichTextString(null ==ncsyjgPo.getBankAllNetFlow()?"0":ncsyjgPo.getBankAllNetFlow().toString())); 
	        
	        
	        HSSFRow row14 = sheet.createRow(14);   
	        HSSFCell c140 = row14.createCell(0);   
	        c140.setCellValue(new HSSFRichTextString("邮政储蓄银行"));
	        HSSFCell c141 = row14.createCell(1);   
	        c141.setCellValue(new HSSFRichTextString(null ==yzcxPo.getBankAllInflow()?"0":yzcxPo.getBankAllInflow().toString())); 
	        HSSFCell c142 = row14.createCell(2);   
	        c142.setCellValue(new HSSFRichTextString(null ==yzcxPo.getBankSpecialInflow()?"0":yzcxPo.getBankSpecialInflow().toString())); 
	        HSSFCell c143 = row14.createCell(3);   
	        c143.setCellValue(new HSSFRichTextString(null ==yzcxPo.getBankAllOutflow()?"0":yzcxPo.getBankAllOutflow().toString())); 
	        HSSFCell c144 = row14.createCell(4);   
	        c144.setCellValue(new HSSFRichTextString(null ==yzcxPo.getBankSpecialOutflow()?"0":yzcxPo.getBankSpecialOutflow().toString())); 
	        HSSFCell c145 = row14.createCell(5);   
	        c145.setCellValue(new HSSFRichTextString(null ==yzcxPo.getBankAllNetFlow()?"0":yzcxPo.getBankAllNetFlow().toString())); 
	        
	        
	        HSSFRow row15 = sheet.createRow(15);   
	        HSSFCell c150 = row15.createCell(0);   
	        c150.setCellValue(new HSSFRichTextString("政策性银行"));
	        HSSFCell c151 = row15.createCell(1);   
	        c151.setCellValue(new HSSFRichTextString(null ==zcxPo.getBankAllInflow()?"0":zcxPo.getBankAllInflow().toString())); 
	        HSSFCell c152 = row15.createCell(2);   
	        c152.setCellValue(new HSSFRichTextString(null ==zcxPo.getBankSpecialInflow()?"0":zcxPo.getBankSpecialInflow().toString())); 
	        HSSFCell c153 = row15.createCell(3);   
	        c153.setCellValue(new HSSFRichTextString(null ==zcxPo.getBankAllOutflow()?"0":zcxPo.getBankAllOutflow().toString())); 
	        HSSFCell c154 = row15.createCell(4);   
	        c154.setCellValue(new HSSFRichTextString(null ==zcxPo.getBankSpecialOutflow()?"0":zcxPo.getBankSpecialOutflow().toString())); 
	        HSSFCell c155 = row15.createCell(5);   
	        c155.setCellValue(new HSSFRichTextString(null ==zcxPo.getBankAllNetFlow()?"0":zcxPo.getBankAllNetFlow().toString())); 
	        
	        
	        
	        HSSFRow row16 = sheet.createRow(16);   
	        HSSFCell c160 = row16.createCell(0);   
	        c160.setCellValue(new HSSFRichTextString("其他机构"));
	        HSSFCell c161 = row16.createCell(1);   
	        c161.setCellValue(new HSSFRichTextString(null ==qtjgPo.getBankAllInflow()?"0":qtjgPo.getBankAllInflow().toString())); 
	        HSSFCell c162 = row16.createCell(2);   
	        c162.setCellValue(new HSSFRichTextString(null ==qtjgPo.getBankSpecialInflow()?"0":qtjgPo.getBankSpecialInflow().toString())); 
	        HSSFCell c163 = row16.createCell(3);   
	        c163.setCellValue(new HSSFRichTextString(null ==qtjgPo.getBankAllOutflow()?"0":qtjgPo.getBankAllOutflow().toString())); 
	        HSSFCell c164 = row16.createCell(4);   
	        c164.setCellValue(new HSSFRichTextString(null ==qtjgPo.getBankSpecialOutflow()?"0":qtjgPo.getBankSpecialOutflow().toString())); 
	        HSSFCell c165 = row16.createCell(5);   
	        c165.setCellValue(new HSSFRichTextString(null ==qtjgPo.getBankAllNetFlow()?"0":qtjgPo.getBankAllNetFlow().toString())); 
	        
	        
	        HSSFRow row17 = sheet.createRow(17);   
	        HSSFCell c170 = row17.createCell(0);   
	        c170.setCellValue(new HSSFRichTextString("合计"));
	        HSSFCell c171 = row17.createCell(1);   
	        c171.setCellValue(new HSSFRichTextString(null ==hjPo.getBankAllInflow()?"0":hjPo.getBankAllInflow().toString())); 
	        HSSFCell c172 = row17.createCell(2);   
	        c172.setCellValue(new HSSFRichTextString(null ==hjPo.getBankSpecialInflow()?"0":hjPo.getBankSpecialInflow().toString())); 
	        HSSFCell c173 = row17.createCell(3);   
	        c173.setCellValue(new HSSFRichTextString(null ==hjPo.getBankAllOutflow()?"0":hjPo.getBankAllOutflow().toString())); 
	        HSSFCell c174 = row17.createCell(4);   
	        c174.setCellValue(new HSSFRichTextString(null ==hjPo.getBankSpecialOutflow()?"0":hjPo.getBankSpecialOutflow().toString())); 
	        HSSFCell c175 = row17.createCell(5);   
	        c175.setCellValue(new HSSFRichTextString(null ==hjPo.getBankAllNetFlow()?"0":hjPo.getBankAllNetFlow().toString())); 
	        
	        
	        Map<String,Object> map1 = ReportTargetPo2Map.po2Map(treasuryFundsNatureList);
	        
	        HSSFRow row18 = sheet.createRow(18);   
	        HSSFCell c180 = row18.createCell(0);   
	        c180.setCellValue(new HSSFRichTextString("二、按资金性质分类"));
	        HSSFCell c181 = row18.createCell(1);   
	        c181.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c182 = row18.createCell(2);   
	        c182.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c183 = row18.createCell(3);   
	        c183.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c184 = row18.createCell(4);   
	        c184.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c185 = row18.createCell(5);   
	        c185.setCellValue(new HSSFRichTextString("")); 
	        
	        HSSFRow row19 = sheet.createRow(19);   
	        HSSFCell c190 = row19.createCell(0);   
	        c190.setCellValue(new HSSFRichTextString("业务类型"));
	        HSSFCell c191 = row19.createCell(1);   
	        c191.setCellValue(new HSSFRichTextString("从商业银行流入国库")); 
	        HSSFCell c192 = row19.createCell(2);   
	        c192.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c193 = row19.createCell(3);   
	        c193.setCellValue(new HSSFRichTextString("从国库流向商业银行")); 
	        HSSFCell c194 = row19.createCell(4);   
	        c194.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c195 = row19.createCell(5);   
	        c195.setCellValue(new HSSFRichTextString("净流入（流出）")); 
	        
	        HSSFRow row20 = sheet.createRow(20);   
	        HSSFCell c200 = row20.createCell(0);   
	        c200.setCellValue(new HSSFRichTextString("预算收支退"));
	        HSSFCell c201 = row20.createCell(1);   
	        c201.setCellValue(new HSSFRichTextString("预算收入流入")); 
	        HSSFCell c202 = row20.createCell(2);   
	        c202.setCellValue(new HSSFRichTextString(null==map1.get("TE_00005")?"0":map1.get("TE_00005").toString())); 
	        HSSFCell c203 = row20.createCell(3);   
	        c203.setCellValue(new HSSFRichTextString("预算支出流出")); 
	        HSSFCell c204 = row20.createCell(4);   
	        c204.setCellValue(new HSSFRichTextString(null==map1.get("TE_00036")?"0":map1.get("TE_00036").toString())); 
	        HSSFCell c205 = row20.createCell(5);   
	        c205.setCellValue(new HSSFRichTextString(null==map1.get("TE_00104")?"0":map1.get("TE_00104").toString())); 
	        
	        HSSFRow row21 = sheet.createRow(21);   
	        HSSFCell c210 = row21.createCell(0);   
	        c210.setCellValue(new HSSFRichTextString(""));
	        HSSFCell c211 = row21.createCell(1);   
	        c211.setCellValue(new HSSFRichTextString("其中：纸质税票")); 
	        HSSFCell c212 = row21.createCell(2);   
	        c212.setCellValue(new HSSFRichTextString(null==map1.get("TE_00014")?"0":map1.get("TE_00014").toString())); 
	        HSSFCell c213 = row21.createCell(3);   
	        c213.setCellValue(new HSSFRichTextString("其中：商业银行代理的集中支付")); 
	        HSSFCell c214 = row21.createCell(4);   
	        c214.setCellValue(new HSSFRichTextString(null==map1.get("TE_00046")?"0":map1.get("TE_00046").toString())); 
	        HSSFCell c215 = row21.createCell(5);   
	        c215.setCellValue(new HSSFRichTextString("")); 
	        
	        HSSFRow row22 = sheet.createRow(22);   
	        HSSFCell c220 = row22.createCell(0);   
	        c220.setCellValue(new HSSFRichTextString(""));
	        HSSFCell c221 = row22.createCell(1);   
	        c221.setCellValue(new HSSFRichTextString("电子税票")); 
	        HSSFCell c222 = row22.createCell(2);   
	        c222.setCellValue(new HSSFRichTextString(null==map1.get("TE_00015")?"0":map1.get("TE_00015").toString())); 
	        HSSFCell c223 = row22.createCell(3);   
	        c223.setCellValue(new HSSFRichTextString("——直接支付")); 
	        HSSFCell c224 = row22.createCell(4);   
	        c224.setCellValue(new HSSFRichTextString(null==map1.get("TE_00114")?"0":map1.get("TE_00114").toString())); 
	        HSSFCell c225 = row22.createCell(5);   
	        c225.setCellValue(new HSSFRichTextString("")); 
	        
	        HSSFRow row23 = sheet.createRow(23);   
	        HSSFCell c230 = row23.createCell(0);   
	        c230.setCellValue(new HSSFRichTextString(""));
	        HSSFCell c231 = row23.createCell(1);   
	        c231.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c232 = row23.createCell(2);   
	        c232.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c233 = row23.createCell(3);   
	        c233.setCellValue(new HSSFRichTextString("——授权支付")); 
	        HSSFCell c234 = row23.createCell(4);   
	        c234.setCellValue(new HSSFRichTextString(null==map1.get("TE_00115")?"0":map1.get("TE_00115").toString())); 
	        HSSFCell c235 = row23.createCell(5);   
	        c235.setCellValue(new HSSFRichTextString("")); 
	        
	        
	        HSSFRow row24 = sheet.createRow(24);   
	        HSSFCell c240 = row24.createCell(0);   
	        c240.setCellValue(new HSSFRichTextString(""));
	        HSSFCell c241 = row24.createCell(1);   
	        c241.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c242 = row24.createCell(2);   
	        c242.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c243 = row24.createCell(3);   
	        c243.setCellValue(new HSSFRichTextString("人行直接办理的集中支付")); 
	        HSSFCell c244 = row24.createCell(4);   
	        c244.setCellValue(new HSSFRichTextString(null==map1.get("TE_00047")?"0":map1.get("TE_00047").toString())); 
	        HSSFCell c245 = row24.createCell(5);   
	        c245.setCellValue(new HSSFRichTextString("")); 
	        
	        
	        
	        HSSFRow row25 = sheet.createRow(25);   
	        HSSFCell c250 = row25.createCell(0);   
	        c250.setCellValue(new HSSFRichTextString(""));
	        HSSFCell c251 = row25.createCell(1);   
	        c251.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c252 = row25.createCell(2);   
	        c252.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c253 = row25.createCell(3);   
	        c253.setCellValue(new HSSFRichTextString("实拨资金")); 
	        HSSFCell c254 = row25.createCell(4);   
	        c254.setCellValue(new HSSFRichTextString(null==map1.get("TE_00048")?"0":map1.get("TE_00048").toString())); 
	        HSSFCell c255 = row25.createCell(5);   
	        c255.setCellValue(new HSSFRichTextString("")); 
	        
	        HSSFRow row26 = sheet.createRow(26);   
	        HSSFCell c260 = row26.createCell(0);   
	        c260.setCellValue(new HSSFRichTextString(""));
	        HSSFCell c261 = row26.createCell(1);   
	        c261.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c262 = row26.createCell(2);   
	        c262.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c263 = row26.createCell(3);   
	        c263.setCellValue(new HSSFRichTextString("——国库直接支付")); 
	        HSSFCell c264 = row26.createCell(4);   
	        c264.setCellValue(new HSSFRichTextString(null==map1.get("TE_00116")?"0":map1.get("TE_00116").toString())); 
	        HSSFCell c265 = row26.createCell(5);   
	        c265.setCellValue(new HSSFRichTextString("")); 
	        
	        
	        HSSFRow row27 = sheet.createRow(27);   
	        HSSFCell c270 = row27.createCell(0);   
	        c270.setCellValue(new HSSFRichTextString(""));
	        HSSFCell c271 = row27.createCell(1);   
	        c271.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c272 = row27.createCell(2);   
	        c272.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c273 = row27.createCell(3);   
	        c273.setCellValue(new HSSFRichTextString("预算收入退付流出")); 
	        HSSFCell c274 = row27.createCell(4);   
	        c274.setCellValue(new HSSFRichTextString(null==map1.get("TE_00037")?"0":map1.get("TE_00037").toString())); 
	        HSSFCell c275 = row27.createCell(5);   
	        c275.setCellValue(new HSSFRichTextString("")); 
	        
	        
	        HSSFRow row28 = sheet.createRow(28);   
	        HSSFCell c280 = row28.createCell(0);   
	        c280.setCellValue(new HSSFRichTextString(""));
	        HSSFCell c281 = row28.createCell(1);   
	        c281.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c282 = row28.createCell(2);   
	        c282.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c283 = row28.createCell(3);   
	        c283.setCellValue(new HSSFRichTextString("其中：手工方式")); 
	        HSSFCell c284 = row28.createCell(4);   
	        c284.setCellValue(new HSSFRichTextString(null==map1.get("TE_00049")?"0":map1.get("TE_00049").toString())); 
	        HSSFCell c285 = row28.createCell(5);   
	        c285.setCellValue(new HSSFRichTextString("")); 
	        
	        HSSFRow row29 = sheet.createRow(29);   
	        HSSFCell c290 = row29.createCell(0);   
	        c290.setCellValue(new HSSFRichTextString(""));
	        HSSFCell c291 = row29.createCell(1);   
	        c291.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c292 = row29.createCell(2);   
	        c292.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c293 = row29.createCell(3);   
	        c293.setCellValue(new HSSFRichTextString("电子方式")); 
	        HSSFCell c294 = row29.createCell(4);   
	        c294.setCellValue(new HSSFRichTextString(null==map1.get("TE_00050")?"0":map1.get("TE_00050").toString())); 
	        HSSFCell c295 = row29.createCell(5);   
	        c295.setCellValue(new HSSFRichTextString("")); 
	        
	        
	        HSSFRow row30 = sheet.createRow(30);   
	        HSSFCell c300 = row30.createCell(0);   
	        c300.setCellValue(new HSSFRichTextString("系统外调拨"));
	        HSSFCell c301 = row30.createCell(1);   
	        c301.setCellValue(new HSSFRichTextString("系统外调拨（转移）收入流入")); 
	        HSSFCell c302 = row30.createCell(2);   
	        c302.setCellValue(new HSSFRichTextString(null==map1.get("TE_00006")?"0":map1.get("TE_00006").toString())); 
	        HSSFCell c303 = row30.createCell(3);   
	        c303.setCellValue(new HSSFRichTextString("系统外调拨（转移）支出流出")); 
	        HSSFCell c304 = row30.createCell(4);   
	        c304.setCellValue(new HSSFRichTextString(null==map1.get("TE_00038")?"0":map1.get("TE_00038").toString())); 
	        HSSFCell c305 = row30.createCell(5);   
	        c305.setCellValue(new HSSFRichTextString(null==map1.get("TE_00105")?"0":map1.get("TE_00105").toString())); 
	        
	        HSSFRow row31 = sheet.createRow(31);   
	        HSSFCell c310 = row31.createCell(0);   
	        c310.setCellValue(new HSSFRichTextString("国债发行与兑付"));
	        HSSFCell c311 = row31.createCell(1);   
	        c311.setCellValue(new HSSFRichTextString("发行国家债券流入")); 
	        HSSFCell c312 = row31.createCell(2);   
	        c312.setCellValue(new HSSFRichTextString(null==map1.get("TE_00007")?"0":map1.get("TE_00007").toString())); 
	        HSSFCell c313 = row31.createCell(3);   
	        c313.setCellValue(new HSSFRichTextString("兑付国家债券流出")); 
	        HSSFCell c314 = row31.createCell(4);   
	        c314.setCellValue(new HSSFRichTextString(null==map1.get("TE_00039")?"0":map1.get("TE_00039").toString())); 
	        HSSFCell c315 = row31.createCell(5);   
	        c315.setCellValue(new HSSFRichTextString(null==map1.get("TE_00106")?"0":map1.get("TE_00106").toString())); 
	        
	        HSSFRow row32 = sheet.createRow(32);   
	        HSSFCell c320 = row32.createCell(0);   
	        c320.setCellValue(new HSSFRichTextString("地方政府债券发行与兑付"));
	        HSSFCell c321 = row32.createCell(1);   
	        c321.setCellValue(new HSSFRichTextString("发行地方政府债券流入")); 
	        HSSFCell c322 = row32.createCell(2);   
	        c322.setCellValue(new HSSFRichTextString(null==map1.get("TE_00008")?"0":map1.get("TE_00008").toString())); 
	        HSSFCell c323 = row32.createCell(3);   
	        c323.setCellValue(new HSSFRichTextString("兑付地方政府债券本息流出")); 
	        HSSFCell c324 = row32.createCell(4);   
	        c324.setCellValue(new HSSFRichTextString(null==map1.get("TE_00040")?"0":map1.get("TE_00040").toString())); 
	        HSSFCell c325 = row32.createCell(5);   
	        c325.setCellValue(new HSSFRichTextString(null==map1.get("TE_00107")?"0":map1.get("TE_00107").toString())); 
	        
	        HSSFRow row33 = sheet.createRow(33);   
	        HSSFCell c330 = row33.createCell(0);   
	        c330.setCellValue(new HSSFRichTextString("国库现金管理"));
	        HSSFCell c331 = row33.createCell(1);   
	        c331.setCellValue(new HSSFRichTextString("国库现金管理到期流入")); 
	        HSSFCell c332 = row33.createCell(2);   
	        c332.setCellValue(new HSSFRichTextString(null==map1.get("TE_00009")?"0":map1.get("TE_00009").toString())); 
	        HSSFCell c333 = row33.createCell(3);   
	        c333.setCellValue(new HSSFRichTextString("国库现金管理操作流出")); 
	        HSSFCell c334 = row33.createCell(4);   
	        c334.setCellValue(new HSSFRichTextString(null==map1.get("TE_00041")?"0":map1.get("TE_00041").toString())); 
	        HSSFCell c335 = row33.createCell(5);   
	        c335.setCellValue(new HSSFRichTextString(null==map1.get("TE_00108")?"0":map1.get("TE_00108").toString())); 
	        
	        
	        HSSFRow row34 = sheet.createRow(34);   
	        HSSFCell c340 = row34.createCell(0);   
	        c340.setCellValue(new HSSFRichTextString("其他"));
	        HSSFCell c341 = row34.createCell(1);   
	        c341.setCellValue(new HSSFRichTextString("其他流入")); 
	        HSSFCell c342 = row34.createCell(2);   
	        c342.setCellValue(new HSSFRichTextString(null==map1.get("TE_00010")?"0":map1.get("TE_00010").toString())); 
	        HSSFCell c343 = row34.createCell(3);   
	        c343.setCellValue(new HSSFRichTextString("其他流出")); 
	        HSSFCell c344 = row34.createCell(4);   
	        c344.setCellValue(new HSSFRichTextString(null==map1.get("TE_00042")?"0":map1.get("TE_00042").toString())); 
	        HSSFCell c345 = row34.createCell(5);   
	        c345.setCellValue(new HSSFRichTextString(null==map1.get("TE_00109")?"0":map1.get("TE_00109").toString())); 
	        
	        HSSFRow row44 = sheet.createRow(35);   
	        HSSFCell c440 = row44.createCell(0);   
	        c440.setCellValue(new HSSFRichTextString(""));
	        HSSFCell c441 = row44.createCell(1);   
	        c441.setCellValue(new HSSFRichTextString("合计")); 
	        HSSFCell c442 = row44.createCell(2);   
	        c442.setCellValue(new HSSFRichTextString(null==map1.get("TE_00002")?"0":map1.get("TE_00002").toString())); 
	        HSSFCell c443 = row44.createCell(3);   
	        c443.setCellValue(new HSSFRichTextString("合计")); 
	        HSSFCell c444 = row44.createCell(4);   
	        c444.setCellValue(new HSSFRichTextString(null==map1.get("TE_00033")?"0":map1.get("TE_00033").toString())); 
	        HSSFCell c445 = row44.createCell(5);   
	        c445.setCellValue(new HSSFRichTextString(null==map1.get("TE_00067")?"0":map1.get("TE_00067").toString())); 
	        
	        BigDecimal aIn =  hjPo.getBankAllInflow();
	        BigDecimal bIn = (BigDecimal)map1.get("TE_00002");
	        
	        
	        BigDecimal aOut =  hjPo.getBankAllOutflow();
	        BigDecimal bOut = (BigDecimal)map1.get("TE_00033");
	        
	        BigDecimal aNet =  hjPo.getBankAllNetFlow();
	        BigDecimal bNet = (BigDecimal)map1.get("TE_00067");
	        
	        HSSFRow row35 = sheet.createRow(36);   
	        HSSFCell c350 = row35.createCell(0);   
	        c350.setCellValue(new HSSFRichTextString("与按资金流向分类的资金流入、流出合计是否一致："));
	        HSSFCell c351 = row35.createCell(1);   
	        c351.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c352 = row35.createCell(2);   
	        c352.setCellValue(new HSSFRichTextString(aIn.equals(bIn)?"核对一致":"核对不一致")); 
	        HSSFCell c353 = row35.createCell(3);   
	        c353.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c354 = row35.createCell(4);   
	        c354.setCellValue(new HSSFRichTextString(aOut.equals(bOut)?"核对一致":"核对不一致")); 
	        HSSFCell c355 = row35.createCell(5);   
	        c355.setCellValue(new HSSFRichTextString(aNet.equals(bNet)?"核对一致":"核对不一致")); 
	        
	        Map<String,Object> map2 = ReportTargetPo2Map.po2Map(treasuryFundsSourceList);
	        
	        HSSFRow row36 = sheet.createRow(37);   
	        HSSFCell c360 = row36.createCell(0);   
	        c360.setCellValue(new HSSFRichTextString("三、按资金来源分类"));
	        HSSFCell c361 = row36.createCell(1);   
	        c361.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c362 = row36.createCell(2);   
	        c362.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c363 = row36.createCell(3);   
	        c363.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c364 = row36.createCell(4);   
	        c364.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c365 = row36.createCell(5);   
	        c365.setCellValue(new HSSFRichTextString("")); 
	        
	        
	        HSSFRow row37 = sheet.createRow(38);   
	        HSSFCell c370 = row37.createCell(0);   
	        c370.setCellValue(new HSSFRichTextString("系统类别"));
	        HSSFCell c371 = row37.createCell(1);   
	        c371.setCellValue(new HSSFRichTextString("从商业银行流入国库")); 
	        HSSFCell c372 = row37.createCell(2);   
	        c372.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c373 = row37.createCell(3);   
	        c373.setCellValue(new HSSFRichTextString("从国库流向商业银行")); 
	        HSSFCell c374 = row37.createCell(4);   
	        c374.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c375 = row37.createCell(5);   
	        c375.setCellValue(new HSSFRichTextString("净流入（流出）")); 
	        
	        
	        HSSFRow row38 = sheet.createRow(39);   
	        HSSFCell c380 = row38.createCell(0);   
	        c380.setCellValue(new HSSFRichTextString("大额支付系统"));
	        HSSFCell c381 = row38.createCell(1);   
	        c381.setCellValue(new HSSFRichTextString(null==map2.get("TE_00011")?"0":map2.get("TE_00011").toString())); 
	        HSSFCell c382 = row38.createCell(2);   
	        c382.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c383 = row38.createCell(3);   
	        c383.setCellValue(new HSSFRichTextString(null==map2.get("TE_00043")?"0":map2.get("TE_00043").toString())); 
	        HSSFCell c384 = row38.createCell(4);   
	        c384.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c385 = row38.createCell(5);   
	        c385.setCellValue(new HSSFRichTextString(null==map2.get("TE_00110")?"0":map2.get("TE_00110").toString())); 
	        
	        HSSFRow row39 = sheet.createRow(40);   
	        HSSFCell c390 = row39.createCell(0);   
	        c390.setCellValue(new HSSFRichTextString("小额支付系统"));
	        HSSFCell c391 = row39.createCell(1);   
	        c391.setCellValue(new HSSFRichTextString(null==map2.get("TE_00012")?"0":map2.get("TE_00012").toString())); 
	        HSSFCell c392 = row39.createCell(2);   
	        c392.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c393 = row39.createCell(3);   
	        c393.setCellValue(new HSSFRichTextString(null==map2.get("TE_00044")?"0":map2.get("TE_00044").toString())); 
	        HSSFCell c394 = row39.createCell(4);   
	        c394.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c395 = row39.createCell(5);   
	        c395.setCellValue(new HSSFRichTextString(null==map2.get("TE_00111")?"0":map2.get("TE_00111").toString())); 
	        
	        
	        
	        HSSFRow row40 = sheet.createRow(41);   
	        HSSFCell c400 = row40.createCell(0);   
	        c400.setCellValue(new HSSFRichTextString("同城票据交换系统"));
	        HSSFCell c401 = row40.createCell(1);   
	        c401.setCellValue(new HSSFRichTextString(null==map2.get("TE_00013")?"0":map2.get("TE_00013").toString())); 
	        HSSFCell c402 = row40.createCell(2);   
	        c402.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c403 = row40.createCell(3);   
	        c403.setCellValue(new HSSFRichTextString(null==map2.get("TE_00045")?"0":map2.get("TE_00045").toString())); 
	        HSSFCell c404 = row40.createCell(4);   
	        c404.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c405 = row40.createCell(5);   
	        c405.setCellValue(new HSSFRichTextString(null==map2.get("TE_00112")?"0":map2.get("TE_00112").toString())); 
	        
	        HSSFRow row41 = sheet.createRow(42);   
	        HSSFCell c410 = row41.createCell(0);   
	        c410.setCellValue(new HSSFRichTextString("其他"));
	        HSSFCell c411 = row41.createCell(1);   
	        c411.setCellValue(new HSSFRichTextString(null==map2.get("TE_00117")?"0":map2.get("TE_00117").toString())); 
	        HSSFCell c412 = row41.createCell(2);   
	        c412.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c413 = row41.createCell(3);   
	        c413.setCellValue(new HSSFRichTextString(null==map2.get("TE_00118")?"0":map2.get("TE_00118").toString())); 
	        HSSFCell c414 = row41.createCell(4);   
	        c414.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c415 = row41.createCell(5);   
	        c415.setCellValue(new HSSFRichTextString(null==map2.get("TE_00113")?"0":map2.get("TE_00113").toString())); 
	        
	        HSSFRow row42 = sheet.createRow(43);   
	        HSSFCell c420 = row42.createCell(0);   
	        c420.setCellValue(new HSSFRichTextString("合 计"));
	        HSSFCell c421 = row42.createCell(1);   
	        c421.setCellValue(new HSSFRichTextString(null==map2.get("SourceBankAllInflowSum")?"0":map2.get("SourceBankAllInflowSum").toString())); 
	        HSSFCell c422 = row42.createCell(2);   
	        c422.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c423 = row42.createCell(3);   
	        c423.setCellValue(new HSSFRichTextString(null==map2.get("SourceBankAllOutflowSum")?"0":map2.get("SourceBankAllOutflowSum").toString())); 
	        HSSFCell c424 = row42.createCell(4);   
	        c424.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c425 = row42.createCell(5);   
	        c425.setCellValue(new HSSFRichTextString(null==map2.get("SourceBankAllNetflowSum")?"0":map2.get("SourceBankAllNetflowSum").toString())); 
	        BigDecimal cIn = (BigDecimal)map2.get("SourceBankAllInflowSum");
	        BigDecimal cOut = (BigDecimal)map2.get("SourceBankAllOutflowSum");
	        BigDecimal cNet = (BigDecimal)map2.get("SourceBankAllNetflowSum");
	        HSSFRow row43 = sheet.createRow(44);   
	        HSSFCell c430 = row43.createCell(0);   
	        c430.setCellValue(new HSSFRichTextString("与按资金流向分类的资金流入、流出合计是否一致："));
	        HSSFCell c431 = row43.createCell(1);   
	        c431.setCellValue(new HSSFRichTextString(aIn.equals(cIn)?"核对一致":"核对不一致")); 
	        HSSFCell c432 = row43.createCell(2);   
	        c432.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c433 = row43.createCell(3);   
	        c433.setCellValue(new HSSFRichTextString(aOut.equals(cOut)?"核对一致":"核对不一致")); 
	        HSSFCell c434 = row43.createCell(4);   
	        c434.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c435 = row43.createCell(5);   
	        c435.setCellValue(new HSSFRichTextString(aNet.equals(cNet)?"核对一致":"核对不一致")); 
	        //单元格合并 ，每行都进行特殊处理
	        CellRangeAddress region1 = new CellRangeAddress(1,1, 0,  5); 
	        CellRangeAddress region2 = new CellRangeAddress(2, 2,0, 1);   
	        CellRangeAddress region3 = new CellRangeAddress(2, 2,2,  3);
	        CellRangeAddress region4 = new CellRangeAddress(2, 2,4,  5); 
	        CellRangeAddress region5 = new CellRangeAddress(3, 3,0,  5);  
	        CellRangeAddress region6 = new CellRangeAddress(18, 18,0,  5);  
	        CellRangeAddress region8 = new CellRangeAddress(19, 19,1,  2); 
	        CellRangeAddress region9 = new CellRangeAddress(19, 19,3,  4); 
	        CellRangeAddress region11 = new CellRangeAddress(20, 29,0,  0); 
	        CellRangeAddress region12 = new CellRangeAddress(20, 29,5,  5); 
	        CellRangeAddress region13 = new CellRangeAddress(36, 36,0,  1);  
	        CellRangeAddress region14 = new CellRangeAddress(38, 38,1,  2); 
	        CellRangeAddress region15 = new CellRangeAddress(38, 38,3, 4); 
	        CellRangeAddress region35 = new CellRangeAddress(37, 37,0, 5); 
	        CellRangeAddress region17 = new CellRangeAddress(39, 39,1,  2); 
	        CellRangeAddress region18 = new CellRangeAddress(39, 39,3,  4); 
	        CellRangeAddress region20 = new CellRangeAddress(40, 40,1,  2); 
	        CellRangeAddress region21 = new CellRangeAddress(40, 40, 3, 4); 
	        CellRangeAddress region23 = new CellRangeAddress(41, 41,1,  2); 
	        CellRangeAddress region24 = new CellRangeAddress(41, 41,3,  4); 
	        CellRangeAddress region26 = new CellRangeAddress(42, 42,1,  2); 
	        CellRangeAddress region27 = new CellRangeAddress(42, 42,3,  4); 
	        CellRangeAddress region29 = new CellRangeAddress(43,43, 1,  2); 
	        CellRangeAddress region30 = new CellRangeAddress(43,43, 3,  4); 
	        CellRangeAddress region32 = new CellRangeAddress(44,44, 1,  2); 
	        CellRangeAddress region33 = new CellRangeAddress(44,44, 3,  4); 
	        sheet.addMergedRegion(region1);   
	        sheet.addMergedRegion(region2);   
	        sheet.addMergedRegion(region3); 
	        sheet.addMergedRegion(region4); 
	        sheet.addMergedRegion(region5);   
	        sheet.addMergedRegion(region6); 
	        sheet.addMergedRegion(region8);   
	        sheet.addMergedRegion(region9); 
	        sheet.addMergedRegion(region11);   
	        sheet.addMergedRegion(region12);   
	        sheet.addMergedRegion(region13); 
	        sheet.addMergedRegion(region14); 
	        sheet.addMergedRegion(region15);   
	        sheet.addMergedRegion(region17);
	        sheet.addMergedRegion(region18);   
	        sheet.addMergedRegion(region20); 
	        sheet.addMergedRegion(region21);   
	        sheet.addMergedRegion(region23); 
	        sheet.addMergedRegion(region24); 
	        sheet.addMergedRegion(region26); 
	        sheet.addMergedRegion(region27);
	        sheet.addMergedRegion(region29); 
	        sheet.addMergedRegion(region30); 
	        sheet.addMergedRegion(region32);   
	        sheet.addMergedRegion(region33); 
	        sheet.addMergedRegion(region35);   
	        
	        //表格框设置
	        c10.setCellStyle(style1); 
	        c11.setCellStyle(style1); 
	        c12.setCellStyle(style1); 
	        c13.setCellStyle(style1); 
	        c14.setCellStyle(style1); 
	        c15.setCellStyle(style1); 
	        
	        c20.setCellStyle(style2); 
	        c21.setCellStyle(style2); 
	        c22.setCellStyle(style2); 
	        c23.setCellStyle(style2); 
	        c24.setCellStyle(style3); 
	        c25.setCellStyle(style3); 
	        
	        c30.setCellStyle(style2); 
	        c31.setCellStyle(style2); 
	        c32.setCellStyle(style2); 
	        c33.setCellStyle(style2); 
	        c34.setCellStyle(style2); 
	        c35.setCellStyle(style2); 
	        
	        
	        c40.setCellStyle(style4); 
	        c41.setCellStyle(style4); 
	        c42.setCellStyle(style4); 
	        c43.setCellStyle(style4); 
	        c44.setCellStyle(style4); 
	        c45.setCellStyle(style4); 
	        
	        c50.setCellStyle(style); 
	        c51.setCellStyle(style); 
	        c52.setCellStyle(style); 
	        c53.setCellStyle(style); 
	        c54.setCellStyle(style); 
	        c55.setCellStyle(style); 
	        
	        c60.setCellStyle(style); 
	        c61.setCellStyle(style); 
	        c62.setCellStyle(style); 
	        c63.setCellStyle(style); 
	        c64.setCellStyle(style); 
	        c65.setCellStyle(style); 
	        
	        c70.setCellStyle(style); 
	        c71.setCellStyle(style); 
	        c72.setCellStyle(style); 
	        c73.setCellStyle(style); 
	        c74.setCellStyle(style); 
	        c75.setCellStyle(style); 
	        
	        c80.setCellStyle(style); 
	        c81.setCellStyle(style); 
	        c82.setCellStyle(style); 
	        c83.setCellStyle(style); 
	        c84.setCellStyle(style); 
	        c85.setCellStyle(style); 
	        
	        
	        c90.setCellStyle(style); 
	        c91.setCellStyle(style); 
	        c92.setCellStyle(style); 
	        c93.setCellStyle(style); 
	        c94.setCellStyle(style); 
	        c95.setCellStyle(style); 
	        
	        c100.setCellStyle(style); 
	        c101.setCellStyle(style); 
	        c102.setCellStyle(style); 
	        c103.setCellStyle(style); 
	        c104.setCellStyle(style); 
	        c105.setCellStyle(style); 
	        
	        c110.setCellStyle(style); 
	        c111.setCellStyle(style); 
	        c112.setCellStyle(style); 
	        c113.setCellStyle(style); 
	        c114.setCellStyle(style); 
	        c115.setCellStyle(style); 
	        
	        
	        c120.setCellStyle(style); 
	        c121.setCellStyle(style); 
	        c122.setCellStyle(style); 
	        c123.setCellStyle(style); 
	        c124.setCellStyle(style); 
	        c125.setCellStyle(style); 
	        
	        c130.setCellStyle(style); 
	        c131.setCellStyle(style); 
	        c132.setCellStyle(style); 
	        c133.setCellStyle(style); 
	        c134.setCellStyle(style); 
	        c135.setCellStyle(style); 
	        
	        
	        c140.setCellStyle(style); 
	        c141.setCellStyle(style); 
	        c142.setCellStyle(style); 
	        c143.setCellStyle(style); 
	        c144.setCellStyle(style); 
	        c145.setCellStyle(style); 
	        
	        c150.setCellStyle(style); 
	        c151.setCellStyle(style); 
	        c152.setCellStyle(style); 
	        c153.setCellStyle(style); 
	        c154.setCellStyle(style); 
	        c155.setCellStyle(style); 
	        
	        
	        c160.setCellStyle(style); 
	        c161.setCellStyle(style); 
	        c162.setCellStyle(style); 
	        c163.setCellStyle(style); 
	        c164.setCellStyle(style); 
	        c165.setCellStyle(style); 
	        
	        c170.setCellStyle(style); 
	        c171.setCellStyle(style); 
	        c172.setCellStyle(style); 
	        c173.setCellStyle(style); 
	        c174.setCellStyle(style); 
	        c175.setCellStyle(style); 
	        
	        c180.setCellStyle(style2); 
	        c181.setCellStyle(style2); 
	        c182.setCellStyle(style2); 
	        c183.setCellStyle(style2); 
	        c184.setCellStyle(style2); 
	        c185.setCellStyle(style2); 
	        
	        
	        c190.setCellStyle(style4); 
	        c191.setCellStyle(style4); 
	        c192.setCellStyle(style4); 
	        c193.setCellStyle(style4); 
	        c194.setCellStyle(style4); 
	        c195.setCellStyle(style4); 
	        
	        c200.setCellStyle(style5); 
	        c201.setCellStyle(style); 
	        c202.setCellStyle(style); 
	        c203.setCellStyle(style); 
	        c204.setCellStyle(style); 
	        c205.setCellStyle(style5); 
	        
	        c210.setCellStyle(style5); 
	        c211.setCellStyle(style); 
	        c212.setCellStyle(style); 
	        c213.setCellStyle(style); 
	        c214.setCellStyle(style); 
	        c215.setCellStyle(style5); 
	        
	        c220.setCellStyle(style5); 
	        c221.setCellStyle(style); 
	        c222.setCellStyle(style); 
	        c223.setCellStyle(style); 
	        c224.setCellStyle(style); 
	        c225.setCellStyle(style5); 
	        
	        c230.setCellStyle(style5); 
	        c231.setCellStyle(style); 
	        c232.setCellStyle(style); 
	        c233.setCellStyle(style); 
	        c234.setCellStyle(style); 
	        c235.setCellStyle(style5); 
	        
	        
	        c240.setCellStyle(style5); 
	        c241.setCellStyle(style); 
	        c242.setCellStyle(style); 
	        c243.setCellStyle(style); 
	        c244.setCellStyle(style); 
	        c245.setCellStyle(style5); 
	        
	        c250.setCellStyle(style5); 
	        c251.setCellStyle(style); 
	        c252.setCellStyle(style); 
	        c253.setCellStyle(style); 
	        c254.setCellStyle(style); 
	        c255.setCellStyle(style5); 
	        
	        
	        c260.setCellStyle(style5); 
	        c261.setCellStyle(style); 
	        c262.setCellStyle(style); 
	        c263.setCellStyle(style); 
	        c264.setCellStyle(style); 
	        c265.setCellStyle(style5); 
	        
	        c270.setCellStyle(style5); 
	        c271.setCellStyle(style); 
	        c272.setCellStyle(style); 
	        c273.setCellStyle(style); 
	        c274.setCellStyle(style); 
	        c275.setCellStyle(style5); 
	        
	        c280.setCellStyle(style5); 
	        c281.setCellStyle(style); 
	        c282.setCellStyle(style); 
	        c283.setCellStyle(style); 
	        c284.setCellStyle(style); 
	        c285.setCellStyle(style5); 
	        
	        
	        c290.setCellStyle(style5); 
	        c291.setCellStyle(style); 
	        c292.setCellStyle(style); 
	        c293.setCellStyle(style); 
	        c294.setCellStyle(style); 
	        c295.setCellStyle(style5); 
	        
	        c300.setCellStyle(style); 
	        c301.setCellStyle(style); 
	        c302.setCellStyle(style); 
	        c303.setCellStyle(style); 
	        c304.setCellStyle(style); 
	        c305.setCellStyle(style); 
	        
	        c310.setCellStyle(style); 
	        c311.setCellStyle(style); 
	        c312.setCellStyle(style); 
	        c313.setCellStyle(style); 
	        c314.setCellStyle(style); 
	        c315.setCellStyle(style); 
	        
	        c320.setCellStyle(style); 
	        c321.setCellStyle(style); 
	        c322.setCellStyle(style); 
	        c323.setCellStyle(style); 
	        c324.setCellStyle(style); 
	        c325.setCellStyle(style); 
	        
	        c330.setCellStyle(style); 
	        c331.setCellStyle(style); 
	        c332.setCellStyle(style); 
	        c333.setCellStyle(style); 
	        c334.setCellStyle(style); 
	        c335.setCellStyle(style); 
	        
	        
	        c340.setCellStyle(style); 
	        c341.setCellStyle(style); 
	        c342.setCellStyle(style); 
	        c343.setCellStyle(style); 
	        c344.setCellStyle(style); 
	        c345.setCellStyle(style); 
	        
	        c350.setCellStyle(style); 
	        c351.setCellStyle(style); 
	        c352.setCellStyle(style); 
	        c353.setCellStyle(style); 
	        c354.setCellStyle(style); 
	        c355.setCellStyle(style); 
	        
	        
	        c360.setCellStyle(style2); 
	        c361.setCellStyle(style2); 
	        c362.setCellStyle(style2); 
	        c363.setCellStyle(style2); 
	        c364.setCellStyle(style2); 
	        c365.setCellStyle(style2); 
	        
	        c370.setCellStyle(style4); 
	        c371.setCellStyle(style4); 
	        c372.setCellStyle(style4); 
	        c373.setCellStyle(style4); 
	        c374.setCellStyle(style4); 
	        c375.setCellStyle(style4); 
	        
	        c380.setCellStyle(style); 
	        c381.setCellStyle(style); 
	        c382.setCellStyle(style); 
	        c383.setCellStyle(style); 
	        c384.setCellStyle(style); 
	        c385.setCellStyle(style); 
	        
	        
	        c390.setCellStyle(style); 
	        c391.setCellStyle(style); 
	        c392.setCellStyle(style); 
	        c393.setCellStyle(style); 
	        c394.setCellStyle(style); 
	        c395.setCellStyle(style); 
	        
	        c400.setCellStyle(style); 
	        c401.setCellStyle(style); 
	        c402.setCellStyle(style); 
	        c403.setCellStyle(style); 
	        c404.setCellStyle(style); 
	        c405.setCellStyle(style); 
	       
	        c410.setCellStyle(style); 
	        c411.setCellStyle(style); 
	        c412.setCellStyle(style); 
	        c413.setCellStyle(style); 
	        c414.setCellStyle(style); 
	        c415.setCellStyle(style); 
	        c420.setCellStyle(style); 
	        c421.setCellStyle(style); 
	        c422.setCellStyle(style); 
	        c423.setCellStyle(style); 
	        c424.setCellStyle(style); 
	        c425.setCellStyle(style); 
	        
	        c430.setCellStyle(style); 
	        c431.setCellStyle(style); 
	        c432.setCellStyle(style); 
	        c433.setCellStyle(style); 
	        c434.setCellStyle(style); 
	        c435.setCellStyle(style); 
	        
	        c440.setCellStyle(style); 
	        c441.setCellStyle(style); 
	        c442.setCellStyle(style); 
	        c443.setCellStyle(style); 
	        c444.setCellStyle(style); 
	        c445.setCellStyle(style); 
	        
	        try{  
	            workbook.write(fout);  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace(); 
	        }  
	}

}
