package ru.job4j;

import ru.job4j.interfaces.Service;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class TopicService implements Service {
    private static final AtomicInteger ID_QUEUE = new AtomicInteger(1);
    private final ConcurrentHashMap<
            String, ConcurrentHashMap<Integer, ConcurrentLinkedQueue<String>>
            > queue = new ConcurrentHashMap<>();

    @Override
    public Resp process(Req req) {
        String nameQueue = req.nameType();
        String text;
        if (req.method().equals(Resp.POST)) {
            queue.putIfAbsent(nameQueue, new ConcurrentHashMap<>());
            text = req.text();
            queue.get(nameQueue).putIfAbsent(ID_QUEUE.getAndIncrement(), new ConcurrentLinkedQueue<>());
            queue.get(nameQueue).get(ID_QUEUE.get()).add(text);
        } else {
            text = queue
                    .getOrDefault(nameQueue, new ConcurrentHashMap<>())
                    .getOrDefault(Integer.valueOf(req.id()), new ConcurrentLinkedQueue<>())
                    .poll();
        }
        return new Resp(text, Resp.STATUS_200);
    }
}
