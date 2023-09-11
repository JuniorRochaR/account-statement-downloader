package com.ebanx.lambda.internal;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.jboss.logging.Logger;

public abstract class Executor<T, U> {
    private static final Logger LOGGER = Logger.getLogger(Executor.class);

    private List<Command<T, U>> actions = new ArrayList<>();

    @PostConstruct
    public void init() {
        setUp();
    }

    public void run(T request, U response) {
        actions.forEach(action -> {
            LOGGER.debugf("RUNNING ACTION {%s}", action.getClass().getName());
            action.execute(request, response);
            LOGGER.debugf("ACTION {%s} FINISHED", action.getClass().getName());
        });
    }

    protected void add(Command<T, U> action) {
        actions.add(action);
    }

    protected abstract void setUp();
}
