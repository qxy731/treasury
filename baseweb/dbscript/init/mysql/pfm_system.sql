/*==============================================================*/
/* Table: IDL_EXE_SORT                                          */
/*==============================================================*/
create table IDL_EXE_SORT
(
   SNO                  int not null,
   DEST_TAR_CODE        varchar(32) not null,
   TAR_CODE             varchar(32),
   RELA_TAR_CODE        varchar(32),
   DEP_TAR_CODE         varchar(32) not null,
   primary key (SNO, DEST_TAR_CODE)
);

/*==============================================================*/
/* Table: PFM_TM_POINT_AVER_RELA                                */
/*==============================================================*/
create table PFM_TM_POINT_AVER_RELA
(
   P_TARCODE            varchar(32) not null,
   A_TARCODE            varchar(32),
   DAY_SCOPE            varchar(32) not null,
   primary key (P_TARCODE, DAY_SCOPE)
);

/*==============================================================*/
/* Table: PFM_TM_PROP_DEF                                       */
/*==============================================================*/
create table PFM_TM_PROP_DEF
(
   TAR_CODE             varchar(32) not null,
   TAR_NAME             varchar(100),
   TAR_SCOPE            varchar(8) comment '参见代码表 TargetScope',
   TAR_BIZ_TYPE         varchar(10) comment '参见代码表 TarBizType',
   TAR_SORT_CODE        varchar(32),
   TAR_STATUS           varchar(10) comment '参见代码表 TarStatus',
   TAR_DESC             varchar(256),
   MISC                 varchar(10),
   REMARK               varchar(512),
   CREATE_USER          varchar(32),
   CREATE_TIME          timestamp,
   CREATE_ORG           varchar(32),
   LAST_UPD_USER        varchar(32),
   LAST_UPD_ORG         varchar(32),
   LAST_UPD_TIME        timestamp,
   primary key (TAR_CODE)
);

/*==============================================================*/
/* Index: TAR_NAME_PROP                                         */
/*==============================================================*/
create index TAR_NAME_PROP on PFM_TM_PROP_DEF
(
   TAR_NAME
);

/*==============================================================*/
/* Table: PFM_TM_QTY_DEF                                        */
/*==============================================================*/
create table PFM_TM_QTY_DEF
(
   BS_FLAG              varchar(10) comment '参见代码表 YesOrNo',
   MEAS_UNIT_CODE       varchar(10) comment '参见代码表 MeasUnitCode',
   MEAS_DEF_CODE        varchar(10) comment '参见代码表 MeasDefCode',
   TAR_TYPE             varchar(10) comment '参见代码表 TarType
            时点指标、日均指标等',
   TAR_PROPERTY         varchar(10) comment '参见代码表 DataSorCode',
   STAT_SCOPE_CODE      varchar(10) comment '参见代码表 StatScopeCode',
   PROC_DATE_CODE       varchar(10) comment '参见代码表 ProcDateCode',
   TAR_PROC_DATE        varchar(10) comment '指标指定处理日期',
   STORE_DATE           varchar(10) comment '参见代码表 StoreDateCode',
   PRDTYPE_CODE         varchar(32) comment '参见产品表',
   SUBJ_CODE            varchar(10) comment '会计科目代号',
   TAR_CODE             varchar(32) not null,
   TAR_NAME             varchar(100),
   TAR_SCOPE            varchar(8) comment '参见代码表 TargetScope',
   TAR_BIZ_TYPE         varchar(10) comment '参见代码表 TarBizType',
   TAR_SORT_CODE        varchar(32),
   TAR_STATUS           varchar(10) comment '参见代码表 TarStatus',
   TAR_DESC             varchar(256),
   MISC                 varchar(10),
   REMARK               varchar(512),
   CREATE_USER          varchar(32),
   CREATE_TIME          timestamp,
   CREATE_ORG           varchar(32),
   LAST_UPD_USER        varchar(32),
   LAST_UPD_ORG         varchar(32),
   LAST_UPD_TIME        timestamp,
   DAY_SCOPE            varchar(10),
   primary key (TAR_CODE)
);

