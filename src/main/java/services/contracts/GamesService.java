package services.contracts;


import java.io.IOException;

public interface GamesService {

    String getGamesList(int number) throws IOException;
}
