package Session08;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.*;

public class Authorization {
    @Test
    public void BasicAuth()
    {
        RequestSpecification reqSpec= RestAssured.given();
        reqSpec.baseUri("https://postman-echo.com/");
        reqSpec.basePath("basic-auth");
        Response resp=reqSpec.auth().basic("postman","password").get();
        Assert.assertEquals(resp.getStatusLine(),"HTTP/1.1 200 OK");
        System.out.println("Response Body\n\n"+resp.getBody().asString());
        System.out.println("Header Content-Type\n\n"+resp.getHeader("Content-Type").toString());
        System.out.println("Status Line : "+resp.statusLine());
    }
    @Test
    public void DigestAuth()
    {
        RequestSpecification reqSpec= RestAssured.given();
        reqSpec.baseUri("https://httpbin.org/");
        reqSpec.basePath("digest-auth/undefined/apurba/das");
        Response resp=reqSpec.auth().basic("apurba","das").get();
        Assert.assertEquals(resp.getStatusLine(),"HTTP/1.1 200 OK");
        System.out.println("Response Body :\n"+resp.getBody().asString());
        System.out.println("Header Content-Type :\n"+resp.getHeader("Content-Type").toString());
        System.out.println("Status Line : "+resp.statusLine());
        JsonPath jp=new JsonPath(resp.asString());
        Assert.assertEquals(jp.get("authenticated"),true);
        Assert.assertEquals(jp.get("user"),"apurba");
    }
}
