// Copyright © 2011-2016 Andy Goryachev <andy@goryachev.com>
package goryachev.crypto;
import goryachev.common.test.TF;
import goryachev.common.test.Test;


public class TestCrypto
{
	public static void main(String[] args)
	{
		TF.run();
	}
	
	
	@Test
	public void testZeroString() throws Exception
	{
		String s = new String("test".toCharArray());
		Crypto.zero(s);
	}
	
	
	@Test(expected = NullPointerException.class)
	public void testZeroStringReuse() throws Exception
	{
		String s = new String("test".toCharArray());
		Crypto.zero(s);
		
		String errorExpected = "" + s;
	}
}
