/**
 * 
 */
package com.mysoft.b2b.bizsupport.util;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import com.mysoft.b2b.bizsupport.api.BasicCategoryNode;
import com.mysoft.b2b.bizsupport.api.StandardCategoryNode;
import com.mysoft.b2b.bizsupport.provider.BasicCategoryDO;
import com.mysoft.b2b.bizsupport.provider.StandardCategoryDO;
import com.mysoft.b2b.bizsupport.vo.BasicCategoryVO;
import com.mysoft.b2b.bizsupport.vo.StandardCategoryVO;
import com.mysoft.b2b.commons.exception.PlatformUncheckException;

/**
 * @author liucz
 * 
 */
public class BizSupportUtil {
	public static final String FIELD_ITEM_ID = "category_id";
	public static final String FIELD_ITEM_TYPE = "category_shortname";
	public static final String FIELD_ITEM_CODE = "category_name";
	public static final String FIELD_ITEM_NAME = "category_code";
	public static final String FIELD_ITEM_VALUE = "parent_code";
	public static final String FIELD_ITEM_STATUS = "creation_time";
	public static final String FIELD_PARENT_ITEM_CODE = "last_modified_time";
	public static final String FIELD_LAST_MODIFIED_TIME = "is_leaf";
	public static final String FIELD_DISPLAY_ORDER = "hierarchy_level";
	
	public static final String CACHE_LIVE_BASIC_CATEGORY = "liveBasicCategory";
	public static final String CACHE_KEY_MAP_BASIC_CATEGORY = "LiveBasicCategoryNode";
	public static final String CACHE_ALL_BASIC_CATEGORY = "allBasicCategory";
	public static final String CACHE_KEY_MAP_ALL_BASIC_CATEGORY = "AllBasicCategory";
	//public static final String CACHE_BASIC_CATEGORY_DO = "basicCategoryDO";
	//public static final String CACHE_KEY_MAP_BASIC_CATEGORY_DO = "BasicCategoryDO";
	//public static final String CACHE_BASIC_CATEGORY_VO = "basicCategoryVO";
	//public static final String CACHE_KEY_MAP_BASIC_CATEGORY_VO = "BasicCategoryVO";
	
	public static final String CACHE_LIVE_STANDARD_CATEGORY = "liveStandardCategory";
	public static final String CACHE_KEY_MAP_STANDARD_CATEGORY = "LiveStandardCategoryNode";
	public static final String CACHE_ALL_STANDARD_CATEGORY = "allStandardCategory";
	public static final String CACHE_KEY_MAP_ALL_STANDARD_CATEGORY = "AllStandardCategory";
	//public static final String CACHE_STANDARD_CATEGORY_DO = "standardCategoryDO";
	//public static final String CACHE_KEY_MAP_STANDARD_CATEGORY_DO = "StandardCategoryDO";
	//public static final String CACHE_STANDARD_CATEGORY_VO = "standardCategoryVO";
	//public static final String CACHE_KEY_MAP_STANDARD_CATEGORY_VO = "StandardCategoryVO";
	
	public static final int SORT_VALUE_TYPE_INT = 1;
	public static final int SORT_VALUE_TYPE_STRING = 2;
	public static final int STATUS_DEATH = 0;
	public static final int STATUS_LIVENESS = 1;
	
	/**
	 * 克隆云平台基础分类节点（不含子节点）
	 * 
	 * @param sourceBasicCategoryNode
	 *            来源基础分类节点
	 * @return
	 */	
	public static BasicCategoryNode cloneBasicCategoryNodeWithoutChildren(BasicCategoryNode sourceBasicCategoryNode) {
		BasicCategoryNode targetBasicCategoryNode = new BasicCategoryNode();
		targetBasicCategoryNode.setCategoryCode(sourceBasicCategoryNode.getCategoryCode());
		targetBasicCategoryNode.setCategoryName(sourceBasicCategoryNode.getCategoryName());
		targetBasicCategoryNode.setCategoryShortname(sourceBasicCategoryNode.getCategoryShortname());
		targetBasicCategoryNode.setDisplayOrder(sourceBasicCategoryNode.getDisplayOrder());
		targetBasicCategoryNode.setHierarchyLevel(sourceBasicCategoryNode.getHierarchyLevel());
		targetBasicCategoryNode.setIsLeaf(sourceBasicCategoryNode.getIsLeaf());
		targetBasicCategoryNode.setParentCode(sourceBasicCategoryNode.getParentCode());
		targetBasicCategoryNode.setCategoryStatus(sourceBasicCategoryNode.getCategoryStatus());
		return targetBasicCategoryNode;
	}
	