/*==============================================================*/
/* Index: TAR_NAME_QTY                                          */
/*==============================================================*/
create index TAR_NAME_QTY on PFM_TM_QTY_DEF
(
   TAR_NAME
);

/*==============================================================*/
/* Table: PFM_TM_QTY_EXP_DEF                                    */
/*==============================================================*/
create table PFM_TM_QTY_EXP_DEF
(
   TAR_CODE             varchar(32) not null,
   TAR_SCOPE            varchar(8) not null comment '参见代码表 TargetScope',
   CALC_EXP             varchar(1024),
   SQL_EXP              varchar(4000),
   MISC                 varchar(10),
   REMARK               varchar(512),
   CREATE_USER          varchar(32),
   CREATE_TIME          timestamp,
   CREATE_ORG           varchar(32),
   LAST_UPD_USER        varchar(32),
   LAST_UPD_TIME        timestamp,
   LAST_UPD_ORG         varchar(32),
   primary key (TAR_CODE, TAR_SCOPE)
);

/*==============================================================*/
/* Table: PFM_TM_QTY_EXP_RELA                                   */
/*==============================================================*/
create table PFM_TM_QTY_EXP_RELA
(
   TAR_CODE             varchar(32) not null,
   RELA_TAR_CODE        varchar(32) not null,
   primary key (TAR_CODE, RELA_TAR_CODE)
);

/*==============================================================*/
/* Table: PFM_TM_QTY_ORG                                        */
/*==============================================================*/
create table PFM_TM_QTY_ORG
(
   FST_M_VALUE          numeric(20,2),
   FST_T_VALUE          numeric(20,2),
   FST_Y_VALUE          numeric(20,2),
   FST_Q_VALUE          numeric(20,2),
   FST_HY_VALUE         numeric(20,2),
   REMARK               varchar(512),
   MISC                 varchar(10),
   ORG_ID               varchar(32) not null,
   TAR_CODE             varchar(32) not null,
   STORE_DATE           varchar(10) not null comment '参见代码表 StoreDateCode',
   TAR_VALUE            numeric(20,2),
   TAR_DATE             date comment '指标日期',
   LOAD_DATE            date,
   DATA_SOR_CODE        varchar(10) comment '参见代码表 DataSorCode',
   primary key (ORG_ID, TAR_CODE, STORE_DATE)
);

/*==============================================================*/
/* Table: PFM_TM_QTY_ORG_HIS                                    */
/*==============================================================*/
create table PFM_TM_QTY_ORG_HIS
(
   FST_M_VALUE          numeric(20,2),
   FST_T_VALUE          numeric(20,2),
   FST_Y_VALUE          numeric(20,2),
   FST_Q_VALUE          numeric(20,2),
   FST_HY_VALUE         numeric(20,2),
   REMARK               varchar(512),
   MISC                 varchar(10),
   ORG_ID               varchar(32) not null,
   TAR_CODE             varchar(32) not null,
   STORE_DATE           varchar(10) not null comment '参见代码表 StoreDateCode',
   TAR_VALUE            numeric(20,2),
   TAR_DATE             date comment '指标日期',
   LOAD_DATE            date,
   DATA_SOR_CODE        varchar(10) comment '参见代码表 DataSorCode',
   primary key (ORG_ID, TAR_CODE, STORE_DATE)
);

/*==============================================================*/
/* Index: Relationship_124_FK2                                  */
/*==============================================================*/
create index PFM_TM_QTY_ORG_HIS_FK on PFM_TM_QTY_ORG_HIS
(
   TAR_CODE
);

