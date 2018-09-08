package bankaccount.service;

import bankaccount.util.CurrentDates;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class CurrentDate implements CurrentDates {
    private LocalDate date;

    public CurrentDate() {
        this.date = LocalDate.now();
    }

    @Override
    public CurrentDates getCurrentDate() {
        return new CurrentDate();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CurrentDate that = (CurrentDate) o;
        return Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {

        return Objects.hash(date);
    }

    @Override
    public String toString() {
        return date.format(DateTimeFormatter.ISO_LOCAL_DATE);
    }
}
