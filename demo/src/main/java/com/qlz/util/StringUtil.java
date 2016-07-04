package com.qlz.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

/**
 * �ַ�����������
 * 
 * @author quan
 * 
 */
public class StringUtil {

	/**
	 * �����ȷָ��ַ���
	 * 
	 * @param content
	 * @param len
	 * @return
	 */
	public static String[] split(String content, int len) {
		if (content == null || content.equals("")) {
			return null;
		}
		int len2 = content.length();
		if (len2 <= len) {
			return new String[] { content };
		} else {
			int i = len2 / len + 1;
			// System.out.println( "i:" + i );
			String[] strA = new String[i];
			int j = 0;
			int begin = 0;
			int end = 0;
			while (j < i) {
				begin = j * len;
				end = (j + 1) * len;
				if (end > len2)
					end = len2;
				strA[j] = content.substring(begin, end);
				// System.out.println(strA[j]+"<br/>");
				j = j + 1;
			}
			return strA;
		}
	}

	public static boolean emailFormat(String email) {
		boolean tag = true;
		final String pattern1 = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		final Pattern pattern = Pattern.compile(pattern1);
		final Matcher mat = pattern.matcher(email);
		if (!mat.find()) {
			tag = false;
		}
		return tag;
	}

	/**
	 * ��֤�ǲ���EMAIL
	 * 
	 * @param email
	 * @return
	 */
	public static boolean isEmail(String email) {
		boolean retval = false;
		if (StringUtil.empty(email))
			return retval;
		String check = "^([a-z0-9A-Z]+[-|_|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		Pattern regex = Pattern.compile(check);
		Matcher matcher = regex.matcher(email);
		retval = matcher.matches();
		return retval;
	}

	/**
	 * ��֤����Ϊtrue
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isLetterorDigit(String s) {
		if (s.equals("") || s == null) {
			return false;
		}
		for (int i = 0; i < s.length(); i++) {
			if (!Character.isLetterOrDigit(s.charAt(i))) {
				// if (!Character.isLetter(s.charAt(i))){
				return false;
			}
		}
		// Character.isJavaLetter()
		return true;
	}

	/**
	 * �ж�ĳ�ַ����Ƿ�Ϊnull��������ȴ���256���򷵻�256���ȵ����ַ�������֮����ԭ��
	 * 
	 * @param str
	 * @return
	 */
	public static String checkStr(String str) {
		if (str == null) {
			return "";
		} else if (str.length() > 256) {
			return str.substring(256);
		} else {
			return str;
		}
	}

	/**
	 * ��֤�ǲ���Int validate a int string
	 * 
	 * @param str
	 *            the Integer string.
	 * @return true if the str is invalid otherwise false.
	 */
	public static boolean validateInt(String str) {
		if (str == null || str.trim().equals(""))
			return false;

		char c;
		for (int i = 0, l = str.length(); i < l; i++) {
			c = str.charAt(i);
			if (!((c >= '0') && (c <= '9')))
				return false;
		}

		return true;
	}