	/**
	 * 转换云平台基础分类DO为Node
	 * 
	 * @param sourceBasicCategoryNode
	 *            来源基础分类节点
	 * @return
	 */	
	public static BasicCategoryNode convertBasicCategoryDO2Node(BasicCategoryDO sourceBasicCategoryDO) {
		BasicCategoryNode targetBasicCategoryNode = new BasicCategoryNode();
		targetBasicCategoryNode.setCategoryCode(sourceBasicCategoryDO.getBasicCategory().getCategoryCode());
		targetBasicCategoryNode.setCategoryName(sourceBasicCategoryDO.getBasicCategory().getCategoryName());
		targetBasicCategoryNode.setCategoryShortname(sourceBasicCategoryDO.getBasicCategory().getCategoryShortname());
		targetBasicCategoryNode.setDisplayOrder(sourceBasicCategoryDO.getBasicCategory().getDisplayOrder());
		targetBasicCategoryNode.setHierarchyLevel(sourceBasicCategoryDO.getHierarchyLevel());
		targetBasicCategoryNode.setIsLeaf(sourceBasicCategoryDO.getIsLeaf());
		targetBasicCategoryNode.setParentCode(sourceBasicCategoryDO.getBasicCategory().getParentCode());
		targetBasicCategoryNode.setCategoryStatus(sourceBasicCategoryDO.getBasicCategory().getCategoryStatus());
		return targetBasicCategoryNode;
	}
	
	/**
	 * 转换云平台基础分类DO为VO
	 * 
	 * @param sourceBasicCategoryNode
	 *            来源基础分类节点
	 * @return
	 */	
	public static BasicCategoryVO convertBasicCategoryDO2VO(BasicCategoryDO sourceBasicCategoryDO) {
		BasicCategoryVO targetBasicCategoryVO = new BasicCategoryVO();		
		targetBasicCategoryVO.setCategoryCode(sourceBasicCategoryDO.getBasicCategory().getCategoryCode());
		targetBasicCategoryVO.setCategoryName(sourceBasicCategoryDO.getBasicCategory().getCategoryName());
		targetBasicCategoryVO.setCategoryShortname(sourceBasicCategoryDO.getBasicCategory().getCategoryShortname());
		targetBasicCategoryVO.setDisplayOrder(sourceBasicCategoryDO.getBasicCategory().getDisplayOrder());
		//targetBasicCategoryVO.setHierarchyLevel(sourceBasicCategoryDO.getHierarchyLevel());
		//targetBasicCategoryVO.setIsLeaf(sourceBasicCategoryDO.getBasicCategory().getIsLeaf());
		targetBasicCategoryVO.setParentCode(sourceBasicCategoryDO.getBasicCategory().getParentCode());
		targetBasicCategoryVO.setCategoryStatus(sourceBasicCategoryDO.getBasicCategory().getCategoryStatus());
		targetBasicCategoryVO.setCreationTime(sourceBasicCategoryDO.getCreationTime());
		targetBasicCategoryVO.setLastModifiedTime(sourceBasicCategoryDO.getLastModifiedTime());
		targetBasicCategoryVO.setOperator(sourceBasicCategoryDO.getOperator());
		return targetBasicCategoryVO;
	}
	

