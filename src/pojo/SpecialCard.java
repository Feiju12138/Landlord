package pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 地主的三张特殊牌
 */
public class SpecialCard {

    private List<Card> cards;

    public SpecialCard() {
        cards = new ArrayList<>();
    }

    public List<Card> getCards() {
        return cards;
    }

    @Override
    public String toString() {
        return "SpecialCard{" +
                "cards=" + cards +
                '}';
    }

    // 添加地主特殊牌
    public void addCard(Card card) {
        cards.add(card);
    }
}
