package cn.yxg.yxgCms.enumeration;

/**
 * 编目项类别玫举。
 * 
 * @author zy
 * @version 1.0.0
 * @since 1.0.0
 */
public enum CatalogItemCategory {
	BASICDATA("基本元数据", 0), BASICINFO("基本信息", 1), PROPERTITLE("题名/主题", 2), DESCRIPTION("描述", 3), COPYRIGHT(
			"版权", 4);

	private String name;

	private int index;

	// 构造方法
	private CatalogItemCategory(String name, int index) {
		this.name = name;
		this.index = index;
	}
	
	/**
	 * Returns the value of the field called 'name'.
	 * @return Returns the name.
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets the field called 'name' to the given value.
	 * @param name The name to set.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns the value of the field called 'index'.
	 * @return Returns the index.
	 */
	public int getIndex() {
		return this.index;
	}

	/**
	 * Sets the field called 'index' to the given value.
	 * @param index The index to set.
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	
}
