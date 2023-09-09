package by.bsuir.lab1.task1;
import org.junit.Assert;
import org.junit.Test;
public class TaskTest {
    @Test
    public void test_1(){
        double actual = Task.solve(1.0, 2.1);
        double expected = 1.38;
        Assert.assertEquals(expected, actual, 0.1);
    }

}
