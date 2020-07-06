//package com.sync.task.utils;
//
//import java.util.Date;
//import java.util.Map;
//import java.util.concurrent.ExecutionException;
//import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeoutException;
//
//import org.apache.commons.codec.digest.DigestUtils;
//import org.asynchttpclient.AsyncCompletionHandler;
//import org.asynchttpclient.AsyncHttpClient;
//import org.asynchttpclient.DefaultAsyncHttpClient;
//import org.asynchttpclient.DefaultAsyncHttpClientConfig;
//import org.asynchttpclient.Response;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.sync.common.ErrorCode;
//import com.sync.common.util.AsyncNettyHttpClientHelper;
//import com.homestead.common.util.bean.JacksonHelper;
//import com.sync.common.util.MyUtils;
//import com.sync.task.application.TaskConfiguration;
//
//public class ImeiEsaHttpUtils {
//
//	final static Logger LOGGER = LoggerFactory.getLogger(ImeiEsaHttpUtils.class);
//
//	static AsyncHttpClient asyncHttpClient;
//
//	static {
//		asyncHttpClient = new DefaultAsyncHttpClient(new DefaultAsyncHttpClientConfig.Builder().setMaxRequestRetry(2).setConnectTimeout(60000)
//				.setReadTimeout(4500000).setRequestTimeout(6000000).build());
//	}
//
//	/**
//	 * 
//	 * @param beginTime
//	 * @param secret
//	 * @param url
//	 * @return
//	 * @throws Exception
//	 */
//	public static JsonNode getOnline(Map<String, String> params, String url, TaskConfiguration configuration) throws Exception {
//		Date date = new Date();
//		MyUtils.addMap(params, "timestamp", String.valueOf(date.getTime() / 1000), "app_id", configuration.getImeiItAppId());
//
//		StringBuffer strB = new StringBuffer(url).append("\n")
//				.append(AsyncNettyHttpClientHelper.createLinkString(params, MyUtils.createList("sign"), "\n"));
//		strB.append("\n").append(configuration.getImeiItSecret());
//		params.put("sign", DigestUtils.md5Hex(strB.toString()));
//		JsonNode jn = null;
//		LOGGER.info("电子保卡获取中 param{}", params);
//		try {
//			jn = AsyncNettyHttpClientHelper.get(asyncHttpClient, configuration.getImeiItApi() + url, params, new AsyncCompletionHandler<JsonNode>() {
//				@Override
//				public JsonNode onCompleted(Response response) throws Exception {
//					String responseBody = response.getResponseBody();
//					JsonNode jn = JacksonHelper.readTree(responseBody);
//					LOGGER.info("请求 电子保卡{},  backCode{} ", params, jn.findPath("response").findPath("code").asText("200"));
//					return JacksonHelper.readTree(responseBody);
//				}
//			}).get(6000, TimeUnit.SECONDS);
//		} catch (InterruptedException | ExecutionException | TimeoutException e) {
//			jn = JacksonHelper.readTree(
//					JacksonHelper.writeValueAsString(MyUtils.createHashMap("errcode", ErrorCode.INTERNAL_ERROR.CODE, "errmsg", e.getMessage())));
//			LOGGER.error("http get exception:" + e.toString() + ", info:" + e.getMessage());
//		}
//
//		return jn;
//	}
//
//}
