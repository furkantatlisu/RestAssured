import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

//Burada Basics1 class ına ek olarak, response da eklenilen log kaldırıldı,
// onun yerine response extract edilerek parse işlemi uygulandı.
// String formatında old için ise atama yapıldı ve sonrasında istenilen id değeri çekilmiş oldu.

public class Basics2 {
    public static void main(String[] args) {
        //validate if Add place API is working as expected
        //given -- all input details
        //when -- submit the API
        //then -- validate the response
        //Add place-> Update place with new address

        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
                .body(payLoad.addPlace())
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.41 (Ubuntu)").extract().response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response);             //Sring formatını json formatına çeviriyor.
        String placeId = js.getString("place_id");  //place_id nin parent ya da child ı olmadığı için direkt çağrıldı.
        System.out.println("place_id: " + placeId);
    }

}
