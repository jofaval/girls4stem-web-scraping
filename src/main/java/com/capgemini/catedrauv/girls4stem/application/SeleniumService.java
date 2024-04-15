package com.capgemini.catedrauv.girls4stem.application;

import com.capgemini.catedrauv.girls4stem.infrastructure.SeleniumRepository;

public class SeleniumService {
    public static SeleniumRepository driver;

    public static SeleniumRepository getInstance() {
        if (driver == null) {
            driver = new SeleniumRepository();
        }

        return driver;
    }
}