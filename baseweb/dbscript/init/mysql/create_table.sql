drop table SYS_ARC_GRANT;
drop table SYS_BIZ_MAP;
drop table SYS_BIZ_TYPE;
drop table SYS_BIZ_VALUE;
drop table SYS_ENUM;
drop table SYS_ENUM_ITEM;
drop table SYS_KEYGEN;
drop table SYS_LOGON_INFO;
drop table SYS_LOG_AUDIT;
drop table SYS_MENU;
drop table SYS_POSITION;
drop table SYS_POSI_ASSIGN;
drop table SYS_RES_FUNCTION;
drop table SYS_RES_URL;
drop table SYS_ROLE;
drop table SYS_ROLE_ASSIGN;
drop table SYS_STAFF;
drop table SYS_STAFF_EXT;
drop table SYS_UNIT;
drop table SYS_UNITMAP;
drop table SYS_UNIT_EXT;
drop table BAT_STEP_CHILD;
drop table BAT_STEP;
drop table PUB_STAFF_QUICK_MENU;
drop table PUB_MENU_USEINFO;
drop table SYS_UPLOAD_FILE;
drop table BAT_STEP_MONITOR;
drop table SYS_STAFF_ORG_CHANGE_HIS;
drop table SYS_PARAMS;
drop table SYS_WIDGET;
drop table SYS_HOMEPAGE;
drop table SYS_ROLE_WIDGET;


create table SYS_ARC_GRANT
(
   PRINCIPAL_CODE       VARCHAR(64)            not null,
   PRINCIPAL_TYPE       VARCHAR(16)            not null,
   RES_CODE             VARCHAR(64)            not null,
   RES_TYPE             VARCHAR(255)           not null,
   CHANNEL_CODE         VARCHAR(64),
   RUN_FLAG             CHAR(1),
   ASS_FLAG             CHAR(1),
   CREATE_USER          VARCHAR(16),
   CREATE_TIME          TIMESTAMP,
   LAST_UPD_USER        VARCHAR(16),
   LAST_UPD_TIME        TIMESTAMP,
   EXT1                 VARCHAR(64),
   EXT2                 VARCHAR(64),
   EXT3                 VARCHAR(64),
   constraint A_SYS_ARC_GRANT_PK unique (PRINCIPAL_CODE, PRINCIPAL_TYPE, RES_CODE, RES_TYPE)
);

create table SYS_BIZ_MAP
(
   BIZ_TYPE_ID          VARCHAR(32)            not null,
   BIZ_VALUE            VARCHAR(32)            not null,
   STAFF_ID             VARCHAR(32)            not null,
   CREATE_USER          VARCHAR(16),
   CREATE_TIME          TIMESTAMP,
   primary key (BIZ_TYPE_ID, BIZ_VALUE, STAFF_ID)
);


create table SYS_BIZ_TYPE
(
   BIZ_TYPE_ID          VARCHAR(32)            not null,
   BIZ_TYPE_NAME        VARCHAR(32)            not null,
   REMARK               VARCHAR(128),
   CREATE_USER          VARCHAR(16),
   CREATE_TIME          TIMESTAMP,
   LAST_UPD_USER        VARCHAR(16),
   LAST_UPD_TIME        TIMESTAMP,
   primary key (BIZ_TYPE_ID)
);


create table SYS_BIZ_VALUE
(
   BIZ_TYPE_ID          VARCHAR(32)            not null,
   BIZ_VALUE            VARCHAR(32)            not null,
   BIZ_NAME             VARCHAR(32),
   REMARK               VARCHAR(128),
   CREATE_USER          VARCHAR(16),
   CREATE_TIME          TIMESTAMP,
   LAST_UPD_USER        VARCHAR(16),
   LAST_UPD_TIME        TIMESTAMP,
   primary key (BIZ_TYPE_ID, BIZ_VALUE)
);

create table SYS_ENUM
(
   ENUM_ID              VARCHAR(16)            not null,
   ENUM_NAME            VARCHAR(64),
   ENUM_DESC            VARCHAR(255),
   LINK_SRC_FLAG        CHAR(1)                not null,
   CREATE_USER          VARCHAR(16),
   CREATE_TIME          TIMESTAMP,
   LAST_UPD_USER        VARCHAR(16),
   LAST_UPD_TIME        TIMESTAMP,
   constraint P_SYS_ENUM_PK primary key (ENUM_ID)
);

