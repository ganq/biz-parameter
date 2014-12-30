package com.mysoft.b2b.bizsupport.api;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Think
 * Date: 14-3-5
 * Time: 上午9:30
 * To change this template use File | Settings | File Templates.
 */

import java.util.List;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Indexed;
import com.google.code.morphia.utils.IndexDirection;
@Entity(value="infr_bid_operation_category",noClassnameStored=true)
public class BidOperationCategory extends BasicCategory {


	private static final long serialVersionUID = 5868893998228902665L;

    /**
     * 直接绑定的URL地址。
     */
	private String bindUrl;

    /**
     * 绑定的基础分类id。
     */
    private List<String> bindBasicCategoryIds;
    /**
     * 绑定SEO设置信息
     */
    private SEOModel seoModel;

    public String getBindUrl() {
        return bindUrl;
    }

    public void setBindUrl(String bindUrl) {
        this.bindUrl = bindUrl;
    }

    public List<String> getBindBasicCategoryIds() {
        return bindBasicCategoryIds;
    }

    public void setBindBasicCategoryIds(List<String> bindBasicCategoryIds) {
        this.bindBasicCategoryIds = bindBasicCategoryIds;
    }
    
	public SEOModel getSeoModel() {
		return seoModel;
	}

	public void setSeoModel(SEOModel seoModel) {
		this.seoModel = seoModel;
	}
    

}
