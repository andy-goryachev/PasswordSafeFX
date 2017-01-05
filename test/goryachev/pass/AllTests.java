// Copyright © 2012-2017 Andy Goryachev <andy@goryachev.com>
package goryachev.pass;
import goryachev.common.test.TF;
import goryachev.crypto.TestCrypto;


public class AllTests
{
	public static void main(String[] args)
	{
		TF.run
		(
			TestDataFormatV2.class,
			TestCrypto.class
		);
	}
}
