package services.impls;

import model.GamesList;
import services.contracts.GamesService;

import java.io.IOException;


public class GamesServiceImpl implements GamesService {


    @Override
    public String getGamesList(int number) throws IOException {
        return new GamesList().getListString(number);
    }

}
