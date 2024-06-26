import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

//Burada Basics2 class ına ek olarak,
// alınan place id ile adresin güncellenip güncellenmediği kontrol edildi.
//Assert için TestNG pom.xml içerisine eklendi.
//jsonpath için reusable diye class oluşturuldu ve okunabilirlik artırıldı.

public class Basics3 {
    public static void main(String[] args) {
        //validate if Add place API is working as expected
        //given -- all input details
        //when -- submit the API
        //then -- validate the response
        //Add place-> Update place with new address
        // -> Get place to validate if new address is present in response. (GET metot tipi kullanıldı.)

        RestAssured.baseURI="https://rahulshettyacademy.com";
        String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
                .body(payLoad.addPlace())
                .when().post("maps/api/place/add/json")
                .then().assertThat().statusCode(200).body("scope",equalTo("APP"))
                .header("Server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
        System.out.println(response);
        JsonPath js = reusableMethods.rowToJson(response);             //Sring formatını json formatına çeviriyor.
        String placeId = js.getString("place_id");  //place_id nin parent ya da child ı olmadığı için direkt çağrıldı.
        System.out.println("place_id: " + placeId);

        System.out.println("\n**************************\n");

        //Update place with new address
        String newAddress = "Umraniye/Istanbul";
        given().log().all().queryParam("key", "qaclick123").queryParam("place_id",placeId)
                .body("{\n" +
                        "\"place_id\":\""+placeId+"\",\n" +
                        "\"address\":\""+newAddress+"\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when().put("maps/api/place/update/json")
                .then().log().all().assertThat().statusCode(200).body("msg",equalTo("Address successfully updated"));

        System.out.println("\n\n-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-*-\n");

        //  GET Place

        String getPlaceResponse = given().log().all().queryParam("key","qaclick123").queryParam("place_id",placeId)
                .when().get("maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();
        JsonPath js1 = reusableMethods.rowToJson(getPlaceResponse);    //Jsonpath objesi için metot oluşturuldu.
        String address = js1.getString("address");
//        Assert.assertEquals(address,newAddress);
        System.out.println(address);

    }

}