/*==============================================================*/
/* Table: PFM_TM_QTY_ORG_PLAN                                   */
/*==============================================================*/
create table PFM_TM_QTY_ORG_PLAN
(
   MISC                 varchar(10),
   REMARK               varchar(512),
   CREATE_USER          varchar(32),
   CREATE_TIME          timestamp,
   CREATE_ORG           varchar(32),
   LAST_UPD_USER        varchar(32),
   LAST_UPD_TIME        timestamp,
   LAST_UPD_ORG         varchar(32),
   ORG_ID               varchar(32) not null,
   TAR_CODE             varchar(32) not null,
   STORE_DATE           varchar(10) not null comment '参见代码表 StoreDateCode',
   TAR_VALUE            numeric(20,2),
   TAR_DATE             date comment '指标日期',
   LOAD_DATE            date,
   PLAN_YEAR            varchar(10) not null,
   PLAN_UNIT            varchar(10) not null,
   DATA_SOR_CODE        varchar(10) comment '参见代码表 DataSorCode',
   TYPE                 varchar(10),
   SUBTYPE              varchar(10),
   primary key (ORG_ID, TAR_CODE, STORE_DATE)
);

/*==============================================================*/
/* Table: PFM_TM_QTY_STF                                        */
/*==============================================================*/
create table PFM_TM_QTY_STF
(
   FST_Y_VALUE          numeric(20,2),
   FST_Q_VALUE          numeric(20,2),
   FST_HY_VALUE         numeric(20,2),
   FST_M_VALUE          numeric(20,2),
   FST_T_VALUE          numeric(20,2),
   REMARK               varchar(512),
   MISC                 varchar(10),
   STAFF_ID             varchar(32) not null,
   BL_ORG               varchar(32) comment '账户归属机构',
   TAR_CODE             varchar(32) not null,
   STORE_DATE           varchar(10) not null comment '参见代码表 StoreDateCode',
   TAR_VALUE            numeric(20,2),
   TAR_DATE             date comment '指标日期',
   LOAD_DATE            date,
   DATA_SOR_CODE        varchar(10) comment '参见代码表 DataSorCode',
   primary key (STAFF_ID, TAR_CODE, STORE_DATE)
);

/*==============================================================*/
/* Table: PFM_TM_QTY_STF_BASE                                   */
/*==============================================================*/
create table PFM_TM_QTY_STF_BASE
(
   MISC                 varchar(10),
   REMARK               varchar(512),
   CREATE_USER          varchar(32),
   CREATE_TIME          timestamp,
   CREATE_ORG           varchar(32),
   LAST_UPD_USER        varchar(32),
   LAST_UPD_TIME        timestamp,
   LAST_UPD_ORG         varchar(32),
   STAFF_ID             varchar(32) not null,
   BL_ORG               varchar(32) comment '账户归属机构',
   TAR_CODE             varchar(32) not null,
   STORE_DATE           varchar(10) not null comment '参见代码表 StoreDateCode',
   TAR_VALUE            numeric(20,2),
   TAR_DATE             date comment '指标日期',
   LOAD_DATE            date,
   DATA_SOR_CODE        varchar(10) comment '参见代码表 DataSorCode',
   primary key (STAFF_ID, TAR_CODE, STORE_DATE)
);

/*==============================================================*/
/* Table: PFM_TM_QTY_STF_HIS                                    */
/*==============================================================*/
create table PFM_TM_QTY_STF_HIS
(
   FST_Y_VALUE          numeric(20,2),
   FST_Q_VALUE          numeric(20,2),
   FST_HY_VALUE         numeric(20,2),
   FST_M_VALUE          numeric(20,2),
   FST_T_VALUE          numeric(20,2),
   REMARK               varchar(512),
   MISC                 varchar(10),
   STAFF_ID             varchar(32) not null,
   BL_ORG               varchar(32) comment '账户归属机构',
   TAR_CODE             varchar(32) not null,
   STORE_DATE           varchar(10) not null comment '参见代码表 StoreDateCode',
   TAR_VALUE            numeric(20,2),
   TAR_DATE             date comment '指标日期',
   LOAD_DATE            date,
   DATA_SOR_CODE        varchar(10) comment '参见代码表 DataSorCode',
   primary key (STAFF_ID, TAR_CODE, STORE_DATE)
);

