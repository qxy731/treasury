
delete from Sys_Enum_Item where enum_id in ('batch_process','batch_node_type','batstep_type');

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batch_node_type', '0', '普通任务节点', null, 1);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batch_node_type', '1', '并发执行节点', null, 2);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batch_node_type', '2', '顺序执行节点', null, 3);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batch_process', 'A', 'com.soule.comm.batch.step.EmptyProcess', '空处理类', 1);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batch_process', 'B', 'com.soule.comm.batch.step.SequenceProcess', '顺序处理类', 2);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batch_process', 'C', 'com.soule.comm.batch.step.ParallelProcess', '并行处理类', 3);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batch_process', 'D', 'com.soule.comm.batch.step.RunBatchProcess', '存储过程处理类', 4);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batch_process', 'E', 'com.soule.comm.batch.step.RunBatch4Process', '存储过程处理类4', 5);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batch_process', 'F', 'com.soule.comm.batch.step.RunBatch4sProcess', '存储过程处理类4s', 6);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batch_process', 'G', 'com.soule.batch.step.AppFlagSetProcess', '应用数据库中SYS_PARAMS参数表标志设置', 7);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batch_process', 'H', 'com.soule.batch.step.CheckMartFlagProcess', '等待集市数据库中SYS_PARAMS中参数', 8);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batch_process', 'I', 'com.soule.batch.step.FileReceivedProcess', '文件到达判断', 9);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batch_process', 'J', 'com.soule.batch.step.FlagFileReceivedProcess', '单个标志文件到达判断', 10);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batch_process', 'K', 'com.soule.batch.step.MartFlagSetProcess', '集市数据库中SYS_PARAMS参数表标志设置', 11);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batch_process', 'L', 'com.soule.batch.step.RunCmdProcess', '本地脚本调用', 12);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batch_process', 'M', 'com.soule.batch.step.RunRemoteCmdProcess', '通过telnet远程脚本调用', 13);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batch_process', 'N', 'com.soule.batch.step.AppRunProceProcess', '调用应用数据库数据同步存储过程', 14);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batch_process', 'O', 'com.soule.batch.step.RunMartCmdProcess', '通过telnet远程脚本调用', 15);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batstep_type', 'D', '每日执行', null, 1);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batstep_type', 'M', '每月执行(需指定日)', null, 2);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batstep_type', 'N', '每月末执行', null, 3);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batstep_type', 'Y', '每年末执行', null, 4);

insert into Sys_Enum_Item (ENUM_ID, ITEM_ID, ITEM_VALUE, ITEM_DESC, SEQ_ID)
values ('batstep_type', 'W', '每周执行(需指定日)', null, 5);

