package models;

import java.util.Set;

public class GetOne extends BaseRequest {
    private IModel result;

    // constructor needed for serialization
    public GetOne() {}

    public GetOne(Set<IModel> models) {
        super();
        if(models.isEmpty()) {
            throw new IllegalArgumentException();
        }
        this.result = (IModel) models.toArray()[0];
    }

    public IModel getResult() {
        return result;
    }
}
