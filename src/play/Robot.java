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
 * æºå¨äººð¤ï¸
 */
public class Robot {

    private Player player;

    public Robot(Player player) {
        this.player = player;
    }

    public String autoPlay(PlayArea oldPlayArea) {

        // åå»ºä¸ä¸ªç¨äºå­æ¾æå¥½åºçæ°éå
        List<Card> cardList = new ArrayList<>();

        // åå»ºä¸ä¸ªè®¡æ°å¨Mapï¼è®¡ç®æ¯ä¸ç§çåºç°çæ¬¡æ°
        Map<Integer, Integer> cardsCountMap = new HashMap<>();
        for (Card card : player.getCards()) {
            Integer weights = card.getWeights();
            if (cardsCountMap.containsKey(weights)) {
                cardsCountMap.put(weights, cardsCountMap.get(weights) + 1);
            } else {
                cardsCountMap.put(weights, 1);
            }
        }

        // åå«åå»ºç¨äºå­æ¾åºç°1æ¬¡ãåºç°2æ¬¡ãåºç°3æ¬¡ãåºç°4æ¬¡çéå
        List<Card> cardFor1 = new ArrayList<>();
        List<Card> cardFor2 = new ArrayList<>();
        List<Card> cardFor3 = new ArrayList<>();
        List<Card> cardFor4 = new ArrayList<>();

        // å°ç»è®¡å¥½çæéåºç°æ¬¡æ°ï¼éè¿æéï¼æ¥æ¾å°å¯¹åºççå¯¹è±¡ï¼æçå¯¹è±¡æ¾å°å¯¹åºçåºç°çæ¬¡æ°çéåä¸­
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

        // ä¸ºæ¯ä¸ä¸ªåºç°æ¬¡æ°çå ä¸ªè¿è¡ä»å°å°å¤§æåº
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

        // åå»ºä¸ä¸ªéåå­æ¾éæ©æ³è¦åºçç
        List<Card> goList = new ArrayList<>();

        // éå°éååºçï¼ä¸åºç
//        if (oldPlayArea.getPlayer().getStatus() == player.getStatus()) {
//            return "";
//        }

        // å¦æææ¹ä¸ºç«ç®­ï¼é£ä¹æ å¯¹ç­
        if (oldPlayArea.getCards().size() == 2) {
            if (oldPlayArea.getCards().get(0).getWeights() + oldPlayArea.getCards().get(1).getWeights() == 14 + 15) {
                return "";
            }
        }

        // å¦æä¸ä¸ä¸ªåºççäººæ¯èªå·±ï¼ææ²¡æä¸ä¸æ¬¡åºççäººï¼é£ä¹éä¾¿åºçï¼é»è®¤éæ©æå°çåç
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

        // éå°åççå¯¹ç­
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

        // éå°å¯¹ççå¯¹ç­
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

        // éå°ä¸ççå¯¹ç­
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

        // éå°ä¸å¸¦ä¸çå¯¹ç­
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

        // éå°ä¸å¸¦ä¸å¯¹çå¯¹ç­
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

        // éå°åå¸¦äºçå¯¹ç­
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

        // éå°åå¸¦äºå¯¹çå¯¹ç­
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

        // éå°åé¡ºçå¯¹ç­
        if (oldPlayArea.getStatus() == 10) {
            // é¦åå¤æ­åºçåºç¬¬ä¸å¼ ççæéæ¯å¦å°äº8ï¼ä¹å°±æ¯ç¹æ°å°äº10ï¼å ä¸º`10JQKA`æ¯æå¤§çä¸ç»åé¡ºï¼
            if (oldPlayArea.getCards().get(0).getWeights() < 8) {

                // åå»ºä¸ä¸ªMapå­æ¾æææççweightsï¼è¿æ ·ä¿è¯æ¯ä¸ç§çåªåºç°ä¸æ¬¡
                Map<Integer, Card> map = new HashMap<>();

                for (Card card : player.getCards()) {
                    if (!map.containsKey(card.getWeights())) {
                        map.put(card.getWeights(), card);
                    }
                }
                // åå»ºä¸ä¸ªListå­æ¾ææç§ç±»çæçåªæä¸å¼ 
                List<Card> list = new ArrayList<>();
                // åå»ºè¿­ä»£å¨ç¨äºè¿­ä»£Mapéåä¸­çææå¼
                Iterator<Integer> it = map.keySet().iterator();
                // éè¿è¿­ä»£å¨å°mapçå¼å¨é¨å¯¼å¥listä¸­
                while (it.hasNext()) {
                    int weights = it.next();
                    Card card = map.get(weights);
                    list.add(card);
                }
                // å°listä»å°å°å¤§æåº
                list.sort(new Comparator<Card>() {
                    @Override
                    public int compare(Card o1, Card o2) {
                        return o1.getWeights() - o2.getWeights();
                    }
                });

                // å¾ªç¯éålistï¼æ¥æ¾æ¯å¦æå¤§äºåºçåºç¬¬ä¸å¼ çæéçç
                for (int i = 0; i < list.size(); i++) {
                    // å¦æåºç°äºè¿æ ·çç
                    if (list.get(i).getWeights() > oldPlayArea.getCards().get(0).getWeights()) {
                        // æè¿æ ·ççæ¾å°goListä¸­
                        goList.add(list.get(i));
                        // åä¸ä¸ªåºçåºçæ»æ°çå¾ªç¯-1ï¼å ä¸ºå·²ç»æ¾å°ç¬¬ä¸å¼ çäºï¼è¯¥æ¾å¶ä»ççäºï¼
                        for (int j = 1; j <= oldPlayArea.getCards().size() - 2; j++) {
                            // å¦æå¾åçç¬¬xå¼ ççæéåå¥½ç­äºå½åçæé+xï¼é£ä¹è¯´ææåæ¾å°ä¸ä¸å¼ åå«è¿ä¸ªé¡ºå­ççï¼ç´å°å¨é¨æ¾å®
                            if (list.get(i + j).getWeights() == list.get(i).getWeights() + j) {
                                goList.add(list.get(i + j));
                            }
                        }
                        // å¦æå¨é¨æ¾å®çæ°ååºçåºççæ°ç¸åï¼è¯´ææ¾å¯¹äºï¼å¦ææ²¡æ¾å¯¹ï¼æ¸ç©ºgoListç»§ç»­å¤§å¾ªç¯
                        if (goList.size() == oldPlayArea.getCards().size()) {
                            return autoGo(goList, player);
                        } else {
                            goList.clear();
                        }
                    }
                }

                // ç´å°å¨é¨æ¾ä¸å¯¹ï¼é£å°±åæ­¢å¯¹ç­äºï¼è¯´æå¯¹ç­å¤±è´¥äº
            }
        }

        // éå°åé¡ºçå¯¹ç­
        if (oldPlayArea.getStatus() == 11) {
            // é¦åå¤æ­åºçåºç¬¬ä¸å¼ ççæéæ¯å¦å°äº10ï¼ä¹å°±æ¯ç¹æ°å°äºQï¼å ä¸º`QQKKAA`æ¯æå¤§çä¸ç»åé¡ºï¼
            if (oldPlayArea.getCards().get(0).getWeights() < 10) {

                // å¾ªç¯éålistï¼æ¥æ¾æ¯å¦æå¤§äºåºçåºç¬¬ä¸å¼ çæéçç
                for (int i = 0; i < cardFor2.size(); i++) {
                    // å¦æåºç°äºè¿æ ·çç
                    if (cardFor2.get(i).getWeights() > oldPlayArea.getCards().get(0).getWeights()) {
                        // æè¿æ ·ççæ¾å°goListä¸­
                        goList.add(cardFor2.get(i));
                        goList.add(cardFor2.get(i + 1));
                        // åä¸ä¸ªåºçåºçæ»æ°çå¾ªç¯-1ï¼å ä¸ºå·²ç»æ¾å°ç¬¬ä¸å¼ çäºï¼è¯¥æ¾å¶ä»ççäºï¼
                        for (int j = 1; j <= oldPlayArea.getCards().size() - 2; j++) {
                            // å¦æå¾åçç¬¬xå¼ ççæéåå¥½ç­äºå½åçæé+xï¼é£ä¹è¯´ææåæ¾å°ä¸ä¸å¼ åå«è¿ä¸ªé¡ºå­ççï¼ç´å°å¨é¨æ¾å®
                            if (cardFor2.get(i + (j * 2)).getWeights() == cardFor2.get(i).getWeights() + j) {
                                goList.add(cardFor2.get(i + (j * 2)));
                                goList.add(cardFor2.get(i + (j * 2 + 1)));
                            }
                        }
                        // å¦æå¨é¨æ¾å®çæ°ååºçåºççæ°ç¸åï¼è¯´ææ¾å¯¹äºï¼å¦ææ²¡æ¾å¯¹ï¼æ¸ç©ºgoListç»§ç»­å¤§å¾ªç¯
                        if (goList.size() == oldPlayArea.getCards().size()) {
                            return autoGo(goList, player);
                        } else {
                            goList.clear();
                        }
                    }
                }

            }
        }

        // éå°ä¸é¡ºçå¯¹ç­
        if (oldPlayArea.getStatus() == 12) {
            // é¦åå¤æ­åºçåºç¬¬ä¸å¼ ççæéæ¯å¦å°äº11ï¼ä¹å°±æ¯ç¹æ°å°äºKï¼å ä¸º`KKKAAA`æ¯æå¤§çä¸ç»ä¸é¡ºï¼
            if (oldPlayArea.getCards().get(0).getWeights() < 11) {

                // å¾ªç¯éålistï¼æ¥æ¾æ¯å¦æå¤§äºåºçåºç¬¬ä¸å¼ çæéçç
                for (int i = 0; i < cardFor3.size(); i++) {
                    // å¦æåºç°äºè¿æ ·çç
                    if (cardFor3.get(i).getWeights() > oldPlayArea.getCards().get(0).getWeights()) {
                        // æè¿æ ·ççæ¾å°goListä¸­
                        goList.add(cardFor3.get(i));
                        goList.add(cardFor3.get(i + 1));
                        goList.add(cardFor3.get(i + 2));
                        // åä¸ä¸ªåºçåºçæ»æ°çå¾ªç¯-1ï¼å ä¸ºå·²ç»æ¾å°ç¬¬ä¸å¼ çäºï¼è¯¥æ¾å¶ä»ççäºï¼
                        for (int j = 1; j <= oldPlayArea.getCards().size() - 2; j++) {
                            // å¦æå¾åçç¬¬xå¼ ççæéåå¥½ç­äºå½åçæé+xï¼é£ä¹è¯´ææåæ¾å°ä¸ä¸å¼ åå«è¿ä¸ªé¡ºå­ççï¼ç´å°å¨é¨æ¾å®
                            if (cardFor3.get(i + (j * 3)).getWeights() == cardFor3.get(i).getWeights() + j) {
                                goList.add(cardFor3.get(i + (j * 2)));
                                goList.add(cardFor3.get(i + (j * 2 + 1)));
                                goList.add(cardFor3.get(i + (j * 2 + 2)));
                            }
                        }
                        // å¦æå¨é¨æ¾å®çæ°ååºçåºççæ°ç¸åï¼è¯´ææ¾å¯¹äºï¼å¦ææ²¡æ¾å¯¹ï¼æ¸ç©ºgoListç»§ç»­å¤§å¾ªç¯
                        if (goList.size() == oldPlayArea.getCards().size()) {
                            return autoGo(goList, player);
                        } else {
                            goList.clear();
                        }
                    }
                }

            }
        }

        // éå°é£æº-å¸¦ç¿è-åï¼aaabbbcdï¼çç­ç¥
        if (oldPlayArea.getStatus() == 13) {
            // é¦åå¤æ­åºçåºç¬¬ä¸å¼ ççæéæ¯å¦å°äº11ï¼ä¹å°±æ¯ç¹æ°å°äºKï¼å ä¸º`KKKAAA`æ¯æå¤§çä¸ç»ä¸é¡ºï¼
            if (oldPlayArea.getCards().get(0).getWeights() < 11) {

                // å¾ªç¯éålistï¼æ¥æ¾æ¯å¦æå¤§äºåºçåºç¬¬ä¸å¼ çæéçç
                for (int i = 0; i < cardFor3.size(); i++) {
                    // å¦æåºç°äºè¿æ ·çç
                    if (cardFor3.get(i).getWeights() > oldPlayArea.getCards().get(0).getWeights()) {
                        // æè¿æ ·ççæ¾å°goListä¸­
                        goList.add(cardFor3.get(i));
                        goList.add(cardFor3.get(i + 1));
                        goList.add(cardFor3.get(i + 2));
                        // åä¸ä¸ªå ç»ä¸å¸¦ä¸çå¾ªç¯-1ï¼å ä¸ºå·²ç»æ¾å°ç¬¬ä¸å¼ çäºï¼è¯¥æ¾å¶ä»ççäºï¼
                        for (int j = 1; j <= (oldPlayArea.getCards().size() / 4) - 2; j++) {
                            // å¦æå¾åçç¬¬xå¼ ççæéåå¥½ç­äºå½åçæé+xï¼é£ä¹è¯´ææåæ¾å°ä¸ä¸å¼ åå«è¿ä¸ªé¡ºå­ççï¼ç´å°å¨é¨æ¾å®
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
                        // å¦æå¨é¨æ¾å®çæ°ååºçåºççæ°ç¸åï¼è¯´ææ¾å¯¹äºï¼å¦ææ²¡æ¾å¯¹ï¼æ¸ç©ºgoListç»§ç»­å¤§å¾ªç¯
                        if (goList.size() == oldPlayArea.getCards().size()) {
                            return autoGo(goList, player);
                        } else {
                            goList.clear();
                        }
                    }
                }

            }
        }

        // éå°é£æº-å¸¦ç¿è-å¯¹ï¼aaabbbccddï¼çå¯¹ç­
        if (oldPlayArea.getStatus() == 14) {
            // é¦åå¤æ­åºçåºç¬¬ä¸å¼ ççæéæ¯å¦å°äº11ï¼ä¹å°±æ¯ç¹æ°å°äºKï¼å ä¸º`KKKAAA`æ¯æå¤§çä¸ç»ä¸é¡ºï¼
            if (oldPlayArea.getCards().get(0).getWeights() < 11) {

                // å¾ªç¯éålistï¼æ¥æ¾æ¯å¦æå¤§äºåºçåºç¬¬ä¸å¼ çæéçç
                for (int i = 0; i < cardFor3.size(); i++) {
                    // å¦æåºç°äºè¿æ ·çç
                    if (cardFor3.get(i).getWeights() > oldPlayArea.getCards().get(0).getWeights()) {
                        // æè¿æ ·ççæ¾å°goListä¸­
                        goList.add(cardFor3.get(i));
                        goList.add(cardFor3.get(i + 1));
                        goList.add(cardFor3.get(i + 2));
                        // åä¸ä¸ªå ç»ä¸å¸¦ä¸å¯¹çå¾ªç¯-1ï¼å ä¸ºå·²ç»æ¾å°ç¬¬ä¸å¼ çäºï¼è¯¥æ¾å¶ä»ççäºï¼
                        for (int j = 1; j <= (oldPlayArea.getCards().size() / 5) - 2; j++) {
                            // å¦æå¾åçç¬¬xå¼ ççæéåå¥½ç­äºå½åçæé+xï¼é£ä¹è¯´ææåæ¾å°ä¸ä¸å¼ åå«è¿ä¸ªé¡ºå­ççï¼ç´å°å¨é¨æ¾å®
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
                        // å¦æå¨é¨æ¾å®çæ°ååºçåºççæ°ç¸åï¼è¯´ææ¾å¯¹äºï¼å¦ææ²¡æ¾å¯¹ï¼æ¸ç©ºgoListç»§ç»­å¤§å¾ªç¯
                        if (goList.size() == oldPlayArea.getCards().size()) {
                            return autoGo(goList, player);
                        } else {
                            goList.clear();
                        }
                    }
                }

            }
        }

        // ä»¥ä¸å¯¹ç­å¤±ææ¶ï¼å¯ä»¥å°è¯ä½¿ç¨ç¸å¼¹
        if (oldPlayArea.getStatus() != 2) {
            if (cardFor4.size() != 0) {
                goList.add(cardFor4.get(0));
                goList.add(cardFor4.get(1));
                goList.add(cardFor4.get(2));
                goList.add(cardFor4.get(3));
                return autoGo(goList, player);
            }
        }

        // éå°ç¸å¼¹çå¯¹ç­
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

        // ææå¯¹ç­å®ææ¶ï¼æ¾æ¾æçææ²¡æç«ç®­
        if (player.getCards().get(player.getCards().size()-1).getWeights()+player.getCards().get(player.getCards().size()-2).getWeights()==14+15) {
            goList.add(player.getCards().get(player.getCards().size()-1));
            goList.add(player.getCards().get(player.getCards().size()-2));
            return autoGo(goList, player);
        }

        // æ²¡æçå¯ä»¥åº
        return "";
    }

    public String autoGo(List<Card> goList, Player player) {

        // åå»ºä¸ä¸ªåºçå­ç¬¦ä¸²
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
