var numFormat = {
		
};
/**
 * 加载页面时,对<n:number></n:number>标签中的内容初始化
 * 要求必须添加cssClass="numberClass"
 */
numFormat.formatInit = function(){
	$(".numberClass").each(function(i,item){
		var pre = item.attributes.pre.value;//获得前缀
		var cnt = item.attributes.cnt.value;//科学记位,默认3
		var dec = item.attributes.dec.value;//精确几位小数 默认2
		var valCN = item.attributes.valCN.value;//是否转换成中文大写 默认false
		var suf = item.attributes.suf.value;//获得后缀
		var numValue = $(this).val();//获得数值
		var num = numFormat.formart(pre,cnt,numValue,dec,valCN,suf);
		item.value = num;
		$(this).bind( {
			focusin : function() {
			$(this).val(numValue);
		},
		    focusout : function() {
			numValue = $(this).val();
			$(this).val(numFormat.formart(pre,cnt, numValue,dec,valCN,suf));
		}
		});
	});
};

/**
 * 前缀 单位例如$，£,
 * 记位(默认是3位一个","),
 * 数值,
 * 精确小数(如果只显示整数，则dzero=0),
 * 是否转换大写true or false,
 * 后缀 单位例如美元，英镑
 */

numFormat.formart = function(prefix,cnt,num,dzero,CN,suffix){
	if(num==null){
	    return num = "";
    }
    if(CN=="true"){
    	var numberCN = numFormat.formartCN(prefix,num,suffix);//阿拉伯数字转换成中文大写
        return numberCN;
    }
    num = num.toString().replace(/\$|\,/g, '');
    if (isNaN(num)||num == ""){
        return  num = "";
    }
    var cents = "";
    for(var i = 0; i<dzero;i++){
    	cents = cents + "0";
    }
    var hundred = "1" + cents;//用于计算小数部分精度
    var tempZero = cents;//用于计算小数部分精度
        
    var numCents = num.toString();
    var length = num.length;
    var index = numCents.indexOf(".");
    var zeroindex = num.indexOf("0");
    //判断输入数字是否为0XXX.XX ，若是进入下面的循环知道输出的是XXX.XX为止
    if(zeroindex==0){
    	num = zeroIndex(num,length);
    }
    //如果是整数
    if(index!= -1){
    	cents = decNumCents(numCents,dzero);
    }
    if(cents < parseFloat(hundred)){
    	cents = cents + tempZero;
    }
    if(cents.length> dzero){
    	cents = cents.substring(0,dzero);
    }      
    num = parseInt(num).toString();//获得整数部分
    num = forNumInt(num,cnt);//对整数部分进行科学记位
    
    var reNum = resultNum(cents,num,prefix,suffix);
    return reNum;
};
/**
 * 返回最后结果
 * @param cents 小数
 * @param num 整数
 * @param prefix 前缀
 * @param suffix 后缀
 * @return
 */
function resultNum(cents,num,prefix,suffix){
	if(cents==""){
		return prefix + num + suffix;
	}else{
		return ( prefix + num + '.' + cents + suffix);
	}
};

/**
 * 计算小数部分
 * @param numCents
 * @param dzero
 * @return
 */
function decNumCents(numCents,dzero){
	var decNum = 1 + dzero;
	cents = numCents.substring(numCents.indexOf(".") + 1, numCents.indexOf(".") + decNum);
	return cents;
};
/**
 * 计算整数部分
 * @param num
 * @param cnt
 * @return
 */
function forNumInt(num,cnt){
   //若输入情况是  .9
	if(isNaN(num)){
	      return num = "0";
	 }
    var intTemp = parseInt(cnt);
    var temp = intTemp + 1;
    for (var i = 0; i < Math.floor((num.length - (1 + i)) / intTemp); i++){
	    num = num.substring(0, num.length - (temp * i + intTemp)) + ',' + num.substring(num.length - (temp * i + intTemp));
	 }
	 return num;
};
/**
 * 判断输入数字是否为0XXX.XX ，若是进入下面的循环知道输出的是XXX.XX为止
 * @param num
 * @param length
 * @return
 */
function zeroIndex(num,length){
	for(var i = 0; i < length; i++){
        var zero = num.indexOf("0");
        if(zero==0){
        	num = num.substring(zero + 1,length);
        }
    }
	return num;
};

