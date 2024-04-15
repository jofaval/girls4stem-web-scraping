package com.capgemini.catedrauv.girls4stem.infrastructure.database;

import com.capgemini.catedrauv.girls4stem.domain.models.Expert;

public class ExpertDbRepository {
    public String toDbRow(Expert expert) {
        return String.join(
                ",",
                expert.getName(),
                expert.getFirstLastName(),
                expert.getSecondLastName(),
                expert.getJob(),
                expert.getCompany(),
                expert.getJobField(),
                expert.getBiography());
    }
}
