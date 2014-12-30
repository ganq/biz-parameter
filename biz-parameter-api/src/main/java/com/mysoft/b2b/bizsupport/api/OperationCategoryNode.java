package com.mysoft.b2b.bizsupport.api;

import java.util.ArrayList;
import java.util.List;

/**
 * （云平台）运营分类节点--数据模型
 *
 * @author liuza
 *
 */
public class OperationCategoryNode extends BasicCategory {
	private static final long serialVersionUID = 8466147156612270236L;

	// 是否叶子
    private int isLeaf;
    // 分类级别
    private int hierarchyLevel;
    //是否展开
    private boolean expanded;

    //是否末级分类，此分类放方可绑定数据
    //private boolean isLastLevel;
    // 子分类
    private List<OperationCategoryNode> childrenCategoryNodes = new ArrayList<OperationCategoryNode>();

    //是否有效启用的节点 (categoryStatus：当上级停用时，下级也无效)
    private boolean isEffective;

    public int getHierarchyLvel() {
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

    public List<OperationCategoryNode> getChildrenCategoryNodes() {
        return childrenCategoryNodes;
    }

    public void setChildBasicCategoryNodes(
            List<OperationCategoryNode> childrenCategoryNodes) {
        this.childrenCategoryNodes = childrenCategoryNodes;
    }

    public void addChildrenCategoryNode(OperationCategoryNode child) {
        getChildrenCategoryNodes().add(child);
    }

    public boolean isEffective() {
        return isEffective;
    }

    public void setEffective(boolean effective) {
        isEffective = effective;
    }

    public static OperationCategoryNode categoryToNode(BasicCategory category){
    	OperationCategoryNode node = new OperationCategoryNode();
    	node.setCategoryCode(category.getCategoryCode());
    	node.setCategoryName(category.getCategoryName());
    	node.setCategoryShortname(category.getCategoryShortname());
    	node.setCategoryStatus(category.getCategoryStatus());
    	node.setDisplayOrder(category.getDisplayOrder());
    	node.setParentCode(category.getParentCode());
    	node.setLastLevel(category.isLastLevel());
    	return node;
    }
	/*public boolean isLastLevel() {
		return isLastLevel;
	}

	public void setLastLevel(boolean isLastLevel) {
		this.isLastLevel = isLastLevel;
	}*/
}