create table SYS_ENUM_ITEM
(
   ENUM_ID              VARCHAR(16)            not null,
   ITEM_ID              VARCHAR(16)            not null,
   ITEM_VALUE           VARCHAR(255)           not null,
   ITEM_DESC            VARCHAR(255),
   SEQ_ID               INTEGER,
   constraint P_SYS_ENUM_ITEM_PK primary key (ENUM_ID, ITEM_ID)
);

create table SYS_KEYGEN
(
   TABLE_NAME           VARCHAR(32)            not null,
   SEQ_VAL              INTEGER,
   SEQ_LEN              INTEGER,
   MODIFING             CHAR(1),
   primary key (TABLE_NAME)
);

create table SYS_LOGON_INFO
(
   LOGON_ID             VARCHAR(20)            not null,
   STAFF_ID             VARCHAR(16)            not null,
   PASSWORD             VARCHAR(64),
   VALID_FLAG           CHAR(1),
   LOCK_FLAG            CHAR(1),
   FAIL_LOGON_COUNT     INTEGER,
   MAX_FAIL_COUNT       INTEGER,
   PWD_EXPIRE_DAYS      INTEGER,
   PWD_EXPIRE_TIME      TIMESTAMP,
   ACC_EXPIRE_TIME      TIMESTAMP,
   LAST_LOGON_IP        VARCHAR(20),
   LAST_LOGON_TIME      TIMESTAMP,
   primary key (LOGON_ID)
);

create table SYS_LOG_AUDIT
(
   ID                   VARCHAR(32)            not null,
   OPER_STAFFID         VARCHAR(32)            not null,
   ROLE_ID              VARCHAR(32),
   DEVO_STAFFID         VARCHAR(32),
   OPER_CODE            VARCHAR(32),
   OPER_NAME            VARCHAR(32),
   IP_ADDR              VARCHAR(32),
   BIZ_TYPE             VARCHAR(32),
   FUNC_TYPE            CHAR(2),
   EXEC_RESULT          CHAR(1),
   EXEC_TIME            TIMESTAMP              not null,
   LOG_DETAIL           VARCHAR(255),
   primary key (ID)
);

create table SYS_MENU
(
   MENU_ID              VARCHAR(16)            not null,
   NODE_ID              VARCHAR(32)            not null,
   NODE_NAME            VARCHAR(64)            not null,
   NODE_URL             VARCHAR(255),
   PARENT_NODE          VARCHAR(16),
   SEQ_ID               INTEGER,
   NODE_IMG             VARCHAR(128),
   RELA_FLAG            CHAR(1)                not null,
   NODE_TARGET          VARCHAR(10),
   NODE_CMD             VARCHAR(128),
   NODE_VISIBLE         CHAR(1),
   NODE_TOOLTIP         VARCHAR(128),
   HAS_CHILD_FLAG       CHAR(1),
   constraint A_SYS_MENU_PK unique (MENU_ID, NODE_ID)
);

create table SYS_POSITION
(
   POSI_ID              VARCHAR(32)            not null,
   UNIT_ID              VARCHAR(32)            not null,
   ROLE_ID              VARCHAR(16)            not null,
   primary key (POSI_ID)
);


create table SYS_POSI_ASSIGN
(
   POSI_ID              VARCHAR(32)            not null,
   STAFF_ID             VARCHAR(16)            not null,
   DEF_FLAG             CHAR(1),
   primary key (POSI_ID, STAFF_ID)
);


create table SYS_RES_FUNCTION
(
   FUNC_ID              VARCHAR(32)            not null,
   FUNC_CODE            VARCHAR(32),
   JSP_PATH             VARCHAR(255),
   FUNC_NAME            VARCHAR(64),
   FUNC_DESC            VARCHAR(128),
   primary key (FUNC_ID)
);


create table SYS_RES_URL
(
   URL_ID               VARCHAR(32)                not null,
   URL_NAME             VARCHAR(32),
   URL_PATH             VARCHAR(255),
   primary key (URL_ID)
);


create table SYS_ROLE
(
   ROLE_ID              VARCHAR(16)            not null,
   ROLE_NAME            VARCHAR(64),
   ROLE_STATUS          CHAR(1)                not null,
   REMARK               VARCHAR(255),
   PARENT_ROLE_ID       VARCHAR(16),
   CREATE_USER          VARCHAR(16),
   CREATE_TIME          TIMESTAMP,
   LAST_UPD_USER        VARCHAR(16),
   LAST_UPD_TIME        TIMESTAMP,
   EXT1                 VARCHAR(64),
   EXT2                 VARCHAR(64),
   EXT3                 VARCHAR(64),
   constraint P_SYS_ROLE_PK primary key (ROLE_ID)
);


