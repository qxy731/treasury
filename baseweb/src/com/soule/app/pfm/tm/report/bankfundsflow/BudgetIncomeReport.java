package com.soule.app.pfm.tm.report.bankfundsflow;

import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

public class BudgetIncomeReport {
	
	public static void Data2Excel(Map<String,Object> map,ByteArrayOutputStream fout){
		String dataDate = map.get("dataDate").toString();		
		//String subCode = map.get("subCode").toString();
		String cntType = map.get("cntType").toString();
		String dataType = map.get("dataType").toString();
		List<ReportBudgetIncomePo> budgetIncomeList =   (List<ReportBudgetIncomePo>)map.get("budgetIncomeList");
		    HSSFWorkbook workbook = new HSSFWorkbook();   
	        //创建sheet页  
	        HSSFSheet sheet = workbook.createSheet("国库预算收入月报表");
	        
	        sheet.setColumnWidth(0, 15*256);
	        sheet.setColumnWidth(1, 25*256);
	        sheet.setColumnWidth(2, 15*256);
	        sheet.setColumnWidth(3, 25*256);
	        sheet.setColumnWidth(4, 20*256);
	        sheet.setColumnWidth(5, 20*256);
	        sheet.setColumnWidth(6, 20*256);
	        sheet.setColumnWidth(7, 20*256);
	        sheet.setColumnWidth(8, 20*256);
	        
	        CellStyle style = workbook.createCellStyle();  
	        CellStyle style1 = workbook.createCellStyle();//合并单元格表格样式
	        CellStyle style2 = workbook.createCellStyle();//第二行表格样式
	        CellStyle style3 = workbook.createCellStyle();//第三行表格样式
	        CellStyle style4 = workbook.createCellStyle();//
	        CellStyle style5 = workbook.createCellStyle();//记录行样式
	        //设置表头字体
	        
	        HSSFFont font = workbook.createFont();    
	        font.setFontName("黑体");    
	        font.setFontHeightInPoints((short) 16);//设置字体大小    
	        style.setFont(font);//选择需要用到的字体格式    
	        //首行样式   
	        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style.setBorderBottom(CellStyle.BORDER_MEDIUM); 
	        style.setBorderLeft(CellStyle.BORDER_MEDIUM);  
	        style.setBorderRight(CellStyle.BORDER_MEDIUM);  
	        style.setBorderTop(CellStyle.BORDER_MEDIUM);
	        style.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
	        //第一行 表格样式
	        style1.setAlignment(HSSFCellStyle.ALIGN_LEFT);
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
	        //合并单元格样式
	        style4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style4.setBorderBottom(CellStyle.BORDER_MEDIUM); 
	        style4.setBorderLeft(CellStyle.BORDER_MEDIUM);  
	        style4.setBorderRight(CellStyle.BORDER_MEDIUM);  
	        style4.setBorderTop(CellStyle.BORDER_MEDIUM); 
	        style4.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
	        style4.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        
	      //首行样式    
	        style5.setBorderBottom(CellStyle.BORDER_MEDIUM); 
	        style5.setBorderLeft(CellStyle.BORDER_MEDIUM);  
	        style5.setBorderRight(CellStyle.BORDER_MEDIUM);  
	        style5.setBorderTop(CellStyle.BORDER_MEDIUM);	        
	        
	        //创建单元格  
	        HSSFRow row = sheet.createRow(1);   
	        HSSFCell c0 = row.createCell(0);   
	        c0.setCellValue(new HSSFRichTextString("国库预算收入月报表"));
	        c0.setCellStyle(style);
	        HSSFCell c1 = row.createCell(1);   
	        c1.setCellValue(new HSSFRichTextString("")); 
	        c1.setCellStyle(style);
	        HSSFCell c2 = row.createCell(2);   
	        c2.setCellValue(new HSSFRichTextString(""));
	        c2.setCellStyle(style);
	        HSSFCell c3 = row.createCell(3);   
	        c3.setCellValue(new HSSFRichTextString("")); 
	        c3.setCellStyle(style);
	        HSSFCell c4 = row.createCell(4);   
	        c4.setCellValue(new HSSFRichTextString("")); 
	        c4.setCellStyle(style);
	        HSSFCell c5 = row.createCell(5);   
	        c5.setCellValue(new HSSFRichTextString("")); 
	        c5.setCellStyle(style);
	        HSSFCell c6 = row.createCell(6);   
	        c6.setCellValue(new HSSFRichTextString("")); 
	        c6.setCellStyle(style);
	        HSSFCell c7 = row.createCell(7);   
	        c7.setCellValue(new HSSFRichTextString(""));
	        c7.setCellStyle(style);
	        HSSFCell c8 = row.createCell(8);   
	        c8.setCellValue(new HSSFRichTextString(""));
	        c8.setCellStyle(style);
	        
	        HSSFRow row1 = sheet.createRow(2);
	        HSSFCell c9 = row1.createCell(0);   
	        c9.setCellValue(new HSSFRichTextString("业务期间："+dataDate));
	        c9.setCellStyle(style1);
	        HSSFCell c10 = row1.createCell(1);   
	        c10.setCellValue(new HSSFRichTextString(""));
	        c10.setCellStyle(style1);
	        HSSFCell c11 = row1.createCell(2);
	        if("0".equals(cntType)){
	        	cntType = "全辖";
	        }else if("1".equals(cntType)){
	        	cntType = "本级";
	        }else if("2".equals(cntType)){
	        	cntType = "全辖非本级";
	        }
	        c11.setCellValue(new HSSFRichTextString("汇表范围："+cntType)); 
	        c11.setCellStyle(style2);
	        HSSFCell c12 = row1.createCell(3);   
	        c12.setCellValue(new HSSFRichTextString(""));
	        c12.setCellStyle(style2);
	        HSSFCell c13 = row1.createCell(4);
	        if("1".equals(dataType)){
	        	dataType = "本月发生额";
	        }else if("2".equals(dataType)){
	        	dataType = "本年累计";
	        }else{
	        	dataType = "";
	        }
	        c13.setCellValue(new HSSFRichTextString("数据范围："+dataType));
	        c13.setCellStyle(style2);
	        HSSFCell c14 = row1.createCell(5);   
	        c14.setCellValue(new HSSFRichTextString(""));
	        c14.setCellStyle(style2);
	        HSSFCell c15 = row1.createCell(6);   
	        c15.setCellValue(new HSSFRichTextString("单位：万元")); 
	        c15.setCellStyle(style3);
	        HSSFCell c16 = row1.createCell(7);   
	        c16.setCellValue(new HSSFRichTextString(""));
	        c16.setCellStyle(style3);
	        HSSFCell c17 = row1.createCell(8);   
	        c17.setCellValue(new HSSFRichTextString(""));
	        c17.setCellStyle(style3);
	        //单元格合并 ，每行都进行特殊处理
	        CellRangeAddress region1 = new CellRangeAddress(1, 1, 0, 8);   
	        CellRangeAddress region2 = new CellRangeAddress(2, 2, 0, 1);   
	        CellRangeAddress region3 = new CellRangeAddress(2, 2, 2, 3);
	        CellRangeAddress region4 = new CellRangeAddress(2, 2, 4, 5); 
	        CellRangeAddress region5 = new CellRangeAddress(2, 2, 6, 8);
	        sheet.addMergedRegion(region1);   
	        sheet.addMergedRegion(region2);   
	        sheet.addMergedRegion(region3); 
	        sheet.addMergedRegion(region4);
	        sheet.addMergedRegion(region5); 
	        
	        HSSFRow row2 = sheet.createRow(3);   
	        HSSFCell c20 = row2.createCell(0);   
	        c20.setCellValue(new HSSFRichTextString("国库代码"));
	        c20.setCellStyle(style4);
	        HSSFCell c21 = row2.createCell(1);   
	        c21.setCellValue(new HSSFRichTextString("国库名称"));
	        c21.setCellStyle(style4);
	        HSSFCell c22 = row2.createCell(2);   
	        c22.setCellValue(new HSSFRichTextString("预算科目代码"));
	        c22.setCellStyle(style4);
	        HSSFCell c23 = row2.createCell(3);   
	        c23.setCellValue(new HSSFRichTextString("预算科目名称"));
	        c23.setCellStyle(style4);
	        HSSFCell c24 = row2.createCell(4);   
	        c24.setCellValue(new HSSFRichTextString("中央"));
	        c24.setCellStyle(style4);
	        HSSFCell c25 = row2.createCell(5);   
	        c25.setCellValue(new HSSFRichTextString("省"));
	        c25.setCellStyle(style4);
	        HSSFCell c26 = row2.createCell(6);   
	        c26.setCellValue(new HSSFRichTextString("地市")); 
	        c26.setCellStyle(style4);
	        HSSFCell c27 = row2.createCell(7);   
	        c27.setCellValue(new HSSFRichTextString("区（县）"));
	        c27.setCellStyle(style4);
	        HSSFCell c28 = row2.createCell(8);   
	        c28.setCellValue(new HSSFRichTextString("乡镇"));
	        c28.setCellStyle(style4);
	        if(budgetIncomeList != null){
	        for(int i=0;i<budgetIncomeList.size();i++){
	        	ReportBudgetIncomePo po = budgetIncomeList.get(i);
	        	HSSFRow row3 = sheet.createRow(i+4);
	        	HSSFCell c31 = row3.createCell(0);   
		        c31.setCellValue(new HSSFRichTextString(po.getUnitId())); 
		        c31.setCellStyle(style5); 
		        HSSFCell c32 = row3.createCell(1);   
		        c32.setCellValue(new HSSFRichTextString(po.getUnitName())); 
		        c32.setCellStyle(style5); 
		        HSSFCell c33 = row3.createCell(2);   
		        c33.setCellValue(new HSSFRichTextString(po.getSubCode())); 
		        c33.setCellStyle(style5);
		        HSSFCell c34 = row3.createCell(3);   
		        c34.setCellValue(new HSSFRichTextString(po.getSubName())); 
		        c34.setCellStyle(style5);
		        HSSFCell c35 = row3.createCell(4);   
		        c35.setCellValue(new HSSFRichTextString(po.getUnitLevel1()));
		        c35.setCellStyle(style5);
		        HSSFCell c36 = row3.createCell(5);   
		        c36.setCellValue(new HSSFRichTextString(po.getUnitLevel2()));
		        c36.setCellStyle(style5);
		        HSSFCell c37 = row3.createCell(6);   
		        c37.setCellValue(new HSSFRichTextString(po.getUnitLevel3()));
		        c37.setCellStyle(style5);
		        HSSFCell c38 = row3.createCell(7);   
		        c38.setCellValue(new HSSFRichTextString(po.getUnitLevel4())); 
		        c38.setCellStyle(style5);
		        HSSFCell c39 = row3.createCell(8);   
		        c39.setCellValue(new HSSFRichTextString(po.getUnitLevel5()));
		        c39.setCellStyle(style5);
	        }
	        }
	        
	        try  
	        {  
	            workbook.write(fout);  
	        }  
	        catch (Exception e)  
	        {  
	            e.printStackTrace();  
	        }  
	}


}
