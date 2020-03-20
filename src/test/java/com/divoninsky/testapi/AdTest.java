package com.divoninsky.testapi;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import java.util.*;

public class AdTest {
    private static RequestSpecification signedRequest;
    private static List<String> urlList;
    private static String PANELID = "your_panel_id";
    private static String title = "JPG_1";
    private static String url = "https://sun1.velcom-by-minsk.userapi.com/c853520/v853520012/204fab/H-jxm52L2P8.jpg";
    private static String active = "true";
    private static String idOfAd = "5";
    private static Random random;


    @BeforeClass
    public static void addParamsToMap() {
        signedRequest = new Auth(Credentials.USERNAME.getTitle(), Credentials.PASSWORD.getTitle()).getSignedRequest();
        random = new Random();
        urlList = new ArrayList<>();
        urlList.add("https://sun1.velcom-by-minsk.userapi.com/c853520/v853520012/204fab/H-jxm52L2P8.jpg");
        urlList.add("https://ulej.by/images/project_images/1260x70800fc25538dbd833b21d0c74396e06abdd95ac17b.jpg");
        urlList.add("https://upload.wikimedia.org/wikipedia/commons/2/2a/Junonia_lemonias_DSF_upper_by_Kadavoor.JPG");
        urlList.add("https://upload.wikimedia.org/wikipedia/commons/c/c9/Moon.jpg");
        urlList.add("https://www.howtogeek.com/wp-content/uploads/2010/10/KeizersgrachtReguliersgrachtAmsterdamcompressed.jpg");
        urlList.add("https://media.alienwarearena.com/media/tux-g.jpg");
    }

    @Test
    public void getAd() {}

    @Test
    public void addAd() {
        List<Map<String, String>> paramsList = getParamsList();
        for (Map<String, String> params : paramsList) {
            signedRequest = new Auth(Credentials.USERNAME.getTitle(), Credentials.PASSWORD.getTitle()).getSignedRequest();
            signedRequest.params(params);
            Response response = signedRequest.post(Credentials.URL.getTitle() + "/core/panels/" + PANELID + "/ads");
            System.out.println(response.getBody().asString());
        }
    }

    @Test
    public void editAd() {
        Map<String, String> params = new HashMap<>();
        params.put("title", title);
        params.put("url", url);
        params.put("active", active);
        signedRequest.params(params);
        Response response = signedRequest.put(Credentials.URL.getTitle() + "/core/panels/" + PANELID + "/ads/" + idOfAd);
        System.out.println(response.getBody().asString());
        Assert.assertEquals(200, response.getStatusCode());
    }

    @Test
    public void deleteAd() {
        Response response = signedRequest.delete(Credentials.URL.getTitle() + "/core/panels/" + PANELID + "/ads/" + idOfAd);
        System.out.println(response.getStatusCode());
        Assert.assertEquals(204, response.getStatusCode());
    }

    @Test
    public void deleteAllAds() {
        Response response = signedRequest.get(Credentials.URL.getTitle() + "/core/panels/" + PANELID + "/ads");
        List<Object> id = response.jsonPath().getList("id");
        for (Object s : id) {
            signedRequest.delete(Credentials.URL.getTitle() + "/core/panels/" + PANELID + "/ads/" + s);
        }
    }

    private List<Map<String, String>> getParamsList() {
        List<Map<String, String>> paramsList = new ArrayList<>();
        for (String url : urlList) {
            Map<String, String> params = new HashMap<>();
            params.put("title", getRandomTitle());
            params.put("url", url);
            params.put("active", "true");
            paramsList.add(params);
        }
        return paramsList;
    }

    private String getRandomTitle() {
        return "Ad" + random.nextInt(1000);
    }

    @AfterClass
    public static void getAllAnns() {
        System.out.println("Get all ads:");
        String[] split = signedRequest.get(Credentials.URL.getTitle() + "/core/panels/" + PANELID + "/ads").getBody().asString().split("},\\{");
        for (String mess : split) {
            System.out.println(mess);
        }
    }
}
