package models;

import java.util.Set;

public class GetOne extends BaseRequest {
    private IModel result;

    public GetOne() {}

    public GetOne(Set<IModel> models) {
        super();
        this.result = (IModel) models.toArray()[0];
    }

    public IModel getResult() {
        return result;
    }
}
