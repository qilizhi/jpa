package com.qlz.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.zip.CRC32;

import org.apache.commons.codec.Charsets;
import org.apache.commons.lang3.Validate;
import com.google.common.hash.Hashing;


/**
 * ��ϢժҪ�Ĺ�����.
 * 
 * ֧��SHA-1/MD5��Щ��ȫ�Խϸߣ�����byte[]��(����Encodes��һ��������ΪHex,
 * Base64��UrlSafeBase64),֧�ִ�salt�ﵽ���ߵİ�ȫ��.
 * 
 * Ҳ֧��crc32��murmur32��Щ��׷��ȫ�ԣ����ܽϸߣ�����int��.
 * 
 * @author calvin
 */
public class Digests {

	private static final String SHA1 = "SHA-1";

	private static final String MD5 = "MD5";

	private static SecureRandom random = new SecureRandom();

	/**
	 * �������ַ�������sha1ɢ��.
	 */
	public static byte[] sha1( byte[] input ) {
		return digest( input, SHA1, null, 1 );
	}

	/**
	 * �������ַ�������sha1ɢ��.
	 */
	public static byte[] sha1( String input ) {
		return digest( input.getBytes( Charsets.UTF_8 ), SHA1, null, 1 );
	}

	/**
	 * �������ַ�������sha1ɢ��.
	 */
	public static byte[] sha1( String input, Charset charset ) {
		return digest( input.getBytes( charset ), SHA1, null, 1 );
	}

	/**
	 * �������ַ�������sha1ɢ�У���salt�ﵽ���ߵİ�ȫ��.
	 */
	public static byte[] sha1( byte[] input, byte[] salt ) {
		return digest( input, SHA1, salt, 1 );
	}

	/**
	 * �������ַ�������sha1ɢ�У���salt�ﵽ���ߵİ�ȫ��.
	 */
	public static byte[] sha1( String input, byte[] salt ) {
		return digest( input.getBytes( Charsets.UTF_8 ), SHA1, salt, 1 );
	}

	/**
	 * �������ַ�������sha1ɢ�У���salt�ﵽ���ߵİ�ȫ��.
	 */
	public static byte[] sha1( String input, Charset charset, byte[] salt ) {
		return digest( input.getBytes( charset ), SHA1, salt, 1 );
	}

	/**
	 * �������ַ�������sha1ɢ�У���salt���ҵ����ﵽ���߸��ߵİ�ȫ��.
	 */
	public static byte[] sha1( byte[] input, byte[] salt, int iterations ) {
		return digest( input, SHA1, salt, iterations );
	}

	/**
	 * �������ַ�������sha1ɢ�У���salt���ҵ����ﵽ���߸��ߵİ�ȫ��.
	 */
	public static byte[] sha1( String input, byte[] salt, int iterations ) {
		return digest( input.getBytes( Charsets.UTF_8 ), SHA1, salt, iterations );
	}

	/**
	 * �������ַ�������sha1ɢ�У���salt���ҵ����ﵽ���߸��ߵİ�ȫ��.
	 */
	public static byte[] sha1( String input, Charset charset, byte[] salt, int iterations ) {
		return digest( input.getBytes( charset ), SHA1, salt, iterations );
	}

	/**
	 * ���ַ�������ɢ��, ֧��md5��sha1�㷨.
	 */
	private static byte[] digest( byte[] input, String algorithm, byte[] salt, int iterations ) {
		try {
			MessageDigest digest = MessageDigest.getInstance( algorithm );

			if( salt != null ) {
				digest.update( salt );
			}

			byte[] result = digest.digest( input );

			for( int i = 1; i < iterations; i++ ) {
				digest.reset();
				result = digest.digest( result );
			}
			return result;
		}
		catch( GeneralSecurityException e ) {
			throw Exceptions.unchecked( e );
		}
	}

	/**
	 * ���������Byte[]��Ϊsalt.
	 * 
	 * @param numBytes salt����Ĵ�С
	 */
	public static byte[] generateSalt( int numBytes ) {
		Validate.isTrue( numBytes > 0, "numBytes argument must be a positive integer (1 or larger)", numBytes );

		byte[] bytes = new byte[numBytes];
		random.nextBytes( bytes );
		return bytes;
	}

