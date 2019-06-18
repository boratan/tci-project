package serializers;

import com.google.gson.Gson;
import models.RequestInfo;

public class RequestInfoSerializer implements IGenericSerializer<RequestInfo> {
    private Gson gson;

    public RequestInfoSerializer() {
        gson = new Gson();
    }

    /**
     * Serializes the RequestInfo model to JSON format.
     * @param object
     * @return
     */
    @Override
    public String serializeToJson(RequestInfo object) {
        if(object != null) {
            return gson.toJson(object);
        }
        throw new IllegalArgumentException();
    }

    /**
     * De-serializes JSON string to RequestInfo model.
     * @param json
     * @return
     */
    @Override
    public RequestInfo deserializeFromJson(String json) {
        if(json != null && !json.equals("")) {
            return gson.fromJson(json, RequestInfo.class);
        }
        throw new IllegalArgumentException();
    }
}
