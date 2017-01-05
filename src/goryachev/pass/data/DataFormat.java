// Copyright © 2013-2017 Andy Goryachev <andy@goryachev.com>
package goryachev.pass.data;
import goryachev.crypto.OpaqueChars;


public interface DataFormat
{
	public byte[] save(DataFile f) throws Exception;
	
	public DataFile load(byte[] encrypted, OpaqueChars passphrase) throws Exception;
}
