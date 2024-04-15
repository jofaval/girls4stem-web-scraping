package com.capgemini.catedrauv.girls4stem.domain.models;

import lombok.Data;
import shared.ToCsv;

@Data
public class Expert implements ToCsv {

    private String name;

    private String firstLastName;

    private String secondLastName;

    private String job;

    private String company;

    private String jobField;

    private String biography;

    @Override
    public String toString() {
        return "Expert [name=" + name + ", firstLastName=" + firstLastName + ", secondLastName=" + secondLastName
                + ", job=" + job + ", company=" + company + ", jobField=" + jobField + ", biography=" + biography
                + "]\n";
    }

    @Override
    public String toCsv(String separator) {
        return String.join(
                separator,
                name,
                firstLastName,
                secondLastName,
                job,
                company,
                jobField,
                biography);
    }

}
