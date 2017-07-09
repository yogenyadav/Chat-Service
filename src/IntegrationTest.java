import com.atlassian.hipchat.service.ChatService;
import com.atlassian.hipchat.service.module.ChatServiceModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IntegrationTest {

    public static void main(String[] args) throws IOException {
        IntegrationTest test = new IntegrationTest();
        test.test();
        System.exit(0);
    }

    @Test
    public void test() throws IOException {
        Injector injector = Guice.createInjector(new ChatServiceModule());
        ChatService chatService = injector.getInstance(ChatService.class);
        assertTrue(chatService.getChatContents("some text @chris @matt some text (megusta) (coffee) some" +
                " text http://www.baeldung.com/guide-to-okhttp").similar(getExpected1()));
        assertTrue(chatService.getChatContents("some text").similar(getExpected2()));
        assertTrue(chatService.getChatContents("").similar(getExpected2()));

        try {
            chatService.getChatContents(null);
        } catch(Exception e) {
            assertTrue(e instanceof NullPointerException);
        }
        System.out.println("+-----------------------------------------+");
        System.out.println("IntegrationTest successful, exiting with 0.");
        System.out.println("+-----------------------------------------+");
    }

    private static JSONObject getExpected1() {
        return new JSONObject()
                .put("mentions", new JSONArray().put("chris").put("matt"))
                .put("emoticons", new JSONArray().put("megusta").put("coffee"))
                .put("links", new JSONArray()
                        .put(new JSONObject().put("url", "http://www.baeldung.com/guide-to-okhttp")
                                .put("title", "A Guide to OkHttp | Baeldung")));
    }

    private static JSONObject getExpected2() {
        return new JSONObject()
                .put("mentions", new JSONArray())
                .put("emoticons", new JSONArray())
                .put("links", new JSONArray());
    }
}
