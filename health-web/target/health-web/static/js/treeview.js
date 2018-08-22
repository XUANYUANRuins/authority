/**
 * 取得node数组
 * @param node
 * @returns {Array}
 */
function getNodeIdArr(node) {
	var ts = [];
	if (node.nodes) {
		for (x in node.nodes) {
			ts.push(node.nodes[x].nodeId)
			if (node.nodes[x].nodes) {
				var getNodeDieDai = getNodeIdArr(node.nodes[x]);
				for (j in getNodeDieDai) {
					ts.push(getNodeDieDai[j]);
				}
			}
		}
	} else {
		ts.push(node.nodeId);
	}
	return ts;
}

/**
 * 选中子节点，父节点也选中
 * @param node
 */
function cascade(tabTreeView,node){
	var parentNode = tabTreeView.treeview('getParent', node);//获取父节点			
	 if(parentNode.nodeId!==undefined && parentNode.nodes != null ){ //存在父节点且子节点不为空，则选中父节点
		
		 tabTreeView.treeview('checkNode', [ parentNode, {
				silent : true
			} ]);
		 cascade(tabTreeView,parentNode);
	 }
}


/**
 * 获取兄弟节点已勾选个数
 * @param node
 */
function checkSibling(tabTreeView,node){
	var siblingNode = tabTreeView.treeview('getSiblings', node);//获取兄弟节点
	var checkNum = 0;
	for(var i=0;i<siblingNode.length;i++){		
		if(siblingNode[i].state.checked == true){//判断兄弟节点是否已勾选
			checkNum +=1;					
		}
	}
	return checkNum;
}

/**
 * 子节点为空时，父节点也为空
 * @param tabTreeView
 * @param node
 */
function uncheckCascade(tabTreeView,node){
	
	var checkNum = checkSibling(tabTreeView,node);
	var parentNode = tabTreeView.treeview('getParent', node);//获取父节点			
	if(parentNode.nodeId!==undefined && checkNum <= 0){ //存在父节点且兄弟节点都未勾选，则取消选中父节点
		tabTreeView.treeview('uncheckNode', [ parentNode, {
			silent : true
		} ])
		uncheckCascade(tabTreeView,parentNode);
	}	
}


/**
 * 选中该节点下的所有子节点
 * @param tabTreeView
 * @param node
 */

function checkNode(tabTreeView, node){
	var selectNodes = getNodeIdArr(node);// 获取所有子节点
	if (selectNodes) { // 子节点不为空，则选中所有子节点
		tabTreeView.treeview('checkNode', [ selectNodes, {
			silent : true
		} ]);
	}	
}

/**
 * 取消选中该节点下的所有子节点
 * @param tabTreeView
 * @param node
 */
function uncheckNode(tabTreeView, node){
	var selectNodes = getNodeIdArr(node);// 获取所有未子节点
	if (selectNodes) { // 子节点不为空，则取消选中所有子节点
		tabTreeView.treeview('uncheckNode', [ selectNodes, {
			silent : true
		} ]);
	}
}

/**
 * 全选
 * @param tabTreeView
 */
function chooseAll(tabTreeView){
	tabTreeView.treeview('checkAll', {
		silent : true
	});
}


/**
 * 反选
 * @param tabTreeView
 */
function reverse(tabTreeView){
	var checklist = tabTreeView.treeview('getChecked');//获取已勾选的节点
	var unchecklist = tabTreeView.treeview('getUnchecked');//获取未勾选的节点

	if(checklist!= null ){	
		 tabTreeView.treeview('uncheckNode', [ checklist, {
				silent : true
			} ]);
		 var nodes = tabTreeView.treeview('getChecked');//获取已勾选的节点
		 for(var i=0;i<nodes.length;i++){
			 cascade(tabTreeView,nodes[i]);
		 }
	}	
	if(unchecklist!= null ){
		 tabTreeView.treeview('checkNode', [ unchecklist, {
				silent : true
			} ]);
		 
		 var nodes = tabTreeView.treeview('getChecked');//获取已勾选的节点
		 for(var i=0;i<nodes.length;i++){
			 cascade(tabTreeView,nodes[i]);
		 }
    } 
}