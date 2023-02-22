import io.restassured.path.json.JsonPath;

public class Basics4 {
    //Elimizde henüz bir response yoksa ve developer ekibinden api henüz gelmemişse,
    //Onları beklemek yerine mock response yani data oluşturup işlemlerimize o şekilde devam edebiliyoruz.

    public static void main(String[] args) {
        JsonPath js = new JsonPath(payLoad.coursePrice());  //herhangi bir request atılmadan mock response işlenmiş oldu
        int count = js.getInt("courses.size");  //printed number of courses returned by API
        System.out.println(count);
        int totalAmount = js.getInt("dashboard.purchaseAmount");    //printed purchase amount
        System.out.println(totalAmount);
        String titleFirstCourses = js.getString("courses[0].title");    //printed title pf the first course
        System.out.println(titleFirstCourses);
    }
}
