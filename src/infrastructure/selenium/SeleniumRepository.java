public abstract class SeleniumRepository {
    public List<WebElement> findElementsByClassName(String className) {
        return SeleniumDriverRepository.getInstance().findElements(By.class(className));
    }

    public WebElement findElementByQuery(String query) {
        return SeleniumDriverRepository.getInstance().findElement(By.css(query));
    }

    public WebElement getElementFromElement(WebElement element, String query) {
        if (element == null) {
            return element;
        }

        return element.findElement(By.css(query));
    }

    public String getInnerTextFromElement(WebElement element) {
        if (element == null) {
            return element;
        }

        return element.getAttribute("innerText");
    }
}