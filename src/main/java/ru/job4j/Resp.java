package ru.job4j;

/**
 * Класс для ответа от сервиса
 */
public class Resp {
    public final static String POST = "POST";
    public final static String GET = "GET";
    public final static int STATUS_200 = 200;
    private final String text;
    private final int status;

    public Resp(String text, int status) {
        this.text = text;
        this.status = status;
    }

    public String text() {
        return text;
    }

    public int status() {
        return status;
    }
}
