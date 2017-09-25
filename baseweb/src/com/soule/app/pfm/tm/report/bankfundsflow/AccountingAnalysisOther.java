package com.soule.app.pfm.tm.report.bankfundsflow;

import java.io.ByteArrayOutputStream;
import java.util.Map;


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

public class AccountingAnalysisOther {
	
	public static void Data2Excel(Map<String,Object> map,ByteArrayOutputStream fout){
		HSSFWorkbook workbook = new HSSFWorkbook();
		//创建sheet页  
        HSSFSheet sheet = workbook.createSheet("国库会计分析其他数据统计表");  
        
        sheet.setColumnWidth(0, 30*256);
        sheet.setColumnWidth(1, 30*256);
        sheet.setColumnWidth(2, 20*256);
        sheet.setColumnWidth(3, 20*256);
        
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

        //第二行 表格样式
        style2.setBorderBottom(CellStyle.BORDER_MEDIUM); 
        style2.setBorderLeft(CellStyle.BORDER_MEDIUM);  
        style2.setBorderRight(CellStyle.BORDER_MEDIUM);  
        style2.setBorderTop(CellStyle.BORDER_MEDIUM);  
        style2.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
        style2.setFillPattern(CellStyle.SOLID_FOREGROUND);
        
        //第三行表格样式
        style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style3.setBorderBottom(CellStyle.BORDER_MEDIUM);
        style3.setBorderLeft(CellStyle.BORDER_MEDIUM);  
        style3.setBorderRight(CellStyle.BORDER_MEDIUM);  
        style3.setBorderTop(CellStyle.BORDER_MEDIUM);  
        style3.setFillForegroundColor(IndexedColors.LIGHT_TURQUOISE.getIndex());
        style3.setFillPattern(CellStyle.SOLID_FOREGROUND);
        //合并单元格样式
        
        style4.setBorderBottom(CellStyle.BORDER_MEDIUM); 
        style4.setBorderLeft(CellStyle.BORDER_MEDIUM);  
        style4.setBorderRight(CellStyle.BORDER_MEDIUM);  
        style4.setBorderTop(CellStyle.BORDER_MEDIUM);  
        style4.setVerticalAlignment(XSSFCellStyle.VERTICAL_CENTER);//垂直  
        
        //创建单元格  
        HSSFRow row = sheet.createRow(1);   
        HSSFCell c0 = row.createCell(0);   
        c0.setCellValue(new HSSFRichTextString("国库会计分析其他数据统计表"));
        HSSFCell c1 = row.createCell(1);   
        c1.setCellValue(new HSSFRichTextString("")); 
        HSSFCell c2 = row.createCell(2);   
        c2.setCellValue(new HSSFRichTextString("")); 
        HSSFCell c3 = row.createCell(3);   
        c3.setCellValue(new HSSFRichTextString("")); 
        
        HSSFRow row1 = sheet.createRow(2);
        HSSFCell c4 = row1.createCell(0);   
        c4.setCellValue(new HSSFRichTextString("填报单位："+(null==map.get("unitName")?"":map.get("unitName").toString()))); 
        HSSFCell c5 = row1.createCell(1);   
        c5.setCellValue(new HSSFRichTextString(""));
        HSSFCell c6 = row1.createCell(2);   
        c6.setCellValue(new HSSFRichTextString("业务期间："+(null==map.get("dataDate")?"":map.get("dataDate").toString()))); 
        HSSFCell c7 = row1.createCell(3);   
        c7.setCellValue(new HSSFRichTextString(""));
        
        
        HSSFRow row2 = sheet.createRow(3);   
        HSSFCell c8 = row2.createCell(0);   
        c8.setCellValue(new HSSFRichTextString("指标")); 
        HSSFCell c9 = row2.createCell(1);   
        c9.setCellValue(new HSSFRichTextString(""));
        HSSFCell c10 = row2.createCell(2);   
        c10.setCellValue(new HSSFRichTextString("数据")); 
        HSSFCell c11 = row2.createCell(3);   
        c11.setCellValue(new HSSFRichTextString(""));
        
        HSSFRow row3 = sheet.createRow(4);   
        HSSFCell c12 = row3.createCell(0);   
        c12.setCellValue(new HSSFRichTextString("国库库存余额（万元）")); 
        HSSFCell c13 = row3.createCell(1);   
        c13.setCellValue(new HSSFRichTextString(""));
        HSSFCell c14 = row3.createCell(2);   
        c14.setCellValue(new HSSFRichTextString(null==map.get("TE_00069")?"0":map.get("TE_00069").toString())); 
        HSSFCell c15 = row3.createCell(3);   
        c15.setCellValue(new HSSFRichTextString(""));
        
        HSSFRow row4 = sheet.createRow(5);   
        HSSFCell c16 = row4.createCell(0);   
        c16.setCellValue(new HSSFRichTextString("制度适应性指标（份）")); 
        HSSFCell c17 = row4.createCell(1);   
        c17.setCellValue(new HSSFRichTextString(""));
        HSSFCell c18 = row4.createCell(2);   
        c18.setCellValue(new HSSFRichTextString(null==map.get("TE_00070")?"0":map.get("TE_00070").toString())); 
        HSSFCell c19 = row4.createCell(3);   
        c19.setCellValue(new HSSFRichTextString("")); 
        c18.setCellStyle(style); 
        c19.setCellStyle(style); 
        
        HSSFRow row5 = sheet.createRow(6);   
        HSSFCell c20 = row5.createCell(0);   
        c20.setCellValue(new HSSFRichTextString("检查情况指标（次）")); 
        HSSFCell c21 = row5.createCell(1);   
        c21.setCellValue(new HSSFRichTextString(""));
        HSSFCell c22 = row5.createCell(2);   
        c22.setCellValue(new HSSFRichTextString(null==map.get("TE_00071")?"0":map.get("TE_00071").toString())); 
        HSSFCell c23 = row5.createCell(3);   
        c23.setCellValue(new HSSFRichTextString(""));
        
        HSSFRow row6 = sheet.createRow(7);   
        HSSFCell c24 = row6.createCell(0);   
        c24.setCellValue(new HSSFRichTextString("业务差错总量")); 
        HSSFCell c25 = row6.createCell(1);   
        c25.setCellValue(new HSSFRichTextString(""));
        HSSFCell c26 = row6.createCell(2);   
        c26.setCellValue(new HSSFRichTextString(null==map.get("TE_00072")?"0":map.get("TE_00072").toString())); 
        HSSFCell c27 = row6.createCell(3);   
        c27.setCellValue(new HSSFRichTextString(""));
        
        HSSFRow row7 = sheet.createRow(8);   
        HSSFCell c28 = row7.createCell(0);   
        c28.setCellValue(new HSSFRichTextString("其中：内部业务差错量")); 
        HSSFCell c29 = row7.createCell(1);   
        c29.setCellValue(new HSSFRichTextString(""));
        HSSFCell c30 = row7.createCell(2);   
        c30.setCellValue(new HSSFRichTextString(null==map.get("TE_00073")?"0":map.get("TE_00073").toString())); 
        HSSFCell c31 = row7.createCell(3);   
        c31.setCellValue(new HSSFRichTextString(""));
        
        
        HSSFRow row8 = sheet.createRow(9);   
        HSSFCell c32 = row8.createCell(0);   
        c32.setCellValue(new HSSFRichTextString("外部业务差错量")); 
        HSSFCell c33 = row8.createCell(1);   
        c33.setCellValue(new HSSFRichTextString(""));
        HSSFCell c34 = row8.createCell(2);   
        c34.setCellValue(new HSSFRichTextString(null==map.get("TE_00074")?"0":map.get("TE_00074").toString()));
        HSSFCell c35 = row8.createCell(3);   
        c35.setCellValue(new HSSFRichTextString(""));
        
        
         HSSFRow row9 = sheet.createRow(10);   
        HSSFCell c36 = row9.createCell(0);   
        c36.setCellValue(new HSSFRichTextString("系统运行指标")); 
        HSSFCell c37 = row9.createCell(1);   
        c37.setCellValue(new HSSFRichTextString("业务系统故障次数（次）")); 
        HSSFCell c38 = row9.createCell(2);   
        c38.setCellValue(new HSSFRichTextString(null==map.get("TE_00075")?"0":map.get("TE_00075").toString()));
        HSSFCell c39 = row9.createCell(3);   
        c39.setCellValue(new HSSFRichTextString(""));
        
        HSSFRow row10 = sheet.createRow(11);   
        HSSFCell c40 = row10.createCell(0);   
        c40.setCellValue(new HSSFRichTextString("系统运行指标")); 
        HSSFCell c41 = row10.createCell(1);   
        c41.setCellValue(new HSSFRichTextString("业务系统改造次数（次）")); 
        HSSFCell c42 = row10.createCell(2);   
        c42.setCellValue(new HSSFRichTextString(null==map.get("TE_00076")?"0":map.get("TE_00076").toString()));
        HSSFCell c43 = row10.createCell(3);   
        c43.setCellValue(new HSSFRichTextString(""));
        
        HSSFRow row11 = sheet.createRow(12);   
        HSSFCell c44 = row11.createCell(0);   
        c44.setCellValue(new HSSFRichTextString("人员指标")); 
        HSSFCell c45 = row11.createCell(1);   
        c45.setCellValue(new HSSFRichTextString("国库会计人员总数）")); 
        HSSFCell c46 = row11.createCell(2);   
        c46.setCellValue(new HSSFRichTextString(null==map.get("TE_00077")?"0":map.get("TE_00077").toString()));
        HSSFCell c47 = row11.createCell(3);   
        c47.setCellValue(new HSSFRichTextString(""));
        
        HSSFRow row12 = sheet.createRow(13);   
        HSSFCell c48 = row12.createCell(0);   
        c48.setCellValue(new HSSFRichTextString("人员指标")); 
        HSSFCell c49 = row12.createCell(1);   
        c49.setCellValue(new HSSFRichTextString("国库聘用人员数量")); 
        HSSFCell c50 = row12.createCell(2);   
        c50.setCellValue(new HSSFRichTextString(null==map.get("TE_00078")?"0":map.get("TE_00078").toString()));
        HSSFCell c51 = row12.createCell(3);   
        c51.setCellValue(new HSSFRichTextString(""));
        
        HSSFRow row13 = sheet.createRow(14);   
        HSSFCell c52 = row13.createCell(0);   
        c52.setCellValue(new HSSFRichTextString("人员指标")); 
        HSSFCell c53 = row13.createCell(1);   
        c53.setCellValue(new HSSFRichTextString("国库会计人员培训人次")); 
        HSSFCell c54 = row13.createCell(2);   
        c54.setCellValue(new HSSFRichTextString(null==map.get("TE_00079")?"0":map.get("TE_00079").toString()));
        HSSFCell c55 = row13.createCell(3);   
        c55.setCellValue(new HSSFRichTextString(""));
        
        HSSFRow row14 = sheet.createRow(15);   
        HSSFCell c56 = row14.createCell(0);   
        c56.setCellValue(new HSSFRichTextString("人员指标")); 
        HSSFCell c57 = row14.createCell(1);   
        c57.setCellValue(new HSSFRichTextString("国库会计人员轮岗人次")); 
        HSSFCell c58 = row14.createCell(2);   
        c58.setCellValue(new HSSFRichTextString(null==map.get("TE_00080")?"0":map.get("TE_00080").toString()));
        HSSFCell c59 = row14.createCell(3);   
        c59.setCellValue(new HSSFRichTextString(""));
        
        HSSFRow row15 = sheet.createRow(16);   
        HSSFCell c60 = row15.createCell(0);   
        c60.setCellValue(new HSSFRichTextString("人员指标")); 
        HSSFCell c61 = row15.createCell(1);   
        c61.setCellValue(new HSSFRichTextString("国库重要岗位人员强制休假人次")); 
        HSSFCell c62 = row15.createCell(2);   
        c62.setCellValue(new HSSFRichTextString(null==map.get("TE_00081")?"0":map.get("TE_00081").toString()));
        HSSFCell c121 = row15.createCell(3);   
        c121.setCellValue(new HSSFRichTextString(""));
        
        HSSFRow row16 = sheet.createRow(17);   
        HSSFCell c63 = row16.createCell(0);   
        c63.setCellValue(new HSSFRichTextString("业务总量")); 
        HSSFCell c64 = row16.createCell(1);   
        c64.setCellValue(new HSSFRichTextString(""));
        HSSFCell c65 = row16.createCell(2);   
        c65.setCellValue(new HSSFRichTextString(null==map.get("TE_00089")?"0":map.get("TE_00089").toString())); 
        HSSFCell c66 = row16.createCell(3);   
        c66.setCellValue(new HSSFRichTextString(""));
        
        HSSFRow row17 = sheet.createRow(18);   
        HSSFCell c67 = row17.createCell(0);   
        c67.setCellValue(new HSSFRichTextString("其中：预算收入业务量")); 
        HSSFCell c68 = row17.createCell(1);   
        c68.setCellValue(new HSSFRichTextString(""));
        HSSFCell c69 = row17.createCell(2);   
        c69.setCellValue(new HSSFRichTextString(null==map.get("TE_00090")?"0":map.get("TE_00090").toString())); 
        HSSFCell c70 = row17.createCell(3);   
        c70.setCellValue(new HSSFRichTextString(""));
        
        
        HSSFRow row18 = sheet.createRow(19);   
        HSSFCell c71 = row18.createCell(0);   
        c71.setCellValue(new HSSFRichTextString("——预算收入电子化业务量")); 
        HSSFCell c72 = row18.createCell(1);   
        c72.setCellValue(new HSSFRichTextString(""));
        HSSFCell c73 = row18.createCell(2);   
        c73.setCellValue(new HSSFRichTextString(null==map.get("TE_00097")?"0":map.get("TE_00097").toString())); 
        HSSFCell c74 = row18.createCell(3);   
        c74.setCellValue(new HSSFRichTextString(""));
        
        HSSFRow row19 = sheet.createRow(20);   
        HSSFCell c75 = row19.createCell(0);   
        c75.setCellValue(new HSSFRichTextString("商业银行代理的直接支付业务量")); 
        HSSFCell c76 = row19.createCell(1);   
        c76.setCellValue(new HSSFRichTextString(""));
        HSSFCell c77 = row19.createCell(2);   
        c77.setCellValue(new HSSFRichTextString(null==map.get("TE_00091")?"0":map.get("TE_00091").toString())); 
        HSSFCell c78 = row19.createCell(3);   
        c78.setCellValue(new HSSFRichTextString(""));
        
          HSSFRow row20 = sheet.createRow(21);   
        HSSFCell c79 = row20.createCell(0);   
        c79.setCellValue(new HSSFRichTextString("——商业银行代理的直接支付电子化业务量")); 
        HSSFCell c80 = row20.createCell(1);   
        c80.setCellValue(new HSSFRichTextString(""));
        HSSFCell c81 = row20.createCell(2);   
        c81.setCellValue(new HSSFRichTextString(null==map.get("TE_00098")?"0":map.get("TE_00098").toString())); 
        HSSFCell c82 = row20.createCell(3);   
        c82.setCellValue(new HSSFRichTextString(""));
        
        
        HSSFRow row21 = sheet.createRow(22);   
        HSSFCell c83 = row21.createCell(0);   
        c83.setCellValue(new HSSFRichTextString("商业银行代理的授权支付业务量")); 
        HSSFCell c84 = row21.createCell(1);   
        c84.setCellValue(new HSSFRichTextString(""));
        HSSFCell c85 = row21.createCell(2);   
        c85.setCellValue(new HSSFRichTextString(null==map.get("TE_00092")?"0":map.get("TE_00092").toString())); 
        HSSFCell c86 = row21.createCell(3);   
        c86.setCellValue(new HSSFRichTextString(""));
        
         HSSFRow row22 = sheet.createRow(23);   
        HSSFCell c87 = row22.createCell(0);   
        c87.setCellValue(new HSSFRichTextString("——商业银行代理的授权支付电子化业务量")); 
        HSSFCell c122 = row22.createCell(1);   
        c122.setCellValue(new HSSFRichTextString(""));
        HSSFCell c88 = row22.createCell(2);   
        c88.setCellValue(new HSSFRichTextString(null==map.get("TE_00099")?"0":map.get("TE_00099").toString())); 
        HSSFCell c123 = row22.createCell(3);   
        c123.setCellValue(new HSSFRichTextString(""));
        
        HSSFRow row23 = sheet.createRow(24);   
        HSSFCell c89 = row23.createCell(0);   
        c89.setCellValue(new HSSFRichTextString("人行直接办理的集中支付业务量")); 
        HSSFCell c90 = row23.createCell(1);   
        c90.setCellValue(new HSSFRichTextString(""));
        HSSFCell c91 = row23.createCell(2);   
        c91.setCellValue(new HSSFRichTextString(null==map.get("TE_00093")?"0":map.get("TE_00093").toString())); 
        HSSFCell c92 = row23.createCell(3);   
        c92.setCellValue(new HSSFRichTextString(""));
        
        
        HSSFRow row24 = sheet.createRow(25);   
        HSSFCell c93 = row24.createCell(0);   
        c93.setCellValue(new HSSFRichTextString("——人行直接办理的集中支付电子化业务量")); 
        HSSFCell c94 = row24.createCell(1);   
        c94.setCellValue(new HSSFRichTextString(""));
        HSSFCell c95 = row24.createCell(2);   
        c95.setCellValue(new HSSFRichTextString(null==map.get("TE_00100")?"0":map.get("TE_00100").toString())); 
        HSSFCell c96 = row24.createCell(3);   
        c96.setCellValue(new HSSFRichTextString(""));
        
        HSSFRow row25 = sheet.createRow(26);   
        HSSFCell c97 = row25.createCell(0);   
        c97.setCellValue(new HSSFRichTextString("实拨资金业务量")); 
        HSSFCell c98 = row25.createCell(1);   
        c98.setCellValue(new HSSFRichTextString(""));
        HSSFCell c99 = row25.createCell(2);   
        c99.setCellValue(new HSSFRichTextString(null==map.get("TE_00094")?"0":map.get("TE_00094").toString())); 
        HSSFCell c100 = row25.createCell(3);   
        c100.setCellValue(new HSSFRichTextString(""));
        
        HSSFRow row26 = sheet.createRow(27);   
        HSSFCell c101 = row26.createCell(0);   
        c101.setCellValue(new HSSFRichTextString("——实拨资金电子化业务量")); 
        HSSFCell c102 = row26.createCell(1);   
        c102.setCellValue(new HSSFRichTextString(""));
        HSSFCell c103 = row26.createCell(2);   
        c103.setCellValue(new HSSFRichTextString(null==map.get("TE_00101")?"0":map.get("TE_00101").toString())); 
        HSSFCell c104 = row26.createCell(3);   
        c104.setCellValue(new HSSFRichTextString(""));
        
        HSSFRow row27 = sheet.createRow(28);   
        HSSFCell c105 = row27.createCell(0);   
        c105.setCellValue(new HSSFRichTextString("其中：国库直接支付业务量")); 
        HSSFCell c106 = row27.createCell(1);   
        c106.setCellValue(new HSSFRichTextString(""));
        HSSFCell c107 = row27.createCell(2);   
        c107.setCellValue(new HSSFRichTextString(null==map.get("TE_00095")?"0":map.get("TE_00095").toString())); 
        HSSFCell c108 = row27.createCell(3);   
        c108.setCellValue(new HSSFRichTextString(""));
        
        
        HSSFRow row28 = sheet.createRow(29);   
        HSSFCell c109 = row28.createCell(0);   
        c109.setCellValue(new HSSFRichTextString("——国库直接支付电子化业务量")); 
        HSSFCell c110 = row28.createCell(1);   
        c110.setCellValue(new HSSFRichTextString(""));
        HSSFCell c111 = row28.createCell(2);   
        c111.setCellValue(new HSSFRichTextString(null==map.get("TE_00102")?"0":map.get("TE_00102").toString())); 
        HSSFCell c112 = row28.createCell(3);   
        c112.setCellValue(new HSSFRichTextString(""));
        
        
        HSSFRow row29 = sheet.createRow(30);   
        HSSFCell c113 = row29.createCell(0);   
        c113.setCellValue(new HSSFRichTextString("退库业务量")); 
        HSSFCell c114 = row29.createCell(1);   
        c114.setCellValue(new HSSFRichTextString(""));
        HSSFCell c115 = row29.createCell(2);   
        c115.setCellValue(new HSSFRichTextString(null==map.get("TE_00096")?"0":map.get("TE_00096").toString())); 
        HSSFCell c116 = row29.createCell(3);   
        c116.setCellValue(new HSSFRichTextString(""));
        
        HSSFRow row30 = sheet.createRow(31);   
        HSSFCell c117 = row30.createCell(0);   
        c117.setCellValue(new HSSFRichTextString("——退库电子化业务量"));
        HSSFCell c118 = row30.createCell(1);   
        c118.setCellValue(new HSSFRichTextString(""));
        HSSFCell c119 = row30.createCell(2);   
        c119.setCellValue(new HSSFRichTextString(null==map.get("TE_00103")?"0":map.get("TE_00103").toString())); 
        HSSFCell c120 = row30.createCell(3);   
        c120.setCellValue(new HSSFRichTextString(""));
        
        //单元格合并 ，每行都进行特殊处理
        Region region1 = new Region((short)1, (short)0, (short)1, (short)3);   
        Region region2 = new Region((short)2, (short)0, (short)2, (short)1);   
        Region region3 = new Region((short)2, (short)2, (short)2, (short)3);
        Region region4 = new Region((short)3, (short)0, (short)3, (short)1);   
        Region region5 = new Region((short)3, (short)2, (short)3, (short)3);
        Region region6 = new Region((short)4, (short)0, (short)4, (short)1);   
        Region region7 = new Region((short)4, (short)2, (short)4, (short)3);
        Region region8 = new Region((short)5, (short)0, (short)5, (short)1);   
        Region region9 = new Region((short)5, (short)2, (short)5, (short)3);
        Region region10 = new Region((short)6, (short)0, (short)6, (short)1);   
        Region region11 = new Region((short)6, (short)2, (short)6, (short)3);
        Region region12 = new Region((short)7, (short)0, (short)7, (short)1);   
        Region region13 = new Region((short)7, (short)2, (short)7, (short)3);
        Region region14 = new Region((short)8, (short)0, (short)8, (short)1);   
        Region region15 = new Region((short)8, (short)2, (short)8, (short)3);
        Region region16 = new Region((short)9, (short)0, (short)9, (short)1);   
        Region region17 = new Region((short)9, (short)2, (short)9, (short)3);
        Region region18 = new Region((short)10, (short)0, (short)11, (short)0);
        Region region19 = new Region((short)10, (short)2, (short)10, (short)3);
        Region region20 = new Region((short)11, (short)2, (short)11, (short)3);
        
        Region region21 = new Region((short)12, (short)0, (short)16, (short)0);
        Region region22 = new Region((short)12, (short)2, (short)12, (short)3);
        Region region23 = new Region((short)13, (short)2, (short)13, (short)3);
        Region region24 = new Region((short)14, (short)2, (short)14, (short)3);
        Region region25 = new Region((short)15, (short)2, (short)15, (short)3);
        Region region26 = new Region((short)16, (short)2, (short)16, (short)3);
        
        
        
        
        
        sheet.addMergedRegion(region1);   
        sheet.addMergedRegion(region2);   
        sheet.addMergedRegion(region3); 
        sheet.addMergedRegion(region4);   
        sheet.addMergedRegion(region5);
        sheet.addMergedRegion(region6);   
        sheet.addMergedRegion(region7);   
        sheet.addMergedRegion(region8); 
        sheet.addMergedRegion(region9);   
        sheet.addMergedRegion(region10);
        sheet.addMergedRegion(region11);   
        sheet.addMergedRegion(region12);   
        sheet.addMergedRegion(region13); 
        sheet.addMergedRegion(region14);   
        sheet.addMergedRegion(region15);
        sheet.addMergedRegion(region16);   
        sheet.addMergedRegion(region17); 
        sheet.addMergedRegion(region18); 
        sheet.addMergedRegion(region19); 
        sheet.addMergedRegion(region20);
        sheet.addMergedRegion(region21);   
        sheet.addMergedRegion(region22);   
        sheet.addMergedRegion(region23); 
        sheet.addMergedRegion(region24);   
        sheet.addMergedRegion(region25);
        sheet.addMergedRegion(region26); 
        
        //表格框设置
        c0.setCellStyle(style1); 
        c1.setCellStyle(style1); 
        c2.setCellStyle(style1); 
        c3.setCellStyle(style1); 
        c4.setCellStyle(style2); 
        c5.setCellStyle(style2); 
        c6.setCellStyle(style2); 
        c7.setCellStyle(style2); 
        c8.setCellStyle(style3); 
        c9.setCellStyle(style3); 
        
        c10.setCellStyle(style3); 
        c11.setCellStyle(style3); 
        c12.setCellStyle(style); 
        c13.setCellStyle(style); 
        c14.setCellStyle(style); 
        c15.setCellStyle(style); 
        c16.setCellStyle(style); 
        c17.setCellStyle(style); 
        c18.setCellStyle(style); 
        c19.setCellStyle(style); 
        
        c20.setCellStyle(style); 
        c21.setCellStyle(style); 
        c22.setCellStyle(style); 
        c23.setCellStyle(style); 
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
        c36.setCellStyle(style4); 
        c37.setCellStyle(style4); 
        c38.setCellStyle(style); 
        c39.setCellStyle(style); 
        
        c40.setCellStyle(style4); 
        c41.setCellStyle(style4); 
        c42.setCellStyle(style); 
        c43.setCellStyle(style); 
        c44.setCellStyle(style4); 
        c45.setCellStyle(style4); 
        c46.setCellStyle(style); 
        c47.setCellStyle(style); 
        c48.setCellStyle(style4); 
        c49.setCellStyle(style4); 
        
        c50.setCellStyle(style); 
        c51.setCellStyle(style); 
        c52.setCellStyle(style4); 
        c53.setCellStyle(style4); 
        c54.setCellStyle(style); 
        c55.setCellStyle(style); 
        c56.setCellStyle(style4); 
        c57.setCellStyle(style4); 
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
        c104.setCellStyle(style); 
        c105.setCellStyle(style); 
        c106.setCellStyle(style); 
        c107.setCellStyle(style); 
        c108.setCellStyle(style); 
        c109.setCellStyle(style); 
        
        c110.setCellStyle(style); 
        c111.setCellStyle(style); 
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
       
        
        for(int index=17;index<32;index++){
        	 Region regionX = new Region((short)index, (short)0, (short)index, (short)1);   
 	         Region regionY = new Region((short)index, (short)2, (short)index, (short)3);
 	        sheet.addMergedRegion(regionX);
 	       sheet.addMergedRegion(regionY);
 	        
        }
        
        try  
        {  
           // FileOutputStream fout = new FileOutputStream("E:/student/students.xls");  
            workbook.write(fout);  
        }  
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }  
		
	}

}
