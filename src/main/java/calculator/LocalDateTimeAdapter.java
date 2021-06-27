package calculator;

import javax.xml.bind.annotation.adapters.XmlAdapter;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class LocalDateTimeAdapter extends XmlAdapter<String, LocalDateTime> {

    @Override
    public LocalDateTime unmarshal(String s) throws Exception {
        if (s == null) {
            return null;
        }
        try {
            return LocalDateTime.parse(s);
        } catch (DateTimeParseException e) {
            System.out.println("Nieudane parsowanie daty " + s);
            return null;
        }
    }

    @Override
    public String marshal(LocalDateTime localDateTime) throws Exception {
        if (localDateTime == null) {
            return null;
        }
        return localDateTime.toString();
    }
}
