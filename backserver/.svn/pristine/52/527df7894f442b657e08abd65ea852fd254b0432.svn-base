package com.comm.sms.util;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.comm.model.TemplateSMS;
import com.comm.util.CipherUtil;
import com.comm.util.Const;
import com.comm.util.DateUtil;
import com.comm.util.HttpUtil;
import com.comm.util.TranscodeUtil;
import com.comm.web.common.util.PropUtil;
import net.sf.json.JSONObject;

public class SmsUtil {

    // public static String server = "api.ucpaas.com";
    // public static String version = "2014-06-30";
    // public static String accountSid = "a4118fcb9cc3aa3e583c28be7659748b";
    // public static String token = "9bb5a935437e1fc1f9b700b3728dc343";
    // public static String appId = "09205710f14a48ca927b66f0b6e5f349";
    // public static String templateId = "17453";

    public static String server = "";
    public static String version = "";
    public static String accountSid = "";
    public static String token = "";
    public static String appId = "";
    public static String templateId = "";

    static {
        server = PropUtil.getProperty(Const.SERVER);
        version = PropUtil.getProperty(Const.VERSION);
        accountSid = PropUtil.getProperty(Const.ACCOUNTSID);
        token = PropUtil.getProperty(Const.TOKEN);
        appId = PropUtil.getProperty(Const.APPID);
        templateId = PropUtil.getProperty(Const.TEMPLATEID);
    }

    private static Logger logger = Logger.getLogger(SmsUtil.class);

    /**
     * 发送短信验证码
     * 
     * @param to
     *            手机号
     * @param param
     *            验证码
     * @return
     */
    public static String templateSMS(String to, String param) throws Exception {
        String result = "";

        // 构造请求URL内容
        String url = getUrl();

        logger.info(url);

        Map<String, String> params = new HashMap<String, String>();

        String timestamp = DateUtil.getNow14();// 时间戳
        String signature = getSignature(accountSid, token, timestamp);

        params.put("sig", signature);

        TemplateSMS templateSMS = new TemplateSMS();
        templateSMS.setAppId(appId);
        templateSMS.setTemplateId(templateId);
        templateSMS.setTo(to);
        templateSMS.setParam(param);

        JSONObject obj = new JSONObject();
        obj.put("templateSMS", JSONObject.fromObject(templateSMS));
        String body = obj.toString();

        logger.info(body);

        String src = accountSid + ":" + timestamp;
        String auth = TranscodeUtil.byteArrayToBase64Str(src.getBytes());

        Map<String, String> headers = new HashMap<String, String>();
        headers.put("Accept", "application/json");
        headers.put("Content-Type", "application/json;charset=utf-8");
        headers.put("Authorization", auth);

        result = HttpUtil.doPostSSL(url, params, body, headers, "utf-8");

        JSONObject resultJson = JSONObject.fromObject(result);
        
        JSONObject respJson = resultJson.optJSONObject("resp");
        
        String respCode = respJson.optString("respCode");

        if ("000000".equals(respCode)) {
            result = Const.RESP_SUCCESS;
            logger.info("短信验证码发送成功");
        } else {
            result = Const.RESP_FAILURE;
            logger.error("短信验证码发送失败");
            logger.error("错误码==" + respCode);
        }
        return result;
    }

    /**
     * 构造请求URL内容
     * 
     * @return
     */
    public static String getUrl() {
        StringBuffer sb = new StringBuffer("https://");
        sb.append(server);
        sb.append("/");
        sb.append(version);
        sb.append("/Accounts/");
        sb.append(accountSid);
        sb.append("/Messages/templateSMS");
        return sb.toString();
    }

    /**
     * 生成签名
     * 
     * @param accountSid
     *            账户Id
     * @param authToken
     *            账户授权令牌
     * @param timestamp
     *            时间戳
     * @return
     */
    public static String getSignature(String accountSid, String authToken, String timestamp) throws Exception {
        String sig = accountSid + authToken + timestamp;
        String signature = CipherUtil.MD5Encode(sig).toUpperCase();
        return signature;
    }

}
