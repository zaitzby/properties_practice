package tests;

import com.github.javafaker.Faker;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;

public class PracticeFormTests extends BaseTest {

    @Test
    void fullFillForm() {
        Faker faker = new Faker();

        String name = faker.name().name();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String gender = "Male";
        String mobile = faker.phoneNumber().subscriberNumber(10);
        String yearOfBirth = "1961";
        String monthOfBirth = "October";
        String dateOfBirth = "13";
        String subject = "Arts";
        String subject2 = "Biology";
        String hobby = "Reading";
        String fileName = "fox.PNG";
        String address = faker.address().fullAddress();
        String state = "Rajasthan";
        String city = "Jaipur";
        String datePickerLocator = String.format("%s %sth, %s", monthOfBirth, dateOfBirth, yearOfBirth);

        step("Open main page.", () ->
            open("https://demoqa.com/automation-practice-form")
        );

        step("Fill in form with personal data", () -> {
            $("#firstName").setValue(name);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(email);
            $("[for='gender-radio-1']").click();
            $("#userNumber").setValue(mobile);
            $("#dateOfBirthInput").click();
            $(".react-datepicker__year-select").selectOption(yearOfBirth);
            $(".react-datepicker__month-select").selectOption(monthOfBirth);
            $("[aria-label$='" + datePickerLocator + "']").click();
            $("#subjectsInput").setValue("ar").pressEnter();
            $("#subjectsInput").setValue("bi").pressEnter();
            $("[for='hobbies-checkbox-2']").click();
            $("#uploadPicture").uploadFromClasspath(fileName);
            $("#currentAddress").setValue(address);
            $("#state").scrollTo().click();
            $("#stateCity-wrapper").$(byText(state)).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText(city)).click();
        });

        step("Submit filled form", () ->
            $("#submit").click()
        );


        step("Check form is crested", () -> {
            $(".table-responsive").shouldHave(
                    text("Student Name " + name + " " + lastName),
                    text("Student Email " + email),
                    text("Gender " + gender),
                    text("Mobile " + mobile),
                    text("Date of Birth " + dateOfBirth + " " + monthOfBirth + "," + yearOfBirth),
                    text("Subjects " + subject + ", " + subject2),
                    text("Hobbies " + hobby),
                    text("Picture " + fileName),
                    text("Address " + address),
                    text("State and City " + state + " " + city)
            );
        });
    }

    @Test
    void failFullFillForm() {
        Faker faker = new Faker();

        String name = faker.name().name();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String gender = "Male";
        String mobile = faker.phoneNumber().subscriberNumber(10);
        String yearOfBirth = "1961";
        String monthOfBirth = "October";
        String dateOfBirth = "13";
        String subject = "Arts";
        String subject2 = "Biology";
        String hobby = "Reading";
        String fileName = "fox.PNG";
        String address = faker.address().fullAddress();
        String state = "Rajasthan";
        String city = "Jaipur";
        String datePickerLocator = String.format("%s %sth, %s", monthOfBirth, dateOfBirth, yearOfBirth);


        step("Open main page.", () ->
                open("https://demoqa.com/automation-practice-form")
        );

        step("Fill in form with personal data", () -> {
            $("#firstName").setValue(name);
            $("#lastName").setValue(lastName);
            $("#userEmail").setValue(email);
            $("[for='gender-radio-1']").click();
            $("#userNumber").setValue(mobile);
            $("#dateOfBirthInput").click();
            $(".react-datepicker__year-select").selectOption(yearOfBirth);
            $(".react-datepicker__month-select").selectOption(monthOfBirth);
            $("[aria-label$='" + datePickerLocator + "']").click();
            $("#subjectsInput").setValue("ar").pressEnter();
            $("#subjectsInput").setValue("bi").pressEnter();
            $("[for='hobbies-checkbox-2']").click();
            $("#uploadPicture").uploadFromClasspath(fileName);
            $("#currentAddress").setValue(address);
            $("#state").scrollTo().click();
            $("#stateCity-wrapper").$(byText(state)).click();
            $("#city").click();
            $("#stateCity-wrapper").$(byText(city)).click();
        });

        step("Submit filled form", () ->
                $("#submit").click()
        );


        step("Check form is crested", () -> {
            $(".table-responsive").shouldHave(
                    text("Student Name " + name + " " + lastName),
                    text("Student Email " + email),
                    text("Gender " + gender),
                    text("Mobile " + mobile),
                    text("Date of Birth " + dateOfBirth + " " + monthOfBirth + "," + yearOfBirth),
                    text("Subjects " + subject + ", " + subject2),
                    text("Hobbies " + hobby),
                    text("Picture " + fileName),
                    text("Address " + address),
                    text("State and City " + state + " " + city + " DAMN!!")
            );
        });
    }
}
