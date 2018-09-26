package service;

import core.OperationDate;

import java.time.LocalDate;

public class OperationDateService implements OperationDate {
    @Override
    public LocalDate getCurrentDate() {
        return LocalDate.now();
    }
}
