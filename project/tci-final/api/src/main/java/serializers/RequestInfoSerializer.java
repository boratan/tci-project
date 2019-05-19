package serializers;

import models.RequestInfo;

public class RequestInfoSerializer implements IGenericSerializer<RequestInfo> {

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
