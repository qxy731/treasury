package com.soule.crm.pfm.param.indexdata;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.app.table.SysUploadFilePo;
import com.soule.base.media.DbAccessException;
import com.soule.base.service.IDefaultService;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.DateFormatCalendar;
import com.soule.comm.tools.DateFormatDefine;
import com.soule.crm.comm.IUserManager;
import com.soule.crm.pfm.param.paraminfo.ParaminfoQPo;
import com.soule.crm.tools.TimeTool;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.Alignment;
import jxl.read.biff.BiffException;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
/**
 * 指标数据补录业务操作
 */
@Service
public class IndexDataServiceImpl implements IIndexDataService {

    private final static Log logger = LogFactory.getLog(IndexDataServiceImpl.class);
    // 数据库通用操作
    @Autowired 
    IDefaultService sDefault;
    
    @Autowired
	IUserManager um;

    /**
     * 指标数据补录信息的查询
     */
    public IndexDataQueryOut query(IndexDataQueryIn in)
        throws ServiceException {
        IndexDataQueryOut out = new IndexDataQueryOut();
        if (in == null) {
			AppUtils.setResult(out, MsgConstants.E0001, "");
			return out;
		}
        String[] objects = in.getObjectId().split("','");
        String[] indexCodes = in.getIndexCode().split("','");
        IndexDataPo paramPo = new IndexDataPo();
        paramPo.setRecoreDate(in.getRecoreDate());
        if(in.getObjectType().equals("1")){
        	try {
    			for(String o : objects){
    				for(String i : indexCodes) {
    					paramPo.setObjectId(o);
    					paramPo.setIndexCode(i);
    					IndexDataPo po = (IndexDataPo)sDefault.getIbatisMediator().findById("indexdataorg.getPfmIndexDataMaulByKey", paramPo);
    					if(po == null) {
    						IndexDataPo indexDataPo = new IndexDataPo();
    						indexDataPo.setIndexCode(i);
    						indexDataPo.setObjectId(o);
    						indexDataPo.setRecoreDate(in.getRecoreDate());
    						sDefault.getIbatisMediator().save("indexdataorg.savePfmIndexDataMaul", indexDataPo);
    					}
    				}
    			}
    			AppUtils.setResult(out, MsgConstants.I0000);
    		} catch (DbAccessException e) {
    			logger.error("DB", e);
    			AppUtils.setResult(out, MsgConstants.E0002, e.getMessage());
    		}
        } else if(in.getObjectType().equals("2")){
        	try {
    			for(String o : objects){
    				for(String i : indexCodes) {
    					paramPo.setObjectId(o);
    					paramPo.setIndexCode(i);
    					IndexDataPo po = (IndexDataPo)sDefault.getIbatisMediator().findById("indexdatastf.getPfmIndexDataMaulByKey", paramPo);
    					if(po == null) {
    						IndexDataPo indexDataPo = new IndexDataPo();
    						indexDataPo.setIndexCode(i);
    						indexDataPo.setObjectId(o);
    						indexDataPo.setRecoreDate(in.getRecoreDate());
    						sDefault.getIbatisMediator().save("indexdatastf.savePfmIndexDataMaul", indexDataPo);
    					}
    				}
    			}
    			AppUtils.setResult(out, MsgConstants.I0000);
    		} catch (DbAccessException e) {
    			logger.error("DB", e);
    			AppUtils.setResult(out, MsgConstants.E0002, e.getMessage());
    		}
        }
        
        return out;
    }
    
