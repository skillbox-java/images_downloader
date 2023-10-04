package ru.skillbox;

import ru.skillbox.dowloader.ImageDownloader;
import ru.skillbox.dowloader.WebScraper;

import java.util.Set;

public class App {
    public static void main(String[] args) {
        String url = "https://skillbox.ru/";

        //относительный путь от корня проекта
        String dirPath = "./images";

        WebScraper webScraper = new WebScraper(url);
        Set<String> links = webScraper.getImageLinks();

        ImageDownloader imageDownloader = new ImageDownloader();

        for (String link : links) {
                download(dirPath, imageDownloader, link);
        }
    }

    private static void download(String dirPath, ImageDownloader imageDownloader, String link) {
        try {
            String downloadedFilePath = imageDownloader.download(link, dirPath);
            System.out.println("Изображение по ссылке " + link + " успешно скачано в файл " + downloadedFilePath);
        } catch (RuntimeException e){
            System.err.println("Ошибка скачивания файла" + link + " в директорию" + dirPath);
            e.printStackTrace();
        }
    }
}