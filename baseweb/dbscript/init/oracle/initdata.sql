insert into SYS_STAFF (STAFF_ID, STAFF_NAME, OWNER_UNITID, STAFF_LEVEL, STAFF_STATUS, SEX, CREATE_USER, CREATE_TIME, LAST_UPD_USER, LAST_UPD_TIME, EXT1, EXT2, EXT3)
values ('admin', '管理员', '000000', 1, '1', 'M', 'admin', to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, to_timestamp('06-02-2012 14:26:24.468000', 'dd-mm-yyyy hh24:mi:ss.ff'), null, null, null);

insert into SYS_LOGON_INFO (STAFF_ID, LOGON_ID, PASSWORD, FAIL_LOGON_COUNT, MAX_FAIL_COUNT, PWD_EXPIRE_DAYS, PWD_EXPIRE_TIME, ACC_EXPIRE_TIME, LAST_LOGON_IP, LAST_LOGON_TIME, VALID_FLAG, LOCK_FLAG)
values ('admin', 'admin', '111111', 0, 10, 100, null, null, null, null, '1', '0');

insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','m_record','工作日历','jsp/mkt/workplace/workcal/workcal_monthquery.jsp','m5',0,null,'1',null,null,'1',null,'0');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','m5_9','快捷定制','jsp/sysmgr/shortcuts.jsp','m5',6,null,'1',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','sysmonitor','系统监控',null,'sysroot',10000,null,'1',null,null,'1',null,'1');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','perform','性能监控',null,'sysmonitor',2,null,'1',null,null,'1',null,'1');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','useronline','在线用户','jsp/monitor/realtime/user/user_list.jsp','perform',2,null,'1',null,null,'1',null,'0');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','files','文件系统','jsp/monitor/daily/file/file_list.jsp','daily',5,null,'1',null,null,'1',null,'0');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','cpumem','CPU监控','jsp/monitor/daily/cpumem/cpumem_cinfo.jsp','daily',8,null,'1',null,null,'1',null,'0');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','netinfo','网卡监控','jsp/monitor/daily/netstat/netstat_hwinfo.jsp','daily',12,null,'1',null,null,'1',null,'0');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','daily','运维监控',null,'sysmonitor',3,null,'1',null,null,'1',null,'1');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','procsys','进程信息','jsp/monitor/daily/proc/proc_list.jsp','daily',2,null,'1',null,null,'1',null,'0');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','mem','内存监控','jsp/monitor/daily/cpumem/cpumem_minfo.jsp','daily',10,null,'1',null,null,'1',null,'0');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','bizline','业务线维护','jsp/sysmgr/bizline/bizline.jsp','m5',10,null,'1',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','waittingTask','待办任务','jsp/flow/work/work_query.jsp','PNROOT',0,null,'1',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','auditlog','日志查询','jsp/sysmgr/auditlog/auditlog_query.jsp','m5',30,null,'1',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','batch','批处理设置','jsp/sysmgr/batch/batstep.jsp','m5',15,null,'1',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','sysroot','主页菜单',null,null,0,null,'1',null,null,'1',null,'1');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','m5','系统管理',null,'sysroot',null,null,'1',null,null,'1',null,'1');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','m5_2','组织维护','jsp/sysmgr/unit/unit.jsp','m5',1,'/images/1.png','0',null,'oo','1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','m5_1','菜单维护','sys/menu!initialize.action','m5',3,'/images/5.png','0',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','m5_3','角色维护','jsp/sysmgr/role/role.jsp','m5',2,'/images/2.png','0',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','m5_4','参数表维护','jsp/sysmgr/enum/enum.jsp','m5',4,'/images/1.png','0',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','m5_5','人员维护','jsp/sysmgr/staff/staff.jsp','m5',5,'/images/2.png','0',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','m5_8','功能点资源','jsp/sysmgr/func/list.jsp','m5',9,null,'1',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','m5_6','角色分配','jsp/sysmgr/role/roleass.jsp','m5',6,null,'1',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','PNROOT','工作流',null,'sysroot',1000,null,'0',null,null,'1',null,'1');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','workflow','请假申请','jsp/demo/workflow/shenqing.jsp','PNROOT',0,null,'1',null,null,'1',null,null);
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','baseinfo','基本信息','jsp/monitor/welcome/baseinfo/baseinfo_initdata.jsp','sysmonitor',0,null,'1',null,null,'1',null,'0');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','SQL','SQL性能','jsp/monitor/realtime/sql/sql_list.jsp','perform',1,null,'1',null,null,'1',null,'0');
insert into SYS_MENU (MENU_ID,NODE_ID,NODE_NAME,NODE_URL,PARENT_NODE,SEQ_ID,NODE_IMG,RELA_FLAG,NODE_TARGET,NODE_CMD,NODE_VISIBLE,NODE_TOOLTIP,HAS_CHILD_FLAG) values ('sys','db','数据库信息','jsp/monitor/daily/db/db_tablespace.jsp','daily',14,null,'1',null,null,'1',null,'0');


insert into SYS_PARAMS (PARA_ID,PARA_RANK,PARA_VALUE,REMARK) values ('ROLE_MIXED','SYS','1','角色混合标志0/1');



