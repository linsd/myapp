package com.tasxxz.myapp.enums;

/**
 * 性别枚举
 * @author Administrator
 *
 */
public enum SexType {

	MALE(1, "男"), 
	FEMALE(2, "女"),
	UNKNOW(3, "未知"),
;

	private int code;
	private String bundlekey;

	private SexType(int code, String bundlekey) {
		this.code = code;
		this.bundlekey = bundlekey;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getBundlekey() {
		return bundlekey;
	}

	public void setBundlekey(String bundlekey) {
		this.bundlekey = bundlekey;
	}

	public SexType getSexTypeByCode(int code) {
		for (SexType st : values()) {
			if (st.getCode().equals(code)) {
				return st;
			}
		}
		return null;
	}

}
