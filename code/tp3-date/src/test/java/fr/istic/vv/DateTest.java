package fr.istic.vv;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.junit.jupiter.api.Assertions.*;

class DateTest {

    @ParameterizedTest(name = "{index} => day={0}, month={1}, year={2}")
    @CsvFileSource(resources = "/validDates.csv")
    void testPreviousDate(int day, int month, int year) {
        try {
            Date date = new Date(day, month, year);
            Date previousDate = date.previousDate();
            assertTrue(Date.isValidDate(previousDate.getDay(), previousDate.getMonth(), previousDate.getYear()));
        } catch (Date.InvalidDateExcepetion invalidDateExcepetion) {
            fail("La date testée n'est pas valide");
        }
    }

    @ParameterizedTest(name = "{index} => day={0}, month={1}, year={2}")
    @CsvFileSource(resources = "/validDates.csv")
    void testNextDate(int day, int month, int year) {
        try {
            Date date = new Date(day, month, year);
            Date nextDate = date.nextDate();
            assertTrue(Date.isValidDate(nextDate.getDay(), nextDate.getMonth(), nextDate.getYear()));
        } catch (Date.InvalidDateExcepetion invalidDateExcepetion) {
            fail("La date testée n'est pas valide");
        }
    }

    @ParameterizedTest(name = "{index} => day={0}, month={1}, year={2}")
    @CsvFileSource(resources = "/validDates.csv")
    void testConstructor(int day, int month, int year) {
        try {
            Date date = new Date(day, month, year);
            assertTrue(Date.isValidDate(day, month, year));
        } catch (Date.InvalidDateExcepetion invalidDateExcepetion) {
            assertFalse(Date.isValidDate(day, month, year));
        }
    }

    @ParameterizedTest(name = "{index} => day={0}, month={1}, year={2}")
    @CsvFileSource(resources = "/validDates.csv")
    void testValidDate(int day, int month, int year) {
        assertTrue(Date.isValidDate(day, month, year));
    }

    @ParameterizedTest(name = "{index} => day={0}, month={1}, year={2}")
    @CsvFileSource(resources = "/invalidDates.csv")
    void testInvalidDate(int day, int month, int year) {
        assertFalse(Date.isValidDate(day, month, year));
        assertThrows(Date.InvalidDateExcepetion.class, () -> new Date(day, month, year));
    }

    @Test
    void testPosterieurCompareTo() {
        try {
            Date date = new Date(10, 1, 23);

            Date posterieurDay = new Date(11, 1, 23);

            Date posterieurMonth = new Date(9, 2, 23);
            Date posterieurMonth2 = new Date(10, 2, 23);
            Date posterieurMonth3 = new Date(12, 2, 23);

            Date posterieurYear0 = new Date(9, 1, 24);
            Date posterieurYear1 = new Date(10, 1, 24);
            Date posterieurYear2 = new Date(11, 1, 24);

            Date posterieurYear3 = new Date(9, 2, 24);

            assertTrue(date.compareTo(posterieurDay) < 0);

            assertTrue(date.compareTo(posterieurMonth) < 0);
            assertTrue(date.compareTo(posterieurMonth2) < 0);
            assertTrue(date.compareTo(posterieurMonth3) < 0);

            assertTrue(date.compareTo(posterieurYear0) < 0);
            assertTrue(date.compareTo(posterieurYear1) < 0);
            assertTrue(date.compareTo(posterieurYear2) < 0);
            assertTrue(date.compareTo(posterieurYear3) < 0);
        } catch (Date.InvalidDateExcepetion e) {
            fail("La date testée n'est pas valide");
        }
    }

    @Test
    void testEqualsCompareTo() {
        try {
            Date date = new Date(10, 1, 23);
            Date other = new Date(10, 1, 23);

            assertEquals(0, date.compareTo(other));
        } catch (Date.InvalidDateExcepetion e) {
            fail("La date testée n'est pas valide");
        }
    }

    @Test
    void testAnterieurCompareTo() {
        try {
            Date date = new Date(15, 6, 23);

            Date anterieurDay = new Date(14, 6, 23);

            Date anterieurMonth = new Date(16, 5, 23);
            Date anterieurMonth2 = new Date(15, 5, 23);
            Date anterieurMonth3 = new Date(14, 5, 23);

            Date anterieurYear0 = new Date(16, 6, 22);
            Date anterieurYear1 = new Date(15, 6, 22);
            Date anterieurYear2 = new Date(14, 6, 22);

            Date anterieurYear3 = new Date(16, 8, 22);

            assertTrue(date.compareTo(anterieurDay) > 0);

            assertTrue(date.compareTo(anterieurMonth) > 0);
            assertTrue(date.compareTo(anterieurMonth2) > 0);
            assertTrue(date.compareTo(anterieurMonth3) > 0);

            assertTrue(date.compareTo(anterieurYear0) > 0);
            assertTrue(date.compareTo(anterieurYear1) > 0);
            assertTrue(date.compareTo(anterieurYear2) > 0);
            assertTrue(date.compareTo(anterieurYear3) > 0);
        } catch (Date.InvalidDateExcepetion e) {
            fail("La date testée n'est pas valide");
        }
    }


}