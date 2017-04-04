--生成建库脚本
------------------------------------------- 业务线类别 -------------------------------------------
-- DROP TABLE IF EXISTS SYS_BIZ_TYPE;
CREATE TABLE SYS_BIZ_TYPE (
    BIZ_TYPE_ID VARCHAR2(32) NOT NULL ,
    BIZ_TYPE_NAME VARCHAR2(32) NOT NULL ,
    REMARK VARCHAR2(128),
    CREATE_USER VARCHAR2(16),
    CREATE_TIME TIMESTAMP,
    LAST_UPD_USER VARCHAR2(16),
    LAST_UPD_TIME TIMESTAMP,
    PRIMARY KEY (BIZ_TYPE_ID)
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS SYS_BIZ_VALUE;
CREATE TABLE SYS_BIZ_VALUE (
    BIZ_TYPE_ID VARCHAR2(32) NOT NULL ,
    BIZ_VALUE VARCHAR2(32) NOT NULL ,
    BIZ_NAME VARCHAR2(32),
    REMARK VARCHAR2(128),
    CREATE_USER VARCHAR2(16),
    CREATE_TIME TIMESTAMP,
    LAST_UPD_USER VARCHAR2(16),
    LAST_UPD_TIME TIMESTAMP,
    PRIMARY KEY (BIZ_TYPE_ID,BIZ_VALUE)
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS SYS_BIZ_MAP;
CREATE TABLE SYS_BIZ_MAP (
    BIZ_TYPE_ID VARCHAR2(32) NOT NULL ,
    BIZ_VALUE VARCHAR2(32) NOT NULL ,
    STAFF_ID VARCHAR2(32) NOT NULL ,
    CREATE_USER VARCHAR2(16),
    CREATE_TIME TIMESTAMP,
    PRIMARY KEY (BIZ_TYPE_ID,BIZ_VALUE,STAFF_ID)
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS SYS_LOG_AUDIT;
CREATE TABLE SYS_LOG_AUDIT (
    ID VARCHAR2(32) NOT NULL ,
    OPER_STAFFID VARCHAR2(32) NOT NULL ,
    ROLE_ID VARCHAR2(32),
    DEVO_STAFFID VARCHAR2(32),
    OPER_CODE VARCHAR2(32),
    OPER_NAME VARCHAR2(32),
    IP_ADDR VARCHAR2(32),
    BIZ_TYPE VARCHAR2(32),
    FUNC_TYPE CHAR(2),
    EXEC_RESULT CHAR(1),
    EXEC_TIME TIMESTAMP NOT NULL ,
    LOG_DETAIL VARCHAR2(255),
    PRIMARY KEY (ID)
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS SYS_UPLOAD_FILE;
CREATE TABLE SYS_UPLOAD_FILE (
    UPLOAD_ID VARCHAR2(32) NOT NULL ,
    FILE_ID VARCHAR2(32) NOT NULL ,
    FILE_NAME VARCHAR2(64),
    FILE_SIZE INTEGER,
    FILE_PATH VARCHAR2(255),
    DEL_FLAG CHAR(1),
    DOWN_NUM INTEGER,
    CREATE_USER VARCHAR2(16),
    CREATE_TIME TIMESTAMP,
    PRIMARY KEY (UPLOAD_ID,FILE_ID)
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS SYS_LOGON_INFO;
CREATE TABLE SYS_LOGON_INFO (
  "LOGON_ID"	VARCHAR(20)	NOT NULL,
  "STAFF_ID"	VARCHAR(16)	NOT NULL,
  "PASSWORD"	VARCHAR(64),
  "VALID_FLAG"	CHARACTER(1),
  "LOCK_FLAG"	CHARACTER(1),
  "FAIL_LOGON_COUNT"	INTEGER,
  "MAX_FAIL_COUNT"	INTEGER,
  "PWD_EXPIRE_DAYS"	INTEGER,
  "PWD_EXPIRE_TIME"	TIMESTAMP,
  "ACC_EXPIRE_TIME"	TIMESTAMP,
  "LAST_LOGON_IP"	VARCHAR(20),
  "LAST_LOGON_TIME"	TIMESTAMP
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS SYS_STAFF;
CREATE TABLE SYS_STAFF(
  STAFF_ID	VARCHAR(16)	NOT NULL,
  STAFF_NAME	VARCHAR(32),
  OWNER_UNITID	VARCHAR(32),
  STAFF_LEVEL	SMALLINT,
  STAFF_STATUS	CHARACTER(1)	NOT NULL,
  SEX	CHARACTER(1),
  CREATE_USER	VARCHAR(16),
  CREATE_TIME	TIMESTAMP,
  LAST_UPD_USER	VARCHAR(16),
  LAST_UPD_TIME	TIMESTAMP,
  EXT1	VARCHAR(64),
  EXT2	VARCHAR(64),
  EXT3	VARCHAR(64)
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS SYS_STAFF_EXT;
CREATE TABLE SYS_STAFF_EXT (
  STAFF_ID	VARCHAR(16)	NOT NULL,
  CREATE_USER	VARCHAR(16),
  CREATE_TIME	TIMESTAMP,
  LAST_UPD_USER	VARCHAR(16),
  LAST_UPD_TIME	TIMESTAMP
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS BAT_STEP;
CREATE TABLE BAT_STEP (
    BATCH_ID VARCHAR2(16) NOT NULL ,
    STEP_ID INTEGER NOT NULL ,
    STEP_NAME VARCHAR2(32),
    STEP_DESC VARCHAR2(128),
    PROC_FREQ CHAR(1),
    PROC_CLASS VARCHAR2(128),
    PARAM1 VARCHAR2(64),
    PARAM2 VARCHAR2(64),
    PARAM3 VARCHAR2(64),
    PARAM4 VARCHAR2(64),
    PARAM5 VARCHAR2(64),
    COMP_COUNT INTEGER,
    SKIP_STEP_ID INTEGER,
    DEPEND_IDS VARCHAR2(64),
    REF_TIME VARCHAR2(10),
    EXT1 VARCHAR2(64),
    EXT2 VARCHAR2(64),
    EXT3 VARCHAR2(64),
    PRIMARY KEY (BATCH_ID,STEP_ID)
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS BAT_STEP_CHILD;
CREATE TABLE BAT_STEP_CHILD (
    STEP_ID INTEGER NOT NULL ,
    CHILD_ID INTEGER NOT NULL ,
    BATCH_ID VARCHAR2(16) NOT NULL ,
    STEP_NAME VARCHAR2(32),
    STEP_DESC VARCHAR2(128),
    PROC_FREQ CHAR(1),
    PROC_CLASS VARCHAR2(128),
    PARAM1 VARCHAR2(64),
    PARAM2 VARCHAR2(64),
    PARAM3 VARCHAR2(64),
    PARAM4 VARCHAR2(64),
    PARAM5 VARCHAR2(64),
    DEPEND_IDS VARCHAR2(64),
    REF_TIME VARCHAR2(10),
    EXT1 VARCHAR2(64),
    EXT2 VARCHAR2(64),
    EXT3 VARCHAR2(64),
    PRIMARY KEY (STEP_ID,CHILD_ID,BATCH_ID)
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS BAT_STEP_MONITOR;
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
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS SYS_ENUM;
CREATE TABLE SYS_ENUM (
  ENUM_ID	VARCHAR(16)	NOT NULL,
  ENUM_NAME	VARCHAR(64),
  ENUM_DESC	VARCHAR(255),
  LINK_SRC_FLAG	CHARACTER(1)	NOT NULL,
  CREATE_USER	VARCHAR(16),
  CREATE_TIME	TIMESTAMP,
  LAST_UPD_USER	VARCHAR(16),
  LAST_UPD_TIME	TIMESTAMP
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS SYS_ENUM_ITEM;
CREATE TABLE SYS_ENUM_ITEM (
  ENUM_ID	VARCHAR(16)	NOT NULL,
  ITEM_ID	VARCHAR(16)	NOT NULL,
  ITEM_VALUE	VARCHAR(255)	NOT NULL,
  ITEM_DESC	VARCHAR(255),
  SEQ_ID	INTEGER
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS SYS_RES_FUNCTION;
CREATE TABLE SYS_RES_FUNCTION (
  FUNC_ID	VARCHAR(32)	NOT NULL,
  FUNC_CODE	VARCHAR(32),
  JSP_PATH	VARCHAR(255),
  FUNC_NAME	VARCHAR(64),
  FUNC_DESC	VARCHAR(128)
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS SYS_MENU;
CREATE TABLE SYS_MENU(
  MENU_ID	VARCHAR(16)	NOT NULL,
  NODE_ID	VARCHAR(32)	NOT NULL,
  NODE_NAME	VARCHAR(64)	NOT NULL,
  NODE_URL	VARCHAR(255),
  PARENT_NODE	VARCHAR(16),
  SEQ_ID	INTEGER,
  NODE_IMG	VARCHAR(128),
  RELA_FLAG	CHARACTER(1)	NOT NULL,
  NODE_TARGET	VARCHAR(10),
  NODE_CMD	VARCHAR(128),
  NODE_VISIBLE	CHARACTER(1),
  NODE_TOOLTIP	VARCHAR(128),
  HAS_CHILD_FLAG	CHARACTER(1)
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS SYS_STAFF_ORG_CHANGE_HIS;
CREATE TABLE SYS_STAFF_ORG_CHANGE_HIS (
  STAFF_ID	VARCHAR(32)	NOT NULL,
  OWNER_UNITID	VARCHAR(32)	NOT NULL,
  UPD_USER	VARCHAR(32),
  START_TIME	TIMESTAMP,
  END_TIME	TIMESTAMP
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS SYS_RES_URL;
CREATE TABLE SYS_RES_URL (
  URL_ID	VARCHAR(32)	NOT NULL,
  URL_NAME	VARCHAR(32),
  URL_PATH	VARCHAR(255)
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS SYS_ARC_GRANT;
CREATE TABLE SYS_ARC_GRANT (
  PRINCIPAL_CODE	VARCHAR(64)	NOT NULL,
  PRINCIPAL_TYPE	VARCHAR(16)	NOT NULL,
  RES_CODE	VARCHAR(64)	NOT NULL,
  RES_TYPE	VARCHAR(255)	NOT NULL,
  CHANNEL_CODE	VARCHAR(64),
  RUN_FLAG	CHARACTER(1),
  ASS_FLAG	CHARACTER(1),
  CREATE_USER	VARCHAR(16),
  CREATE_TIME	TIMESTAMP,
  LAST_UPD_USER	VARCHAR(16),
  LAST_UPD_TIME	TIMESTAMP,
  EXT1	VARCHAR(64),
  EXT2	VARCHAR(64),
  EXT3	VARCHAR(64)
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS SYS_PARAMS;
CREATE TABLE SYS_PARAMS (
    PARA_ID VARCHAR(32),
    PARA_RANK VARCHAR(10),
    PARA_VALUE VARCHAR(255),
    REMARK VARCHAR(512),
    PRIMARY KEY (PARA_ID)
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS SYS_ROLE;
CREATE TABLE SYS_ROLE (
    ROLE_ID VARCHAR2(16) NOT NULL ,
    ROLE_NAME VARCHAR2(64),
    ROLE_STATUS CHAR(1) NOT NULL ,
    REMARK VARCHAR2(255),
    PARENT_ROLE_ID VARCHAR2(16),
    CREATE_USER VARCHAR2(16),
    CREATE_TIME TIMESTAMP,
    LAST_UPD_USER VARCHAR2(16),
    LAST_UPD_TIME TIMESTAMP,
    EXT1 VARCHAR2(64),
    EXT2 VARCHAR2(64),
    EXT3 VARCHAR2(64),
    PRIMARY KEY (ROLE_ID)
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS PUB_MENU_USEINFO;
CREATE TABLE PUB_MENU_USEINFO(
  STAFF_ID	VARCHAR(32)	NOT NULL,
  UNIT_ID	VARCHAR(32)	NOT NULL,
  ROLE_ID	VARCHAR(32)	NOT NULL,
  MENU_ID	VARCHAR(32)	NOT NULL,
  USER_COUNT	INTEGER,
  MISC	VARCHAR(10)
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS PUB_STAFF_QUICK_MENU;
CREATE TABLE PUB_STAFF_QUICK_MENU (
  STAFF_ID	VARCHAR(32)	NOT NULL,
  UNIT_ID	VARCHAR(32)	NOT NULL,
  ROLE_ID	VARCHAR(32)	NOT NULL,
  MENU_ID	VARCHAR(32)	NOT NULL,
  SEQ	INTEGER,
  MISC	VARCHAR(10)
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS SYS_UNIT;
CREATE TABLE SYS_UNIT (
  UNIT_ID	VARCHAR(32)	NOT NULL,
  UNIT_NAME	VARCHAR(64),
  SUPER_UNITID	VARCHAR(32),
  UNIT_LEVEL	CHARACTER(1),
  UNIT_KIND	CHARACTER(1)	NOT NULL,
  UNIT_STATUS	CHARACTER(1)	NOT NULL,
  UNIT_INDEX	INTEGER,
  UNIT_PATH	VARCHAR(512),
  LEAF_FLAG	CHARACTER(1),
  CREATE_USER	VARCHAR(16),
  CREATE_TIME	TIMESTAMP,
  LAST_UPD_USER	VARCHAR(16),
  LAST_UPD_TIME	TIMESTAMP,
  EXT1	VARCHAR(64),
  EXT2	VARCHAR(64),
  EXT3	VARCHAR(64),
  EXT4	VARCHAR(32),
  EXT5	VARCHAR(32),
  MAPPING_CODE	VARCHAR(256)
) ;
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS SYS_UNITMAP;
CREATE TABLE SYS_UNITMAP(
  UNIT_ID	VARCHAR(32)	NOT NULL,
  RELA_UNITID	VARCHAR(32)	NOT NULL
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS SYS_UNIT_EXT;
CREATE TABLE SYS_UNIT_EXT(
  UNIT_ID	VARCHAR(32)	NOT NULL,
  CREATE_USER	VARCHAR(16),
  CREATE_TIME	TIMESTAMP,
  LAST_UPD_USER	VARCHAR(16),
  LAST_UPD_TIME	TIMESTAMP
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS SYS_WIDGET;
CREATE TABLE SYS_WIDGET (
    WID VARCHAR2(10) NOT NULL ,
    TITLE VARCHAR2(32),
    DATA_SERVICE VARCHAR2(32),
    TYPE VARCHAR2(32),
    PARAMA VARCHAR2(32),
    PARAMB VARCHAR2(32),
    PARAMC VARCHAR2(128),
    REMARK VARCHAR2(128),
    PRIMARY KEY (WID)
);
-----------------------------------------------------------------------------------------------------
-- DROP TABLE IF EXISTS SYS_HOMEPAGE;
CREATE TABLE SYS_HOMEPAGE (
    USER_ID VARCHAR2(32) NOT NULL ,
    LAYOUT_DATA VARCHAR2(1024),
    PRIMARY KEY (USER_ID)
);
-----------------------------------------------------------------------------------------------------
