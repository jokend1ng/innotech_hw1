import com.example.innotech_hw1.PhrasesDao;
import com.example.innotech_hw1.PhrasesDaoImpl;
import com.example.innotech_hw1.SubscriberHandlerBeanPostprocessor;
import org.example.MessageQueue;
import org.example.Publisher;
import org.springframework.context.annotation.Bean;

@org.springframework.boot.test.context.TestConfiguration
public class TestServletConfiguration {

    @Bean
    public Publisher PublisherBean(MessageQueue queue) {
        return new Publisher(queue);
    }

    @Bean
    public MessageQueue MessageQueueBean() {
        return new MessageQueue();
    }
    @Bean
    public SubscriberHandlerBeanPostprocessor SubscriberHandlerBeanPostprocessorBean(MessageQueue queue){
        return  new SubscriberHandlerBeanPostprocessor(queue);
    }

    @Bean
    public PhrasesDao PhrasesDaoBean(){
        return new PhrasesDaoImpl() ;

    }
}
