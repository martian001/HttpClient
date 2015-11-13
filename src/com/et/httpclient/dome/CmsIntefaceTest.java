package com.et.httpclient.dome;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆ @author： liangyanjun <br>
 * ★☆ @time：2015年11月13日上午11:10:43 <br>
 * ★☆ @version： <br>
 * ★☆ @lastMotifyTime： <br>
 * ★☆ @ClassAnnotation：cms接口测试 <br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 * ★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★☆★<br>
 */
public class CmsIntefaceTest {
   public static void main(String[] args) throws ClientProtocolException, IOException {
      CmsIntefaceTest loginCms = new CmsIntefaceTest();
      String set_cookie = loginCms.getCookie();
      String sessionId = set_cookie.substring(0, set_cookie.indexOf(";"));
      System.out.println(sessionId);

      // 根据获得的Cookie值，设置头信息，然后发送请求，获得内容
      HttpPost post = new HttpPost("http://localhost:8080/cms/provinceinfo/provinceInfoList");
      post.setHeader("Cookie", "JSESSIONID=" + sessionId);
      HttpResponse response = new DefaultHttpClient().execute(post);
      loginCms.printResponse(response.getEntity());
   }

   /**登录地址*/
   private String loginUrl = "http://localhost:8080/cms/admin/login";
   /**登录用户名*/
   private String userName = "admin";
   /**登录密码*/
   private String password = "123456";

   /**
    * 获取cookie
    * @return
    * @throws UnsupportedEncodingException
    * @throws IOException
    * @throws ClientProtocolException
    */
   private String getCookie() throws UnsupportedEncodingException, IOException, ClientProtocolException {
      HttpClient httpclient = new DefaultHttpClient();

      // 创建参数队列：设置登录参数
      List<NameValuePair> formparams = new ArrayList<NameValuePair>();
      formparams.add(new BasicNameValuePair("userName", userName));
      formparams.add(new BasicNameValuePair("password", password));
      UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formparams, "UTF-8");

      // 新建Http post请求
      HttpPost httppost = new HttpPost(loginUrl);
      httppost.setEntity(entity);

      // 执行请求，得到响应
      HttpResponse response = httpclient.execute(httppost);
      // InputStream content = response.getEntity().getContent();
      String set_cookie = response.getFirstHeader("Set-Cookie").getValue();
      return set_cookie;
   }

   /**
    * 打印Response
    * @param entity
    * @throws IOException
    * @throws UnsupportedEncodingException
    */
   private void printResponse(HttpEntity entity) throws IOException, UnsupportedEncodingException {
      StringBuilder result = new StringBuilder();
      if (entity != null) {
         InputStream is = entity.getContent();
         BufferedReader br = new BufferedReader(new InputStreamReader(is));
         String temp = "";
         while ((temp = br.readLine()) != null) {
            String str = new String(temp.getBytes(), "utf-8");
            result.append(str);
         }
      }
      System.out.println(result);
   }
}
