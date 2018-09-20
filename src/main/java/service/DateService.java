package service;

import utils.Dates;

import java.time.LocalDate;

public class DateService implements Dates {
    @Override
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}
