<%-- <link href="${_CONTEXT_PATH}/jwebui/skins/sys/css/ligerui-common.css" rel="stylesheet" type="text/css" />
<link href="${_CONTEXT_PATH}/jwebui/skins/sys/css/ligerui-grid.css" rel="stylesheet" type="text/css" />
<link href="${_CONTEXT_PATH}/jwebui/skins/sys/css/ligerui-form.css" rel="stylesheet" type="text/css" />
<link href="${_CONTEXT_PATH}/jwebui/skins/sys/css/ligerui-layout.css" rel="stylesheet" type="text/css" />
<link href="${_CONTEXT_PATH}/jwebui/skins/sys/css/ligerui-menu.css" rel="stylesheet" type="text/css" />
<link href="${_CONTEXT_PATH}/jwebui/skins/sys/css/ligerui-tab.css" rel="stylesheet" type="text/css" />
<link href="${_CONTEXT_PATH}/jwebui/skins/sys/css/ligerui-tree.css" rel="stylesheet" type="text/css" />
<link href="${_CONTEXT_PATH}/jwebui/skins/ligerui-icons.css" rel="stylesheet" type="text/css" /> --%>
<link href="${_CONTEXT_PATH}/jwebui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />
<link href="${_CONTEXT_PATH}/jwebui/artdialog/skins/blue.css" rel="stylesheet" />

<script src="${_CONTEXT_PATH}/jwebui/jquery/jquery-1.4.2.min.js" type="text/javascript" ></script>
<script src="${_CONTEXT_PATH}/jwebui/jquery/jquery.cookie.js" type="text/javascript" ></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/ligerui.min.js" type="text/javascript" ></script>
<script src="${_CONTEXT_PATH}/jwebui/jquery/jquery.validate.min.js" type="text/javascript" ></script>
<script src="${_CONTEXT_PATH}/jwebui/jquery/jquery.metadata.js" type="text/javascript" ></script>
<script src="${_CONTEXT_PATH}/jwebui/jquery/messages_cn.js" type="text/javascript" ></script>
<script src="${_CONTEXT_PATH}/jwebui/jquery/json2.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/artdialog/jquery.artDialog.source.js"></script>
<script src="${_CONTEXT_PATH}/jwebui/artdialog/plugins/iframeTools.source.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/js/dialogBox.js" type="text/javascript"></script> 
<%-- <script src="${_CONTEXT_PATH}/js/echarts.common.min.js" type="text/javascript"></script>  --%>
<script src="${_CONTEXT_PATH}/js/echarts.js" type="text/javascript"></script> 
<script src="${_CONTEXT_PATH}/js/Utils.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/js/numFormat.js" type="text/javascript"></script>
<script type="text/javascript">
var _CONTEXT_PATH = '${_CONTEXT_PATH}';
var _CREATE_ORG = '${logUserInfo.operUnitId}';
var _CREATE_ORGNAME = '${logUserInfo.operUnitName}';
var _CREATE_USER = '${logUserInfo.user.staff.staffId}';
var _CREATE_USERNAME = '${logUserInfo.user.staff.staffName}';
var _ROLE_ID = '${logUserInfo.roleId}';
var _ROLE_NAME = '${logUserInfo.roleName}';
var currentSkin = $.cookie('SkinType');
if (!currentSkin) {
    currentSkin = "blue";
}
document.write("<link href='${_CONTEXT_PATH}/jwebui/artdialog/skins/"+ currentSkin +".css' rel='stylesheet' type='text/css' />");
document.write("<link href='${_CONTEXT_PATH}/css/default.css' rel='stylesheet' type='text/css' />");
document.write("<link href='${_CONTEXT_PATH}/css/"+ currentSkin +".css' rel='stylesheet' type='text/css' />");

$(function () {
    numFormat.formatInit();
});
</script>
