package com.capgemini.catedrauv.girls4stem.domain.UseCase;

import org.openqa.selenium.WebElement;

import com.capgemini.catedrauv.girls4stem.domain.models.Expert;

public interface ExpertUseCase {
    public String findName();

    public String findFirstLastName();

    public String findSecondLastName();

    public String findJob();

    public String findCompany();

    public String findJobField();

    public String findBiography();

    public Expert toExpert(WebElement element) throws InterruptedException;
}
