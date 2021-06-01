package play;

import pojo.Card;
import pojo.PlayArea;
import pojo.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 机器人🤖️
 */
public class Robot {

    private Player player;

    public Robot(Player player) {
        this.player = player;
    }

    public String autoPlay(PlayArea oldPlayArea) {

        // 创建一个用于存放排好序的新集合
        List<Card> cardList = new ArrayList<>();

        // 创建一个计数器Map，计算每一种牌出现的次数
        Map<Integer, Integer> cardsCountMap = new HashMap<>();
        for (Card card : player.getCards()) {
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
                    for (Card card : player.getCards()) {
                        if (card.getWeights()==key) {
                            cardFor1.add(card);
                        }
                    }
                    break;
                case 2:
                    for (Card card : player.getCards()) {
                        if (card.getWeights()==key) {
                            cardFor2.add(card);
                        }
                    }
                    break;
                case 3:
                    for (Card card : player.getCards()) {
                        if (card.getWeights()==key) {
                            cardFor3.add(card);
                        }
                    }
                    break;
                case 4:
                    for (Card card : player.getCards()) {
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

        // 创建一个集合存放选择想要出的牌
        List<Card> goList = new ArrayList<>();

        // 创一个用于确定选牌结束的开关
        boolean key = false;

        // 遇到队友出牌的对策


        // 如果上一个出牌的人是自己，或没有上一次出牌的人，那么随便出牌，默认选择最小的单牌
        if (oldPlayArea.getPlayer().getId() == player.getId() || oldPlayArea.getPlayer().getId() == -1) {
            if (!cardFor1.isEmpty()) {
                goList.add(cardFor1.get(0));
            } else if (!cardFor2.isEmpty()) {
                goList.add(cardFor2.get(0));
            } else if (!cardFor3.isEmpty()) {
                goList.add(cardFor3.get(0));
            } else if (!cardFor4.isEmpty()) {
                goList.add(cardFor4.get(0));
            }
            key = true;
        }

        if (key) {
            return autoGo(goList);
        }

        // 遇到单牌的对策
        if (oldPlayArea.getStatus() == 3) {

        }



        return "";
    }

    public String autoGo(List<Card> goList) {

        // 创建一个出牌字符串
        String goStr = "";


        return goStr;
    }

}
