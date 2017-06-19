package com.soule.crm.tools;

import java.beans.PropertyDescriptor;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.read.biff.BiffException;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.collections.map.ListOrderedMap;
import org.apache.log4j.Logger;

import com.soule.app.table.SysUploadFilePo;
import com.soule.base.po.EnumItem;
import com.soule.base.po.EnumType;
import com.soule.comm.ObjectUtil;
import com.soule.comm.po.IEnumItem;
import com.soule.comm.tools.EnumTypeUtil;
import com.soule.comm.tools.StringUtil;
import com.soule.comm.xls.config.XlsPoint;

/*import com.neusoft.app.table.SysUploadFilePo;
import com.neusoft.base.po.EnumItem;
import com.neusoft.base.po.EnumType;
import com.neusoft.comm.ObjectUtil;
import com.neusoft.comm.po.IEnumItem;
import com.neusoft.comm.tools.EnumTypeUtil;
import com.neusoft.comm.tools.StringUtil;
import com.neusoft.comm.xls.config.XlsPoint;*/

/**
 * Excel工具类
 */
public class ExcelUtil {
    private final static Logger logger = Logger.getLogger(ExcelUtil.class);
    private String decimalFormat = "0.00";// 保存到小数点后2位
    private String dateFormat = "yyyy-MM-dd";
    private jxl.format.Colour bgColor = jxl.format.Colour.SKY_BLUE;
    public static String COLUMN_NAMES = "columnNames";
    public static String COLUMN_DISPLAY_NAMES = "columnLabels";
    private static int INVALID_PARAMETERS = 1;
    private static int WRITE_EXCEL_EXCEPTION = 2;

    /**
     * @return the decimalFormat
     */
    public String getDecimalFormat() {
        return decimalFormat;
    }

    /**
     * @param decimalFormat
     *            the decimalFormat to set
     */
    public void setDecimalFormat(String decimalFormat) {
        this.decimalFormat = decimalFormat;
    }

    /**
     * @return the bgColor
     */
    public Colour getBgColor() {
        return bgColor;
    }

    /**
     * @param bgColor
     *            the bgColor to set
     */
    public void setBgColor(Colour bgColor) {
        this.bgColor = bgColor;
    }

    public ExcelUtil() {
    }

