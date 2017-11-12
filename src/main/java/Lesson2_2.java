import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.List;

public class Lesson2_2 {
    public static final String CONTENT_TITLE = ".//*[@id='content']/div[1]/div/h2";

    // only for MODULES and for CATALOGUE
    public static final String MAIN_DIV_TITLE = ".//*[@id='main-div']/div[1]/h2";

    public static final String DASHBOARD = ".//*[@id='tab-AdminDashboard']/a";
    public static final String ORDERS = ".//*[@id='subtab-AdminParentOrders']/a";
    public static final String CATALOGUE = ".//*[@id='subtab-AdminCatalog']/a";
    public static final String CLIENTS = "/html/body/nav/ul/li[5]/a";
    public static final String CLIENTSERVICE = ".//*[@id='subtab-AdminParentCustomerThreads']/a";
    public static final String STATISTIC = ".//*[@id='subtab-AdminStats']/a";
    public static final String MODULES = ".//*[@id='subtab-AdminParentModulesSf']/a";
    public static final String DESIGN = "/html/body/nav/ul/li[10]/a";
    //public static final String DESIGN = ".//*[@id='subtab-AdminParentThemes']/a";
    public static final String DELIVERY = ".//*[@id='subtab-AdminParentShipping']/a";
    public static final String PAYMENT = ".//*[@id='subtab-AdminParentPayment']/a";
    public static final String INTERNATIONAL = ".//*[@id='subtab-AdminInternational']/a";
    public static final String SHOPPARAMETERS = ".//*[@id='subtab-ShopParameters']/a";
    public static final String ADMINADVANCEDPARAMETERS = ".//*[@id='subtab-AdminAdvancedParameters']/a";

    public static void main (String [] args){
        Lesson2_2 lesson2_2 = new Lesson2_2();
        List<String> menuLinksArray = new ArrayList<String>();
        String etalonTitle;
        String afterRefreshTitle;
        menuLinksArray.add(DASHBOARD);
        menuLinksArray.add(ORDERS);
        menuLinksArray.add(CATALOGUE);
        menuLinksArray.add(CLIENTS);
        menuLinksArray.add(CLIENTSERVICE);
        menuLinksArray.add(STATISTIC);
        menuLinksArray.add(MODULES);
        menuLinksArray.add(DESIGN);
        menuLinksArray.add(DELIVERY);
        menuLinksArray.add(PAYMENT);
        menuLinksArray.add(INTERNATIONAL);
        menuLinksArray.add(SHOPPARAMETERS);
        menuLinksArray.add(ADMINADVANCEDPARAMETERS);

        WebDriver driver = initChrome();
        driver.get("http://prestashop-automation.qatestlab.com.ua/admin147ajyvk0/");
        lesson2_2.enterPassword (driver, "webinar.test@gmail.com", "Xcg7299bnSmMuRLp9ITw");
        lesson2_2.threadSleep(3000);

        for (String menuName : menuLinksArray) {
            String changingTitleName;

            if (menuName.equals(MODULES) | menuName.equals(CATALOGUE)) {
                changingTitleName = MAIN_DIV_TITLE;
            } else {
                changingTitleName = CONTENT_TITLE;
            }

            etalonTitle = lesson2_2.clickMenue_getTitle(driver, menuName, changingTitleName);
            driver.navigate().refresh();
            lesson2_2.threadSleep(1000);
            afterRefreshTitle = lesson2_2.getOnlyTitle(driver, changingTitleName);

            if (etalonTitle.equals(afterRefreshTitle)) {
                System.out.println("Tilte " + etalonTitle + "is equivalent itself after refresh");
            } else System.out.println("Tilte " + etalonTitle + " is NOT equivalent itself after refresh. Title after refresh is " + afterRefreshTitle);

        }

        driver.quit();
    }

    public static WebDriver initChrome (){
        System.setProperty("webdriver.chrome.driver","D:\\IdeaProjects\\lesson2\\lesson2\\drivers\\chromedriver.exe");
        return new ChromeDriver();
    }

    public void enterPassword (WebDriver driver, String log, String pas){
        WebElement login = driver.findElement(By.id("email"));
        login.sendKeys(log);
        WebElement password = driver.findElement(By.id("passwd"));
        password.sendKeys(pas);
        WebElement button = driver.findElement(By.name ("submitLogin"));
        button.click();
    }

    public String clickMenue_getTitle(WebDriver driver, String pathMenueItem, String pathTile){
        WebElement webElement = driver.findElement(By.xpath(pathMenueItem));
        webElement.click();
        threadSleep(3000);
        WebElement webElementTitle = driver.findElement(By.xpath(pathTile));
        return webElementTitle.getText();

    }

    public String getOnlyTitle (WebDriver driver, String pathTile){
        WebElement webElementTitle = driver.findElement(By.xpath(pathTile));
        return webElementTitle.getText();

    }


    public void threadSleep(int i){
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}