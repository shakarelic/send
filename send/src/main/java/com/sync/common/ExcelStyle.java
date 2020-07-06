package com.sync.common;

import java.util.ArrayList;
import java.util.List;

public enum ExcelStyle {
	BASE_STYLE, COLOR_STYLE, BORDE_RRIGHT_STYLE;

	public static List<String> names() {
		List<String> list = new ArrayList<>();
		ExcelStyle[] s = ExcelStyle.values();
		for (ExcelStyle i : s) {
			list.add(i.name());
		}
		return list;
	}
}
