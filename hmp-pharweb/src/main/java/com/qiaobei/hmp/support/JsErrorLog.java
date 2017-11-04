package com.qiaobei.hmp.support;

/**
 * js 错误dto
 *
 * @author weiyang
 */
public class JsErrorLog {
    // 错误信息
    private String errorMessage;
    // 出错文件
    private String scriptURI;
    // 出错行号
    private Integer lineNumber;
    // 出错列号
    private Integer columnNumber;

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getScriptURI() {
        return scriptURI;
    }

    public void setScriptURI(String scriptURI) {
        this.scriptURI = scriptURI;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public Integer getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(Integer columnNumber) {
        this.columnNumber = columnNumber;
    }

}
