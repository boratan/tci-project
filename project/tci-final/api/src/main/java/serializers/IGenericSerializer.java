package serializers;

public interface IGenericSerializer<T> {
    String serializeToJson(T object);
    T deserializeFromJson(String json);
}
