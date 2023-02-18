import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

//Burada Basics1 class ına ek olarak

public class Basics2 {
    public static void main(String[] args) {
        //validate if Add place API is working as expected
        //given -- all input details
        //when -- submit the API
        //then -- validate the response
        //Add place-> Update place with new address

        RestAssured.baseURI="https://rahulshettyacademy.com";
        given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
                .body(payLoad.addPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.41 (Ubuntu)");
    }

}
