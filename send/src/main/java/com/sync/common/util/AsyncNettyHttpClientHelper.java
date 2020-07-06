package com.sync.common.util;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.asynchttpclient.AsyncCompletionHandler;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.BoundRequestBuilder;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.asynchttpclient.request.body.multipart.Part;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.google.common.base.Charsets;
import com.sync.common.async.GuavaListenableFuture;

/**
 * @author
 * @date 2016.07.05
 */
public class AsyncNettyHttpClientHelper {

	private static final Logger logger = LoggerFactory.getLogger(AsyncNettyHttpClientHelper.class);

	public static final String CONTENT_TYPE_HEADER = "Content-Type";

	public static final String JSON_HEADER_VALUE = "application/json; charset=utf-8";

	public static final Charset DEFAULT_CHARSET = Charsets.UTF_8;

	private static final AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();

	/**
	 * @param asyncHttpClient
	 * @param url
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> get(AsyncHttpClient asyncHttpClient, String url,
			AsyncCompletionHandler<T> handler) {
		return get(asyncHttpClient, url, Collections.emptyMap(), handler);
	}

	/**
	 * @param asyncHttpClient
	 * @param url
	 * @param params
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> get(AsyncHttpClient asyncHttpClient, String url,
			Map<String, String> params, AsyncCompletionHandler<T> handler) {
		logger.debug("get invoke url:{}", url);
		BoundRequestBuilder prepareGet = asyncHttpClient.prepareGet(url);
		if (params != null) {
			for (Map.Entry<String, String> entry : params.entrySet()) {
				prepareGet.addQueryParam(entry.getKey(), entry.getValue());
			}
		}
		return new GuavaListenableFuture<T>(prepareGet.execute(handler));
	}

	/**
	 * @param asyncHttpClient
	 * @param url
	 * @param params
	 * @param headers
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> get(AsyncHttpClient asyncHttpClient, String url,
			Map<String, String> params, Map<String, Object> headers, AsyncCompletionHandler<T> handler) {
		logger.debug("get invoke url:{}", url);
		BoundRequestBuilder prepareGet = asyncHttpClient.prepareGet(url);
		newHeaders(headers, prepareGet);
		newQueryParams(params, prepareGet);
		return new GuavaListenableFuture<T>(prepareGet.execute(handler));
	}

	/**
	 * @param url
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> get(String url, AsyncCompletionHandler<T> handler) {
		return get(asyncHttpClient, url, handler);
	}

	/**
	 * @param url
	 * @param params
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> get(String url, Map<String, String> params,
			AsyncCompletionHandler<T> handler) {
		return get(asyncHttpClient, url, params, handler);
	}

	/**
	 * @param asyncHttpClient
	 * @param url
	 * @param jsonContent
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> postJson(AsyncHttpClient asyncHttpClient, String url, String jsonContent,
			AsyncCompletionHandler<T> handler) {
		logger.debug("post invoke url:{}, content:{}", url, jsonContent);
		BoundRequestBuilder preparePost = asyncHttpClient.preparePost(url);
		preparePost.setHeader(CONTENT_TYPE_HEADER, JSON_HEADER_VALUE);
		preparePost.setBody(jsonContent);
		preparePost.setCharset(DEFAULT_CHARSET);
		return new GuavaListenableFuture<T>(preparePost.execute(handler));
	}

	/**
	 * @param url
	 * @param jsonContent
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> postJson(String url, String jsonContent,
			AsyncCompletionHandler<T> handler) {
		return postJson(asyncHttpClient, url, jsonContent, handler);
	}

	public static <T> com.google.common.util.concurrent.ListenableFuture<T> postUrlEncode(AsyncHttpClient asyncHttpClient, String url,
			byte[] encodeBody, Map<String, Object> headers, AsyncCompletionHandler<T> handler) {
		logger.debug("post invoke url:{}, content:{}", url, encodeBody);
		BoundRequestBuilder preparePost = asyncHttpClient.preparePost(url);
		preparePost.setBody(encodeBody);
		preparePost.setCharset(DEFAULT_CHARSET);
		newHeaders(headers, preparePost);
		return new GuavaListenableFuture<T>(preparePost.execute(handler));
	}

	/**
	 * @param url
	 * @param param
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> post(String url, Map<String, String> param,
			AsyncCompletionHandler<T> handler) {
		return post(asyncHttpClient, url, param, handler);
	}

	/**
	 * @param asyncHttpClient
	 * @param url
	 * @param param
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> post(AsyncHttpClient asyncHttpClient, String url,
			Map<String, String> param, AsyncCompletionHandler<T> handler) {
		return post(asyncHttpClient, url, param, Collections.emptyMap(), handler);
	}

	/**
	 * @param asyncHttpClient
	 * @param url
	 * @param queryParams
	 * @param formParams
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> post(AsyncHttpClient asyncHttpClient, String url,
			Map<String, String> queryParams, Map<String, String> formParams, AsyncCompletionHandler<T> handler) {
		return post(asyncHttpClient, url, queryParams, formParams, Collections.emptyMap(), handler);
	}

	/**
	 * @param url
	 * @param queryParams
	 * @param formParams
	 * @param partParams
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> post(String url, Map<String, String> queryParams,
			Map<String, String> formParams, Map<String, Part> partParams, AsyncCompletionHandler<T> handler) {
		return post(asyncHttpClient, url, queryParams, formParams, partParams, handler);
	}

	/**
	 * @param url
	 * @param param
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> put(String url, Map<String, String> param,
			AsyncCompletionHandler<T> handler) {
		return put(asyncHttpClient, url, param, handler);
	}

	/**
	 * @param asyncHttpClient
	 * @param url
	 * @param param
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> put(AsyncHttpClient asyncHttpClient, String url,
			Map<String, String> param, AsyncCompletionHandler<T> handler) {
		return put(asyncHttpClient, url, param, Collections.emptyMap(), Collections.emptyMap(), handler);
	}

	/**
	 * @param url
	 * @param queryParams
	 * @param formParams
	 * @param partParams
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> put(String url, Map<String, String> queryParams,
			Map<String, String> formParams, Map<String, Part> partParams, AsyncCompletionHandler<T> handler) {
		return put(asyncHttpClient, url, queryParams, formParams, partParams, handler);
	}

	/**
	 * @param asyncHttpClient
	 * @param url
	 * @param queryParams
	 * @param formParams
	 * @param partParams
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> post(AsyncHttpClient asyncHttpClient, String url,
			Map<String, String> queryParams, Map<String, String> formParams, Map<String, Part> partParams, AsyncCompletionHandler<T> handler) {
		return post(asyncHttpClient, url, queryParams, formParams, partParams, Collections.emptyMap(), handler);
	}

	/**
	 * @param asyncHttpClient
	 * @param url
	 * @param queryParams
	 * @param formParams
	 * @param partParams
	 * @param headers
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> post(AsyncHttpClient asyncHttpClient, String url,
			Map<String, String> queryParams, Map<String, String> formParams, Map<String, Part> partParams, Map<String, Object> headers,
			AsyncCompletionHandler<T> handler) {
		logger.debug("post invoke url:{}", url);
		BoundRequestBuilder preparePost = asyncHttpClient.preparePost(url);
		preparePost.setCharset(DEFAULT_CHARSET);
		newParams(headers, queryParams, formParams, partParams, preparePost);
		return new GuavaListenableFuture<T>(preparePost.execute(handler));
	}

	/**
	 * @param asyncHttpClient
	 * @param url
	 * @param bodyFile
	 * @param headers
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> post(AsyncHttpClient asyncHttpClient, String url, File bodyFile,
			Map<String, Object> headers, AsyncCompletionHandler<T> handler) {
		logger.debug("post invoke url:{}", url);
		BoundRequestBuilder preparePost = asyncHttpClient.preparePost(url);
		preparePost.setCharset(DEFAULT_CHARSET);
		preparePost.setBody(bodyFile);
		newHeaders(headers, preparePost);
		return new GuavaListenableFuture<T>(preparePost.execute(handler));
	}

	/**
	 * @param url
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> delete(String url, AsyncCompletionHandler<T> handler) {
		return delete(asyncHttpClient, url, handler);
	}

	/**
	 * @param asyncHttpClient
	 * @param url
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> delete(AsyncHttpClient asyncHttpClient, String url,
			AsyncCompletionHandler<T> handler) {
		return delete(asyncHttpClient, url, Collections.emptyMap(), Collections.emptyMap(), handler);
	}

	/**
	 * @param asyncHttpClient
	 * @param url
	 * @param queryParams
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> delete(AsyncHttpClient asyncHttpClient, String url,
			Map<String, String> queryParams, AsyncCompletionHandler<T> handler) {
		return delete(asyncHttpClient, url, queryParams, Collections.emptyMap(), handler);
	}

	/**
	 * @param asyncHttpClient
	 * @param url
	 * @param queryParams
	 * @param headers
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> delete(AsyncHttpClient asyncHttpClient, String url,
			Map<String, String> queryParams, Map<String, Object> headers, AsyncCompletionHandler<T> handler) {
		logger.debug("delete invoke url:{}", url);
		BoundRequestBuilder prepareDelete = asyncHttpClient.prepareDelete(url);
		prepareDelete.setCharset(DEFAULT_CHARSET);
		newParams(headers, queryParams, Collections.emptyMap(), Collections.emptyMap(), prepareDelete);
		return new GuavaListenableFuture<T>(prepareDelete.execute(handler));
	}

	/**
	 * @param asyncHttpClient
	 * @param url
	 * @param queryParams
	 * @param formParams
	 * @param partParams
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> put(AsyncHttpClient asyncHttpClient, String url,
			Map<String, String> queryParams, Map<String, String> formParams, Map<String, Part> partParams, AsyncCompletionHandler<T> handler) {
		return put(asyncHttpClient, url, queryParams, formParams, partParams, Collections.emptyMap(), handler);
	}

	/**
	 * @param asyncHttpClient
	 * @param url
	 * @param queryParams
	 * @param formParams
	 * @param partParams
	 * @param headers
	 * @param handler
	 * @param <T>
	 * @return
	 */
	public static <T> com.google.common.util.concurrent.ListenableFuture<T> put(AsyncHttpClient asyncHttpClient, String url,
			Map<String, String> queryParams, Map<String, String> formParams, Map<String, Part> partParams, Map<String, Object> headers,
			AsyncCompletionHandler<T> handler) {
		logger.debug("put invoke url:{}", url);
		BoundRequestBuilder preparePut = asyncHttpClient.preparePut(url);
		preparePut.setCharset(DEFAULT_CHARSET);
		newParams(headers, queryParams, formParams, partParams, preparePut);
		return new GuavaListenableFuture<T>(preparePut.execute(handler));
	}

