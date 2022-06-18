package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ReservationTests {

    @Test
    void totalFeeWith1stDayDiscount() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1),
                1,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10,00))
        );
        assertTrue(new Reservation(customer, showing, 3).totalFee() == 28.5);
    }
    @Test
    void totalFeeWith2ndDayDiscount() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Turning Red", Duration.ofMinutes(120), 10, 0),
                2,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10,00))
        );
        assertTrue(new Reservation(customer, showing, 2).totalFee() == 16);
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
    void totalFeeWith7thdayOfmonthDiscount() {
        var customer = new Customer("John Doe", "unused-id");
        var showing = new Showing(
                new Movie("Turning Red", Duration.ofMinutes(120), 10, 1), 3,
                LocalDateTime.of(LocalDate.now(), LocalTime.of(10,00))
        );
        assertTrue(new Reservation(customer, showing, 4).totalFee() == 32);
    }
}
