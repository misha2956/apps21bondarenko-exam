package json;

/**
 * Created by Andrii_Rodionov on 1/4/2017.
 */
public class JsonBoolean extends Json {
    private final boolean val;

    public JsonBoolean(Boolean val) {
        this.val = val;
    }

    @Override
    public String toJson() {
        return val ? "true" : "false";
    }
}
