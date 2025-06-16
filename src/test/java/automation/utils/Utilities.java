package automation.utils;

import com.github.javafaker.Faker;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.HasDevTools;
import org.openqa.selenium.devtools.v135.network.Network;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Utilities {
    private static final Faker faker = new Faker();

    // ========================================== Wait Utilities ==========================================

    /** WebElement input = Utilities.waitForElementVisible(driver, By.id("username"), 1000); */
    public static WebElement waitForElementVisible(WebDriver driver, By locator, int timeoutMillis) {
        System.out.println("[WAIT] Menunggu elemen terlihat: " + locator);
        return new WebDriverWait(driver, Duration.ofMillis(timeoutMillis))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    /** Utilities.waitForDelay(2000); */
    public static void waitForDelay(int timeoutMillis) {
        try {
            System.out.println("[WAIT] Delay selama " + timeoutMillis + "ms");
            Thread.sleep(timeoutMillis);
        } catch (InterruptedException e) {
            System.err.println("[ERROR] Gagal delay: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    /** Utilities.pause(driver, 2000); */
    public static void pause(WebDriver driver, int timeoutMillis) {
        new WebDriverWait(driver, Duration.ofMillis(timeoutMillis)).until(d -> true);
        System.out.println("[WAIT] Pause selama " + timeoutMillis + "ms");
    }

    // ========================================== Interaction Utilities ==========================================

    /** Utilities.clickWithJS(driver, element); */
    public static void clickWithJS(WebDriver driver, WebElement element) {
        System.out.println("[CLICK] Klik dengan JS");
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
    }

    /** Utilities.safeClick(driver, By.id("submit")); */
    public static void safeClick(WebDriver driver, By locator) {
        try {
            System.out.println("[CLICK] Klik elemen: " + locator);
            waitForElementVisible(driver, locator, 800).click();
        } catch (Exception e) {
            System.err.println("[WARN] Klik biasa gagal, menggunakan JS untuk: " + locator);
            clickWithJS(driver, waitForElementVisible(driver, locator, 800));
        }
    }

    /** Utilities.safeClickTimer(driver, By.id("submit"), 2000); */
    public static void safeClickTimer(WebDriver driver, By locator, int timeoutMillis) {
        try {
            new WebDriverWait(driver, Duration.ofMillis(timeoutMillis))
                    .until(ExpectedConditions.elementToBeClickable(locator));
            safeClick(driver, locator);
        } catch (Exception e) {
            System.err.println("[ERROR] Gagal klik dengan timer pada: " + locator);
        }
    }

    /** Utilities.clickMultipleTimes(driver, By.id("btn"), 3, 1000); */
    public static void clickMultipleTimes(WebDriver driver, By locator, int times, int delayMillis) {
        for (int i = 0; i < times; i++) {
            System.out.println("[CLICK] Klik ke-" + (i + 1) + " pada: " + locator);
            safeClick(driver, locator);
            waitForDelay(delayMillis);
        }
    }

    /** Utilities.rightClickWithJS(driver, By.id("menu"), 1000); */
    public static void rightClickWithJS(WebDriver driver, By locator, int timeoutMillis) {
        WebElement element = waitForElementVisible(driver, locator, timeoutMillis);
        String script = "var event = new MouseEvent('contextmenu', {bubbles:true,cancelable:true,view:window});arguments[0].dispatchEvent(event);";
        ((JavascriptExecutor) driver).executeScript(script, element);
        System.out.println("[CLICK] Klik kanan (JS) pada elemen: " + locator);
    }

    /** Utilities.doubleClick(driver, By.id("doubleClickBtn")); */
    public static void doubleClick(WebDriver driver, By locator) {
        System.out.println("[CLICK] Double click pada: " + locator);
        new Actions(driver).doubleClick(waitForElementVisible(driver, locator, 800)).perform();
    }

    /** Utilities.clickMultipleElements(driver, listOfLocators); */
    public static void clickMultipleElements(WebDriver driver, List<By> locators) {
        for (By locator : locators) {
            safeClick(driver, locator);
        }
    }

    /** Utilities.dragAndDrop(driver, By.id("from"), By.id("to")); */
    public static void dragAndDrop(WebDriver driver, By sourceLocator, By targetLocator) {
        WebElement source = waitForElementVisible(driver, sourceLocator, 800);
        WebElement target = waitForElementVisible(driver, targetLocator, 800);
        new Actions(driver).dragAndDrop(source, target).perform();
        System.out.println("[ACTION] Drag & Drop dari " + sourceLocator + " ke " + targetLocator);
    }

    /** Utilities.dragAndHold(driver, By.id("from"), By.id("to")); */
    public static void dragAndHold(WebDriver driver, By sourceLocator, By targetLocator) {
        WebElement source = waitForElementVisible(driver, sourceLocator, 800);
        WebElement target = waitForElementVisible(driver, targetLocator, 800);
        new Actions(driver).clickAndHold(source).moveToElement(target).perform();
        System.out.println("[ACTION] Drag & Hold dari " + sourceLocator + " ke " + targetLocator);
    }

    /** Utilities.resizeElement(driver, By.id("resizer"), 50, 50); */
    public static void resizeElement(WebDriver driver, By resizerLocator, int xOffset, int yOffset) {
        WebElement resizer = waitForElementVisible(driver, resizerLocator, 800);
        new Actions(driver).clickAndHold(resizer).moveByOffset(xOffset, yOffset).release().perform();
        System.out.println("[ACTION] Resize elemen dengan offset X: " + xOffset + ", Y: " + yOffset);
    }

    /** Utilities.scrollToElement(driver, element, "center"); */
    public static void findElement(WebDriver driver, By locator, String blockPosition) {
        WebElement element = waitForElementVisible(driver, locator, 800); // atau driver.findElement(locator)
        scrollToElement(driver, element, blockPosition);
    }
    // Scroll ke elemen (posisi tengah default)
    public static void scrollToElement(WebDriver driver, WebElement element, String blockPosition) {
        if (!(blockPosition.equals("start") || blockPosition.equals("center") || blockPosition.equals("end"))) {
            blockPosition = "center";
        }
        String script = String.format("arguments[0].scrollIntoView({block: '%s'});", blockPosition);
        ((JavascriptExecutor) driver).executeScript(script, element);
    }

    // ========================================== SendKeys / Input Utilities ==========================================

    /** String text = utilities.getElementTextSafe(driver, By.id("label"), "N/A"); */
    public static String getElementTextSafe(WebDriver driver, By locator, String defaultValue) {
        try {
            WebElement element = waitForElementVisible(driver, locator, 800);
            String text = element.getText();
            System.out.println("[INFO] Element text: " + text);
            return text;
        } catch (Exception e) {
            System.err.println("[WARN] Tidak bisa ambil teks elemen, kembali ke default: " + defaultValue);
            return defaultValue;
        }
    }
    /**  Utilities.sendKeys(driver, By.id("email"), "user@mail.com"); */
    public static void sendKeys(WebDriver driver, By locator, String text) {
        WebElement input = waitForElementVisible(driver, locator, 800);
        input.clear();
        input.sendKeys(text);
        System.out.println("[INPUT] Isi teks: '" + text + "' ke elemen: " + locator);
    }

    /** Utilities.sendKeysWithJS(driver, By.id("username"), "admin"); */
    public static void sendKeysWithJS(WebDriver driver, By locator, String text) {
        WebElement element = waitForElementVisible(driver, locator, 800);
        String script = "arguments[0].value='" + text + "';";
        ((JavascriptExecutor) driver).executeScript(script, element);
        System.out.println("[INPUT] (JS) Isi teks: '" + text + "' ke elemen: " + locator);
    }

    /** utilities.sendKeysWithKeyboard(driver, By.name("search"), "query", Keys.ENTER);  */
    public static void sendKeysWithKeyboard(WebDriver driver, By locator, String text, Keys key) {
        WebElement element = waitForElementVisible(driver, locator, 800);
        element.clear();
        element.sendKeys(text);
        element.sendKeys(key);
    }

    /** utilities.pressKeyOnly(driver, By.tagName("body"), Keys.ESCAPE);  */
    public static void pressKeyOnly(WebDriver driver, By locator, Keys key) {
        WebElement element = waitForElementVisible(driver, locator, 800);
        element.sendKeys(key);
    }

    public static boolean isElementInteractable(WebDriver driver, By locator, int timeoutMillis) {
        WebElement element = waitForElementVisible(driver, locator, timeoutMillis);
        if (element == null) {
            System.err.println("Elemen tidak ditemukan atau tidak terlihat: " + locator.toString());
            return false;
        }

        String tagName = element.getTagName();
        String typeAttr = element.getAttribute("type");

        System.out.println("Elemen ditemukan: " + locator.toString());
        System.out.println("Tag: <" + tagName + ">, type: " + (typeAttr != null ? typeAttr : "N/A"));

        if (element.isDisplayed() && element.isEnabled()) {
            System.out.println("Elemen terlihat dan aktif.");
            return true;
        } else {
            System.err.println("Elemen ditemukan tapi tidak aktif (mungkin disabled).");
            return false;
        }
    }


    // ========================================== Window / Tab Utilities ==========================================

    /** utilities.switchToWindowByIndex(driver, 1); */
    public static void switchToWindowByIndex(WebDriver driver, int index) {
        List<String> windowHandles = new ArrayList<>(driver.getWindowHandles());
        if (index < 0 || index >= windowHandles.size()) {
            throw new RuntimeException("Index tab di luar jangkauan: " + index);
        }
        driver.switchTo().window(windowHandles.get(index));
    }

     /** utilities.switchToDefaultTab(driver); */
    public static void switchToDefaultTab(WebDriver driver) {
        driver.switchTo().window(driver.getWindowHandles().iterator().next());
    }

     /** utilities.printAllWindowTitles(driver); */
    public static void printAllWindowTitles(WebDriver driver) {
        for (String handle : driver.getWindowHandles()) {
            driver.switchTo().window(handle);
            System.out.println("Window title: " + driver.getTitle());
        }
    }

    /** Utilities.closeAllOtherTabs(driver); */
    public static void closeAllOtherTabs(WebDriver driver) {
        String mainWindowHandle = driver.getWindowHandles().iterator().next();
        Set<String> allWindowHandles = driver.getWindowHandles();

        for (String handle : allWindowHandles) {
            if (!handle.equals(mainWindowHandle)) {
                driver.switchTo().window(handle);
                System.out.println("[INFO] Menutup tab: " + driver.getTitle());
                driver.close();
            }
        }

        switchToDefaultTab(driver);
    }


    // ========================================== Validation Utilities ==========================================

    /** Utilities.checkImageStatus("https://example.com/image.png"); */
    public static void checkImageStatus(String imageUrl) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(imageUrl).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("[VALIDATION] Gambar aktif: " + imageUrl);
            } else {
                System.err.println("[ERROR] Gambar rusak: " + imageUrl);
            }
        } catch (Exception e) {
            System.err.println("[ERROR] Gagal mengecek gambar: " + e.getMessage());
        }
    }

    /** Memeriksa apakah link aktif (HTTP OK). Utilities.checkLinkStatus("https://example.com"); */
    public static void checkLinkStatus(String linkUrl) {
        try {
            HttpURLConnection connection = (HttpURLConnection) new URL(linkUrl).openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                System.out.println("[VALIDATION] Link aktif: " + linkUrl);
            } else {
                System.err.println("[ERROR] Link rusak: " + linkUrl);
            }
        } catch (Exception e) {
            System.err.println("[ERROR] Gagal mengecek link: " + e.getMessage());
        }
    }

    /** Utilities.validate(driver); */
    public static void validate(WebDriver driver) {
        List<WebElement> elements = new ArrayList<>();
        elements.addAll(driver.findElements(By.tagName("a")));
        elements.addAll(driver.findElements(By.tagName("img")));

        for (WebElement el : elements) {
            String url = el.getTagName().equals("a") ? el.getAttribute("href") : el.getAttribute("src");

            if (url == null || url.trim().isEmpty()) {
                continue; // skip jika kosong
            }

            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setConnectTimeout(3000);
                connection.connect();

                int status = connection.getResponseCode();
                String type = el.getTagName().equals("a") ? "LINK" : "IMAGE";

                if (status == 200) {
                    System.out.println("[VALID " + type + "] " + url);
                } else {
                    System.err.println("[BROKEN " + type + "] " + url + " - " + connection.getResponseMessage());
                }

            } catch (Exception e) {
                String type = el.getTagName().equals("a") ? "LINK" : "IMAGE";
                System.err.println("[ERROR " + type + "] " + url + " - " + e.getMessage());
            }
        }
    }
    // ========================================== Alert Utilities ==========================================

    /** Utilities.acceptAlert(driver); */
    public static void acceptAlert(WebDriver driver) {
        try {
            Alert alert = driver.switchTo().alert();
            System.out.println("[ALERT] Teks: " + alert.getText());
            alert.accept();
            System.out.println("[ALERT] Diterima");
        } catch (NoAlertPresentException e) {
            System.err.println("[ERROR] Tidak ada alert yang muncul");
        }
    }

    /** String alertText = utilities.getAlertText(driver); */
    public static String getAlertText(WebDriver driver) {
        try {
            return driver.switchTo().alert().getText();
        } catch (Exception e) {
            System.err.println("[ERROR] Tidak bisa switch ke alert atau ambil teks");
            return null;
        }
    }

    /** utilities.dismissAlert(driver); */
    public static void dismissAlert(WebDriver driver) {
        try {
            driver.switchTo().alert().dismiss();
        } catch (Exception e) {
            System.err.println("[ERROR] Tidak bisa dismiss alert");
            throw new RuntimeException(e);
        }
    }


    // ========================================== Faker Data Generator ==========================================


    /** Generate nama lengkap acak */
    public static String generateFullName() {
        return faker.name().fullName();
    }

    /** Generate alamat email acak */
    public static String generateEmail() {
        return faker.internet().emailAddress();
    }

    /** Generate alamat lengkap acak */
    public static String generateAddress() {
        return faker.address().fullAddress();
    }

    /** Generate nomor telepon acak */
    public static String generatePhoneNumber() {
        Faker faker = new Faker();
        String number = faker.number().digits(10);
        return number;
    }

    /** Generate nama perusahaan acak */
    public static String generateCompanyName() {
        return faker.company().name();
    }

    /** Generate nama depan acak */
    public static String generateFirstName() {
        return faker.name().firstName();
    }

    /** Generate nama belakang acak */
    public static String generateLastName() {
        return faker.name().lastName();
    }

    public static String generatePassword(){
        return faker.internet().password();
    }

    /** Generate tanggal lahir acak antara usia 22 hingga 39 tahun (format MM/dd/yyyy) */
    public static String generateBirthDate() {
        Date birthDate = faker.date().birthday(22, 39);
        return new SimpleDateFormat("MM/dd/yyyy").format(birthDate);
    }
    public static void validateLinksInSection(WebDriver driver, String sectionXpath) {
        WebElement section;
        try {
            section = waitForElementVisible(driver, By.xpath(sectionXpath), 2000);
        } catch (Exception e) {
            System.err.println("❌ Bagian tidak ditemukan: " + sectionXpath);
            return;
        }

        List<WebElement> links = section.findElements(By.tagName("a"));

        if (links.isEmpty()) {
            System.out.println("Tidak ada link ditemukan di dalam section.");
            return;
        }

        System.out.println("Memvalidasi " + links.size() + " link dalam section...");

        for (WebElement link : links) {
            String href = link.getAttribute("href");
            if (href == null || href.isEmpty()) {
                System.out.println("Link kosong atau tidak ada href.");
                continue;
            }

            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(href).openConnection();
                connection.setRequestMethod("HEAD");
                connection.setConnectTimeout(3000);
                connection.setReadTimeout(3000);
                int responseCode = connection.getResponseCode();
                System.out.println("Link: " + href + " --> Status: " + responseCode);
            } catch (IOException e) {
                System.out.println("Gagal mengakses: " + href + " --> " + e.getMessage());
            }
        }
    }
    /**
     * Memeriksa semua elemen <a> dan <img> dalam section tertentu (ditentukan dengan XPath),
     * lalu memvalidasi status HTTP (HEAD request) dari setiap link atau gambar.
     *
     * @param driver       WebDriver instance
     * @param sectionXpath XPath dari section (misalnya: "/html/body/main/div/div/div[2]")
     *
     * <p><b>Contoh Penggunaan:</b></p>
     * <pre>
     *     Utilities.validateLinksAndImagesInSection(driver, "/html/body/main/div/div/div[2]");
     * </pre>
     */
    public static void validateLinksAndImagesInSection(WebDriver driver, String sectionXpath) {
        WebElement section;
        try {
            section = waitForElementVisible(driver, By.xpath(sectionXpath), 2000);
        } catch (Exception e) {
            System.err.println("Section tidak ditemukan: " + sectionXpath);
            return;
        }

        List<String> urlsToCheck = new ArrayList<>();

        // Ambil semua href dari <a>
        List<WebElement> links = section.findElements(By.tagName("a"));
        for (WebElement link : links) {
            String href = link.getAttribute("href");
            if (href != null && !href.isEmpty()) {
                urlsToCheck.add(href);
            }
        }

        // Ambil semua src dari <img>
        List<WebElement> images = section.findElements(By.tagName("img"));
        for (WebElement img : images) {
            String src = img.getAttribute("src");
            if (src != null && !src.isEmpty()) {
                urlsToCheck.add(src);
            }
        }

        if (urlsToCheck.isEmpty()) {
            System.out.println("Tidak ada <a> atau <img> dengan URL ditemukan dalam section.");
            return;
        }

        System.out.println("Memvalidasi " + urlsToCheck.size() + " link dan gambar...");

        for (String url : urlsToCheck) {
            try {
                HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
                connection.setRequestMethod("HEAD");
                connection.setConnectTimeout(3000);
                connection.setReadTimeout(3000);
                int status = connection.getResponseCode();
                System.out.println("URL: " + url + " --> Status: " + status);
            } catch (IOException e) {
                System.out.println("Gagal akses: " + url + " --> " + e.getMessage());
            }
        }
    }
    // Pilih dropdown by visible text
    public static void selectDropdownByVisibleText(WebDriver driver, By locator, String text) {
        try {
            WebElement dropdown = waitForElementVisible(driver, locator, 800);
            new Select(dropdown).selectByVisibleText(text);
            System.out.println("Selected dropdown option with visible text: " + text);
        } catch (Exception e) {
            System.err.println("Failed to select dropdown by visible text: " + text);
            e.printStackTrace();
        }
    }

    public static void selectDropdownByVisibleTextIndex(WebDriver driver, By locator, String value) {
        try {
            WebElement dropdown = waitForElementVisible(driver, locator, 800);
            new Select(dropdown).selectByValue(value);
            System.out.println("Selected dropdown option with value: " + value);
        } catch (Exception e) {
            System.err.println("Failed to select dropdown by value: " + value);
            e.printStackTrace();
        }
    }

    public static void loading(int timeoutMillis) {
        try {
            Thread.sleep(timeoutMillis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.err.println("Thread terinterupsi saat menunggu " + timeoutMillis + "ms");
        }
    }
    public static void sendKeysToAlert(WebDriver driver, String inputText) {
        try {
            Alert alert = driver.switchTo().alert();
            alert.sendKeys(inputText);
            System.out.println("Sent keys to alert: " + inputText);
        } catch (NoAlertPresentException e) {
            System.err.println("No alert present to send keys.");
        }
    }
}