/*==============================================================*/
/* Index: Relationship_122_FK2                                  */
/*==============================================================*/
create index PFM_TM_QTY_STF_HIS_FK on PFM_TM_QTY_STF_HIS
(
   TAR_CODE
);

/*==============================================================*/
/* Table: PFM_TM_QTY_STF_PLAN                                   */
/*==============================================================*/
create table PFM_TM_QTY_STF_PLAN
(
   MISC                 varchar(10),
   REMARK               varchar(512),
   CREATE_USER          varchar(32),
   CREATE_TIME          timestamp,
   CREATE_ORG           varchar(32),
   LAST_UPD_USER        varchar(32),
   LAST_UPD_TIME        timestamp,
   LAST_UPD_ORG         varchar(32),
   STAFF_ID             varchar(32) not null,
   BL_ORG               varchar(32) comment '账户归属机构',
   TAR_CODE             varchar(32) not null,
   STORE_DATE           varchar(10) not null comment '参见代码表 StoreDateCode',
   TAR_VALUE            numeric(20,2),
   TAR_DATE             date comment '指标日期',
   LOAD_DATE            date,
   DATA_SOR_CODE        varchar(10) comment '参见代码表 DataSorCode',
   TYPE                 varchar(10),
   SUBTYPE              varchar(10),
   PLAN_YEAR            varchar(10) not null,
   PLAN_UNIT            varchar(10) not null,
   primary key (STAFF_ID, TAR_CODE, PLAN_YEAR, PLAN_UNIT)
);

/*==============================================================*/
/* Table: PFM_TM_SORT                                           */
/*==============================================================*/
create table PFM_TM_SORT
(
   TAR_SORT_CODE        varchar(32) not null,
   TAR_SORT_NAME        varchar(100),
   TAR_SORT_DESC        varchar(512),
   TAR_SORT_LVL         varchar(10) comment '参见代码表 TarSortLvl',
   PARENT_SORT_CODE     varchar(32),
   MISC                 varchar(10),
   REMARK               varchar(512),
   CREATE_USER          varchar(32),
   CREATE_TIME          timestamp,
   CREATE_ORG           varchar(32),
   LAST_UPD_USER        varchar(32),
   LAST_UPD_TIME        timestamp,
   LAST_UPD_ORG         varchar(32),
   TAR_BIZ_TYPE         varchar(10),
   primary key (TAR_SORT_CODE)
);

/*==============================================================*/
/* Table: PUB_MENU_USEINFO                                      */
/*==============================================================*/
create table PUB_MENU_USEINFO
(
   STAFF_ID             varchar(32) not null,
   UNIT_ID              varchar(32) not null,
   ROLE_ID              varchar(32) not null,
   MENU_ID              varchar(32) not null,
   USER_COUNT           int,
   MISC                 varchar(10),
   primary key (STAFF_ID, UNIT_ID, ROLE_ID, MENU_ID)
);


INSERT INTO `sys_enum` VALUES ('education_type', '学历', '学历', '0', null, '2017-03-05 10:52:11', null, '2017-03-05 10:52:11');

INSERT INTO `sys_enum_item` VALUES ('education_type', '1', '中专', '中专', '1');
INSERT INTO `sys_enum_item` VALUES ('education_type', '2', '大专', '大专', '2');
INSERT INTO `sys_enum_item` VALUES ('education_type', '3', '本科', '本科', '3');
INSERT INTO `sys_enum_item` VALUES ('education_type', '4', '硕士', '硕士', '4');
INSERT INTO `sys_enum_item` VALUES ('education_type', '5', '博士', '博士', '5');
INSERT INTO `sys_enum_item` VALUES ('education_type', '9', '其他', '其他', '6');
