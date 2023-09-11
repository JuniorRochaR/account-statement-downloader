package com.ebanx.lambda.internal;

public interface Command<T, U> {
    void execute(T request, U response);
}
