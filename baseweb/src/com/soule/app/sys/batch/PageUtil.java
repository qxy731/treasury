package com.soule.app.sys.batch;

import com.soule.base.service.ServiceInput;

public class PageUtil {
    public static PagePo getPagePo(ServiceInput inputHead) {
        PagePo p = new PagePo();
        if (inputHead != null) {
            p.offset = (inputHead.getPageNo() -1 )* inputHead.getPageSize();
            p.pageSize = inputHead.getPageSize();
        }
        return p;
    }
    static class PagePo {
        public PagePo() {
            offset = 0;
            pageSize = 10;
        }
        int offset;
        int pageSize;
    }
}

