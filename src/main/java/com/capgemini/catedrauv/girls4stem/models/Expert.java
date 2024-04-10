package com.capgemini.catedrauv.girls4stem.models;

public class Expert {

    private String name;

    private String firstLastName;

    private String secondLastName;

    private String job;

    private String company;

    private String jobField;

    private String biography;

    
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }



    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }



    /**
     * @return the firstLastName
     */
    public String getFirstLastName() {
        return firstLastName;
    }



    /**
     * @param firstLastName the firstLastName to set
     */
    public void setFirstLastName(String firstLastName) {
        this.firstLastName = firstLastName;
    }



    /**
     * @return the secondLastName
     */
    public String getSecondLastName() {
        return secondLastName;
    }



    /**
     * @param secondLastName the secondLastName to set
     */
    public void setSecondLastName(String secondLastName) {
        this.secondLastName = secondLastName;
    }



    /**
     * @return the job
     */
    public String getJob() {
        return job;
    }



    /**
     * @param job the job to set
     */
    public void setJob(String job) {
        this.job = job;
    }



    /**
     * @return the company
     */
    public String getCompany() {
        return company;
    }



    /**
     * @param company the company to set
     */
    public void setCompany(String company) {
        this.company = company;
    }



    /**
     * @return the jobField
     */
    public String getJobField() {
        return jobField;
    }



    /**
     * @param jobField the jobField to set
     */
    public void setJobField(String jobField) {
        this.jobField = jobField;
    }



    /**
     * @return the biography
     */
    public String getBiography() {
        return biography;
    }



    /**
     * @param biography the biography to set
     */
    public void setBiography(String biography) {
        this.biography = biography;
    }



    @Override
    public String toString() {
        return "Expert [name=" + name + ", firstLastName=" + firstLastName + ", secondLastName=" + secondLastName
                + ", job=" + job + ", company=" + company + ", jobField=" + jobField + ", biography=" + biography + "]\n";
    }

    
}