	/**
	 * ���ļ�����md5ɢ��.
	 */
	public static byte[] md5( InputStream input ) throws IOException {
		return digest( input, MD5 );
	}

	/**
	 * ���ļ�����sha1ɢ��.
	 */
	public static byte[] sha1( InputStream input ) throws IOException {
		return digest( input, SHA1 );
	}

	private static byte[] digest( InputStream input, String algorithm ) throws IOException {
		try {
			MessageDigest messageDigest = MessageDigest.getInstance( algorithm );
			int bufferLength = 8 * 1024;
			byte[] buffer = new byte[bufferLength];
			int read = input.read( buffer, 0, bufferLength );

			while( read > -1 ) {
				messageDigest.update( buffer, 0, read );
				read = input.read( buffer, 0, bufferLength );
			}

			return messageDigest.digest();
		}
		catch( GeneralSecurityException e ) {
			throw Exceptions.unchecked( e );
		}
	}

	/**
	 * �������ַ�������crc32ɢ��.
	 */
	public static int crc32( byte[] input ) {
		CRC32 crc32 = new CRC32();
		crc32.update( input );
		return (int)crc32.getValue();
	}

	/**
	 * �������ַ�������crc32ɢ��.
	 */
	public static int crc32( String input ) {
		CRC32 crc32 = new CRC32();
		crc32.update( input.getBytes( Charsets.UTF_8 ) );
		return (int)crc32.getValue();
	}

	/**
	 * �������ַ�������crc32ɢ��.
	 */
	public static int crc32( String input, Charset charset ) {
		CRC32 crc32 = new CRC32();
		crc32.update( input.getBytes( charset ) );
		return (int)crc32.getValue();
	}

	/**
	 * �������ַ�������crc32ɢ�У���php���ݣ���64bitϵͳ�·�����Զ��������long
	 */
	public static long crc32AsLong( byte[] input ) {
		CRC32 crc32 = new CRC32();
		crc32.update( input );
		return crc32.getValue();
	}

	/**
	 * �������ַ�������crc32ɢ�У���php���ݣ���64bitϵͳ�·�����Զ��������long
	 */
	public static long crc32AsLong( String input ) {
		CRC32 crc32 = new CRC32();
		crc32.update( input.getBytes( Charsets.UTF_8 ) );
		return crc32.getValue();
	}

	/**
	 * �������ַ�������crc32ɢ�У���php���ݣ���64bitϵͳ�·�����Զ��������long
	 */
	public static long crc32AsLong( String input, Charset charset ) {
		CRC32 crc32 = new CRC32();
		crc32.update( input.getBytes( charset ) );
		return crc32.getValue();
	}

	/**
	 * �������ַ�������murmur32ɢ��
	 */
	public static int murmur32( byte[] input ) {
		return Hashing.murmur3_32().hashBytes( input ).asInt();
	}

	/**
	 * �������ַ�������murmur32ɢ��
	 */
	public static int murmur32( String input ) {
		return Hashing.murmur3_32().hashString( input, Charsets.UTF_8 ).asInt();
	}

	/**
	 * �������ַ�������murmur32ɢ��
	 */
	public static int murmur32( String input, Charset charset ) {
		return Hashing.murmur3_32().hashString( input, charset ).asInt();
	}

	/**
	 * �������ַ�������murmur32ɢ�У�����seed
	 */
	public static int murmur32( byte[] input, int seed ) {
		return Hashing.murmur3_32( seed ).hashBytes( input ).asInt();
	}

	/**
	 * �������ַ�������murmur32ɢ�У�����seed
	 */
	public static int murmur32( String input, int seed ) {
		return Hashing.murmur3_32( seed ).hashString( input, Charsets.UTF_8 ).asInt();
	}

	/**
	 * �������ַ�������murmur32ɢ�У�����seed
	 */
	public static int murmur32( String input, Charset charset, int seed ) {
		return Hashing.murmur3_32( seed ).hashString( input, charset ).asInt();
	}
}
