package ru.skillbox.dowloader;

import ru.skillbox.exception.DownloadFileException;
import ru.skillbox.exception.WrongFormatUrlException;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class ImageDownloader {

    private final String dirPath;
    private int fileNumber = 0;

    public ImageDownloader(String dirPath) {
        this.dirPath = dirPath;
    }

    public String download(String link) {
        InputStream inStream;
        try {
            URLConnection connection = new URL(link).openConnection();
            inStream = connection.getInputStream();
        } catch (MalformedURLException e) {
            throw new WrongFormatUrlException(link, e);
        } catch (IOException e) {
            throw new DownloadFileException(link, e);
        }

        String filePath = createFilePath(link, dirPath);
        try {
            writeFile(inStream, filePath);
        } catch (IOException e) {
            System.err.println("Ошибка записи файла, файл пропущен:" + filePath);
            e.printStackTrace();
        }

        return filePath;
    }

    public String getDirPath() {
        return dirPath;
    }

    private static void writeFile(InputStream inStream, String filePath) throws IOException {
        FileOutputStream outStream = new FileOutputStream(filePath);
        int b;
        while ((b = inStream.read()) != -1) {
            outStream.write(b);
        }
        outStream.flush();
        outStream.close();
        inStream.close();
    }

    private String createFilePath(String link, String dirPath) {
        String fileExtension = link.replaceAll("^.+\\.", "").replace("?.+$", "");
        return dirPath + "/" + fileNumber++ + "." + fileExtension;
    }
}

