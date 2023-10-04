package ru.skillbox.exception;

import java.net.MalformedURLException;

public class WrongFormatUrlException extends RuntimeException {
    public WrongFormatUrlException(String link, MalformedURLException e) {
        super("Неверный формат URL" + link, e);
    }
}
