package ru.skillbox.exception;

import java.io.IOException;

public class DownloadFileException extends RuntimeException {
    public DownloadFileException(String link, IOException e) {
        super("Не удалось скачать файл: " + link, e);
    }
}