    /**
     * 导出模板
     */
	public IndexDataQueryOut exportTemplate(IndexDataQueryIn in)
			throws ServiceException {
		IndexDataQueryOut out = new IndexDataQueryOut();
		if (in == null) {
			AppUtils.setResult(out, MsgConstants.E0001, "");
			return out;
		}
		//String[] objects = in.getObjectId().split(",");
		//String[] objectNames = in.getObjectName().split(",");
		String[] indexCodes = in.getIndexCode().split(",");
		try {
			String fileName = "/upload/ExportFiles/"
					+ TimeTool.paserString(new Date(), "yyyy-MM-dd_HHmmss")
					+ ".xls";
			System.out.println(ServletActionContext.getServletContext().getRealPath("/") + fileName
					+ "===========");
			File file = new File(ServletActionContext.getServletContext().getRealPath("/") + fileName);
			if (!file.exists())file.createNewFile();
			WritableWorkbook book = null;
			// 创建一个可写的工作簿
			book = Workbook.createWorkbook(new FileOutputStream(file));
			// 添加一个工作表
			WritableSheet ws = book.createSheet("指标补录数据", 1);

			// 添加带有字型Formatting的对象,用来输出标题行
			jxl.write.WritableFont wf = new jxl.write.WritableFont(
					WritableFont.ARIAL, 10, WritableFont.BOLD, false);
			jxl.write.WritableCellFormat wcf = new jxl.write.WritableCellFormat(
					wf);
			wcf.setBackground(jxl.format.Colour.GRAY_25);
			wcf.setAlignment(Alignment.CENTRE);
			
			ws.setColumnView(0, 15);
			ws.setColumnView(1, 15);
			for(int x=0;x<indexCodes.length;x++){
				ws.setColumnView(x+2, indexCodes[x].length()*3);
			}

			/*if (in.getObjectType().equals("2")) {
				jxl.write.Label label = new jxl.write.Label(0, 0, "客户号", wcf);
				ws.addCell(label);
				label = new jxl.write.Label(1, 0, "客户号", wcf);
				ws.addCell(label);
			} else if (in.getObjectType().equals("1")) {*/
				jxl.write.Label label = new jxl.write.Label(0, 0, "国库代码", wcf);
				ws.addCell(label);
				label = new jxl.write.Label(1, 0, "国库名称", wcf);
				ws.addCell(label);
			/*}*/

			int i = 2;
			for (String s : indexCodes) {
				jxl.write.Label label1 = new jxl.write.Label(i, 0, s, wcf);
				ws.addCell(label1);
				i++;
			}
			i = 1;
			/*for (int j = 0; j < objects.length; j++) {
				jxl.write.Label label = new jxl.write.Label(0, i, objects[j]);
				ws.addCell(label);
				label = new jxl.write.Label(1, i, objectNames[j]);
				ws.addCell(label);
				i++;
			}*/
			 if (book != null) {
                 book.write();
                 book.close();
             }
			out.setTemplateFile(fileName);
			AppUtils.setResult(out, MsgConstants.I0000);
			
		} catch (Exception e) {
			logger.error("DB", e);
			AppUtils.setResult(out, MsgConstants.E0002, e.getMessage());
		}
		return out;
	}
	
	private static HashMap<String, String> paramMap = new HashMap<String, String>();
	private static HashMap<String, String> paramDateMap = new HashMap<String, String>();
	
