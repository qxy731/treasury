/**
 * 树状管理模式
 * 
 * data: 页面中数据来源
 * nodeIdField: 节点id数据对应域名
 * nodeNameField: 节点名称数据对应域名
 * parentField: 父节点id数据对应域名
 * nodeHeight: 节点高度
 * lineHeight: 节点之间高度
 * nodeSpan: 节点之间水平间隔
 * nodeWidth: 节点宽度
 * lineClassMap:节点关系线颜色集合,格式
 *                {KEY:["class","描述"],...}
 *               如： {"1":["aclass","依赖关系"],"2":["bclass","所属关系"],'3':['cclass','客户关系']}
 * nodeClassMap:节点颜色集合,格式
 *                {KEY:["class","描述"],...}
 * lineClassField:关系线颜色数据对应域名
 * nodeClassField:节点颜色数据对应域名
 * menuitems : [{name:'增加子节点',click:toAddNode,filter:"branch/leaf/all"},{name:'删除子节点',click:removeNode}] 右键菜单
 * isRootRecord:function(record) {
 *          return record.parentId == null;
 *      }
 * 使用方式:
 * var options = {
        data: json data,
        nodeIdField: 'nodeIdField',
        nodeNameField: 'nodeNameField',
        parentField: 'parentField',
        lineHeight: 15,
        nodeSpan: 4,
        nodeWidth: 80,
        nodeHeight: 20,
        renderUnit: null,
        leftoffset:20,
        canExpand:function (data) {
            return false;
        },
        isRootRecord:function(record) {
            return record.parentId == null;
        }
        menuitems : [{name:'增加子节点',click:toAddNode,filter:"branch"},{name:'删除子节点',click:removeNode}] 
    };

 */
