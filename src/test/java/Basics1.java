import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

//Burada Basics class ına ek olarak assertion lar eklendi ve
// payLoad da static olarak bir body nin string i eklenerek kod kalabalığı önlenmiş oldu.

public class Basics1 {
    public static void main(String[] args) {
        //validate if Add place API is working as expected
        //given -- all input details
        //when -- submit the API
        //then -- validate the response
        //Add place->(Post metot tipi kullanıldı.)

        RestAssured.baseURI="https://rahulshettyacademy.com";
        given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
                .body(payLoad.addPlace())
                .when().post("maps/api/place/add/json")
                .then().log().all().assertThat().statusCode(200).body("scope",equalTo("APP"))   //Assertion kısmı--assertThat sonrasında istediğin kadar assert ekleyebilirsin.
                //equalTo icin hamcrest manuel olarak import edildi. Otomatik gelmiyor.
                .header("Server","Apache/2.4.41 (Ubuntu)");                  //when sonrasnda yazıldığı için response dönmektedir. yukarıdakinde ise given sonrası olduğu için request header dır.
    }

}
