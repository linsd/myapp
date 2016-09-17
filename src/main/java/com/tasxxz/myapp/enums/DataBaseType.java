package com.tasxxz.myapp.enums;

/**
 * 数据库连接类型
 * @author Administrator
 *
 */
public enum DataBaseType {
    
    MYSQL("com.mysql.jdbc.Driver","jdbc:mysql://127.0.0.1:3306/test?characterEncoding=utf8","root","linsd"),
    ORACLE("oracle.jdbc.driver.OracleDriver","jdbc:oracle:thin:@127.0.0.1:1521:orcl","yqoa","yqoa"),
    
;
    
    private String driver;
    private String url;
    private String user;
    private String password;
    
	private DataBaseType(String driver, String url, String user, String password) {
		this.driver = driver;
		this.url = url;
		this.user = user;
		this.password = password;
	}

	public String getDriver() {
		return driver;
	}

	public String getUrl() {
		return url;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public DataBaseType getDataBaseTypeByDriver(String driver) {
		for (DataBaseType dbt : values()) {
			if (dbt.getDriver().equals(driver)) {
				return dbt;
			}
		}
		return null;
	}

}