	/**
	 * 克隆行业标准分类节点（不含子节点）
	 * 
	 * @param sourceStandardCategoryNode
	 *            来源行业标准分类节点
	 * @return
	 */	
	public static StandardCategoryNode cloneStandardCategoryNodeWithoutChildren(StandardCategoryNode sourceStandardCategoryNode) {
		StandardCategoryNode targetStandardCategoryNode = new StandardCategoryNode();
		targetStandardCategoryNode.setCategoryCode(sourceStandardCategoryNode.getCategoryCode());
		targetStandardCategoryNode.setCategoryUiCode(sourceStandardCategoryNode.getCategoryUiCode());
		targetStandardCategoryNode.setCategoryName(sourceStandardCategoryNode.getCategoryName());
		targetStandardCategoryNode.setCategoryShortname(sourceStandardCategoryNode.getCategoryShortname());
		targetStandardCategoryNode.setDisplayOrder(sourceStandardCategoryNode.getDisplayOrder());
		targetStandardCategoryNode.setHierarchyLevel(sourceStandardCategoryNode.getHierarchyLevel());
		targetStandardCategoryNode.setIsLeaf(sourceStandardCategoryNode.getIsLeaf());
		targetStandardCategoryNode.setParentCode(sourceStandardCategoryNode.getParentCode());
		targetStandardCategoryNode.setCategoryStatus(sourceStandardCategoryNode.getCategoryStatus());
		return targetStandardCategoryNode;
	}
	
	/**
	 * 转换行业标准分类DO为Node
	 * 
	 * @param sourceStandardCategoryNode
	 *            来源行业标准分类节点
	 * @return
	 */	
	public static StandardCategoryNode convertStandardCategoryDO2Node(StandardCategoryDO sourceStandardCategoryDO) {
		StandardCategoryNode targetStandardCategoryNode = new StandardCategoryNode();
		targetStandardCategoryNode.setCategoryCode(sourceStandardCategoryDO.getStandardCategory().getCategoryCode());
		targetStandardCategoryNode.setCategoryUiCode(sourceStandardCategoryDO.getStandardCategory().getCategoryUiCode());
		targetStandardCategoryNode.setCategoryName(sourceStandardCategoryDO.getStandardCategory().getCategoryName());
		targetStandardCategoryNode.setCategoryShortname(sourceStandardCategoryDO.getStandardCategory().getCategoryShortname());
		targetStandardCategoryNode.setDisplayOrder(sourceStandardCategoryDO.getStandardCategory().getDisplayOrder());
		targetStandardCategoryNode.setHierarchyLevel(sourceStandardCategoryDO.getHierarchyLevel());
		targetStandardCategoryNode.setIsLeaf(sourceStandardCategoryDO.getIsLeaf());
		targetStandardCategoryNode.setParentCode(sourceStandardCategoryDO.getStandardCategory().getParentCode());
		targetStandardCategoryNode.setCategoryStatus(sourceStandardCategoryDO.getStandardCategory().getCategoryStatus());
		return targetStandardCategoryNode;
	}
	
	/**
	 * 转换行业标准分类DO为VO
	 * 
	 * @param sourceStandardCategoryNode
	 *            来源行业标准分类节点
	 * @return
	 */	
	public static StandardCategoryVO convertStandardCategoryDO2VO(StandardCategoryDO sourceStandardCategoryDO) {
		StandardCategoryVO targetStandardCategoryVO = new StandardCategoryVO();		
		targetStandardCategoryVO.setCategoryCode(sourceStandardCategoryDO.getStandardCategory().getCategoryCode());
		targetStandardCategoryVO.setCategoryUiCode(sourceStandardCategoryDO.getStandardCategory().getCategoryUiCode());
		targetStandardCategoryVO.setCategoryName(sourceStandardCategoryDO.getStandardCategory().getCategoryName());
		targetStandardCategoryVO.setCategoryShortname(sourceStandardCategoryDO.getStandardCategory().getCategoryShortname());
		targetStandardCategoryVO.setDisplayOrder(sourceStandardCategoryDO.getStandardCategory().getDisplayOrder());
		//targetStandardCategoryVO.setHierarchyLevel(sourceStandardCategoryDO.getHierarchyLevel());
		//targetStandardCategoryVO.setIsLeaf(sourceStandardCategoryDO.getStandardCategory().getIsLeaf());
		targetStandardCategoryVO.setParentCode(sourceStandardCategoryDO.getStandardCategory().getParentCode());
		targetStandardCategoryVO.setCategoryStatus(sourceStandardCategoryDO.getStandardCategory().getCategoryStatus());
		targetStandardCategoryVO.setCreationTime(sourceStandardCategoryDO.getCreationTime());
		targetStandardCategoryVO.setLastModifiedTime(sourceStandardCategoryDO.getLastModifiedTime());
		targetStandardCategoryVO.setOperator(sourceStandardCategoryDO.getOperator());
		return targetStandardCategoryVO;
	}

