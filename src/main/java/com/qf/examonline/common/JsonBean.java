package com.qf.examonline.common;

import lombok.Data;

@Data
public class JsonBean<T> {

	private int code;//0:代表成功
	private T data;

	public JsonBean() {
	}

	public JsonBean(int code, T data) {
		this.code = code;
		this.data = data;
	}
}
