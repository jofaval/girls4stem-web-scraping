package com.capgemini.catedrauv.girls4stem.domain.UseCase;

import java.sql.SQLException;
import java.util.List;

import com.capgemini.catedrauv.girls4stem.domain.models.Expert;

public interface Girls4STEMUseCase {
    public List<Expert> findExperts() throws InterruptedException;

    public void saveToCsv(List<Expert> experts);

    public int saveToDb(List<Expert> experts) throws SQLException;
}
