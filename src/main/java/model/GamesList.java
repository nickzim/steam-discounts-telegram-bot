package model;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public final class GamesList {

    private List<Game> gameList;

    public GamesList() throws IOException {
        gameList = new SteamWebScraper().getPricesList();
    }

    public String getListString(int number){
        StringBuilder stringBuilder = new StringBuilder();
        AtomicInteger counter = new AtomicInteger(0);

        gameList.stream().limit(number).forEach(game -> stringBuilder.append(counter.incrementAndGet()).append(") ").append(game).append("\n\n"));

        return stringBuilder.toString();
    }
}
