package cn.lixingyu.Apache.service.impl;

import com.alipay.api.AlipayApiException;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradePagePayRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AlipayUtil {

    @Value("${appId}")
    private String appId;
    @Value("${privateKey}")
    private String privateKey;
    @Value("${publicKey}")
    private String publicKey;
    @Value("${notifyUrl}")
    private String notifyUrl;
    @Value("${returnUrl}")
    private String returnUrl;
    @Value("${signType}")
    private String signType;
    @Value("${charset}")
    private String charset;
    @Value("${gatewayUrl}")
    private String gatewayUrl;
    @Value("${logPath}")
    private String logPath;
    @Value("${certpublickey}")
    private String certpublickey;
    @Value("${rootcert}")
    private String rootcert;
    @Value("${appcertpublickey}")
    private String appcertpublickey;

    public DefaultAlipayClient getDefaultAlipayClient() {
        CertAlipayRequest certAlipayRequest = new CertAlipayRequest();
        certAlipayRequest.setServerUrl(gatewayUrl);
        certAlipayRequest.setAppId(appId);
        certAlipayRequest.setPrivateKey(privateKey);
        certAlipayRequest.setFormat("json");
        certAlipayRequest.setCharset(charset);
        certAlipayRequest.setSignType(signType);
        certAlipayRequest.setCertPath(appcertpublickey);
        certAlipayRequest.setAlipayPublicCertPath(certpublickey);
        certAlipayRequest.setRootCertPath(rootcert);
        DefaultAlipayClient alipayClient = null;
        try {
            alipayClient = new DefaultAlipayClient(certAlipayRequest);
        } catch (AlipayApiException e) {
            System.out.println("实例化alipayClient失败！");
        }
        return alipayClient;
    }

    public AlipayTradePagePayRequest getAlipayTradePagePayRequest() {
        AlipayTradePagePayRequest alipayRequest = new AlipayTradePagePayRequest();
        alipayRequest.setReturnUrl(returnUrl);
        alipayRequest.setNotifyUrl(notifyUrl);
        return alipayRequest;
    }
}
