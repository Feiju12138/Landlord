package pojo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * 牌堆对象
 */
public class Deck {

    /*
     * cards：包含全部牌的牌堆
     */
    private List<Card> cards;

    public Deck() {

        // 创建一个牌堆
        cards = new ArrayList<>();
        // 创建52张牌，并放进牌堆
        for (int i = 1; i <= 15; i++) {
            if (i == 15) {
                cards.add(new Card(5, "Joker"));
            } else if (i == 14) {
                cards.add(new Card(6, "Joker"));
            } else if (i == 13) {
                for (int j = 1; j <= 4; j++) {
                    cards.add(new Card(j, "2"));
                }
            } else if (i == 12) {
                for (int j = 1; j <= 4; j++) {
                    cards.add(new Card(j, "A"));
                }
            } else if (i == 11) {
                for (int j = 1; j <= 4; j++) {
                    cards.add(new Card(j, "K"));
                }
            } else if (i == 10) {
                for (int j = 1; j <= 4; j++) {
                    cards.add(new Card(j, "Q"));
                }
            } else if (i == 9) {
                for (int j = 1; j <= 4; j++) {
                    cards.add(new Card(j, "J"));
                }
            } else {
                for (int j = 1; j <= 4; j++) {
                    cards.add(new Card(j, i+2+""));
                }
            }

        }

    }

    // 从牌堆随机取出一张牌
    public Card out() {
        int random = (int)(Math.random()*cards.size());
        Card card = cards.remove(random);
        return card;
    }

    @Override
    public String toString() {
        return "Deck{" +
                "cards=" + cards +
                '}';
    }
}
