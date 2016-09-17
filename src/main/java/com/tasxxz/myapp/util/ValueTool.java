package com.tasxxz.myapp.util;

import java.math.BigDecimal;

public class ValueTool {
	
	public static BigDecimal getBigDecimal(Object obj) {
		if (obj == null) {
			return null;
		}
		return (new BigDecimal(String.valueOf(obj)));
	}

	public static Long getLong(Object object) {
		return getLong(object, 0l);
	}

	public static Long getLong(Object object, Long defaultValue) {
		if (null == object) {
			return defaultValue;
		} else if (object instanceof Number) {
			return ((Number) object).longValue();
		} else {
			try {
				return Long.parseLong(String.valueOf(object));
			} catch (NumberFormatException e) {
				return defaultValue;
			}
		}
	}

	public static Integer getInteger(Object object) {
		return getInteger(object, 0);
	}

	public static Integer getInteger(Object object, Integer defaultValue) {
		if (null == object) {
			return defaultValue;
		} else if (object instanceof Number) {
			return ((Number) object).intValue();
		} else {
			try {
				return Integer.parseInt(String.valueOf(object));
			} catch (NumberFormatException e) {
				return defaultValue;
			}
		}
	}

	public static Double getDouble(Object object) {
		return getDouble(object, 0.0);
	}

	public static Double getDouble(Object object, Double defaultValue) {
		if (null == object) {
			return defaultValue;
		} else if (object instanceof Number) {
			return ((Number) object).doubleValue();
		} else {
			try {
				return Double.parseDouble(String.valueOf(object));
			} catch (NumberFormatException e) {
				return defaultValue;
			}
		}
	}

	public static String getString(Object object) {
		return getString(object, "");
	}

	public static String getString(Object object, String defaultValue) {
		if (null == object) {
			return defaultValue;
		} else {
			return String.valueOf(object);
		}
	}

	public static void main(String[] args) {
	}
}
