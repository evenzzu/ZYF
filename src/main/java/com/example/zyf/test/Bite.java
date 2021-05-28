/**
 * @projectName ZYF
 * @package com.example.zyf.test
 * @className com.example.zyf.test.Bite
 * @copyright Copyright 2020 Thunisoft, Inc All rights reserved.
 */
package com.example.zyf.test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * Bite
 *
 * @description
 * @author zyf
 * @date 2021/4/25 18:18
 * @version 1.0
 */
public class Bite {
    @Test
    public void doGetTestWayTwo() {
        // 获得Http客户端(可以理解为:你得先有一个浏览器;注意:实际上HttpClient与浏览器是不一样的)
        CloseableHttpClient httpClient = HttpClientBuilder.create().build();

        String hbtc = "api.hbtc.co";
        String hbtcPath = "/openapi/quote/v1/ticker/price";
        String kraken = "api.kraken.com";
        String krakenPath = "/0/public/Ticker";
        BasicNameValuePair hbtcbasicNameValuePair = new BasicNameValuePair("symbol", "SUSHIUSDT");
        BasicNameValuePair krakenbasicNameValuePair = new BasicNameValuePair("pair", "BTCUSDT");

        // 参数
        URI uri = null;
        try {
            // 将参数放入键值对类NameValuePair中,再放入集合中
            List<NameValuePair> params = new ArrayList<>();
            params.add(krakenbasicNameValuePair);
            // 设置uri信息,并将参数集合放入uri;
            // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)
            uri = new URIBuilder().setScheme("https").setHost(kraken)
                    .setPath(krakenPath)
                    .setParameters(params).build();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        // 创建Get请求
        HttpGet httpGet = new HttpGet(uri);

        // 响应模型
        CloseableHttpResponse response = null;
        try {
//            // 配置信息
//            RequestConfig requestConfig = RequestConfig.custom()
//                    // 设置连接超时时间(单位毫秒)
//                    .setConnectTimeout(5000)
//                    // 设置请求超时时间(单位毫秒)
//                    .setConnectionRequestTimeout(5000)
//                    // socket读写超时时间(单位毫秒)
//                    .setSocketTimeout(5000)
//                    // 设置是否允许重定向(默认为true)
//                    .setRedirectsEnabled(true).build();
//
//            // 将上面的配置信息 运用到这个Get请求里
//            httpGet.setConfig(requestConfig);

            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);

            // 从响应模型中获取响应实体
            HttpEntity responseEntity = response.getEntity();
            System.out.println("响应状态为:" + response.getStatusLine());
            if (responseEntity != null) {
                JSONObject jsonObject = (JSONObject)JSONObject.parse(EntityUtils.toString(responseEntity));
                Object o = jsonObject.getJSONObject("result").getJSONObject("XBTUSDT").get("c");
                o.toString();

                System.out.println("=============="+o.toString());
                System.out.println("响应内容长度为:" + responseEntity.getContentLength());
//                System.out.println("响应内容为:" + EntityUtils.toString(responseEntity));

            }
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // 释放资源
                if (httpClient != null) {
                    httpClient.close();
                }
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
