package ru.job4j.interfaces;

import ru.job4j.Req;
import ru.job4j.Resp;

public interface Service {

    Resp process(Req req);
}
