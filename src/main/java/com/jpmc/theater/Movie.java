package com.jpmc.theater;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.*;

public class Movie {

    private String title;
    private Duration runningTime;
    private double ticketPrice;
    private int specialCode;

    public Movie(String title, Duration runningTime, double ticketPrice, int specialCode) {
        this.title = title;
        this.runningTime = runningTime;
        this.ticketPrice = ticketPrice;
        this.specialCode = specialCode;
    }

    public String getTitle() {
        return title;
    }

    public Duration getRunningTime() {
        return runningTime;
    }

    public double calculateTicketPrice(Showing showing) {
        return ticketPrice - getDiscount(showing);
    }
    private double getDiscount(Showing showing) {
        int showSequence = showing.getSequenceOfTheDay();
        ArrayList<Double> discounts = new ArrayList<>();
        if (Constant.MOVIE_CODE_SPECIAL == specialCode) {
            discounts.add(ticketPrice * 0.2);  // 20% discount for special movie
        }
        if (showSequence == 1) {
            discounts.add(Double.valueOf(3)); // $3 discount for 1st show
        } else if (showSequence == 2) {
            discounts.add(Double.valueOf(2)); // $2 discount for 2nd show
        }
        if (showing.getStartTime().getDayOfMonth() == 7) {
            discounts.add(Double.valueOf(1)); //$1 discount for shows on 7th
        }
        int index = showing.getStartTime().toString().indexOf("T");
        Date time = null;
        Date rangeFrom = null;
        Date rangeTo = null;
        try {
            time = new SimpleDateFormat("HH:mm").parse(showing.getStartTime().toString().substring(index + 1));
            rangeFrom = new SimpleDateFormat("HH:mm").parse("10:59");
            rangeTo = new SimpleDateFormat("HH:mm").parse("16:01");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        if (time.after(rangeFrom) && time.before(rangeTo)) {
            discounts.add(ticketPrice * 0.25);  //25% discount for Shows Between 11 AM to 4 PM
        }
        //Finding Highest Discount from the list
       double discount = (discounts.size() > 0) ? Collections.max(discounts) : 0;
        return discount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Double.compare(movie.ticketPrice, ticketPrice) == 0
                && Objects.equals(title, movie.title)
                && Objects.equals(runningTime, movie.runningTime)
                && Objects.equals(specialCode, movie.specialCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, runningTime, ticketPrice, specialCode);
    }
}