package com.et.httpclient.dome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆            @author： The One                  ☆★
★☆            @time：2014年10月13日 上午8:55:44      ☆★
★☆            @version：1.0                      ☆★
★☆            @lastMotifyTime：                                                      ☆★
★☆            @ClassAnnotation：                                                   ☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★
 */
public class Login {
   public static void main(String[] args) throws ClientProtocolException, IOException {
      HttpClient httpclient = new DefaultHttpClient();

      // 创建参数队列：设置登录参数
      List<NameValuePair> formparams = new ArrayList<NameValuePair>();
      formparams.add(new BasicNameValuePair("loginName", "admin"));
      formparams.add(new BasicNameValuePair("loginPassword", "4444"));
      UrlEncodedFormEntity entity1 = new UrlEncodedFormEntity(formparams, "UTF-8");

      // 新建Http post请求
      HttpPost httppost = new HttpPost(
            "https://account.xiaomi.com/pass/serviceLogin?callback=http%3A%2F%2Forder.mi.com%2Flogin%2Fcallback%3Ffollowup%3Dhttp%253A%252F%252Fwww.mi.com%252Findex.html%26sign%3DNDRhYjQwYmNlZTg2ZGJhZjI0MTJjY2ZiMTNiZWExODMwYjkwNzg2ZQ%2C%2C&sid=mi_eshop");
      httppost.setEntity(entity1);

      // 执行请求，得到响应
      HttpResponse response = httpclient.execute(httppost);
      // InputStream content = response.getEntity().getContent();
      String set_cookie = response.getFirstHeader("Set-Cookie").getValue();

      // 打印Cookie值
      System.out.println(set_cookie.substring(0, set_cookie.indexOf(";")));

      // 打印返回的结果：entity是发送或者接收消息的载体。entities 可以通过request和 response获取到
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
      System.out.println(result);

      // 获得Session之后，把该值保存下来。然后再访问其他受保护对象时设置头信息的Cookie字段就好了，代码片段如下
      HttpClient httpclient2 = new DefaultHttpClient();
      // 根据获得的Cookie值，设置头信息，然后发送请求，获得内容
      HttpGet httpget = new HttpGet("http://***/showsource?solution_id=7263065");
      httpget.setHeader("Cookie", "JSESSIONID=817CF84802016AF09E3DFFE59532FD02");
      HttpResponse response2 = httpclient2.execute(httpget);
   }
}
