package silverdragon.async;

public enum AsyncEnum {
    READY("ready"), RUNNING("running"), SUCCESS("success"), FAILED("failed");

    private final String value;
    AsyncEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
