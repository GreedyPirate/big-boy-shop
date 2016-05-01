package cn.sina.core.query.product;


import java.util.ArrayList;
import java.util.List;

import cn.sina.core.query.BaseQuery;


public class BrandQuery extends BaseQuery {
	/**
	 * ==============================批量查询、更新、删除时的Where条件设置======================
	 * ============
	 **/
	private Integer id;
	public Integer getId() {
		return id;
	}
	public BrandQuery setId(Integer id) {
		this.id = id;
		return this;
	}
	private String name;
	public String getName() {
		return name;
	}
	public BrandQuery setName(String name) {
		this.name = name;
		return this;
	}
	private boolean nameLike;
	public BrandQuery setNameLike(boolean isLike) {
		this.nameLike = isLike;
		return this;
	}
	private String description;
	public String getDescription() {
		return description;
	}
	public BrandQuery setDescription(String description) {
		this.description = description;
		return this;
	}
	private boolean descriptionLike;
	public BrandQuery setDescriptionLike(boolean isLike) {
		this.descriptionLike = isLike;
		return this;
	}
	private String imgUrl;
	public String getImgUrl() {
		return imgUrl;
	}
	public BrandQuery setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
		return this;
	}
	private boolean imgUrlLike;
	public BrandQuery setImgUrlLike(boolean isLike) {
		this.imgUrlLike = isLike;
		return this;
	}
	private String webSite;
	public String getWebSite() {
		return webSite;
	}
	public BrandQuery setWebSite(String webSite) {
		this.webSite = webSite;
		return this;
	}
	private boolean webSiteLike;
	public BrandQuery setWebSiteLike(boolean isLike) {
		this.webSiteLike = isLike;
		return this;
	}
	private Integer sort;
	public Integer getSort() {
		return sort;
	}
	public BrandQuery setSort(Integer sort) {
		this.sort = sort;
		return this;
	}
	private Integer isDisplay;
	public Integer getIsDisplay() {
		return isDisplay;
	}
	public BrandQuery setIsDisplay(Integer isDisplay) {
		this.isDisplay = isDisplay;
		return this;
	}
	/**
	 * ==============================批量查询时的Order条件顺序设置==========================
	 * ========
	 **/
	 	public class OrderField {
		public OrderField(String fieldName, String order) {
			super();
			this.fieldName = fieldName;
			this.order = order;
		}
		private String fieldName;
		private String order;

		public String getFieldName() {
			return fieldName;
		}
		public OrderField setFieldName(String fieldName) {
			this.fieldName = fieldName;
			return this;
		}
		public String getOrder() {
			return order;
		}
		public OrderField setOrder(String order) {
			this.order = order;
			return this;
		}
	}
		/**
	 * ==============================批量查询时的Order条件顺序设置==========================
	 * ========
	 **/
	/** 排序列表字段 **/
	private List<OrderField> orderFields = new ArrayList<OrderField>();
	/**
	 * 设置排序按属性：id
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public BrandQuery orderbyId(boolean isAsc) {
		orderFields.add(new OrderField("id", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：name
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public BrandQuery orderbyName(boolean isAsc) {
		orderFields.add(new OrderField("name", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：description
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public BrandQuery orderbyDescription(boolean isAsc) {
		orderFields.add(new OrderField("description", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：img_url
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public BrandQuery orderbyImgUrl(boolean isAsc) {
		orderFields.add(new OrderField("img_url", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：web_site
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public BrandQuery orderbyWebSite(boolean isAsc) {
		orderFields.add(new OrderField("web_site", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：sort
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public BrandQuery orderbySort(boolean isAsc) {
		orderFields.add(new OrderField("sort", isAsc ? "ASC" : "DESC"));
		return this;
	}
	/**
	 * 设置排序按属性：is_display
	 * 
	 * @param isAsc
	 *            是否升序，否则为降序
	 */
	public BrandQuery orderbyIsDisplay(boolean isAsc) {
		orderFields.add(new OrderField("is_display", isAsc ? "ASC" : "DESC"));
		return this;
	}

}
