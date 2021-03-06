package utils;

import model.Game;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class SteamWebScraperUtil {

    private final String LINK = "https://store.steampowered.com/?l=russian";


    public List<Game> getPricesList() throws IOException {
        URL url = new URL(LINK);

        BufferedReader stream = new BufferedReader(new InputStreamReader(url.openStream()));

        Pattern discount = Pattern.compile("<div class=\"discount_block tab_item_discount\"");
        Pattern gameName = Pattern.compile("<div class=\"tab_item_name\">");


        List<String> list = stream.lines().parallel().filter(s -> !s.isEmpty() && (discount.matcher(s).find() || gameName.matcher(s).find()))
                .map(String::trim)
                .collect(Collectors.toList());

        List<Game> games = new ArrayList<>();

        for (int i = 0; i < list.size(); i++){
            String it = list.get(i);

            if (discount.matcher(it).find()){
                Game game = new Game();

                Matcher matcher;

                matcher = Pattern.compile("\\d+%").matcher(it);
                if (matcher.find()){
                    game.setDiscountPercent(matcher.group());
                }

                matcher = Pattern.compile("\"discount_original_price\">\\$\\d+.\\d{2}").matcher(it);
                if (matcher.find()){
                    game.setOriginPrice(matcher.group().replaceAll("\".+>",""));
                }

                matcher = Pattern.compile("\"discount_final_price\">\\$\\d+.\\d{2}").matcher(it);
                if (matcher.find()){
                    game.setDiscountPrice(matcher.group().replaceAll("\".+>",""));
                }

                matcher = Pattern.compile(">.+<").matcher(list.get(i + 1));
                if (matcher.find()){
                    game.setName(matcher.group().replaceAll("[><]",""));
                }

                games.add(game);
            }
        }

        games.sort(Comparator.comparing(Game::getDiscountPercent).reversed());
        return games.stream().distinct().collect(Collectors.toList());
    }
}

