import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class Basics4 {
    //Elimizde henüz bir response yoksa ve developer ekibinden api henüz gelmemişse,
    //Onları beklemek yerine mock response yani data oluşturup işlemlerimize o şekilde devam edebiliyoruz.

    public static void main(String[] args) {

        JsonPath js = new JsonPath(payLoad.coursePrice());  //herhangi bir request atılmadan mock response işlenmiş oldu


        int count = js.getInt("courses.size");  //printed number of courses returned by API
        System.out.println(count);
        System.out.println("1***********");

        int totalAmount = js.getInt("dashboard.purchaseAmount");    //printed purchase amount
        System.out.println(totalAmount);
        System.out.println("2***********");

        String titleFirstCourses = js.getString("courses[2].title");    //printed title of the first course
        System.out.println(titleFirstCourses);
        System.out.println("3***********");

        //Print all course titles and their respective prices.
        for (int i=0;i<count;i++){
            String allCourseTitles = js.get("courses["  +i+  "].title");
            int allCoursePrices = js.get("courses["  +i+  "].price");
            System.out.println(allCourseTitles + ": " +  allCoursePrices);
        }
        System.out.println("4***********");

        //print no of copies sold by RPA course
        for (int i=0;i<count;i++) {          //RPA course daki satılan kopyaları yazdıracak
            String courseTitles = js.get("courses[" +i+ "].title");
            if(courseTitles.equalsIgnoreCase("rpa")){   //equals dan farkı küçük harf duyarlılığı yok
                int copies = js.get("courses[" + i + "].copies");
                System.out.println(copies);
                break;
            }
        }
        System.out.println("5***********");

        //Verify if sum of all course prices matches with purchase amount
        int purchaseAmount = js.get("dashboard.purchaseAmount");
        int sum =0;
        for (int i=0;i<count;i++) {
            int eachCoursePrices = js.get("courses["  +i+  "].price");
            int eachcopies = js.get("courses[" + i + "].copies");
            int amount = eachcopies*eachCoursePrices;
            sum = sum + amount;
        }
        System.out.println(sum);
        Assert.assertEquals(sum,purchaseAmount,"they are equal");
    }
}
