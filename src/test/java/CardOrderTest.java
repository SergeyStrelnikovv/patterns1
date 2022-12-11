
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.Flaky;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class CardOrderTest {

    @BeforeAll
    static void addListener(){
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide().screenshots(true).savePageSource(false));
    }

    @AfterAll
    static void removeListener(){
        SelenideLogger.removeListener("AllureSelenide");
    }

    @Test
    @Flaky
    void shouldSendForm() {
        open("http://localhost:9999/");
        $("[data-test-id=city] .input__control").setValue(DataGenerator.getCityInternally());
        $("[data-test-id=date] [placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE, DataGenerator.when(0));
        $("[data-test-id=name] [name=name]").setValue(DataGenerator.getFullName());
        $("[data-test-id=phone] [name=phone]").setValue(DataGenerator.getPhone());
        $("[data-test-id=agreement]>.checkbox__box").click();
        $(".grid-col .button__text").click();
        $("[data-test-id='success-notification']>.notification__content").shouldBe(visible).shouldHave(text("Встреча успешно запланирована на " + DataGenerator.when(0)));
        $("[data-test-id=date] [placeholder='Дата встречи']").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.BACK_SPACE, DataGenerator.when(2));
        $(".grid-col .button__text").click();
        $("[data-test-id='replan-notification'] .button__text").click();
        $("[data-test-id='success-notification']>.notification__content").shouldBe(visible).shouldHave(text("Встреча успешно запланирована на " + DataGenerator.when(2)));
    }
}
