package sc.whorl.system.commons;

import java.io.Serializable;

public class MsgResponseBody<T> implements Serializable {
    private static final String SUCCESS = "0";
    private static final String ERROR = "1";
    private String resultCode;
    private T result;

    private MsgResponseBody(String statusCode) {
        this.resultCode = statusCode;
    }

    public static MsgResponseBody success() {
        return new MsgResponseBody("0");
    }

    public static MsgResponseBody error() {
        return new MsgResponseBody("1");
    }

    public static MsgResponseBody error(String errorCode) {
        return new MsgResponseBody(errorCode);
    }

    public String getResultCode() {
        return this.resultCode;
    }

    public MsgResponseBody setResultCode(String resultCode) {
        this.resultCode = resultCode;
        return this;
    }

    public T getResult() {
        return this.result;
    }

    public MsgResponseBody setResult(T result) {
        this.result = result;
        return this;
    }
}