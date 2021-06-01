package utils;

import pojo.Card;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 排序工具类
 */
public class SortCard {

    // 为手牌排序
    public static List<Card> sortForPlayer(List<Card> cards) {

        cards.sort(new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getWeights() - o2.getWeights();
            }
        });

        return cards;
    }

    // 为出牌区排序
    public static List<Card> sortForPlayArea(List<Card> cards) {

        // 首先将全部牌从小到大排序
//        cards.sort(new Comparator<Card>() {
//            @Override
//            public int compare(Card o1, Card o2) {
//                return o1.getWeights() - o2.getWeights();
//            }
//        });

        // 创建一个用于存放排好序的新集合
        List<Card> cardList = new ArrayList<>();

        // 创建一个计数器Map，计算每一种牌出现的次数
        Map<Integer, Integer> cardsCountMap = new HashMap<>();
        for (Card card : cards) {
            Integer weights = card.getWeights();
            if (cardsCountMap.containsKey(weights)) {
                cardsCountMap.put(weights, cardsCountMap.get(weights)+1);
            } else {
                cardsCountMap.put(weights, 1);
            }
        }

        // 分别创建用于存放出现1次、出现2次、出现3次、出现4次的集合
        List<Card> cardFor1 = new ArrayList<>();
        List<Card> cardFor2 = new ArrayList<>();
        List<Card> cardFor3 = new ArrayList<>();
        List<Card> cardFor4 = new ArrayList<>();

        // 将统计好的权重出现次数，通过权重，查找到对应的牌对象，把牌对象放到对应的出现的次数的集合中
        for (Integer key : cardsCountMap.keySet()) {
            Integer count = cardsCountMap.get(key);
            switch (count) {
                case 1:
                    for (Card card : cards) {
                        if (card.getWeights()==key) {
                            cardFor1.add(card);
                        }
                    }
                    break;
                case 2:
                    for (Card card : cards) {
                        if (card.getWeights()==key) {
                            cardFor2.add(card);
                        }
                    }
                    break;
                case 3:
                    for (Card card : cards) {
                        if (card.getWeights()==key) {
                            cardFor3.add(card);
                        }
                    }
                    break;
                case 4:
                    for (Card card : cards) {
                        if (card.getWeights()==key) {
                            cardFor4.add(card);
                        }
                    }
                    break;
            }
        }

        // 为每一个出现次数的几个进行从小到大排序
        cardFor1.sort(new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getWeights()-o2.getWeights();
            }
        });
        cardFor2.sort(new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getWeights()-o2.getWeights();
            }
        });
        cardFor3.sort(new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getWeights()-o2.getWeights();
            }
        });
        cardFor4.sort(new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getWeights()-o2.getWeights();
            }
        });

        // 将集合合并，出现4次>出现3次>出现2次>出现1次
        for (Card card : cardFor4) {
            cardList.add(card);
        }
        for (Card card : cardFor3) {
            cardList.add(card);
        }
        for (Card card : cardFor2) {
            cardList.add(card);
        }
        for (Card card : cardFor1) {
            cardList.add(card);
        }

        return cardList;
    }

}
