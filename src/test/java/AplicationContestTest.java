
import com.example.innotech_hw1.controllers.ControlServlet;
import com.example.innotech_hw1.controllers.ControlServletImpl;
import com.example.innotech_hw1.dao.PhrasesDao;
import com.example.innotech_hw1.model.Phrase;
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
        test.scanControllers("com.example");
        PhrasesDao pD= (PhrasesDao)test.getBeansFactory().getBean("PhrasesDao");
        ControlServlet cS =(ControlServlet) test.getBeansFactory().getBean("ControlServlet");
        pD.getWords().add(new Phrase("У тебя все получится"));
        test.getAutowired();
        assertEquals("У тебя все получится",cS.getPhrase());
    }

}
