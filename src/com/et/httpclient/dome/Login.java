package com.et.httpclient.dome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Test;

/** ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2016年10月11日上午11:16:25 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation： <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br> */
public class Login {
    @Test
    public void testName() throws Exception {
        try {
            HttpClient httpclient = new DefaultHttpClient();
            // 创建参数队列：设置登录参数
            Map<String, String> param = new HashMap<>();
            param.put("user_id", "20196");
            //        param.put("password", "MTIzNDU2");
            String url = "http://localhost:8088/BMS/mobileApi/getUserInfo1.action";
            HttpPost httppost = fillParam(param, url);
//            Header header1 = new BasicHeader("Content-Type", "application/json");
//            httppost.addHeader(header1);
//            Header header2 = new BasicHeader("charset", "utf-8");
//            httppost.addHeader(header2);
//            Header header3 = new BasicHeader("Accept-Version", "api_v1");
//            httppost.addHeader(header3);
//            // 执行请求，得到响应
//            HttpResponse response = httpclient.execute(httppost);
//            // 打印返回的结果：entity是发送或者接收消息的载体。entities?可以通过request和 response获取到
//            StringBuilder result = getResponseContent(response);
//            System.out.println(result);
//            // InputStream content = response.getEntity().getContent();
//            String set_cookie = response.getFirstHeader("Set-Cookie").getValue();
//            // 打印Cookie值
//            String cookieStr = set_cookie.substring(0, set_cookie.indexOf(";"));
//            System.out.println(cookieStr);
            // 获得Session之后，把该值保存下来。然后再访问其他受保护对象时设置头信息的Cookie字段就好了，代码片段如下
            HttpClient httpclient2 = new DefaultHttpClient();
            // 根据获得的Cookie值，设置头信息，然后发送请求，获得内容
            url = "http://localhost:8088/BMS/mobileApi/getSignDays.action";
            param = new HashMap<>();
            param.put("userId", "20196");
            httppost = fillParam(param, url);
            httppost.setHeader("Cookie", "JSESSIONID=8A4882EC0377F088F472290691D9D32E");
            HttpResponse response2 = httpclient2.execute(httppost);
            System.out.println(getResponseContent(response2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testName2() throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        // 创建参数队列：设置登录参数
        Map<String, String> param = new HashMap<>();
        String url = "";

        // 获得Session之后，把该值保存下来。然后再访问其他受保护对象时设置头信息的Cookie字段就好了，代码片段如下
        httpclient = new DefaultHttpClient();
        // 根据获得的Cookie值，设置头信息，然后发送请求，获得内容
        url = "http://0755.testerp.qfang.com/qfang-hr/f7/hr/personData";//测试环境
        //url = "http://0755.qfang.com/qfang-hr/f7/hr/personData";//正式环境
        HttpPost httppost = fillParam(param, url);
        param = new HashMap<>();
        param.put("checkValue", "checked");
        param.put("parentId", "834e675b-2cdd-460e-adaf-d1b6cca2d072");
        param.put("pagerNumber", "1");
        param.put("perPage", "1000");
        httppost = fillParam(param, url);
        httppost.setHeader("Cookie", "JSESSIONID=aaaSmAJOfxh5K5T3pM0Iv; COMMONSESSION=aaaSmAJOfxh5K5T3pM0Iv; personId=4b2ee417-aff5-472b-9f93-eb0a823d7cb0; loginHistory_19999999999=19999999999%7C%E6%B7%B1%E5%9C%B3%E4%B8%93%E5%91%98; noClew=flase; 4b2ee417-aff5-472b-9f93-eb0a823d7cb0=");
        HttpResponse response2 = httpclient.execute(httppost);
        System.out.println(getResponseContent(response2));
    }

    /** 创建参数
     *
     * @author:liangyanjun
     * @time:2016年10月11日上午9:59:42
     * @param param
     * @return
     * @throws UnsupportedEncodingException */
    private static HttpPost fillParam(Map<String, String> param, String url) throws UnsupportedEncodingException {
        Set<String> keySet = param.keySet();
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        for (String key : keySet) {
            formparams.add(new BasicNameValuePair(key, param.get(key)));
        }
        UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");
        // 新建Http post请求
        HttpPost httppost = new HttpPost(url);
        httppost.setEntity(entity);
        return httppost;
    }

    /** 获取response内容
     * 
     * @author:liangyanjun
     * @time:2016年10月11日上午11:15:03
     * @param response
     * @return
     * @throws IOException
     * @throws UnsupportedEncodingException */
    private static StringBuilder getResponseContent(HttpResponse response) throws IOException, UnsupportedEncodingException {
        HttpEntity entity = response.getEntity();
        StringBuilder result = new StringBuilder();
        if (entity != null) {
            InputStream instream = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(instream));
            String temp = "";
            while ((temp = br.readLine()) != null) {
                String str = new String(temp.getBytes(), "utf-8");
                result.append(str);
            }
        }
        return result;
    }
}
