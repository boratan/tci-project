package serializers;

import com.google.gson.Gson;
import models.RequestInfo;

public class RequestInfoSerializer implements IGenericSerializer<RequestInfo> {
    private Gson gson;

    public RequestInfoSerializer() {
        gson = new Gson();
    }

    public Gson getGson() {
        return gson;
    }

    /**
     * Serializes the RequestInfo model to JSON format.
     * @param object
     * @return
     */
    @Override
    public String serializeToJson(RequestInfo object) {
        return null;
    }

    /**
     * De-serializes JSON string to RequestInfo model.
     * @param json
     * @return
     */
    @Override
    public RequestInfo deserializeFromJson(String json) {
        return null;
    }
}