	/**
	 * 取当前时间
	 * 
	 * @return String
	 */
	public static String getNowDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = null;
		try {
			datetime = format.format(new Date());
		} catch (Exception e) {
			throw new PlatformUncheckException(e.getMessage(), null, e);
		}
		return datetime;
	}
	
	/**
	 * 格式化时间
	 * 
	 * @return String
	 */
	public static String formateDate(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String datetime = null;
		try {
			datetime = format.format(date);
		} catch (Exception e) {
			throw new PlatformUncheckException(e.getMessage(), null, e);
		}
		return datetime;
	}

	/**
	 * 字符是否为null或者空串
	 * 
	 * @param value
	 *            字符
	 * @return boolean
	 */
	public static boolean isNullOrEmpty(final String value) {
		if (value == null || value.equals("")) {
			return true;
		}
		return false;
	}

	/**
	 * 根据属性名获取属性值
	 * 
	 * @param bean
	 *            对象
	 * @param name
	 *            属性名
	 * @return 属性值
	 */
	public static String getValueByProperty(final Object bean, final String name) {
		String value = null;
		Object o;
		try {
			o = BeanUtils.getPropertyDescriptor(bean.getClass(), name).getReadMethod().invoke(bean);
			if (o != null) {
				value = o.toString();
			}
		} catch (BeansException e) {
			throw new PlatformUncheckException(e.getMessage(), null, e);
		} catch (IllegalArgumentException e) {
			throw new PlatformUncheckException(e.getMessage(), null, e);
		} catch (IllegalAccessException e) {
			throw new PlatformUncheckException(e.getMessage(), null, e);
		} catch (InvocationTargetException e) {
			throw new PlatformUncheckException(e.getMessage(), null, e);
		}
		
		return value;
	}

	/**
	 * 集合是否为空
	 * 
	 * @param collection
	 *            集合
	 * @return boolean
	 */
	@SuppressWarnings("rawtypes")
	public static boolean checkIsNullOrEmpty(final Collection collection) {
		if (collection == null) {
			return true;
		}
		if (collection.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 根据属性名按升序排序List
	 * 
	 * @param listBean
	 *            Beand List
	 * @param property
	 *            排序属性名
	 * @param valueType
	 *            属性值类型
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void sortListByPropertyInAsc(final List listBean, final String property, final int valueType) {
		if (checkIsNullOrEmpty(listBean)) {
			return;
		}
		Collections.sort(listBean, new Comparator() {
			public int compare(final Object obj0, final Object obj1) {
				String val0 = getValueByProperty(obj0, property);
				if (isNullOrEmpty(val0)) {
					val0 = "0";
				}
				String val1 = getValueByProperty(obj1, property);
				if (isNullOrEmpty(val1)) {
					val1 = "0";
				}
				if (valueType == SORT_VALUE_TYPE_INT) {
					long i0 = Long.parseLong(val0);
					long i1 = Long.parseLong(val1);
					if (i0 == i1) {
						return 0;
					} else if (i0 > i1) {
						return 1;
					} else {
						return -1;
					}
				} else {
					return val0.compareTo(val1);
				}
			}
		});
	}

	/**
	 * 多属性按字符升序排序List
	 * 
	 * @param listBean
	 *            Beand List
	 * @param properties
	 *            排序属性名(example: name01,name02,name03)
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void sortListByPropertiesInAsc(final List listBean, final String properties) {
		if (checkIsNullOrEmpty(listBean)) {
			return;
		}
		Collections.sort(listBean, new Comparator() {
			public int compare(final Object obj0, final Object obj1) {
				String val0;
				String val1;
				int result = 0;
				String[] names = properties.split(",");
				for (int i = 0; i < names.length; i++) {
					val0 = getValueByProperty(obj0, names[i]);
					if (isNullOrEmpty(val0)) {
						val0 = "0";
					}
					val1 = getValueByProperty(obj1, names[i]);
					if (isNullOrEmpty(val1)) {
						val1 = "0";
					}
					result = val0.compareTo(val1);
					if (result != 0) {
						break;
					}
				}

				return result;
			}
		});
	}

}
