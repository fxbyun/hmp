package com.qiaobei.hmp.support;

/**
 * Created by iceyanbin on 2015-12-20.
 */
public class PatientLoginResult extends Result {
    public enum Status {
        Passed, Used, NewCard, Invalid
    }

    private Status status;

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public static PatientLoginResult ok(Status status) {
        PatientLoginResult result = new PatientLoginResult();
        Result result1 = result.setSuccess(true);
        result.setStatus(status);
        return result;
    }


}
