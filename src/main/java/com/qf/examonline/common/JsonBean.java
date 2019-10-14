package com.qf.examonline.common;

import lombok.Data;

@Data
public class JsonBean<T> {

	private int code;//0:代表成功
	private T info;

	public JsonBean() {
	}

	public JsonBean(int code, T info) {
		this.code = code;
		this.info = info;
	}
}