    /**
     * 将List中的所有对象输出到Excel, list包含的对象必须为HashMap， map中包含了所有字段和值。
     * 通过字段名，就可以从map中得到该字段的值，从ibatis返回的记录集就是一这种形式保存
     * 
     * @param list
     * @param filename
     *            要输出的excel文件名，包含路径
     * @param sheetName
     *            要输出到excel中的工作表名，如果为空，则取默认值Sheet1
     * @param columnNames
     *            要输出的所有字段名，以逗号分隔
     * @param columnDisplayNames
     *            要输出的字段名的显示名称，通常为中文，以逗号分隔
     * @param columnWidth
     *            输出列的宽度，数值形数组
     * @param columnCode
     *            把数据库列值转成码表对应描述值；只存放需要转码的列名，表示对应列名的列值需要转码
     * @param columnSum
     *            要汇总的列名；只存放需要汇总的列名
     * @param columnSumTip
     *            汇总信息提示串；只存放需要提示的列名，默认提示为“合计：”
     * @param formatSum
     *            汇总行是否需要提示串；默认为true
     * @param bottomTip
     *            内容最底行，提示信息
     * @return 0 - 正常返回， 1 - 输入参数非法，2 - 写excel异常发生
     */
    public int writeListToExcel(List list, String filename, String sheetName, String columnNames,
            String columnDisplayNames, int[] columnWidth, Map columnCode, Map columnSum, Map columnSumTip,
            boolean formatSum, String[] bottomTip) {
        if (list == null || StringUtil.isEmpty(filename) || StringUtil.isEmpty(columnNames)
                || StringUtil.isEmpty(columnDisplayNames))
            return INVALID_PARAMETERS;
        String[] columnName = columnNames.split(",");
        String[] columnDisplayName = columnDisplayNames.split(",");
        WritableWorkbook book = null;

        if (StringUtil.isEmpty(sheetName))
            sheetName = "Sheet1";

        int numberOfFields = columnName.length, result = 0;
        int[] columnWidths = new int[numberOfFields];

        try {
            String path = filename.substring(0, filename.lastIndexOf("/"));
            if (path != null && !"".equals(path)) {
                File tmp = new File(path);
                if (!tmp.isDirectory()) {
                    tmp.mkdir();
                }
            }
            File file = new File(filename);
            if (file.exists())
                file.delete();

            // 创建一个可写的工作簿
            book = Workbook.createWorkbook(file);
            // 添加一个工作表
            WritableSheet ws = book.createSheet(sheetName, 1);
            // 添加带有字型Formatting的对象,用来输出标题行
            jxl.write.WritableFont wf = new jxl.write.WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false);
            jxl.write.WritableCellFormat wcf = new jxl.write.WritableCellFormat(wf);
            wcf.setBackground(bgColor);
            wcf.setAlignment(Alignment.CENTRE);
            // 输出标题行
            for (int j = 0; j < numberOfFields; j++) {
                jxl.write.Label label = new jxl.write.Label(j, 0, columnDisplayName[j], wcf);
                ws.addCell(label);
                columnWidths[j] = columnDisplayName[j].length() * 4 + 2;
                if (columnWidth == null) {
                    ws.setColumnView(j, columnWidths[j]);
                } else {
                    ws.setColumnView(j, columnWidth[j]);
                }
            }
            // 创建数字单元格式对象
            jxl.write.NumberFormat nf = new jxl.write.NumberFormat(decimalFormat);
            jxl.write.WritableCellFormat wcfNumber = new jxl.write.WritableCellFormat(nf);

            // 创建TimeStamp单元格式对象
            jxl.write.DateFormat dfTS = new jxl.write.DateFormat(dateFormat + " hh:mm:ss");
            jxl.write.WritableCellFormat wcfTimestamp = new jxl.write.WritableCellFormat(dfTS);
            // 创建日期单元格式对象
            jxl.write.DateFormat dfDate = new jxl.write.DateFormat(dateFormat);
            jxl.write.WritableCellFormat wcfDate = new jxl.write.WritableCellFormat(dfDate);

            int row = 1;
            Map columnTotal = new HashMap();
            // 将List中的对象输出到excel
            for (int i = 0; i < list.size(); i++) {
                Object _object = list.get(i);
                if (_object == null)
                    continue;
                Map map = null;
                Object vo = null;
                if (_object instanceof org.apache.commons.collections.map.ListOrderedMap) {// 判断该List对象是Map还是VO
                    ListOrderedMap map1 = (ListOrderedMap) list.get(i);
                    map = new HashMap();
                    map.putAll(map1);
                } else if (_object instanceof java.util.Map) {
                    map = (HashMap) list.get(i);
                } else {
                    vo = list.get(i);
                }
                for (int column = 0; column < numberOfFields; column++) {
                    // 当对象为空时，如果继续调用jxl的API，经常会出异常，所以先检测
                    Object obj = null;
                    if (map != null) {
                        obj = map.get(columnName[column]);
                        if (columnCode != null) {
                            if (columnCode.get(columnName[column]) != null) {
                                Map mapCode = this.SysCodeValue(columnCode.get(columnName[column]).toString());
                                String colValue = mapCode.get(obj) == null ? "" : mapCode.get(obj).toString();
                                obj = colValue;
                            }
                        }
                    } else if (vo != null) {
                        obj = PropertyUtils.getProperty(vo, columnName[column]);
                        if (columnCode != null) {
                            if (columnCode.get(columnName[column]) != null) {
                                Map mapCode = this.SysCodeValue(columnCode.get(columnName[column]).toString());
                                String colValue = mapCode.get(obj) == null ? "" : mapCode.get(obj).toString();
                                obj = colValue;
                            }
                        }
                    }
                    if (obj == null)
                        continue;
                    // 如果需要统计，将从map中读到的值累加到total,同时设置统计列的位置，用于最后输出统计信息
                    if (columnSum != null && columnSum.get(columnName[column]) != null) {
                        double total = columnTotal.get(columnName[column]) == null ? 0 : Double.parseDouble(columnTotal
                                .get(columnName[column]).toString());
                        total += Double.parseDouble(obj.toString());
                        columnTotal.put(columnName[column], total);
                    }
                    if (obj instanceof String) {
                        jxl.write.Label label = new jxl.write.Label(column, row, (String) obj);
                        ws.addCell(label);
                    } else if (obj instanceof Integer) {
                        jxl.write.Number number = new jxl.write.Number(column, row, ((Integer) obj).doubleValue(),
                                wcfNumber);
                        ws.addCell(number);
                    } else if (obj instanceof Timestamp) {
                        jxl.write.DateTime datetime = new jxl.write.DateTime(column, row, (Timestamp) obj, wcfTimestamp);
                        ws.addCell(datetime);
                    } else if (obj instanceof BigDecimal) {
                        jxl.write.Number number = new jxl.write.Number(column, row, ((BigDecimal) obj).doubleValue(),
                                wcfNumber);
                        ws.addCell(number);
                    } else if (obj instanceof Double) {
                        jxl.write.Number number = new jxl.write.Number(column, row, ((Double) obj).doubleValue(),
                                wcfNumber);
                        ws.addCell(number);
                    } else if (obj instanceof Float) {
                        jxl.write.Number number = new jxl.write.Number(column, row, ((Float) obj).doubleValue(),
                                wcfNumber);
                        ws.addCell(number);
                    } else if ((obj instanceof Long)) {
                        jxl.write.Number number = new jxl.write.Number(column, row, ((Long) obj).doubleValue(),
                                wcfNumber);
                        ws.addCell(number);
                    } else if ((obj instanceof Short)) {
                        jxl.write.Number number = new jxl.write.Number(column, row, ((Short) obj).doubleValue(),
                                wcfNumber);
                        ws.addCell(number);
                    } else if (obj instanceof Date) {
                        jxl.write.DateTime datetime = new jxl.write.DateTime(column, row, (Date) obj, wcfDate);
                        ws.addCell(datetime);
                    } else if (obj instanceof Boolean) {
                        jxl.write.Boolean labelB = new jxl.write.Boolean(column, row, ((Boolean) obj).booleanValue());
                        ws.addCell(labelB);
                    } else {
                        // 把其他类型都作为字符串处理
                        jxl.write.Label label = new jxl.write.Label(column, row, obj.toString());
                        ws.addCell(label);
                    }
                }
                row++;
            }
            // 输出统计信息
            if (columnSum != null) {
                for (int column = 0; column < numberOfFields; column++) {
                    Object total = columnTotal.get(columnName[column]);
                    if (total != null) {
                        double colTotal = Double.parseDouble(total.toString());
                        if (formatSum) {
                            jxl.write.Number number = new jxl.write.Number(column, row, colTotal, wcfNumber);
                            ws.addCell(number);
                        } else {
                            Object tip = columnSumTip.get(columnName[column]);
                            if (tip != null) {
                                tip = tip.toString() + ":" + colTotal;
                            } else {
                                tip = "合计:" + colTotal;
                            }
                            jxl.write.Label labelPrompt = new jxl.write.Label(column, row, tip.toString());
                            ws.addCell(labelPrompt);
                        }
                    }
                }
            } else {
                if (bottomTip != null) {
                    int bottomTipLength = bottomTip.length;
                    StringBuffer sb = new StringBuffer();
                    int height = 0;
                    for (int k = 0; k < bottomTipLength; k++) {
                        jxl.write.Label labelPrompt = new jxl.write.Label(0, row + 2 + k, "");
                        ws.addCell(labelPrompt);
                        // sb.append(bottomTip[k]).append("\012");
                        sb.append(bottomTip[k]);
                        if (!StringUtil.isEmpty(bottomTip[k]))
                            height += bottomTip[k].split("\n").length;
                    }
                    ws.mergeCells(0, row + 2, numberOfFields - 1, row + 2 + height);
                    WritableCellFormat wcfFr = new WritableCellFormat();
                    wcfFr.setWrap(true);// 自动换行
                    wcfFr.setAlignment(jxl.format.Alignment.LEFT);// 把水平对齐方式指定为居左
                    wcfFr.setVerticalAlignment(jxl.format.VerticalAlignment.TOP);// 把垂直对齐方式指定为居上
                    jxl.write.Label labelC = new jxl.write.Label(0, row + 2, sb.toString(), wcfFr);
                    ws.addCell(labelC);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = WRITE_EXCEL_EXCEPTION;
            logger.error("写Excel失败。" + e.getMessage());
        } finally {
            try {
                if (book != null) {
                    book.write();
                    book.close();
                }
            } catch (Exception e) {
            }
        }
        return result;
    }

    /**
     * @return the dateFormat
     */
    public String getDateFormat() {
        return dateFormat;
    }

    /**
     * @param dateFormat
     *            the dateFormat to set
     */
    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    /**
     * 导出 excel
     * 
     * @author qixy
     * @param request
     * @param response
     * @param list
     * @param downloadChineseFileName
     * @param sheetName
     * @param columnNames
     * @param columnDisplayNames
     * @param columnWidth
     * @param columnCode
     * @param columnSum
     * @param columnSumTip
     */
    public static void exportToExcel(HttpServletRequest request, HttpServletResponse response, List list,
            String downloadChineseFileName, String sheetName, String columnNames, String columnDisplayNames,
            int[] columnWidth, Map columnCode, Map columnSum, Map columnSumTip) {
        exportToExcel(request, response, list, downloadChineseFileName, sheetName, columnNames, columnDisplayNames,
                columnWidth, columnCode, columnSum, columnSumTip, true, null);
    }

    public static void exportToExcel(HttpServletRequest request, HttpServletResponse response, List list,
            String downloadChineseFileName, String sheetName, String columnNames, String columnDisplayNames,
            int[] columnWidth, Map columnCode) {
        exportToExcel(request, response, list, downloadChineseFileName, sheetName, columnNames, columnDisplayNames,
                columnWidth, columnCode, null, null, false, null);
    }

    public static void exportToExcel(HttpServletRequest request, HttpServletResponse response, List list,
            String downloadChineseFileName, String sheetName, String columnNames, String columnDisplayNames,
            int[] columnWidth, String[] bottomTip) {
        exportToExcel(request, response, list, downloadChineseFileName, sheetName, columnNames, columnDisplayNames,
                columnWidth, null, null, null, false, bottomTip);
    }

    public static void exportToExcel(HttpServletRequest request, HttpServletResponse response, List list,
            String downloadChineseFileName, String sheetName, String columnNames, String columnDisplayNames,
            int[] columnWidth, Map columnCode, Map columnSum, Map columnSumTip, boolean formatSum, String[] bottomTip) {
        PrintWriter write = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            write = response.getWriter();
            String excelFileName = TimeTool.paserString(new Date(), "yyyyMMddHHmmssS") + ".xls";
            String filename = request.getSession().getServletContext().getRealPath("/upload") + "/ExportFiles/"
                    + excelFileName;
            /*
             * if (list == null || list.size() < 1) { //return;
             * write.append("{\"retCode\":\"I0000\",\"retMsg\":\"导出成功。\",\"path\":\""+path+"\"}"); }
             * else {
             */
            ExcelUtil excelUtil = new ExcelUtil();
            int result = excelUtil.writeListToExcel(list, filename, sheetName, columnNames, columnDisplayNames,
                    columnWidth, columnCode, columnSum, columnSumTip, formatSum, bottomTip);
            downloadChineseFileName = java.net.URLEncoder.encode(downloadChineseFileName, "UTF-8");
            String path = request.getContextPath() + "/downloadFileTemplate.action?fileName=" + excelFileName
                    + "&downloadChineseFileName=" + downloadChineseFileName;
            if (result == 0) {
                write.append("{\"retCode\":\"I0000\",\"retMsg\":\"导出成功。\",\"path\":\"" + path + "\"}");
            } else {
                write.append("{\"retCode\":\"E0000\",\"retMsg\":\"导出失败。\",\"path\":\"\"}");
            }
            // }
        } catch (IOException e) {
            e.printStackTrace();
            write.append("{\"retCode\":\"E0000\",\"retMsg\":\"导出失败。\",\"path\":\"\"}");
        } finally {
            write.flush();
            write.close();
        }
    }

