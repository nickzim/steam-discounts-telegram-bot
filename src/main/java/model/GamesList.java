package model;

import utils.SteamWebScraperUtil;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public final class GamesList {


    public String getListString(int number) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger counter = new AtomicInteger(0);

        new SteamWebScraperUtil().getPricesList().stream().limit(number).forEach(game -> stringBuilder.append(counter.incrementAndGet()).append(") ").append(game).append("\n\n"));

        return stringBuilder.toString();
    }
}
