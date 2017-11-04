package com.qiaobei.hmp.support;

/**
 * Created by iceyanbin on 2015-12-20.
 */
public class PatientLoginResult extends Result {
    private Status status;

    public static PatientLoginResult ok(Status status) {
        PatientLoginResult result = new PatientLoginResult();
        result.setSuccess(true);
        result.setStatus(status);
        return result;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public enum Status {
        Passed, Used, NewCard, Invalid
    }


}
