public class GirlService {
    public final GIRLS_CLASS_NAME = ".girl";

    public final SeleniumRepository seleniumRepository;

    public List<WebElement> findEveryGirl() {
        return this.seleniumRepository.findElementsByClassName(GirlService::GIRLS_CLASS_NAME);
    }

    public GirlModel getGirlDetail(WebElement element) {
        element.click();
    }
}