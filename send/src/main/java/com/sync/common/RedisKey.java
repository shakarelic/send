package com.sync.common;

public class RedisKey {

	public static String getCheckAllDateSalesSend(String date) {
		return CommonConstants.PROJECT + "_getCheckAllDateSalesSend" + date;
	}

	public static String getCheckOpdDateSalesSend(String date) {
		return CommonConstants.PROJECT + "_getCheckOpdDateSalesSend" + date;
	}

	// 每个时间点发送
	public static String getAllDateSalesSend(String date) {
		return CommonConstants.PROJECT + "_getAllDateSalesSend" + date;
	}

	// 部分人只在最后时间发送
	public static String getAllEndDateSalesSend(String date) {
		return CommonConstants.PROJECT + "_getAllEndDateSalesSend" + date;
	}

	public static String getOpdUpload(String date) {
		return CommonConstants.PROJECT + "_getOpdUpload" + date;
	}

	public static String getGroupPlatformTree() {
		return CommonConstants.PROJECT + "_getGroupPlatformTree";
	}

	public static String getGroupPlatformFlat() {
		return CommonConstants.PROJECT + "_getGroupPlatformFlat";
	}

	public static String getSpuSkuTree() {
		return CommonConstants.PROJECT + "_getSpuSkuTree";
	}

	public static String getSpuSkuFlat() {
		return CommonConstants.PROJECT + "_getSpuSkuFlat";
	}

	public static String getSalesJxc(String key) {
		return CommonConstants.PROJECT + "_getSalesJxc_" + key;
	}

	public static String getCard(String key) {
		return CommonConstants.PROJECT + "_getCard_" + key;
	}
}
