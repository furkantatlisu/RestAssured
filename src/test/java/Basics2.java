import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

//Burada Basics1 class ına ek olarak, response da eklenilen log kaldırıldı,
// String formatındaki response json ile parse edilerek id çekildi
//put metodu ile adres güncellendi.

public class Basics2 {
    public static void main(String[] args) {
        //validate if Add place API is working as expected
        //given -- all input details
        //when -- submit the API
        //then -- validate the response
        //Add place-> Update place with new address (Put metot tipi kullanıldı.)

        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
                .body(payLoad.addPlace())
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.52 (Ubuntu)").extract().response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response);             //Sring formatını json formatına çeviriyor.
        String placeId = js.getString("place_id");  //place_id nin parent ya da child ı olmadığı için direkt çağrıldı.
        System.out.println("place_id: " + placeId);

        System.out.println("\n**************************\n");
        //Update place with new address
        given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+placeId+"\",\n" +
                        "\"address\":\"Umraniye/Istanbul\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));
    }

}
