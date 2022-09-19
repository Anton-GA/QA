package smoke;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class Test1 {

    @Test
    public void test1() {
        System.out.println("Test 1");
    }


    @Test (dependsOnMethods = {"test1"}) //указали зависимость от первого метода
    public void test11() {
        System.out.println("Test 11");
    }


}
