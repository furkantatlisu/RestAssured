import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson7 {

    @Test
    public void addBook(){
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().header("Content-Type","application/json")
                .body(payLoad.AddBook()).                   //Her istek atıldığında id farklı istiyor o yüzden aisle id si değiştirilmeli.
        when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js = reusableMethods.rowToJson(response);
        String id = js.get("ID");
        System.out.println(id);

    }
}
