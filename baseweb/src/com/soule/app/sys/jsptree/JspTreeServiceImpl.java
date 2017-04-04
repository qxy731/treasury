package com.soule.app.sys.jsptree;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.soule.MsgConstants;
import com.soule.base.service.ServiceException;
import com.soule.comm.tools.AppUtils;
import com.soule.comm.tools.FileUtil;

/**
 * 树状显示JSP业务操作
 */
@Service
public class JspTreeServiceImpl implements IJspTreeService {

    /**
     * 根据当前目录，获得子目录和文件
     */
    public JspTreeListFileOut listFile(JspTreeListFileIn in) throws ServiceException {
        JspTreeListFileOut out = new JspTreeListFileOut();
        List<JspTreeListPo> output = new ArrayList<JspTreeListPo>();
        if (in.getCurrPath() == null ||in.getCurrPath().length() == 0 ) {
            JspTreeListPo po = new JspTreeListPo();
            po.setPath("jsp");
            po.setName("jsp");
            po.setPathType("1");
            output.add(po);
        }
        else {
            File root = new File(in.getRootPath(),in.getCurrPath());
            File[] files = root.listFiles();
            for (File file: files) {
                if (file.isDirectory()) {
                    JspTreeListPo po = new JspTreeListPo();
                    po.setName(file.getName());
                    po.setPath(file.getAbsolutePath().substring(in.getRootPath().length()));
                    String path = po.getPath().replace('\\', '/');
                    if (!path.startsWith("/")) {
                        path = "/" + path ;
                    }
                    po.setPath(path);
                    po.setPathType("1");
                    output.add(po);
                }
                else if (file.getName().endsWith(".jsp")) {
                    JspTreeListPo po = new JspTreeListPo();
                    String title = getJspTitle(file);
                    if (StringUtils.isEmpty(title)){
                        po.setName(file.getName());
                    }
                    else{
                        po.setName(file.getName() + "(" + title+")");
                    }
                    po.setPath(file.getAbsolutePath().substring(in.getRootPath().length()));
                    String path = po.getPath().replace('\\', '/');
                    if (!path.startsWith("/")) {
                        path = "/" + path ;
                    }
                    po.setPath(path);
                    po.setPathType("0");
                    output.add(po);
                }
            }
        }
        Collections.sort(output,new Comparator<JspTreeListPo>() {
            public int compare(JspTreeListPo o1, JspTreeListPo o2) {
                int e1 = o2.getPathType().compareTo(o1.getPathType());
                int ret = 0;
                if (e1 == 0) {
                    ret = o1.getPath().compareTo(o2.getPath());
                }
                else {
                    ret = e1;
                }
                return ret;
            }
        });
        out.setList(output);
        AppUtils.setResult(out, MsgConstants.I0000);
        return out;
    }

    private String getJspTitle(File file) {
        String fcontent;
        try {
            byte[] bs = FileUtil.getFileContent(file);
            fcontent = new String(bs,"UTF-8");
        } catch (Exception e) {
            fcontent = "";
        }
        //简单的识别
        int sidx = fcontent.indexOf("<title>");
        int eidx = fcontent.indexOf("</title>",sidx+1);
        if ( sidx > 0 && (eidx-sidx)>7 && (eidx-sidx)< 60 ) {
            return (fcontent.substring(sidx + 7,eidx));
        }
        return "";
    }

}
