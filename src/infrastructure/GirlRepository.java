public final class GirlRepository {
    public final SeleniumRepository seleniumRepository;

    public static final String NAME_CSS_QUERY = "";
    public static final String FIRST_SURNAME_CSS_QUERY = "";
    public static final String SECOND_SURNAME_CSS_QUERY = "";
    public static final String JOB_ROLE_CSS_QUERY = "";
    public static final String BUSINESS_CSS_QUERY = "";
    public static final String JOB_FIELD_CSS_QUERY = "";
    public static final String BIOGRAPHY_CSS_QUERY = "";

    public GirlModel getGirlFromElement(WebElement element) {
        return GirlModel(
            this.seleniumRepository.getInnerTextFromElement(
                this.seleniumRepository.findElementByQuery(GirlRepository::NAME_CSS_QUERY)
            ),
            this.seleniumRepository.getInnerTextFromElement(
                this.seleniumRepository.findElementByQuery(GirlRepository::FIRST_SURNAME_CSS_QUERY)
            ),
            this.seleniumRepository.getInnerTextFromElement(
                this.seleniumRepository.findElementByQuery(GirlRepository::SECOND_SURNAME_CSS_QUERY)
            ),
            this.seleniumRepository.getInnerTextFromElement(
                this.seleniumRepository.findElementByQuery(GirlRepository::JOB_ROLE_CSS_QUERY)
            ),
            this.seleniumRepository.getInnerTextFromElement(
                this.seleniumRepository.findElementByQuery(GirlRepository::BUSINESS_CSS_QUERY)
            ),
            this.seleniumRepository.getInnerTextFromElement(
                this.seleniumRepository.findElementByQuery(GirlRepository::JOB_FIELD_CSS_QUERY)
            ),
            this.seleniumRepository.getInnerTextFromElement(
                this.seleniumRepository.findElementByQuery(GirlRepository::BIOGRAPHY_CSS_QUERY)
            ),
        );
    }
}