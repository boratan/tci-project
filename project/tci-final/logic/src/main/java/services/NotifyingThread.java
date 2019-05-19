package services;

public abstract class NotifyingThread extends Thread{

    /**
     * Adds a new custom listener to a local Set<>.
     * @param listener
     */
    public final void addListener(final ThreadCompleteListener listener){

    }

    /**
     * Removes a custom listener from a local Set<>.
     * @param listener
     */
    public final void removeListener(final ThreadCompleteListener listener){

    }

    /**
     * Notifies a listener that a thread has completed a task.
     */
    private final void notifyListeners(){
    }

    /**
     * Starts the method startScraper() on a new thread.
     * When complete, notifies a listener that the thread is complete.
     */
    @Override
    public final void run(){

    }

    /**
     * Starts a scraper task
     */
    public abstract void startScraper();
}