create table SYS_ROLE_ASSIGN
(
   ROLE_ID              VARCHAR(16),
   STAFF_ID             VARCHAR(16),
   DEF_FLAG             CHAR(1),
   REMARK               VARCHAR(255),
   CREATE_USER          VARCHAR(16),
   CREATE_TIME          TIMESTAMP,
   LAST_UPD_USER        VARCHAR(16),
   LAST_UPD_TIME        TIMESTAMP,
   constraint A_SYS_ROLE_ASS_PK unique (ROLE_ID, STAFF_ID)
);

create table SYS_STAFF
(
   STAFF_ID             VARCHAR(16)            not null,
   STAFF_NAME           VARCHAR(32),
   OWNER_UNITID         VARCHAR(32),
   STAFF_LEVEL          SMALLINT,
   STAFF_STATUS         CHAR(1)                not null,
   SEX                  CHAR(1),
   CREATE_USER          VARCHAR(16),
   CREATE_TIME          TIMESTAMP,
   LAST_UPD_USER        VARCHAR(16),
   LAST_UPD_TIME        TIMESTAMP,
   EXT1                 VARCHAR(64),
   EXT2                 VARCHAR(64),
   EXT3                 VARCHAR(64),
   constraint P_SYS_STAFF_PK primary key (STAFF_ID)
);

create table SYS_STAFF_EXT
(
   STAFF_ID             VARCHAR(16)            not null,
   CREATE_USER          VARCHAR(16),
   CREATE_TIME          TIMESTAMP,
   LAST_UPD_USER        VARCHAR(16),
   LAST_UPD_TIME        TIMESTAMP,
   primary key (STAFF_ID)
);

create table SYS_UNIT
(
   UNIT_ID              VARCHAR(32)            not null,
   UNIT_NAME            VARCHAR(64),
   SUPER_UNITID         VARCHAR(32),
   UNIT_LEVEL           CHAR(1),
   UNIT_KIND            CHAR(1)                not null,
   UNIT_STATUS          CHAR(1)                not null,
   UNIT_INDEX           INTEGER,
   UNIT_PATH            VARCHAR(512),
   LEAF_FLAG            CHAR(1),
   CREATE_USER          VARCHAR(16),
   CREATE_TIME          TIMESTAMP,
   LAST_UPD_USER        VARCHAR(16),
   LAST_UPD_TIME        TIMESTAMP,
   EXT1                 VARCHAR(64),
   EXT2                 VARCHAR(64),
   EXT3                 VARCHAR(64),
   EXT4                 VARCHAR(32),
   EXT5                 VARCHAR(32),
   constraint P_SYS_UNIT_PK primary key (UNIT_ID)
);

create table SYS_UNITMAP
(
   UNIT_ID              VARCHAR(32)            not null,
   RELA_UNITID          VARCHAR(32)            not null,
   primary key (UNIT_ID, RELA_UNITID)
);

create table SYS_UNIT_EXT
(
   UNIT_ID              VARCHAR(32)            not null,
   CREATE_USER          VARCHAR(16),
   CREATE_TIME          TIMESTAMP,
   LAST_UPD_USER        VARCHAR(16),
   LAST_UPD_TIME        TIMESTAMP,
   primary key (UNIT_ID)
);




create table BAT_STEP  (
   BATCH_ID             VARCHAR(16)                    not null,
   STEP_ID              INTEGER                         not null,
   STEP_NAME            VARCHAR(32),
   STEP_DESC            VARCHAR(128),
   PROC_FREQ            CHAR(1),
   PROC_CLASS           VARCHAR(128),
   PARAM1               VARCHAR(64),
   PARAM2               VARCHAR(64),
   PARAM3               VARCHAR(64),
   PARAM4               VARCHAR(64),
   PARAM5               VARCHAR(64),
   COMP_COUNT           INTEGER,
   SKIP_STEP_ID         INTEGER,
   DEPEND_IDS           VARCHAR(64),
   REF_TIME             VARCHAR(10),
   EXT1                 VARCHAR(64),
   EXT2                 VARCHAR(64),
   EXT3                 VARCHAR(64),
   constraint PK_BAT_STEP primary key (BATCH_ID, STEP_ID)
);


