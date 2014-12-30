package com.mysoft.b2b.bizsupport.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * （行业）标准分类节点--数据模型
 * 
 * @author liucz
 * 
 */
public class StandardCategoryNode extends StandardCategory implements Serializable {
	private static final long serialVersionUID = 5414276695941257626L;

	// 是否末级
	private int isLeaf;
	// 分类级别
	private int hierarchyLevel;	
	//是否展开
	private boolean expanded;

	// 子分类
	private List<StandardCategoryNode> childStandardCategoryNodes = new ArrayList<StandardCategoryNode>();

	public List<StandardCategoryNode> getChildStandardCategoryNodes() {
		return childStandardCategoryNodes;
	}

	public void setChildStandardCategoryNodes(
			List<StandardCategoryNode> childStandardCategoryNodes) {
		this.childStandardCategoryNodes = childStandardCategoryNodes;
	}

	public void addChildStandardCategoryNode(StandardCategoryNode child) {
		getChildStandardCategoryNodes().add(child);
	}
	
	public int getHierarchyLevel() {
		return hierarchyLevel;
	}

	public void setHierarchyLevel(int hierarchyLevel) {
		this.hierarchyLevel = hierarchyLevel;
	}
	
	public int getIsLeaf() {
		return isLeaf;
	}

	public void setIsLeaf(int isLeaf) {
		this.isLeaf = isLeaf;
	}
	
	public boolean isExpanded() {
		return expanded;
	}

	public void setExpanded(boolean expanded) {
		this.expanded = expanded;
	}
}
