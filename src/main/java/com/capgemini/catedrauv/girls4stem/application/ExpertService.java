package com.capgemini.catedrauv.girls4stem.application;

import org.openqa.selenium.WebElement;

import com.capgemini.catedrauv.girls4stem.domain.UseCase.ExpertUseCase;
import com.capgemini.catedrauv.girls4stem.domain.models.Expert;

public class ExpertService implements ExpertUseCase {
    public final static int SPEAKER_SLEEP_TIME = 500;

    @Override
    public String findName() {
        return SeleniumService.getInstance().getValue("name");
    }

    @Override
    public String findFirstLastName() {
        return SeleniumService.getInstance().getValue("surname");
    }

    @Override
    public String findSecondLastName() {
        return SeleniumService.getInstance().getValue("surname2");
    }

    @Override
    public String findJob() {
        return SeleniumService.getInstance().getValue("job");
    }

    @Override
    public String findCompany() {
        return SeleniumService.getInstance().getValue("company");
    }

    @Override
    public String findJobField() {
        return SeleniumService.getInstance().getValue("field");
    }

    @Override
    public String findBiography() {
        return SeleniumService.getInstance().getValue("bibliography");
    }

    public Expert populate(Expert expert) {
        expert.setName(findName());
        expert.setFirstLastName(findFirstLastName());
        expert.setSecondLastName(findSecondLastName());
        expert.setJob(findJob());
        expert.setCompany(findCompany());
        expert.setJobField(findJobField());
        expert.setBiography(findBiography());

        return expert;
    }

    @Override
    public Expert toExpert(WebElement element) {
        try {

            openSpeaker(element);
            Expert expert = populate(new Expert());
            closeSpeaker();

            System.out.println("Detalle de la experta: " + expert.getName());

            return expert;
        } catch (Exception e) {
            return null;
        }
    }

    public static void waitForSpeakerAnimation() throws InterruptedException {
        Thread.sleep(SPEAKER_SLEEP_TIME);
    }

    public static void openSpeaker(WebElement expert) throws InterruptedException {
        System.out.println("Abriendo el detalle de una experta");
        SeleniumService.getInstance().click(expert);
        waitForSpeakerAnimation();
    }

    public static void closeSpeaker() throws InterruptedException {
        System.out.println("Cerrando el detalle de una experta");
        SeleniumService.getInstance().click(".cdk-overlay-backdrop");
        waitForSpeakerAnimation();
    }
}
