import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

public class DataGenerator {

    private DataGenerator() { }

    private static final Faker faker = new Faker(new Locale("ru"));

    public static String getCityInternally() {
        String[] city = {"Нижний Новгород", "Москва", "Санкт-Петербург"};
        return city[new Random().nextInt(3)];
    }

    public static String when(int reschedule) {
        return LocalDate.now().plusDays(7 + reschedule).format(DateTimeFormatter.ofPattern("dd.MM.uuuu"));
    }

    public static String getFullName() {
        return faker.name().lastName().replace("ё", "е") + " " + faker.name().firstName().replace("ё", "е");
    }

    public static String getPhone() {
        return faker.phoneNumber().phoneNumber().replace("+", "").replace("(", "").replace(")", "").replace("-", "");
    }
}