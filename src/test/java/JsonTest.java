import com.google.gson.Gson;
import model.Human;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;
import java.util.List;

public class JsonTest {

    ClassLoader cl = JsonTest.class.getClassLoader();
    Gson gson = new Gson();

    @Test
    void improvedJsonTest() throws  Exception {
        try (InputStream stream = cl.getResourceAsStream("human.json")) {
            try (Reader reader = new InputStreamReader(stream)) {
                Human human = gson.fromJson(reader, Human.class);

                Assertions.assertEquals("Nikita", human.getName());
                Assertions.assertEquals(20, human.getAge());

                List<String> expectedHobbies = Arrays.asList("music", "sport", "books");

                Assertions.assertEquals(expectedHobbies, human.getNavigationSystem());
            }
        }
    }
}
