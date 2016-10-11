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

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
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
        HttpClient httpclient = new DefaultHttpClient();
        // 创建参数队列：设置登录参数
        Map<String, String> param = new HashMap<>();
        param.put("userName", "jigou1");
        param.put("password", "123456");
        String url = "http://localhost:8080/AOM/ignore/checkUserLogin.action";
        HttpPost httppost = fillParam(param, url);

        // 执行请求，得到响应
        HttpResponse response = httpclient.execute(httppost);
        // InputStream content = response.getEntity().getContent();
        String set_cookie = response.getFirstHeader("Set-Cookie").getValue();
        // 打印Cookie值
        String cookieStr = set_cookie.substring(0, set_cookie.indexOf(";"));
        System.out.println(cookieStr);
        // 打印返回的结果：entity是发送或者接收消息的载体。entities?可以通过request和 response获取到
        StringBuilder result = getResponseContent(response);
        //        System.out.println(result);

        // 获得Session之后，把该值保存下来。然后再访问其他受保护对象时设置头信息的Cookie字段就好了，代码片段如下
        HttpClient httpclient2 = new DefaultHttpClient();
        // 根据获得的Cookie值，设置头信息，然后发送请求，获得内容
        url = "http://localhost:8080/AOM/financeController/orgMakeLoansList.action";
        param = new HashMap<>();
        param.put("page", "1");
        param.put("rows", "10");
        httppost = fillParam(param, url);
        httppost.setHeader("Cookie", cookieStr);
        HttpResponse response2 = httpclient2.execute(httppost);
        System.out.println(getResponseContent(response2));
    }

    @Test
    public void testName2() throws Exception {
        HttpClient httpclient = new DefaultHttpClient();
        // 创建参数队列：设置登录参数
        Map<String, String> param = new HashMap<>();
        String url = "http://0755.qfang.com/qfang-infra/login/loginTimeOutValidation?userName=13145860701&password=995396426";
        HttpGet httpGet = new HttpGet(url);
        // 执行请求，得到响应
        HttpResponse response = httpclient.execute(httpGet);
        String set_cookie = response.getFirstHeader("Set-Cookie").getValue();
        // 打印Cookie值
        String cookieStr = set_cookie.substring(0, set_cookie.indexOf(";"));
        System.out.println(cookieStr);

        // 获得Session之后，把该值保存下来。然后再访问其他受保护对象时设置头信息的Cookie字段就好了，代码片段如下
        httpclient = new DefaultHttpClient();
        // 根据获得的Cookie值，设置头信息，然后发送请求，获得内容
        url = "http://0755.qfang.com/qfang-hr/f7/hr/personData";
        HttpPost httppost = fillParam(param, url);
        param = new HashMap<>();
        param.put("checkValue", "checked");
        param.put("parentId", "87dbe022-03e2-4b6e-9092-65455042ad52");
        param.put("pagerNumber", "2");
        param.put("perPage", "13");
        httppost = fillParam(param, url);
        httppost.setHeader("Cookie", cookieStr);
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
