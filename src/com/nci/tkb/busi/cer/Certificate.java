package com.nci.tkb.busi.cer;

import com.nci.tkb.busi.utils.CertificateUtils;

public class Certificate
{

	private static final String KEY_STORE_NAME = "demo.keystore";
	private static final String CERTIFICATE_NAME = "EncCert2-p15.cer";
	private static final String password = "123456";
	private static final String alias = "mykey";
	private static String certificatePath;
	private static String keyStorePath;

	static
	{
		String currentDir = Certificate.class.getResource("").getPath();
		if (currentDir.startsWith("/"))
			currentDir = currentDir.substring(1);
		if (!currentDir.endsWith("/"))
			currentDir += "/";
		keyStorePath = currentDir + KEY_STORE_NAME;
		certificatePath = currentDir + CERTIFICATE_NAME;
	}

	public static void main(String[] args) throws Exception
	{
		simple();
		simpleSign();
		testFileSign();
	}

	
	/** 
	 * 公钥加密—私钥解密
	 * @throws Exception 
	 **/
	static void simple() throws Exception
	{
		System.err.println("公钥加密——私钥解密");
		String source = "这是一行没有任何意义的文字，你看完了等于没看，不是吗？";
		byte[] data = source.getBytes();

		byte[] encrypt = CertificateUtils.encryptByPublicKey(data, certificatePath);

		byte[] decrypt = CertificateUtils.decryptByPrivateKey(encrypt, keyStorePath, alias, password);
		String outputStr = new String(decrypt);
		System.out.println("加密前: \r\n" + source + "\r\n" + "解密后: \r\n" + outputStr);
	}

	
	/** 
	 * 私钥加密——公钥解密
	 * @throws Exception 
	 **/
	static void simpleSign() throws Exception
	{
		System.err.println("私钥加密——公钥解密");
		String source = "这是一行签名的测试文字";
		byte[] data = source.getBytes();
		// 私钥加密
		byte[] encodedData = CertificateUtils.encryptByPrivateKey(data, keyStorePath, alias, password);
		// 公钥加密
		byte[] decodedData = CertificateUtils.decryptByPublicKey(encodedData, certificatePath);
		String target = new String(decodedData);
		System.out.println("加密前: \r\n" + source + "\r\n" + "解密后: \r\n" + target);
		System.err.println("私钥签名——公钥验证签名");
		// 产生签名
		String sign = CertificateUtils.signToBase64(encodedData, keyStorePath, alias, password);
		System.out.println("签名:\r\n" + sign);
		// 验证签名
		boolean status = CertificateUtils.verifySign(encodedData, sign, certificatePath);
		System.err.println("状态:\r\n" + status);
	}

	static void testFileSign() throws Exception
	{
		String filePath = "E:/test/test.txt";
		String sign = CertificateUtils.signFileToBase64(filePath, keyStorePath, alias, password);
		System.err.println("生成签名：\r\n" + sign);
		boolean result = CertificateUtils.verifyFileSign(filePath, sign, certificatePath);
		System.err.println("校验结果：" + result);
	}

}