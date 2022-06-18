package com.jpmc.theater;

import org.junit.jupiter.api.Test;
import java.time.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MovieTests {
    @Test
    void testEquals() {
        Movie movie1 = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Movie movie2 = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        assertEquals(movie1, movie2);
        assertTrue( movie1.hashCode()==movie2.hashCode());
    }
    @Test
    void movieWithSpecialDiscount()  {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(18,59,00)));
        assertEquals(10, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void movieWith1stDayDiscount()  {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5, 1);
        Showing showing = new Showing(spiderMan, 5, LocalDateTime.of(LocalDate.now(), LocalTime.of(04,59,00)));
        assertEquals(10, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void movieWith2ndDayDiscount()  {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5,0);
        Showing showing = new Showing(spiderMan, 8, LocalDateTime.of(LocalDate.of(2022, Month.JUNE,07), LocalTime.of(10,59,00)));
        assertEquals(11.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void specialMovieWithTimeDiscount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),12.5,0);
        Showing showing = new Showing(spiderMan, 8, LocalDateTime.of(LocalDate.now(), LocalTime.of(11,20,10)));
        assertEquals(9.375, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void movieWithTime11Discount()  {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10,0);
        Showing showing = new Showing(spiderMan, 8, LocalDateTime.of(LocalDate.now(), LocalTime.of(11,00,00)));
        assertEquals(7.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void movieWithTime4Discount() {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10,0);
        Showing showing = new Showing(spiderMan, 8, LocalDateTime.of(LocalDate.now(), LocalTime.of(16,00,00)));
        assertEquals(7.5, spiderMan.calculateTicketPrice(showing));
    }

    @Test
    void movieWithTimeRangeDiscount()  {
        Movie spiderMan = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90),10,0);
        Showing showing = new Showing(spiderMan, 8, LocalDateTime.of(LocalDate.now(), LocalTime.of(16,00,00)));
        assertEquals(7.5, spiderMan.calculateTicketPrice(showing));
    }
}