    /**
     * 把list里的记录中columnName数据转换为代码表的描述
     * 
     * @param codetype
     */
    public Map SysCodeValue(String codetype) {
        EnumType type = (EnumType) EnumTypeUtil.getEnumType(codetype);
        List<IEnumItem> items = type.getItems();
        Map map = new HashMap();
        for (int i = 0; i < items.size(); i++) {
            EnumItem item = (EnumItem) items.get(i);
            map.put(item.getKey(), item.getValue());
        }
        return map;
    }

    public static HashMap CheckFromXlsFile(List fileList, List modellist) throws Exception {
        return CheckFromXlsFile(fileList, modellist, null);
    }

    public static HashMap CheckFromXlsFile(List fileList, List modellist, String sheetName) throws Exception {
        SysUploadFilePo uploadFile = (SysUploadFilePo) fileList.get(0);
        String filePath = uploadFile.getFilePath() + "/" + uploadFile.getFileId();
        HashMap retmp = new HashMap();
        int idx = 0;
        for (int m = 0; m < modellist.size(); m++) {
            HashMap hm = (HashMap) modellist.get(m);
            String visible = (String)hm.get("VISIBLE");
            if (visible != null && visible.equals("0")) {//文件中不存在的列
                continue;
            }
            idx ++;
        }
        String[] columnDisNames = new String[idx];
        String[] columnNames = new String[idx];
        idx = 0;
        for (int m = 0; m < modellist.size(); m++) {
            HashMap hm = (HashMap) modellist.get(m);
            String visible = (String)hm.get("VISIBLE");
            if (visible == null || visible.equals("1")) {//文件中不存在的列
                columnDisNames[idx] = hm.get("TITLE_CNNAME").toString().trim();
                columnNames[idx] = hm.get("TITLE_NAME").toString();
                idx ++;
            }
        }
        HashSet<String> allNamesMap = new HashSet<String>();
        String[] ret = readFromXlsFileTitle(filePath, sheetName);
        for (int x = 0 ; x < ret.length; x ++) {
            allNamesMap.add(ret[x]);
        }
        ArrayList<ErrorInfoPo> msgs = new ArrayList<ErrorInfoPo>();
        retmp.put("msg", msgs);
        for (int i = 0 ; i < columnDisNames.length ; i ++) {
            if (!allNamesMap.contains(columnDisNames[i])) {
                addMsg(msgs,1,columnDisNames[i],"导入模板不匹配！导入文件中缺少列[" + columnDisNames[i]+ "]");
            }
        }
        if (msgs.size() > 0 ) {
            return retmp;
        }
        List li = new ArrayList();
        List listtmp = readFromXlsFile(filePath, sheetName, columnDisNames,columnNames, HashMap.class,ret);
        StringBuffer sbf = new StringBuffer();
        for (int j = 0; j < listtmp.size(); j++) {
            HashMap record = (HashMap) listtmp.get(j);
            String titleName = "";
            String titleCnName = "";
            String isNotempty = "0";
            String isNumber = "0";
            String valArea = "";
            int mustLen = 0;
            int maxLen = 0;
            for (int m = 0; m < modellist.size(); m++) {
                HashMap hm = (HashMap) modellist.get(m);
                String visible = (String)hm.get("VISIBLE");
                if (visible != null && visible.equals("0")) {//文件中不存在的列
                    continue;
                }
                titleName = hm.get("TITLE_NAME").toString();
                titleCnName = hm.get("TITLE_CNNAME").toString();
                isNotempty = hm.get("IS_NOTEMPTY").toString();
                isNumber = hm.get("IS_NUMBER").toString();
                valArea = hm.get("REMARK") == null ? "" : hm.get("REMARK").toString();
                mustLen = Integer.parseInt(hm.get("MUST_LEN").toString());
                maxLen = Integer.parseInt(hm.get("MAX_LEN").toString());

                String val = (String)record.get(titleName);
                if ("".equals(val)) {
                    if ("1".equals(isNotempty)) {
                        //1行为表头，一行为数据循环从0开始（+1）
                        addMsg(msgs,new Integer(j + 2),titleCnName,"值["+val+"]不能为空");
                        //sbf.append("第" + new Integer(j + 1) + "行，" + titleCnName + "不能为空<br>");
                    }
                } else {
                    if (mustLen > 0 && val.length() != mustLen) {
                        addMsg(msgs, new Integer(j + 2), titleCnName, "值["+val+"]长度应该为" + mustLen + "位");
                        //sbf.append("第" + new Integer(j + 1) + "行，" + titleCnName + "长度应该为" + mustLen + "位<br>");
                    }
                    if ("1".equals(isNumber)) {
                        val = val.replace(",", "");
                        record.put(titleName, val);
                        if (!ExcelUtil.isNumberic(val.trim())) {
                            addMsg(msgs,j+2,titleCnName,"值["+val+"]必须为数字");
                            //sbf.append("第" + new Integer(j + 1) + "行，" + titleCnName + "必须为数字<br>");
                        }
                    }
                    if (!"".equals(valArea) && valArea.indexOf(val) == -1) {
                        addMsg(msgs,j+2,titleCnName,"只能为(" + valArea + ")");
                        //sbf.append("第" + new Integer(j + 1) + "行，" + titleCnName + "只能为(" + valArea + ")<br>");
                    }
                    if (maxLen > 0 && val.length() > maxLen) {
                        addMsg(msgs,j+2,titleCnName,"值["+val+"]长度不能超过" + maxLen + "位");
                        //sbf.append("第" + new Integer(j + 1) + "行，" + titleCnName + "长度不能超过" + maxLen + "位<br>");
                    }
                }
            }
            // 当为""时才进库，否则说明数据格式有问题
            if (sbf.length() == 0) {
                record.put("UPLOAD_ID", uploadFile.getUploadId());
                record.put("FILE_ID", uploadFile.getFileId());
                record.put("BIZ_DATE", new Date());
                li.add(record);
            }
        }
        if (msgs.size() == 0) {
            retmp.put("list", li);
        }
        return retmp;
    }

