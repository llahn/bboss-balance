package com.hpe.bboss.core.entity;

import org.apache.commons.lang.StringUtils;

import com.hpe.bboss.core.entity.resultenum.ResultStatus;

public class CommonResult<T> {
    private int status;
    private String message;
    private int pageNo;
    private int pageSize;
    private int totalCount;
    private T body;

    public CommonResult() {
        super();
    }

    public CommonResult(CommonResult<?> result) {
        super();
        this.status = result.status;
        this.message = result.message;
    }

    public CommonResult(ResultStatus status) {
        this();
        this.status = status.status;
        this.message = status.message;
    }

    public CommonResult(ResultStatus status, String supplement) {
        this();
        this.status = status.status;
        this.message = StringUtils.isBlank(supplement) ? status.message : supplement;
    }

    @Override
    public String toString() {
        return "CommonResult [status=" + status + ", message=" + message + ", pageNo=" + pageNo + ", pageSize="
                + pageSize + ", totalCount=" + totalCount + ", body=" + body + "]";
    }

    public int getStatus() {
        return this.status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getPageNo() {
        return this.pageNo;
    }

    public CommonResult<T> setPageNo(int pageNo) {
        this.pageNo = pageNo;
        return this;
    }

    public int getPageSize() {
        return this.pageSize;
    }

    public CommonResult<T> setPageSize(int pageSize) {
        this.pageSize = pageSize;
        return this;
    }

    public int getTotalCount() {
        return this.totalCount;
    }

    public CommonResult<T> setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        return this;
    }

    public T getBody() {
        return this.body;
    }

    public CommonResult<T> setBody(T body) {
        this.body = body;
        return this;
    }
}