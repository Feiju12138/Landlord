package play;

import pojo.Card;
import pojo.PlayArea;
import pojo.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
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
                cardsCountMap.put(weights, cardsCountMap.get(weights) + 1);
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
                        if (card.getWeights() == key) {
                            cardFor1.add(card);
                        }
                    }
                    break;
                case 2:
                    for (Card card : player.getCards()) {
                        if (card.getWeights() == key) {
                            cardFor2.add(card);
                        }
                    }
                    break;
                case 3:
                    for (Card card : player.getCards()) {
                        if (card.getWeights() == key) {
                            cardFor3.add(card);
                        }
                    }
                    break;
                case 4:
                    for (Card card : player.getCards()) {
                        if (card.getWeights() == key) {
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
                return o1.getWeights() - o2.getWeights();
            }
        });
        cardFor2.sort(new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getWeights() - o2.getWeights();
            }
        });
        cardFor3.sort(new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getWeights() - o2.getWeights();
            }
        });
        cardFor4.sort(new Comparator<Card>() {
            @Override
            public int compare(Card o1, Card o2) {
                return o1.getWeights() - o2.getWeights();
            }
        });

        // 创建一个集合存放选择想要出的牌
        List<Card> goList = new ArrayList<>();

        // 遇到队友出牌，不出牌
