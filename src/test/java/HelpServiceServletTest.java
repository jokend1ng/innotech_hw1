//
//import com.example.innotech_hw1.dao.PhrasesDao;
//
//import com.example.innotech_hw1.dao.PhrasesDaoImpl;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import net.bytebuddy.agent.VirtualMachine;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import java.io.*;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//
//
//
//public class HelpServiceServletTest {
//    private HttpServletRequest request;
//    private HttpServletResponse response;
//    private DispatcherServlet servlet;
//
//    @BeforeEach
//    public void before() throws ServletException {
//        request = mock(HttpServletRequest.class);
//        response = mock(HttpServletResponse.class);
//        PhrasesDao dao=new PhrasesDaoImpl();
//        servlet = new DispatcherServlet(dao);
//        servlet.init();
//    }
//
//    @Test
//    void getTextFromResponse() throws IOException {
//        StringWriter stringWriter = new StringWriter();
//        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
//        servlet.doGet(request, response);
//        assertEquals(stringWriter.toString(), "у тебя все получится");
//        assertEquals(servlet.getPhrasesDao().getWords().size(), 1);
//
//    }
//
//    @Test
//    void getContentTypeFromResponse() throws IOException {
//        StringWriter stringWriter = new StringWriter();
//        when(response.getWriter()).thenReturn(new PrintWriter(stringWriter));
//        when(response.getContentType()).thenReturn("text/plain");
//        servlet.doGet(request, response);
//        assertEquals(response.getContentType(),"text/plain");
//    }
//
//    @Test
//    void getSizeOfListAfterPost() throws IOException {
//        when(request.getReader()).thenReturn(new BufferedReader(new StringReader("Не унывайте пацаны!")));
//        servlet.doPost(request, response);
//        assertEquals(servlet.getPhrasesDao().getWords().size(), 2);
//    }
//
//
//}
