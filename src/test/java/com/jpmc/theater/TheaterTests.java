package com.jpmc.theater;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TheaterTests {
    @Test
    void totalFeeForCustomer() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Doe", "id-12345");
        Reservation reservation = theater.reserve(john, 2, 4);
        assertEquals(37.5,reservation.totalFee());
    }

    @Test
    void totalFeeForCustomerwithSpecialOffer() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        Customer john = new Customer("John Mark", "id-12345");
        Reservation reservation = theater.reserve(john, 5, 1);
        assertEquals(10, reservation.totalFee());
    }
    @Test
    void printMovieSchedule() {
        Theater theater = new Theater(LocalDateProvider.singleton());
        theater.printSchedule();
    }

    @Test
    void humanReadableFormat(){
        Theater theater = new Theater(LocalDateProvider.singleton());
        Movie movie = new Movie("Spider-Man: No Way Home", Duration.ofMinutes(90), 12.5, 1);
        String result =theater.humanReadableFormat(movie.getRunningTime());
        assertEquals("1 hour 30 minutes",result);
    }
}
