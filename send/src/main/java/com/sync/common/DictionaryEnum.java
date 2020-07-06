package com.sync.common;

public enum DictionaryEnum {
	平台简写(0), 快递公司(0), 订单类型(0),
	// 仓库
	ATTRSHIPLOCATION(0),
	// T1城市
	ATTRCITYT1(0),
	// 平台类型
	PLATFORMTYPE(0),
	
	// 省份
	PROVINCE(1),

	;

	/**
	 * 1 sql 里面的基础数据表 2 sql 里面的非基础数据表 3 自定义枚举
	 */
	public final int type;

	DictionaryEnum(int type) {
		this.type = type;
	}
}
