import com.fasterxml.jackson.databind.ObjectMapper;
import model.Human;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class JsonTest {
    ClassLoader cl = JsonTest.class.getClassLoader();

    ObjectMapper mapper = new ObjectMapper();

    @Test
    void jsonTest() throws Exception {

        try (InputStream is = cl.getResourceAsStream("human.json");
             InputStreamReader isr = new InputStreamReader(is)) {

            Human human = mapper.readValue(isr, Human.class);

            Assertions.assertEquals("Nikita", human.getName());
            Assertions.assertEquals(20, human.getAge());
            Assertions.assertEquals(List.of("music", "sport", "books"), human.getHobbies());
        }
    }
}