create table BAT_STEP_CHILD  (
   STEP_ID              INTEGER                         not null,
   CHILD_ID             INTEGER                         not null,
   BATCH_ID             VARCHAR(16)                    not null,
   STEP_NAME            VARCHAR(32),
   STEP_DESC            VARCHAR(128),
   PROC_FREQ            CHAR(1),
   PROC_CLASS           VARCHAR(128),
   PARAM1               VARCHAR(64),
   PARAM2               VARCHAR(64),
   PARAM3               VARCHAR(64),
   PARAM4               VARCHAR(64),
   PARAM5               VARCHAR(64),
   DEPEND_IDS           VARCHAR(64),
   REF_TIME             VARCHAR(10),
   EXT1                 VARCHAR(64),
   EXT2                 VARCHAR(64),
   EXT3                 VARCHAR(64),
   constraint PK_BAT_STEP_CHILD primary key (STEP_ID, CHILD_ID, BATCH_ID)
);

create table PUB_STAFF_QUICK_MENU  (
   STAFF_ID             VARCHAR(32)                     not null,
   UNIT_ID              VARCHAR(32)                     not null,
   ROLE_ID              VARCHAR(32)                     not null,
   MENU_ID              VARCHAR(32)                     not null,
   SEQ                  INTEGER,
   MISC                 VARCHAR(10),
   constraint PK_PUB_STAFF_QUICK_MENU primary key (STAFF_ID, UNIT_ID, ROLE_ID, MENU_ID)
);

create table PUB_MENU_USEINFO  (
   STAFF_ID             VARCHAR(32)                     not null,
   UNIT_ID              VARCHAR(32)                     not null,
   ROLE_ID              VARCHAR(32)                     not null,
   MENU_ID              VARCHAR(32)                     not null,
   USER_COUNT           INTEGER,
   MISC                 VARCHAR(10),
   constraint PK_PUB_MENU_USEINFO primary key (STAFF_ID, UNIT_ID, ROLE_ID, MENU_ID)
);

CREATE TABLE SYS_UPLOAD_FILE (
    UPLOAD_ID VARCHAR(32) NOT NULL ,
    FILE_ID VARCHAR(32) NOT NULL ,
    FILE_NAME VARCHAR(64),
    FILE_SIZE INTEGER,
    FILE_PATH VARCHAR(255),
    DEL_FLAG CHAR(1),
    DOWN_NUM INTEGER,
    CREATE_USER VARCHAR(16),
    CREATE_TIME TIMESTAMP,
    PRIMARY KEY (UPLOAD_ID,FILE_ID)
);

CREATE TABLE BAT_STEP_MONITOR (
   INST_ID	VARCHAR(32)	NOT NULL,
   STEP_ID 	INTEGER	NOT NULL,
   CHILD_ID 	INTEGER	NOT NULL,
   BATCH_ID 	VARCHAR(16)	NOT NULL,
   START_TIME 	TIMESTAMP,
   END_TIME 	TIMESTAMP,
   PROC_STATUS  CHARACTER(1),
   PROC_INFO	VARCHAR(512)
);

CREATE TABLE SYS_STAFF_ORG_CHANGE_HIS (
  STAFF_ID	VARCHAR(32)	NOT NULL,
  OWNER_UNITID	VARCHAR(32)	NOT NULL,
  UPD_USER	VARCHAR(32),
  START_TIME	TIMESTAMP,
  END_TIME	TIMESTAMP
);

CREATE TABLE SYS_PARAMS (
    PARA_ID VARCHAR(32),
    PARA_RANK VARCHAR(10),
    PARA_VALUE VARCHAR(255),
    REMARK VARCHAR(512),
    PRIMARY KEY (PARA_ID)
);


CREATE TABLE SYS_WIDGET (
    WID VARCHAR(10) NOT NULL ,
    TITLE VARCHAR(32),
    DATA_SERVICE VARCHAR(32),
    TYPE VARCHAR(32),
    PARAMA VARCHAR(32),
    PARAMB VARCHAR(32),
    PARAMC VARCHAR(128),
    REMARK VARCHAR(128),
    PRIMARY KEY (WID)
);


CREATE TABLE SYS_HOMEPAGE (
    USER_ID VARCHAR(32) NOT NULL ,
    LAYOUT_DATA VARCHAR(1024),
    PRIMARY KEY (USER_ID)
);

CREATE TABLE SYS_ROLE_WIDGET  (
   WID                  VARCHAR(10)                    not null,
   ROLE_ID              VARCHAR(16)                    not null,
   OPER_USER            VARCHAR(16),
   OPER_TIME            DATE,
   constraint PK_SYS_ROLE_WIDGET primary key (WID, ROLE_ID)
);