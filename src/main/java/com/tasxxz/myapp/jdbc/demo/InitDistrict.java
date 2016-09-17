package com.tasxxz.myapp.jdbc.demo;

import com.alibaba.fastjson.JSONObject;
import com.tasxxz.myapp.util.JdbcUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import java.util.Map.Entry;


public class InitDistrict {
	public static void main(String[] args) {
		long start = System.currentTimeMillis();
		district();
		System.out.println("time:"+(System.currentTimeMillis() - start));
//		URL url = InitDistrict.class.getClassLoader().getResource("my/demo/chinese_district.2.txt");
//		url = InitDistrict.class.getResource("/my/demo/chinese_district.2.txt");
//		url = InitDistrict.class.getResource("/my/test/TestMysqlConnection.java");
//		System.out.println(url);
	}
	
	public static void doit() {
		Connection conn = JdbcUtil.getConnection();
		try {
			PreparedStatement ps = conn.prepareStatement("select * from tb_province_city_code t where t.code = ?");
			ps.setString(1, "340000");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				System.out.println(rs.getString("name"));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JdbcUtil.close(conn, stmt, rs);
		}
	}
	
	public static void district() {
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(InitDistrict.class.getResourceAsStream("chinese_district.2.txt"),Charset.forName("utf-8")));
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ( (line = reader.readLine()) != null) {
				sb.append(line);
			}
			JSONObject json = JSONObject.parseObject(sb.toString());
			Set<Entry<String, Object>> set = json.entrySet();
			List<Integer> list = new ArrayList<Integer>();
			
			List<Map<String,String>> data = new ArrayList<Map<String,String>>();
			getData(json,"1",data);
			
			insert(data);
			System.out.println(data.size());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void getData(JSONObject json, String key, List<Map<String,String>> list) {
		if (null == json) {
			return ;
		}
		JSONObject sub = json.getJSONObject(key);
		if (null == sub) {
			return ;
		}
		Set<String> set = sub.keySet();
		String[] keys = new String[set.size()];
		keys = set.toArray(keys);
		Arrays.sort(keys);
		for (String k : keys) {
			Map<String,String> map = new HashMap<>();
			map.put("code", k);
			map.put("name", sub.getString(k));
			map.put("parentCode", "1".equals(key)?null:key);
			list.add(map);
			
			getData(json, k, list);
		}
	}
	
	public static void insert(List<Map<String,String>> list) {
		Connection conn = JdbcUtil.getConnection();
		try {
			conn.setAutoCommit(false);
			PreparedStatement ps = conn.prepareStatement("INSERT INTO tb_chinese_district(code,name,parentCode) VALUES(?,?,?)");
			final int batchSize = 1000;
			int count = 0;
			for (Map<String, String> map : list) {
				System.out.println(map.get("name"));
				ps.setString(1, map.get("code"));
				ps.setString(2, map.get("name"));
				ps.setString(3, map.get("parentCode"));
				ps.addBatch();
				
				if (++count % batchSize == 0) {
					ps.executeBatch();
					conn.commit();
				}
			}
			int[] arr = ps.executeBatch();
			conn.commit();
			ps.close();
			conn.close();;
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			//JdbcUtil.close(conn, stmt, rs);
		}
	}
}
