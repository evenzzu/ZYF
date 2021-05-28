package com.example.zyf.service;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * Bite
 *
 * @description
 * @author zhangyifan
 * @date 2021/4/25 18:18
 * @version 1.0
 */
@Component
@Service
@Slf4j
public class BiteService {
    // HBTC请求地址
    @Value("${hbtc.host}")
    private String hbtcHost;
    // HBTC请求路径
    @Value("${hbtc.path}")
    private String hbtcPath;
    // kraken请求地址
    @Value("${kraken.host}")
    private String krakenHost;
    // kraken请求路径
    @Value("${kraken.path}")
    private String krakenPath;
    // 获得Http客户端
    CloseableHttpClient httpClient = HttpClientBuilder.create().build();
    // 获取货币对象
    BasicNameValuePair hbtcBTCUSDT = new BasicNameValuePair("symbol", "BTCUSDT");
    BasicNameValuePair krakenBTCUSDT = new BasicNameValuePair("pair", "BTCUSDT");
    BasicNameValuePair hbtcETHUSDT = new BasicNameValuePair("symbol", "ETHUSDT");
    BasicNameValuePair krakETHUSDT = new BasicNameValuePair("pair", "ETHUSDT");

    /**
     *
     * BiteService
     * @description 比较BTCUSDT货币的价格，hbtc.co较高时打印日志
     * @date 2021/4/26 12:27
     * @author zhangyifan
     * @version 1.0
     */
    // 定时任务 从第0秒每十秒查询一次
//    @Scheduled(cron = "0/10 * * * * ?")
    public String btcusdt() {
        String hbtcPrice = null;
        String krakenPrice = null;
        // 查询hbtc.co的BTCUSDT的价格返回JSON
        JSONObject hbtcjsonObject = connectHttp(hbtcHost, hbtcPath, hbtcBTCUSDT);
        if (hbtcjsonObject != null) {
            hbtcPrice = hbtcjsonObject.get("price").toString();
            log.info("当前时间：" + LocalDateTime.now() + '\n' + "交易平台:" + hbtcHost + '\n' + hbtcBTCUSDT.getValue() + "当前价格为：" + hbtcPrice);
        }
        // 查询kraken.com的BTCUSDT的价格返回JSON
        JSONObject krakenjsonObject = connectHttp(krakenHost, krakenPath, krakenBTCUSDT);
        if (krakenjsonObject != null) {
            // kraken.com获取交易价格时有多个竞拍价，这里取了实际成交价格
            krakenPrice = krakenjsonObject.getJSONObject("result").getJSONObject("XBTUSDT").get("c").toString().substring(2, 11);
            log.info("当前时间：" + LocalDateTime.now() + '\n' + "交易平台:" + krakenHost + '\n' + krakenBTCUSDT.getValue() + "当前价格为：" + krakenPrice);
        }
        // 如果hbtc.co平台货币价格高于kraken.com时，打印日志
        if (Double.parseDouble(hbtcPrice) > Double.parseDouble(krakenPrice)) {
            String logs = "当前时间：" + LocalDateTime.now() + "货币BTCUSDT在交易所hbtc.co交易价格较高，价格为：" + hbtcPrice;
            log.info("===========================");
            log.info(logs);
            log.info("===========================");
            return logs;
        }
        return null;
    }

    /**
     *
     * BiteService
     * @description 比较ETHUSDT货币的价格，hbtc.co较高时打印日志
     * @date 2021/4/26 12:27
     * @author zhangyifan
     * @version 1.0
     */
    // 定时任务 从第五秒开始每十秒查询一次
//    @Scheduled(cron = "5/10 * * * * ?")
    public String ethusdt() {
        String hbtcPrice = null;
        String krakenPrice = null;
        JSONObject hbtcjsonObject = connectHttp(hbtcHost, hbtcPath, hbtcETHUSDT);
        if (hbtcjsonObject != null) {
            hbtcPrice = hbtcjsonObject.get("price").toString();
            log.info("当前时间：" + LocalDateTime.now() + '\n' + "交易平台:" + hbtcHost + '\n' + hbtcETHUSDT.getValue() + "当前价格为：" + hbtcPrice);
        }
        JSONObject krakenjsonObject = connectHttp(krakenHost, krakenPath, krakETHUSDT);
        if (krakenjsonObject != null) {
            krakenPrice = krakenjsonObject.getJSONObject("result").getJSONObject("ETHUSDT").get("c").toString().substring(2, 11);
            log.info("当前时间：" + LocalDateTime.now() + '\n' + "交易平台:" + krakenHost + '\n' + krakETHUSDT.getValue() + "当前价格为：" + krakenPrice);
        }
        // 如果hbtc.co平台货币价格高于kraken.com时，打印日志
        if (Double.parseDouble(hbtcPrice) > Double.parseDouble(krakenPrice)) {
            String logs = "当前时间：" + LocalDateTime.now() + "货币ETHUSDT在交易所hbtc.co交易价格较高，价格为：" + hbtcPrice;
            log.info("===========================");
            log.info(logs);
            log.info("===========================");
            return logs;
        }
        return null;

    }

    /**
     *
     * BiteService
     * @description 连接HTTP 返回请求实体
     * @param host
     * @param path
     * @param param
     * @return org.apache.http.HttpEntity
     * @date 2021/4/26 11:31
     * @author zhangyifan
     * @version 1.0
     */
    public JSONObject connectHttp(String host, String path, BasicNameValuePair param) {
        // 参数
        URI uri = null;
        try {
            // 将参数放入键值对类NameValuePair中,再放入集合中
            List<NameValuePair> params = new ArrayList<>();
            params.add(param);
            // 设置uri信息,并将参数集合放入uri;
            // 注:这里也支持一个键值对一个键值对地往里面放setParameter(String key, String value)
            uri = new URIBuilder().setScheme("https").setHost(host)
                    .setPath(path)
                    .setParameters(params).build();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
        // 创建Get请求
        HttpGet httpGet = new HttpGet(uri);

        // 响应模型
        CloseableHttpResponse response = null;
        // 响应体
        HttpEntity responseEntity = null;
        // 响应体JSON
        JSONObject jsonObject = null;
        try {
            // 配置信息
            RequestConfig requestConfig = RequestConfig.custom()
                    // 设置连接超时时间(单位毫秒)
                    .setConnectTimeout(5000)
                    // 设置请求超时时间(单位毫秒)
                    .setConnectionRequestTimeout(5000)
                    // socket读写超时时间(单位毫秒)
                    .setSocketTimeout(5000).build();

            // 将上面的配置信息 运用到这个Get请求里
            httpGet.setConfig(requestConfig);

            // 由客户端执行(发送)Get请求
            response = httpClient.execute(httpGet);

            // 从响应模型中获取响应实体
            responseEntity = response.getEntity();
            // 获取实体类JSON
            jsonObject = (JSONObject) JSONObject.parse(EntityUtils.toString(responseEntity));
        } catch (ClientProtocolException e) {
            log.error("ClientProtocolExceptionl连接异常", e);
        } catch (ParseException e) {
            log.error("JSONObject格式化异常", e);
        } catch (IOException e) {
            log.error("IO异常", e);
        }
        return jsonObject;
    }
}
