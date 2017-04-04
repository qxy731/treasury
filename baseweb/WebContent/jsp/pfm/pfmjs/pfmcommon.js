function PfmUtil(){}

//校验是否为正浮点数
PfmUtil.testPlusFloat=function(str){
	var pattern=/^([+]?)\d*\.\d+$/;
	return pattern.test(str);
};


//校验是否为20位正浮点数，小数位为四位
PfmUtil.testPlusNumeric4=function(str){
	var pattern=/^(\d{1,19}\.\d{1})$|^(\d{1,18}\.\d{1,2})$|^(\d{1,17}\.\d{1,3})$|^(\d{1,16}\.\d{1,4})$|^(\d{1,20})$/;
	return pattern.test(str);
};

//校验是否为20位正浮点数，小数位为二位
PfmUtil.testPlusNumeric2=function(str){
	var pattern=/^(\d{1,19}\.\d{1})$|^(\d{1,18}\.\d{1,2})$|^(\d{1,20})$/;
	return pattern.test(str);
};

