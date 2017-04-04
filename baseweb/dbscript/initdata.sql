---初始化数据脚本
-- 菜单之上传文件-FileTransferUpload --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNFileTransferUpload','上传文件','jsp/sys/filetransfer/filetransfer_upload.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 菜单之上传文件列表-FileTransferList --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNFileTransferList','上传文件列表','jsp/sys/filetransfer/filetransfer_list.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 菜单之上传文件删除-FileTransferDelete --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNFileTransferDelete','上传文件删除','jsp/sys/filetransfer/filetransfer_delete.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 菜单之人员维护-StaffMaintain --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNStaffMaintain','人员维护','jsp/sysmgr/staff/staff.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 菜单之日志查询-AuditLogQuery --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNAuditLogQuery','日志查询','jsp/sysmgr/auditlog/auditlog_query.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 菜单之业务线维护-BusinessLineMaintain --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNBusinessLineMaintain','业务线维护','jsp/sysmgr/bizline/bizline.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 菜单之参数表维护-EnumMaintain --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNEnumMaintain','参数表维护','jsp/sysmgr/enum/enum.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 菜单之功能资源点-FunctionAuth --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNFunctionAuth','功能资源点','jsp/sysmgr/func/list.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 菜单之菜单维护-MenuMaintain --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNMenuMaintain','菜单维护','jsp/sysmgr/menu/menu.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 菜单之系统参数管理-ParamsmaintainQuery --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNParamsQuery','系统参数管理','jsp/sysmgr/params/paramsmaintain.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 菜单之角色维护-RoleMaintain --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNRoleMaintain','角色维护','jsp/sysmgr/role/role.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 菜单之角色分配-RoleAssign --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNRoleAssign','角色分配','jsp/sysmgr/role/roleass.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 菜单之快捷菜单定制-ShortCutMenu --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNShortCutMenu','快捷菜单定制','jsp/sysmgr/shortcuts.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 菜单之组织维护-UnitMaintain --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNUnitMaintain','组织维护','jsp/sysmgr/unit/unit.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 菜单之显示图形-SingleDisplay --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNSingleDisplay','显示图形','jsp/demo/charts/single/single_display.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- ????首页初始化-HomepageInitData --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNHomepageInitData','首页初始化','jsp/sys/app/homepage/homepage_initdata.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- ????修改首页布局-HomepageModify --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNHomepageModify','修改首页布局','jsp/sys/app/homepage/homepage_modify.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- ????微件信息-HomepageListWidget --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNHomepageListWidget','微件信息','jsp/sys/app/homepage/homepage_listwidget.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
--Sql监控结果查询-SqlList --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNSqlList','Sql监控结果查询','jsp/monitor/realtime/sql/sql_list.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 首页初始化-BaseinfoInitData --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNBaseinfoInitData','首页初始化','jsp/monitor/welcome/baseinfo/baseinfo_initdata.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 在线用户查询-UserList --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNUserList','在线用户查询','jsp/monitor/realtime/user/user_list.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 文件系统信息-FileList --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNFileList','文件系统信息','jsp/monitor/daily/file/file_list.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 进程信息-ProcList --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNProcList','进程信息','jsp/monitor/daily/proc/proc_list.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 处理器信息-CpumemCinfo --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNCpumemCinfo','处理器信息','jsp/monitor/daily/cpumem/cpumem_cinfo.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 内存信息-CpumemMinfo --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNCpumemMinfo','内存信息','jsp/monitor/daily/cpumem/cpumem_minfo.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 网卡信息-NetstatHwinfo --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNNetstatHwinfo','网卡信息','jsp/monitor/daily/netstat/netstat_hwinfo.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- 流量信息-NetstatFlowInfo --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNNetstatFlowInfo','流量信息','jsp/monitor/daily/netstat/netstat_flowinfo.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
--表空间信息-DbTablespace --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNDbTablespace','表空间信息','jsp/monitor/daily/db/db_tablespace.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------

