import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

public class MainClassTest extends  MainClass{


    @Test
    public void testGetLocalNumber() {
        Assert.assertTrue("getLocalNumber != 14",this.getLocalNumber() == 14);
    }

    @Test
    public void testGetClassNumber() {
        Assert.assertTrue("getClassNumber < 45",this.getClassNumber() > 45);
    }
    @Test
    public void testGetClassString() {
        String text = this.getClassString();

        Assert.assertTrue("String doesn't contain either hello or Hello",
                text.contains("hello") || text.contains("Hello"));}

}
