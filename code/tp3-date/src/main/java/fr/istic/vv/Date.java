package fr.istic.vv;

class Date implements Comparable<Date> {
    private final int day;
    private final int month;
    private final int year;

    public Date(int day, int month, int year) throws InvalidDateExcepetion {
        if(isValidDate(day,month,year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            throw new InvalidDateExcepetion();
        }
    }

    private static int numberOfDaysInMonth(int month, int year) {
        return switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> isLeapYear(year) ? 29 : 28;
            default -> -1;
        };
    }

    public static boolean isValidDate(int day, int month, int year) {
        return month <= 12 && day > 0 && day <= numberOfDaysInMonth(month, year);
    }

    public static boolean isLeapYear(int year) { return (year % 4 == 0) && (year % 100 != 0 || year % 400 == 0); }

    public Date nextDate() {
        try {
            if (isValidDate(this.day+1, this.month, this.year)) {
                return new Date(this.day+1, this.month, this.year);
            } else if (isValidDate(1, this.month + 1, this.year)) {
                return new Date(1, this.month+1, this.year);
            } else {
                return new Date(1, 1, this.year+1);
            }
        }
        catch (InvalidDateExcepetion e) {
            return this;
        }
    }

    public Date previousDate() {
        try {
            if (isValidDate(this.day-1, this.month, this.year)) {
                return new Date(this.day-1, this.month, this.year);
            } else if (isValidDate(numberOfDaysInMonth(this.month-1, this.year), this.month - 1, this.year)) {
                return new Date(numberOfDaysInMonth(this.month-1, this.year), this.month - 1, this.year);
            } else {
                return new Date(31, 12, this.year-1);
            }
        }
        catch (InvalidDateExcepetion e) {
            return this;
        }
    }

    public int compareTo(Date other) {
        if (this.year != other.year) {
            return Integer.compare(this.year, other.year);
        } else if (this.month != other.month) {
            return Integer.compare(this.month, other.month);
        } else {
            return Integer.compare(this.day, other.day);
        }
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public static class InvalidDateExcepetion extends Throwable { }
}