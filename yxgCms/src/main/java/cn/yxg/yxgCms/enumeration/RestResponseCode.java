package cn.yxg.yxgAppServer.enumeration;

public enum RestResponseCode {
	SUCCESS(200,"成功"),
	
	/**
	 * 已发送邀请
	 */
	INTERNERROR(500,"内部错误"),
	
	/**
	 * 回复同意要求
	 */
	OTHERERROR(550,"其他错误"),
	
	OTHERERROR1(551,"其他错误");
	
	private int value;
	
	private String name;
	
	RestResponseCode(int value,String name){
		this.name = name;
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
