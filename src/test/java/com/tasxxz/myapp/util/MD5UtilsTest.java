package com.tasxxz.myapp.util;

import org.junit.Assert;
import org.junit.Test;

public class MD5UtilsTest {


	@Test
	public void testMD5(){

		String input = "ksdif453士大夫";
		System.out.println(MD5Utils.encode(input));
	}

	@Test
	public void testByteToHex() {
		byte[] bytes = MD5Utils.digest("12");
		Assert.assertTrue(MD5Utils.byteArrayToHexString(bytes).equals(MD5Utils.byteArrayToHexString2(bytes)));
	}
}