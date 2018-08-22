package cn.ffcs.util;

import java.util.ArrayList;
import java.util.List;

import cn.ffcs.model.TbMenu;
import cn.ffcs.pojo.TreeView;


public class TreeUtils {

    /**
     * 获取树节点
     * @param treeDataList
     * @return
     */
    public final static List<TreeView> formatNode(List<TreeView> treeDataList) {
        List<TreeView> newTreeDataList = new ArrayList<TreeView>();
        for (TreeView tree : treeDataList) {
            if(tree.getParentId() == null || "0".equals(tree.getParentId().toString())) {
                //获取父节点下的子节点
                tree.setNodes(getChildrenNode(tree.getId(),treeDataList));
                newTreeDataList.add(tree);
            }
        }
        return newTreeDataList;
    }
    
    /**
     * 递归获取子节点
     * @param parentId
     * @param treeDataList
     * @return
     */
    private final static List<TreeView> getChildrenNode(Object parentId , List<TreeView> treeDataList) {
        List<TreeView> newTreeDataList = new ArrayList<TreeView>();
        for (TreeView tree : treeDataList) {
            if(tree.getParentId() == null || "0".equals(tree.getParentId().toString()))  continue;
            //这是一个子节点
            if(tree.getParentId().equals(parentId)){
                //递归获取子节点下的子节点
                tree.setNodes(getChildrenNode(tree.getId() , treeDataList));
                newTreeDataList.add(tree);
            }
        }
        return newTreeDataList;
    }
    
    
    /**
     * 格式化菜单树
     * @param menus
     * @return
     */
    public final static List<TreeView> formatMenuTree(List<TbMenu> menus){
        List<TreeView> treeList = new ArrayList<TreeView>();
        for(TbMenu menu : menus){
            TreeView tree = new TreeView();
            tree.setId(menu.getId());
            tree.setText(menu.getName());
            tree.setIcon(menu.getIcon());
            tree.setParentId(menu.getParentid());
            tree.setUrl(menu.getUrl());
            tree.setStatus(menu.getStatus());
            
            treeList.add(tree);
        }
        
        return formatNode(treeList);
    }
    
}
