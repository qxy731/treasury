insert into SYS_STAFF (STAFF_ID, STAFF_NAME, OWNER_UNITID, STAFF_LEVEL, STAFF_STATUS, SEX, CREATE_USER, CREATE_TIME, LAST_UPD_USER, LAST_UPD_TIME, EXT1, EXT2, EXT3)
values ('admin', '����Ա', '000000', 1, '1', 'M', 'admin', to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('06-02-2012 14:26:24.468000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null, null);

insert into SYS_LOGON_INFO (STAFF_ID, LOGON_ID, PASSWORD, FAIL_LOGON_COUNT, MAX_FAIL_COUNT, PWD_EXPIRE_DAYS, PWD_EXPIRE_TIME, ACC_EXPIRE_TIME, LAST_LOGON_IP, LAST_LOGON_TIME, VALID_FLAG, LOCK_FLAG)
values ('admin', 'admin', '111111', 0, 10, 100, null, null, null, null, '1', '0');

insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','m_record','��������','jsp/mkt/workplace/workcal/workcal_monthquery.jsp','m5',0,null,'1',null,null,'1',null,'0');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','m5_9','��ݶ���','jsp/sysmgr/shortcuts.jsp','m5',6,null,'1',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','sysmonitor','ϵͳ���',null,'sysroot',10000,null,'1',null,null,'1',null,'1');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','perform','���ܼ��',null,'sysmonitor',2,null,'1',null,null,'1',null,'1');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','useronline','�����û�','jsp/monitor/realtime/user/user_list.jsp','perform',2,null,'1',null,null,'1',null,'0');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','files','�ļ�ϵͳ','jsp/monitor/daily/file/file_list.jsp','daily',5,null,'1',null,null,'1',null,'0');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','cpumem','CPU���','jsp/monitor/daily/cpumem/cpumem_cinfo.jsp','daily',8,null,'1',null,null,'1',null,'0');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','netinfo','�������','jsp/monitor/daily/netstat/netstat_hwinfo.jsp','daily',12,null,'1',null,null,'1',null,'0');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','daily','��ά���',null,'sysmonitor',3,null,'1',null,null,'1',null,'1');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','procsys','������Ϣ','jsp/monitor/daily/proc/proc_list.jsp','daily',2,null,'1',null,null,'1',null,'0');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','mem','�ڴ���','jsp/monitor/daily/cpumem/cpumem_minfo.jsp','daily',10,null,'1',null,null,'1',null,'0');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','bizline','ҵ����ά��','jsp/sysmgr/bizline/bizline.jsp','m5',10,null,'1',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','waittingTask','��������','jsp/flow/work/work_query.jsp','PNROOT',0,null,'1',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','auditlog','��־��ѯ','jsp/sysmgr/auditlog/auditlog_query.jsp','m5',30,null,'1',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','batch','����������','jsp/sysmgr/batch/batstep.jsp','m5',15,null,'1',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','sysroot','��ҳ�˵�',null,null,0,null,'1',null,null,'1',null,'1');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','m5','ϵͳ����',null,'sysroot',null,null,'1',null,null,'1',null,'1');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','m5_2','��֯ά��','jsp/sysmgr/unit/unit.jsp','m5',1,'/images/1.png','0',null,'oo','1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','m5_1','�˵�ά��','sys/menu!initialize.action','m5',3,'/images/5.png','0',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','m5_3','��ɫά��','jsp/sysmgr/role/role.jsp','m5',2,'/images/2.png','0',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','m5_4','������ά��','jsp/sysmgr/enum/enum.jsp','m5',4,'/images/1.png','0',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','m5_5','��Աά��','jsp/sysmgr/staff/staff.jsp','m5',5,'/images/2.png','0',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','m5_8','���ܵ���Դ','jsp/sysmgr/func/list.jsp','m5',9,null,'1',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','m5_6','��ɫ����','jsp/sysmgr/role/roleass.jsp','m5',6,null,'1',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','PNROOT','������',null,'sysroot',1000,null,'0',null,null,'1',null,'1');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','workflow','�������','jsp/demo/workflow/shenqing.jsp','PNROOT',0,null,'1',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','baseinfo','������Ϣ','jsp/monitor/welcome/baseinfo/baseinfo_initdata.jsp','sysmonitor',0,null,'1',null,null,'1',null,'0');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','SQL','SQL����','jsp/monitor/realtime/sql/sql_list.jsp','perform',1,null,'1',null,null,'1',null,'0');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','db','���ݿ���Ϣ','jsp/monitor/daily/db/db_tablespace.jsp','daily',14,null,'1',null,null,'1',null,'0');


insert into SYS_PARAMS (PARA_ID,PARA_RANK,PARA_VALUE,REMARK) values ('ROLE_MIXED','SYS','1','��ɫ��ϱ�־0/1');



