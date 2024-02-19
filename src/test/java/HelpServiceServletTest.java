
import com.example.innotech_hw1.HelpServiceServlet;
import com.example.innotech_hw1.Phrase;
import com.example.innotech_hw1.PhrasesDao;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


@WebMvcTest(HelpServiceServlet.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {HelpServiceServletTest.class})
public class HelpServiceServletTest {


        @Autowired
        MockMvc mvc;

        @MockBean
        PhrasesDao repository;

        @Test
        void getRequest() throws Exception {
            Mockito.when(repository.getRandomPhrase()).thenReturn(new Phrase("Any phrase"));
            mvc.perform(MockMvcRequestBuilders.get("/help-service/v1/support"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.phrase").value("Any phrase"));
        }


        @Test
        void postRequest() throws Exception {
            Mockito.doNothing().when(repository).addPhrase(Mockito.any());
            mvc.perform((MockMvcRequestBuilders.post("/help-service/v1/support"))
                            .contentType("application/json")
                            .content("""
                                 {
                                 "phrase" : "Any phrase"
                                 }
                                """));
        }
    }


