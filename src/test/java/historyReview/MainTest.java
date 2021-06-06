package historyReview;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.internal.verification.Times;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.mockito.Mockito.*;

public class MainTest {

    @Test
    void readDate() {
        EnteringDates enteringDatesMock = mock((EnteringDates.class));
        when(enteringDatesMock.enterDate(Mockito.anyString())).thenReturn("29.05.2021 12:00:00");
        Main main = new Main(enteringDatesMock);
        Assertions.assertEquals(LocalDateTime.of(2021, 5, 29, 12, 0, 0), main.readDate("STRING"));
    }

    @Test
    void readDateWithIncorrectValues() {
        EnteringDates enteringDatesMock = mock((EnteringDates.class));
        when(enteringDatesMock.enterDate(Mockito.anyString())).then(new Answer<String>() {
            int counter = 0;

            @Override
            public String answer(InvocationOnMock invocationOnMock) throws Throwable {
                counter++;
                if (counter == 1) {
                    return "abcd";
                } else if (counter == 2) {
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
                    return LocalDateTime.now().plusDays(1).format(dateTimeFormatter);
                } else {
                    return "29.05.2021 12:00:00";
                }
            }
        });
        Main main = new Main(enteringDatesMock);
        Assertions.assertEquals(LocalDateTime.of(2021, 5, 29, 12, 0, 0), main.readDate("startowa"));
        verify(enteringDatesMock, new Times(3)).enterDate(Mockito.anyString());
    }
}
