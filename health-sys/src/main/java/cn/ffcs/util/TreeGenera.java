package cn.ffcs.util;

import java.util.ArrayList;
import java.util.List;

import cn.ffcs.pojo.Node;

public class TreeGenera<T extends Node> {
	
	private List<T> list = new ArrayList<>();
	private List<T> jqgridlist = new ArrayList<>();	//排序后的列表
	private String NODE_ID = "id";
	
	public TreeGenera(List<T> list) {
		this.list = list;
	}
	
	//开始排序
	public List<T> startSorting(){
		List<T> roots = findRoots();
		for(T rootNode : roots){
			jqgridlist.add(rootNode);
			deepSearchChildNodes(rootNode);
		}
		return jqgridlist;
	}
	
	public void deepSearchChildNodes(T parent) {
		parent.isLeaf = false;
		List<T> findChildAtNode = findChildAtNode(parent);
		if( findChildAtNode.size() ==0 ){
			parent.isLeaf = true;
			return;
		} else {
			for(T n : findChildAtNode) {
				n.level = parent.level + 1;
			}
		}
		
		while(findChildAtNode.size()>0){
			jqgridlist.add(findChildAtNode.get(0));
			deepSearchChildNodes(findChildAtNode.get(0));
			findChildAtNode.remove(0);
		}
	}
	
	public List<T> findRoots(){
		List<T> roots = new ArrayList<>();
		for(T n : list){
			if(n.parent == null || "".equals(n.parent) || "0".equals(n.parent))
				roots.add(n);
		}
		return roots;
	}
	
	public List<T> findChildAtNode(T currentNode) {
		List<T> childs = new ArrayList<>();
		for(T n : list) {
			if(n == currentNode) continue;
			Object id = MethodUtils.getFieldValue(currentNode, NODE_ID);
			if(String.valueOf(id).equals(n.parent)) {
				childs.add(n);
			}
		}
		return childs;
	}
	
}