/**
 * 阿拉伯数字转换成中文大写
 * prefix 货币前缀单位
 * suffix 货币后缀单位
 */
numFormat.formartCN = function(prefix,num,suffix){
	if (typeof (parseFloat(num)) == "number") {
		if (-1 == num.toString().indexOf(".")) {
			return setInt(num);
		} else {
			var strSplit = num.toString().split(".");
			var integer  = strSplit[0];//获得整数部分
			var decimals = strSplit[1];//获得小数部分
			var intStr = setInt(integer);//转换整数部分
			var decStr = setDec(decimals);//转换小数部分
			rstr = intStr + "零" + decStr ;
			rstr = rstr.replace(/零+/g, "零");
			return prefix + rstr + suffix;
		}
	} else {
		return "--- ";
	}
};

/**
 * 转换整数部分大写
 * @param integer
 * @return
 */
function setInt(integer) {
	var ns = integer.toString();
	var tempstr = "";
	for ( var i = 1; i < ns.length + 1; i++) {
		switch (i) {
		case 1:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "圆" + tempstr;
			} else {
				tempstr = "圆" + tempstr;
			}
			break;
		case 2:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "拾" + tempstr;
			} else {
				tempstr = "0" + tempstr;
			}
			break;
		case 3:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "佰" + tempstr;
			} else {
				tempstr = "0" + tempstr;
			}
			break;
		case 4:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "仟" + tempstr;
			} else {
				tempstr = "0" + tempstr;
			}
			break;
		case 5:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "万" + tempstr;
			} else {
				tempstr = "万" + tempstr;
			}
			break;
		case 6:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "拾" + tempstr;
			} else {
				tempstr = "0" + tempstr;
			}
			break;
		case 7:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "佰" + tempstr;
			} else {
				tempstr = "0" + tempstr;
			}
			break;
		case 8:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "仟" + tempstr;
			} else {
				tempstr = "0" + tempstr;
			}
			break;
		case 9:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "亿" + tempstr;
			} else {
				tempstr = "亿" + tempstr;
			}
			break;
		case 10:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "拾" + tempstr;
			} else {
				tempstr = "0" + tempstr;
			}
			break;
		case 11:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "佰" + tempstr;
			} else {
				tempstr = "0" + tempstr;
			}
			break;
		case 12:
			var t = ns.substr(ns.length - i, 1);
			if (t != "0") {
				tempstr = t + "仟" + tempstr;
			} else {
				tempstr = "0" + tempstr;
			}
			break;
		default:
			break;
		}
	}
	return chinaFormat(tempstr);
};
/**
 * 转换小数部分大写
 * @param decimals
 * @return
 */

function setDec(decimals) {
	var ns = decimals.toString();
	var tempstr = "";
	for ( var i = 0; i < ns.length; i++) {
		switch (i) {
		case 0:
			var t = ns.substr(i, 1);
			if (t != "0") {
				tempstr += t + "角";
			} else {
				tempstr += "0";
			}
			break;
		case 1:
			var t = ns.substr(i, 1);
			if (t != "0") {
				tempstr += t + "分";
			}
			break;
		case 2:
			var t = ns.substr(i, 1);
			if (t != "0") {
				tempstr += t + "厘";
			}
			break;
		case 3:
			var t = ns.substr(i, 1);
			if (t != "0") {
				tempstr += t + "毫";
			}
			break;
		default:
			break;
		
		}
	}
	return chinaFormat(tempstr);
};

function chinaFormat(s) {
	rs = s.replace(/0+/g, "0");
	rs = rs.replace("0圆", "圆");
	rs = rs.replace("0亿", "亿");
	rs = rs.replace("0万", "万");
	rs = rs.replace("亿万", "亿");
	rs = rs.replace(/0/g, "零");
	rs = rs.replace(/1/g, "壹");
	rs = rs.replace(/2/g, "贰");
	rs = rs.replace(/3/g, "叁");
	rs = rs.replace(/4/g, "肆");
	rs = rs.replace(/5/g, "伍");
	rs = rs.replace(/6/g, "陆");
	rs = rs.replace(/7/g, "柒");
	rs = rs.replace(/8/g, "捌");
	rs = rs.replace(/9/g, "玖");
	return rs;
}