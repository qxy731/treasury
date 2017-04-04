/**
 * 首页微件
 * @author wuwei
 */
(function($) {
    $.zWidgets = $.zWidgets || {};
    $.zWidgets.calc = {
        context :{
            lingState:"beStart",   //当前状态
            curOper:"start",     //当前运算符
            num1:0,       //初值
            num2:0,       //初值
            subOper:false,   // 是否刚刚单击过运算符
            upOper:false,//运算符的标签
            tnumber:false,//MR记住显示框的状态
            cM:false,//记住M启动
            xM:false,//记住按M+
            ms:false,//记住是否按MS
            sk:false,
            twonumber:false,
            ss:null,//保存MS要保存的值
            sb:0
        },
        /**
         * 更多链接可用
         */
        getIconImage: function(datamodel) {
        	return "../../images/icons/21.png";
        },
        /**
         * 更多链接可用
         */
        moreEnabled: function(datamodel) {
        	return false;
        },
        renderHtml: function(id,datamodel){
           var p = $.zWidgets.calc.context;
           var meths = {
                switchAction: function(event) {
                    var el = event.target || event.srtElement;
                    var i = $(el).attr('value');
                    if ("01234567890".indexOf(i) >=0) {
                        meths.number(i);
                    }
                    else if ("+-*/".indexOf(i) >=0) {
                        meths.allfhao(i);
                    }
                    else if ("=" == i) {
                        meths.sum();
                    }
                    else if ("." == i) {
                        meths.point();
                    }
                    else if ("退格 CE CA".indexOf(i) >=0 ){
                        meths.cleaktext(i);
                    }
                    else if ("sqr sin % cos 1/x tan +/-".indexOf(i)>=0) {
                        meths.alltx(i);
                    }
                    else if ("MC MR M+ MS".indexOf(i) >=0){
                        meths.checkallM(i);
                    }
                    
                },
                //数字键触发功能模块
                number:function(i) {
                   var form = document.getElementById('calcuteform');
                   if(p.subOper ) //表示在此之前刚刚单击过运算符或者刚刚单击过等号 
                   {
                       form.ipt.value=i; //把输入的值赋给文本框显示
                       p.subOper=false;//输入数后，点击了符号 
                       p.lingState="beInteger";
                   }
                   else
                   {                  //表示正在进行或开始一个数字的输入
                       if(form.ipt.value=="0")//当文本显示框为0时，则把刚刚单击的数值赋给文本框
                       {
                           form.ipt.value=i;//当符合条件则把单击的数值(this.value)赋予给显示框1
                           p.lingState="beInteger";
                       }
                       else 
                           form.ipt.value += i;//当文本显示框不为0，则数值累加显示文本框
                    }  
                    if(p.curOper!="start")//判断是否单击过运算符
                    {
                       p.num2=form.ipt.value;
                    }
                    //lingState="beStart";   //清除当前状态
                    p.upOper=true;//记住输入数字 以便下面+号连+运算
                    p.tnumber=true;//记住有值
                },
                //结果运算模块
                sum:function ()
                {
                    var form = document.getElementById('calcuteform');
                    if (p.curOper!="start")//判断是否单击按钮(符号为空
                    {
                        switch(p.curOper)
                        {
                         case "+":         //判断符号为+时执行+运算
                              p.num1= parseFloat(p.num1)+parseFloat(p.num2); //把第一次输入的值和第二次的值进行运算
                              break;
                         case "-":         //判断符号为-时执行+运算
                              p.num1= parseFloat(p.num1)-parseFloat(p.num2);
                              break;
                         case "*":         //判断符号为*时执行+运算
                              p.num1= parseFloat(p.num1)*parseFloat(p.num2);
                              break;
                         case "/":         //判断符号为/时执行+运算
                             if(p.num2=="0") {
                                alert("除数不能为零");
                             }
                             else {
                                 p.num1= parseFloat(p.num1)/parseFloat(p.num2);
                             }
                             break;
                         }
                         form.ipt.value=p.num1;//把运算结果赋给显示框 
                     }  
                     p.subOper=true;//输入数后，点击了符号
                     //curOper="start";    //清除当前符号状态
                     p.lingState="beStart";//清除当前状态
                     p.upOper=false;//=运算一次后记住 避免再按+号又进行运算 （ 清除当前符号状态
                     p.sk=true;
                 },
                 //常规符号运算功能模块
                 allfhao:function(i) {
                     var form = document.getElementById('calcuteform');
                     p.subOper=true;//输入数后 输入符号  进行运算
                     if (p.curOper=="start") //实现连运算 原理：当运行当前运算符时实现连运算
                     { 
                         p.num1=form.ipt.value;   //把第一个数赋值给num1
                         p.curOper=i;  //单击运算符用变量把运算符记住
                         p.tnumber=true;
                     }
                     else { 
                         if(p.upOper) {//当upOper为真时则实现连运算
                             meths.sum();//当符合条件时调用结果运算 实现连运算
                         }
                         p.curOper=i;//单击运算符用变量把运算符记住
                     }
                     p.upOper=false;//=运算一次后记住 避免再按+号又进行运算 （ 清除当前符号状态
                     p.lingState="beStart";//清除当前状态
                 },
                 //小数点功能模块
                 point:function() {  
                     var form = document.getElementById('calcuteform');
                     if(form.ipt.value.indexOf(".")==-1) { //判断是否有小数点，如果有就不显示 如果没有那么进行下面的运算
                         if(p.lingState=="beStart") { //如果进行了等号运算 但并没有小数点 但单击了小数点则显示0.几
                             form.ipt.value="0.";//当符合条件则显示框1 显示0.
                             p.subOper=false;//输入数后，点击了符号
                             p.lingState="beFloat";     //让一个变量记住以输入小数点
                         }
                         if(p.lingState=="beInteger" ) { //判断是否有数输入，如果有数数输入但不是接这等号运算则显示小数点
                             form.ipt.value+=".";//当符合条件则显示小数点
                             p.lingState="beFloat";//用一个变量记住已经输入一个小数点，当下次输入由于值的改变则不能输入，起到只能输入一个小数点的功能
                         }
                      }
                 },
                 //全部清除功能模块 CA CE 退格
                 cleaktext:function (i) {
                     var form = document.getElementById('calcuteform');
                     switch(i)
                     {
                      case"CA"://清除C
                          form.ipt.value="0";    //清除文本框内的内容
                          p.lingState="beStart";   //清除当前状态
                          p.curOper="start";    //清除当前符号状态
                          p.subOper=false;   // 是否刚刚单击过运算符
                          p.upOper=false;//运算符的标签
                          p.num1=0;
                          p.num2=0;
                          break;
                      case"CE": //清除CE
                          form.ipt.value="0";    //清除文本框内的内容 
                          break;
                      case"退格":   //推格删除
                          if(p.cM==false) {//如果启动MR那么不能实现推格功能
                              if(p.upOper) {
                                  if (form.ipt.value.length>1) {
                                      form.ipt.value=form.ipt.value.substring(0,form.ipt.value.length-1); //运用substring取字符串方法将返回一个包含从原始对象中获得的子字符串的 String 对象。 使用 start 和 end 两者的较小值作为子字符串的起始点。 
                                  }
                                  else {
                                      form.ipt.value="0";//一个一个删除
                                  } 
                              }
                              break;
                          }
                      }
                 },
                 //全部的特殊符号运算模块  
                 alltx: function (i) {
                     var form = document.getElementById('calcuteform');
                     switch(i)
                     {
                     case "%"://%运算
                         form.ipt.value=form.ipt.value/100;  
                         p.num2=form.ipt.value;
                         break;
                     case "1/x":
                         if(form.ipt.value=="0"){
                             form.ipt.value="除数不能为零。";
                         }
                         else {
                             form.ipt.value=1/form.ipt.value;
                             p.num2=form.ipt.value;
                         }
                         break;
                     case "sqr"://开方
                         form.ipt.value=Math.sqrt(form.ipt.value);  //math对象 开方运算
                         p.num2=form.ipt.value;
                         break;
                     case "+/-"://+/-运算符  负号运算
                         if(p.upOper){ //当运行了符号则不能按负号
                             form.ipt.value=0-form.ipt.value;
                             p.num2=form.ipt.value;
                         }
                         else {
                             form.ipt.value="0";
                         }
                         break;
                     case "pi":
                         form.ipt.value="3.1415926";
                         p.num2=form.ipt.value;
                         break;
                     case "sin":
                         form.ipt.value=Math.sin(form.ipt.value); 
                         p.num2=form.ipt.value;
                         break;
                     case "cos":
                         form.ipt.value=Math.sin(form.ipt.value); 
                         p.num2=form.ipt.value;
                         break;
                     case "tan":
                         form.ipt.value=Math.sin(form.ipt.value); 
                         p.num2=form.ipt.value;
                         break;
                     }
                 },
                 //全部M系列功能模块
                 checkallM:  function (i){
                     var form = document.getElementById('calcuteform');
                     switch(i)
                     {
                     case"M+"://启动M系列功能模块
                         if(form.ipt.value!="0" && p.ms==false) {
                             form.ipt1.value="M";//在显示框2显示M以告知用户以启动M系列功能
                             p.sm=form.ipt.value;
                         }
                         if(form.ipt1.value=="M" && p.ms!=false) {
                             p.sm=eval(p.ss+'+'+form.ipt.value);//MR保存的值提取实现M+功能
                         }
                         p.xM=true;   //记住单击过M+ 以便MR操作
                         break;
                     case "MS"://启用M系列功能 启动记忆功能（记忆上一次计算结果）
                         p.ss=form.ipt.value;// 把显示框结果给SS保存（予以MR提取
                         if(form.ipt.value=="0") { //当显示框1显示结果为0时单击MS时也可以清除显示框2 M功能
                             form.ipt1.value="";//清除显示框2 M
                         }
                         if(form.ipt.value!="0") {
                             form.ipt1.value="M";
                         }
                         p.ms=true;   //记住单击过Ms 以便MR操作 (看是否赋+运算后的值 还是当前值
                         break;
                     case "MR"://提取MS保存数值功能
                         if(p.xM) {//是否单击m+
                             if(form.ipt1.value=="M"  ) {//当启动M功能时 MR才能起到保存提取的功能
                                form.ipt.value=p.sm;//当条件符合 则把MS保存的值 并实现M+功能
                             }
                             else if(p.subOper || p.tnumber){//没有启动M功能 则清除显示框
                                 form.ipt.value="0";
                             }
                         }
                         // else{ form.ipt.value=ss;}
                         break;
                     case "MC":  //  清除M显示框中M系列的功能
                         form.ipt1.value="";//清空显示框2的 M功能
                         p.xM=false;
                         p.ms=false;
                         break;
                     }
                     p.cM=true;
                     p.lingState="beStart";//清除当前状态
                 }
           };
           $.zWidgets.calc.meths = meths;
           var htmlstr = [];
           htmlstr.push("<div class='caculate'>");
           htmlstr.push("<FORM METHOD=POST ACTION='' name='calcuteform' id='calcuteform'>");
           htmlstr.push("    <table border='0' align='center' cellspacing='5' >");
           htmlstr.push("       <tr >");
           htmlstr.push("           <td colspan='6'><input type='text' id='ipts' name='ipt' value='0' size='40' maxlength='20' readonly='readonly' /></td>");
           htmlstr.push("       </tr>");
           htmlstr.push("       <tr >");
           htmlstr.push("          <td colspan='2'><input type='button' class='bt2' value='退格' /></td>");
           htmlstr.push("          <td colspan='2'><input type='button' class='bt2' value='CE' /></td>");
           htmlstr.push("          <td colspan='2'><input type='button' class='bt2' value='CA' /></td>");
           htmlstr.push("       </tr>");
           htmlstr.push("       <tr>");
           htmlstr.push("         <td><input type='button' class='bt' value='7' /></td>");
           htmlstr.push("         <td><input type='button' class='bt' value='8' /></td>");
           htmlstr.push("         <td><input type='button' class='bt' value='9' /></td>");
           htmlstr.push("         <td><input type='button' class='bt' value='/' /></td>");
           htmlstr.push("         <td><input type='button' class='bt' value='sqr' /></td>");
           htmlstr.push("         <td><input type='button' class='bt' value='sin' /></td>");
           htmlstr.push("       </tr>");
           htmlstr.push("       <tr>");
           htmlstr.push("         <td><input type='button' class='bt' value='4' /></td>");
           htmlstr.push("         <td><input type='button' class='bt' value='5' /></td>");
           htmlstr.push("         <td><input type='button' class='bt' value='6' /></td>");
           htmlstr.push("         <td><input type='button' class='bt' value='*' /></td>");
           htmlstr.push("         <td><input type='button' class='bt' value='%' /></td>");
           htmlstr.push("         <td><input type='button' class='bt' value='cos' /></td>");
           htmlstr.push("       </tr>");
           htmlstr.push("       <tr>");
           htmlstr.push("         <td><input type='button' class='bt' value='1' /></td>");
           htmlstr.push("         <td><input type='button' class='bt' value='2' /></td>");
           htmlstr.push("         <td><input type='button' class='bt' value='3' /></td>");
           htmlstr.push("         <td><input type='button' class='bt' value='-' /></td>");
           htmlstr.push("         <td><input type='button' class='bt' value='1/x' /></td>");
           htmlstr.push("         <td><input type='button' class='bt' value='tan' /></td>");
           htmlstr.push("       </tr>");
           htmlstr.push("       <tr>");
           htmlstr.push("           <td><input type='button' class='bt' value='0' /></td>");
           htmlstr.push("           <td><input type='button' class='bt' value='+/-' /></td>");
           htmlstr.push("           <td><input type='button' class='bt' value='.' /></td>");
           htmlstr.push("           <td><input type='button' class='bt' value='+' /></td>");
           htmlstr.push("           <td colspan='2'><input type='button' class='bt2' value='=' /></td>");
           htmlstr.push("        </tr>");
           htmlstr.push("     </table>");
           htmlstr.push("</FORM>");
           htmlstr.push("</div>");
           return htmlstr.join('');
        },
        initWidget: function(cdiv,datamodel){
            $(cdiv).css("height","250px");
            //$(cdiv).css("width","300px");
            var list = $('input[type=button]',cdiv);
            var f = $.zWidgets.calc.meths.switchAction;
            for (var x= 0 ; x< list.length; x++) {
                $(list[x]).bind('click',f);
            }
        }
    };
})(jQuery);