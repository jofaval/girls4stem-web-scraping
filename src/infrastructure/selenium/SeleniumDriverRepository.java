public final class SeleniumDriverRepository {
    private static SeleniumDriverRepository instance;

    public static getInstance() {
        if (this.instance == null) {
            this.instance = SeleniumDriverRepository();
        }

        return this.instance;
    }

    private void initDriver() {
        // TODO: configure and init driver
    }

    public SeleniumDriverRepository() {
        this.initDriver();
    }
}