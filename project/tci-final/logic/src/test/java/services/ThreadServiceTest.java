package services;

import org.junit.Test;

import static org.junit.Assert.*;

public class ThreadServiceTest {
    @Test
    public void CheckThatScrapeMethodThrowsExceptionWhenUrlsSetIsEmpty(){}

    @Test
    public void CheckThatScrapeMethodCreatesFutureTasksWhenUrlsSetIsNotEmpty(){}

    @Test
    public void CheckThatScrapeMethodReturnsPairOfRequestInfoAndSetOfScrapedModels(){}

    @Test
    public void CheckThatScrapeMethodStartsExecutesFutureTasksForEachUrlInTheUrlsSet(){}

    @Test
    public void CheckThatScrapeMethodInvokesCheckFutureTasksMethodWhenTypeAndDetailsParametersAreNull(){}

    @Test
    public void CheckThatScrapeMethodInvokesCheckFutureTasksForSpecificItemWhenTypeAndDetailsParametersAreNotNull(){}

    @Test
    public void CheckThatScrapeMethodInvokesCheckFutureTasksForSpecificItemWhenDetailsParameterIsNotNull(){}

    @Test
    public void CheckThatScrapeMethodInvokesCheckFutureTasksForSpecificItemWhenTypeParameterIsNotNull(){}
}