(function($) {
    $.zStructure = $.zStructure || {};
    $.zStructure.options = {
        data : null,
        nodeIdField : 'nodeIdField',
        nodeNameField : 'nodeNameField',
        parentField : 'parentField',
        lineHeight : 15,
        nodeSpan : 4,
        nodeWidth : 80,
        nodeHeight : 20,
        leftoffset :0,
        canExpand:function(){return true;},
        menuitems :[]
    };

    $.fn.zStructure = function(options) {
        var p = $.extend($.zStructure.options, options || {});
        $(this).append("<div class='st_content'></div>");
        var contentDiv = $('.st_content',this)[0];
        p.levelHeight = p.lineHeight * 2 + p.nodeHeight + 10;
        var _rootNode = null;
        p._offsetTop = 0;
        var pm = {
           _getNode : function(nodes, pid) {
                for ( var i = 0; i < nodes.length; i++) {
                    if (nodes[i].nodeId == pid) {
                        return nodes[i];
                    }
                }
                return null;
            },
            _getParentRecord : function(records, idx, pid) {
                for ( var i = idx; i < records.length; i++) {
                    var rd = records[i];
                    var recordId = rd[p.nodeIdField];
                    if (recordId == pid) {
                        return i;
                    }
                }
                return -1;
            },
            _bTNode : function(nodes, records, idx) {
                var record = records[idx];
                var nodeId = record[p.nodeIdField];
                var node = pm._getNode(nodes, nodeId);
                if (!node) {
                    var nodeName = record[p.nodeNameField];
                    var parentId = record[p.parentField];
                    node = new pm.TNode(nodeId, nodeName, record);
                    nodes[nodes.length] = node;
                    var parent = pm._getNode(nodes, parentId);
                    if (!parent) {
                        //父节点不存在，递归建
                        if (p.isRootRecord(record)) {
                            //根节点
                            _rootNode = node;
                            node.level = 0;
                            return node;
                        }
                        var pidx = pm._getParentRecord(records, 0, parentId);
                        if (pidx >= 0) {
                            parent = pm._bTNode(nodes, records, pidx);
                        }
                    }
                    if (parent) {
                        node.level = parent.level + 1;
                        parent.addNode(node);
                        node.parent = parent;
                    }
                }
                return node;
            },
            _buildTNode : function() {
                for ( var i = 0; i < p.data.length; i++) {
                    pm._bTNode(g._nodes, p.data, i);
                }
            },
            _buildTipDiv : function() {
                var sb = "";
                sb += "<div id='st_tip_div' ></div>";
                return sb;
            },
            TNode : function(nodeId, nodeName, data) {
                this.nodeId = nodeId;
                this.nodeName = nodeName;
                this.level = 0;
                this.width = 0;
                this._data = data;
                this._childs = new Array();
                this.parent ;
                this.expand = true;
                this.addNode = function(child) {
                    this._childs[this._childs.length] = child;
                };
                this.getChildCount = function () {
                    return this._childs.length;
                };
                this.removeNode = function(node) {
                    var nchilds = new Array();
                    for (var x=0 ; x< this._childs.length; x++) {
                        if (this._childs[x] != node) {
                            nchilds[nchilds.length] = this._childs[x];
                        }
                    }
                    this._childs = nchilds;
                };
                this.getData = function() {
                    return _data;
                };
                this.getFirstNode = function() {
                    if (this._childs.length > 0) {
                        return this._childs[0];
                    }
                    return null;
                };
                this.buildDom = function(parentDoc,leftoffset) {
                    var widthVal = 0;
                    var py = p.levelHeight * this.level + p._offsetTop;
                    var sb = [];
                    var isexpand = (p.canExpand && p.canExpand(this._data));
                    if (this._childs.length > 0 || isexpand) {
                        sb.push("<div class='st_nodeblock' id='B");
                        sb.push(nodeId);
                        sb.push("'></div>");
                        var htmlstr = sb.join('');
                        $(parentDoc).append(htmlstr);
                        var currDoc = $('#B' + nodeId,parentDoc)[0];
                        var lineleft = leftoffset;
                        var xoffset = leftoffset;
                        if (this._childs.length == 0 ) {
                            widthVal = p.nodeWidth;
                        }
                        for ( var i = 0; i < this._childs.length; i++) {
                            this._childs[i].buildDom(currDoc,xoffset);
                            widthVal += this._childs[i].width;
                            sb = [];
                            if (i == 0) {
                                //fisrt line
                                sb.push("<div class='st_linefirst' ");
                                sb.push("id='l_" + this._childs[i].nodeId + "' ");
                                sb.push(" style=\"width:1px;");
                                sb.push("left:" + (leftoffset + this._childs[0].width / 2) + "px;");
                                sb.push("top:" + ((this.level + 1) * p.levelHeight - p.lineHeight + p._offsetTop) + "px;");
                                sb.push("height:" + p.lineHeight+ "px;\"></div>");
                                lineleft += (this._childs[0].width) / 2;
                            } else {
                                var linewidth = (this._childs[i].width + this._childs[i - 1].width) / 2 + p.nodeSpan;
                                sb.push("<div class='st_line' ");
                                sb.push("id='l_" + this._childs[i].nodeId + "' ");
                                sb.push("style=\"width:" + linewidth + "px;");
                                sb.push("height:" + p.lineHeight + "px;");
                                sb.push("left:" + lineleft + "px;");
                                sb.push("top:" + ((this.level + 1) * p.levelHeight - p.lineHeight+ p._offsetTop) + "px;\"></div>");
                                lineleft += linewidth;
                                widthVal += p.nodeSpan;
                            }
                            $(currDoc).append(sb.join(''));
                            xoffset = leftoffset + widthVal + p.nodeSpan;
                        }      
                        sb = [];
                        sb.push("<div class='st_linecross' style=\"width:1px;");
                        if (this._childs.length == 0) {
                            sb.push("display:none;");
                        }
                        sb.push("left:" + (leftoffset + (widthVal) / 2) + "px;");
                        sb.push("height:" + p.lineHeight + "px;");
                        sb.push("top:" + ((this.level + 1) * p.levelHeight - p.lineHeight * 2 + p._offsetTop) + "px;\"></div>");
                        $(currDoc).append(sb.join(''));
                    } else {
                        widthVal = p.nodeWidth;
                    }
                    // self
                    sb = [];
                    sb.push("<div class='st_node " + (isexpand?'':'st_leaf')+ "'");
                    sb.push(" id=\"" + nodeId + "\"");
                    sb.push(" style=\"left:" + (leftoffset + (widthVal - p.nodeWidth) / 2) + "px;top:");
                    sb.push( py + "px;width:" + p.nodeWidth + "px;height:" + (p.nodeHeight + 10) + "px;\"");
                    sb.push("><div class='st_nodecontent' style='height:" + p.nodeHeight + "px;'></div></div>");
                    $(parentDoc).append(sb.join(''));
                    var selfdoc = $('#' + nodeId,parentDoc)[0];
                    selfdoc.data = this;
                    this.width = widthVal;
                    return selfdoc;
                };
                this.refreshPosition = function(parentDoc,leftoffset) {
                    var widthVal = 0;
                    var py = p.levelHeight * this.level + p._offsetTop;
                    if (this._childs.length > 0 || (p.canExpand && p.canExpand(this._data))) {
                        var currDoc = $('#B' + nodeId,parentDoc)[0];
                        var lineleft = leftoffset;
                        var xoffset = leftoffset;
                        if (this._childs.length == 0) {
                            widthVal = p.nodeWidth;
                        }
                        for ( var i = 0; i < this._childs.length; i++) {
                            this._childs[i].refreshPosition(currDoc,xoffset);
                            widthVal += this._childs[i].width;
                            if (i == 0) {
                                //fisrt line
                                var left = (leftoffset + this._childs[0].width / 2) + "px";
                                var top = ((this.level + 1) * p.levelHeight - p.lineHeight + p._offsetTop) + "px";
                                lineleft += (this._childs[0].width) / 2;
                                $('> .st_linefirst',currDoc).css('left',left);
                                $('> .st_linefirst',currDoc).css('top',top);
                            } else {
                                var linewidth = (this._childs[i].width + this._childs[i - 1].width) / 2 + p.nodeSpan;
                                var left = lineleft+ "px";
                                var top = ((this.level + 1) * p.levelHeight - p.lineHeight+ p._offsetTop) + "px";
                                var width = linewidth + "px";
                                $('#l_' + this._childs[i].nodeId , currDoc).css('left',left);
                                $('#l_' + this._childs[i].nodeId , currDoc).css('top',top);
                                $('#l_' + this._childs[i].nodeId , currDoc).css('width',width);
                                lineleft += linewidth;
                                widthVal += p.nodeSpan;
                            }
                            xoffset = leftoffset + widthVal + p.nodeSpan;
                        }
                        var left =  (leftoffset + (widthVal) / 2) + "px";
                        var top = ((this.level + 1) * p.levelHeight - p.lineHeight * 2 + p._offsetTop) + "px";
                        $('> .st_linecross',currDoc).css('left',left);
                        $('> .st_linecross',currDoc).css('top',top);
                        $('> .st_linecross',currDoc).css('display', ((this._childs.length > 0)?"block":"none"));
                        if (!this.expand) {//不展开
                            widthVal = p.nodeWidth;
                        }
                    } else {
                        widthVal = p.nodeWidth;
                    }
                    // self
                    var left = (leftoffset + (widthVal - p.nodeWidth) / 2) + "px";
                    var top = py + "px";
                    $('#'+ nodeId,parentDoc).css('left',left);
                    $('#'+ nodeId,parentDoc).css('top',top);
                    this.width = widthVal;
                };


                return this;
            },
            st_showBranch : function(e) {
                var dd = $(this);
                var bid = "#B" + $(this).attr("id");
                if ( dd.hasClass("st_node")) {
                    $(bid).toggle(false);
                    dd[0].data.expand = false;
                    dd.removeClass('st_node').addClass('st_nodeclose');
                }
                else {
                    $(bid).toggle(true);
                    dd[0].data.expand = true;
                    dd.removeClass('st_nodeclose').addClass('st_node');
                }
                _rootNode.refreshPosition(contentDiv,p.leftoffset);
                $(contentDiv).css('width',_rootNode.width);
            },
            st_tip_hidden : function() {
                event.srcElement.style.display = "none";
            },
            _buildLegend : function() {
                var sb = "";
                sb += "<div class='st_legend'>";
                for (var k in p.nodeClassMap) {
                   var col = p.nodeClassMap[k];
                   if (col[0]) {
                       sb += "<div class='st_node_legend "+ col[0] + 
                       "'>&nbsp;</div><div style='display:inline;'>" + col[1] + "</div>";
                   }
               }
               sb += "</div>";
               sb += "<div class='st_legend'>";
                  for (var k in p.lineClassMap) {
                       var col = p.lineClassMap[k];
                       if (col[0]) {
                           sb += "<div class='st_line_legend " + col[0] + 
                           "'>&nbsp;</div><div style='display:inline;'>" + col[1] + "</div>";
                       }
                   }
               sb += "</div>";
               p._offsetTop = p.nodeHeight;
               return sb;
             },
             showDetail : function(event) {
                 var tipDiv = $("#st_tip_div",contentDiv);
                 var branch = event.data.data.nodeId;
                 var bc = $("#" +branch).position();
                 if (p.showTip) {
                     if (tipDiv) {
                         tipDiv.show();
                         p.showTip(tipDiv,$("#" +branch)[0].data._data);
                         tipDiv.css('left', bc.left+50);
                         var y;
                         if (bc.top < tipDiv.height()) {
                             y = bc.top + this.nodeHeight;
                         } else {
                             y = bc.top -  tipDiv.height();
                         }
                         tipDiv.css('top' ,y + "px");
                     }
                  }
              },
              hideDetail : function(event) {
                  $("#st_tip_div",contentDiv).hide();
              },
              _buildMenu : function() {
                  var sb = [];
                  sb.push("<div class='st_menu' style='display:none;'>");
                  sb.push("<ul>");
                  if (p.menuitems) {
                      for (var x = 0 ; x < p.menuitems.length ; x++) {
                          sb.push("<li>");
                          sb.push(p.menuitems[x].name);
                          sb.push("</li>");
                      }
                  }
                  sb.push("</ul>");
                  sb.push("</div>");
                  return sb.join('');
              },
              showMenu : function(event) {
                  pm.hideDetail();
                  if (!$(event.target).hasClass("st_nodecontent")) {
                      return;
                  }
                  var data = $(event.target).parent()[0].data._data;
                  var isBranch = ( p.canExpand && p.canExpand(data));
                  var isRoot = p.isRootRecord(data);
                  if (p.menuitems == null || p.menuitems.length == 0){
                      return;
                  }
                  $('.st_menu ul li').each(function(i ,item) {
                      $(item).unbind('click');
                      var filter = p.menuitems[i].filter;
                      var ok = false;
                      if (filter == null || filter.indexOf('all') >= 0) {
                          ok = true;
                      }
                      if (!ok && isBranch && !isRoot) {
                          if (filter.indexOf("branch") >= 0 ) {
                              ok = true;
                          }
                      }
                      else if (!ok && isRoot) {
                          if (filter.indexOf("root")>= 0) {
                              ok = true;
                          }
                      }
                      if (!ok && !isBranch) {
                          if (filter.indexOf("leaf")>= 0) {
                              ok = true;
                          }
                      }
                      if (ok) {
                          $(item).removeClass('disabled');
                          var mydata = $(event.target).parent()[0].data;
                          $(item).bind('click',mydata,p.menuitems[i].click);
                      }
                      else {
                          $(item).addClass('disabled');
                      }
                  });
                  var pp = $(contentDiv).parent();
                  var nh = event.pageY-5;
                  if (pp.height() < event.pageY + $(".st_menu",pp[0]).height() ){
                      nh = event.pageY - $(".st_menu",pp[0]).height() + 5;
                  }
                  $(".st_menu",pp[0]).css('left',event.pageX-5);
                  $(".st_menu",pp[0]).css('top',nh);
                  $(".st_menu",pp[0]).show();
              },
              hideMenu : function(event) {
                  var pp = $(contentDiv).parent();
                  $(".st_menu",pp[0]).hide();
              }
        };
        var g = {
            getData : function(id) {
                var node = pm._getNode(g._nodes, id);
                if (node) {
                    return node.getData();
                }
                return null;
            },
            insertNode : function(record) {
                var nodeId = record[p.nodeIdField];
                var node = pm._getNode(g._nodes, nodeId);
                if (!node) {
                    var nodeName = record[p.nodeNameField];
                    var parentId = record[p.parentField];
                    if (!parentId) {
                        return;
                    }
                    node = new pm.TNode(nodeId, nodeName, record);
                    g._nodes[g._nodes.length] = node;
                    var parent = pm._getNode(g._nodes, parentId);
                    if (parent) {
                        parent.addNode(node);
                        node.parent = parent;
                        node.level = parent.level + 1;
                        var parentDoc = $('#B' + parent.nodeId);
                        var ndoc =node.buildDom(parentDoc[0],0);
                        $(ndoc).click(pm.st_showBranch);
                        $(ndoc).bind("mouseenter",{data:ndoc.data},pm.showDetail);
                        $(ndoc).bind("mouseleave", pm.hideDetail);
                        if (p.renderUnit) {
                            var mydata = ndoc.data._data;
                            var nodecontent = $('.st_nodecontent',ndoc);
                            p.renderUnit(0,nodecontent[0],mydata);
                        }
                        var sb = [];
                        if (parent._childs.length == 1) {
                            //fisrt line
                            sb.push("<div class='st_linefirst' style=\"width:1px;");
                            sb.push("height:" + p.lineHeight+ "px;\"></div>");
                        } else {
                            sb.push("<div class='st_line' ");
                            sb.push("id='l_" + nodeId + "' ");
                            sb.push("style='height:" + p.lineHeight + "px;'></div>");
                        }
                        parentDoc.append(sb.join(''));
                        _rootNode.refreshPosition(contentDiv,p.leftoffset);
                        $(contentDiv).css('width',_rootNode.width);
                    }
                }
            },
            removeNode : function(record) {
                var nodeId = record[p.nodeIdField];
                var node = pm._getNode(g._nodes, nodeId);
                if (node) {
                    var parent = node.parent;
                    if (parent) {
                        var fn = parent.getFirstNode();
                        parent.removeNode(node);
                        if (fn == node) {
                            if (parent.getChildCount() == 0) {
                                var pd = $('#B'+ parent.nodeId);
                                $('.st_linefirst',pd[0]).remove();
                            }
                            else {
                                $('#l_' + parent.getFirstNode().nodeId).removeClass('st_line').addClass('st_linefirst');
                            }
                        }
                        $('#l_' + nodeId).remove();
                        $('#'+ nodeId).remove();
                        $('#B'+ nodeId).remove();
                        _rootNode.refreshPosition(contentDiv,p.leftoffset);
                        $(contentDiv).css('width',_rootNode.width);
                    }
                }
            },
            modifyNode : function(record) {
                var nodeId = record[p.nodeIdField];
                var node = pm._getNode(g._nodes, nodeId);
                if (node) {
                    node._data = record;
                    node.nodeName = record[p.nodeNameField];
                    if (p.renderUnit) {
                        var nd = $('#' + nodeId + ' .st_nodecontent')[0];
                        p.renderUnit(0,nd,record);
                    }
                    _rootNode.refreshPosition(contentDiv,p.leftoffset);
                    $(contentDiv).css('width',_rootNode.width);
                }
            },
            _nodes : [] 

        };
        //内部对象构建
        pm._buildTNode();
        if (_rootNode) {
            var innerHtml = pm._buildLegend();
            $(this).append(innerHtml);
            $('.st_node_legend',this[0]).css('width',p.nodeWidth + 'px')
                .css('height',p.nodeHeight +'px').css('line-height',p.nodeHeight +'px');
            $(this).append(pm._buildMenu());
            $(contentDiv).append(pm._buildTipDiv());
            _rootNode.buildDom(contentDiv,p.leftoffset);
            $(contentDiv).css('width',_rootNode.width);
            $('.st_menu').bind("mouseleave", pm.hideMenu);
            $('.st_node').click(pm.st_showBranch);
            $('.st_node').each(function(n,item) {
                if (p.renderUnit) {
                    var nodecontent = $('.st_nodecontent',item);
                    p.renderUnit(n,nodecontent[0],item.data._data);
                }
                $(item).bind("mouseenter",{data:item.data},pm.showDetail);
                $(item).bind("mouseleave", pm.hideDetail);
            });
            document.oncontextmenu = function(e){
                if (e.button == 2) {//右键)
                    pm.showMenu(e);
                    return false;
                }
                return true;
            };
        }
        else {
            $(this).html("根节点为空");
        }
        return g;
    };
})(jQuery);
