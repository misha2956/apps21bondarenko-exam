package json;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Created by Andrii_Rodionov on 1/3/2017.
 */
public class JsonObject extends Json {
    private Map<String, Json> body = new HashMap<>();

    public JsonObject(JsonPair... jsonPairs) {
        for (JsonPair jsonPair : jsonPairs) {
            body.put(jsonPair.key, jsonPair.value);
        }
    }

    @Override
    public String toJson() {
        StringBuilder jsonStr = new StringBuilder();

        for (Map.Entry<String, Json> entry : body.entrySet()) {
            jsonStr.append("'").append(entry.getKey()).append("'");
            jsonStr.append(": ");
            jsonStr.append(entry.getValue().toJson());
            jsonStr.append(", ");
        }
        if (jsonStr.length() >= 2) {
            jsonStr.delete(jsonStr.length() - 2, jsonStr.length());
        }

        return "{" + jsonStr + "}";
    }

    public void add(JsonPair jsonPair) {
        body.put(jsonPair.key, jsonPair.value);
    }

    public Json find(String name) {
        return body.get(name);
    }

    public JsonObject projection(String... names) {
        JsonObject res = new JsonObject();
        for (String name : names) {
            Json val = find(name);
            if (val != null) {
                res.add(new JsonPair(name, val));
            }
        }
        return res;
    }
}
