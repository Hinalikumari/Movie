package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ShowingTests{

    @Test
    void getSequeneOfTheDay() {
         var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10,00))
        );
        assertEquals(1,showing.getSequenceOfTheDay());
    }
    @Test
    void getMovieFee() {
          var showing = new Showing(
                new Movie("Turning Red", Duration.ofMinutes(120), 10, 0),
                2,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10, 0))
        );
        //applied 2nd day discount
        assertEquals(8, showing.getMovieFee());
    }

    @Test
    void getMovie() {
        Movie movie = new Movie("Turning Red", Duration.ofMinutes(120), 10, 0);
        var showing = new Showing(
                new Movie("Turning Red", Duration.ofMinutes(120), 10, 0),
                2,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10,00))
        );
        //applied 2nd day discount
        assertEquals(movie, showing.getMovie());
    }

    @Test
    void totalFeeWithSpecialDiscount() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Turning Red", Duration.ofMinutes(120), 10, 1), 3,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10,00))
        );
        assertTrue(new Reservation(customer, showing, 4).totalFee() == 32);
    }

    @Test
    void getStartTime() {
        Movie movie = new Movie("Turning Red", Duration.ofMinutes(120), 10, 0);
        var showing = new Showing(
                new Movie("Turning Red", Duration.ofMinutes(120), 10, 0),
                2,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10,00))
        );
        //applied 2nd day discount
        assertEquals(LocalDateTime.of(LocalDate.now(), LocalTime.of(10,00)), showing.getStartTime());
    }
}

