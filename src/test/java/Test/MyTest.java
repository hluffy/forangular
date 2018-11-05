package Test;

import com.dk.op.ComputeDistance;
import com.dk.util.ParseUtils;
import com.mysql.cj.core.util.Base64Decoder;
import com.sun.xml.internal.org.jvnet.staxex.Base64EncoderStream;
import sun.misc.BASE64Decoder;
import org.junit.Test;
import sun.net.www.ParseUtil;

public class MyTest {
//	@Test
	public void test(){
		ComputeDistance cd = new ComputeDistance();
		cd.setA(1);
		cd.setK(47);

		double d = cd.getDistance(-56);

		System.out.println(d);

	}

	@Test
	public void byteToString(){
		String data = "0d4d1c53000000b7C1DC120100000000A41E6E7410900000";
		byte[] decode = ParseUtils.hexStringToByte(data);
//		System.out.println(ParseUtils.bytesToHexString(decode));
//
//		String a1 = ParseUtils.binary(ParseUtils.subBytes(decode, 0, 2), 16);
//		String a2 = ParseUtils.binary(ParseUtils.subBytes(decode,2,1), 16);
//		String a3 = ParseUtils.binary(ParseUtils.subBytes(decode,3,2), 10);
//		String a4 = ParseUtils.binary(ParseUtils.subBytes(decode,5,2), 10);
//		String a5 = ParseUtils.binary(ParseUtils.subBytes(decode,7,1), 10);
//		String a6 = ParseUtils.binary(ParseUtils.subBytes(decode,8,2), 10);
//
//		System.out.println(a1);
//		System.out.println(a2);
//		System.out.println(a3);
//		System.out.println(a4);
//		System.out.println(a5);
//		System.out.println(a6);

		String a = ParseUtils.binary(ParseUtils.subBytes(decode,0,10),16);
		System.out.println(a);




	}
}
