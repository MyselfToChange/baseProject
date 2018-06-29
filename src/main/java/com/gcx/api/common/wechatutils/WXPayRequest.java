package com.gcx.api.common.wechatutils;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName WXPayRequest
 * @Description 请求微信统一下单接口
 * @Author qimy
 * @Date 2018/6/20 14:26
 **/
public class WXPayRequest {


    private static final Logger log = LoggerFactory.getLogger(WXPayRequest.class);



    /**
     * @param order 订单对象
     * @return 微信返回的二维码的url(返回结果)
     */
    public static String request(Object order){
        
        CloseableHttpClient httpClient = null;
        String code_url=null;
        //TODO 封装参数，将参数转换成xml字符串
        Map<String, String> data=new HashMap<>();
        data.put("appid",WXPayConstants.APPID);
        data.put("mch_id",WXPayConstants.MCH_ID);
        data.put("nonce_str", WXPayUtil.generateNonceStr());
        data.put("body","");
        data.put("out_trade_no","");
        data.put("total_fee",String.valueOf(new BigDecimal("1022").multiply(new BigDecimal("100")).intValue()));//价格（单位分）
//        data.put("total_fee","1");
        data.put("spbill_create_ip","119.90.51.183");
        data.put("notify_url","http://dev.gcx365.com:8001/feesys/wechatPay/notify_url");//TODO 回调地址，后期改成配置文件形式
        data.put("trade_type",WXPayConstants.NATIVE);


        try {
            String signedXml = WXPayUtil.generateSignedXml(data, WXPayConstants.KEY); //发起下单请求，获取判断请求是否成功，获取返回的code_url
            httpClient = HttpClientPool.getHttpClient();//从连接池获取httpclient连接
            HttpPost httpPost = new HttpPost(WXPayConstants.DOMAIN_API+WXPayConstants.UNIFIEDORDER_URL_SUFFIX);
            StringEntity postEntity = new StringEntity(signedXml, "UTF-8");
            httpPost.addHeader("Content-Type", "text/xml");
            httpPost.addHeader("User-Agent", "wxpay sdk java v1.0 ");
            httpPost.setEntity(postEntity);
            CloseableHttpResponse responseBody = httpClient.execute(httpPost);//发送请求
            int statusCode = responseBody.getStatusLine().getStatusCode();
            if(statusCode==200){
                HttpEntity httpEntity = responseBody.getEntity();
                String string = EntityUtils.toString(httpEntity, "UTF-8");
                Map<String, String> xmlToMap = WXPayUtil.xmlToMap(string);
                log.error("获取到的响应参数是"+xmlToMap.toString());
                String return_code = xmlToMap.get("return_code");
                if(return_code.equals("SUCCESS")){
                    String result_code = xmlToMap.get("result_code");
                    if(result_code.equals("SUCCESS")){
                        code_url = xmlToMap.get("code_url");
                    }else{
                        //TODO 这里要不要对错误的业务码做处理?
                        code_url = xmlToMap.get("err_code");
                    }
                }
            }else{
                log.error("请求微信下单接口失败,检查服务器是否能正常访问外网");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return code_url;
    }
}
