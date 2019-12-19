package sc.whorl.system.commons;

public class SenUnitException extends RuntimeException {
    private String code;

    public SenUnitException(String code, String message) {
        super(message);
        this.code = code;
    }

    public SenUnitException(int code, String message) {
        super(message);
        this.code = String.valueOf(code);
    }

    public SenUnitException(String code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public SenUnitException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public String getCode() {
        return this.code;
    }
}
