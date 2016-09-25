package com.tasxxz.myapp.util;

import com.tasxxz.myapp.encrypt.MD5Utils;
import org.junit.Assert;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MD5UtilsTest {


	@Test
	public void testMD5(){

		String input = "ksdif453士大夫";
		System.out.println(MD5Utils.encode(input));
		System.out.println(MD5Utils.encode(input.getBytes()));
	}

	@Test
	public void testByteToHex() {
		byte[] bytes = MD5Utils.digest("12w");
		Assert.assertTrue(MD5Utils.byteArrayToHexString(bytes).equals(MD5Utils.byteArrayToHexString2(bytes)));
	}

	@Test
	public void testValidFile() {
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("D:/temp/HTTPClient-0.3-3.pom");
			byte[] b = new byte[fis.available()];
			fis.read(b);
			System.out.println(MD5Utils.encode(b));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
