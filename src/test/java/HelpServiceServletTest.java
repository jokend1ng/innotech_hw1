
import com.example.innotech_hw1.dao.PhrasesDao;
import com.example.innotech_hw1.DispatcherServlet;
import com.example.springframework.ApplicationContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;




public class HelpServiceServletTest {
    private HttpServletRequest request;
    private HttpServletResponse response;
    private DispatcherServlet servlet;

    @BeforeEach
    public void before() throws ServletException {
        var test =new ApplicationContext();
        test.scanService("com.example");
        test.scanControllers("com.example");
        test.scanComponent("com.example");
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        PhrasesDao dao= (PhrasesDao) test.getBeansFactory().getBean("PhrasesDao");
        servlet = new DispatcherServlet();
        test.getAutowired();
        servlet.init();

    }

    @Test
    void getTextFromResponse() throws IOException {
        StringWriter stringWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
        servlet.doGet(request, response);
        assertEquals(servlet.getDao().getWords().size(), 1);

    }

    @Test
    void getContentTypeFromResponse() throws IOException {
        StringWriter stringWriter = new StringWriter();
        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
        when(response.getContentType()).thenReturn("text/plain");
        servlet.doGet(request, response);
        assertEquals(response.getContentType(),"text/plain");
    }

    @Test
    void getSizeOfListAfterPost() throws IOException {
        when(request.getReader()).thenReturn(new BufferedReader(new StringReader("Не унывайте пацаны!")));
        servlet.doPost(request, response);
        assertEquals(servlet.getDao().getWords().size(), 2);
    }


}
