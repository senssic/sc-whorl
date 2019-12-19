package sc.whorl.system.utils.rest;

import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;

public class RestClient {
    private static int connectionTimout = 10000;
    private static int readTimeout = 10000;

    public static JSONObject doPostObject(String url, Object request) throws Exception {
        return doPostObject(url, null, request);
    }


    public static JSONObject doPostObject(String url, Map<String, String> header, Object request) throws Exception {
        String requestJson = JSONObject.toJSONString(request);
        String responseJson = doPostJson(url, header, requestJson);
        JSONObject response = JSONObject.parseObject(responseJson);
        return response;
    }

    public static String doPostJson(String url, Map<String, String> header, String requestJson) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

        conn.setConnectTimeout(connectionTimout);
        conn.setReadTimeout(readTimeout);
        conn.setRequestMethod("POST");// 提交模式
        conn.setDoOutput(true);// 是否输入参数
        conn.setRequestProperty("content-type", "application/json");
        if (header != null) {
            for (String key : header.keySet()) {
                String value = header.get(key);
                conn.setRequestProperty(key, value);
            }
        }
        conn.setRequestProperty("Charset", "UTF-8");

        byte[] data = requestJson.getBytes(Charset.forName("utf-8"));
        conn.getOutputStream().write(data);
        int resultCode = conn.getResponseCode();
        return getString(conn, resultCode);
    }

    public static JSONObject doGetJsonObject(String url) throws Exception {
        return doGetJsonObject(url, null);
    }

    public static JSONObject doGetJsonObject(String url, Map<String, String> header) throws Exception {
        String responseJson = doGetJson(url, header);
        JSONObject response = JSONObject.parseObject(responseJson);
        return response;
    }

    public static String doGetJson(String url, Map<String, String> header) throws Exception {

        HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();

        conn.setConnectTimeout(connectionTimout);
        conn.setReadTimeout(readTimeout);
        conn.setRequestMethod("GET");// 提交模式
        conn.setDoOutput(false);// 是否输入参数
        conn.setRequestProperty("content-type", "application/json");
        if (header != null) {
            for (String key : header.keySet()) {
                String value = header.get(key);
                conn.setRequestProperty(key, value);
            }
        }
        conn.setRequestProperty("Charset", "UTF-8");
        int resultCode = conn.getResponseCode();
        return getString(conn, resultCode);
    }


    private static String getString(HttpURLConnection conn, int resultCode) throws Exception {
        String response;
        if (HttpURLConnection.HTTP_OK == resultCode) {
            StringBuilder sb = new StringBuilder();
            String readLine = new String();
            try (BufferedReader responseReader = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"))) {
                while ((readLine = responseReader.readLine()) != null) {
                    sb.append(readLine).append("\n");
                }
            }
            response = sb.toString();
            conn.disconnect();
        } else {
            conn.disconnect();
            throw new Exception("Http Status Code:" + resultCode);
        }
        return response;
    }
}