insert into SYS_ENUM(ENUM_ID,ENUM_NAME,ENUM_DESC,LINK_SRC_FLAG,CREATE_USER,CREATE_TIME,LAST_UPD_USER,LAST_UPD_TIME) values ('log_func_type','��������',null,'0',null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'),null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into SYS_ENUM(ENUM_ID,ENUM_NAME,ENUM_DESC,LINK_SRC_FLAG,CREATE_USER,CREATE_TIME,LAST_UPD_USER,LAST_UPD_TIME) values ('unit_kind','��֯����',null,'1',null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'),null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into SYS_ENUM(ENUM_ID,ENUM_NAME,ENUM_DESC,LINK_SRC_FLAG,CREATE_USER,CREATE_TIME,LAST_UPD_USER,LAST_UPD_TIME) values ('log_exec_result','ִ�н��',null,'1',null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'),null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into SYS_ENUM(ENUM_ID,ENUM_NAME,ENUM_DESC,LINK_SRC_FLAG,CREATE_USER,CREATE_TIME,LAST_UPD_USER,LAST_UPD_TIME) values ('log_biz_type','ҵ������',null,'0',null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'),null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into SYS_ENUM(ENUM_ID,ENUM_NAME,ENUM_DESC,LINK_SRC_FLAG,CREATE_USER,CREATE_TIME,LAST_UPD_USER,LAST_UPD_TIME) values ('batstep_type','��������',null,'0',null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'),null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into SYS_ENUM(ENUM_ID,ENUM_NAME,ENUM_DESC,LINK_SRC_FLAG,CREATE_USER,CREATE_TIME,LAST_UPD_USER,LAST_UPD_TIME) values ('valid_type','��Ч��',null,'0',null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'),null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into SYS_ENUM(ENUM_ID,ENUM_NAME,ENUM_DESC,LINK_SRC_FLAG,CREATE_USER,CREATE_TIME,LAST_UPD_USER,LAST_UPD_TIME) values ('yes_no','�Ƿ��־','�Ƿ��־','0',null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'),null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into SYS_ENUM(ENUM_ID,ENUM_NAME,ENUM_DESC,LINK_SRC_FLAG,CREATE_USER,CREATE_TIME,LAST_UPD_USER,LAST_UPD_TIME) values ('sex','�Ա�',null,'0',null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'),null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'));

insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('yes_no','0','��',null,1);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('yes_no','1','��',null,2);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('batstep_type','D','ÿ��',null,2);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('batstep_type','Y','ÿ��',null,1);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('unit_kind','2','����',null,2);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('unit_kind','1','����',null,1);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_exec_result','0','ʧ��',null,2);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_exec_result','1','�ɹ�',null,1);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_biz_type','unit','��֯ά��',null,1);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_biz_type','LOGON','��½',null,4);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_func_type','DE','ɾ��',null,3);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_biz_type','enum','������ά��',null,3);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_biz_type','role','��ɫά��',null,2);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_func_type','IN','����',null,1);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_func_type','QU','��ѯ',null,4);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_func_type','UP','�޸�',null,2);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_func_type','NO','��ͨ����',null,5);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('valid_type','0','��Ч',null,1);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('valid_type','1','��Ч',null,2);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('sex','F','Ů',null,2);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('sex','M','��',null,1);

insert into "SYS_BIZ_TYPE"("BIZ_TYPE_ID","BIZ_TYPE_NAME","REMARK","CREATE_USER","CREATE_TIME","LAST_UPD_USER","LAST_UPD_TIME") values ('ENT_PSN','�Թ���˽ҵ����','','admin',null,'admin',null);
insert into "SYS_BIZ_VALUE"("BIZ_TYPE_ID","BIZ_VALUE","BIZ_NAME","REMARK","CREATE_USER","CREATE_TIME","LAST_UPD_USER","LAST_UPD_TIME") values ('ENT_PSN','1','�Թ�ҵ����',null,'admin',null,null,null);
insert into "SYS_BIZ_VALUE"("BIZ_TYPE_ID","BIZ_VALUE","BIZ_NAME","REMARK","CREATE_USER","CREATE_TIME","LAST_UPD_USER","LAST_UPD_TIME") values ('ENT_PSN','0','��˽�ͶԹ�ҵ����',null,'admin',null,'admin',null);
insert into "SYS_BIZ_VALUE"("BIZ_TYPE_ID","BIZ_VALUE","BIZ_NAME","REMARK","CREATE_USER","CREATE_TIME","LAST_UPD_USER","LAST_UPD_TIME") values ('ENT_PSN','2','��˽ҵ����',null,'admin',null,null,null);


Insert into SYS_WIDGET (WID,TITLE,DATA_SERVICE,TYPE,PARAMA,PARAMB,PARAMC,REMARK,CLOSEABLE) values ('ZW3','ͼ��-��ͼ',null,'chart','Pie2D',null,null,'ͼ����ʾ΢��',null);
Insert into SYS_WIDGET (WID,TITLE,DATA_SERVICE,TYPE,PARAMA,PARAMB,PARAMC,REMARK,CLOSEABLE) values ('ZW4','ͼ��-��״ͼ',null,'chart','Column2D',null,null,null,null);
Insert into SYS_WIDGET (WID,TITLE,DATA_SERVICE,TYPE,PARAMA,PARAMB,PARAMC,REMARK,CLOSEABLE) values ('ZW5','ͼ��-����ͼ',null,'chart','Line',null,null,null,null);
Insert into SYS_WIDGET (WID,TITLE,DATA_SERVICE,TYPE,PARAMA,PARAMB,PARAMC,REMARK,CLOSEABLE) values ('ZW6','ͼ��-����ͼ',null,'chart','Area2D',null,null,null,null);
Insert into SYS_WIDGET (WID,TITLE,DATA_SERVICE,TYPE,PARAMA,PARAMB,PARAMC,REMARK,CLOSEABLE) values ('ZW7','��ɫ�б�',null,'rolegrid','','',null,null,null);
Insert into SYS_WIDGET (WID,TITLE,DATA_SERVICE,TYPE,PARAMA,PARAMB,PARAMC,REMARK,CLOSEABLE) values ('ZW2','������',null,'calc','','',null,'������΢��',null);