    private static void addMsg(ArrayList<ErrorInfoPo> msgs, Integer j, String titleCnName, String info) {
        ErrorInfoPo ep = new ErrorInfoPo();
        ep.setRowid(j );
        ep.setColumnName(titleCnName);
        ep.setErrorInfo(info);
        msgs.add(ep);
    }

    // 读表格第一行标题
    public static String[] readFromXlsFileTitle(String filepath, String sheetName) throws Exception {
        File file = new File(filepath);
        if (!file.exists()) {
            return null;
        }
        Workbook wb = Workbook.getWorkbook(file);
        Sheet sheet = (sheetName == null) ? wb.getSheet(0) : wb.getSheet(sheetName);
        int columns = sheet.getColumns();
        String[] ret = new String[columns];
        for (int c = 0; c < columns; c++) {
            Cell cell = sheet.getCell(c, 0);
            if (cell != null) {
                String t = cell.getContents();
                ret[c] = (t==null?"":t.trim());
            }
            else {
                ret[c] = "";
            }
        }
        return ret;
    }

    /**
     * 读取xls文件
     * 
     * @param filepath
     *            数据文件路径
     * @param sheetName
     *            sheet名称，如null表示第一个
     * @param fields
     *            指定xls域名
     * @param properties
     *            指定属性,如为空就等于fields
     * @param retClass
     *            指定返回结果类HashMap
     * @param columnNames
     *            文件中列名，允许为空
     * @return
     * @throws Exception
     */
    public static ArrayList readFromXlsFile(String filepath, String sheetName, String[] fields,String[] properties, Class retClass,String[] columnDisNames)
            throws Exception {
        ArrayList ret = new ArrayList();
        File file = new File(filepath);
        if (!file.exists()) {
            return ret;
        }
        Workbook wb = Workbook.getWorkbook(file);
        Sheet sheet = (sheetName == null) ? wb.getSheet(0) : wb.getSheet(sheetName);
        if (sheet == null) {
            throw new RuntimeException("文件 [" + filepath + "]Sheet[" + sheetName + "]不存在");
        }
        if(properties == null) {
            properties = fields;
        }
        if (properties.length != fields.length) {
            throw new RuntimeException("显示列名和属性列名数量不等");
        }
        int maxRow = sheet.getRows();
        //确定位置
        int[] positions = new int[fields.length];
        //已经读出来
        if (columnDisNames == null || columnDisNames.length ==0) {
            int columns = sheet.getColumns();
            columnDisNames = new String[columns];
            for (int c = 0; c < columns; c++) {
                Cell cell = sheet.getCell(c, 0);
                String t = cell.getContents();
                columnDisNames[c] = (t==null?"":t.trim());
            }
        }
        for (int i = 0 ; i < positions.length ; i++) {
            int idx = -1;
            for (int s = 0 ; s < columnDisNames.length ; s++) {
                if (columnDisNames[s].equals(fields[i])) {
                    idx = s;
                    break;
                }
            }
            if (idx < 0) {
                throw new RuntimeException("列[" +  fields[i] + "]不存在");
            }
            positions[i] =idx;
        }
        for (int r = 1; r < maxRow; r++) {
            Object record = ObjectUtil.toObject(logger, retClass.getCanonicalName());
            if (record == null) {
                throw new RuntimeException("实例化[" + retClass.getCanonicalName() + "]失败");
            }
            int c = 0;
            Object value = null;
            try {
                for (; c < fields.length; c++) {
                    if (fields[c] != null) {
                        Cell cell = sheet.getCell(positions[c], r);

                        if (cell != null) {
                            value = cell.getContents();
                            if (record instanceof Map) {
                                PropertyUtils.setProperty(record, properties[c], value);
                            } else {
                                PropertyDescriptor pd = PropertyUtils.getPropertyDescriptor(record, fields[c]);
                                Class x = pd.getPropertyType();
                                if (x.isPrimitive()) {
                                    if ("int".equals(x.getName())) {
                                        Integer d = Integer.parseInt(value.toString());
                                        PropertyUtils.setProperty(record, fields[c], d);
                                    } else if ("float".equals(x.getName())) {
                                        Float d = Float.parseFloat(value.toString());
                                        PropertyUtils.setProperty(record, fields[c], d);
                                    } else if ("double".equals(x.getName())) {
                                        Double d = Double.parseDouble(value.toString());
                                        PropertyUtils.setProperty(record, fields[c], d);
                                    }
                                } else {
                                    if (null != value && !"".equals(value.toString().trim())) {
                                        if ("java.lang.Integer".equals(x.getName())) {
                                            PropertyUtils.setProperty(record, fields[c], (Integer) value);
                                        } else if ("java.lang.Long".equals(x.getName())) {
                                            PropertyUtils.setProperty(record, fields[c], (Long) value);
                                        } else if ("java.lang.Double".equals(x.getName())) {
                                            PropertyUtils.setProperty(record, fields[c], (Double) value);
                                        } else if ("java.math.BigDecimal".equals(x.getName())) {
                                            PropertyUtils.setProperty(record, fields[c],
                                                    new BigDecimal(value.toString()));
                                        } else {
                                            PropertyUtils.setProperty(record, fields[c], value);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                XlsPoint p = new XlsPoint(c, r);
                logger.error("Point[" + p.toString() + "] value=" + value, e);
                throw e;
            }
            ret.add(record);
        }
        return ret;
    }
    
    public static void exportToTxt(HttpServletRequest request, HttpServletResponse response, String excelFileName, int result) {
        PrintWriter write = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            write = response.getWriter();
//            String excelFileName = TimeTool.paserString(new Date(), "yyyyMMddHHmmssS") + ".xls";
//            String filename = request.getSession().getServletContext().getRealPath("/upload") + "/ExportFiles/"
//                    + excelFileName;
            /*
             * if (list == null || list.size() < 1) { //return;
             * write.append("{\"retCode\":\"I0000\",\"retMsg\":\"导出成功。\",\"path\":\""+path+"\"}"); }
             * else {
             */
//            ExcelUtil excelUtil = new ExcelUtil();
//            int result = excelUtil.writeListToExcel(list, filename, sheetName, columnNames, columnDisplayNames,
//                    columnWidth, columnCode, columnSum, columnSumTip, formatSum, bottomTip);
//            excelFileName = java.net.URLEncoder.encode(excelFileName, "UTF-8");
            String path = request.getContextPath() + "/downloadFileTemplate.action?fileName=" + excelFileName
                    + "&downloadChineseFileName=" + excelFileName;
            if (result == 0) {
                write.append("{\"retCode\":\"I0000\",\"retMsg\":\"导出成功。\",\"path\":\"" + path + "\"}");
            } else {
                write.append("{\"retCode\":\"E0000\",\"retMsg\":\"导出失败。\",\"path\":\"\"}");
            }
            // }
        } catch (IOException e) {
            e.printStackTrace();
            write.append("{\"retCode\":\"E0000\",\"retMsg\":\"导出失败。\",\"path\":\"\"}");
        } finally {
            write.flush();
            write.close();
        }
        
    }

    public static boolean isNumberic(String str) {
        if (str == null || str.length() == 0 || !str.matches("^([0-9]\\d*|0)(\\.\\d+)?$")) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * 如果导出文件中首行需要加提示信息调用此方法.
     * @param request
     * @param response
     * @param list
     * @param downloadChineseFileName
     *              要输出的excel文件名，包含路径
     * @param sheetName
     *              要输出到excel中的工作表名，如果为空，则取默认值Sheet1
     * @param columnNames
     *              要输出的所有字段名，以逗号分隔
     * @param columnDisplayNames
     *              要输出的字段名的显示名称，通常为中文，以逗号分隔
     * @param columnWidth
     *              输出列的宽度，数值形数组
     * @param columnCode
     *              把数据库列值转成码表对应描述值；只存放需要转码的列名，表示对应列名的列值需要转码
     * @param columnSum
     *              要汇总的列名；只存放需要汇总的列名
     * @param columnSumTip
     *              汇总信息提示串；只存放需要提示的列名，默认提示为“合计：”
     * @param isTip
     *              是否提示内容
     * @param tip
     *              首行提示信息的内容
     * @param tipHei
     *              首行提示信息的行高
     */
    public static void exportToExcelTip(HttpServletRequest request, HttpServletResponse response, List list,
            String downloadChineseFileName, String sheetName, String columnNames, String columnDisplayNames,
            int[] columnWidth, Map columnCode, Map columnSum, Map columnSumTip, boolean isTip, String tip, int tipHei) {
        exportToExcelTip(request, response, list, downloadChineseFileName, sheetName, columnNames, columnDisplayNames,
                columnWidth, columnCode, columnSum, columnSumTip, true, null,isTip, tip, tipHei);
    }
    public static void exportToExcelTip(HttpServletRequest request, HttpServletResponse response, List list,
            String downloadChineseFileName, String sheetName, String columnNames, String columnDisplayNames,
            int[] columnWidth, Map columnCode, Map columnSum, Map columnSumTip, boolean formatSum, String[] bottomTip,
            boolean isTip, String tip, int tipHei) {
        PrintWriter write = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            write = response.getWriter();
            String excelFileName = TimeTool.paserString(new Date(), "yyyyMMddHHmmssS") + ".xls";
            String filename = request.getSession().getServletContext().getRealPath("/upload") + "/ExportFiles/"
                    + excelFileName;
            ExcelUtil excelUtil = new ExcelUtil();
            int result = excelUtil.writeListToExcelTip(list, filename, sheetName, columnNames, columnDisplayNames,
                    columnWidth, columnCode, columnSum, columnSumTip, formatSum, bottomTip, isTip, tip, tipHei);
            downloadChineseFileName = java.net.URLEncoder.encode(downloadChineseFileName, "UTF-8");
            String path = request.getContextPath() + "/downloadFileTemplate.action?fileName=" + excelFileName
                    + "&downloadChineseFileName=" + downloadChineseFileName;
            if (result == 0) {
                write.append("{\"retCode\":\"I0000\",\"retMsg\":\"导出成功。\",\"path\":\"" + path + "\"}");
            } else {
                write.append("{\"retCode\":\"E0000\",\"retMsg\":\"导出失败。\",\"path\":\"\"}");
            }
            // }
        } catch (IOException e) {
            e.printStackTrace();
            write.append("{\"retCode\":\"E0000\",\"retMsg\":\"导出失败。\",\"path\":\"\"}");
        } finally {
            write.flush();
            write.close();
        }
    }
    /**
     * 将List中的所有对象输出到Excel, list包含的对象必须为HashMap， map中包含了所有字段和值。
     * 通过字段名，就可以从map中得到该字段的值，从ibatis返回的记录集就是一这种形式保存
     * 
     * @param list
     * @param filename
     *            要输出的excel文件名，包含路径
     * @param sheetName
     *            要输出到excel中的工作表名，如果为空，则取默认值Sheet1
     * @param columnNames
     *            要输出的所有字段名，以逗号分隔
     * @param columnDisplayNames
     *            要输出的字段名的显示名称，通常为中文，以逗号分隔
     * @param columnWidth
     *            输出列的宽度，数值形数组
     * @param columnCode
     *            把数据库列值转成码表对应描述值；只存放需要转码的列名，表示对应列名的列值需要转码
     * @param columnSum
     *            要汇总的列名；只存放需要汇总的列名
     * @param columnSumTip
     *            汇总信息提示串；只存放需要提示的列名，默认提示为“合计：”
     * @param formatSum
     *            汇总行是否需要提示串；默认为true
     * @param bottomTip
     *            内容最底行，提示信息
     * @return 0 - 正常返回， 1 - 输入参数非法，2 - 写excel异常发生
     * 
     * @param isTip
     *            是否提示内容
     * @param tip
     *            首行提示信息的内容
     * @param tipHei
     *            首行提示信息的行高
     */
    public int writeListToExcelTip(List list, String filename, String sheetName, String columnNames,
            String columnDisplayNames, int[] columnWidth, Map columnCode, Map columnSum, Map columnSumTip,
            boolean formatSum, String[] bottomTip,  boolean isTip, String tipName, int tipHei) {
        if (list == null || StringUtil.isEmpty(filename) || StringUtil.isEmpty(columnNames)
                || StringUtil.isEmpty(columnDisplayNames))
            return INVALID_PARAMETERS;
        String[] columnName = columnNames.split(",");
        String[] columnDisplayName = columnDisplayNames.split(",");
        WritableWorkbook book = null;

        if (StringUtil.isEmpty(sheetName))
            sheetName = "Sheet1";

        int numberOfFields = columnName.length, result = 0;
        int[] columnWidths = new int[numberOfFields];

        try {
            String path = filename.substring(0, filename.lastIndexOf("/"));
            if (path != null && !"".equals(path)) {
                File tmp = new File(path);
                if (!tmp.isDirectory()) {
                    tmp.mkdir();
                }
            }
            File file = new File(filename);
            if (file.exists())
                file.delete();

            // 创建一个可写的工作簿
            book = Workbook.createWorkbook(file);
            // 添加一个工作表
            WritableSheet ws = book.createSheet(sheetName, 1);
            // 添加带有字型Formatting的对象,用来输出标题行
            jxl.write.WritableFont wf = new jxl.write.WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD, false);
            jxl.write.WritableCellFormat wcf = new jxl.write.WritableCellFormat(wf);
            wcf.setBackground(bgColor);
            wcf.setAlignment(Alignment.CENTRE);
            
            /**
             * 如果需要添加提示，合并单元格 并添加行高度
             */
            if (isTip){
                jxl.write.Label titleLable = new jxl.write.Label(0, 0, tipName);
                ws.mergeCells(0, 0, numberOfFields-1, 0);
                ws.setRowView(0, tipHei);
                ws.addCell(titleLable);
            }
            int row = 1;
            // 输出标题行
            for (int j = 0; j < numberOfFields; j++) {
                jxl.write.Label label = null;
                if (isTip){
                    row = 2;
                    label = new jxl.write.Label(j, 1, columnDisplayName[j], wcf);
                } else {
                    label = new jxl.write.Label(j, 0, columnDisplayName[j], wcf);
                }
                ws.addCell(label);
                columnWidths[j] = columnDisplayName[j].length() * 4 + 2;
                if (columnWidth == null) {
                    ws.setColumnView(j, columnWidths[j]);
                } else {
                    ws.setColumnView(j, columnWidth[j]);
                }
            }
            // 创建数字单元格式对象
            jxl.write.NumberFormat nf = new jxl.write.NumberFormat(decimalFormat);
            jxl.write.WritableCellFormat wcfNumber = new jxl.write.WritableCellFormat(nf);

            // 创建TimeStamp单元格式对象
            jxl.write.DateFormat dfTS = new jxl.write.DateFormat(dateFormat + " hh:mm:ss");
            jxl.write.WritableCellFormat wcfTimestamp = new jxl.write.WritableCellFormat(dfTS);
            // 创建日期单元格式对象
            jxl.write.DateFormat dfDate = new jxl.write.DateFormat(dateFormat);
            jxl.write.WritableCellFormat wcfDate = new jxl.write.WritableCellFormat(dfDate);

            Map columnTotal = new HashMap();
            // 将List中的对象输出到excel
            for (int i = 0; i < list.size(); i++) {
                Object _object = list.get(i);
                if (_object == null)
                    continue;
                Map map = null;
                Object vo = null;
                if (_object instanceof org.apache.commons.collections.map.ListOrderedMap) {// 判断该List对象是Map还是VO
                    ListOrderedMap map1 = (ListOrderedMap) list.get(i);
                    map = new HashMap();
                    map.putAll(map1);
                } else if (_object instanceof java.util.Map) {
                    map = (HashMap) list.get(i);
                } else {
                    vo = list.get(i);
                }
                for (int column = 0; column < numberOfFields; column++) {
                    // 当对象为空时，如果继续调用jxl的API，经常会出异常，所以先检测
                    Object obj = null;
                    if (map != null) {
                        obj = map.get(columnName[column]);
                        if (columnCode != null) {
                            if (columnCode.get(columnName[column]) != null) {
                                Map mapCode = this.SysCodeValue(columnCode.get(columnName[column]).toString());
                                String colValue = mapCode.get(obj) == null ? "" : mapCode.get(obj).toString();
                                obj = colValue;
                            }
                        }
                    } else if (vo != null) {
                        obj = PropertyUtils.getProperty(vo, columnName[column]);
                        if (columnCode != null) {
                            if (columnCode.get(columnName[column]) != null) {
                                Map mapCode = this.SysCodeValue(columnCode.get(columnName[column]).toString());
                                String colValue = mapCode.get(obj) == null ? "" : mapCode.get(obj).toString();
                                obj = colValue;
                            }
                        }
                    }
                    if (obj == null)
                        continue;
                    // 如果需要统计，将从map中读到的值累加到total,同时设置统计列的位置，用于最后输出统计信息
                    if (columnSum != null && columnSum.get(columnName[column]) != null) {
                        double total = columnTotal.get(columnName[column]) == null ? 0 : Double.parseDouble(columnTotal
                                .get(columnName[column]).toString());
                        total += Double.parseDouble(obj.toString());
                        columnTotal.put(columnName[column], total);
                    }
                    if (obj instanceof String) {
                        jxl.write.Label label = new jxl.write.Label(column, row, (String) obj);
                        ws.addCell(label);
                    } else if (obj instanceof Integer) {
                        jxl.write.Number number = new jxl.write.Number(column, row, ((Integer) obj).doubleValue(),
                                wcfNumber);
                        ws.addCell(number);
                    } else if (obj instanceof Timestamp) {
                        jxl.write.DateTime datetime = new jxl.write.DateTime(column, row, (Timestamp) obj, wcfTimestamp);
                        ws.addCell(datetime);
                    } else if (obj instanceof BigDecimal) {
                        jxl.write.Number number = new jxl.write.Number(column, row, ((BigDecimal) obj).doubleValue(),
                                wcfNumber);
                        ws.addCell(number);
                    } else if (obj instanceof Double) {
                        jxl.write.Number number = new jxl.write.Number(column, row, ((Double) obj).doubleValue(),
                                wcfNumber);
                        ws.addCell(number);
                    } else if (obj instanceof Float) {
                        jxl.write.Number number = new jxl.write.Number(column, row, ((Float) obj).doubleValue(),
                                wcfNumber);
                        ws.addCell(number);
                    } else if ((obj instanceof Long)) {
                        jxl.write.Number number = new jxl.write.Number(column, row, ((Long) obj).doubleValue(),
                                wcfNumber);
                        ws.addCell(number);
                    } else if ((obj instanceof Short)) {
                        jxl.write.Number number = new jxl.write.Number(column, row, ((Short) obj).doubleValue(),
                                wcfNumber);
                        ws.addCell(number);
                    } else if (obj instanceof Date) {
                        jxl.write.DateTime datetime = new jxl.write.DateTime(column, row, (Date) obj, wcfDate);
                        ws.addCell(datetime);
                    } else if (obj instanceof Boolean) {
                        jxl.write.Boolean labelB = new jxl.write.Boolean(column, row, ((Boolean) obj).booleanValue());
                        ws.addCell(labelB);
                    } else {
                        // 把其他类型都作为字符串处理
                        jxl.write.Label label = new jxl.write.Label(column, row, obj.toString());
                        ws.addCell(label);
                    }
                }
                row++;
            }
            // 输出统计信息
            if (columnSum != null) {
                for (int column = 0; column < numberOfFields; column++) {
                    Object total = columnTotal.get(columnName[column]);
                    if (total != null) {
                        double colTotal = Double.parseDouble(total.toString());
                        if (formatSum) {
                            jxl.write.Number number = new jxl.write.Number(column, row, colTotal, wcfNumber);
                            ws.addCell(number);
                        } else {
                            Object tip = columnSumTip.get(columnName[column]);
                            if (tip != null) {
                                tip = tip.toString() + ":" + colTotal;
                            } else {
                                tip = "合计:" + colTotal;
                            }
                            jxl.write.Label labelPrompt = new jxl.write.Label(column, row, tip.toString());
                            ws.addCell(labelPrompt);
                        }
                    }
                }
            } else {
                if (bottomTip != null) {
                    int bottomTipLength = bottomTip.length;
                    StringBuffer sb = new StringBuffer();
                    int height = 0;
                    for (int k = 0; k < bottomTipLength; k++) {
                        jxl.write.Label labelPrompt = new jxl.write.Label(0, row + 2 + k, "");
                        ws.addCell(labelPrompt);
                        // sb.append(bottomTip[k]).append("\012");
                        sb.append(bottomTip[k]);
                        if (!StringUtil.isEmpty(bottomTip[k]))
                            height += bottomTip[k].split("\n").length;
                    }
                    ws.mergeCells(0, row + 2, numberOfFields - 1, row + 2 + height);
                    WritableCellFormat wcfFr = new WritableCellFormat();
                    wcfFr.setWrap(true);// 自动换行
                    wcfFr.setAlignment(jxl.format.Alignment.LEFT);// 把水平对齐方式指定为居左
                    wcfFr.setVerticalAlignment(jxl.format.VerticalAlignment.TOP);// 把垂直对齐方式指定为居上
                    jxl.write.Label labelC = new jxl.write.Label(0, row + 2, sb.toString(), wcfFr);
                    ws.addCell(labelC);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            result = WRITE_EXCEL_EXCEPTION;
            logger.error("写Excel失败。" + e.getMessage());
        } finally {
            try {
                if (book != null) {
                    book.write();
                    book.close();
                }
            } catch (Exception e) {
            }
        }
        return result;
    }
    public static void main(String[] agrs) {

    }

    /**
     * 文件导入公共类.
     * @param file   文件
     * @param maxCol 最大列
     * @param maxRol 第几行开始读取
     * @return
     * @throws BiffException
     * @throws IOException
     */
    public static List<Map<String,Object>> readFromXls(File file, int maxCol, int maxRol) throws BiffException, IOException{
        List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Workbook wb = Workbook.getWorkbook(file);
        Sheet sheet = wb.getSheet(0);
        if (sheet == null) {
            throw new RuntimeException("文件不存在");
        }
        int maxRow = sheet.getRows();
        for (int r = maxRol; r < maxRow; r++) {
            Map<String,Object> map = new HashMap<String, Object>();
            Cell[] cells = sheet.getRow(r);
            if(cells == null || cells.length == 0){
                continue;
            }
            for(int s = 0; s < maxCol; s++) {
                if(s >= cells.length){
                    continue;
                }
                if(cells[s].getContents().equals("") || cells[s].getContents() == null){
                }else{
                    map.put(String.valueOf(s), cells[s].getContents());
                }
            }
            if(map.size() != 0){
                list.add(map);
            }
        }
        return list;
    }
    
  /*  public static HashMap CheckFromXlsFile(List fileList, List modellist) throws Exception {
        return CheckFromXlsFile(fileList, modellist, null);
    }

    public static HashMap CheckFromXlsFile(List fileList, List modellist, String sheetName) throws Exception {
        SysUploadFilePo uploadFile = (SysUploadFilePo) fileList.get(0);
        String filePath = uploadFile.getFilePath() + "/" + uploadFile.getFileId();
        HashMap retmp = new HashMap();
        int idx = 0;
        for (int m = 0; m < modellist.size(); m++) {
            HashMap hm = (HashMap) modellist.get(m);
            String visible = (String)hm.get("VISIBLE");
            if (visible != null && visible.equals("0")) {//文件中不存在的列
                continue;
            }
            idx ++;
        }
        String[] columnDisNames = new String[idx];
        String[] columnNames = new String[idx];
        idx = 0;
        for (int m = 0; m < modellist.size(); m++) {
            HashMap hm = (HashMap) modellist.get(m);
            String visible = (String)hm.get("VISIBLE");
            if (visible == null || visible.equals("1")) {//文件中不存在的列
                columnDisNames[idx] = hm.get("TITLE_CNNAME").toString().trim();
                columnNames[idx] = hm.get("TITLE_NAME").toString();
                idx ++;
            }
        }
        HashSet<String> allNamesMap = new HashSet<String>();
        String[] ret = readFromXlsFileTitle(filePath, sheetName);
        for (int x = 0 ; x < ret.length; x ++) {
            allNamesMap.add(ret[x]);
        }
        ArrayList<ErrorInfoPo> msgs = new ArrayList<ErrorInfoPo>();
        retmp.put("msg", msgs);
        for (int i = 0 ; i < columnDisNames.length ; i ++) {
            if (!allNamesMap.contains(columnDisNames[i])) {
                addMsg(msgs,1,columnDisNames[i],"导入模板不匹配！导入文件中缺少列[" + columnDisNames[i]+ "]");
            }
        }
        if (msgs.size() > 0 ) {
            return retmp;
        }
        List li = new ArrayList();
        List listtmp = readFromXlsFile(filePath, sheetName, columnDisNames,columnNames, HashMap.class,ret);
        StringBuffer sbf = new StringBuffer();
        for (int j = 0; j < listtmp.size(); j++) {
            HashMap record = (HashMap) listtmp.get(j);
            String titleName = "";
            String titleCnName = "";
            String isNotempty = "0";
            String isNumber = "0";
            String valArea = "";
            int mustLen = 0;
            int maxLen = 0;
            for (int m = 0; m < modellist.size(); m++) {
                HashMap hm = (HashMap) modellist.get(m);
                String visible = (String)hm.get("VISIBLE");
                if (visible != null && visible.equals("0")) {//文件中不存在的列
                    continue;
                }
                titleName = hm.get("TITLE_NAME").toString();
                titleCnName = hm.get("TITLE_CNNAME").toString();
                isNotempty = hm.get("IS_NOTEMPTY").toString();
                isNumber = hm.get("IS_NUMBER").toString();
                valArea = hm.get("REMARK") == null ? "" : hm.get("REMARK").toString();
                mustLen = Integer.parseInt(hm.get("MUST_LEN").toString());
                maxLen = Integer.parseInt(hm.get("MAX_LEN").toString());

                String val = (String)record.get(titleName);
                if ("".equals(val)) {
                    if ("1".equals(isNotempty)) {
                        //1行为表头，一行为数据循环从0开始（+1）
                        addMsg(msgs,new Integer(j + 2),titleCnName,"值["+val+"]不能为空");
                        //sbf.append("第" + new Integer(j + 1) + "行，" + titleCnName + "不能为空<br>");
                    }
                } else {
                    if (mustLen > 0 && val.length() != mustLen) {
                        addMsg(msgs, new Integer(j + 2), titleCnName, "值["+val+"]长度应该为" + mustLen + "位");
                        //sbf.append("第" + new Integer(j + 1) + "行，" + titleCnName + "长度应该为" + mustLen + "位<br>");
                    }
                    if ("1".equals(isNumber)) {
                        val = val.replace(",", "");
                        record.put(titleName, val);
                        if (!ExcelUtil.isNumberic(val.trim())) {
                            addMsg(msgs,j+2,titleCnName,"值["+val+"]必须为数字");
                            //sbf.append("第" + new Integer(j + 1) + "行，" + titleCnName + "必须为数字<br>");
                        }
                    }
                    if (!"".equals(valArea) && valArea.indexOf(val) == -1) {
                        addMsg(msgs,j+2,titleCnName,"只能为(" + valArea + ")");
                        //sbf.append("第" + new Integer(j + 1) + "行，" + titleCnName + "只能为(" + valArea + ")<br>");
                    }
                    if (maxLen > 0 && val.length() > maxLen) {
                        addMsg(msgs,j+2,titleCnName,"值["+val+"]长度不能超过" + maxLen + "位");
                        //sbf.append("第" + new Integer(j + 1) + "行，" + titleCnName + "长度不能超过" + maxLen + "位<br>");
                    }
                }
            }
            // 当为""时才进库，否则说明数据格式有问题
            if (sbf.length() == 0) {
                record.put("UPLOAD_ID", uploadFile.getUploadId());
                record.put("FILE_ID", uploadFile.getFileId());
                record.put("BIZ_DATE", new Date());
                li.add(record);
            }
        }
        if (msgs.size() == 0) {
            retmp.put("list", li);
        }
        return retmp;
    }*/
}