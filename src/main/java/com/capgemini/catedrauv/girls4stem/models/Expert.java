package com.capgemini.catedrauv.girls4stem.models;

import lombok.Data;

@Data
public class Expert {

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

}
