import com.example.innotech_hw1.dao.PhrasesDaoImpl;
import com.example.springframework.ApplicationContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PHraseDaoTest {
    PhrasesDaoImpl dao = new PhrasesDaoImpl();

    @BeforeEach
    public void beforeEach() {
        ApplicationContext appContext = new ApplicationContext();
        appContext.scanControllers("com.example");
        appContext.scanComponent("com.example");
        appContext.scanService("com.example");
        appContext.getAutowired();

    }

    @Test
    public void PhraseDao_shouldreturn_SupportPhrase() {

        assertEquals(0, dao.getWords().size());
    }
}
