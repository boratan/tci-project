package services;

import models.IModel;

public interface ThreadCompleteListener {

    void notifyOfThreadComplete(final Thread thread, final IModel data);
}
