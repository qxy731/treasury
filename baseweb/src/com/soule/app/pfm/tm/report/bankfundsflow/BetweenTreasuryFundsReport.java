package com.soule.app.pfm.tm.report.bankfundsflow;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
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

public class BetweenTreasuryFundsReport {
	
	public static void Data2Excel(Map<String,Object> map,ByteArrayOutputStream fout){
		
		 HSSFWorkbook workbook = new HSSFWorkbook();   
	        //创建sheet页  
	        HSSFSheet sheet = workbook.createSheet("与其他国库之间资金流动情况统计表");  
	        
	        sheet.setColumnWidth(0, 35*256);
	        sheet.setColumnWidth(1, 15*256);
	        sheet.setColumnWidth(2, 15*256);
	        sheet.setColumnWidth(3, 15*256);
	        sheet.setColumnWidth(4, 15*256);
	        sheet.setColumnWidth(5, 20*256);
	        sheet.setColumnWidth(6, 20*256);
	        sheet.setColumnWidth(7, 20*256);
	        
	        CellStyle style = workbook.createCellStyle();  
	        CellStyle style1 = workbook.createCellStyle();//第一行表格样式
	        CellStyle style2 = workbook.createCellStyle();//第二行表格样式
	        CellStyle style3 = workbook.createCellStyle();//第三行表格样式
	        CellStyle style4 = workbook.createCellStyle();//合并单元格表格样式
    
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
	        //合并单元格样式
	        
	        style4.setAlignment(HSSFCellStyle.ALIGN_CENTER);
	        style4.setBorderBottom(CellStyle.BORDER_MEDIUM); 
	        style4.setBorderLeft(CellStyle.BORDER_MEDIUM);  
	        style4.setBorderRight(CellStyle.BORDER_MEDIUM);  
	        style4.setBorderTop(CellStyle.BORDER_MEDIUM); 
	        style4.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
	        style4.setFillPattern(CellStyle.SOLID_FOREGROUND);
	        
	        
	        //创建单元格  
	        HSSFRow row = sheet.createRow(1);   
	        HSSFCell c0 = row.createCell(0);   
	        c0.setCellValue(new HSSFRichTextString("与其他国库之间资金流动情况统计表"));
	        HSSFCell c1 = row.createCell(1);   
	        c1.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c2 = row.createCell(2);   
	        c2.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c3 = row.createCell(3);   
	        c3.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c4 = row.createCell(4);   
	        c4.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c5 = row.createCell(5);   
	        c5.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c6 = row.createCell(6);   
	        c6.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c7 = row.createCell(7);   
	        c7.setCellValue(new HSSFRichTextString("")); 
	        
	        HSSFRow row1 = sheet.createRow(2);
	        HSSFCell c8 = row1.createCell(0);   
	        c8.setCellValue(new HSSFRichTextString("填报单位："+(null==map.get("unitName")?"":map.get("unitName").toString()))); 
	        HSSFCell c9 = row1.createCell(1);   
	        c9.setCellValue(new HSSFRichTextString(""));
	        HSSFCell c10 = row1.createCell(2);   
	        c10.setCellValue(new HSSFRichTextString("业务期间："+(null==map.get("dataDate")?"":map.get("dataDate").toString()))); 
	        HSSFCell c11 = row1.createCell(3);   
	        c11.setCellValue(new HSSFRichTextString(""));
	        HSSFCell c12 = row1.createCell(4);   
	        c12.setCellValue(new HSSFRichTextString("单位：万元")); 
	        HSSFCell c13 = row1.createCell(5);   
	        c13.setCellValue(new HSSFRichTextString(""));
	        HSSFCell c14 = row1.createCell(6);   
	        c14.setCellValue(new HSSFRichTextString("")); 
	        HSSFCell c15 = row1.createCell(7);   
	        c15.setCellValue(new HSSFRichTextString(""));
	        
	        
	        HSSFRow row2 = sheet.createRow(3);   
	        HSSFCell c16 = row2.createCell(0);   
	        c16.setCellValue(new HSSFRichTextString("从其他国库流入")); 
	        HSSFCell c17 = row2.createCell(1);   
	        c17.setCellValue(new HSSFRichTextString("小计"));
	        HSSFCell c18 = row2.createCell(2);   
	        c18.setCellValue(new HSSFRichTextString("从总库流入")); 
	        HSSFCell c19 = row2.createCell(3);   
	        c19.setCellValue(new HSSFRichTextString("从省分库流入"));
	        HSSFCell c20 = row2.createCell(4);   
	        c20.setCellValue(new HSSFRichTextString("从中心支库流入")); 
	        HSSFCell c21 = row2.createCell(5);   
	        c21.setCellValue(new HSSFRichTextString("从区（县）支库流入"));
	        HSSFCell c22 = row2.createCell(6);   
	        c22.setCellValue(new HSSFRichTextString("从乡镇金库流入")); 
	        HSSFCell c23 = row2.createCell(7);   
	        c23.setCellValue(new HSSFRichTextString("从同级国库流入"));
	        
	        HSSFRow row3 = sheet.createRow(4);   
	        HSSFCell c24 = row3.createCell(0);   
	        c24.setCellValue(new HSSFRichTextString("划入（或下级国库上划）预算收入")); 
	        HSSFCell c25 = row3.createCell(1);   
	        c25.setCellValue(new HSSFRichTextString(null==map.get("R1")?"0":map.get("R1").toString()));
	        HSSFCell c26 = row3.createCell(2);   
	        c26.setCellValue(new HSSFRichTextString(null==map.get("TE_00119")?"0":map.get("TE_00119").toString())); 
	        HSSFCell c27 = row3.createCell(3);   
	        c27.setCellValue(new HSSFRichTextString(null==map.get("TE_00125")?"0":map.get("TE_00125").toString()));
	        HSSFCell c28 = row3.createCell(4);   
	        c28.setCellValue(new HSSFRichTextString(null==map.get("TE_00133")?"0":map.get("TE_00133").toString())); 
	        HSSFCell c29 = row3.createCell(5);   
	        c29.setCellValue(new HSSFRichTextString(null==map.get("TE_00141")?"0":map.get("TE_00141").toString()));
	        HSSFCell c30 = row3.createCell(6);   
	        c30.setCellValue(new HSSFRichTextString(null==map.get("TE_00149")?"0":map.get("TE_00149").toString())); 
	        HSSFCell c31 = row3.createCell(7);   
	        c31.setCellValue(new HSSFRichTextString("-"));
	        
	        HSSFRow row4 = sheet.createRow(5);   
	        HSSFCell c32 = row4.createCell(0);   
	        c32.setCellValue(new HSSFRichTextString("税收返还流入")); 
	        HSSFCell c33 = row4.createCell(1);   
	        c33.setCellValue(new HSSFRichTextString(null==map.get("R2")?"0":map.get("R2").toString()));
	        HSSFCell c34 = row4.createCell(2);   
	        c34.setCellValue(new HSSFRichTextString(null==map.get("TE_00120")?"0":map.get("TE_00120").toString())); 
	        HSSFCell c35 = row4.createCell(3);   
	        c35.setCellValue(new HSSFRichTextString(null==map.get("TE_00126")?"0":map.get("TE_00126").toString())); 
	        HSSFCell c36 = row4.createCell(4);   
	        c36.setCellValue(new HSSFRichTextString(null==map.get("TE_00134")?"0":map.get("TE_00134").toString())); 
	        HSSFCell c37 = row4.createCell(5);   
	        c37.setCellValue(new HSSFRichTextString(null==map.get("TE_00142")?"0":map.get("TE_00142").toString()));
	        HSSFCell c38 = row4.createCell(6);   
	        c38.setCellValue(new HSSFRichTextString(null==map.get("TE_00150")?"0":map.get("TE_00150").toString())); 
	        HSSFCell c39 = row4.createCell(7);   
	        c39.setCellValue(new HSSFRichTextString("-")); 
	       /* c18.setCellStyle(style); 
	        c19.setCellStyle(style); */
	        
	        HSSFRow row5 = sheet.createRow(6);   
	        HSSFCell c40 = row5.createCell(0);   
	        c40.setCellValue(new HSSFRichTextString("体制上解流入")); 
	        HSSFCell c41 = row5.createCell(1);   
	        c41.setCellValue(new HSSFRichTextString(null==map.get("R3")?"0":map.get("R3").toString()));
	        HSSFCell c42 = row5.createCell(2);   
	        c42.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c43 = row5.createCell(3);   
	        c43.setCellValue(new HSSFRichTextString(null==map.get("TE_00127")?"0":map.get("TE_00127").toString()));
	        HSSFCell c44 = row5.createCell(4);   
	        c44.setCellValue(new HSSFRichTextString(null==map.get("TE_00135")?"0":map.get("TE_00135").toString())); 
	        HSSFCell c45 = row5.createCell(5);   
	        c45.setCellValue(new HSSFRichTextString(null==map.get("TE_00143")?"0":map.get("TE_00143").toString()));
	        HSSFCell c46 = row5.createCell(6);   
	        c46.setCellValue(new HSSFRichTextString(null==map.get("TE_00151")?"0":map.get("TE_00151").toString())); 
	        HSSFCell c47 = row5.createCell(7);   
	        c47.setCellValue(new HSSFRichTextString("-"));
	        
	        HSSFRow row6 = sheet.createRow(7);   
	        HSSFCell c48 = row6.createCell(0);   
	        c48.setCellValue(new HSSFRichTextString("总额分成流入")); 
	        HSSFCell c49 = row6.createCell(1);   
	        c49.setCellValue(new HSSFRichTextString(null==map.get("R4")?"0":map.get("R4").toString()));
	        HSSFCell c50 = row6.createCell(2);   
	        c50.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c51 = row6.createCell(3);   
	        c51.setCellValue(new HSSFRichTextString(null==map.get("TE_00128")?"0":map.get("TE_00128").toString()));
	        HSSFCell c52 = row6.createCell(4);   
	        c52.setCellValue(new HSSFRichTextString(null==map.get("TE_00136")?"0":map.get("TE_00136").toString())); 
	        HSSFCell c53 = row6.createCell(5);   
	        c53.setCellValue(new HSSFRichTextString(null==map.get("TE_00144")?"0":map.get("TE_00144").toString()));
	        HSSFCell c54 = row6.createCell(6);   
	        c54.setCellValue(new HSSFRichTextString(null==map.get("TE_00152")?"0":map.get("TE_00152").toString())); 
	        HSSFCell c55 = row6.createCell(7);   
	        c55.setCellValue(new HSSFRichTextString("-"));
	        
	        HSSFRow row7 = sheet.createRow(8);   
	        HSSFCell c56 = row7.createCell(0);   
	        c56.setCellValue(new HSSFRichTextString("兑付国家债券流入")); 
	        HSSFCell c57 = row7.createCell(1);   
	        c57.setCellValue(new HSSFRichTextString(null==map.get("R5")?"0":map.get("R5").toString()));
	        HSSFCell c58 = row7.createCell(2);   
	        c58.setCellValue(new HSSFRichTextString(null==map.get("TE_00121")?"0":map.get("TE_00121").toString())); 
	        HSSFCell c59 = row7.createCell(3);   
	        c59.setCellValue(new HSSFRichTextString("-"));
	        HSSFCell c60 = row7.createCell(4);   
	        c60.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c61 = row7.createCell(5);   
	        c61.setCellValue(new HSSFRichTextString("-"));
	        HSSFCell c62 = row7.createCell(6);   
	        c62.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c63 = row7.createCell(7);   
	        c63.setCellValue(new HSSFRichTextString("-"));
	        
	        
	        HSSFRow row8 = sheet.createRow(9);   
	        HSSFCell c64 = row8.createCell(0);   
	        c64.setCellValue(new HSSFRichTextString("转贷（或下级国库上划）地方政府债券")); 
	        HSSFCell c65 = row8.createCell(1);   
	        c65.setCellValue(new HSSFRichTextString("-"));
	        HSSFCell c66 = row8.createCell(2);   
	        c66.setCellValue(new HSSFRichTextString(null==map.get("R6")?"0":map.get("R6").toString())); 
	        HSSFCell c67 = row8.createCell(3);   
	        c67.setCellValue(new HSSFRichTextString(null==map.get("TE_00129")?"0":map.get("TE_00129").toString()));
	        HSSFCell c68 = row8.createCell(4);   
	        c68.setCellValue(new HSSFRichTextString(null==map.get("TE_00137")?"0":map.get("TE_00137").toString())); 
	        HSSFCell c69 = row8.createCell(5);   
	        c69.setCellValue(new HSSFRichTextString(null==map.get("TE_00145")?"0":map.get("TE_00145").toString()));
	        HSSFCell c70 = row8.createCell(6);   
	        c70.setCellValue(new HSSFRichTextString(null==map.get("TE_00153")?"0":map.get("TE_00153").toString())); 
	        HSSFCell c71 = row8.createCell(7);   
	        c71.setCellValue(new HSSFRichTextString("-"));
	        
	        
	        HSSFRow row9 = sheet.createRow(10);   
	        HSSFCell c72 = row9.createCell(0);   
	        c72.setCellValue(new HSSFRichTextString("系统内调拨（转移）收入")); 
	        HSSFCell c73 = row9.createCell(1);   
	        c73.setCellValue(new HSSFRichTextString(null==map.get("R7")?"0":map.get("R7").toString()));
	        HSSFCell c74 = row9.createCell(2);   
	        c74.setCellValue(new HSSFRichTextString(null==map.get("TE_00122")?"0":map.get("TE_00122").toString())); 
	        HSSFCell c75 = row9.createCell(3);   
	        c75.setCellValue(new HSSFRichTextString(null==map.get("TE_00130")?"0":map.get("TE_00130").toString()));
	        HSSFCell c76 = row9.createCell(4);   
	        c76.setCellValue(new HSSFRichTextString(null==map.get("TE_00138")?"0":map.get("TE_00138").toString())); 
	        HSSFCell c77 = row9.createCell(5);   
	        c77.setCellValue(new HSSFRichTextString(null==map.get("TE_00146")?"0":map.get("TE_00146").toString()));
	        HSSFCell c78 = row9.createCell(6);   
	        c78.setCellValue(new HSSFRichTextString(null==map.get("TE_00154")?"0":map.get("TE_00154").toString())); 
	        HSSFCell c79 = row9.createCell(7);   
	        c79.setCellValue(new HSSFRichTextString("-"));
	        
	        HSSFRow row10 = sheet.createRow(11);   
	        HSSFCell c80 = row10.createCell(0);   
	        c80.setCellValue(new HSSFRichTextString("划入其他资金")); 
	        HSSFCell c81 = row10.createCell(1);   
	        c81.setCellValue(new HSSFRichTextString(null==map.get("R8")?"0":map.get("R8").toString()));
	        HSSFCell c82 = row10.createCell(2);   
	        c82.setCellValue(new HSSFRichTextString(null==map.get("TE_00123")?"0":map.get("TE_00123").toString())); 
	        HSSFCell c83 = row10.createCell(3);   
	        c83.setCellValue(new HSSFRichTextString(null==map.get("TE_00131")?"0":map.get("TE_00131").toString()));
	        HSSFCell c84 = row10.createCell(4);   
	        c84.setCellValue(new HSSFRichTextString(null==map.get("TE_00139")?"0":map.get("TE_00139").toString())); 
	        HSSFCell c85 = row10.createCell(5);   
	        c85.setCellValue(new HSSFRichTextString(null==map.get("TE_00147")?"0":map.get("TE_00147").toString()));
	        HSSFCell c86 = row10.createCell(6);   
	        c86.setCellValue(new HSSFRichTextString(null==map.get("TE_00155")?"0":map.get("TE_00155").toString())); 
	        HSSFCell c87 = row10.createCell(7);   
	        c87.setCellValue(new HSSFRichTextString("-"));
	        
	        HSSFRow row11 = sheet.createRow(12);   
	        HSSFCell c88 = row11.createCell(0);
	        c88.setCellValue(new HSSFRichTextString("从同级国库流入")); 
	        HSSFCell c89 = row11.createCell(1);   
	        c89.setCellValue(new HSSFRichTextString(null==map.get("R9")?"0":map.get("R9").toString()));
	        HSSFCell c90 = row11.createCell(2);   
	        c90.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c91 = row11.createCell(3);   
	        c91.setCellValue(new HSSFRichTextString("-"));
	        HSSFCell c92 = row11.createCell(4);   
	        c92.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c93 = row11.createCell(5);   
	        c93.setCellValue(new HSSFRichTextString("-"));
	        HSSFCell c94 = row11.createCell(6);   
	        c94.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c95 = row11.createCell(7);   
	        c95.setCellValue(new HSSFRichTextString(null==map.get("TE_00031")?"0":map.get("TE_00031").toString()));
	        
	        HSSFRow row12 = sheet.createRow(13);   
	        HSSFCell c96 = row12.createCell(0);
	        c96.setCellValue(new HSSFRichTextString("流入合计")); 
	        HSSFCell c97 = row12.createCell(1);   
	        c97.setCellValue(new HSSFRichTextString(null==map.get("R10")?"0":map.get("R10").toString()));
	        HSSFCell c98 = row12.createCell(2);   
	        c98.setCellValue(new HSSFRichTextString(null==map.get("TE_00124")?"0":map.get("TE_00124").toString())); 
	        HSSFCell c99 = row12.createCell(3);   
	        c99.setCellValue(new HSSFRichTextString(null==map.get("TE_00132")?"0":map.get("TE_00132").toString()));
	        HSSFCell c100 = row12.createCell(4);   
	        c100.setCellValue(new HSSFRichTextString(null==map.get("TE_00140")?"0":map.get("TE_00140").toString())); 
	        HSSFCell c101 = row12.createCell(5);   
	        c101.setCellValue(new HSSFRichTextString(null==map.get("TE_00148")?"0":map.get("TE_00148").toString()));
	        HSSFCell c102 = row12.createCell(6);   
	        c102.setCellValue(new HSSFRichTextString(null==map.get("TE_00156")?"0":map.get("TE_00156").toString())); 
	        HSSFCell c103 = row12.createCell(7);   
	        c103.setCellValue(new HSSFRichTextString(null==map.get("TE_00031")?"0":map.get("TE_00031").toString()));
	        
	        HSSFRow row13 = sheet.createRow(14);   
	        HSSFCell c104 = row13.createCell(0);   
	        c104.setCellValue(new HSSFRichTextString("流向其他国库")); 
	        HSSFCell c105 = row13.createCell(1);   
	        c105.setCellValue(new HSSFRichTextString("小计"));
	        HSSFCell c106 = row13.createCell(2);   
	        c106.setCellValue(new HSSFRichTextString("流向总库")); 
	        HSSFCell c107 = row13.createCell(3);   
	        c107.setCellValue(new HSSFRichTextString("流向省分库"));
	        HSSFCell c108 = row13.createCell(4);   
	        c108.setCellValue(new HSSFRichTextString("流向中心支库")); 
	        HSSFCell c109 = row13.createCell(5);   
	        c109.setCellValue(new HSSFRichTextString("流向区（县)支库"));
	        HSSFCell c110 = row13.createCell(6);   
	        c110.setCellValue(new HSSFRichTextString("流向乡镇金库")); 
	        HSSFCell c111 = row13.createCell(7);   
	        c111.setCellValue(new HSSFRichTextString("流向同级国库"));
	        
	        HSSFRow row14 = sheet.createRow(15);   
	        HSSFCell c112 = row14.createCell(0);   
	        c112.setCellValue(new HSSFRichTextString("上划（或划出）预算收入")); 
	        HSSFCell c113 = row14.createCell(1);   
	        c113.setCellValue(new HSSFRichTextString(null==map.get("R11")?"0":map.get("R11").toString()));
	        HSSFCell c114 = row14.createCell(2);   
	        c114.setCellValue(new HSSFRichTextString(null==map.get("TE_00157")?"0":map.get("TE_00157").toString())); 
	        HSSFCell c115 = row14.createCell(3);   
	        c115.setCellValue(new HSSFRichTextString(null==map.get("TE_00164")?"0":map.get("TE_00164").toString()));
	        HSSFCell c116 = row14.createCell(4);   
	        c116.setCellValue(new HSSFRichTextString(null==map.get("TE_00173")?"0":map.get("TE_00173").toString())); 
	        HSSFCell c117 = row14.createCell(5);   
	        c117.setCellValue(new HSSFRichTextString(null==map.get("TE_00182")?"0":map.get("TE_00182").toString()));
	        HSSFCell c118 = row14.createCell(6);   
	        c118.setCellValue(new HSSFRichTextString(null==map.get("TE_00191")?"0":map.get("TE_00191").toString())); 
	        HSSFCell c119 = row14.createCell(7);   
	        c119.setCellValue(new HSSFRichTextString("-"));
	        
	        HSSFRow row15 = sheet.createRow(16);   
	        HSSFCell c120 = row15.createCell(0);   
	        c120.setCellValue(new HSSFRichTextString("税收返还流出")); 
	        HSSFCell c121 = row15.createCell(1);   
	        c121.setCellValue(new HSSFRichTextString(null==map.get("R12")?"0":map.get("R12").toString()));
	        HSSFCell c122 = row15.createCell(2);   
	        c122.setCellValue(new HSSFRichTextString(null==map.get("TE_00158")?"0":map.get("TE_00158").toString())); 
	        HSSFCell c123 = row15.createCell(3);   
	        c123.setCellValue(new HSSFRichTextString(null==map.get("TE_00165")?"0":map.get("TE_00165").toString())); 
	        HSSFCell c124 = row15.createCell(4);   
	        c124.setCellValue(new HSSFRichTextString(null==map.get("TE_00174")?"0":map.get("TE_00174").toString())); 
	        HSSFCell c125 = row15.createCell(5);   
	        c125.setCellValue(new HSSFRichTextString(null==map.get("TE_00183")?"0":map.get("TE_00183").toString()));
	        HSSFCell c126 = row15.createCell(6);   
	        c126.setCellValue(new HSSFRichTextString(null==map.get("TE_00192")?"0":map.get("TE_00192").toString())); 
	        HSSFCell c127 = row15.createCell(7);   
	        c127.setCellValue(new HSSFRichTextString("-")); 
	       /* c18.setCellStyle(style); 
	        c19.setCellStyle(style); */
	        
	        HSSFRow row16 = sheet.createRow(17);   
	        HSSFCell c128 = row16.createCell(0);   
	        c128.setCellValue(new HSSFRichTextString("体制上解流出")); 
	        HSSFCell c129 = row16.createCell(1);   
	        c129.setCellValue(new HSSFRichTextString(null==map.get("R13")?"0":map.get("R13").toString()));
	        HSSFCell c130 = row16.createCell(2);   
	        c130.setCellValue(new HSSFRichTextString(null==map.get("TE_00159")?"0":map.get("TE_00159").toString())); 
	        HSSFCell c131 = row16.createCell(3);   
	        c131.setCellValue(new HSSFRichTextString(null==map.get("TE_00166")?"0":map.get("TE_00166").toString()));
	        HSSFCell c132 = row16.createCell(4);   
	        c132.setCellValue(new HSSFRichTextString(null==map.get("TE_00175")?"0":map.get("TE_00175").toString())); 
	        HSSFCell c133 = row16.createCell(5);   
	        c133.setCellValue(new HSSFRichTextString(null==map.get("TE_00184")?"0":map.get("TE_00184").toString()));
	        HSSFCell c134 = row16.createCell(6);   
	        c134.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c135 = row16.createCell(7);   
	        c135.setCellValue(new HSSFRichTextString("-"));
	        
	        HSSFRow row17 = sheet.createRow(18);   
	        HSSFCell c136 = row17.createCell(0);   
	        c136.setCellValue(new HSSFRichTextString("总额分成流出")); 
	        HSSFCell c137 = row17.createCell(1);   
	        c137.setCellValue(new HSSFRichTextString(null==map.get("R14")?"0":map.get("R14").toString()));
	        HSSFCell c138 = row17.createCell(2);   
	        c138.setCellValue(new HSSFRichTextString(null==map.get("TE_00160")?"0":map.get("TE_00160").toString())); 
	        HSSFCell c139 = row17.createCell(3);   
	        c139.setCellValue(new HSSFRichTextString(null==map.get("TE_00167")?"0":map.get("TE_00167").toString()));
	        HSSFCell c140 = row17.createCell(4);   
	        c140.setCellValue(new HSSFRichTextString(null==map.get("TE_00176")?"0":map.get("TE_00176").toString())); 
	        HSSFCell c141 = row17.createCell(5);   
	        c141.setCellValue(new HSSFRichTextString(null==map.get("TE_00185")?"0":map.get("TE_00185").toString()));
	        HSSFCell c142 = row17.createCell(6);   
	        c142.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c143 = row17.createCell(7);   
	        c143.setCellValue(new HSSFRichTextString("-"));
	        
	        HSSFRow row18 = sheet.createRow(19);   
	        HSSFCell c144 = row18.createCell(0);   
	        c144.setCellValue(new HSSFRichTextString("兑付国家债券流出")); 
	        HSSFCell c145= row18.createCell(1);   
	        c145.setCellValue(new HSSFRichTextString(null==map.get("R15")?"0":map.get("R15").toString()));
	        HSSFCell c146 = row18.createCell(2);   
	        c146.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c147 = row18.createCell(3);   
	        c147.setCellValue(new HSSFRichTextString(null==map.get("TE_00168")?"0":map.get("TE_00168").toString()));
	        HSSFCell c148 = row18.createCell(4);   
	        c148.setCellValue(new HSSFRichTextString(null==map.get("TE_00177")?"0":map.get("TE_00177").toString())); 
	        HSSFCell c149 = row18.createCell(5);   
	        c149.setCellValue(new HSSFRichTextString(null==map.get("TE_00186")?"0":map.get("TE_00186").toString()));
	        HSSFCell c150 = row18.createCell(6);   
	        c150.setCellValue(new HSSFRichTextString(null==map.get("TE_00193")?"0":map.get("TE_00193").toString())); 
	        HSSFCell c151 = row18.createCell(7);   
	        c151.setCellValue(new HSSFRichTextString("-"));
	        
	        
	        HSSFRow row19 = sheet.createRow(20);   
	        HSSFCell c152 = row19.createCell(0);   
	        c152.setCellValue(new HSSFRichTextString("上划（或转贷）地方政府债券流出")); 
	        HSSFCell c153 = row19.createCell(1);   
	        c153.setCellValue(new HSSFRichTextString(null==map.get("R16")?"0":map.get("R16").toString()));
	        HSSFCell c154 = row19.createCell(2);   
	        c154.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c155 = row19.createCell(3);   
	        c155.setCellValue(new HSSFRichTextString(null==map.get("TE_00169")?"0":map.get("TE_00169").toString()));
	        HSSFCell c156 = row19.createCell(4);   
	        c156.setCellValue(new HSSFRichTextString(null==map.get("TE_00178")?"0":map.get("TE_00178").toString())); 
	        HSSFCell c157 = row19.createCell(5);   
	        c157.setCellValue(new HSSFRichTextString(null==map.get("TE_00187")?"0":map.get("TE_00187").toString()));
	        HSSFCell c158 = row19.createCell(6);   
	        c158.setCellValue(new HSSFRichTextString(null==map.get("TE_00194")?"0":map.get("TE_00194").toString())); 
	        HSSFCell c159 = row19.createCell(7);   
	        c159.setCellValue(new HSSFRichTextString("-"));
	        
	        
	        HSSFRow row20 = sheet.createRow(21);   
	        HSSFCell c160 = row20.createCell(0);   
	        c160.setCellValue(new HSSFRichTextString("系统内调拨（转移）支出")); 
	        HSSFCell c161 = row20.createCell(1);   
	        c161.setCellValue(new HSSFRichTextString(null==map.get("R17")?"0":map.get("R17").toString()));
	        HSSFCell c162 = row20.createCell(2);   
	        c162.setCellValue(new HSSFRichTextString(null==map.get("TE_00161")?"0":map.get("TE_00161").toString())); 
	        HSSFCell c163 = row20.createCell(3);   
	        c163.setCellValue(new HSSFRichTextString(null==map.get("TE_00170")?"0":map.get("TE_00170").toString()));
	        HSSFCell c164 = row20.createCell(4);   
	        c164.setCellValue(new HSSFRichTextString(null==map.get("TE_00179")?"0":map.get("TE_00179").toString())); 
	        HSSFCell c165 = row20.createCell(5);   
	        c165.setCellValue(new HSSFRichTextString(null==map.get("TE_00188")?"0":map.get("TE_00188").toString()));
	        HSSFCell c166 = row20.createCell(6);   
	        c166.setCellValue(new HSSFRichTextString(null==map.get("TE_00195")?"0":map.get("TE_00195").toString())); 
	        HSSFCell c167 = row20.createCell(7);   
	        c167.setCellValue(new HSSFRichTextString("-"));
	        
	        HSSFRow row21 = sheet.createRow(22);   
	        HSSFCell c168 = row21.createCell(0);   
	        c168.setCellValue(new HSSFRichTextString("划出其他资金")); 
	        HSSFCell c169 = row21.createCell(1);   
	        c169.setCellValue(new HSSFRichTextString(null==map.get("R18")?"0":map.get("R18").toString()));
	        HSSFCell c170 = row21.createCell(2);   
	        c170.setCellValue(new HSSFRichTextString(null==map.get("TE_00162")?"0":map.get("TE_00162").toString())); 
	        HSSFCell c171 = row21.createCell(3);   
	        c171.setCellValue(new HSSFRichTextString(null==map.get("TE_00171")?"0":map.get("TE_00171").toString()));
	        HSSFCell c172 = row21.createCell(4);   
	        c172.setCellValue(new HSSFRichTextString(null==map.get("TE_00180")?"0":map.get("TE_00180").toString())); 
	        HSSFCell c173 = row21.createCell(5);   
	        c173.setCellValue(new HSSFRichTextString(null==map.get("TE_00189")?"0":map.get("TE_00189").toString()));
	        HSSFCell c174 = row21.createCell(6);   
	        c174.setCellValue(new HSSFRichTextString(null==map.get("TE_00196")?"0":map.get("TE_00196").toString())); 
	        HSSFCell c175 = row21.createCell(7);   
	        c175.setCellValue(new HSSFRichTextString("-"));
	        
	        HSSFRow row22 = sheet.createRow(23);   
	        HSSFCell c176 = row22.createCell(0);
	        c176.setCellValue(new HSSFRichTextString("流向同级国库")); 
	        HSSFCell c177 = row22.createCell(1);   
	        c177.setCellValue(new HSSFRichTextString(null==map.get("R19")?"0":map.get("R19").toString()));
	        HSSFCell c178 = row22.createCell(2);   
	        c178.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c179 = row22.createCell(3);   
	        c179.setCellValue(new HSSFRichTextString("-"));
	        HSSFCell c180 = row22.createCell(4);   
	        c180.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c181 = row22.createCell(5);   
	        c181.setCellValue(new HSSFRichTextString("-"));
	        HSSFCell c182 = row22.createCell(6);   
	        c182.setCellValue(new HSSFRichTextString("-")); 
	        HSSFCell c183 = row22.createCell(7);   
	        c183.setCellValue(new HSSFRichTextString(null==map.get("TE_00065")?"0":map.get("TE_00065").toString()));
	        
	        HSSFRow row23 = sheet.createRow(24);   
	        HSSFCell c184 = row23.createCell(0);
	        c184.setCellValue(new HSSFRichTextString("流出合计")); 
	        HSSFCell c185 = row23.createCell(1);   
	        c185.setCellValue(new HSSFRichTextString(null==map.get("R20")?"0":map.get("R20").toString()));
	        HSSFCell c186 = row23.createCell(2);   
	        c186.setCellValue(new HSSFRichTextString(null==map.get("TE_00163")?"0":map.get("TE_00163").toString())); 
	        HSSFCell c187 = row23.createCell(3);   
	        c187.setCellValue(new HSSFRichTextString(null==map.get("TE_00172")?"0":map.get("TE_00172").toString()));
	        HSSFCell c188 = row23.createCell(4);   
	        c188.setCellValue(new HSSFRichTextString(null==map.get("TE_00181")?"0":map.get("TE_00181").toString())); 
	        HSSFCell c189 = row23.createCell(5);   
	        c189.setCellValue(new HSSFRichTextString(null==map.get("TE_00190")?"0":map.get("TE_00190").toString()));
	        HSSFCell c190 = row23.createCell(6);   
	        c190.setCellValue(new HSSFRichTextString(null==map.get("TE_00197")?"0":map.get("TE_00197").toString())); 
	        HSSFCell c191 = row23.createCell(7);   
	        c191.setCellValue(new HSSFRichTextString(null==map.get("TE_00065")?"0":map.get("TE_00065").toString()));
	        
	        HSSFRow row24 = sheet.createRow(25);   
	        HSSFCell c192 = row24.createCell(0);
	        c192.setCellValue(new HSSFRichTextString("净流入（净流出）")); 
	        HSSFCell c193 = row24.createCell(1);   
	        c193.setCellValue(new HSSFRichTextString(null==map.get("R21")?"0":map.get("R21").toString()));
	        HSSFCell c194 = row24.createCell(2);   
	        c194.setCellValue(new HSSFRichTextString(null==map.get("TE_00198")?"0":map.get("TE_00198").toString())); 
	        HSSFCell c195 = row24.createCell(3);   
	        c195.setCellValue(new HSSFRichTextString(null==map.get("TE_00199")?"0":map.get("TE_00199").toString()));
	        HSSFCell c196 = row24.createCell(4);   
	        c196.setCellValue(new HSSFRichTextString(null==map.get("TE_00200")?"0":map.get("TE_00200").toString())); 
	        HSSFCell c197 = row24.createCell(5);   
	        c197.setCellValue(new HSSFRichTextString(null==map.get("TE_00201")?"0":map.get("TE_00201").toString()));
	        HSSFCell c198 = row24.createCell(6);   
	        c198.setCellValue(new HSSFRichTextString(null==map.get("TE_00202")?"0":map.get("TE_00202").toString())); 
	        HSSFCell c199 = row24.createCell(7);   
	        c199.setCellValue(new HSSFRichTextString(null==map.get("TE_00203")?"0":map.get("TE_00203").toString()));
	        
	        //单元格合并 ，每行都进行特殊处理
	        Region region1 = new Region((short)1, (short)0, (short)1, (short)7);   
	        Region region2 = new Region((short)2, (short)0, (short)2, (short)1);   
	        Region region3 = new Region((short)2, (short)2, (short)2, (short)3);
	        Region region4 = new Region((short)2, (short)4, (short)2, (short)7);   
	        
	        
	        
	        
	        sheet.addMergedRegion(region1);   
	        sheet.addMergedRegion(region2);   
	        sheet.addMergedRegion(region3); 
	        sheet.addMergedRegion(region4);   
	       
	        
	        //表格框设置
	        c0.setCellStyle(style1); 
	        c1.setCellStyle(style1); 
	        c2.setCellStyle(style1); 
	        c3.setCellStyle(style1); 
	        c4.setCellStyle(style1); 
	        c5.setCellStyle(style1); 
	        c6.setCellStyle(style1); 
	        c7.setCellStyle(style1); 
	        c8.setCellStyle(style2); 
	        c9.setCellStyle(style2); 
	        c10.setCellStyle(style2); 
	        c11.setCellStyle(style2); 
	        c12.setCellStyle(style3); 
	        c13.setCellStyle(style3); 
	        c14.setCellStyle(style3); 
	        c15.setCellStyle(style3); 
	        c16.setCellStyle(style4); 
	        c17.setCellStyle(style4); 
	        c18.setCellStyle(style4); 
	        c19.setCellStyle(style4); 
	        
	        c20.setCellStyle(style4); 
	        c21.setCellStyle(style4); 
	        c22.setCellStyle(style4); 
	        c23.setCellStyle(style4); 
	        c24.setCellStyle(style); 
	        c25.setCellStyle(style); 
	        c26.setCellStyle(style); 
	        c27.setCellStyle(style); 
	        c28.setCellStyle(style); 
	        c29.setCellStyle(style); 
	        
	        c30.setCellStyle(style); 
	        c31.setCellStyle(style); 
	        c32.setCellStyle(style); 
	        c33.setCellStyle(style); 
	        c34.setCellStyle(style); 
	        c35.setCellStyle(style); 
	        c36.setCellStyle(style); 
	        c37.setCellStyle(style); 
	        c38.setCellStyle(style); 
	        c39.setCellStyle(style); 
	        
	        c40.setCellStyle(style); 
	        c41.setCellStyle(style); 
	        c42.setCellStyle(style); 
	        c43.setCellStyle(style); 
	        c44.setCellStyle(style); 
	        c45.setCellStyle(style); 
	        c46.setCellStyle(style); 
	        c47.setCellStyle(style); 
	        c48.setCellStyle(style); 
	        c49.setCellStyle(style); 
	        
	        c50.setCellStyle(style); 
	        c51.setCellStyle(style); 
	        c52.setCellStyle(style); 
	        c53.setCellStyle(style); 
	        c54.setCellStyle(style); 
	        c55.setCellStyle(style); 
	        c56.setCellStyle(style); 
	        c57.setCellStyle(style); 
	        c58.setCellStyle(style); 
	        c59.setCellStyle(style); 
	        
	        c60.setCellStyle(style); 
	        c61.setCellStyle(style); 
	        c62.setCellStyle(style); 
	        c63.setCellStyle(style); 
	        c64.setCellStyle(style); 
	        c65.setCellStyle(style); 
	        c66.setCellStyle(style); 
	        c67.setCellStyle(style); 
	        c68.setCellStyle(style); 
	        c69.setCellStyle(style); 
	        
	        c70.setCellStyle(style); 
	        c71.setCellStyle(style); 
	        c72.setCellStyle(style); 
	        c73.setCellStyle(style); 
	        c74.setCellStyle(style); 
	        c75.setCellStyle(style); 
	        c76.setCellStyle(style); 
	        c77.setCellStyle(style); 
	        c78.setCellStyle(style); 
	        c79.setCellStyle(style); 
	        
	        c80.setCellStyle(style); 
	        c81.setCellStyle(style); 
	        c82.setCellStyle(style); 
	        c83.setCellStyle(style); 
	        c84.setCellStyle(style); 
	        c85.setCellStyle(style); 
	        c86.setCellStyle(style); 
	        c87.setCellStyle(style); 
	        c88.setCellStyle(style); 
	        c89.setCellStyle(style); 
	        
	        c90.setCellStyle(style); 
	        c91.setCellStyle(style); 
	        c92.setCellStyle(style); 
	        c93.setCellStyle(style); 
	        c94.setCellStyle(style); 
	        c95.setCellStyle(style); 
	        c96.setCellStyle(style); 
	        c97.setCellStyle(style); 
	        c98.setCellStyle(style); 
	        c99.setCellStyle(style); 
	        
	        c100.setCellStyle(style); 
	        c101.setCellStyle(style); 
	        c102.setCellStyle(style); 
	        c103.setCellStyle(style); 
	        c104.setCellStyle(style4); 
	        c105.setCellStyle(style4); 
	        c106.setCellStyle(style4); 
	        c107.setCellStyle(style4); 
	        c108.setCellStyle(style4); 
	        c109.setCellStyle(style4); 
	        
	        c110.setCellStyle(style4); 
	        c111.setCellStyle(style4); 
	        c112.setCellStyle(style); 
	        c113.setCellStyle(style); 
	        c114.setCellStyle(style); 
	        c115.setCellStyle(style); 
	        c116.setCellStyle(style); 
	        c117.setCellStyle(style); 
	        c118.setCellStyle(style); 
	        c119.setCellStyle(style); 
	        
	        c120.setCellStyle(style); 
	        c121.setCellStyle(style); 
	        c122.setCellStyle(style); 
	        c123.setCellStyle(style); 
	        c124.setCellStyle(style); 
	        c125.setCellStyle(style); 
	        c126.setCellStyle(style); 
	        c127.setCellStyle(style); 
	        c128.setCellStyle(style); 
	        c129.setCellStyle(style); 
	        
	        c130.setCellStyle(style); 
	        c131.setCellStyle(style); 
	        c132.setCellStyle(style); 
	        c133.setCellStyle(style); 
	        c134.setCellStyle(style); 
	        c135.setCellStyle(style); 
	        c136.setCellStyle(style); 
	        c137.setCellStyle(style); 
	        c138.setCellStyle(style); 
	        c139.setCellStyle(style); 
	        
	        c140.setCellStyle(style); 
	        c141.setCellStyle(style); 
	        c142.setCellStyle(style); 
	        c143.setCellStyle(style); 
	        c144.setCellStyle(style); 
	        c145.setCellStyle(style); 
	        c146.setCellStyle(style); 
	        c147.setCellStyle(style); 
	        c148.setCellStyle(style); 
	        c149.setCellStyle(style); 
	        
	        c150.setCellStyle(style); 
	        c151.setCellStyle(style); 
	        c152.setCellStyle(style); 
	        c153.setCellStyle(style); 
	        c154.setCellStyle(style); 
	        c155.setCellStyle(style); 
	        c156.setCellStyle(style); 
	        c157.setCellStyle(style); 
	        c158.setCellStyle(style); 
	        c159.setCellStyle(style); 
	        
	        c160.setCellStyle(style); 
	        c161.setCellStyle(style); 
	        c162.setCellStyle(style); 
	        c163.setCellStyle(style); 
	        c164.setCellStyle(style); 
	        c165.setCellStyle(style); 
	        c166.setCellStyle(style); 
	        c167.setCellStyle(style); 
	        c168.setCellStyle(style); 
	        c169.setCellStyle(style); 
	        
	        c170.setCellStyle(style); 
	        c171.setCellStyle(style); 
	        c172.setCellStyle(style); 
	        c173.setCellStyle(style); 
	        c174.setCellStyle(style); 
	        c175.setCellStyle(style); 
	        c176.setCellStyle(style); 
	        c177.setCellStyle(style); 
	        c178.setCellStyle(style); 
	        c179.setCellStyle(style); 
	        
	        
	        c180.setCellStyle(style); 
	        c181.setCellStyle(style); 
	        c182.setCellStyle(style); 
	        c183.setCellStyle(style); 
	        c184.setCellStyle(style); 
	        c185.setCellStyle(style); 
	        c186.setCellStyle(style); 
	        c187.setCellStyle(style); 
	        c188.setCellStyle(style); 
	        c189.setCellStyle(style); 
	        
	        c190.setCellStyle(style); 
	        c191.setCellStyle(style); 
	        c192.setCellStyle(style); 
	        c193.setCellStyle(style); 
	        c194.setCellStyle(style); 
	        c195.setCellStyle(style); 
	        c196.setCellStyle(style); 
	        c197.setCellStyle(style); 
	        c198.setCellStyle(style); 
	        c199.setCellStyle(style); 
	        
	        
	       
	        
	       /* for(int index=17;index<32;index++){
	        	 Region regionX = new Region((short)index, (short)0, (short)index, (short)1);   
	 	         Region regionY = new Region((short)index, (short)2, (short)index, (short)3);
	 	        sheet.addMergedRegion(regionX);
	 	       sheet.addMergedRegion(regionY);
	 	        
	        }*/
	        
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
