package search.com.ctest.model;


public class ErrorObject extends  Throwable {
    String errorCode;
    String errorMessage;

    private ErrorObject(Builder builder) {
        errorCode = builder.errorCode;
        errorMessage = builder.errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public static final class Builder {
        private String errorCode;
        private String errorMessage;

        public Builder() {
        }

        public Builder errorCode(String val) {
            errorCode = val;
            return this;
        }

        public Builder errorMessage(String val) {
            errorMessage = val;
            return this;
        }

        public ErrorObject build() {
            return new ErrorObject(this);
        }
    }
}
