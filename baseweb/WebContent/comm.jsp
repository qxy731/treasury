<%-- <link href="${_CONTEXT_PATH}/jwebui/skins/sys/css/ligerui-common.css" rel="stylesheet" type="text/css" />
<link href="${_CONTEXT_PATH}/jwebui/skins/sys/css/ligerui-grid.css" rel="stylesheet" type="text/css" />
<link href="${_CONTEXT_PATH}/jwebui/skins/sys/css/ligerui-form.css" rel="stylesheet" type="text/css" />
<link href="${_CONTEXT_PATH}/jwebui/skins/sys/css/ligerui-layout.css" rel="stylesheet" type="text/css" />
<link href="${_CONTEXT_PATH}/jwebui/skins/sys/css/ligerui-menu.css" rel="stylesheet" type="text/css" />
<link href="${_CONTEXT_PATH}/jwebui/skins/sys/css/ligerui-tab.css" rel="stylesheet" type="text/css" />
<link href="${_CONTEXT_PATH}/jwebui/skins/sys/css/ligerui-tree.css" rel="stylesheet" type="text/css" /> --%>
<link href="${_CONTEXT_PATH}/jwebui/skins/ligerui-icons.css" rel="stylesheet" type="text/css" />
<link href="${_CONTEXT_PATH}/jwebui/artdialog/skins/blue.css" rel="stylesheet" />
<link href="${_CONTEXT_PATH}/jwebui/procbar/procbar.css" rel="stylesheet" />
<link href="${_CONTEXT_PATH}/jwebui/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />

<script src="${_CONTEXT_PATH}/jwebui/jquery/jquery-1.12.4.min.js" type="text/javascript" ></script>
<script src="${_CONTEXT_PATH}/jwebui/jquery/jquery.cookie.js" type="text/javascript" ></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/core/base.js"  type="text/javascript"></script> 
<%-- <script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/liger.all.js" type="text/javascript"></script> --%>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerAccordion.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerButton.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerCheckBox.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerComboBox.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerDateEditor.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerDialog.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerDrag.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerEasyTab.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerForm.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerGrid.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerLayout.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerMenu.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerMenuBar.js" type="text/javascript"></script>
<%-- <script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerMenuBarTop.js" type="text/javascript"></script> --%>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerMessageBox.js" type="text/javascript"></script>
<%-- <script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerNoSelect.js" type="text/javascript"></script> --%>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerPanel.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerRadio.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerResizable.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerSpinner.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerTab.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerTextBox.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerTip.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerToolBar.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/ligerUI/js/plugins/ligerTree.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/jquery/jquery.validate.min.js" type="text/javascript" ></script>
<script src="${_CONTEXT_PATH}/jwebui/jquery/jquery.metadata.js" type="text/javascript" ></script>
<script src="${_CONTEXT_PATH}/jwebui/jquery/messages_cn.js" type="text/javascript" ></script>
<script src="${_CONTEXT_PATH}/jwebui/jquery/json2.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/artdialog/jquery.artDialog.source.js"></script>
<script src="${_CONTEXT_PATH}/jwebui/artdialog/plugins/iframeTools.source.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/js/dialogBox.js" type="text/javascript"></script> 
<script src="${_CONTEXT_PATH}/js/Utils.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/js/numFormat.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/procbar/procbar.js" type="text/javascript"></script>
<script src="${_CONTEXT_PATH}/jwebui/My97DatePicker/WdatePicker.js" type="text/javascript" ></script>
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
/* document.write("<link href='${_CONTEXT_PATH}/jwebui/artdialog/skins/"+ currentSkin +".css' rel='stylesheet' type='text/css' />"); */
document.write("<link href='${_CONTEXT_PATH}/css/default.css' rel='stylesheet' type='text/css' />"); 
document.write("<link href='${_CONTEXT_PATH}/css/"+ currentSkin +".css' rel='stylesheet' type='text/css' />");

$(function () {
    numFormat.formatInit();
});
</script>
