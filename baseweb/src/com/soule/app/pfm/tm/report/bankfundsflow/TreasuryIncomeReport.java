package com.soule.app.pfm.tm.report.bankfundsflow;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

public class TreasuryIncomeReport {
	
	public static void Data2Excel(Map map1,ByteArrayOutputStream fout){
		
		List<TreasuryIncomePo> list =(List<TreasuryIncomePo> ) map1.get("treasuryIncomeList");
		Map map = new HashMap();
		String dataDate =(String) map1.get("dataDate");
		for(int index=0;index<list.size();index++){
			TreasuryIncomePo treasuryIncomePo = list.get(index);
			map.put(treasuryIncomePo.getSubCode()+"_amc", treasuryIncomePo.getAmtMonthC());
			map.put(treasuryIncomePo.getSubCode()+"_ayc", treasuryIncomePo.getAmtYearC());
			map.put(treasuryIncomePo.getSubCode()+"_agrc", treasuryIncomePo.getAnnualGrowthRateC());
			
			map.put(treasuryIncomePo.getSubCode()+"_aml", treasuryIncomePo.getAmtMonthL());
			map.put(treasuryIncomePo.getSubCode()+"_ayl", treasuryIncomePo.getAmtYearL());
			map.put(treasuryIncomePo.getSubCode()+"_agrl", treasuryIncomePo.getAnnualGrowthRateL());
			
			map.put(treasuryIncomePo.getSubCode()+"_amt", treasuryIncomePo.getAmtMonthT());
			map.put(treasuryIncomePo.getSubCode()+"_agrt", treasuryIncomePo.getAnnualGrowthRateT());
			
		}
		 HSSFWorkbook workbook = new HSSFWorkbook();   
	        //创建sheet页  
	        HSSFSheet sheet = workbook.createSheet("大连市国库收支统计表");  
	        
	        sheet.setColumnWidth(0, 35*256);
	        sheet.setColumnWidth(1, 15*256);
	        sheet.setColumnWidth(2, 15*256);
	        sheet.setColumnWidth(3, 15*256);
	        sheet.setColumnWidth(4, 15*256);
	        sheet.setColumnWidth(5, 20*256);
	        sheet.setColumnWidth(6, 20*256);
	        sheet.setColumnWidth(7, 20*256);
	        sheet.setColumnWidth(8, 20*256);
	        
	        CellStyle style = workbook.createCellStyle();  
	        CellStyle style1 = workbook.createCellStyle();//第一行表格样式
	        CellStyle style2 = workbook.createCellStyle();//第二行表格样式
	        CellStyle style3 = workbook.createCellStyle();//第三行表格样式
	        CellStyle style4 = workbook.createCellStyle();//合并单元格表格样式
	        CellStyle style5 = workbook.createCellStyle();//合并单元格表格样式
	        
	        
	        
    
	        //设置表头字体
	        
	        HSSFFont font = workbook.createFont();    
	        font.setFontName("黑体");    
	        font.setFontHeightInPoints((short) 16);//设置字体大小    
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
	        style5.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
	        style5.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        //创建单元格  
	        HSSFRow row1 = sheet.createRow(1);   
	        HSSFCell c10 = row1.createCell(0);   
	        c10.setCellValue(new HSSFRichTextString("大连市国库收支统计表"));
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
	        HSSFCell c16 = row1.createCell(6);   
	        c16.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c17 = row1.createCell(7);   
	        c17.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c18 = row1.createCell(8);   
	        c18.setCellValue(new HSSFRichTextString("")); 
	        
	        HSSFRow row2 = sheet.createRow(2);   
	        HSSFCell c20 = row2.createCell(0);   
	        c20.setCellValue(new HSSFRichTextString("业务期间："+dataDate));
	        HSSFCell c21 = row2.createCell(1);   
	        c21.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c22 = row2.createCell(2);   
	        c22.setCellValue(new HSSFRichTextString("单位：亿元")); 
	        HSSFCell c23 = row2.createCell(3);   
	        c23.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c24 = row2.createCell(4);   
	        c24.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c25 = row2.createCell(5);   
	        c25.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c26 = row2.createCell(6);   
	        c26.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c27 = row2.createCell(7);   
	        c27.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c28 = row2.createCell(8);   
	        c28.setCellValue(new HSSFRichTextString("")); 
	        
	        HSSFRow row3 = sheet.createRow(3);   
	        HSSFCell c30 = row3.createCell(0);   
	        c30.setCellValue(new HSSFRichTextString("项目"));
	        HSSFCell c31 = row3.createCell(1);   
	        c31.setCellValue(new HSSFRichTextString("中央级")); 
	        HSSFCell c32 = row3.createCell(2);   
	        c32.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c33 = row3.createCell(3);   
	        c33.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c34 = row3.createCell(4);   
	        c34.setCellValue(new HSSFRichTextString("地方级")); 
	        HSSFCell c35 = row3.createCell(5);   
	        c35.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c36 = row3.createCell(6);   
	        c36.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c37 = row3.createCell(7);   
	        c37.setCellValue(new HSSFRichTextString("合计数")); 
	        HSSFCell c38 = row3.createCell(8);   
	        c38.setCellValue(new HSSFRichTextString("")); 
	        
	        
	        HSSFRow row4 = sheet.createRow(4);   
	        HSSFCell c40 = row4.createCell(0);   
	        c40.setCellValue(new HSSFRichTextString(""));
	        HSSFCell c41 = row4.createCell(1);   
	        c41.setCellValue(new HSSFRichTextString("发生金额")); 
	        HSSFCell c42 = row4.createCell(2);   
	        c42.setCellValue(new HSSFRichTextString("累计金额")); 
	        HSSFCell c43 = row4.createCell(3);   
	        c43.setCellValue(new HSSFRichTextString("增长(%)")); 
	        HSSFCell c44 = row4.createCell(4);   
	        c44.setCellValue(new HSSFRichTextString("发生金额")); 
	        HSSFCell c45 = row4.createCell(5);   
	        c45.setCellValue(new HSSFRichTextString("累计金额")); 
	        HSSFCell c46 = row4.createCell(6);   
	        c46.setCellValue(new HSSFRichTextString("增长(%)")); 
	        HSSFCell c47 = row4.createCell(7);   
	        c47.setCellValue(new HSSFRichTextString("累计金额")); 
	        HSSFCell c48 = row4.createCell(8);   
	        c48.setCellValue(new HSSFRichTextString("增长(%)")); 
	        
	        
	        
	        HSSFRow row5 = sheet.createRow(5);   
	        HSSFCell c50 = row5.createCell(0);   
	        c50.setCellValue(new HSSFRichTextString("一、国库收入"));
	        HSSFCell c51 = row5.createCell(1);   
	        c51.setCellValue(new HSSFRichTextString(null==map.get("T01_amc")?"0":map.get("T01_amc").toString())); 
	        HSSFCell c52 = row5.createCell(2);   
	        c52.setCellValue(new HSSFRichTextString(null==map.get("T01_ayc")?"0":map.get("T01_ayc").toString())); 
	        HSSFCell c53 = row5.createCell(3);   
	        c53.setCellValue(new HSSFRichTextString(null==map.get("T01_agrc")?"0":map.get("T01_agrc").toString())); 
	        HSSFCell c54 = row5.createCell(4);   
	        c54.setCellValue(new HSSFRichTextString(null==map.get("T01_aml")?"0":map.get("T01_aml").toString())); 
	        HSSFCell c55 = row5.createCell(5);   
	        c55.setCellValue(new HSSFRichTextString(null==map.get("T01_ayl")?"0":map.get("T01_ayl").toString())); 
	        HSSFCell c56 = row5.createCell(6);   
	        c56.setCellValue(new HSSFRichTextString(null==map.get("T01_agrl")?"0":map.get("T01_agrl").toString())); 
	        HSSFCell c57 = row5.createCell(7);   
	        c57.setCellValue(new HSSFRichTextString(null==map.get("T01_amt")?"0":map.get("T01_amt").toString())); 
	        HSSFCell c58 = row5.createCell(8);   
	        c58.setCellValue(new HSSFRichTextString(null==map.get("T01_agrt")?"0":map.get("T01_agrt").toString())); 
	        
	        
	        
	        HSSFRow row6 = sheet.createRow(6);   
	        HSSFCell c60 = row6.createCell(0);   
	        c60.setCellValue(new HSSFRichTextString("1.公共预算收入"));
	        HSSFCell c61 = row6.createCell(1);   
	        c61.setCellValue(new HSSFRichTextString(null==map.get("T010101_amc")?"0":map.get("T010101_amc").toString())); 
	        HSSFCell c62 = row6.createCell(2);   
	        c62.setCellValue(new HSSFRichTextString(null==map.get("T010101_ayc")?"0":map.get("T010101_ayc").toString())); 
	        HSSFCell c63 = row6.createCell(3);   
	        c63.setCellValue(new HSSFRichTextString(null==map.get("T010101_agrc")?"0":map.get("T010101_agrc").toString())); 
	        HSSFCell c64 = row6.createCell(4);   
	        c64.setCellValue(new HSSFRichTextString(null==map.get("T010101_aml")?"0":map.get("T010101_aml").toString())); 
	        HSSFCell c65 = row6.createCell(5);   
	        c65.setCellValue(new HSSFRichTextString(null==map.get("T010101_ayl")?"0":map.get("T010101_ayl").toString())); 
	        HSSFCell c66 = row6.createCell(6);   
	        c66.setCellValue(new HSSFRichTextString(null==map.get("T010101_agrl")?"0":map.get("T010101_agrl").toString())); 
	        HSSFCell c67 = row6.createCell(7);   
	        c67.setCellValue(new HSSFRichTextString(null==map.get("T010101_amt")?"0":map.get("T010101_amt").toString())); 
	        HSSFCell c68 = row6.createCell(8);   
	        c68.setCellValue(new HSSFRichTextString(null==map.get("T010101_agrt")?"0":map.get("T010101_agrt").toString())); 
	        
	        
	        HSSFRow row7 = sheet.createRow(7);   
	        HSSFCell c70 = row7.createCell(0);   
	        c70.setCellValue(new HSSFRichTextString("2.公共预算调拨收入"));
	        HSSFCell c71 = row7.createCell(1);   
	        c71.setCellValue(new HSSFRichTextString(null==map.get("T010102_amc")?"0":map.get("T010102_amc").toString())); 
	        HSSFCell c72 = row7.createCell(2);   
	        c72.setCellValue(new HSSFRichTextString(null==map.get("T010102_ayc")?"0":map.get("T010102_ayc").toString())); 
	        HSSFCell c73 = row7.createCell(3);   
	        c73.setCellValue(new HSSFRichTextString(null==map.get("T010102_agrc")?"0":map.get("T010102_agrc").toString())); 
	        HSSFCell c74 = row7.createCell(4);   
	        c74.setCellValue(new HSSFRichTextString(null==map.get("T010102_aml")?"0":map.get("T010102_aml").toString())); 
	        HSSFCell c75 = row7.createCell(5);   
	        c75.setCellValue(new HSSFRichTextString(null==map.get("T010102_ayl")?"0":map.get("T010102_ayl").toString())); 
	        HSSFCell c76 = row7.createCell(6);   
	        c76.setCellValue(new HSSFRichTextString(null==map.get("T010102_agrl")?"0":map.get("T010102_agrl").toString())); 
	        HSSFCell c77 = row7.createCell(7);   
	        c77.setCellValue(new HSSFRichTextString(null==map.get("T010102_amt")?"0":map.get("T010102_amt").toString())); 
	        HSSFCell c78 = row7.createCell(8);   
	        c78.setCellValue(new HSSFRichTextString(null==map.get("T010102_agrt")?"0":map.get("T010102_agrt").toString())); 
	        
	        
	        HSSFRow row8 = sheet.createRow(8);   
	        HSSFCell c80 = row8.createCell(0);   
	        c80.setCellValue(new HSSFRichTextString("其中：转移性收入"));
	        HSSFCell c81 = row8.createCell(1);   
	        c81.setCellValue(new HSSFRichTextString(null==map.get("110_amc")?"0":map.get("110_amc").toString())); 
	        HSSFCell c82 = row8.createCell(2);   
	        c82.setCellValue(new HSSFRichTextString(null==map.get("110_ayc")?"0":map.get("110_ayc").toString())); 
	        HSSFCell c83 = row8.createCell(3);   
	        c83.setCellValue(new HSSFRichTextString(null==map.get("110_agrc")?"0":map.get("110_agrc").toString())); 
	        HSSFCell c84 = row8.createCell(4);   
	        c84.setCellValue(new HSSFRichTextString(null==map.get("110_aml")?"0":map.get("110_aml").toString())); 
	        HSSFCell c85 = row8.createCell(5);   
	        c85.setCellValue(new HSSFRichTextString(null==map.get("110_ayl")?"0":map.get("110_ayl").toString())); 
	        HSSFCell c86 = row8.createCell(6);   
	        c86.setCellValue(new HSSFRichTextString(null==map.get("110_agrl")?"0":map.get("110_agrl").toString())); 
	        HSSFCell c87 = row8.createCell(7);   
	        c87.setCellValue(new HSSFRichTextString(null==map.get("110_amt")?"0":map.get("110_amt").toString())); 
	        HSSFCell c88 = row8.createCell(8);   
	        c88.setCellValue(new HSSFRichTextString(null==map.get("110_agrt")?"0":map.get("110_agrt").toString())); 
	        
	        HSSFRow row9 = sheet.createRow(9);   
	        HSSFCell c90 = row9.createCell(0);   
	        c90.setCellValue(new HSSFRichTextString("3.基金预算收入"));
	        HSSFCell c91 = row9.createCell(1);   
	        c91.setCellValue(new HSSFRichTextString(null==map.get("T0102_amc")?"0":map.get("T0102_amc").toString())); 
	        HSSFCell c92 = row9.createCell(2);   
	        c92.setCellValue(new HSSFRichTextString(null==map.get("T0102_ayc")?"0":map.get("T0102_ayc").toString())); 
	        HSSFCell c93 = row9.createCell(3);   
	        c93.setCellValue(new HSSFRichTextString(null==map.get("T0102_agrc")?"0":map.get("T0102_agrc").toString())); 
	        HSSFCell c94 = row9.createCell(4);   
	        c94.setCellValue(new HSSFRichTextString(null==map.get("T0102_aml")?"0":map.get("T0102_aml").toString())); 
	        HSSFCell c95 = row9.createCell(5);   
	        c95.setCellValue(new HSSFRichTextString(null==map.get("T0102_ayl")?"0":map.get("T0102_ayl").toString())); 
	        HSSFCell c96 = row9.createCell(6);   
	        c96.setCellValue(new HSSFRichTextString(null==map.get("T0102_agrl")?"0":map.get("T0102_agrl").toString())); 
	        HSSFCell c97 = row9.createCell(7);   
	        c97.setCellValue(new HSSFRichTextString(null==map.get("T0102_amt")?"0":map.get("T0102_amt").toString())); 
	        HSSFCell c98 = row9.createCell(8);   
	        c98.setCellValue(new HSSFRichTextString(null==map.get("T0102_agrt")?"0":map.get("T0102_agrt").toString())); 
	        
	        HSSFRow row10 = sheet.createRow(10);   
	        HSSFCell c100 = row10.createCell(0);   
	        c100.setCellValue(new HSSFRichTextString("4.债务预算收入"));
	        HSSFCell c101 = row10.createCell(1);   
	        c101.setCellValue(new HSSFRichTextString(null==map.get("T0103_amc")?"0":map.get("T0103_amc").toString())); 
	        HSSFCell c102 = row10.createCell(2);   
	        c102.setCellValue(new HSSFRichTextString(null==map.get("T0103_ayc")?"0":map.get("T0103_ayc").toString())); 
	        HSSFCell c103 = row10.createCell(3);   
	        c103.setCellValue(new HSSFRichTextString(null==map.get("T0103_agrc")?"0":map.get("T0103_agrc").toString())); 
	        HSSFCell c104 = row10.createCell(4);   
	        c104.setCellValue(new HSSFRichTextString(null==map.get("T0103_aml")?"0":map.get("T0103_aml").toString())); 
	        HSSFCell c105 = row10.createCell(5);   
	        c105.setCellValue(new HSSFRichTextString(null==map.get("T0103_ayl")?"0":map.get("T0103_ayl").toString())); 
	        HSSFCell c106 = row10.createCell(6);   
	        c106.setCellValue(new HSSFRichTextString(null==map.get("T0103_agrl")?"0":map.get("T0103_agrl").toString())); 
	        HSSFCell c107 = row10.createCell(7);   
	        c107.setCellValue(new HSSFRichTextString(null==map.get("T0103_amt")?"0":map.get("T0103_amt").toString())); 
	        HSSFCell c108 = row10.createCell(8);   
	        c108.setCellValue(new HSSFRichTextString(null==map.get("T0103_agrt")?"0":map.get("T0103_agrt").toString())); 
	        
	        HSSFRow row11 = sheet.createRow(11);   
	        HSSFCell c110 = row11.createCell(0);   
	        c110.setCellValue(new HSSFRichTextString("5.国资经营预算收入"));
	        HSSFCell c111 = row11.createCell(1);   
	        c111.setCellValue(new HSSFRichTextString(null==map.get("T0106_amc")?"0":map.get("T0106_amc").toString())); 
	        HSSFCell c112 = row11.createCell(2);   
	        c112.setCellValue(new HSSFRichTextString(null==map.get("T0106_ayc")?"0":map.get("T0106_ayc").toString())); 
	        HSSFCell c113 = row11.createCell(3);   
	        c113.setCellValue(new HSSFRichTextString(null==map.get("T0106_agrc")?"0":map.get("T0106_agrc").toString())); 
	        HSSFCell c114 = row11.createCell(4);   
	        c114.setCellValue(new HSSFRichTextString(null==map.get("T0106_aml")?"0":map.get("T0106_aml").toString())); 
	        HSSFCell c115 = row11.createCell(5);   
	        c115.setCellValue(new HSSFRichTextString(null==map.get("T0106_ayl")?"0":map.get("T0106_ayl").toString())); 
	        HSSFCell c116 = row11.createCell(6);   
	        c116.setCellValue(new HSSFRichTextString(null==map.get("T0106_agrl")?"0":map.get("T0106_agrl").toString())); 
	        HSSFCell c117 = row11.createCell(7);   
	        c117.setCellValue(new HSSFRichTextString(null==map.get("T0106_amt")?"0":map.get("T0106_amt").toString())); 
	        HSSFCell c118 = row11.createCell(8);   
	        c118.setCellValue(new HSSFRichTextString(null==map.get("T0106_agrt")?"0":map.get("T0106_agrt").toString())); 
	        
	        HSSFRow row12 = sheet.createRow(12);   
	        HSSFCell c120 = row12.createCell(0);   
	        c120.setCellValue(new HSSFRichTextString("二、税收收入"));
	        HSSFCell c121 = row12.createCell(1);   
	        c121.setCellValue(new HSSFRichTextString(null==map.get("101_amc")?"0":map.get("101_amc").toString())); 
	        HSSFCell c122 = row12.createCell(2);   
	        c122.setCellValue(new HSSFRichTextString(null==map.get("101_ayc")?"0":map.get("101_ayc").toString())); 
	        HSSFCell c123 = row12.createCell(3);   
	        c123.setCellValue(new HSSFRichTextString(null==map.get("101_agrc")?"0":map.get("101_agrc").toString())); 
	        HSSFCell c124 = row12.createCell(4);   
	        c124.setCellValue(new HSSFRichTextString(null==map.get("101_aml")?"0":map.get("101_aml").toString())); 
	        HSSFCell c125 = row12.createCell(5);   
	        c125.setCellValue(new HSSFRichTextString(null==map.get("101_ayl")?"0":map.get("101_ayl").toString())); 
	        HSSFCell c126 = row12.createCell(6);   
	        c126.setCellValue(new HSSFRichTextString(null==map.get("101_agrl")?"0":map.get("101_agrl").toString())); 
	        HSSFCell c127 = row12.createCell(7);   
	        c127.setCellValue(new HSSFRichTextString(null==map.get("101_amt")?"0":map.get("101_amt").toString())); 
	        HSSFCell c128 = row12.createCell(8);   
	        c128.setCellValue(new HSSFRichTextString(null==map.get("101_agrt")?"0":map.get("101_agrt").toString())); 
	        
	        HSSFRow row13 = sheet.createRow(13);   
	        HSSFCell c130 = row13.createCell(0);   
	        c130.setCellValue(new HSSFRichTextString("1.增值税"));
	        HSSFCell c131 = row13.createCell(1);   
	        c131.setCellValue(new HSSFRichTextString(null==map.get("10101_amc")?"0":map.get("10101_amc").toString())); 
	        HSSFCell c132 = row13.createCell(2);   
	        c132.setCellValue(new HSSFRichTextString(null==map.get("10101_ayc")?"0":map.get("10101_ayc").toString())); 
	        HSSFCell c133 = row13.createCell(3);   
	        c133.setCellValue(new HSSFRichTextString(null==map.get("10101_agrc")?"0":map.get("10101_agrc").toString())); 
	        HSSFCell c134 = row13.createCell(4);   
	        c134.setCellValue(new HSSFRichTextString(null==map.get("10101_aml")?"0":map.get("10101_aml").toString())); 
	        HSSFCell c135 = row13.createCell(5);   
	        c135.setCellValue(new HSSFRichTextString(null==map.get("10101_ayl")?"0":map.get("10101_ayl").toString())); 
	        HSSFCell c136 = row13.createCell(6);   
	        c136.setCellValue(new HSSFRichTextString(null==map.get("10101_agrl")?"0":map.get("10101_agrl").toString())); 
	        HSSFCell c137 = row13.createCell(7);   
	        c137.setCellValue(new HSSFRichTextString(null==map.get("10101_amt")?"0":map.get("10101_amt").toString())); 
	        HSSFCell c138 = row13.createCell(8);   
	        c138.setCellValue(new HSSFRichTextString(null==map.get("10101_agrt")?"0":map.get("10101_agrt").toString())); 
	        
	        
	        HSSFRow row14 = sheet.createRow(14);   
	        HSSFCell c140 = row14.createCell(0);   
	        c140.setCellValue(new HSSFRichTextString("2.消费税"));
	        HSSFCell c141 = row14.createCell(1);   
	        c141.setCellValue(new HSSFRichTextString(null==map.get("10102_amc")?"0":map.get("10102_amc").toString())); 
	        HSSFCell c142 = row14.createCell(2);   
	        c142.setCellValue(new HSSFRichTextString(null==map.get("10102_ayc")?"0":map.get("10102_ayc").toString())); 
	        HSSFCell c143 = row14.createCell(3);   
	        c143.setCellValue(new HSSFRichTextString(null==map.get("10102_agrc")?"0":map.get("10102_agrc").toString())); 
	        HSSFCell c144 = row14.createCell(4);   
	        c144.setCellValue(new HSSFRichTextString(null==map.get("10102_aml")?"0":map.get("10102_aml").toString())); 
	        HSSFCell c145 = row14.createCell(5);   
	        c145.setCellValue(new HSSFRichTextString(null==map.get("10102_ayl")?"0":map.get("10102_ayl").toString())); 
	        HSSFCell c146 = row14.createCell(6);   
	        c146.setCellValue(new HSSFRichTextString(null==map.get("10102_agrl")?"0":map.get("10102_agrl").toString())); 
	        HSSFCell c147 = row14.createCell(7);   
	        c147.setCellValue(new HSSFRichTextString(null==map.get("10102_amt")?"0":map.get("10102_amt").toString())); 
	        HSSFCell c148 = row14.createCell(8);   
	        c148.setCellValue(new HSSFRichTextString(null==map.get("10102_agrt")?"0":map.get("10102_agrt").toString())); 
	        
	        
	        
	        HSSFRow row15 = sheet.createRow(15);   
	        HSSFCell c150 = row15.createCell(0);   
	        c150.setCellValue(new HSSFRichTextString("3.营业税"));
	        HSSFCell c151 = row15.createCell(1);   
	        c151.setCellValue(new HSSFRichTextString(null==map.get("10103_amc")?"0":map.get("10103_amc").toString())); 
	        HSSFCell c152 = row15.createCell(2);   
	        c152.setCellValue(new HSSFRichTextString(null==map.get("10103_ayc")?"0":map.get("10103_ayc").toString())); 
	        HSSFCell c153 = row15.createCell(3);   
	        c153.setCellValue(new HSSFRichTextString(null==map.get("10103_agrc")?"0":map.get("10103_agrc").toString())); 
	        HSSFCell c154 = row15.createCell(4);   
	        c154.setCellValue(new HSSFRichTextString(null==map.get("10103_aml")?"0":map.get("10103_aml").toString())); 
	        HSSFCell c155 = row15.createCell(5);   
	        c155.setCellValue(new HSSFRichTextString(null==map.get("10103_ayl")?"0":map.get("10103_ayl").toString())); 
	        HSSFCell c156 = row15.createCell(6);   
	        c156.setCellValue(new HSSFRichTextString(null==map.get("10103_agrl")?"0":map.get("10103_agrl").toString())); 
	        HSSFCell c157 = row15.createCell(7);   
	        c157.setCellValue(new HSSFRichTextString(null==map.get("10103_amt")?"0":map.get("10103_amt").toString())); 
	        HSSFCell c158 = row15.createCell(8);   
	        c158.setCellValue(new HSSFRichTextString(null==map.get("10103_agrt")?"0":map.get("10103_agrt").toString())); 
	        
	        
	        
	        HSSFRow row16 = sheet.createRow(16);   
	        HSSFCell c160 = row16.createCell(0);   
	        c160.setCellValue(new HSSFRichTextString("4.企业所得税"));
	        HSSFCell c161 = row16.createCell(1);   
	        c161.setCellValue(new HSSFRichTextString(null==map.get("10104_amc")?"0":map.get("10104_amc").toString())); 
	        HSSFCell c162 = row16.createCell(2);   
	        c162.setCellValue(new HSSFRichTextString(null==map.get("10104_ayc")?"0":map.get("10104_ayc").toString())); 
	        HSSFCell c163 = row16.createCell(3);   
	        c163.setCellValue(new HSSFRichTextString(null==map.get("10104_agrc")?"0":map.get("10104_agrc").toString())); 
	        HSSFCell c164 = row16.createCell(4);   
	        c164.setCellValue(new HSSFRichTextString(null==map.get("10104_aml")?"0":map.get("10104_aml").toString())); 
	        HSSFCell c165 = row16.createCell(5);   
	        c165.setCellValue(new HSSFRichTextString(null==map.get("10104_ayl")?"0":map.get("10104_ayl").toString())); 
	        HSSFCell c166 = row16.createCell(6);   
	        c166.setCellValue(new HSSFRichTextString(null==map.get("10104_agrl")?"0":map.get("10104_agrl").toString())); 
	        HSSFCell c167 = row16.createCell(7);   
	        c167.setCellValue(new HSSFRichTextString(null==map.get("10104_amt")?"0":map.get("10104_amt").toString())); 
	        HSSFCell c168 = row16.createCell(8);   
	        c168.setCellValue(new HSSFRichTextString(null==map.get("10104_agrt")?"0":map.get("10104_agrt").toString())); 
	        
	        
	        HSSFRow row17 = sheet.createRow(17);   
	        HSSFCell c170 = row17.createCell(0);   
	        c170.setCellValue(new HSSFRichTextString("5.个人所得税"));
	        HSSFCell c171 = row17.createCell(1);   
	        c171.setCellValue(new HSSFRichTextString(null==map.get("10106_amc")?"0":map.get("10106_amc").toString())); 
	        HSSFCell c172 = row17.createCell(2);   
	        c172.setCellValue(new HSSFRichTextString(null==map.get("10106_ayc")?"0":map.get("10106_ayc").toString())); 
	        HSSFCell c173 = row17.createCell(3);   
	        c173.setCellValue(new HSSFRichTextString(null==map.get("10106_agrc")?"0":map.get("10106_agrc").toString())); 
	        HSSFCell c174 = row17.createCell(4);   
	        c174.setCellValue(new HSSFRichTextString(null==map.get("10106_aml")?"0":map.get("10106_aml").toString())); 
	        HSSFCell c175 = row17.createCell(5);   
	        c175.setCellValue(new HSSFRichTextString(null==map.get("10106_ayl")?"0":map.get("10106_ayl").toString())); 
	        HSSFCell c176 = row17.createCell(6);   
	        c176.setCellValue(new HSSFRichTextString(null==map.get("10106_agrl")?"0":map.get("10106_agrl").toString())); 
	        HSSFCell c177 = row17.createCell(7);   
	        c177.setCellValue(new HSSFRichTextString(null==map.get("10106_amt")?"0":map.get("10106_amt").toString())); 
	        HSSFCell c178 = row17.createCell(8);   
	        c178.setCellValue(new HSSFRichTextString(null==map.get("10106_agrt")?"0":map.get("10106_agrt").toString())); 
	        
	        
	        HSSFRow row18 = sheet.createRow(18);   
	        HSSFCell c180 = row18.createCell(0);   
	        c180.setCellValue(new HSSFRichTextString("6.资源税"));
	        HSSFCell c181 = row18.createCell(1);   
	        c181.setCellValue(new HSSFRichTextString(null==map.get("10107_amc")?"0":map.get("10107_amc").toString())); 
	        HSSFCell c182 = row18.createCell(2);   
	        c182.setCellValue(new HSSFRichTextString(null==map.get("10107_ayc")?"0":map.get("10107_ayc").toString())); 
	        HSSFCell c183 = row18.createCell(3);   
	        c183.setCellValue(new HSSFRichTextString(null==map.get("10107_agrc")?"0":map.get("10107_agrc").toString())); 
	        HSSFCell c184 = row18.createCell(4);   
	        c184.setCellValue(new HSSFRichTextString(null==map.get("10107_aml")?"0":map.get("10107_aml").toString())); 
	        HSSFCell c185 = row18.createCell(5);   
	        c185.setCellValue(new HSSFRichTextString(null==map.get("10107_ayl")?"0":map.get("10107_ayl").toString())); 
	        HSSFCell c186 = row18.createCell(6);   
	        c186.setCellValue(new HSSFRichTextString(null==map.get("10107_agrl")?"0":map.get("10107_agrl").toString())); 
	        HSSFCell c187 = row18.createCell(7);   
	        c187.setCellValue(new HSSFRichTextString(null==map.get("10107_amt")?"0":map.get("10107_amt").toString())); 
	        HSSFCell c188 = row18.createCell(8);   
	        c188.setCellValue(new HSSFRichTextString(null==map.get("10107_agrt")?"0":map.get("10107_agrt").toString())); 
	        
	        HSSFRow row19 = sheet.createRow(19);   
	        HSSFCell c190 = row19.createCell(0);   
	        c190.setCellValue(new HSSFRichTextString("7.城市维护建设税"));
	        HSSFCell c191 = row19.createCell(1);   
	        c191.setCellValue(new HSSFRichTextString(null==map.get("10109_amc")?"0":map.get("10109_amc").toString())); 
	        HSSFCell c192 = row19.createCell(2);   
	        c192.setCellValue(new HSSFRichTextString(null==map.get("10109_ayc")?"0":map.get("10109_ayc").toString())); 
	        HSSFCell c193 = row19.createCell(3);   
	        c193.setCellValue(new HSSFRichTextString(null==map.get("10109_agrc")?"0":map.get("10109_agrc").toString())); 
	        HSSFCell c194 = row19.createCell(4);   
	        c194.setCellValue(new HSSFRichTextString(null==map.get("10109_aml")?"0":map.get("10109_aml").toString())); 
	        HSSFCell c195 = row19.createCell(5);   
	        c195.setCellValue(new HSSFRichTextString(null==map.get("10109_ayl")?"0":map.get("10109_ayl").toString())); 
	        HSSFCell c196 = row19.createCell(6);   
	        c196.setCellValue(new HSSFRichTextString(null==map.get("10109_agrl")?"0":map.get("10109_agrl").toString())); 
	        HSSFCell c197 = row19.createCell(7);   
	        c197.setCellValue(new HSSFRichTextString(null==map.get("10109_amt")?"0":map.get("10109_amt").toString())); 
	        HSSFCell c198 = row19.createCell(8);   
	        c198.setCellValue(new HSSFRichTextString(null==map.get("10109_agrt")?"0":map.get("10109_agrt").toString())); 
	        
	        HSSFRow row20 = sheet.createRow(20);   
	        HSSFCell c200 = row20.createCell(0);   
	        c200.setCellValue(new HSSFRichTextString("8.房产税"));
	        HSSFCell c201 = row20.createCell(1);   
	        c201.setCellValue(new HSSFRichTextString(null==map.get("10110_amc")?"0":map.get("10110_amc").toString())); 
	        HSSFCell c202 = row20.createCell(2);   
	        c202.setCellValue(new HSSFRichTextString(null==map.get("10110_ayc")?"0":map.get("10110_ayc").toString())); 
	        HSSFCell c203 = row20.createCell(3);   
	        c203.setCellValue(new HSSFRichTextString(null==map.get("10110_agrc")?"0":map.get("10110_agrc").toString())); 
	        HSSFCell c204 = row20.createCell(4);   
	        c204.setCellValue(new HSSFRichTextString(null==map.get("10110_aml")?"0":map.get("10110_aml").toString())); 
	        HSSFCell c205 = row20.createCell(5);   
	        c205.setCellValue(new HSSFRichTextString(null==map.get("10110_ayl")?"0":map.get("10110_ayl").toString())); 
	        HSSFCell c206 = row20.createCell(6);   
	        c206.setCellValue(new HSSFRichTextString(null==map.get("10110_agrl")?"0":map.get("10110_agrl").toString())); 
	        HSSFCell c207 = row20.createCell(7);   
	        c207.setCellValue(new HSSFRichTextString(null==map.get("10110_amt")?"0":map.get("10110_amt").toString())); 
	        HSSFCell c208 = row20.createCell(8);   
	        c208.setCellValue(new HSSFRichTextString(null==map.get("10110_agrt")?"0":map.get("10110_agrt").toString())); 
	        
	        HSSFRow row21 = sheet.createRow(21);   
	        HSSFCell c210 = row21.createCell(0);   
	        c210.setCellValue(new HSSFRichTextString("9.印花税"));
	        HSSFCell c211 = row21.createCell(1);   
	        c211.setCellValue(new HSSFRichTextString(null==map.get("10111_amc")?"0":map.get("10111_amc").toString())); 
	        HSSFCell c212 = row21.createCell(2);   
	        c212.setCellValue(new HSSFRichTextString(null==map.get("10111_ayc")?"0":map.get("10111_ayc").toString())); 
	        HSSFCell c213 = row21.createCell(3);   
	        c213.setCellValue(new HSSFRichTextString(null==map.get("10111_agrc")?"0":map.get("10111_agrc").toString())); 
	        HSSFCell c214 = row21.createCell(4);   
	        c214.setCellValue(new HSSFRichTextString(null==map.get("10111_aml")?"0":map.get("10111_aml").toString())); 
	        HSSFCell c215 = row21.createCell(5);   
	        c215.setCellValue(new HSSFRichTextString(null==map.get("10111_ayl")?"0":map.get("10111_ayl").toString())); 
	        HSSFCell c216 = row21.createCell(6);   
	        c216.setCellValue(new HSSFRichTextString(null==map.get("10111_agrl")?"0":map.get("10111_agrl").toString())); 
	        HSSFCell c217 = row21.createCell(7);   
	        c217.setCellValue(new HSSFRichTextString(null==map.get("10111_amt")?"0":map.get("10111_amt").toString())); 
	        HSSFCell c218 = row21.createCell(8);   
	        c218.setCellValue(new HSSFRichTextString(null==map.get("10111_agrt")?"0":map.get("10111_agrt").toString())); 
	        
	        HSSFRow row22 = sheet.createRow(22);   
	        HSSFCell c220 = row22.createCell(0);   
	        c220.setCellValue(new HSSFRichTextString("10.城镇土地使用税"));
	        HSSFCell c221 = row22.createCell(1);   
	        c221.setCellValue(new HSSFRichTextString(null==map.get("10112_amc")?"0":map.get("10112_amc").toString())); 
	        HSSFCell c222 = row22.createCell(2);   
	        c222.setCellValue(new HSSFRichTextString(null==map.get("10112_ayc")?"0":map.get("10112_ayc").toString())); 
	        HSSFCell c223 = row22.createCell(3);   
	        c223.setCellValue(new HSSFRichTextString(null==map.get("10112_agrc")?"0":map.get("10112_agrc").toString())); 
	        HSSFCell c224 = row22.createCell(4);   
	        c224.setCellValue(new HSSFRichTextString(null==map.get("10112_aml")?"0":map.get("10112_aml").toString())); 
	        HSSFCell c225 = row22.createCell(5);   
	        c225.setCellValue(new HSSFRichTextString(null==map.get("10112_ayl")?"0":map.get("10112_ayl").toString())); 
	        HSSFCell c226 = row22.createCell(6);   
	        c226.setCellValue(new HSSFRichTextString(null==map.get("10112_agrl")?"0":map.get("10112_agrl").toString())); 
	        HSSFCell c227 = row22.createCell(7);   
	        c227.setCellValue(new HSSFRichTextString(null==map.get("10112_amt")?"0":map.get("10112_amt").toString())); 
	        HSSFCell c228 = row22.createCell(8);   
	        c228.setCellValue(new HSSFRichTextString(null==map.get("10112_agrt")?"0":map.get("10112_agrt").toString())); 
	        
	        HSSFRow row23 = sheet.createRow(23);   
	        HSSFCell c230 = row23.createCell(0);   
	        c230.setCellValue(new HSSFRichTextString("11.土地增值税"));
	        HSSFCell c231 = row23.createCell(1);   
	        c231.setCellValue(new HSSFRichTextString(null==map.get("10113_amc")?"0":map.get("10113_amc").toString())); 
	        HSSFCell c232 = row23.createCell(2);   
	        c232.setCellValue(new HSSFRichTextString(null==map.get("10113_ayc")?"0":map.get("10113_ayc").toString())); 
	        HSSFCell c233 = row23.createCell(3);   
	        c233.setCellValue(new HSSFRichTextString(null==map.get("10113_agrc")?"0":map.get("10113_agrc").toString())); 
	        HSSFCell c234 = row23.createCell(4);   
	        c234.setCellValue(new HSSFRichTextString(null==map.get("10113_aml")?"0":map.get("10113_aml").toString())); 
	        HSSFCell c235 = row23.createCell(5);   
	        c235.setCellValue(new HSSFRichTextString(null==map.get("10113_ayl")?"0":map.get("10113_ayl").toString())); 
	        HSSFCell c236 = row23.createCell(6);   
	        c236.setCellValue(new HSSFRichTextString(null==map.get("10113_agrl")?"0":map.get("10113_agrl").toString())); 
	        HSSFCell c237 = row23.createCell(7);   
	        c237.setCellValue(new HSSFRichTextString(null==map.get("10113_amt")?"0":map.get("10113_amt").toString())); 
	        HSSFCell c238 = row23.createCell(8);   
	        c238.setCellValue(new HSSFRichTextString(null==map.get("10113_agrt")?"0":map.get("10113_agrt").toString())); 
	        
	        
	        HSSFRow row24 = sheet.createRow(24);   
	        HSSFCell c240 = row24.createCell(0);   
	        c240.setCellValue(new HSSFRichTextString("12.车船税"));
	        HSSFCell c241 = row24.createCell(1);   
	        c241.setCellValue(new HSSFRichTextString(null==map.get("10114_amc")?"0":map.get("10114_amc").toString())); 
	        HSSFCell c242 = row24.createCell(2);   
	        c242.setCellValue(new HSSFRichTextString(null==map.get("10114_ayc")?"0":map.get("10114_ayc").toString())); 
	        HSSFCell c243 = row24.createCell(3);   
	        c243.setCellValue(new HSSFRichTextString(null==map.get("10114_agrc")?"0":map.get("10114_agrc").toString())); 
	        HSSFCell c244 = row24.createCell(4);   
	        c244.setCellValue(new HSSFRichTextString(null==map.get("10114_aml")?"0":map.get("10114_aml").toString())); 
	        HSSFCell c245 = row24.createCell(5);   
	        c245.setCellValue(new HSSFRichTextString(null==map.get("10114_ayl")?"0":map.get("10114_ayl").toString())); 
	        HSSFCell c246 = row24.createCell(6);   
	        c246.setCellValue(new HSSFRichTextString(null==map.get("10114_agrl")?"0":map.get("10114_agrl").toString())); 
	        HSSFCell c247 = row24.createCell(7);   
	        c247.setCellValue(new HSSFRichTextString(null==map.get("10114_amt")?"0":map.get("10114_amt").toString())); 
	        HSSFCell c248 = row24.createCell(8);   
	        c248.setCellValue(new HSSFRichTextString(null==map.get("10114_agrt")?"0":map.get("10114_agrt").toString())); 
	        
	        
	        
	        HSSFRow row25 = sheet.createRow(25);   
	        HSSFCell c250 = row25.createCell(0);   
	        c250.setCellValue(new HSSFRichTextString("13.船舶吨税"));
	        HSSFCell c251 = row25.createCell(1);   
	        c251.setCellValue(new HSSFRichTextString(null==map.get("10115_amc")?"0":map.get("10115_amc").toString())); 
	        HSSFCell c252 = row25.createCell(2);   
	        c252.setCellValue(new HSSFRichTextString(null==map.get("10115_ayc")?"0":map.get("10115_ayc").toString())); 
	        HSSFCell c253 = row25.createCell(3);   
	        c253.setCellValue(new HSSFRichTextString(null==map.get("10115_agrc")?"0":map.get("10115_agrc").toString())); 
	        HSSFCell c254 = row25.createCell(4);   
	        c254.setCellValue(new HSSFRichTextString(null==map.get("10115_aml")?"0":map.get("10115_aml").toString())); 
	        HSSFCell c255 = row25.createCell(5);   
	        c255.setCellValue(new HSSFRichTextString(null==map.get("10115_ayl")?"0":map.get("10115_ayl").toString())); 
	        HSSFCell c256 = row25.createCell(6);   
	        c256.setCellValue(new HSSFRichTextString(null==map.get("10115_agrl")?"0":map.get("10115_agrl").toString())); 
	        HSSFCell c257 = row25.createCell(7);   
	        c257.setCellValue(new HSSFRichTextString(null==map.get("10115_amt")?"0":map.get("10115_amt").toString())); 
	        HSSFCell c258 = row25.createCell(8);   
	        c258.setCellValue(new HSSFRichTextString(null==map.get("10115_agrt")?"0":map.get("10115_agrt").toString())); 
	        
	        
	        
	        HSSFRow row26 = sheet.createRow(26);   
	        HSSFCell c260 = row26.createCell(0);   
	        c260.setCellValue(new HSSFRichTextString("14.车辆购置税"));
	        HSSFCell c261 = row26.createCell(1);   
	        c261.setCellValue(new HSSFRichTextString(null==map.get("10116_amc")?"0":map.get("10116_amc").toString())); 
	        HSSFCell c262 = row26.createCell(2);   
	        c262.setCellValue(new HSSFRichTextString(null==map.get("10116_ayc")?"0":map.get("10116_ayc").toString())); 
	        HSSFCell c263 = row26.createCell(3);   
	        c263.setCellValue(new HSSFRichTextString(null==map.get("10116_agrc")?"0":map.get("10116_agrc").toString())); 
	        HSSFCell c264 = row26.createCell(4);   
	        c264.setCellValue(new HSSFRichTextString(null==map.get("10116_aml")?"0":map.get("10116_aml").toString())); 
	        HSSFCell c265 = row26.createCell(5);   
	        c265.setCellValue(new HSSFRichTextString(null==map.get("10116_ayl")?"0":map.get("10116_ayl").toString())); 
	        HSSFCell c266 = row26.createCell(6);   
	        c266.setCellValue(new HSSFRichTextString(null==map.get("10116_agrl")?"0":map.get("10116_agrl").toString())); 
	        HSSFCell c267 = row26.createCell(7);   
	        c267.setCellValue(new HSSFRichTextString(null==map.get("10116_amt")?"0":map.get("10116_amt").toString())); 
	        HSSFCell c268 = row26.createCell(8);   
	        c268.setCellValue(new HSSFRichTextString(null==map.get("10116_agrt")?"0":map.get("10116_agrt").toString())); 
	        
	        
	        HSSFRow row27 = sheet.createRow(27);   
	        HSSFCell c270 = row27.createCell(0);   
	        c270.setCellValue(new HSSFRichTextString("15.关税"));
	        HSSFCell c271 = row27.createCell(1);   
	        c271.setCellValue(new HSSFRichTextString(null==map.get("10117_amc")?"0":map.get("10117_amc").toString())); 
	        HSSFCell c272 = row27.createCell(2);   
	        c272.setCellValue(new HSSFRichTextString(null==map.get("10117_ayc")?"0":map.get("10117_ayc").toString())); 
	        HSSFCell c273 = row27.createCell(3);   
	        c273.setCellValue(new HSSFRichTextString(null==map.get("10117_agrc")?"0":map.get("10117_agrc").toString())); 
	        HSSFCell c274 = row27.createCell(4);   
	        c274.setCellValue(new HSSFRichTextString(null==map.get("10117_aml")?"0":map.get("10117_aml").toString())); 
	        HSSFCell c275 = row27.createCell(5);   
	        c275.setCellValue(new HSSFRichTextString(null==map.get("10117_ayl")?"0":map.get("10117_ayl").toString())); 
	        HSSFCell c276 = row27.createCell(6);   
	        c276.setCellValue(new HSSFRichTextString(null==map.get("10117_agrl")?"0":map.get("10117_agrl").toString())); 
	        HSSFCell c277 = row27.createCell(7);   
	        c277.setCellValue(new HSSFRichTextString(null==map.get("10117_amt")?"0":map.get("10117_amt").toString())); 
	        HSSFCell c278 = row27.createCell(8);   
	        c278.setCellValue(new HSSFRichTextString(null==map.get("10117_agrt")?"0":map.get("10117_agrt").toString())); 
	        
	        
	        HSSFRow row28 = sheet.createRow(28);   
	        HSSFCell c280 = row28.createCell(0);   
	        c280.setCellValue(new HSSFRichTextString("16.耕地占用税"));
	        HSSFCell c281 = row28.createCell(1);   
	        c281.setCellValue(new HSSFRichTextString(null==map.get("10118_amc")?"0":map.get("10118_amc").toString())); 
	        HSSFCell c282 = row28.createCell(2);   
	        c282.setCellValue(new HSSFRichTextString(null==map.get("10118_ayc")?"0":map.get("10118_ayc").toString())); 
	        HSSFCell c283 = row28.createCell(3);   
	        c283.setCellValue(new HSSFRichTextString(null==map.get("10118_agrc")?"0":map.get("10118_agrc").toString())); 
	        HSSFCell c284 = row28.createCell(4);   
	        c284.setCellValue(new HSSFRichTextString(null==map.get("10118_aml")?"0":map.get("10118_aml").toString())); 
	        HSSFCell c285 = row28.createCell(5);   
	        c285.setCellValue(new HSSFRichTextString(null==map.get("10118_ayl")?"0":map.get("10118_ayl").toString())); 
	        HSSFCell c286 = row28.createCell(6);   
	        c286.setCellValue(new HSSFRichTextString(null==map.get("10118_agrl")?"0":map.get("10118_agrl").toString())); 
	        HSSFCell c287 = row28.createCell(7);   
	        c287.setCellValue(new HSSFRichTextString(null==map.get("10118_amt")?"0":map.get("10118_amt").toString())); 
	        HSSFCell c288 = row28.createCell(8);   
	        c288.setCellValue(new HSSFRichTextString(null==map.get("10118_agrt")?"0":map.get("10118_agrt").toString())); 
	        
	        HSSFRow row29 = sheet.createRow(29);   
	        HSSFCell c290 = row29.createCell(0);   
	        c290.setCellValue(new HSSFRichTextString("17.契税"));
	        HSSFCell c291 = row29.createCell(1);   
	        c291.setCellValue(new HSSFRichTextString(null==map.get("10119_amc")?"0":map.get("10119_amc").toString())); 
	        HSSFCell c292 = row29.createCell(2);   
	        c292.setCellValue(new HSSFRichTextString(null==map.get("10119_ayc")?"0":map.get("10119_ayc").toString())); 
	        HSSFCell c293 = row29.createCell(3);   
	        c293.setCellValue(new HSSFRichTextString(null==map.get("10119_agrc")?"0":map.get("10119_agrc").toString())); 
	        HSSFCell c294 = row29.createCell(4);   
	        c294.setCellValue(new HSSFRichTextString(null==map.get("10119_aml")?"0":map.get("10119_aml").toString())); 
	        HSSFCell c295 = row29.createCell(5);   
	        c295.setCellValue(new HSSFRichTextString(null==map.get("10119_ayl")?"0":map.get("10119_ayl").toString())); 
	        HSSFCell c296 = row29.createCell(6);   
	        c296.setCellValue(new HSSFRichTextString(null==map.get("10119_agrl")?"0":map.get("10119_agrl").toString())); 
	        HSSFCell c297 = row29.createCell(7);   
	        c297.setCellValue(new HSSFRichTextString(null==map.get("10119_amt")?"0":map.get("10119_amt").toString())); 
	        HSSFCell c298 = row29.createCell(8);   
	        c298.setCellValue(new HSSFRichTextString(null==map.get("10119_agrt")?"0":map.get("10119_agrt").toString())); 
	        
	        
	        HSSFRow row30 = sheet.createRow(30);   
	        HSSFCell c300 = row30.createCell(0);   
	        c300.setCellValue(new HSSFRichTextString("三、非税收入"));
	        HSSFCell c301 = row30.createCell(1);   
	        c301.setCellValue(new HSSFRichTextString(null==map.get("103_amc")?"0":map.get("103_amc").toString())); 
	        HSSFCell c302 = row30.createCell(2);   
	        c302.setCellValue(new HSSFRichTextString(null==map.get("103_ayc")?"0":map.get("103_ayc").toString())); 
	        HSSFCell c303 = row30.createCell(3);   
	        c303.setCellValue(new HSSFRichTextString(null==map.get("103_agrc")?"0":map.get("103_agrc").toString())); 
	        HSSFCell c304 = row30.createCell(4);   
	        c304.setCellValue(new HSSFRichTextString(null==map.get("103_aml")?"0":map.get("103_aml").toString())); 
	        HSSFCell c305 = row30.createCell(5);   
	        c305.setCellValue(new HSSFRichTextString(null==map.get("103_ayl")?"0":map.get("103_ayl").toString())); 
	        HSSFCell c306 = row30.createCell(6);   
	        c306.setCellValue(new HSSFRichTextString(null==map.get("103_agrl")?"0":map.get("103_agrl").toString())); 
	        HSSFCell c307 = row30.createCell(7);   
	        c307.setCellValue(new HSSFRichTextString(null==map.get("103_amt")?"0":map.get("103_amt").toString())); 
	        HSSFCell c308 = row30.createCell(8);   
	        c308.setCellValue(new HSSFRichTextString(null==map.get("103_agrt")?"0":map.get("103_agrt").toString())); 
	        
	        HSSFRow row31 = sheet.createRow(31);   
	        HSSFCell c310 = row31.createCell(0);   
	        c310.setCellValue(new HSSFRichTextString("1.专项收入"));
	        HSSFCell c311 = row31.createCell(1);   
	        c311.setCellValue(new HSSFRichTextString(null==map.get("10302_amc")?"0":map.get("10302_amc").toString())); 
	        HSSFCell c312 = row31.createCell(2);   
	        c312.setCellValue(new HSSFRichTextString(null==map.get("10302_ayc")?"0":map.get("10302_ayc").toString())); 
	        HSSFCell c313 = row31.createCell(3);   
	        c313.setCellValue(new HSSFRichTextString(null==map.get("10302_agrc")?"0":map.get("10302_agrc").toString())); 
	        HSSFCell c314 = row31.createCell(4);   
	        c314.setCellValue(new HSSFRichTextString(null==map.get("10302_aml")?"0":map.get("10302_aml").toString())); 
	        HSSFCell c315 = row31.createCell(5);   
	        c315.setCellValue(new HSSFRichTextString(null==map.get("10302_ayl")?"0":map.get("10302_ayl").toString())); 
	        HSSFCell c316 = row31.createCell(6);   
	        c316.setCellValue(new HSSFRichTextString(null==map.get("10302_agrl")?"0":map.get("10302_agrl").toString())); 
	        HSSFCell c317 = row31.createCell(7);   
	        c317.setCellValue(new HSSFRichTextString(null==map.get("10302_amt")?"0":map.get("10302_amt").toString())); 
	        HSSFCell c318 = row31.createCell(8);   
	        c318.setCellValue(new HSSFRichTextString(null==map.get("10302_agrt")?"0":map.get("10302_agrt").toString())); 
	        
	        HSSFRow row32 = sheet.createRow(32);   
	        HSSFCell c320 = row32.createCell(0);   
	        c320.setCellValue(new HSSFRichTextString("2.行政事业收费收入"));
	        HSSFCell c321 = row32.createCell(1);   
	        c321.setCellValue(new HSSFRichTextString(null==map.get("10304_amc")?"0":map.get("10304_amc").toString())); 
	        HSSFCell c322 = row32.createCell(2);   
	        c322.setCellValue(new HSSFRichTextString(null==map.get("10304_ayc")?"0":map.get("10304_ayc").toString())); 
	        HSSFCell c323 = row32.createCell(3);   
	        c323.setCellValue(new HSSFRichTextString(null==map.get("10304_agrc")?"0":map.get("10304_agrc").toString())); 
	        HSSFCell c324 = row32.createCell(4);   
	        c324.setCellValue(new HSSFRichTextString(null==map.get("10304_aml")?"0":map.get("10304_aml").toString())); 
	        HSSFCell c325 = row32.createCell(5);   
	        c325.setCellValue(new HSSFRichTextString(null==map.get("10304_ayl")?"0":map.get("10304_ayl").toString())); 
	        HSSFCell c326 = row32.createCell(6);   
	        c326.setCellValue(new HSSFRichTextString(null==map.get("10304_agrl")?"0":map.get("10304_agrl").toString())); 
	        HSSFCell c327 = row32.createCell(7);   
	        c327.setCellValue(new HSSFRichTextString(null==map.get("10304_amt")?"0":map.get("10304_amt").toString())); 
	        HSSFCell c328 = row32.createCell(8);   
	        c328.setCellValue(new HSSFRichTextString(null==map.get("10304_agrt")?"0":map.get("10304_agrt").toString())); 
	        
	        HSSFRow row33 = sheet.createRow(33);   
	        HSSFCell c330 = row33.createCell(0);   
	        c330.setCellValue(new HSSFRichTextString("3.罚没收入"));
	        HSSFCell c331 = row33.createCell(1);   
	        c331.setCellValue(new HSSFRichTextString(null==map.get("10305_amc")?"0":map.get("10305_amc").toString())); 
	        HSSFCell c332 = row33.createCell(2);   
	        c332.setCellValue(new HSSFRichTextString(null==map.get("10305_ayc")?"0":map.get("10305_ayc").toString())); 
	        HSSFCell c333 = row33.createCell(3);   
	        c333.setCellValue(new HSSFRichTextString(null==map.get("10305_agrc")?"0":map.get("10305_agrc").toString())); 
	        HSSFCell c334 = row33.createCell(4);   
	        c334.setCellValue(new HSSFRichTextString(null==map.get("10305_aml")?"0":map.get("10305_aml").toString())); 
	        HSSFCell c335 = row33.createCell(5);   
	        c335.setCellValue(new HSSFRichTextString(null==map.get("10305_ayl")?"0":map.get("10305_ayl").toString())); 
	        HSSFCell c336 = row33.createCell(6);   
	        c336.setCellValue(new HSSFRichTextString(null==map.get("10305_agrl")?"0":map.get("10305_agrl").toString())); 
	        HSSFCell c337 = row33.createCell(7);   
	        c337.setCellValue(new HSSFRichTextString(null==map.get("10305_amt")?"0":map.get("10305_amt").toString())); 
	        HSSFCell c338 = row33.createCell(8);   
	        c338.setCellValue(new HSSFRichTextString(null==map.get("10305_agrt")?"0":map.get("10305_agrt").toString())); 
	        
	        
	        HSSFRow row34 = sheet.createRow(34);   
	        HSSFCell c340 = row34.createCell(0);   
	        c340.setCellValue(new HSSFRichTextString("4.国有资本经营收入"));
	        HSSFCell c341 = row34.createCell(1);   
	        c341.setCellValue(new HSSFRichTextString(null==map.get("10306_amc")?"0":map.get("10306_amc").toString())); 
	        HSSFCell c342 = row34.createCell(2);   
	        c342.setCellValue(new HSSFRichTextString(null==map.get("10306_ayc")?"0":map.get("10306_ayc").toString())); 
	        HSSFCell c343 = row34.createCell(3);   
	        c343.setCellValue(new HSSFRichTextString(null==map.get("10306_agrc")?"0":map.get("10306_agrc").toString())); 
	        HSSFCell c344 = row34.createCell(4);   
	        c344.setCellValue(new HSSFRichTextString(null==map.get("10306_aml")?"0":map.get("10306_aml").toString())); 
	        HSSFCell c345 = row34.createCell(5);   
	        c345.setCellValue(new HSSFRichTextString(null==map.get("10306_ayl")?"0":map.get("10306_ayl").toString())); 
	        HSSFCell c346 = row34.createCell(6);   
	        c346.setCellValue(new HSSFRichTextString(null==map.get("10306_agrl")?"0":map.get("10306_agrl").toString())); 
	        HSSFCell c347 = row34.createCell(7);   
	        c347.setCellValue(new HSSFRichTextString(null==map.get("10306_amt")?"0":map.get("10306_amt").toString())); 
	        HSSFCell c348 = row34.createCell(8);   
	        c348.setCellValue(new HSSFRichTextString(null==map.get("10306_agrt")?"0":map.get("10306_agrt").toString())); 
	        
	        
	        
	        HSSFRow row35 = sheet.createRow(35);   
	        HSSFCell c350 = row35.createCell(0);   
	        c350.setCellValue(new HSSFRichTextString("5.国有资产有偿收入"));
	        HSSFCell c351 = row35.createCell(1);   
	        c351.setCellValue(new HSSFRichTextString(null==map.get("10307_amc")?"0":map.get("10307_amc").toString())); 
	        HSSFCell c352 = row35.createCell(2);   
	        c352.setCellValue(new HSSFRichTextString(null==map.get("10307_ayc")?"0":map.get("10307_ayc").toString())); 
	        HSSFCell c353 = row35.createCell(3);   
	        c353.setCellValue(new HSSFRichTextString(null==map.get("10307_agrc")?"0":map.get("10307_agrc").toString())); 
	        HSSFCell c354 = row35.createCell(4);   
	        c354.setCellValue(new HSSFRichTextString(null==map.get("10307_aml")?"0":map.get("10307_aml").toString())); 
	        HSSFCell c355 = row35.createCell(5);   
	        c355.setCellValue(new HSSFRichTextString(null==map.get("10307_ayl")?"0":map.get("10307_ayl").toString())); 
	        HSSFCell c356 = row35.createCell(6);   
	        c356.setCellValue(new HSSFRichTextString(null==map.get("10307_agrl")?"0":map.get("10307_agrl").toString())); 
	        HSSFCell c357 = row35.createCell(7);   
	        c357.setCellValue(new HSSFRichTextString(null==map.get("10307_amt")?"0":map.get("10307_amt").toString())); 
	        HSSFCell c358 = row35.createCell(8);   
	        c358.setCellValue(new HSSFRichTextString(null==map.get("10307_agrt")?"0":map.get("10307_agrt").toString())); 
	        
	        
	        
	        HSSFRow row36 = sheet.createRow(36);   
	        HSSFCell c360 = row36.createCell(0);   
	        c360.setCellValue(new HSSFRichTextString("6.其他收入"));
	        HSSFCell c361 = row36.createCell(1);   
	        c361.setCellValue(new HSSFRichTextString(null==map.get("10399_amc")?"0":map.get("10399_amc").toString())); 
	        HSSFCell c362 = row36.createCell(2);   
	        c362.setCellValue(new HSSFRichTextString(null==map.get("10399_ayc")?"0":map.get("10399_ayc").toString())); 
	        HSSFCell c363 = row36.createCell(3);   
	        c363.setCellValue(new HSSFRichTextString(null==map.get("10399_agrc")?"0":map.get("10399_agrc").toString())); 
	        HSSFCell c364 = row36.createCell(4);   
	        c364.setCellValue(new HSSFRichTextString(null==map.get("10399_aml")?"0":map.get("10399_aml").toString())); 
	        HSSFCell c365 = row36.createCell(5);   
	        c365.setCellValue(new HSSFRichTextString(null==map.get("10399_ayl")?"0":map.get("10399_ayl").toString())); 
	        HSSFCell c366 = row36.createCell(6);   
	        c366.setCellValue(new HSSFRichTextString(null==map.get("10399_agrl")?"0":map.get("10399_agrl").toString())); 
	        HSSFCell c367 = row36.createCell(7);   
	        c367.setCellValue(new HSSFRichTextString(null==map.get("10399_amt")?"0":map.get("10399_amt").toString())); 
	        HSSFCell c368 = row36.createCell(8);   
	        c368.setCellValue(new HSSFRichTextString(null==map.get("10399_agrt")?"0":map.get("10399_agrt").toString())); 
	        
	        
	        HSSFRow row37 = sheet.createRow(37);   
	        HSSFCell c370 = row37.createCell(0);   
	        c370.setCellValue(new HSSFRichTextString("四、政府性基金收入"));
	        HSSFCell c371 = row37.createCell(1);   
	        c371.setCellValue(new HSSFRichTextString(null==map.get("10301_amc")?"0":map.get("10301_amc").toString())); 
	        HSSFCell c372 = row37.createCell(2);   
	        c372.setCellValue(new HSSFRichTextString(null==map.get("10301_ayc")?"0":map.get("10301_ayc").toString())); 
	        HSSFCell c373 = row37.createCell(3);   
	        c373.setCellValue(new HSSFRichTextString(null==map.get("10301_agrc")?"0":map.get("10301_agrc").toString())); 
	        HSSFCell c374 = row37.createCell(4);   
	        c374.setCellValue(new HSSFRichTextString(null==map.get("10301_aml")?"0":map.get("10301_aml").toString())); 
	        HSSFCell c375 = row37.createCell(5);   
	        c375.setCellValue(new HSSFRichTextString(null==map.get("10301_ayl")?"0":map.get("10301_ayl").toString())); 
	        HSSFCell c376 = row37.createCell(6);   
	        c376.setCellValue(new HSSFRichTextString(null==map.get("10301_agrl")?"0":map.get("10301_agrl").toString())); 
	        HSSFCell c377 = row37.createCell(7);   
	        c377.setCellValue(new HSSFRichTextString(null==map.get("10301_amt")?"0":map.get("10301_amt").toString())); 
	        HSSFCell c378 = row37.createCell(8);   
	        c378.setCellValue(new HSSFRichTextString(null==map.get("10301_agrt")?"0":map.get("10301_agrt").toString())); 
	        
	        
	        HSSFRow row38 = sheet.createRow(38);   
	        HSSFCell c380 = row38.createCell(0);   
	        c380.setCellValue(new HSSFRichTextString("其中：土地出让收入"));
	        HSSFCell c381 = row38.createCell(1);   
	        c381.setCellValue(new HSSFRichTextString(null==map.get("103014801_amc")?"0":map.get("103014801_amc").toString())); 
	        HSSFCell c382 = row38.createCell(2);   
	        c382.setCellValue(new HSSFRichTextString(null==map.get("103014801_ayc")?"0":map.get("103014801_ayc").toString())); 
	        HSSFCell c383 = row38.createCell(3);   
	        c383.setCellValue(new HSSFRichTextString(null==map.get("103014801_agrc")?"0":map.get("103014801_agrc").toString())); 
	        HSSFCell c384 = row38.createCell(4);   
	        c384.setCellValue(new HSSFRichTextString(null==map.get("103014801_aml")?"0":map.get("103014801_aml").toString())); 
	        HSSFCell c385 = row38.createCell(5);   
	        c385.setCellValue(new HSSFRichTextString(null==map.get("103014801_ayl")?"0":map.get("103014801_ayl").toString())); 
	        HSSFCell c386 = row38.createCell(6);   
	        c386.setCellValue(new HSSFRichTextString(null==map.get("103014801_agrl")?"0":map.get("103014801_agrl").toString())); 
	        HSSFCell c387 = row38.createCell(7);   
	        c387.setCellValue(new HSSFRichTextString(null==map.get("103014801_amt")?"0":map.get("103014801_amt").toString())); 
	        HSSFCell c388 = row38.createCell(8);   
	        c388.setCellValue(new HSSFRichTextString(null==map.get("103014801_agrt")?"0":map.get("103014801_agrt").toString())); 
	        
	        HSSFRow row39 = sheet.createRow(39);   
	        HSSFCell c390 = row39.createCell(0);   
	        c390.setCellValue(new HSSFRichTextString("五、国库支出"));
	        HSSFCell c391 = row39.createCell(1);   
	        c391.setCellValue(new HSSFRichTextString(null==map.get("T02_amc")?"0":map.get("T02_amc").toString())); 
	        HSSFCell c392 = row39.createCell(2);   
	        c392.setCellValue(new HSSFRichTextString(null==map.get("T02_ayc")?"0":map.get("T02_ayc").toString())); 
	        HSSFCell c393 = row39.createCell(3);   
	        c393.setCellValue(new HSSFRichTextString(null==map.get("T02_agrc")?"0":map.get("T02_agrc").toString())); 
	        HSSFCell c394 = row39.createCell(4);   
	        c394.setCellValue(new HSSFRichTextString(null==map.get("T02_aml")?"0":map.get("T02_aml").toString())); 
	        HSSFCell c395 = row39.createCell(5);   
	        c395.setCellValue(new HSSFRichTextString(null==map.get("T02_ayl")?"0":map.get("T02_ayl").toString())); 
	        HSSFCell c396 = row39.createCell(6);   
	        c396.setCellValue(new HSSFRichTextString(null==map.get("T02_agrl")?"0":map.get("T02_agrl").toString())); 
	        HSSFCell c397 = row39.createCell(7);   
	        c397.setCellValue(new HSSFRichTextString(null==map.get("T02_amt")?"0":map.get("T02_amt").toString())); 
	        HSSFCell c398 = row39.createCell(8);   
	        c398.setCellValue(new HSSFRichTextString(null==map.get("T02_agrt")?"0":map.get("T02_agrt").toString())); 
	        
	        
	        
	        HSSFRow row40 = sheet.createRow(40);   
	        HSSFCell c400 = row40.createCell(0);   
	        c400.setCellValue(new HSSFRichTextString("1.公共预算支出"));
	        HSSFCell c401 = row40.createCell(1);   
	        c401.setCellValue(new HSSFRichTextString(null==map.get("T020101_amc")?"0":map.get("T020101_amc").toString())); 
	        HSSFCell c402 = row40.createCell(2);   
	        c402.setCellValue(new HSSFRichTextString(null==map.get("T020101_ayc")?"0":map.get("T020101_ayc").toString())); 
	        HSSFCell c403 = row40.createCell(3);   
	        c403.setCellValue(new HSSFRichTextString(null==map.get("T020101_agrc")?"0":map.get("T020101_agrc").toString())); 
	        HSSFCell c404 = row40.createCell(4);   
	        c404.setCellValue(new HSSFRichTextString(null==map.get("T020101_aml")?"0":map.get("T020101_aml").toString())); 
	        HSSFCell c405 = row40.createCell(5);   
	        c405.setCellValue(new HSSFRichTextString(null==map.get("T020101_ayl")?"0":map.get("T020101_ayl").toString())); 
	        HSSFCell c406 = row40.createCell(6);   
	        c406.setCellValue(new HSSFRichTextString(null==map.get("T020101_agrl")?"0":map.get("T020101_agrl").toString())); 
	        HSSFCell c407 = row40.createCell(7);   
	        c407.setCellValue(new HSSFRichTextString(null==map.get("T020101_amt")?"0":map.get("T020101_amt").toString())); 
	        HSSFCell c408 = row40.createCell(8);   
	        c408.setCellValue(new HSSFRichTextString(null==map.get("T020101_agrt")?"0":map.get("T020101_agrt").toString())); 
	        
	        HSSFRow row41 = sheet.createRow(41);   
	        HSSFCell c410 = row41.createCell(0);   
	        c410.setCellValue(new HSSFRichTextString("2.公共预算调拨支出"));
	        HSSFCell c411 = row41.createCell(1);   
	        c411.setCellValue(new HSSFRichTextString(null==map.get("T020102_amc")?"0":map.get("T020102_amc").toString())); 
	        HSSFCell c412 = row41.createCell(2);   
	        c412.setCellValue(new HSSFRichTextString(null==map.get("T020102_ayc")?"0":map.get("T020102_ayc").toString())); 
	        HSSFCell c413 = row41.createCell(3);   
	        c413.setCellValue(new HSSFRichTextString(null==map.get("T020102_agrc")?"0":map.get("T020102_agrc").toString())); 
	        HSSFCell c414 = row41.createCell(4);   
	        c414.setCellValue(new HSSFRichTextString(null==map.get("T020102_aml")?"0":map.get("T020102_aml").toString())); 
	        HSSFCell c415 = row41.createCell(5);   
	        c415.setCellValue(new HSSFRichTextString(null==map.get("T020102_ayl")?"0":map.get("T020102_ayl").toString())); 
	        HSSFCell c416 = row41.createCell(6);   
	        c416.setCellValue(new HSSFRichTextString(null==map.get("T020102_agrl")?"0":map.get("T020102_agrl").toString())); 
	        HSSFCell c417 = row41.createCell(7);   
	        c417.setCellValue(new HSSFRichTextString(null==map.get("T020102_amt")?"0":map.get("T020102_amt").toString())); 
	        HSSFCell c418 = row41.createCell(8);   
	        c418.setCellValue(new HSSFRichTextString(null==map.get("T020102_agrt")?"0":map.get("T020102_agrt").toString())); 
	        
	        HSSFRow row42 = sheet.createRow(42);   
	        HSSFCell c420 = row42.createCell(0);   
	        c420.setCellValue(new HSSFRichTextString("3.基金预算支出"));
	        HSSFCell c421 = row42.createCell(1);   
	        c421.setCellValue(new HSSFRichTextString(null==map.get("T020201_amc")?"0":map.get("T020201_amc").toString())); 
	        HSSFCell c422 = row42.createCell(2);   
	        c422.setCellValue(new HSSFRichTextString(null==map.get("T020201_ayc")?"0":map.get("T020201_ayc").toString())); 
	        HSSFCell c423 = row42.createCell(3);   
	        c423.setCellValue(new HSSFRichTextString(null==map.get("T020201_agrc")?"0":map.get("T020201_agrc").toString())); 
	        HSSFCell c424 = row42.createCell(4);   
	        c424.setCellValue(new HSSFRichTextString(null==map.get("T020201_aml")?"0":map.get("T020201_aml").toString())); 
	        HSSFCell c425 = row42.createCell(5);   
	        c425.setCellValue(new HSSFRichTextString(null==map.get("T020201_ayl")?"0":map.get("T020201_ayl").toString())); 
	        HSSFCell c426 = row42.createCell(6);   
	        c426.setCellValue(new HSSFRichTextString(null==map.get("T020201_agrl")?"0":map.get("T020201_agrl").toString())); 
	        HSSFCell c427 = row42.createCell(7);   
	        c427.setCellValue(new HSSFRichTextString(null==map.get("T020201_amt")?"0":map.get("T020201_amt").toString())); 
	        HSSFCell c428 = row42.createCell(8);   
	        c428.setCellValue(new HSSFRichTextString(null==map.get("T020201_agrt")?"0":map.get("T020201_agrt").toString())); 
	        
	        HSSFRow row43 = sheet.createRow(43);   
	        HSSFCell c430 = row43.createCell(0);   
	        c430.setCellValue(new HSSFRichTextString("4.基金调拨支出"));
	        HSSFCell c431 = row43.createCell(1);   
	        c431.setCellValue(new HSSFRichTextString(null==map.get("T020202_amc")?"0":map.get("T020202_amc").toString())); 
	        HSSFCell c432 = row43.createCell(2);   
	        c432.setCellValue(new HSSFRichTextString(null==map.get("T020202_ayc")?"0":map.get("T020202_ayc").toString())); 
	        HSSFCell c433 = row43.createCell(3);   
	        c433.setCellValue(new HSSFRichTextString(null==map.get("T020202_agrc")?"0":map.get("T020202_agrc").toString())); 
	        HSSFCell c434 = row43.createCell(4);   
	        c434.setCellValue(new HSSFRichTextString(null==map.get("T020202_aml")?"0":map.get("T020202_aml").toString())); 
	        HSSFCell c435 = row43.createCell(5);   
	        c435.setCellValue(new HSSFRichTextString(null==map.get("T020202_ayl")?"0":map.get("T020202_ayl").toString())); 
	        HSSFCell c436 = row43.createCell(6);   
	        c436.setCellValue(new HSSFRichTextString(null==map.get("T020202_agrl")?"0":map.get("T020202_agrl").toString())); 
	        HSSFCell c437 = row43.createCell(7);   
	        c437.setCellValue(new HSSFRichTextString(null==map.get("T020202_amt")?"0":map.get("T020202_amt").toString())); 
	        HSSFCell c438 = row43.createCell(8);   
	        c438.setCellValue(new HSSFRichTextString(null==map.get("T020202_agrt")?"0":map.get("T020202_agrt").toString())); 
	        
	        
	        HSSFRow row44 = sheet.createRow(44);   
	        HSSFCell c440 = row44.createCell(0);   
	        c440.setCellValue(new HSSFRichTextString("5.债务预算支出"));
	        HSSFCell c441 = row44.createCell(1);   
	        c441.setCellValue(new HSSFRichTextString(null==map.get("T0203_amc")?"0":map.get("T0203_amc").toString())); 
	        HSSFCell c442 = row44.createCell(2);   
	        c442.setCellValue(new HSSFRichTextString(null==map.get("T0203_ayc")?"0":map.get("T0203_ayc").toString())); 
	        HSSFCell c443 = row44.createCell(3);   
	        c443.setCellValue(new HSSFRichTextString(null==map.get("T0203_agrc")?"0":map.get("T0203_agrc").toString())); 
	        HSSFCell c444 = row44.createCell(4);   
	        c444.setCellValue(new HSSFRichTextString(null==map.get("T0203_aml")?"0":map.get("T0203_aml").toString())); 
	        HSSFCell c445 = row44.createCell(5);   
	        c445.setCellValue(new HSSFRichTextString(null==map.get("T0203_ayl")?"0":map.get("T0203_ayl").toString())); 
	        HSSFCell c446 = row44.createCell(6);   
	        c446.setCellValue(new HSSFRichTextString(null==map.get("T0203_agrl")?"0":map.get("T0203_agrl").toString())); 
	        HSSFCell c447 = row44.createCell(7);   
	        c447.setCellValue(new HSSFRichTextString(null==map.get("T0203_amt")?"0":map.get("T0203_amt").toString())); 
	        HSSFCell c448 = row44.createCell(8);   
	        c448.setCellValue(new HSSFRichTextString(null==map.get("T0203_agrt")?"0":map.get("T0203_agrt").toString())); 
	        
	        
	        
	        HSSFRow row45 = sheet.createRow(45);   
	        HSSFCell c450 = row45.createCell(0);   
	        c450.setCellValue(new HSSFRichTextString("6.国有经营预算支出"));
	        HSSFCell c451 = row45.createCell(1);   
	        c451.setCellValue(new HSSFRichTextString(null==map.get("T0206_amc")?"0":map.get("T0206_amc").toString())); 
	        HSSFCell c452 = row45.createCell(2);   
	        c452.setCellValue(new HSSFRichTextString(null==map.get("T0206_ayc")?"0":map.get("T0206_ayc").toString())); 
	        HSSFCell c453 = row45.createCell(3);   
	        c453.setCellValue(new HSSFRichTextString(null==map.get("T0206_agrc")?"0":map.get("T0206_agrc").toString())); 
	        HSSFCell c454 = row45.createCell(4);   
	        c454.setCellValue(new HSSFRichTextString(null==map.get("T0206_aml")?"0":map.get("T0206_aml").toString())); 
	        HSSFCell c455 = row45.createCell(5);   
	        c455.setCellValue(new HSSFRichTextString(null==map.get("T0206_ayl")?"0":map.get("T0206_ayl").toString())); 
	        HSSFCell c456 = row45.createCell(6);   
	        c456.setCellValue(new HSSFRichTextString(null==map.get("T0206_agrl")?"0":map.get("T0206_agrl").toString())); 
	        HSSFCell c457 = row45.createCell(7);   
	        c457.setCellValue(new HSSFRichTextString(null==map.get("T0206_amt")?"0":map.get("T0206_amt").toString())); 
	        HSSFCell c458 = row45.createCell(8);   
	        c458.setCellValue(new HSSFRichTextString(null==map.get("T0206_agrt")?"0":map.get("T0206_agrt").toString())); 
	        
	        
	        
	        HSSFRow row46 = sheet.createRow(46);   
	        HSSFCell c460 = row46.createCell(0);   
	        c460.setCellValue(new HSSFRichTextString("六、期末库存"));
	        HSSFCell c461 = row46.createCell(1);   
	        c461.setCellValue(new HSSFRichTextString(null==map.get("_amc")?"0":map.get("_amc").toString())); 
	        HSSFCell c462 = row46.createCell(2);   
	        c462.setCellValue(new HSSFRichTextString(null==map.get("_ayc")?"0":map.get("_ayc").toString())); 
	        HSSFCell c463 = row46.createCell(3);   
	        c463.setCellValue(new HSSFRichTextString(null==map.get("_agrc")?"0":map.get("_agrc").toString())); 
	        HSSFCell c464 = row46.createCell(4);   
	        c464.setCellValue(new HSSFRichTextString(null==map.get("_aml")?"0":map.get("_aml").toString())); 
	        HSSFCell c465 = row46.createCell(5);   
	        c465.setCellValue(new HSSFRichTextString(null==map.get("_ayl")?"0":map.get("_ayl").toString())); 
	        HSSFCell c466 = row46.createCell(6);   
	        c466.setCellValue(new HSSFRichTextString(null==map.get("_agrl")?"0":map.get("_agrl").toString())); 
	        HSSFCell c467 = row46.createCell(7);   
	        c467.setCellValue(new HSSFRichTextString(null==map.get("_amt")?"0":map.get("_amt").toString())); 
	        HSSFCell c468 = row46.createCell(8);   
	        c468.setCellValue(new HSSFRichTextString(null==map.get("_agrt")?"0":map.get("_agrt").toString())); 
	        
	        
	        //单元格合并 ，每行都进行特殊处理
	        Region region1 = new Region((short)1, (short)0, (short)1, (short)8);   
	        Region region2 = new Region((short)2, (short)0, (short)2, (short)1);   
	        Region region3 = new Region((short)2, (short)2, (short)2, (short)8);
	        Region region4 = new Region((short)3, (short)0, (short)4, (short)0);   
	        Region region5 = new Region((short)3, (short)1, (short)3, (short)3);  
	        Region region6 = new Region((short)3, (short)4, (short)3, (short)6);  
	        Region region7 = new Region((short)3, (short)7, (short)3, (short)8);  
	        
	        sheet.addMergedRegion(region1);   
	        sheet.addMergedRegion(region2);   
	        sheet.addMergedRegion(region3); 
	        sheet.addMergedRegion(region4); 
	        sheet.addMergedRegion(region5);   
	        sheet.addMergedRegion(region6); 
	        sheet.addMergedRegion(region7); 
	       
	        
	        //表格框设置
	        c10.setCellStyle(style1); 
	        c11.setCellStyle(style1); 
	        c12.setCellStyle(style1); 
	        c13.setCellStyle(style1); 
	        c14.setCellStyle(style1); 
	        c15.setCellStyle(style1); 
	        c16.setCellStyle(style1); 
	        c17.setCellStyle(style1); 
	        c18.setCellStyle(style1); 
	        
	        c20.setCellStyle(style2); 
	        c21.setCellStyle(style2); 
	        c22.setCellStyle(style3); 
	        c23.setCellStyle(style3); 
	        c24.setCellStyle(style3); 
	        c25.setCellStyle(style3); 
	        c26.setCellStyle(style3); 
	        c27.setCellStyle(style3); 
	        c28.setCellStyle(style3); 
	        
	        c30.setCellStyle(style5); 
	        c31.setCellStyle(style4); 
	        c32.setCellStyle(style4); 
	        c33.setCellStyle(style4); 
	        c34.setCellStyle(style4); 
	        c35.setCellStyle(style4); 
	        c36.setCellStyle(style4); 
	        c37.setCellStyle(style4); 
	        c38.setCellStyle(style4); 
	        
	        
	        c40.setCellStyle(style5); 
	        c41.setCellStyle(style4); 
	        c42.setCellStyle(style4); 
	        c43.setCellStyle(style4); 
	        c44.setCellStyle(style4); 
	        c45.setCellStyle(style4); 
	        c46.setCellStyle(style4); 
	        c47.setCellStyle(style4); 
	        c48.setCellStyle(style4); 
	        
	        c50.setCellStyle(style); 
	        c51.setCellStyle(style); 
	        c52.setCellStyle(style); 
	        c53.setCellStyle(style); 
	        c54.setCellStyle(style); 
	        c55.setCellStyle(style); 
	        c56.setCellStyle(style); 
	        c57.setCellStyle(style); 
	        c58.setCellStyle(style); 
	        
	        
	        c60.setCellStyle(style); 
	        c61.setCellStyle(style); 
	        c62.setCellStyle(style); 
	        c63.setCellStyle(style); 
	        c64.setCellStyle(style); 
	        c65.setCellStyle(style); 
	        c66.setCellStyle(style); 
	        c67.setCellStyle(style); 
	        c68.setCellStyle(style); 
	        
	        c70.setCellStyle(style); 
	        c71.setCellStyle(style); 
	        c72.setCellStyle(style); 
	        c73.setCellStyle(style); 
	        c74.setCellStyle(style); 
	        c75.setCellStyle(style); 
	        c76.setCellStyle(style); 
	        c77.setCellStyle(style); 
	        c78.setCellStyle(style); 
	        
	        c80.setCellStyle(style); 
	        c81.setCellStyle(style); 
	        c82.setCellStyle(style); 
	        c83.setCellStyle(style); 
	        c84.setCellStyle(style); 
	        c85.setCellStyle(style); 
	        c86.setCellStyle(style); 
	        c87.setCellStyle(style); 
	        c88.setCellStyle(style); 
	        
	        
	        c90.setCellStyle(style); 
	        c91.setCellStyle(style); 
	        c92.setCellStyle(style); 
	        c93.setCellStyle(style); 
	        c94.setCellStyle(style); 
	        c95.setCellStyle(style); 
	        c96.setCellStyle(style); 
	        c97.setCellStyle(style); 
	        c98.setCellStyle(style); 
	        
	        c100.setCellStyle(style); 
	        c101.setCellStyle(style); 
	        c102.setCellStyle(style); 
	        c103.setCellStyle(style); 
	        c104.setCellStyle(style); 
	        c105.setCellStyle(style); 
	        c106.setCellStyle(style); 
	        c107.setCellStyle(style); 
	        c108.setCellStyle(style); 
	        
	        c110.setCellStyle(style); 
	        c111.setCellStyle(style); 
	        c112.setCellStyle(style); 
	        c113.setCellStyle(style); 
	        c114.setCellStyle(style); 
	        c115.setCellStyle(style); 
	        c116.setCellStyle(style); 
	        c117.setCellStyle(style); 
	        c118.setCellStyle(style); 
	        
	        
	        c120.setCellStyle(style); 
	        c121.setCellStyle(style); 
	        c122.setCellStyle(style); 
	        c123.setCellStyle(style); 
	        c124.setCellStyle(style); 
	        c125.setCellStyle(style); 
	        c126.setCellStyle(style); 
	        c127.setCellStyle(style); 
	        c128.setCellStyle(style); 
	        
	        c130.setCellStyle(style); 
	        c131.setCellStyle(style); 
	        c132.setCellStyle(style); 
	        c133.setCellStyle(style); 
	        c134.setCellStyle(style); 
	        c135.setCellStyle(style); 
	        c136.setCellStyle(style); 
	        c137.setCellStyle(style); 
	        c138.setCellStyle(style); 
	        
	        
	        c140.setCellStyle(style); 
	        c141.setCellStyle(style); 
	        c142.setCellStyle(style); 
	        c143.setCellStyle(style); 
	        c144.setCellStyle(style); 
	        c145.setCellStyle(style); 
	        c146.setCellStyle(style); 
	        c147.setCellStyle(style); 
	        c148.setCellStyle(style); 
	        
	        c150.setCellStyle(style); 
	        c151.setCellStyle(style); 
	        c152.setCellStyle(style); 
	        c153.setCellStyle(style); 
	        c154.setCellStyle(style); 
	        c155.setCellStyle(style); 
	        c156.setCellStyle(style); 
	        c157.setCellStyle(style); 
	        c158.setCellStyle(style); 
	        
	        
	        c160.setCellStyle(style); 
	        c161.setCellStyle(style); 
	        c162.setCellStyle(style); 
	        c163.setCellStyle(style); 
	        c164.setCellStyle(style); 
	        c165.setCellStyle(style); 
	        c166.setCellStyle(style); 
	        c167.setCellStyle(style); 
	        c168.setCellStyle(style); 
	        
	        c170.setCellStyle(style); 
	        c171.setCellStyle(style); 
	        c172.setCellStyle(style); 
	        c173.setCellStyle(style); 
	        c174.setCellStyle(style); 
	        c175.setCellStyle(style); 
	        c176.setCellStyle(style); 
	        c177.setCellStyle(style); 
	        c178.setCellStyle(style); 
	        
	        c180.setCellStyle(style); 
	        c181.setCellStyle(style); 
	        c182.setCellStyle(style); 
	        c183.setCellStyle(style); 
	        c184.setCellStyle(style); 
	        c185.setCellStyle(style); 
	        c186.setCellStyle(style); 
	        c187.setCellStyle(style); 
	        c188.setCellStyle(style); 
	        
	        
	        c190.setCellStyle(style); 
	        c191.setCellStyle(style); 
	        c192.setCellStyle(style); 
	        c193.setCellStyle(style); 
	        c194.setCellStyle(style); 
	        c195.setCellStyle(style); 
	        c196.setCellStyle(style); 
	        c197.setCellStyle(style); 
	        c198.setCellStyle(style); 
	        
	        c200.setCellStyle(style); 
	        c201.setCellStyle(style); 
	        c202.setCellStyle(style); 
	        c203.setCellStyle(style); 
	        c204.setCellStyle(style); 
	        c205.setCellStyle(style); 
	        c206.setCellStyle(style); 
	        c207.setCellStyle(style); 
	        c208.setCellStyle(style); 
	        
	        c210.setCellStyle(style); 
	        c211.setCellStyle(style); 
	        c212.setCellStyle(style); 
	        c213.setCellStyle(style); 
	        c214.setCellStyle(style); 
	        c215.setCellStyle(style); 
	        c216.setCellStyle(style); 
	        c217.setCellStyle(style); 
	        c218.setCellStyle(style); 
	        
	        c220.setCellStyle(style); 
	        c221.setCellStyle(style); 
	        c222.setCellStyle(style); 
	        c223.setCellStyle(style); 
	        c224.setCellStyle(style); 
	        c225.setCellStyle(style); 
	        c226.setCellStyle(style); 
	        c227.setCellStyle(style); 
	        c228.setCellStyle(style); 
	        
	        c230.setCellStyle(style); 
	        c231.setCellStyle(style); 
	        c232.setCellStyle(style); 
	        c233.setCellStyle(style); 
	        c234.setCellStyle(style); 
	        c235.setCellStyle(style); 
	        c236.setCellStyle(style); 
	        c237.setCellStyle(style); 
	        c238.setCellStyle(style); 
	        
	        
	        c240.setCellStyle(style); 
	        c241.setCellStyle(style); 
	        c242.setCellStyle(style); 
	        c243.setCellStyle(style); 
	        c244.setCellStyle(style); 
	        c245.setCellStyle(style); 
	        c246.setCellStyle(style); 
	        c247.setCellStyle(style); 
	        c248.setCellStyle(style); 
	        
	        c250.setCellStyle(style); 
	        c251.setCellStyle(style); 
	        c252.setCellStyle(style); 
	        c253.setCellStyle(style); 
	        c254.setCellStyle(style); 
	        c255.setCellStyle(style); 
	        c256.setCellStyle(style); 
	        c257.setCellStyle(style); 
	        c258.setCellStyle(style); 
	        
	        
	        c260.setCellStyle(style); 
	        c261.setCellStyle(style); 
	        c262.setCellStyle(style); 
	        c263.setCellStyle(style); 
	        c264.setCellStyle(style); 
	        c265.setCellStyle(style); 
	        c266.setCellStyle(style); 
	        c267.setCellStyle(style); 
	        c268.setCellStyle(style); 
	        
	        c270.setCellStyle(style); 
	        c271.setCellStyle(style); 
	        c272.setCellStyle(style); 
	        c273.setCellStyle(style); 
	        c274.setCellStyle(style); 
	        c275.setCellStyle(style); 
	        c276.setCellStyle(style); 
	        c277.setCellStyle(style); 
	        c278.setCellStyle(style); 
	        
	        c280.setCellStyle(style); 
	        c281.setCellStyle(style); 
	        c282.setCellStyle(style); 
	        c283.setCellStyle(style); 
	        c284.setCellStyle(style); 
	        c285.setCellStyle(style); 
	        c286.setCellStyle(style); 
	        c287.setCellStyle(style); 
	        c288.setCellStyle(style); 
	        
	        
	        c290.setCellStyle(style); 
	        c291.setCellStyle(style); 
	        c292.setCellStyle(style); 
	        c293.setCellStyle(style); 
	        c294.setCellStyle(style); 
	        c295.setCellStyle(style); 
	        c296.setCellStyle(style); 
	        c297.setCellStyle(style); 
	        c298.setCellStyle(style); 
	        
	        c300.setCellStyle(style); 
	        c301.setCellStyle(style); 
	        c302.setCellStyle(style); 
	        c303.setCellStyle(style); 
	        c304.setCellStyle(style); 
	        c305.setCellStyle(style); 
	        c306.setCellStyle(style); 
	        c307.setCellStyle(style); 
	        c308.setCellStyle(style); 
	        
	        c310.setCellStyle(style); 
	        c311.setCellStyle(style); 
	        c312.setCellStyle(style); 
	        c313.setCellStyle(style); 
	        c314.setCellStyle(style); 
	        c315.setCellStyle(style); 
	        c316.setCellStyle(style); 
	        c317.setCellStyle(style); 
	        c318.setCellStyle(style); 
	        
	        c320.setCellStyle(style); 
	        c321.setCellStyle(style); 
	        c322.setCellStyle(style); 
	        c323.setCellStyle(style); 
	        c324.setCellStyle(style); 
	        c325.setCellStyle(style); 
	        c326.setCellStyle(style); 
	        c327.setCellStyle(style); 
	        c328.setCellStyle(style); 
	        
	        c330.setCellStyle(style); 
	        c331.setCellStyle(style); 
	        c332.setCellStyle(style); 
	        c333.setCellStyle(style); 
	        c334.setCellStyle(style); 
	        c335.setCellStyle(style); 
	        c336.setCellStyle(style); 
	        c337.setCellStyle(style); 
	        c338.setCellStyle(style); 
	        
	        
	        c340.setCellStyle(style); 
	        c341.setCellStyle(style); 
	        c342.setCellStyle(style); 
	        c343.setCellStyle(style); 
	        c344.setCellStyle(style); 
	        c345.setCellStyle(style); 
	        c346.setCellStyle(style); 
	        c347.setCellStyle(style); 
	        c348.setCellStyle(style); 
	        
	        c350.setCellStyle(style); 
	        c351.setCellStyle(style); 
	        c352.setCellStyle(style); 
	        c353.setCellStyle(style); 
	        c354.setCellStyle(style); 
	        c355.setCellStyle(style); 
	        c356.setCellStyle(style); 
	        c357.setCellStyle(style); 
	        c358.setCellStyle(style); 
	        
	        
	        c360.setCellStyle(style); 
	        c361.setCellStyle(style); 
	        c362.setCellStyle(style); 
	        c363.setCellStyle(style); 
	        c364.setCellStyle(style); 
	        c365.setCellStyle(style); 
	        c366.setCellStyle(style); 
	        c367.setCellStyle(style); 
	        c368.setCellStyle(style); 
	        
	        c370.setCellStyle(style); 
	        c371.setCellStyle(style); 
	        c372.setCellStyle(style); 
	        c373.setCellStyle(style); 
	        c374.setCellStyle(style); 
	        c375.setCellStyle(style); 
	        c376.setCellStyle(style); 
	        c377.setCellStyle(style); 
	        c378.setCellStyle(style); 
	        
	        c380.setCellStyle(style); 
	        c381.setCellStyle(style); 
	        c382.setCellStyle(style); 
	        c383.setCellStyle(style); 
	        c384.setCellStyle(style); 
	        c385.setCellStyle(style); 
	        c386.setCellStyle(style); 
	        c387.setCellStyle(style); 
	        c388.setCellStyle(style); 
	        
	        
	        c390.setCellStyle(style); 
	        c391.setCellStyle(style); 
	        c392.setCellStyle(style); 
	        c393.setCellStyle(style); 
	        c394.setCellStyle(style); 
	        c395.setCellStyle(style); 
	        c396.setCellStyle(style); 
	        c397.setCellStyle(style); 
	        c398.setCellStyle(style); 
	        
	        c400.setCellStyle(style); 
	        c401.setCellStyle(style); 
	        c402.setCellStyle(style); 
	        c403.setCellStyle(style); 
	        c404.setCellStyle(style); 
	        c405.setCellStyle(style); 
	        c406.setCellStyle(style); 
	        c407.setCellStyle(style); 
	        c408.setCellStyle(style); 
	        
	        c410.setCellStyle(style); 
	        c411.setCellStyle(style); 
	        c412.setCellStyle(style); 
	        c413.setCellStyle(style); 
	        c414.setCellStyle(style); 
	        c415.setCellStyle(style); 
	        c416.setCellStyle(style); 
	        c417.setCellStyle(style); 
	        c418.setCellStyle(style); 
	        
	        c420.setCellStyle(style); 
	        c421.setCellStyle(style); 
	        c422.setCellStyle(style); 
	        c423.setCellStyle(style); 
	        c424.setCellStyle(style); 
	        c425.setCellStyle(style); 
	        c426.setCellStyle(style); 
	        c427.setCellStyle(style); 
	        c428.setCellStyle(style); 
	        
	        c430.setCellStyle(style); 
	        c431.setCellStyle(style); 
	        c432.setCellStyle(style); 
	        c433.setCellStyle(style); 
	        c434.setCellStyle(style); 
	        c435.setCellStyle(style); 
	        c436.setCellStyle(style); 
	        c437.setCellStyle(style); 
	        c438.setCellStyle(style); 
	        
	        c440.setCellStyle(style); 
	        c441.setCellStyle(style); 
	        c442.setCellStyle(style); 
	        c443.setCellStyle(style); 
	        c444.setCellStyle(style); 
	        c445.setCellStyle(style); 
	        c446.setCellStyle(style); 
	        c447.setCellStyle(style); 
	        c448.setCellStyle(style); 
	        
	        c450.setCellStyle(style); 
	        c451.setCellStyle(style); 
	        c452.setCellStyle(style); 
	        c453.setCellStyle(style); 
	        c454.setCellStyle(style); 
	        c455.setCellStyle(style); 
	        c456.setCellStyle(style); 
	        c457.setCellStyle(style); 
	        c458.setCellStyle(style); 
	        
	        c460.setCellStyle(style); 
	        c461.setCellStyle(style); 
	        c462.setCellStyle(style); 
	        c463.setCellStyle(style); 
	        c464.setCellStyle(style); 
	        c465.setCellStyle(style); 
	        c466.setCellStyle(style); 
	        c467.setCellStyle(style); 
	        c468.setCellStyle(style); 
	        
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
