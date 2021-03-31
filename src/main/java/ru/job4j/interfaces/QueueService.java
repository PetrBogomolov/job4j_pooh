package ru.job4j.interfaces;

import ru.job4j.Req;
import ru.job4j.Resp;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class QueueService implements Service {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<String>> queue =
            new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String nameQueue = req.nameType();
        String text;
        if (req.method().equals(Resp.POST)) {
            queue.putIfAbsent(nameQueue, new ConcurrentLinkedQueue<>());
            text = req.text();
            queue.get(nameQueue).add(text);
        } else {
            text = queue.getOrDefault(nameQueue, new ConcurrentLinkedQueue<>()).poll();
        }
        return new Resp(text, Resp.STATUS_200);
    }
}
