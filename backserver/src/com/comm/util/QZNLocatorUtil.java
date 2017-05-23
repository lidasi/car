package com.comm.util;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;

import com.comm.web.common.util.PropUtil;

import net.sf.json.JSONObject;

public class QZNLocatorUtil {

	public static final String URL_LOCATE = "/mobile/jishidingwei_dingwei_enc.do";
	
	public static final int CONN_TIMEOUT = 20000;
	public static final int READ_TIMEOUT = 20000;
	
	public static String doLocate(String facc, String acc, String ftel, String encKey, String ap) {
		String strRet = null;
		
		String url = PropUtil.getProperty("WEIXIN_IF_DWDEVICE_URL");

//		String url = "http://180.166.222.4/dearwhere";
//		String url = "http://172.60.0.26:8080/dearwhere";
		
		JSONObject json = new JSONObject();
//		json.put("f_account", facc);
//		json.put("account", acc);
//		json.put("f_telNum", ftel);
		json.put("facc", facc);
		json.put("acc", acc);
		json.put("ftl", ftel);
		
        // 获取时间戳
        String timestamp = DateFormatUtils.format(new Date(), DateUtil.FMT_sdf17);
        
        // 构造digest
        StringBuilder sbToDigest = new StringBuilder();
        sbToDigest.append(timestamp).append(facc).append(acc).append(ftel);
        String digest = CipherUtil.MD5Encode(sbToDigest.toString());

        // 构造安全验证串
        String params[] = new String[5];
        params[0] = timestamp;
        params[1] = facc;
        params[2] = acc;
        params[3] = ftel;
        params[4] = digest;
        
		String encValue = CommCryptoUtil.getEncValue(params, encKey, ap);
		
		json.put("envValue", encValue);
		
		JSONObject jsonContent = new JSONObject();
		jsonContent.put("content", json);
		
		strRet = HttpUtil.getBody4Post(url + URL_LOCATE, jsonContent, CONN_TIMEOUT, READ_TIMEOUT);
		
		return strRet;
	}

//	public static void main(String[] args) {
//		String strRet = doLocate("q5953848710", "q6238248105", "18016450137",
//				"B3B0182C7FF8C32FDAA1FCAB55910930", "0987654321fedcba");
//		System.out.println("strRet=" + strRet);
//	}
	
}
