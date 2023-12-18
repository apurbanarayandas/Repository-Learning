package Session3;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Test_GetMethod {
    @Test
    public void test01()
    {
        int expectedStatusCode=200;
        Response response= RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println("Response Code : "+response.getStatusCode());
        System.out.println("Response Body : "+response.getBody().asString());
        System.out.println("Response Body : "+response.getTime());
        System.out.println("Response Header : "+response.getHeader("Content-Type"));
        int statusCode=response.getStatusCode();
        Assert.assertEquals(statusCode, expectedStatusCode);
    }
    @Test
    public void test02()
    {
        //given,when,then
        baseURI="https://reqres.in/api/users";
        given().queryParam("page","2")
                .when().get()
                .then().statusCode(200);
    }
}
