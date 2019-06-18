package services;

import models.*;
import java.util.*;
import java.util.concurrent.*;

public class ThreadService{
    private Set<FutureTask<IModel>> tasks;
    private Set<FutureTask<IModel>> toRemove;
    private Set<IModel> models;
    private ExecutorService threadPool;

    /**
     * Constructor of ThreadService class.
     */
    public ThreadService() {
        threadPool = Executors.newFixedThreadPool(10);
        tasks = new HashSet<>();
        models = new HashSet<>();
        toRemove = new HashSet<>();
    }

    /**
     * Starts scraping of the provided url by creating a new Scraper and future task.
     * It then adds the future task to the thread pool and list of future tasks.
     * @param urlToScrape URL that need to be scraped.
     * @return Pair of the info for the request and the set of models scraped.
     */
    public void scrape(EnrichedUrl urlToScrape){
        if (urlToScrape == null){
            throw new IllegalArgumentException();
        }
        FutureTask<IModel> task = new FutureTask<>(new Scraper(urlToScrape));
        tasks.add(task);
        threadPool.submit(task);
    }

    /**
     * Checks which tasks are completed and adds the results to the return set.
     * @return The set of IModel found in the whole website.
     */
    public Set<IModel> checkFutureTasks(){
        for (FutureTask task : tasks) {
            IModel result = getFromTask(task);
            models.add(result);
        }
        threadPool.shutdown();
        return models;
    }

    /**
     * Checks which tasks are completed and adds a single result to the return set.
     * @param type Type of IModel. Can be null.
     * @param details Specific detail that should be present in the model. Can be null.
     * @return The set of the first IModel found that meets the criteria provided.
     */
    public Set<IModel> checkFutureTasksForSpecificItem(String type, String details) {
        for (FutureTask<IModel> task : tasks) {
            IModel result = this.getFromTask(task);
            if (result != null) {
                if (type != null && details != null) {
                    if (checkIfAnyFieldInAModelIsEqualToArgumentAndType(result, details, type.toLowerCase())) {
                        models.add(result);
                        threadPool.shutdownNow();
                        return models;
                    }
                } else if (details != null) {
                    if (checkIfAnyFieldInAModelIsEqualToArgument(result, details)) {
                        models.add(result);
                        threadPool.shutdownNow();
                        return models;
                    }
                } else if (type != null) {
                    if (checkIfAnyFieldInAModelIsEqualToType(result, type.toLowerCase())) {
                        models.add(result);
                        threadPool.shutdownNow();
                        return models;
                    }
                }
                toRemove.add(task);
            }
        }
        tasks.removeAll(toRemove);
        return models;
    }

    /**
     * Thread pool shutdown for usage outside of the class.
     */
    public void shutdownPool(){
        threadPool.shutdown();
    }

    /**
     * Gets all future tasks currently running in the ThreadService.
     */
    public Set<FutureTask<IModel>> getTasks() {
        return tasks;
    }

    /**
     * Gets the result of the specified future task.
     * @param task The task for which the result should be retrieved/
     * @return The result of the task.
     */
    private IModel getFromTask(FutureTask<IModel> task){
        try {
            return task.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean checkIfAnyFieldInAModelIsEqualToArgumentAndType(IModel model, String argument, String type) {
        if (type.equals("book") && model instanceof Book) {
            return this.checkBook((Book)model, argument);
        } else if (type.equals("movie") && model instanceof Movie) {
            return this.checkMovie((Movie)model, argument);
        } else if (type.equals("music") && model instanceof Music) {
            return this.checkMusic((Music)model, argument);
        }
        return false;
    }

    private boolean checkIfAnyFieldInAModelIsEqualToArgument(IModel model, String argument) {
        if (model instanceof Book) {
            return this.checkBook((Book)model, argument);
        } else if (model instanceof Movie) {
            return this.checkMovie((Movie)model, argument);
        } else if (model instanceof Music) {
            return this.checkMusic((Music)model, argument);
        }
        return false;
    }

    private boolean checkIfAnyFieldInAModelIsEqualToType(IModel model, String type) {
        if (type.equals("book") && model instanceof Book) {
            return true;
        } else if (type.equals("movie") && model instanceof Movie) {
            return true;
        } else if (type.equals("music") && model instanceof Music) {
            return true;
        }
        return false;
    }

    private boolean checkMusic(Music model, String argument){
        if (model.getName().equals(argument))
            return true;
        else if (model.getArtist().equals(argument))
            return true;
        else if (model.getFormat().equals(argument))
            return true;
        else return (model.getYear().toString().equals(argument));
    }

    private boolean checkMovie(Movie model, String argument){
        if (model.getName().equals(argument))
            return true;
        else if (model.getGenre().equals(argument))
            return true;
        else if (model.getDirector().equals(argument))
            return true;
        else if (model.getFormat().equals(argument))
            return true;
        else if (model.getWriters().contains(argument))
            return true;
        else if (model.getStars().contains(argument))
            return true;
        else return (model.getYear().toString().equals(argument));
    }

    private boolean checkBook(Book model, String argument){
        if (model.getName().equals(argument))
            return true;
        else if (model.getGenre().equals(argument))
            return true;
        else if (model.getPublisher().equals(argument))
            return true;
        else if (model.getFormat().equals(argument))
            return true;
        else if (model.getIsnb().equals(argument))
            return true;
        else if (model.getAuthors().contains(argument))
            return true;
        else return (model.getYear().toString().equals(argument));
    }
}
