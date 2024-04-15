package com.capgemini.catedrauv.girls4stem.application;

import com.capgemini.catedrauv.girls4stem.infrastructure.database.MariaDbRepository;

public class DatabaseService {
    private static MariaDbRepository instance;

    public static MariaDbRepository getInstance() {
        if (instance == null) {
            instance = new MariaDbRepository();
        }

        return instance;
    }
}
