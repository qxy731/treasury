delete from Sys_Enum where enum_id in ('batch_process','batch_node_type','batstep_type');

insert into Sys_Enum (ENUM_ID, ENUM_NAME, ENUM_DESC, LINK_SRC_FLAG, CREATE_USER, CREATE_TIME, LAST_UPD_USER, LAST_UPD_TIME)
values ('batch_node_type', '节点类型', '系统管理 ', '0', 'admin', null, 'admin', null);

insert into Sys_Enum (ENUM_ID, ENUM_NAME, ENUM_DESC, LINK_SRC_FLAG, CREATE_USER, CREATE_TIME, LAST_UPD_USER, LAST_UPD_TIME)
values ('batch_process', '批处理类', '系统管理 ', '0', 'admin', null, 'admin', null);

insert into Sys_Enum (ENUM_ID, ENUM_NAME, ENUM_DESC, LINK_SRC_FLAG, CREATE_USER, CREATE_TIME, LAST_UPD_USER, LAST_UPD_TIME)
values ('batstep_type', '任务执行频率', '系统管理 ', '0', 'admin', null, 'admin', null);

