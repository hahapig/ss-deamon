package com.taihe.schedule.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.log4j.Logger;

public class HttpRequest {
	private static final Logger log=Logger.getLogger(HttpRequest.class);
	
    private HttpClient httpClient;
    private HttpUriRequest request;
    private HttpResponse response;
    private List<NameValuePair> formData;

    public HttpRequest() {
        httpClient = HttpClients.createDefault();
        

    }

    public HttpRequest Get(String url) {
        request = new HttpGet(url);
        log.debug("HttpClient发送请求： " + url + " [GET]");
        return this;
    }

    public HttpRequest Post(String url) {
        request = new HttpPost(url);
        formData = new ArrayList<NameValuePair>();
        request.setHeader(HTTP.CONTENT_ENCODING, "UTF-8");
        log.info("HttpClient发送请求： " + url + " [POST]");
        return this;
    }

    public HttpRequest addHeader(String key, String value) {
        request.addHeader(key, value);
        return this;
    }

    public HttpRequest addAllHeaders(Header[] headers) {
        request.setHeaders(headers);
        return this;
    }

    public HttpRequest addFormData(String key, String value) {
        if (formData != null) {
            formData.add(new BasicNameValuePair(key, value));
            log.debug("新增参数-> " + key + " : " + value);
        } else {
            throw new NullPointerException("addFormData() is only for Post() method!");
        }
        return this;
    }

    public HttpRequest execute() {
        try {
            if (formData != null) {
                ((HttpPost) request).setEntity(new UrlEncodedFormEntity(formData,"UTF-8"));
            }

            response = httpClient.execute(request);
        } catch (Exception e) {
            log.error("访问远程服务出错",e);
        }
        return this;
    }

    public String returnContent() {
        String responseInfo = "";
        if (response != null) {
            try (InputStream is = response.getEntity().getContent()) {
                int c;
                while ((c = is.read()) != -1) {
                    responseInfo += (char) c;
                }

                responseInfo = new String(responseInfo.getBytes("ISO-8859-1"), "UTF-8");
            } catch (Exception e) {
                log.error("",e);
            }
        } else {
            throw new NullPointerException("must do execute() before!");
        }
        log.debug("获取返回结果：" + responseInfo);
        return responseInfo;
    }

    public Header returnHeader(String headerName) {
        Header result = null;
        if (response != null) {
            Header[] headers = response.getAllHeaders();
            for (Header header : headers) {
                if (header.getName().equals(headerName)) {
                    result = header;
                    break;
                }
            }
        } else {
            throw new NullPointerException("must do execute() before!");
        }
        return result;
    }

    public Header[] returnAllHeader() {
        Header[] headers;
        if (response != null) {
            headers = response.getAllHeaders();
        } else {
            throw new NullPointerException("must do execute() before!");
        }
        return headers;
    }
}
