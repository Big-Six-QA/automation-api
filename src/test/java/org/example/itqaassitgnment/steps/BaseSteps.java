package org.example.itqaassitgnment.steps;

import org.example.itqaassitgnment.config.PlaywrightConfig;
import org.example.itqaassitgnment.service.BookApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BaseSteps {
    protected static final Logger logger = LoggerFactory.getLogger(BaseSteps.class);
    protected final PlaywrightConfig playwrightConfig;
    protected final BookApiService bookApiService;

    public BaseSteps() {
        this.playwrightConfig = PlaywrightConfig.getInstance();
        this.bookApiService = new BookApiService();
    }
}