
import com.example.innotech_hw1.controllers.ControlServletImpl;
import com.example.springframework.ApplicationContext;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;



public class AplicationContestTest {
    ApplicationContext test = new ApplicationContext();

    @Test
    public void getTwoBeanAfterAplicationContextIsStart(){
        test.scanService("com.example");
        test.scanControllers("com.example");
        test.scanComponent("com.example");
        assertEquals(2,test.getBeansFactory().getInstance().size());
    }
    @Test
    public void getAutowiredBeanAfterAplicationContextIsStart(){
        test.scanComponent("com.example");
        test.scanService("com.example");
        test.scanControllers("com.example");
        test.getAutowired();
        var  csi = new ControlServletImpl();
        csi.getPhrase();
//        System.out.println(csi.phrasesDao.getClass());
//        assertEquals("у тебя все получится",csi.getPhrase());

    }

}
