package com.gcx.api.common.wechatutils;

/**
 * 常量
 */
public class WXPayConstants {

    public enum SignType {
        MD5, HMACSHA256
    }

    public static final String DOMAIN_API = "https://api.mch.weixin.qq.com";
    public static final String DOMAIN_API2 = "api2.mch.weixin.qq.com";
    public static final String DOMAIN_APIHK = "apihk.mch.weixin.qq.com";
    public static final String DOMAIN_APIUS = "apius.mch.weixin.qq.com";


    public static final String FAIL     = "FAIL";
    public static final String SUCCESS  = "SUCCESS";
    public static final String HMACSHA256 = "HMAC-SHA256";
    public static final String MD5 = "MD5";

    public static final String FIELD_SIGN = "sign";
    public static final String FIELD_SIGN_TYPE = "sign_type";


    public static final String  APPID= "wx8c783c35914da8d4";//微信公众号ID
    public static final String  MCH_ID= "1495037362";//商户号
    public static final String  KEY="c8f5ab8918814ee183e1c406f0473456";//签名的时候需要用到
    public static final String  NATIVE="NATIVE";//支付类型（扫码支付）

    public static final String MICROPAY_URL_SUFFIX     = "/pay/micropay";
    public static final String UNIFIEDORDER_URL_SUFFIX = "/pay/unifiedorder";//统一下单接口
    public static final String ORDERQUERY_URL_SUFFIX   = "/pay/orderquery";//主动查询订单接口
    public static final String REVERSE_URL_SUFFIX      = "/secapi/pay/reverse";
    public static final String CLOSEORDER_URL_SUFFIX   = "/pay/closeorder";
    public static final String REFUND_URL_SUFFIX       = "/secapi/pay/refund";
    public static final String REFUNDQUERY_URL_SUFFIX  = "/pay/refundquery";
    public static final String DOWNLOADBILL_URL_SUFFIX = "/pay/downloadbill";
    public static final String REPORT_URL_SUFFIX       = "/payitil/report";
    public static final String SHORTURL_URL_SUFFIX     = "/tools/shorturl";
    public static final String AUTHCODETOOPENID_URL_SUFFIX = "/tools/authcodetoopenid";









}

