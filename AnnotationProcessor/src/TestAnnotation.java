
/**
 * Created by 14792 on 2019/5/4.
 */

public class TestAnnotation {
    @MyAnnotation
    public void test(){

    }

    @MyAnnotation(priority = 1, value = "hi")
    public void test2(){

    }
}
