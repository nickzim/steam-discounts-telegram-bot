package model;

import java.util.Objects;

public final class Game {

    private String name;
    private String originPrice;
    private String discountPrice;
    private String discountPercent;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOriginPrice() {
        return originPrice;
    }

    public void setOriginPrice(String originPrice) {
        this.originPrice = originPrice;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(String discountPercent) {
        this.discountPercent = discountPercent;
    }

    public String getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(String discountPrice) {
        this.discountPrice = discountPrice;
    }

    @Override
    public String toString() {
        return name + " : " + originPrice + " -> " + discountPrice + " : " + discountPercent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(name, game.name) &&
                Objects.equals(originPrice, game.originPrice) &&
                Objects.equals(discountPrice, game.discountPrice) &&
                Objects.equals(discountPercent, game.discountPercent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, originPrice, discountPrice, discountPercent);
    }
}
