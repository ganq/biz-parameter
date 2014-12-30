package com.mysoft.b2b.bizsupport.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * （云平台）基础分类节点--数据模型
 * 
 * @author liucz
 * 
 */
public class BasicCategoryNode extends BasicCategory implements Serializable {
	private static final long serialVersionUID = 7459931379411666487L;

	// 是否末级
	private int isLeaf;
	// 分类级别
	private int hierarchyLevel;	
	//是否展开
	private boolean expanded;

	// 子分类
	private List<BasicCategoryNode> childBasicCategoryNodes = new ArrayList<BasicCategoryNode>();
	
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

	public List<BasicCategoryNode> getChildBasicCategoryNodes() {
		return childBasicCategoryNodes;
	}

	public void setChildBasicCategoryNodes(
			List<BasicCategoryNode> childBasicCategoryNodes) {
		this.childBasicCategoryNodes = childBasicCategoryNodes;
	}
	
	public void addChildBasicCategoryNode(BasicCategoryNode child) {
		getChildBasicCategoryNodes().add(child);
	}
}
