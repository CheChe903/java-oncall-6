package oncall.utils.exception;

public enum ErrorMessage {

    ERROR("에러"),
    MONTH_OUT_OF_RANGE("월은 1부터 12 사이여야 합니다."),
    WEEK_NOT_AVAILABLE("요일이 올바르지 않습니다."),
    HAVE_TO_INT("숫자로 입력해야 합니다."),
    NOT_AVAILABLE_WORKER("유효하지 근무자 입력 값입니다. 다시 입력해 주세요."),
    WORKER_SIZE_OUT_OF_RANGE("근무자는 5명 이상, 35명 이하여야 합니다."),
    WORKER_NAME_OUT_OF_RANGE("근무자 이름은 5자 이하여야 합니다.");

    private static final String PREFIX = "[ERROR] ";
    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return PREFIX + message;
    }
}
