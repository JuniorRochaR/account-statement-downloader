package com.ebanx.lambda;

import static org.mockito.MockitoAnnotations.openMocks;

import org.junit.jupiter.api.BeforeEach;

public abstract class BaseTest {

    @BeforeEach
    protected void init() {
        openMocks(this);
    }
}
