package com.hoohacks.healthmanagement.utils;

public class Result<T> {
	//返回码
    private int resultCode;
    
    //返回信息
    private String message;
    
    //返回数据
    private T data;

    public Result() {
    }

    public Result(int resultCode, String message) {
        this.resultCode = resultCode;
        this.message = message;
    }

	public int getResultCode() {
		return resultCode;
	}

	public String getMessage() {
		return message;
	}

	public T getData() {
		return data;
	}

	public void setResultCode(int resultCode) {
		this.resultCode = resultCode;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setData(T data) {
		this.data = data;
	}

    @Override
    public String toString() {
        return "Result{" +
                "resultCode=" + resultCode +
                ", message='" + message + '\'' +
                ", data=" + data +
                '}';
    }
}
