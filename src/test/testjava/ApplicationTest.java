package testjava;

import com.example.SupportPhrase;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RequiredArgsConstructor
public class ApplicationTest {

    private final MockMvc mockMvc;

    private final  ObjectMapper objectMapper;

    @Test
    @SneakyThrows
    void getPhraseIsOk(){
        mockMvc.perform(get("/support"))
                .andExpect(jsonPath("$.phrase").value("Держись!"));
    }
    @Test
    @SneakyThrows
    void shouldConsumeMessageFromBrokerAndPathToSubscriber() {
        final var supportPhraseRequest=new SupportPhrase("чо каво");
        final var supportPhrase = new SupportPhrase("чо каво");
        mockMvc.perform(post("/support")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(supportPhraseRequest)))
                .andExpect(status().isOk());
    }
}
