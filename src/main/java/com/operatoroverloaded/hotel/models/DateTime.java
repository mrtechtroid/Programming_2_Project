package com.operatoroverloaded.hotel.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTime {
    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;
    private int second;

    // Constructor
    public DateTime(int year, int month, int day, int hour, int minute, int second) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }

    // Parses an ISO 8601 string into a DateTime object
    public static DateTime fromISOString(String isoString) {
        // Parse the ISO string into a LocalDateTime object
        LocalDateTime parsedDateTime = LocalDateTime.parse(
                isoString.replace("Z", ""), // Remove 'Z' for UTC (if present)
                DateTimeFormatter.ISO_DATE_TIME
        );

        // Create and return a new DateTime object
        return new DateTime(
                parsedDateTime.getYear(),
                parsedDateTime.getMonthValue(),
                parsedDateTime.getDayOfMonth(),
                parsedDateTime.getHour(),
                parsedDateTime.getMinute(),
                parsedDateTime.getSecond()
        );
    }

    // Gets the current time
    public static DateTime getCurrentTime() {
        LocalDateTime now = LocalDateTime.now();
        return new DateTime(now.getYear(), now.getMonthValue(), now.getDayOfMonth(), now.getHour(), now.getMinute(), now.getSecond());
    }

    //parse the date and time
    public static DateTime parse(String dateTime) {
        System.out.println(dateTime);
        String[] parts = dateTime.split("-");
        String date = parts[0].replace("/", "-");
        String time = parts[1];
        return fromString(date, time);
    }

    // Checks if the year is a leap year
    public boolean isLeap() {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    // Validates the time
    public boolean isValidTime() {
        return hour >= 0 && hour < 24 && minute >= 0 && minute < 60 && second >= 0 && second < 60;
    }

    private int daysInMonth() {
        int[] days = { 31, isLeap() ? 29 : 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
        return days[month - 1];
    }

    // Validates the date
    public boolean isValidDate() {
        return month > 0 && month <= 12 && day > 0 && day <= daysInMonth();
    }

    // Compares this DateTime to another DateTime
    public int compareTo(DateTime other) {
        LocalDateTime thisDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
        LocalDateTime otherDateTime = LocalDateTime.of(other.year, other.month, other.day, other.hour, other.minute, other.second);
        return thisDateTime.compareTo(otherDateTime);
    }

    // Checks if the current date-time has passed
    public boolean hasPastCurrentTime() {
        DateTime now = getCurrentTime();
        return this.compareTo(now) < 0;
    }

    // Calculates the date difference in days
    public int dateDifference(DateTime other) {
        LocalDateTime thisDate = LocalDateTime.of(year, month, day, 0, 0);
        LocalDateTime otherDate = LocalDateTime.of(other.year, other.month, other.day, 0, 0);
        return (int) ChronoUnit.DAYS.between(thisDate, otherDate);
    }
    
    // Calculates the time difference in seconds
    public int timeDifference(DateTime other) {
        LocalDateTime thisTime = LocalDateTime.of(year, month, day, hour, minute, second);
        LocalDateTime otherTime = LocalDateTime.of(other.year, other.month, other.day, other.hour, other.minute, other.second);
        return (int) ChronoUnit.SECONDS.between(thisTime, otherTime);
    }

    // Increments the day by 1
    public void incrementDay() {
        LocalDateTime dateTime = LocalDateTime.of(year, month, day, hour, minute, second).plusDays(1);
        this.year = dateTime.getYear();
        this.month = dateTime.getMonthValue();
        this.day = dateTime.getDayOfMonth();
    }

    // Calculates the date-time difference in minutes
    public int dateTimeDifference(DateTime other) {
        LocalDateTime thisDateTime = LocalDateTime.of(year, month, day, hour, minute, second);
        LocalDateTime otherDateTime = LocalDateTime.of(other.year, other.month, other.day, other.hour, other.minute, other.second);
        return (int) ChronoUnit.MINUTES.between(thisDateTime, otherDateTime);
    }

    // Difference in days between two dates
    public int dayDiff(DateTime other) {
        return dateDifference(other);
    }

    // Returns a string representation of the time
    public String getTimeString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }

    // Returns a string representation of the date
    public String getDateString() {
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    // Returns a string representation of the date-time
    public String toString(DateTime date) {
        return String.format("%04d-%02d-%02d", date.year, date.month, date.day);
    }

    // Returns a DateTime object from a string
    public static DateTime fromString(String date, String time) {
        if(date.equals("") || time.equals("")) {
            return null;
        }
        String[] dateParts = date.split("-");
        String[] timeParts = time.split(":");
        return new DateTime(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]),
                Integer.parseInt(timeParts[0]), Integer.parseInt(timeParts[1]), Integer.parseInt(timeParts[2]));
    }
}