---��ʼ�����ݽű�
-- �˵�֮�ϴ��ļ�-FileTransferUpload --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNFileTransferUpload','�ϴ��ļ�','jsp/sys/filetransfer/filetransfer_upload.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- �˵�֮�ϴ��ļ��б�-FileTransferList --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNFileTransferList','�ϴ��ļ��б�','jsp/sys/filetransfer/filetransfer_list.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- �˵�֮�ϴ��ļ�ɾ��-FileTransferDelete --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNFileTransferDelete','�ϴ��ļ�ɾ��','jsp/sys/filetransfer/filetransfer_delete.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- �˵�֮��Աά��-StaffMaintain --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNStaffMaintain','��Աά��','jsp/sysmgr/staff/staff.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- �˵�֮��־��ѯ-AuditLogQuery --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNAuditLogQuery','��־��ѯ','jsp/sysmgr/auditlog/auditlog_query.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- �˵�֮ҵ����ά��-BusinessLineMaintain --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNBusinessLineMaintain','ҵ����ά��','jsp/sysmgr/bizline/bizline.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- �˵�֮������ά��-EnumMaintain --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNEnumMaintain','������ά��','jsp/sysmgr/enum/enum.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- �˵�֮������Դ��-FunctionAuth --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNFunctionAuth','������Դ��','jsp/sysmgr/func/list.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- �˵�֮�˵�ά��-MenuMaintain --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNMenuMaintain','�˵�ά��','jsp/sysmgr/menu/menu.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- �˵�֮ϵͳ��������-ParamsmaintainQuery --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNParamsQuery','ϵͳ��������','jsp/sysmgr/params/paramsmaintain.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- �˵�֮��ɫά��-RoleMaintain --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNRoleMaintain','��ɫά��','jsp/sysmgr/role/role.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- �˵�֮��ɫ����-RoleAssign --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNRoleAssign','��ɫ����','jsp/sysmgr/role/roleass.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- �˵�֮��ݲ˵�����-ShortCutMenu --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNShortCutMenu','��ݲ˵�����','jsp/sysmgr/shortcuts.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- �˵�֮��֯ά��-UnitMaintain --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNUnitMaintain','��֯ά��','jsp/sysmgr/unit/unit.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- �˵�֮��ʾͼ��-SingleDisplay --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNSingleDisplay','��ʾͼ��','jsp/demo/charts/single/single_display.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- ????��ҳ��ʼ��-HomepageInitData --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNHomepageInitData','��ҳ��ʼ��','jsp/sys/app/homepage/homepage_initdata.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- ????�޸���ҳ����-HomepageModify --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNHomepageModify','�޸���ҳ����','jsp/sys/app/homepage/homepage_modify.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- ????΢����Ϣ-HomepageListWidget --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNHomepageListWidget','΢����Ϣ','jsp/sys/app/homepage/homepage_listwidget.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
--Sql��ؽ����ѯ-SqlList --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNSqlList','Sql��ؽ����ѯ','jsp/monitor/realtime/sql/sql_list.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- ��ҳ��ʼ��-BaseinfoInitData --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNBaseinfoInitData','��ҳ��ʼ��','jsp/monitor/welcome/baseinfo/baseinfo_initdata.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- �����û���ѯ-UserList --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNUserList','�����û���ѯ','jsp/monitor/realtime/user/user_list.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- �ļ�ϵͳ��Ϣ-FileList --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNFileList','�ļ�ϵͳ��Ϣ','jsp/monitor/daily/file/file_list.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- ������Ϣ-ProcList --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNProcList','������Ϣ','jsp/monitor/daily/proc/proc_list.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- ��������Ϣ-CpumemCinfo --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNCpumemCinfo','��������Ϣ','jsp/monitor/daily/cpumem/cpumem_cinfo.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- �ڴ���Ϣ-CpumemMinfo --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNCpumemMinfo','�ڴ���Ϣ','jsp/monitor/daily/cpumem/cpumem_minfo.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- ������Ϣ-NetstatHwinfo --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNNetstatHwinfo','������Ϣ','jsp/monitor/daily/netstat/netstat_hwinfo.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
-- ������Ϣ-NetstatFlowInfo --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNNetstatFlowInfo','������Ϣ','jsp/monitor/daily/netstat/netstat_flowinfo.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------
--��ռ���Ϣ-DbTablespace --
INSERT INTO SYS_MENU(MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) 
VALUES ('sys', 'PNDbTablespace','��ռ���Ϣ','jsp/monitor/daily/db/db_tablespace.jsp', 'PNROOT' , 20 , '',    '0', '',  '', '1',  '',  '0' );
---------------------------------------------------------------

