package com.sync.task.application;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sync.common.application.BaseConfiguration;

public class Configuration extends BaseConfiguration {

	@JsonProperty("jdbc4.url")
	private String jdbc4Url;
	@JsonProperty("jdbc4.username")
	private String jdbc4Username;
	@JsonProperty("jdbc4.password")
	private String jdbc4Password;
	@JsonProperty("jdbc4.driver")
	private String jdbc4Driver;

	@JsonProperty("file.store.path")
	private String fileStorePath;

	@JsonProperty("date.day")
	private Integer dateDay;

	@JsonProperty("jst.url")
	private String jstUrl;
	@JsonProperty("jst.appKey")
	private String jstAppKey;
	@JsonProperty("jst.secret")
	private String jstSecret;
	@JsonProperty("jst.apiVersion")
	private String jstApiVersion;
	@JsonProperty("jst.tm.session")
	private String jstTmSession;
	@JsonProperty("jst.fx.session")
	private String jstFxSession;

	@JsonProperty("imei.fx.upload.get")
	private String imeiFxUploadGet;
	@JsonProperty("imei.fx.sale.get")
	private String imeiFxSaleGet;

	@JsonProperty("imei.it.secret")
	private String imeiItSecret;
	@JsonProperty("imei.it.app.id")
	private String imeiItAppId;
	@JsonProperty("imei.it.api")
	private String imeiItApi;
	@JsonProperty("imei.online.url")
	private String imeiOnlineUrl;
	@JsonProperty("imei.send.url")
	private String imeiSendUrl;

	@JsonProperty("bd.web.static.total")
	private String bdWebStaticTotal;
	@JsonProperty("bd.web.static.platform")
	private String bdWebStaticPlatform;
	@JsonProperty("bd.web.static.goods")
	private String bdWebStaticGoods;
	@JsonProperty("bd.web.static.export")
	private String bdWebStaticExport;
	@JsonProperty("bd.web.utmsource.export")
	private String bdWebUtmsourceExport;

	@JsonProperty("eco.url")
	private String ecoUrl;
	@JsonProperty("eco.app.token")
	private String ecoAppToken;
	@JsonProperty("eco.app.id")
	private String ecoAppId;

	public Integer getDateDay() {
		return dateDay;
	}

	public void setDateDay(Integer dateDay) {
		this.dateDay = dateDay;
	}

	public String getJstUrl() {
		return jstUrl;
	}

	public String getJstAppKey() {
		return jstAppKey;
	}

	public String getJstSecret() {
		return jstSecret;
	}

	public String getJstApiVersion() {
		return jstApiVersion;
	}

	public String getJstTmSession() {
		return jstTmSession;
	}

	public String getJstFxSession() {
		return jstFxSession;
	}

	public void setJstUrl(String jstUrl) {
		this.jstUrl = jstUrl;
	}

	public void setJstAppKey(String jstAppKey) {
		this.jstAppKey = jstAppKey;
	}

	public void setJstSecret(String jstSecret) {
		this.jstSecret = jstSecret;
	}

	public void setJstApiVersion(String jstApiVersion) {
		this.jstApiVersion = jstApiVersion;
	}

	public void setJstTmSession(String jstTmSession) {
		this.jstTmSession = jstTmSession;
	}

	public void setJstFxSession(String jstFxSession) {
		this.jstFxSession = jstFxSession;
	}

	public String getFileStorePath() {
		return fileStorePath;
	}

	public void setFileStorePath(String fileStorePath) {
		this.fileStorePath = fileStorePath;
	}

	public String getImeiFxUploadGet() {
		return imeiFxUploadGet;
	}

	public String getImeiFxSaleGet() {
		return imeiFxSaleGet;
	}

	public void setImeiFxUploadGet(String imeiFxUploadGet) {
		this.imeiFxUploadGet = imeiFxUploadGet;
	}

	public void setImeiFxSaleGet(String imeiFxSaleGet) {
		this.imeiFxSaleGet = imeiFxSaleGet;
	}

	public String getImeiOnlineUrl() {
		return imeiOnlineUrl;
	}

	public void setImeiOnlineUrl(String imeiOnlineUrl) {
		this.imeiOnlineUrl = imeiOnlineUrl;
	}

	public String getImeiItSecret() {
		return imeiItSecret;
	}

	public String getImeiItAppId() {
		return imeiItAppId;
	}

	public String getImeiItApi() {
		return imeiItApi;
	}

	public String getImeiSendUrl() {
		return imeiSendUrl;
	}

	public String getBdWebStaticTotal() {
		return bdWebStaticTotal;
	}

	public String getBdWebStaticPlatform() {
		return bdWebStaticPlatform;
	}

	public String getBdWebStaticGoods() {
		return bdWebStaticGoods;
	}

	public String getBdWebStaticExport() {
		return bdWebStaticExport;
	}

	public String getEcoUrl() {
		return ecoUrl;
	}

	public String getEcoAppToken() {
		return ecoAppToken;
	}

	public String getEcoAppId() {
		return ecoAppId;
	}

	public String getBdWebUtmsourceExport() {
		return bdWebUtmsourceExport;
	}

}
