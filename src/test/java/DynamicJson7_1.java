 import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
 import org.testng.annotations.DataProvider;
 import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson7_1 {
    //dataProvider ile iki boyutlu array oluşturuldu ve değerler buradan çekildi.

    @Test(dataProvider = "BooksData")       //hangi data nın çekileceği ismi ile belirtildi.
    public void addBook(String isbn,String aisle){      //String parametreler aşağıdaki data dan çekildi. Eşit oplması gerekiyor parametre sayıları.
        RestAssured.baseURI = "http://216.10.245.166";
        String response = given().header("Content-Type","application/json")
                .body(payLoad.AddBook(isbn,aisle)).                   //Her istek atıldığında id farklı istiyor o yüzden aisle id si değiştirilmeli.
        when().post("/Library/Addbook.php")
                .then().log().all().assertThat().statusCode(200)
                .extract().response().asString();
        JsonPath js = reusableMethods.rowToJson(response);
        String id = js.get("ID");
        System.out.println(id);
    }

    @DataProvider(name="BooksData")
    public Object[][] getData(){

        return new Object[][] {{"adasd","8552"},{"sdfs","456"},{"fgasdasch","4946"}};
    }

}
