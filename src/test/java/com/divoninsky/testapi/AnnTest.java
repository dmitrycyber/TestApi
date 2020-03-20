package com.divoninsky.testapi;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AnnTest {
    private static RequestSpecification signedRequest;
    private static List<String> messList;
    private static String PANELID = "notebookId";
    private static String id = "13454318";
    private static String active = "true";
    private static String text = "Дальнейшее развитие различных форм деятельности обеспечивает широкому Дальнейшее развитие различных форм деятельности обеспечивает широкому Дальнейшее развитие различных форм деятельности обеспечивает широкому";

    @BeforeClass
    public static void addParamsToMap() {
        signedRequest = new Auth(Credentials.USERNAME.getTitle(), Credentials.PASSWORD.getTitle()).getSignedRequest();
        messList = new ArrayList<>();
        messList.add("1. Москва, осень 2016 года. В город возвращается бывший студент-филолог Илья Горюнов, который отсидел семь лет за подкинутые в клубе наркотики. Главный герой не узнаёт столицу, которая сильно изменилась за эти годы. В особенности его глаз цепляется за смартфоны, которые раньше были «только у продвинутых», а сейчас — у каждого.");
        messList.add("2. Горюнов направляется домой, в подмосковную Лобню. Она, в отличие от Москвы, осталась той же. Героя охватывают воспоминания: школьные годы, бывшая девушка, друзья. Илья мечтает наконец увидеть мать, с которой он жил до ареста, но, приехав домой, узнает, что мать умерла от инфаркта за день до его возвращения.");
        messList.add("3. Илья приглашает к себе старого друга Серёгу, но при встрече понимает, что они стали друг другу чужими. С помощью телефона друга Горюнов находит во «ВКонтакте» страницу Петра Хазина — лейтенанта ФСКН, который семь лет назад отправил Илью за решётку, подкинув наркотики. Сам герой называет его про себя «Сукой». Видя довольное лицо человека, сломавшего ему жизнь, герой решает отомстить.");
    }

    @Test
    public void getAnn() {}

    @Test
    public void addAnn() {
        for (String mess : messList){
            signedRequest = new Auth(Credentials.USERNAME.getTitle(), Credentials.PASSWORD.getTitle()).getSignedRequest();
            Map<String, String> params = new HashMap<>();
            params.put("text", mess);
            params.put("active", "true");
            signedRequest.params(params);
            Response response = signedRequest.post(Credentials.URL.getTitle() + "/core/panels/" + PANELID + "/announcements");
            System.out.println("Get added ann:");
            System.out.println(response.getBody().asString());
        }
    }

    @Test
    public void editAnn() {
        Map<String, String> params = new HashMap<>();
        params.put("id", id);
        params.put("text", text);
        params.put("active", active);
        signedRequest.params(params);
        Response response = signedRequest.put(Credentials.URL.getTitle() + "/core/panels/" + PANELID + "/announcements/" + id);
        System.out.println("Get edited ann:");
        System.out.println(response.getBody().asString());
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void deleteAnn() {
        Response response = signedRequest.delete(Credentials.URL.getTitle() + "/core/panels/" + PANELID + "/announcements/" + id);
        System.out.println(response.getStatusCode());
        Assert.assertEquals(204, response.getStatusCode());
    }

    @Test
    public void deleteAllAnns(){
        Response response = signedRequest.get(Credentials.URL.getTitle() + "/core/panels/" + PANELID + "/announcements");
        List<Object> id = response.jsonPath().getList("id");
        for (Object s : id){
            signedRequest.delete(Credentials.URL.getTitle() + "/core/panels/" + PANELID + "/announcements/" + s);
        }

    }

    @AfterClass
    public static void getAnns(){
        System.out.println("Get all anns:");
        String[] split = signedRequest.get(Credentials.URL.getTitle() + "/core/panels/" + PANELID + "/announcements").getBody().asString().split("},\\{");
        for (String mess : split){
            System.out.println(mess);
        }
    }
}