	/**
	 * �ֽ���ת����16�����ַ��� �ڲ�����ʹ��
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2hex(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			if (n < b.length - 1)
				hs = hs + ":";
		}
		return hs.toUpperCase();
	}

	/**
	 * �ֽ���ת�����Զ����ַ��� �ڲ�����ʹ��
	 * 
	 * @param b
	 * @return
	 */
	public static String byte2string(byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
			if (stmp.length() == 1)
				hs = hs + "0" + stmp;
			else
				hs = hs + stmp;
			// if (n<b.length-1) hs=hs+":";
		}
		return hs.toUpperCase();
	}

	public static byte[] string2byte(String hs) {
		byte[] b = new byte[hs.length() / 2];
		for (int i = 0; i < hs.length(); i = i + 2) {
			String sub = hs.substring(i, i + 2);
			byte bb = new Integer(Integer.parseInt(sub, 16)).byteValue();
			b[i / 2] = bb;
		}
		return b;
	}

	/**
	 * ��֤�ַ����Ƿ�Ϊ��
	 * 
	 * @param param
	 * @return
	 */
	public static boolean empty(String param) {
		return param == null || param.trim().length() < 1;
	}

	/**
	 * ��֤�ַ����Ƿ�ǿ�
	 * 
	 * @param param
	 * @return
	 */
	public static boolean isNotEmpty(String param) {
		return !empty(param);
	}

	// 65~90 97~122
	/**
	 * �ж��ַ��Ƿ�Ϊ��ĸ
	 * 
	 * @param c
	 * @return
	 */
	public static boolean isLetter(char c) {
		if (c >= 65 && c <= 90) {
			return true;
		}
		if (c >= 97 && c <= 122) {
			return true;
		}
		return false;
	}

	// ��֤Ӣ����ĸ������
	public static boolean isLetterOrDigit(String str) {
		java.util.regex.Pattern p = null; // ������ʽ
		java.util.regex.Matcher m = null; // �������ַ���
		boolean value = true;
		try {
			p = java.util.regex.Pattern.compile("[^0-9A-Za-z]");
			m = p.matcher(str);
			if (m.find()) {

				value = false;
			}
		} catch (Exception e) {
		}
		return value;
	}

	/**
	 * ��֤�Ƿ���Сд�ַ���
	 */
	@SuppressWarnings("unused")
	private static boolean isLowerLetter(String str) {
		java.util.regex.Pattern p = null; // ������ʽ
		java.util.regex.Matcher m = null; // �������ַ���
		boolean value = true;
		try {
			p = java.util.regex.Pattern.compile("[^a-z]");
			m = p.matcher(str);
			if (m.find()) {
				value = false;
			}
		} catch (Exception e) {
		}
		return value;
	}

	/**
	 * �ж�һ���ַ����Ƿ�Ϊ����
	 * 
	 * @param strNum
	 * @return
	 */
	public static boolean isDigit(String strNum) {
		return strNum.matches("[0-9]{1,}");
	}

	/**
	 * �ж�һ���ַ����Ƿ�Ϊ����
	 * 
	 * @param strNum
	 * @return
	 */
	public static boolean isDigit2(String strNum) {
		Pattern pattern = Pattern.compile("[0-9]{1,}");
		Matcher matcher = pattern.matcher(strNum);
		return matcher.matches();
	}

	/**
	 * �ж�һ���ַ����Ƿ�Ϊ����(����С����)
	 * 
	 * @param strNum
	 * @return
	 */
	public static boolean isDigit3(String strNum) {
		Pattern pattern = Pattern.compile("^([1-9][0-9]*(\\.[0-9]{1,2})?)|(0\\.[0-9]{1,2})?");
		Matcher matcher = pattern.matcher(strNum);
		return matcher.matches();
	}

	/**
	 * ��ȡ����
	 * 
	 * @param content
	 * @return
	 */
	public static String getNumbers(String content) {
		Pattern pattern = Pattern.compile("\\d+");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			return matcher.group(0);
		}
		return "";
	}

	/**
	 * ��ȡ������
	 * 
	 * @param content
	 * @return
	 */
	public static String splitNotNumber(String content) {
		Pattern pattern = Pattern.compile("\\D+");
		Matcher matcher = pattern.matcher(content);
		while (matcher.find()) {
			return matcher.group(0);
		}
		return "";
	}

	public static String encode(String str, String code) {
		try {
			return URLEncoder.encode(str, code);
		} catch (Exception ex) {
			ex.fillInStackTrace();
			return "";
		}
	}

	public static String decode(String str, String code) {
		try {
			return URLDecoder.decode(str, code);
		} catch (Exception ex) {
			ex.fillInStackTrace();
			return "";
		}
	}

	/**
	 * ȥ���ַ������߿ո� Ϊ�շ�����ո�
	 * 
	 * @param param
	 * @return
	 */
	public static String nvl(String param) {
		return param != null ? param.trim() : "";
	}

	/**
	 * ��String���͵�����ת����int���� Ϊ�ջ���ַ�������ʱ��������Ĭ��ֵd
	 * 
	 * @param param
	 * @param d
	 * @return
	 */
	public static int parseInt(String param, int d) {
		int i = d;
		try {
			i = Integer.parseInt(param);
		} catch (Exception e) {
		}
		return i;
	}

	/**
	 * �ַ�����������ת�� Ϊ�ջ������ ����Ĭ��ֵ0
	 * 
	 * @param param
	 * @return
	 */
	public static int parseInt(String param) {
		return parseInt(param, 0);
	}

	/**
	 * �ַ�����������ת��
	 * 
	 * @param param
	 * @return
	 */
	public static int parseInteger(String param, int d) {
		return parseInt(param, d);
	}

	/**
	 * �ַ���long��������ת�� ����������ʱ����0L
	 * 
	 * @param param
	 * @return
	 */
	public static long parseLong(String param) {
		long l = 0L;
		try {
			l = Long.parseLong(param);
		} catch (Exception e) {
		}
		return l;
	}

	/**
	 * �ַ���double��������ת�� ����������ʱ����1.0
	 * 
	 * @param param
	 * @return
	 */
	public static double parseDouble(String param) {
		return parseDouble(param, 1.0);
	}

	/**
	 * �ַ���double��������ת�� ����������ʱ��������Ĭ��ֵ
	 * 
	 * @param param
	 * @param t
	 * @return
	 */
	public static double parseDouble(String param, double t) {
		double tmp = 0.0;
		try {
			tmp = Double.parseDouble(param.trim());
		} catch (Exception e) {
			tmp = t;
		}
		return tmp;
	}

	/**
	 * 
	 * @param param
	 * @return
	 */
	public static boolean parseBoolean(String param) {
		if (empty(param))
			return false;
		switch (param.charAt(0)) {
		case 49: // '1'
		case 84: // 'T'
		case 89: // 'Y'
		case 116: // 't'
		case 121: // 'y'
			return true;

		}
		return false;
	}

	/**
	 * public static String replace(String mainString, String oldString, String
	 * newString) { if(mainString == null) return null; int i =
	 * mainString.lastIndexOf(oldString); if(i < 0) return mainString;
	 * StringBuffer mainSb = new StringBuffer(mainString); for(; i >= 0; i =
	 * mainString.lastIndexOf(oldString, i - 1)) mainSb.replace(i, i +
	 * oldString.length(), newString);
	 * 
	 * return mainSb.toString(); }
	 * 
	 */

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static final String[] split(String str, String delims) {
		StringTokenizer st = new StringTokenizer(str, delims);
		ArrayList list = new ArrayList();
		for (; st.hasMoreTokens(); list.add(st.nextToken()))
			;
		return (String[]) list.toArray(new String[list.size()]);
	}

	public static String substring(String str, int length) {
		if (str == null)
			return null;

		if (str.length() > length)
			return (str.substring(0, length - 2) + "...");
		else
			return str;
	}

	public static boolean validateDouble(String str) throws RuntimeException {
		if (str == null)
			// throw new RuntimeException("null input");
			return false;
		char c;
		int k = 0;
		for (int i = 0, l = str.length(); i < l; i++) {
			c = str.charAt(i);
			if (!((c >= '0') && (c <= '9')))
				if (!(i == 0 && (c == '-' || c == '+')))
					if (!(c == '.' && i < l - 1 && k < 1))
						// throw new RuntimeException("invalid number");
						return false;
					else
						k++;
		}
		return true;
	}

	public static boolean validateMobile(String str, boolean includeUnicom) {
		if (str == null || str.trim().equals(""))
			return false;
		str = str.trim();

		if (str.length() != 11 || !validateInt(str))
			return false;

		if (includeUnicom && (str.startsWith("130") || str.startsWith("133") || str.startsWith("131")))
			return true;

		if (!(str.startsWith("139") || str.startsWith("138") || str.startsWith("137") || str.startsWith("136")
				|| str.startsWith("135")))
			return false;
		return true;
	}

	public static boolean validateMobile(String str) {
		return validateMobile(str, false);
	}

	/**
	 * �ж��Ƿ�Ϊ�ֻ�����
	 * 
	 * @param mdn
	 *            �ֻ�����
	 * @return
	 */
	public static boolean isMobileNumber(String mdn) {
		if (StringUtil.empty(mdn))
			return false;
		Pattern p = Pattern.compile(
				"^((13[0,1,2,3,4,5,6,7,8,9])|(145,147)|(15[0,1,2,3,4,5,6,7,8,9])|(18[0,1,2,3,4,5,6,7,8,9]))\\d{8}$");
		Matcher m = p.matcher(mdn);
		return m.matches();
	}

	/**
	 * delete file
	 * 
	 * @param fileName
	 * @return -1 exception,1 success,0 false,2 there is no one directory of the
	 *         same name in system
	 */
	public static int deleteFile(String fileName) {
		File file = null;
		int returnValue = -1;
		try {
			file = new File(fileName);
			if (file.exists())
				if (file.delete())
					returnValue = 1;
				else
					returnValue = 0;
			else
				returnValue = 2;

		} catch (Exception e) {
			e.printStackTrace();
			// System.out.println( "Exception:e=" + e.getMessage() );
		} finally {
			file = null;
			// return returnValue;
		}
		return returnValue;
	}

	public static String gbToIso(String s) throws UnsupportedEncodingException {
		return covertCode(s, "GB2312", "ISO8859-1");
	}

	public static String covertCode(String s, String code1, String code2) throws UnsupportedEncodingException {
		if (s == null)
			return null;
		else if (s.trim().equals(""))
			return "";
		else
			return new String(s.getBytes(code1), code2);
	}

	public static String transCode(String s0) throws UnsupportedEncodingException {
		if (s0 == null || s0.trim().equals(""))
			return null;
		else {
			s0 = s0.trim();
			return new String(s0.getBytes("GBK"), "ISO8859-1");
		}
	}

	/**
	 * ISO8859-1����ת��UTF-8�����ַ���
	 * 
	 * @param value
	 * @return
	 */
	public static String isoToUTF8(String value) {
		try {
			return covertCode(value, "ISO8859-1", "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return value;
	}

	/**
	 * ����ת��
	 * 
	 * @param s
	 * @return
	 */
	public static String GBToUTF8(String s) {
		try {
			StringBuffer out = new StringBuffer("");
			byte[] bytes = s.getBytes("unicode");
			for (int i = 2; i < bytes.length - 1; i += 2) {
				out.append("\\u");
				String str = Integer.toHexString(bytes[i + 1] & 0xff);
				for (int j = str.length(); j < 2; j++) {
					out.append("0");
				}
				out.append(str);
				String str1 = Integer.toHexString(bytes[i] & 0xff);
				for (int j = str1.length(); j < 2; j++) {
					out.append("0");
				}

				out.append(str1);
			}
			return out.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * �ַ����������ַ����滻
	 * 
	 * @param obj
	 * @param oldString
	 * @param newString
	 * @return
	 */
	@SuppressWarnings("unused")
	public static final String[] replaceAll(String[] obj, String oldString, String newString) {
		if (obj == null) {
			return null;
		}
		int length = obj.length;
		String[] returnStr = new String[length];
		String str = null;
		for (int i = 0; i < length; i++) {
			returnStr[i] = replaceAll(obj[i], oldString, newString);
		}
		return returnStr;
	}

	/**
	 * �ַ���ȫ���滻
	 * 
	 * @param s0
	 * @param oldstr
	 * @param newstr
	 * @return
	 */
	public static String replaceAll(String s0, String oldstr, String newstr) {
		if (s0 == null || s0.trim().equals(""))
			return null;
		StringBuffer sb = new StringBuffer(s0);
		int begin = 0;
		// int from = 0;
		begin = s0.indexOf(oldstr);
		while (begin > -1) {
			sb = sb.replace(begin, begin + oldstr.length(), newstr);
			s0 = sb.toString();
			begin = s0.indexOf(oldstr, begin + newstr.length());
		}
		return s0;
	}

	/**
	 * �ַ���ת����html����
	 * 
	 * @param str
	 * @return
	 */
	public static String toHtml(String str) {
		String html = str;
		if (str == null || str.length() == 0) {
			return str;
		}
		html = replaceAll(html, "&", "&amp;");
		html = replaceAll(html, "<", "&lt;");
		html = replaceAll(html, ">", "&gt;");
		html = replaceAll(html, "\r\n", "\n");
		html = replaceAll(html, "\n", "<br>\n");
		html = replaceAll(html, "\t", "         ");
		html = replaceAll(html, " ", "&nbsp;");
		return html;
	}

	/**
	 * �ַ����滻
	 * 
	 * @param line
	 * @param oldString
	 * @param newString
	 * @return
	 */
	public static final String replace(String line, String oldString, String newString) {
		if (line == null) {
			return null;
		}
		int i = 0;
		if ((i = line.indexOf(oldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = line.indexOf(oldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	public static final String replaceIgnoreCase(String line, String oldString, String newString) {
		if (line == null) {
			return null;
		}
		String lcLine = line.toLowerCase();
		String lcOldString = oldString.toLowerCase();
		int i = 0;
		if ((i = lcLine.indexOf(lcOldString, i)) >= 0) {
			char[] line2 = line.toCharArray();
			char[] newString2 = newString.toCharArray();
			int oLength = oldString.length();
			StringBuffer buf = new StringBuffer(line2.length);
			buf.append(line2, 0, i).append(newString2);
			i += oLength;
			int j = i;
			while ((i = lcLine.indexOf(lcOldString, i)) > 0) {
				buf.append(line2, j, i - j).append(newString2);
				i += oLength;
				j = i;
			}
			buf.append(line2, j, line2.length - j);
			return buf.toString();
		}
		return line;
	}

	/**
	 * ��ǩת��
	 * 
	 * @param input
	 * @return
	 */
	public static final String escapeHTMLTags(String input) {
		// Check if the string is null or zero length -- if so, return
		// what was sent in.
		if (input == null || input.length() == 0) {
			return input;
		}
		// Use a StringBuffer in lieu of String concatenation -- it is
		// much more efficient this way.
		StringBuffer buf = new StringBuffer(input.length());
		char ch = ' ';
		for (int i = 0; i < input.length(); i++) {
			ch = input.charAt(i);
			if (ch == '<') {
				buf.append("&lt;");
			} else if (ch == '>') {
				buf.append("&gt;");
			} else {
				buf.append(ch);
			}
		}
		return buf.toString();
	}

	/**
	 * Returns a random String of numbers and letters of the specified length.
	 * The method uses the Random class that is built-in to Java which is
	 * suitable for low to medium grade security uses. This means that the
	 * output is only pseudo random, i.e., each number is mathematically
	 * generated so is not truly random.
	 * <p>
	 * 
	 * For every character in the returned String, there is an equal chance that
	 * it will be a letter or number. If a letter, there is an equal chance that
	 * it will be lower or upper case.
	 * <p>
	 * 
	 * The specified length must be at least one. If not, the method will return
	 * null.
	 * 
	 * @param length
	 *            the desired length of the random String to return.
	 * @return a random String of numbers and letters of the specified length.
	 */

	private static Random randGen = null;

	private static Object initLock = new Object();

	private static char[] numbersAndLetters = null;

	public static final String randomString(int length) {
		if (length < 1) {
			return null;
		}
		// Init of pseudo random number generator.
		if (randGen == null) {
			synchronized (initLock) {
				if (randGen == null) {
					randGen = new Random();
					// Also initialize the numbersAndLetters array
					numbersAndLetters = ("0123456789abcdefghijklmnopqrstuvwxyz"
							+ "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ").toCharArray();
				}
			}
		}
		// Create a char buffer to put random letters and numbers in.
		char[] randBuffer = new char[length];
		for (int i = 0; i < randBuffer.length; i++) {
			randBuffer[i] = numbersAndLetters[randGen.nextInt(71)];
		}
		return new String(randBuffer);
	}

	/**
	 * �̶������ַ�����ȡ
	 * 
	 * @param content
	 * @param i
	 * @return
	 */
	public static String getSubstring(String content, int i) {
		int varsize = 10;
		String strContent = content;
		if (strContent.length() < varsize + 1) {
			return strContent;
		} else {
			int max = (int) Math.ceil((double) strContent.length() / varsize);
			if (i < max - 1) {
				return strContent.substring(i * varsize, (i + 1) * varsize);
			} else {
				return strContent.substring(i * varsize);
			}
		}
	}

	/**
	 * ����תString
	 * 
	 * @param pattern
	 * @return
	 */
	public static String now(String pattern) {
		return dateToString(new Date(), pattern);
	}

	public static String dateToString(Date date, String pattern) {
		if (date == null) {
			return "";
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
	}

	public static synchronized String getNow() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		return sdf.format(new Date());
	}

	/**
	 * �����ַ���ת��������
	 * 
	 * @param strDate
	 * @param pattern
	 * @return
	 */
	public static Date getDate(String strDate, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = null;
		try {
			date = simpleDateFormat.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * StringתDate
	 * 
	 * @param strDate
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static java.sql.Date stringToDate(String strDate, String pattern) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		Date date = simpleDateFormat.parse(strDate);
		long lngTime = date.getTime();
		return new java.sql.Date(lngTime);
	}

	/**
	 * Util����Date
	 * 
	 * @param strDate
	 * @param pattern
	 * @return
	 * @throws ParseException
	 */
	public static java.util.Date stringToUtilDate(String strDate, String pattern) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.parse(strDate);
	}

	/**
	 * html����ת��String�����ַ���
	 * 
	 * @param s
	 * @return
	 */
	public static String formatHTMLOutput(String s) {
		if (s == null)
			return null;

		if (s.trim().equals(""))
			return "";

		String formatStr;
		formatStr = replaceAll(s, " ", "&nbsp;");
		formatStr = replaceAll(formatStr, "\n", "<br />");

		return formatStr;
	}

	/*
	 * 4��5�� @param num Ҫ�������� @param x Ҫ������С��λ
	 */
	public static double myround(double num, int x) {
		BigDecimal b = new BigDecimal(num);
		return b.setScale(x, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	/*
	 * public static String getSubstring(String content,int i){ int varsize=10;
	 * String strContent=content; if(strContent.length()<varsize+1){ return
	 * strContent; }else{ int
	 * max=(int)Math.ceil((double)strContent.length()/varsize); if(i<max-1){
	 * return strContent.substring(i*varsize,(i+1)*varsize); }else{ return
	 * strContent.substring(i*varsize); } } }
	 */

	/**
	 * liuqs
	 * 
	 * @param param
	 * @param d
	 * @return
	 */
	public static int parseLongInt(Long param, int d) {
		int i = d;
		try {
			i = Integer.parseInt(String.valueOf(param));
		} catch (Exception e) {
		}
		return i;
	}

	public static int parseLongInt(Long param) {
		return parseLongInt(param, 0);
	}

	public static String returnString(Object obj) {
		if (obj == null) {
			return "";
		} else {
			return obj.toString();
		}
	}

	/**
	 * �޸������ַ�����
	 * 
	 * @param value
	 * @return
	 */
	public static String htmlEncode(String value) {
		if (value == null || value.isEmpty())
			return value;
		String re[][] = { { "<", "&lt;" }, { ">", "&gt;" }, { "\"", "&quot;" }, { "\\��", "&acute;" },
				{ "&", "&amp;" } };

		for (int i = 0; i < 4; i++) {
			value = value.replaceAll(re[i][0], re[i][1]);
		}
		return value;
	}

	/**
	 * ��SQLע��
	 * 
	 * @param str
	 * @return
	 */
	public static boolean sql_inj(String str) {
		String inj_str = "'|and|exec|insert|select|delete|update|count|*|%|chr|mid|master|truncate|char|declare|;|or|-|+|,";
		String inj_stra[] = inj_str.split("|");
		for (int i = 0; i < inj_stra.length; i++) {
			if (str.indexOf(inj_stra[i]) >= 0) {
				return true;
			}
		}
		return false;
	}

	/**
	 * ��ȡ�ַ���ֵ���ж�null����ȥ�����ҿո�
	 * 
	 * @param v
	 * @param def
	 * @return
	 */
	public static String stringValue(String v, String def) {
		if (v == null || v.length() == 0)
			return def;
		return v.trim();
	}

	/***
	 * �ַ����滻
	 * 
	 * @param str
	 *            Ҫ�滻���ַ��� ����: "����һ��{0}����,��������{1}ʹ�õ�."
	 * @param values
	 *            �滻��ֵ ����: ["����","��ô"]
	 * @return �����滻����ַ��� ����: "����һ�����԰���,����������ôʹ�õ�."
	 */
	public static String format(String str, String... values) {
		if (values == null || values.length <= 0)
			return str;
		for (int i = 0; i < values.length; i++) {
			str = str.replace("{" + i + "}", values[i]);
		}
		return str;
	}

	/**
	 * ��ȡ�ͻ���IP
	 * 
	 * @param request
	 * @return
	 */
	public static final String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	/**
	 * ������ˮ��(����)
	 * 
	 * @return
	 */
	public static String generateSerialNumber(String prefix) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String str = sdf.format(cal.getTime());
		long value = Long.parseLong(str) - 20140101000000000L; // �ض�ֵ
		return prefix + String.valueOf(value);
	}

	/**
	 * ���ɲ�Ʒ��(���)
	 * 
	 * @param prefix
	 *            ���ǰ׺
	 * @return prefix+yyyyMMddHHmmssSSS+��λ�����
	 */
	public static String generateProductSerialNumber(String prefix) {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
		String str = sdf.format(cal.getTime()) + randomNumber();
		return prefix + str;
	}

	/**
	 * ���ַ���תΪList ����
	 * 
	 * @param ids
	 *            1,2,3,5
	 * @wuzy
	 * @return
	 */
	public static List<Integer> generateListInteger(String ids) {
		if (!StringUtil.empty(ids)) {
			String[] IDS = ids.split(",");
			List<Integer> list = new ArrayList<Integer>();
			for (String id : IDS) {
				if (StringUtil.isNotEmpty(id))
					list.add(Integer.parseInt(id));
			}
			return list;
		}
		return null;
	}

	/**
	 * ���ַ���תΪ����
	 * 
	 * @param ids
	 *            1,2,3,5
	 * @wuzy
	 * @return
	 */
	public static int[] generateIntArray(String ids) {
		if (!StringUtil.empty(ids)) {
			String[] IDS = ids.split(",");
			int[] list = new int[IDS.length];
			for (int i = 0; i < IDS.length; i++) {
				if (StringUtil.isNotEmpty(IDS[i]))
					list[i] = Integer.parseInt(IDS[i]);
			}
			return list;
		}
		return null;
	}

	/**
	 * ���ַ���תΪList ����
	 * 
	 * @param ids
	 *            1,2,3,5
	 * @return
	 */
	public static List<Long> generateListLong(String ids) {
		if (StringUtil.isNotEmpty(ids)) {
			String[] IDS = ids.split(",");
			List<Long> list = new ArrayList<Long>();
			for (String id : IDS) {
				if (StringUtil.isNotEmpty(id))
					list.add(Long.parseLong(id));
			}
			return list;
		}
		return null;
	}

	/**
	 * �ָ��ַ���ת����List
	 * 
	 * @param strings
	 * @return
	 */
	public static List<String> splitToStringList(String strings) {
		return splitToStringList(strings, ",");
	}

	/**
	 * �ָ��ַ���ת����List
	 * 
	 * @param strings
	 * @param regex
	 *            �ָ���
	 * @return
	 */
	public static List<String> splitToStringList(String strings, String regex) {
		if (StringUtil.isNotEmpty(strings)) {
			String[] strArr = strings.split(regex);
			List<String> list = new ArrayList<String>();
			for (String str : strArr) {
				if (StringUtil.isNotEmpty(str))
					list.add(str);
			}
			return list;
		}
		return null;
	}

	/**
	 * �Ƴ�һ���ַ����ʼ�ͽ�����ָ������
	 * 
	 * @param str
	 * @param prefix
	 * @return
	 */
	public static String trim(String str, String prefix) {
		if (empty(str))
			return str;
		int preLen = prefix.length();
		String newStr = str;
		if (str.startsWith(prefix)) {
			newStr = newStr.replaceFirst(prefix, "");
		}
		if (str.endsWith(prefix)) {
			newStr = newStr.substring(0, newStr.length() - preLen);
		}
		return newStr;
	}

	/**
	 * ȥ��json�ַ�����ת���
	 * 
	 * @param str
	 * @param prefix
	 * @return
	 */
	// public static String trimJson( String str ) {
	// if( empty( str ) )
	// return str;
	// return trim( str.replace( "\\\"", "\"" ), "\"" );
	// }

	/**
	 * ��ȡ��ǰ��վ��·��
	 * 
	 * @param request
	 * @return
	 */
	public static String getBasePath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		return basePath;
	}

	/**
	 * ��������˿�����������ް��� ֧�������õ������ַ�
	 * 
	 * @author wenhua_Lee
	 * @param str
	 * @return
	 */
	public static boolean checkRefundReason(String str) {
		// �ַ���Ϊ�� �򲻰������ַ�
		if (empty(str))
			return false;
		// ����
		final Pattern pattern = Pattern.compile("[\\^\\|\\$\\#]");
		final Matcher mat = pattern.matcher(str);
		// ���������ַ�����true
		if (mat.find())
			return true;
		else
			return false;
	}

	/**
	 * �������6λ���� ���㵥���е���
	 * 
	 * @return
	 */
	public static String generatorNo() {
		Random rd = new Random();
		int n = 0;
		while (n < 100000) {
			n = rd.nextInt(999999);
		}
		return String.valueOf(n);
	}

	/**
	 * �ַ���ת��unicode
	 */
	public static String string2Unicode(String str) {

		StringBuffer unicode = new StringBuffer();

		for (int i = 0; i < str.length(); i++) {

			// ȡ��ÿһ���ַ�
			char c = str.charAt(i);

			// ת��Ϊunicode
			unicode.append("\\u" + Integer.toHexString(c));
		}

		return unicode.toString();
	}

	/**
	 * unicode ת�ַ���
	 */
	public static String unicode2String(String unicode) {

		StringBuffer string = new StringBuffer();

		String[] hex = unicode.split("\\\\u");

		for (int i = 1; i < hex.length; i++) {

			// ת����ÿһ�������
			int data = Integer.parseInt(hex[i], 16);

			// ׷�ӳ�string
			string.append((char) data);
		}

		return string.toString();
	}

	/**
	 * ���˻ᵼ�³���XSS(��վ�ű�����)�Ĵ���
	 * 
	 * @param bodyHtml
	 * @return
	 */
/*	public static String cleaner(String bodyHtml) {
		if (empty(bodyHtml)) {
			return bodyHtml;
		}
		return Jsoup.clean(bodyHtml, Whitelist.relaxed());
	}
*/
	/**
	 * ������� ������java����ת����Map<String,String>����
	 * Tips:������ʹ�ù��������������������po���д���final���͵�ֵʱ�������ͨ���˷�ת����map
	 * ����һϵͳͨ���������ֱ�Ӱ�mapת���ɶ���ʱ�ᱨ��
	 * ,po���͵�final��ͼ������ı䡣������Ҳ�ܶ�̬�ı�fianlֵ,����˱�Υ�����趨final�ĳ��ԣ���������������ã�����
	 * 
	 * @param obj
	 * @author wenhua_Lee
	 * @return
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	public static Map<String, String> getMapValue(Object obj) {
		Map<String, String> map = new HashMap<String, String>();
		// ��ȡ���������е�����
		Field[] fields = obj.getClass().getDeclaredFields();
		if (fields.length > 0) {
			for (int i = 0; i < fields.length; i++) {
				try {
					// ��ȡ������
					String valueName = fields[i].getName();
					// ��÷���Ȩ�޵�booleanֵ
					boolean isAccessible = fields[i].isAccessible();
					// String type = fields[i].getGenericType().toString();
					// System.out.println( "����:" + type );
					fields[i].setAccessible(true);
					// ��ȡ��ǰ���Ե�ֵ
					Object value = fields[i].get(obj);
					if (value != null) {
						// ʱ���������ݵĴ��� ��������ת�����ض���ʽ���ݵĿ����ڴ���������жϼ��Ը�ʽ����toString
						if (value instanceof Date) {
							Date data = (Date) value;
							value = new SimpleDateFormat("yyyy-MM-dd").format(data);
						}
						map.put(valueName, value.toString());
					}
					// �ָ�ԭ�е�Ȩ�޷���
					fields[i].setAccessible(isAccessible);
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
		// System.out.println( map );
		return map;
	}

	/**
	 * ����ת�ַ���
	 * 
	 * @param ary
	 *            [1,2,3]
	 * @param prefix
	 *            ����:","
	 * @return "1,2,3"
	 */
	public static String arrayToString(String[] ary) {
		return arrayToString(ary, ",");
	}

	/**
	 * ����ת�ַ���
	 * 
	 * @param ary
	 *            [1,2,3]
	 * @param prefix
	 *            ����:","
	 * @return "1,2,3"
	 */
	public static String arrayToString(String[] ary, String prefix) {
		if (ary == null || ary.length <= 0) {
			return null;
		}
		StringBuilder sb = new StringBuilder();
		for (String string : ary) {
			sb.append(prefix).append(string);
		}
		return sb.toString().replaceFirst(prefix, "");
	}

	/**
	 * ����Ψһ��ͬ���
	 * 
	 * @param type
	 *            �̼�����
	 * @return
	 */
	public static String getContractNo(int type) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("CT");
		switch (type) {
		case 1:
			return stringBuffer.append("T").append(sf.format(new Date())).toString();
		case 2:
			return stringBuffer.append("H").append(sf.format(new Date())).toString();
		case 3:
			return stringBuffer.append("S").append(sf.format(new Date())).toString();
		case 4:
			return stringBuffer.append("M").append(sf.format(new Date())).toString();
		case 5:
			return stringBuffer.append("D").append(sf.format(new Date())).toString();

		default:
			return null;
		}
	}

	/**
	 * ��������ַ���
	 * 
	 * @param num
	 *            �����������������
	 * @return ����ַ���
	 */
	public static String generateStr(int num) {
		StringBuilder builder = new StringBuilder();
		String strSource = "0123456789qwertyuiopasdfghjklzxcvbnm";
		for (int i = 0; i < num; i++) {
			int index = (int) (Math.random() * (strSource.length() - 1));
			String subStr = strSource.substring(index, (strSource.length() - 1) <= index ? index : (index + 1));
			builder.append(subStr);
		}
		return builder.toString();
	}

	/**
	 * �������4λ��֤��
	 * 
	 * @return
	 */
	public static String randomNumber() {
		String s = "0123456789";
		char[] c = s.toCharArray();
		Random random = new Random();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < 4; i++) {
			sb.append(c[random.nextInt(c.length)]);
		}
		return sb.toString();
	}

}