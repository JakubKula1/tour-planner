package sk.stuba.fiit.Exceptions;

import javafx.scene.control.DatePicker;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * The InvalidDateException class represents an exception thrown if the inputted date of the beginning of tourne is sooner than week from now.
 * If it is thrown, it will change the wrong date to correct date -> one week from now.
 * It inherits Exception class.
 *
 * @author Jakub Kula
 */
public class InvalidDateException extends Exception{
    public InvalidDateException(DatePicker since, DatePicker until) {
        super("Invalid date "+since.getValue());

        long range = ChronoUnit.DAYS.between(since.getValue(), until.getValue());

        since.setValue(LocalDate.now().plusWeeks(1));
        until.setValue(since.getValue().plusDays(range));
    }
}