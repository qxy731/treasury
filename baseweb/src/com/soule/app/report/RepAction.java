package com.soule.app.report;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import org.apache.struts2.json.annotations.JSON;

import com.soule.base.action.BaseAction;
import com.soule.base.service.ServiceException;
import com.soule.comm.ParamConstants;
import com.soule.comm.xls.XlsFileManager;
import com.soule.crm.tools.TimeTool;


public abstract class RepAction<IN> extends BaseAction {
    private static final long serialVersionUID = 1L;
    protected String fileName;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    @JSON(serialize=false)
    public InputStream getDownloadFile() throws FileNotFoundException {
        File uploadPath = new File(ParamConstants.DOWNLOAD_ROOT);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        String realPath = ParamConstants.DOWNLOAD_ROOT + "/" + fileName;
        File file = new File(realPath);
        InputStream in = new FileInputStream(file);
        return in;
    }
    @JSON(serialize=false)
    public String getDownloadChineseFileName() {
        String downloadChineseFileName = fileName;
        try {
            downloadChineseFileName = new String(downloadChineseFileName.getBytes(), "ISO8859-1");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return downloadChineseFileName;
    }

    public String execute() throws Exception {
        exportHtml();
        return null;
    }

    public String export() throws IOException, Exception {
        try {
            exportFile();
//            appUtils.saveAuditLog("export", "报表导出", BizType.REPM, FunctionType.EXPORT, ExecuteResult.SUCCESS);
            return "succDown";
        } catch (ServiceException e) {
            this.retMsg = e.getErrorMsg();
            return "errorpage";
        } catch (Exception e) {
//        	appUtils.saveAuditLog("export", "报表导出", BizType.REPM, FunctionType.EXPORT, ExecuteResult.FAIL);
            this.retMsg = e.getMessage();
            return "errorpage";
        }
    }

    /**
     * 导出excel
     * 
     * @param context
     * @param temp
     * @param templateKey
     * @param out
     * @throws Exception
     */
    public void exportFile() throws Exception {
        File uploadPath = new File(ParamConstants.DOWNLOAD_ROOT);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        XlsFileManager mgr = new XlsFileManager();
        String[] templates = getXlsTemplate();
        String temp = templates[0];
        String templateKey = templates[1];
        fileName = templates[2];
        String templatePath = request.getSession().getServletContext().getRealPath("/") + "template/" + temp;
        String filepath = ParamConstants.DOWNLOAD_ROOT + "/" + fileName;

        IN in = initQueryIn();
        HashMap context = fileDatas(in, null);
        mgr.exportFile(templatePath, templateKey, filepath, context);
    }
    
    public void exportFileAjax()throws Exception {
        File uploadPath = new File(ParamConstants.DOWNLOAD_ROOT);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs();
        }
        XlsFileManager mgr = new XlsFileManager();
        String[] templates = getXlsTemplate();
        String temp = templates[0];
        String templateKey = templates[1];
        fileName = templates[2];
        String templatePath = request.getSession().getServletContext().getRealPath("/") + "template/" + temp;
        String excelFileName = TimeTool.paserString(new Date(), "yyyyMMddHHmmssS")+fileName.substring(fileName.lastIndexOf("."), fileName.length());
        String filepath = request.getSession().getServletContext().getRealPath("/")+"upload/ExportFiles/"+excelFileName;
        String pathDirec = filepath.substring(0, filepath.lastIndexOf("/"));
        if(pathDirec!=null && !"".equals(pathDirec)){
            File tem = new File(pathDirec);
            if(!tem.isDirectory()){
                tem.mkdirs();
            }
        }
        IN in = initQueryIn();
        HashMap context = fileDatas(in, null);
        mgr.exportFile(templatePath, templateKey, filepath, context);       
        String file = java.net.URLEncoder.encode(fileName, "UTF-8");
        String path = request.getContextPath()+"/downloadFileTemplate.action?fileName="+excelFileName+"&downloadChineseFileName="+file;
        
        PrintWriter write = null;
        response.setContentType("text/html;charset=UTF-8");
        write = response.getWriter();
        if(true){
            write.append("{\"retCode\":\"I0000\",\"retMsg\":\"导出成功。\",\"path\":\""+path+"\"}");
        }else{
            write.append("{\"retCode\":\"E0000\",\"retMsg\":\"导出失败。\"}");
        }
        write.flush();
        write.close();
    }
    
    /**
     * 预览数据
     * 
     * @param context
     * @param outStream
     * @param temp
     * @param templateKey
     * @param out
     * @throws Exception
     */
    public void exportHtml() throws Exception {
        XlsFileManager mgr = new XlsFileManager();
        String[] templates = getXlsTemplate();
        String temp = templates[0];
        String templateKey = templates[1];
        String templatePath = request.getSession().getServletContext().getRealPath("/") + "template/" + temp;
        OutputStream outStream = response.getOutputStream();
        try {
            response.setContentType("text/html; charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            // 预览页面返回按钮
            /*
             * String s =
             * "<html><style type='text/css'>.l-button{border: solid 1px #A3C0E8;height:20px; overflow:hidden; line-height:20px;background: #E0EDFF url(../images/controls/button-bg.gif) repeat-x center; width:80px;text-align:center;color: #2C4D79;  text-decoration:none; cursor:pointer;} .l-button-over,.l-button:hover {background: #FFBE76 url(../images/controls/button-bg-over.gif) repeat-x center; border-color:#D6A886;}</style><table width='100%'><tr><td align='right' style=\"text-align:right;\"><input class='l-button' type='button' value='返 回' onclick='window.history.go(-1)' /></td></tr></table></html>"
             * ; outStream.write(s.getBytes());
             */
            IN in = initQueryIn();
            HashMap context = fileDatas(in, "1");
            mgr.exportHtml(templatePath, templateKey, outStream, context, null, null);
        } finally {
            outStream.close();
        }
    }

    /**
     * 初始化页面传过来的参数
     * 
     * @throws ServiceException
     */
    protected abstract IN initQueryIn() throws ServiceException;

    /**
     * 填充数据
     * 
     * @return
     * @throws Exception
     */
    protected abstract HashMap fileDatas(IN in, String vflag) throws Exception;

    /**
     * 获取模板名称
     * 
     * @return
     */
    protected abstract String[] getXlsTemplate();
}
