var ahs=[];
ahs.push('<div class="aui_state_focus" style="display: block; position: absolute;margin-left: 5%;top: 70px;z-index: 1999;">');
ahs.push('<div class="aui_outer"><table class="aui_border"><tbody><tr>');
ahs.push('<td class="aui_nw"></td><td class="aui_n"></td><td class="aui_ne"></td></tr>');
ahs.push('<tr><td class="aui_w"></td><td class="aui_c"><div class="aui_inner">');
ahs.push('<table class="aui_dialog"><tbody><tr>');
ahs.push('<td class="aui_header" colspan="2"><div class="aui_titleBar">');
ahs.push('<div class="aui_title" style="">菜单列表</div>');
ahs.push('<a id="mclose" class="aui_close" href="javascript:/*artDialog*/;">×</a></div></td></tr>');
ahs.push('<tr><td class="aui_main" style="width: 100%; height: 450px;">');
ahs.push('<div id="menushowid" class="aui_content aui_state_full" style="padding: 20px 25px;overflow-x: hidden;overflow-y: auto;">');
ahs.push('</div></td></tr><tr><td class="aui_footer" colspan="2">');
ahs.push('<div class="aui_buttons" style="display: none;"></div>');
ahs.push('</td></tr></tbody></table></div></td>');
ahs.push('<td class="aui_e"></td></tr><tr><td class="aui_sw"></td>');
ahs.push('<td class="aui_s"></td><td class="aui_se" style="cursor: se-resize;"></td>');
ahs.push('</tr></tbody></table></div></div>');
var allmenuhtml = ahs.join('');
var itemstr="";
function  getMenuItem(obj,cnt){
	var leftint = cnt*15;
	$(obj).each(function(index){
    	var val = obj[index];
    	if(val.children!=undefined){
    		itemstr+="<div class='mntd2' style='padding-left:"+leftint+"px'>"+val.text+"</div>";
    		var n = cnt+1;
    		getMenuItem(val.children,n);
    	}else{
    		itemstr+="<div class='mntd1' style='padding-left:"+leftint+"px'><a href='#' onclick=\"getmenu('"+val.nodeId+"','"+val.text+"','"+val.url+"')\" >"+val.text+"</a></div>";
    	}
    });
}
function  getMenutable(menuJson){
	var root="<table witdh='100%' ><tr>";
    $(menuJson.items).each(function(i){
    	root+="<td valign='top' width='150px' class='mntitle' >"+menuJson.items[i].text+"</td>";
    });
    root+="</tr><tr>";
    $(menuJson.items).each(function(i){
    	var obj = menuJson.items[i].menu.items;
    	getMenuItem(obj,1);
    	root+="<td valign='top' class='mntd0' >"+itemstr+"</td>";
    	itemstr="";
    });
    root+="</tr></table>";
    return root;
}
function getmenu(nodeId,text,url){
	f_addTab(nodeId , text, url);
	$("#hh")[0].style.display='none';
}