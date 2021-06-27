package calculator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

public class LocalDateTimeAdapterTest {

    @Test
    void testUnmarshal() throws Exception {
        String date = "2021-06-26T11:42:32.845211100";
        String incorrectDate = "2021-bb-26T11:42:32.845211100";

        LocalDateTimeAdapter localDateTimeAdapter = new LocalDateTimeAdapter();

        Assertions.assertEquals(LocalDateTime.parse(date), localDateTimeAdapter.unmarshal(date));

        Assertions.assertNull(localDateTimeAdapter.unmarshal(incorrectDate));

        Assertions.assertNull(localDateTimeAdapter.unmarshal(null));
    }

    @Test
    void testMarshal() throws Exception {
        String dateString = "2021-06-26T11:42:32.845211100";
        LocalDateTime date = LocalDateTime.parse("2021-06-26T11:42:32.845211100");

        LocalDateTimeAdapter localDateTimeAdapter = new LocalDateTimeAdapter();

        Assertions.assertEquals(dateString, localDateTimeAdapter.marshal(date));
        Assertions.assertNull(localDateTimeAdapter.marshal(null));

    }
}