insert into SYS_ENUM(ENUM_ID,ENUM_NAME,ENUM_DESC,LINK_SRC_FLAG,CREATE_USER,CREATE_TIME,LAST_UPD_USER,LAST_UPD_TIME) values ('log_func_type','操作类型',null,'0',null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'),null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into SYS_ENUM(ENUM_ID,ENUM_NAME,ENUM_DESC,LINK_SRC_FLAG,CREATE_USER,CREATE_TIME,LAST_UPD_USER,LAST_UPD_TIME) values ('unit_kind','组织类型',null,'1',null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'),null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into SYS_ENUM(ENUM_ID,ENUM_NAME,ENUM_DESC,LINK_SRC_FLAG,CREATE_USER,CREATE_TIME,LAST_UPD_USER,LAST_UPD_TIME) values ('log_exec_result','执行结果',null,'1',null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'),null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into SYS_ENUM(ENUM_ID,ENUM_NAME,ENUM_DESC,LINK_SRC_FLAG,CREATE_USER,CREATE_TIME,LAST_UPD_USER,LAST_UPD_TIME) values ('log_biz_type','业务类型',null,'0',null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'),null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into SYS_ENUM(ENUM_ID,ENUM_NAME,ENUM_DESC,LINK_SRC_FLAG,CREATE_USER,CREATE_TIME,LAST_UPD_USER,LAST_UPD_TIME) values ('batstep_type','步骤类型',null,'0',null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'),null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into SYS_ENUM(ENUM_ID,ENUM_NAME,ENUM_DESC,LINK_SRC_FLAG,CREATE_USER,CREATE_TIME,LAST_UPD_USER,LAST_UPD_TIME) values ('valid_type','有效标',null,'0',null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'),null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into SYS_ENUM(ENUM_ID,ENUM_NAME,ENUM_DESC,LINK_SRC_FLAG,CREATE_USER,CREATE_TIME,LAST_UPD_USER,LAST_UPD_TIME) values ('yes_no','是否标志','是否标志','0',null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'),null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'));
insert into SYS_ENUM(ENUM_ID,ENUM_NAME,ENUM_DESC,LINK_SRC_FLAG,CREATE_USER,CREATE_TIME,LAST_UPD_USER,LAST_UPD_TIME) values ('sex','性别',null,'0',null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'),null,to_timestamp('06-02-2012 14:01:59.656000', 'dd-mm-yyyy hh24:mi:ss.ff'));

insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('yes_no','0','否',null,1);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('yes_no','1','是',null,2);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('batstep_type','D','每日',null,2);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('batstep_type','Y','每年',null,1);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('unit_kind','2','部门',null,2);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('unit_kind','1','机构',null,1);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_exec_result','0','失败',null,2);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_exec_result','1','成功',null,1);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_biz_type','unit','组织维护',null,1);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_biz_type','LOGON','登陆',null,4);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_func_type','DE','删除',null,3);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_biz_type','enum','参数表维护',null,3);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_biz_type','role','角色维护',null,2);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_func_type','IN','新增',null,1);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_func_type','QU','查询',null,4);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_func_type','UP','修改',null,2);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('log_func_type','NO','普通操作',null,5);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('valid_type','0','无效',null,1);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('valid_type','1','有效',null,2);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('sex','F','女',null,2);
insert into SYS_ENUM_ITEM(ENUM_ID,ITEM_ID,ITEM_VALUE,ITEM_DESC,SEQ_ID) values ('sex','M','男',null,1);

insert into "SYS_BIZ_TYPE"("BIZ_TYPE_ID","BIZ_TYPE_NAME","REMARK","CREATE_USER","CREATE_TIME","LAST_UPD_USER","LAST_UPD_TIME") values ('ENT_PSN','对公对私业务线','','admin',null,'admin',null);
insert into "SYS_BIZ_VALUE"("BIZ_TYPE_ID","BIZ_VALUE","BIZ_NAME","REMARK","CREATE_USER","CREATE_TIME","LAST_UPD_USER","LAST_UPD_TIME") values ('ENT_PSN','1','对公业务线',null,'admin',null,null,null);
insert into "SYS_BIZ_VALUE"("BIZ_TYPE_ID","BIZ_VALUE","BIZ_NAME","REMARK","CREATE_USER","CREATE_TIME","LAST_UPD_USER","LAST_UPD_TIME") values ('ENT_PSN','0','对私和对公业务线',null,'admin',null,'admin',null);
insert into "SYS_BIZ_VALUE"("BIZ_TYPE_ID","BIZ_VALUE","BIZ_NAME","REMARK","CREATE_USER","CREATE_TIME","LAST_UPD_USER","LAST_UPD_TIME") values ('ENT_PSN','2','对私业务线',null,'admin',null,null,null);


Insert into SYS_WIDGET (WID,TITLE,DATA_SERVICE,TYPE,PARAMA,PARAMB,PARAMC,REMARK,CLOSEABLE) values ('ZW3','图表-饼图',null,'chart','Pie2D',null,null,'图形显示微件',null);
Insert into SYS_WIDGET (WID,TITLE,DATA_SERVICE,TYPE,PARAMA,PARAMB,PARAMC,REMARK,CLOSEABLE) values ('ZW4','图表-柱状图',null,'chart','Column2D',null,null,null,null);
Insert into SYS_WIDGET (WID,TITLE,DATA_SERVICE,TYPE,PARAMA,PARAMB,PARAMC,REMARK,CLOSEABLE) values ('ZW5','图表-曲线图',null,'chart','Line',null,null,null,null);
Insert into SYS_WIDGET (WID,TITLE,DATA_SERVICE,TYPE,PARAMA,PARAMB,PARAMC,REMARK,CLOSEABLE) values ('ZW6','图表-曲线图',null,'chart','Area2D',null,null,null,null);
Insert into SYS_WIDGET (WID,TITLE,DATA_SERVICE,TYPE,PARAMA,PARAMB,PARAMC,REMARK,CLOSEABLE) values ('ZW7','角色列表',null,'rolegrid','','',null,null,null);
Insert into SYS_WIDGET (WID,TITLE,DATA_SERVICE,TYPE,PARAMA,PARAMB,PARAMC,REMARK,CLOSEABLE) values ('ZW2','计算器',null,'calc','','',null,'计算器微件',null);