//        if (oldPlayArea.getPlayer().getStatus() == player.getStatus()) {
//            return "";
//        }

        // 如果敌方为火箭，那么无对策
        if (oldPlayArea.getCards().size() == 2) {
            if (oldPlayArea.getCards().get(0).getWeights() + oldPlayArea.getCards().get(1).getWeights() == 14 + 15) {
                return "";
            }
        }

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
            return autoGo(goList, player);
        }

        // 遇到单牌的对策
        if (oldPlayArea.getStatus() == 3) {
            if (!cardFor1.isEmpty()) {
                for (int i = 0; i < cardFor1.size(); i++) {
                    Card card = cardFor1.get(i);
                    if (card.getWeights() > oldPlayArea.getCards().get(0).getWeights()) {
                        goList.add(card);
                        return autoGo(goList, player);
                    }
                }
            }
        }

        // 遇到对牌的对策
        if (oldPlayArea.getStatus() == 4) {
            if (!cardFor2.isEmpty()) {
                for (int i = 0; i < cardFor2.size(); i++) {
                    Card card = cardFor2.get(i);
                    if (card.getWeights() > oldPlayArea.getCards().get(0).getWeights()) {
                        goList.add(card);
                        goList.add(cardFor2.get(i + 1));
                        return autoGo(goList, player);
                    }
                }
            }
        }

        // 遇到三牌的对策
        if (oldPlayArea.getStatus() == 5) {
            if (!cardFor3.isEmpty()) {
                for (int i = 0; i < cardFor3.size(); i++) {
                    Card card = cardFor3.get(i);
                    if (card.getWeights() > oldPlayArea.getCards().get(0).getWeights()) {
                        goList.add(card);
                        goList.add(cardFor3.get(i + 1));
                        goList.add(cardFor3.get(i + 2));
                        return autoGo(goList, player);
                    }
                }
            }
        }

        // 遇到三带一的对策
        if (oldPlayArea.getStatus() == 6) {
            if (!cardFor3.isEmpty()) {
                for (int i = 0; i < cardFor3.size(); i++) {
                    Card card = cardFor3.get(i);
                    if (card.getWeights() > oldPlayArea.getCards().get(0).getWeights()) {
                        goList.add(card);
                        goList.add(cardFor3.get(i + 1));
                        goList.add(cardFor3.get(i + 2));
                        break;
                    }
                }
                if (!cardFor1.isEmpty()) {
                    goList.add(cardFor1.get(0));
                    return autoGo(goList, player);
                } else {
                    goList.clear();
                }
            }
        }

        // 遇到三带一对的对策
        if (oldPlayArea.getStatus() == 7) {
            if (!cardFor3.isEmpty()) {
                for (int i = 0; i < cardFor3.size(); i++) {
                    Card card = cardFor3.get(i);
                    if (card.getWeights() > oldPlayArea.getCards().get(0).getWeights()) {
                        goList.add(card);
                        goList.add(cardFor3.get(i + 1));
                        goList.add(cardFor3.get(i + 2));
                        break;
                    }
                }
                if (!cardFor2.isEmpty()) {
                    goList.add(cardFor2.get(0));
                    goList.add(cardFor2.get(1));
                    return autoGo(goList, player);
                } else {
                    goList.clear();
                }
            }
        }

        // 遇到四带二的对策
        if (oldPlayArea.getStatus() == 8) {
            if (!cardFor4.isEmpty()) {
                for (int i = 0; i < cardFor4.size(); i++) {
                    Card card = cardFor4.get(i);
                    if (card.getWeights() > oldPlayArea.getCards().get(0).getWeights()) {
                        goList.add(card);
                        goList.add(cardFor4.get(i + 1));
                        goList.add(cardFor4.get(i + 2));
                        goList.add(cardFor4.get(i + 3));
                        break;
                    }
                }
                if (cardFor1.size() >= 2) {
                    goList.add(cardFor1.get(0));
                    goList.add(cardFor1.get(1));
                    return autoGo(goList, player);
                } else {
                    goList.clear();
                }
            }
        }

        // 遇到四带二对的对策
        if (oldPlayArea.getStatus() == 9) {
            if (!cardFor4.isEmpty()) {
                for (int i = 0; i < cardFor4.size(); i++) {
                    Card card = cardFor4.get(i);
                    if (card.getWeights() > oldPlayArea.getCards().get(0).getWeights()) {
                        goList.add(card);
                        goList.add(cardFor4.get(i + 1));
                        goList.add(cardFor4.get(i + 2));
                        goList.add(cardFor4.get(i + 3));
                        break;
                    }
                }
                if (cardFor2.size() >= 4) {
                    goList.add(cardFor2.get(0));
                    goList.add(cardFor2.get(1));
                    goList.add(cardFor2.get(2));
                    goList.add(cardFor2.get(3));
                    return autoGo(goList, player);
                } else {
                    goList.clear();
                }
            }
        }

        // 遇到单顺的对策
        if (oldPlayArea.getStatus() == 10) {
            // 首先判断出牌区第一张牌的权重是否小于8，也就是点数小于10（因为`10JQKA`是最大的一组单顺）
            if (oldPlayArea.getCards().get(0).getWeights() < 8) {

                // 创建一个Map存放所有手牌的weights，这样保证每一种牌只出现一次
                Map<Integer, Card> map = new HashMap<>();

                for (Card card : player.getCards()) {
                    if (!map.containsKey(card.getWeights())) {
                        map.put(card.getWeights(), card);
                    }
                }
                // 创建一个List存放所有种类的手牌只有一张
                List<Card> list = new ArrayList<>();
                // 创建迭代器用于迭代Map集合中的所有值
                Iterator<Integer> it = map.keySet().iterator();
                // 通过迭代器将map的值全部导入list中
                while (it.hasNext()) {
                    int weights = it.next();
                    Card card = map.get(weights);
                    list.add(card);
                }
                // 将list从小到大排序
                list.sort(new Comparator<Card>() {
                    @Override
                    public int compare(Card o1, Card o2) {
                        return o1.getWeights() - o2.getWeights();
                    }
                });

                // 循环遍历list，查找是否有大于出牌区第一张牌权重的牌
                for (int i = 0; i < list.size(); i++) {
                    // 如果出现了这样的牌
                    if (list.get(i).getWeights() > oldPlayArea.getCards().get(0).getWeights()) {
                        // 把这样的牌放到goList中
                        goList.add(list.get(i));
                        // 做一个出牌区牌总数的循环-1（因为已经找到第一张牌了，该找其他的牌了）
                        for (int j = 1; j <= oldPlayArea.getCards().size() - 2; j++) {
                            // 如果往后的第x张牌的权重刚好等于当前牌权重+x，那么说明成功找到下一张包含这个顺子的牌，直到全部找完
                            if (list.get(i + j).getWeights() == list.get(i).getWeights() + j) {
                                goList.add(list.get(i + j));
                            }
                        }
                        // 如果全部找完牌数和出牌区的牌数相同，说明找对了，如果没找对，清空goList继续大循环
                        if (goList.size() == oldPlayArea.getCards().size()) {
                            return autoGo(goList, player);
                        } else {
                            goList.clear();
                        }
                    }
                }

                // 直到全部找不对，那就停止对策了，说明对策失败了
            }
        }

        // 遇到双顺的对策
        if (oldPlayArea.getStatus() == 11) {
            // 首先判断出牌区第一张牌的权重是否小于10，也就是点数小于Q（因为`QQKKAA`是最大的一组双顺）
            if (oldPlayArea.getCards().get(0).getWeights() < 10) {

                // 循环遍历list，查找是否有大于出牌区第一张牌权重的牌
                for (int i = 0; i < cardFor2.size(); i++) {
                    // 如果出现了这样的牌
                    if (cardFor2.get(i).getWeights() > oldPlayArea.getCards().get(0).getWeights()) {
                        // 把这样的牌放到goList中
                        goList.add(cardFor2.get(i));
                        goList.add(cardFor2.get(i + 1));
                        // 做一个出牌区牌总数的循环-1（因为已经找到第一张牌了，该找其他的牌了）
                        for (int j = 1; j <= oldPlayArea.getCards().size() - 2; j++) {
                            // 如果往后的第x张牌的权重刚好等于当前牌权重+x，那么说明成功找到下一张包含这个顺子的牌，直到全部找完
                            if (cardFor2.get(i + (j * 2)).getWeights() == cardFor2.get(i).getWeights() + j) {
                                goList.add(cardFor2.get(i + (j * 2)));
                                goList.add(cardFor2.get(i + (j * 2 + 1)));
                            }
                        }
                        // 如果全部找完牌数和出牌区的牌数相同，说明找对了，如果没找对，清空goList继续大循环
                        if (goList.size() == oldPlayArea.getCards().size()) {
                            return autoGo(goList, player);
                        } else {
                            goList.clear();
                        }
                    }
                }

            }
        }

        // 遇到三顺的对策
        if (oldPlayArea.getStatus() == 12) {
            // 首先判断出牌区第一张牌的权重是否小于11，也就是点数小于K（因为`KKKAAA`是最大的一组三顺）
            if (oldPlayArea.getCards().get(0).getWeights() < 11) {

                // 循环遍历list，查找是否有大于出牌区第一张牌权重的牌
                for (int i = 0; i < cardFor3.size(); i++) {
                    // 如果出现了这样的牌
                    if (cardFor3.get(i).getWeights() > oldPlayArea.getCards().get(0).getWeights()) {
                        // 把这样的牌放到goList中
                        goList.add(cardFor3.get(i));
                        goList.add(cardFor3.get(i + 1));
                        goList.add(cardFor3.get(i + 2));
                        // 做一个出牌区牌总数的循环-1（因为已经找到第一张牌了，该找其他的牌了）
                        for (int j = 1; j <= oldPlayArea.getCards().size() - 2; j++) {
                            // 如果往后的第x张牌的权重刚好等于当前牌权重+x，那么说明成功找到下一张包含这个顺子的牌，直到全部找完
                            if (cardFor3.get(i + (j * 3)).getWeights() == cardFor3.get(i).getWeights() + j) {
                                goList.add(cardFor3.get(i + (j * 2)));
                                goList.add(cardFor3.get(i + (j * 2 + 1)));
                                goList.add(cardFor3.get(i + (j * 2 + 2)));
                            }
                        }
                        // 如果全部找完牌数和出牌区的牌数相同，说明找对了，如果没找对，清空goList继续大循环
                        if (goList.size() == oldPlayArea.getCards().size()) {
                            return autoGo(goList, player);
                        } else {
                            goList.clear();
                        }
                    }
                }

            }
        }

        // 遇到飞机-带翅膀-单（aaabbbcd）的策略
        if (oldPlayArea.getStatus() == 13) {
            // 首先判断出牌区第一张牌的权重是否小于11，也就是点数小于K（因为`KKKAAA`是最大的一组三顺）
            if (oldPlayArea.getCards().get(0).getWeights() < 11) {

                // 循环遍历list，查找是否有大于出牌区第一张牌权重的牌
                for (int i = 0; i < cardFor3.size(); i++) {
                    // 如果出现了这样的牌
                    if (cardFor3.get(i).getWeights() > oldPlayArea.getCards().get(0).getWeights()) {
                        // 把这样的牌放到goList中
                        goList.add(cardFor3.get(i));
                        goList.add(cardFor3.get(i + 1));
                        goList.add(cardFor3.get(i + 2));
                        // 做一个几组三带一的循环-1（因为已经找到第一张牌了，该找其他的牌了）
                        for (int j = 1; j <= (oldPlayArea.getCards().size() / 4) - 2; j++) {
                            // 如果往后的第x张牌的权重刚好等于当前牌权重+x，那么说明成功找到下一张包含这个顺子的牌，直到全部找完
                            if (cardFor3.get(i + (j * 3)).getWeights() == cardFor3.get(i).getWeights() + j) {
                                goList.add(cardFor3.get(i + (j * 2)));
                                goList.add(cardFor3.get(i + (j * 2 + 1)));
                                goList.add(cardFor3.get(i + (j * 2 + 2)));
                            }
                        }
                        if (cardFor1.size() >= oldPlayArea.getCards().size() / 4) {
                            for (int j = 0; j < oldPlayArea.getCards().size() / 4; j++) {
                                goList.add(cardFor1.get(j));
                            }
                        }
                        // 如果全部找完牌数和出牌区的牌数相同，说明找对了，如果没找对，清空goList继续大循环
                        if (goList.size() == oldPlayArea.getCards().size()) {
                            return autoGo(goList, player);
                        } else {
                            goList.clear();
                        }
                    }
                }

            }
        }

        // 遇到飞机-带翅膀-对（aaabbbccdd）的对策
        if (oldPlayArea.getStatus() == 14) {
            // 首先判断出牌区第一张牌的权重是否小于11，也就是点数小于K（因为`KKKAAA`是最大的一组三顺）
            if (oldPlayArea.getCards().get(0).getWeights() < 11) {

                // 循环遍历list，查找是否有大于出牌区第一张牌权重的牌
                for (int i = 0; i < cardFor3.size(); i++) {
                    // 如果出现了这样的牌
                    if (cardFor3.get(i).getWeights() > oldPlayArea.getCards().get(0).getWeights()) {
                        // 把这样的牌放到goList中
                        goList.add(cardFor3.get(i));
                        goList.add(cardFor3.get(i + 1));
                        goList.add(cardFor3.get(i + 2));
                        // 做一个几组三带一对的循环-1（因为已经找到第一张牌了，该找其他的牌了）
                        for (int j = 1; j <= (oldPlayArea.getCards().size() / 5) - 2; j++) {
                            // 如果往后的第x张牌的权重刚好等于当前牌权重+x，那么说明成功找到下一张包含这个顺子的牌，直到全部找完
                            if (cardFor3.get(i + (j * 3)).getWeights() == cardFor3.get(i).getWeights() + j) {
                                goList.add(cardFor3.get(i + (j * 2)));
                                goList.add(cardFor3.get(i + (j * 2 + 1)));
                                goList.add(cardFor3.get(i + (j * 2 + 2)));
                            }
                        }
                        if (cardFor2.size() >= oldPlayArea.getCards().size() / 5) {
                            for (int j = 0; j < oldPlayArea.getCards().size() / 5; j+=2) {
                                goList.add(cardFor2.get(j));
                                goList.add(cardFor2.get(j+1));
                            }
                        }
                        // 如果全部找完牌数和出牌区的牌数相同，说明找对了，如果没找对，清空goList继续大循环
                        if (goList.size() == oldPlayArea.getCards().size()) {
                            return autoGo(goList, player);
                        } else {
                            goList.clear();
                        }
                    }
                }

            }
        }

        // 以上对策失效时，可以尝试使用炸弹
        if (oldPlayArea.getStatus() != 2) {
            if (cardFor4.size() != 0) {
                goList.add(cardFor4.get(0));
                goList.add(cardFor4.get(1));
                goList.add(cardFor4.get(2));
                goList.add(cardFor4.get(3));
                return autoGo(goList, player);
            }
        }

        // 遇到炸弹的对策
        if (oldPlayArea.getStatus() == 2) {
            if (cardFor4.size() != 0) {
                for (int i = 0; i < cardFor4.size(); i+=4) {
                    if (cardFor4.get(i).getWeights()>oldPlayArea.getCards().get(0).getWeights()) {
                        goList.add(cardFor4.get(i));
                        goList.add(cardFor4.get(i+1));
                        goList.add(cardFor4.get(i+2));
                        goList.add(cardFor4.get(i+3));
                        return autoGo(goList, player);
                    }
                }
            }
        }

        // 所有对策实效时，找找手牌有没有火箭
        if (player.getCards().get(player.getCards().size()-1).getWeights()+player.getCards().get(player.getCards().size()-2).getWeights()==14+15) {
            goList.add(player.getCards().get(player.getCards().size()-1));
            goList.add(player.getCards().get(player.getCards().size()-2));
            return autoGo(goList, player);
        }

        // 没有牌可以出
        return "";
    }

    public String autoGo(List<Card> goList, Player player) {

        // 创建一个出牌字符串
        String goStr = "";

        for (int i = 0; i < goList.size(); i++) {

            Card goCard = goList.get(i);

            for (int j = 0; j < player.getCards().size(); j++) {
                if (player.getCards().get(j).equals(goCard)) {
                    goStr += j;
                    break;
                }
            }

            if (i != goList.size() - 1) {
                goStr += " ";
            }

        }

        return goStr;
    }


}
