package cn.ffcs.pojo;

public class State {
	
	boolean checked = false; // 指示一个节点是否处于checked状态，用一个checkbox图标表示
	boolean disabled = false; // 指示一个节点是否处于disabled状态。（不是selectable，expandable或checkable）
	boolean expanded = true; // 指示一个节点是否处于展开状态
	boolean selected = false; // 指示一个节点是否可以被选择

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}
}
