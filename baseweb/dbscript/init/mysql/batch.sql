drop table if exists BAT_STEP;

/*==============================================================*/
/* Table: BAT_STEP                                              */
/*==============================================================*/
create table BAT_STEP
(
   BATCH_ID             varchar(16) not null,
   STEP_ID              int not null,
   PARENT_ID            int,
   STEP_TYPE            char(1),
   STEP_NO              int,
   STEP_NAME            varchar(32),
   STEP_DESC            varchar(128),
   PROC_FREQ            char(1) comment '参考代码表 proc_freq',
   PROC_CLASS           varchar(128),
   PARAM1               varchar(255),
   PARAM2               varchar(255),
   PARAM3               varchar(255),
   PARAM4               varchar(255),
   PARAM5               varchar(255),
   COMP_COUNT           int,
   SKIP_STEP_ID         int,
   DEPEND_IDS           varchar(64),
   REF_TIME             varchar(10),
   EXT1                 varchar(64),
   EXT2                 varchar(64),
   EXT3                 varchar(64),
   key AK_BAT_STEP_PK (BATCH_ID, STEP_ID)
);


drop table if exists BAT_PARAMS;

/*==============================================================*/
/* Table: BAT_PARAMS                                            */
/*==============================================================*/
create table BAT_PARAMS
(
   BATCH_ID             varchar(16) not null,
   PARA_ID              varchar(32) not null,
   PARA_NAME            varchar(32),
   PARA_VALUE           varchar(255),
   REMARK               varchar(512),
   primary key (BATCH_ID, PARA_ID)
);


drop table if exists BAT_TX_LOG;

/*==============================================================*/
/* Table: BAT_TX_LOG                                            */
/*==============================================================*/
create table BAT_TX_LOG
(
   LOG_ID               int not null,
   TX_DATE              varchar(10),
   INST_ID              int,
   BATCH_ID             varchar(16),
   STEP_ID              varchar(32),
   EXE_TIME             timestamp,
   RET_CODE             varchar(16),
   RET_MSG              varchar(512),
   key AK_BAT_TX_LOG_PK (LOG_ID)
);



drop table if exists BAT_STEP_MONITOR;

/*==============================================================*/
/* Table: BAT_STEP_MONITOR                                      */
/*==============================================================*/
create table BAT_STEP_MONITOR
(
   INST_ID              int,
   STEP_ID              int,
   START_TIME           timestamp,
   END_TIME             timestamp,
   PROC_STATUS          char(1) comment '参考代码表 proc_status',
   PROC_INFO            varchar(512),
   key AK_primarykey (INST_ID, STEP_ID)
);

alter table BAT_STEP_MONITOR add constraint FK_Relationship_10 foreign key ()
      references BAT_STEP on delete restrict on update restrict;


