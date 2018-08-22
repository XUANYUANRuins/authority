package cn.ffcs.pojo;

import java.io.Serializable;
import java.util.List;

public class TreeView implements Serializable {

	private static final long serialVersionUID = 4516142782066120414L;
	
	private Object id;
    private Object parentId;
    private Object status;	//状态
    private String url;
    
    /**
     * 以下TreeView节点属性
     */
    private String text;	//列表树节点上的文本，通常是节点右边的小图标
    private String icon;	//列表树节点上的图标，通常是节点左边的图标
    private String selectedIcon;	//当某个节点被选择后显示的图标，通常是节点左边的图标
    private String href;	//结合全局enableLinks选项为列表树节点指定URL
    private boolean selectable = true;	//指定列表树的节点是否可选择。设置为false将使节点展开，并且不能被选择
    private Object state;	//一个节点的初始状态 
    private String color;	//节点的前景色，覆盖全局的前景色选项
    private String backColor;	//节点的背景色，覆盖全局的背景色选项
    private String[] tags;	//通过结合全局showTags选项来在列表树节点的右边添加额外的信息
    
    // 子节点
    private List<TreeView> nodes;
    

	public Object getId() {
		return id;
	}

	public void setId(Object id) {
		this.id = id;
	}

	public Object getParentId() {
		return parentId;
	}

	public void setParentId(Object parentId) {
		this.parentId = parentId;
	}

	public Object getStatus() {
		return status;
	}

	public void setStatus(Object status) {
		this.status = status;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getSelectedIcon() {
		return selectedIcon;
	}

	public void setSelectedIcon(String selectedIcon) {
		this.selectedIcon = selectedIcon;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public boolean isSelectable() {
		return selectable;
	}

	public void setSelectable(boolean selectable) {
		this.selectable = selectable;
	}
	
	public Object getState() {
		return state;
	}

	public void setState(Object state) {
		this.state = state;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBackColor() {
		return backColor;
	}

	public void setBackColor(String backColor) {
		this.backColor = backColor;
	}

	public String[] getTags() {
		return tags;
	}

	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public List<TreeView> getNodes() {
		return nodes;
	}

	public void setNodes(List<TreeView> nodes) {
		this.nodes = nodes;
	}
    
	
}