	private String getMapValue(String indexName,String type){
		String returnVal = "";
		try {
			if(type.equals("code")){
				returnVal = paramMap.get(indexName);
				if(returnVal == null || returnVal.trim().length()<1){
					List indexCodeList = sDefault.getIbatisMediator().find("paraminfo.getPfmParamIndexCode", indexName);
					if(indexCodeList != null && indexCodeList.size() > 0){
						ParaminfoQPo paraminfoQPo = (ParaminfoQPo)indexCodeList.get(0);
						returnVal = paraminfoQPo.getIndexCode();
						paramMap.put(indexName, paraminfoQPo.getIndexCode());
						paramDateMap.put(indexName, paraminfoQPo.getSaveDate());//保存日期类型
					}
				}
				
			}else if(type.equals("date")){
				returnVal = paramDateMap.get(indexName);
				if(returnVal == null || returnVal.trim().length()<1){
					List indexCodeList = sDefault.getIbatisMediator().find("paraminfo.getPfmParamIndexCode", indexName);
					if(indexCodeList != null && indexCodeList.size() > 0){
						ParaminfoQPo paraminfoQPo = (ParaminfoQPo)indexCodeList.get(0);
						returnVal = paraminfoQPo.getSaveDate();
						paramMap.put(indexName, paraminfoQPo.getIndexCode());
						paramDateMap.put(indexName, paraminfoQPo.getSaveDate());//保存日期类型
					}
				}
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnVal;
	}
	
	private Date getBizDate(String indexName, Date recoreDate){
		Date returnDate = null;
		String d = getMapValue(indexName, "date").trim();
		String dateStr = TimeTool.paserString(recoreDate, "yyyyMMdd");
		String year = dateStr.substring(0, 4);
		String month = dateStr.substring(4, 6);
		String day = dateStr.substring(6);
		if(d.equals("MONTH")){//月
			returnDate = TimeTool.paserDate(year+"-"+month+"-"+TimeTool.getLastDayOfMonth(year+"-"+month+"-"+"01"), "yyyy-MM-dd");
		}else if(d.equals("TENDAYS")){//旬
			if(Integer.parseInt(day) < 11){
				returnDate = TimeTool.paserDate(year+"-"+month+"-10");
			}else if(Integer.parseInt(day) < 21){
				returnDate = TimeTool.paserDate(year+"-"+month+"-20");
			}else{
				returnDate = TimeTool.paserDate(year+"-"+month+"-"+TimeTool.getLastDayOfMonth(year+"-"+month+"-"+"01"), "yyyy-MM-dd");
			}
		}else if(d.equals("QUARTER")){//季
			if(Integer.parseInt(month) < 4){
				returnDate = TimeTool.paserDate(year+"-03-31");
			}else if(Integer.parseInt(month) < 7){
				returnDate = TimeTool.paserDate(year+"-06-30");
			}else if(Integer.parseInt(month) < 10){
				returnDate = TimeTool.paserDate(year+"-09-30");
			}else{
				returnDate = TimeTool.paserDate(year+"-12-31");
			}
		}else if(d.equals("HALFYEAR")){//半年
			if(Integer.parseInt(month) < 7){
				returnDate = TimeTool.paserDate(year+"-06-30");
			}else{
				returnDate = TimeTool.paserDate(year+"-12-31");
			}
		}else if(d.equals("YEAR")){//年
			returnDate = TimeTool.paserDate(year+"-12-31");
		}else{
			returnDate = recoreDate;
		}
		return returnDate;
	}
	
	private Date getBizDate1(String indexName, Date recoreDate){
		Date returnDate = null;
		String str = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			List<ParaminfoQPo> indexCodeList = sDefault.getIbatisMediator().find("paraminfo.getPfmParamIndexCode", indexName);
			if(indexCodeList != null && indexCodeList.size() > 0){
				ParaminfoQPo paraminfoQPo = (ParaminfoQPo)indexCodeList.get(0);
				str = paraminfoQPo.getSaveDate();
				//DateFormatCalendar.getInstance(recoreDate);
				//returnDate = sdf.parse(DateFormatCalendar.getBusinessDate(str));
				DateFormatCalendar dfc2 =new DateFormatCalendar(recoreDate,DateFormatDefine.FORMAT_YYYYMMDD_02);
				String date = dfc2.getBusinessDate(str);
				returnDate = sdf.parse(date.replaceAll("-", ""));
			}
		} catch (DbAccessException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return returnDate;
	}
	
	
    /**
     * 解析指标数据补录文件并更新表数据
     */
    public IndexDataReadXlsFileOut readXlsFile(IndexDataReadXlsFileIn in)
        throws ServiceException {
        IndexDataReadXlsFileOut out = new IndexDataReadXlsFileOut();
		try {
			HashMap<String, String> map = new HashMap<String, String>(1);
			map.put("uploadId", in.getUploadId());
			//map.put("fileId", in.getFileId());
			List<SysUploadFilePo> list = sDefault.getIbatisMediator().find("upload.getSysUploadFile", map);
			if(list != null && list.size() > 0) {
				SysUploadFilePo filePo = list.get(0);
				String filePath = filePo.getFilePath().trim() + "/" + filePo.getFileId().trim();	
				File file = new File(filePath);
		        if (!file.exists()) {
		        	AppUtils.setResult(out, MsgConstants.E0001, "上传文件未找到！");
		            return out;
		        }
		        List<Map<String,Object>> resultList = ReadFromXls(file);
		        Map<String,Object> columnsName = resultList.get(0);
	        	for(int i = 1; i < resultList.size(); i++) {
		        	Map<String,Object> dataMap = resultList.get(i);
		        	for(int j = 2; j < columnsName.size(); j++) {
		        		String indexName = (String)columnsName.get(String.valueOf(j));
		        		IndexDataPo indexDataPo = new IndexDataPo();
		        		indexDataPo.setRecoreDate(getBizDate1(indexName,in.getRecoreDate()));
		        		indexDataPo.setObjectId((String)(dataMap.get(String.valueOf(0))));
			        	indexDataPo.setIndexName(indexName);
			        	IndexDataPo result = (IndexDataPo)sDefault.getIbatisMediator().findById("indexdatastf.getPfmIndexDataMaulByKey2", indexDataPo);
    					if(result == null) {
    						String indexCode = getMapValue(indexName,"code");
    						indexDataPo.setIndexCode(indexCode);
    						indexDataPo.setIndexVal((Double.valueOf((String)dataMap.get(String.valueOf(j)))));
				        	indexDataPo.setLoadDate(new Date());
				        	indexDataPo.setCreateUser(um.getStaffId());
				        	sDefault.getIbatisMediator().update("indexdatastf.savePfmTmQtyOrg", indexDataPo);
    					}else{
    						result.setIndexVal((Double.valueOf((String)dataMap.get(String.valueOf(j)))));
    						result.setLoadDate(new Date());
    						result.setCreateUser(um.getStaffId());
				        	sDefault.getIbatisMediator().update("indexdatastf.updPfmTmQtyOrg", result);
    					}
	        	    }
		        }
			        //更新所属机构
			       // sDefault.getIbatisMediator().update("indexdatastf.updPfmIndexDataMaulOrg",null);
				
			}
			AppUtils.setResult(out, MsgConstants.I0000);
		} catch (DbAccessException e) {
			logger.error("DB", e);
			AppUtils.setResult(out, MsgConstants.E0002, e.getMessage());
		} catch (BiffException e) {
			AppUtils.setResult(out, "解析Xls出错了！", e.getMessage());
		} catch (IOException e) {
			AppUtils.setResult(out, "解析Xls出错了！", e.getMessage());
		}
        return out;
    }
    
    public List<Map<String,Object>> ReadFromXls(File file) throws BiffException, IOException {
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
    	WorkbookSettings workbookSettings = new WorkbookSettings();
        workbookSettings.setEncoding("ISO-8859-1"); //关键代码，解决中文乱码
    	Workbook wb = Workbook.getWorkbook(file,workbookSettings);
        Sheet sheet = wb.getSheet(0);
        if (sheet == null) {
            throw new RuntimeException("文件 不存在");
        }
        int maxRow = sheet.getRows();
        int maxCol = sheet.getRow(0).length;
        for (int r = 0; r < maxRow; r++) {
        	Map<String,Object> map = new HashMap<String, Object>();
        	Cell[] cells = sheet.getRow(r);
        	for(int s = 0; s < maxCol; s++) {
        		String content = cells[s].getContents();
        		map.put(String.valueOf(s), content);
        	}
        	list.add(r,map);
        }
    	return list;
    }

    
    public static void main(String[] args) {
		/*File file = new File("D:\\workspaces\\treasury\\.metadata\\.plugins\\org.eclipse.wst.server.core\\tmp0\\wtpwebapps\\baseweb\\upload/ExportFiles/2017-10-10_033930.xls");
		System.out.println(file.getAbsolutePath());
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}*/
    	try {
    		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
			Date date = sdf.parse("2017-12-21");
			System.out.println(sdf.format(date));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