	public static void newParams(Map<String, Object> headers, Map<String, String> queryParams, Map<String, String> formParams,
			Map<String, Part> partParams, BoundRequestBuilder requestBuilder) {
		newHeaders(headers, requestBuilder);
		newQueryParams(queryParams, requestBuilder);
		for (Map.Entry<String, String> entry : formParams.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			logger.debug("param key:{}, value:{}", key, value);
			requestBuilder.addFormParam(key, value);
		}
		for (Map.Entry<String, Part> entry : partParams.entrySet()) {
			String key = entry.getKey();
			Part part = entry.getValue();
			logger.debug("param key:{}, value class:{}", key, part.getClass());
			requestBuilder.addBodyPart(part);
		}
	}

	public static void newQueryParams(Map<String, String> queryParams, BoundRequestBuilder requestBuilder) {
		for (Map.Entry<String, String> entry : queryParams.entrySet()) {
			String key = entry.getKey();
			String value = entry.getValue();
			logger.debug("param key:{}, value:{}", key, value);
			requestBuilder.addQueryParam(key, value);
		}
	}

	public static void newHeaders(Map<String, Object> headers, BoundRequestBuilder requestBuilder) {
		for (Map.Entry<String, Object> entry : headers.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (value != null && Collection.class.isAssignableFrom(value.getClass())) {
				Collection<String> collection = (Collection) value;
				for (String o : collection) {
					requestBuilder.addHeader(key, o);
				}
			} else {
				requestBuilder.setHeader(key, String.valueOf(value));
			}
		}
	}

	public static String createLinkString(Map<String, String> params, List<String> continueParams) {
		return createLinkString(params, continueParams, "&");
	}

	/**
	 * 
	 * @param params
	 * @param continueParams
	 * @return
	 */
	public static String createLinkString(Map<String, String> params, List<String> continueParams, String split) {

		List<String> keys = new ArrayList<String>(params.keySet());
		Collections.sort(keys);
		StringBuffer prestr = new StringBuffer();
		boolean t = !CollectionUtils.isEmpty(continueParams);
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			if (t && continueParams.contains(key)) {
				continue;
			}
			String value = params.get(key);
			prestr.append(key).append("=").append(value);
			if (i != keys.size() - 1) {// 拼接时，不包括最后一个&字符
				prestr.append(split);
			}
		}
		return prestr.toString();
	}
}
