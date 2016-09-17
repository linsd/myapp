package com.tasxxz.myapp.enums;

/**
 * 性别枚举
 * @author Administrator
 *
 */
public enum GenderType {

	MEN(1, "男"), 
	WOMEN(2, "女"),
	UNKNOW(3, "未知"),
;

	private int code;
	private String bundlekey;

	private GenderType(int code, String bundlekey) {
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

	public GenderType getGenderTypeByCode(int code) {
		for (GenderType gt : values()) {
			if (gt.getCode().equals(code)) {
				return gt;
			}
		}
		return null;
	}
}
