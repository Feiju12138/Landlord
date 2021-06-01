package utils;

import pojo.Card;
import pojo.PlayArea;

import java.util.List;

/**
 * 校验类别工具类
 */
public class CheckCard {

    /*
     * 校验出牌区中的牌数组是否合法，返回合法标记
     * status：合法标记
     *  -1：不合法
     *   1：火箭（双王）{2}✅
     *   2：炸弹（aaaa）{4}✅
     *   3：单牌（a）{1}✅
     *   4：对牌（aa）>{2}✅
     *   5：三牌（aaa）{3}✅
     *   6：三带一（aaab）{4}✅
     *   7：三带一对（aaabb）{5}✅
     *   8：四带二（aaaabc）{6}✅
     *   9：四带二对（aaaabbcc）{8}✅
     *  10：单顺[至少5个连续的单不包括2和王]（abcde）{5✅,6✅,7✅,8✅,9✅,10✅,11✅,12✅}
     *  11：双顺[至少3个连续的对不包括2和王]（aabbcc）{6✅,8✅,10✅,12✅,14✅,16✅,18✅,20✅}
     *  12：三顺[至少2个连续的三张不包括2和王]（aaabbb）{6✅,9✅,12✅,15✅,18✅}
     *  13：飞机-带翅膀-单（aaabbbcd）{8}✅
     *  14：飞机-带翅膀-对（aaabbbccdd）{10}✅
     */
    public static Integer isPlayCards(PlayArea playArea) {

        List<Card> cardsUnsort = playArea.getCards();
        List<Card> cards = SortCard.sortForPlayArea(cardsUnsort);

        Integer status = -1;

        // 根据牌数量鉴别类别
        switch (cards.size()) {
            case 1:
                status = 3;

                break;
            case 2:
                int cardWeight1 = cards.get(0).getWeights();
                int cardWeight2 = cards.get(1).getWeights();

                // 判定火箭
                if (cardWeight1 + cardWeight2 == 14 + 15) {
                    status = 1;
                }

                // 判定对牌
                if (cardWeight1 == cardWeight2) {
                    status = 4;
                }

                break;
            case 3:
                cardWeight1 = cards.get(0).getWeights();
                cardWeight2 = cards.get(1).getWeights();
                int cardWeight3 = cards.get(2).getWeights();

                // 判定三牌
                if (cardWeight1 == cardWeight2
                        && cardWeight2 == cardWeight3) {
                    status = 5;
                }

                break;
            case 4:
                cardWeight1 = cards.get(0).getWeights();
                cardWeight2 = cards.get(1).getWeights();
                cardWeight3 = cards.get(2).getWeights();
                int cardWeight4 = cards.get(3).getWeights();

                // 判定炸弹
                if (cardWeight1 == cardWeight2
                        && cardWeight2 == cardWeight3
                        && cardWeight3 == cardWeight4) {
                    status = 2;
                }

                // 判定三带一
                if (cardWeight1 == cardWeight2
                        && cardWeight2 == cardWeight3
                        && cardWeight4 != 14
                        && cardWeight4 != 15) {
                    status = 6;
                }

                break;
            case 5:
                cardWeight1 = cards.get(0).getWeights();
                cardWeight2 = cards.get(1).getWeights();
                cardWeight3 = cards.get(2).getWeights();
                cardWeight4 = cards.get(3).getWeights();
                int cardWeight5 = cards.get(4).getWeights();

                // 判定三带一对
                if (cardWeight1 == cardWeight2
                        && cardWeight2 == cardWeight3
                        && cardWeight4 == cardWeight5) {
                    status = 7;
                }

                // 判定单顺
                int sum = 0;
                for (int i = cardWeight1; i <= cardWeight5; i++) {
                    sum += i;
                }
                int all = 0;
                for (Card card : cards) {
                    all += card.getWeights();
                }
                if (sum == all) {
                    status = 10;
                }

                break;
            case 6:
                cardWeight1 = cards.get(0).getWeights();
                cardWeight2 = cards.get(1).getWeights();
                cardWeight3 = cards.get(2).getWeights();
                cardWeight4 = cards.get(3).getWeights();
                cardWeight5 = cards.get(4).getWeights();
                int cardWeight6 = cards.get(5).getWeights();

                // 判定四带二
                if (cardWeight1 == cardWeight2
                        && cardWeight2 == cardWeight3
                        && cardWeight3 == cardWeight4
                        && cardWeight5 != cardWeight6) {
                    status = 8;
                }

                // 判定单顺
                sum = 0;
                for (int i = cardWeight1; i <= cardWeight6; i++) {
                    sum += i;
                }
                all = 0;
                for (Card card : cards) {
                    all += card.getWeights();
                }
                if (sum == all) {
                    status = 10;
                }

                // 判定双顺
                sum = 0;
                for (int i = cardWeight1; i <= cardWeight6; i++) {
                    sum += i;
                }
                all = 0;
                for (Card card : cards) {
                    all += card.getWeights();
                }
                all /= 2;
                if (sum == all) {
                    status = 11;
                }

                // 判定三顺
                sum = 0;
                for (int i = cardWeight1; i <= cardWeight6; i++) {
                    sum += i;
                }
                all = 0;
                for (Card card : cards) {
                    all += card.getWeights();
                }
                all /= 3;
                if (sum == all) {
                    status = 12;
                }

                break;
            case 7:
                cardWeight1 = cards.get(0).getWeights();
                int cardWeight7 = cards.get(6).getWeights();

                // 判定单顺
                sum = 0;
                for (int i = cardWeight1; i <= cardWeight7; i++) {
                    sum += i;
                }
                all = 0;
                for (Card card : cards) {
                    all += card.getWeights();
                }
                if (sum == all) {
                    status = 10;
                }

                break;
            case 8:
                cardWeight1 = cards.get(0).getWeights();
                cardWeight2 = cards.get(1).getWeights();
                cardWeight3 = cards.get(2).getWeights();
                cardWeight4 = cards.get(3).getWeights();
                cardWeight5 = cards.get(4).getWeights();
                cardWeight6 = cards.get(5).getWeights();
                cardWeight7 = cards.get(6).getWeights();
                int cardWeight8 = cards.get(7).getWeights();

                // 判定四带二对
                if (cardWeight1 == cardWeight2
                        && cardWeight2 == cardWeight3
                        && cardWeight3 == cardWeight4
                        && cardWeight5 == cardWeight6
                        && cardWeight7 == cardWeight8) {
                    status = 9;
                }

                // 判定单顺
                sum = 0;
                for (int i = cardWeight1; i <= cardWeight8; i++) {
                    sum += i;
                }
                all = 0;
                for (Card card : cards) {
                    all += card.getWeights();
                }
                if (sum == all) {
                    status = 10;
                }

                // 判定双顺
                sum = 0;
                for (int i = cardWeight1; i <= cardWeight8; i++) {
                    sum += i;
                }
                all = 0;
                for (Card card : cards) {
                    all += card.getWeights();
                }
                all /= 2;
                if (sum == all) {
                    status = 10;
                }

                // 判定飞机-带翅膀-单
                if (cardWeight1 == cardWeight2
                        && cardWeight2 == cardWeight3
                        && cardWeight4 == cardWeight5
                        && cardWeight5 == cardWeight6
                        && cardWeight7 != cardWeight8) {
                    status = 13;
                }

                break;
            case 9:
                cardWeight1 = cards.get(0).getWeights();
                int cardWeight9 = cards.get(8).getWeights();

                // 判定单顺
                sum = 0;
                for (int i = cardWeight1; i <= cardWeight9; i++) {
                    sum += i;
                }
                all = 0;
                for (Card card : cards) {
                    all += card.getWeights();
                }
                if (sum == all) {
                    status = 10;
                }

                // 判定三顺
                sum = 0;
                for (int i = cardWeight1; i <= cardWeight9; i++) {
                    sum += i;
                }
                all = 0;
                for (Card card : cards) {
                    all += card.getWeights();
                }
                all /= 3;
                if (sum == all) {
                    status = 12;
                }

                break;
            case 10:
                cardWeight1 = cards.get(0).getWeights();
                cardWeight2 = cards.get(1).getWeights();
                cardWeight3 = cards.get(2).getWeights();
                cardWeight4 = cards.get(3).getWeights();
                cardWeight5 = cards.get(4).getWeights();
                cardWeight6 = cards.get(5).getWeights();
                cardWeight7 = cards.get(6).getWeights();
                cardWeight8 = cards.get(7).getWeights();
                cardWeight9 = cards.get(8).getWeights();
                int cardWeight10 = cards.get(9).getWeights();

                // 判定单顺
                sum = 0;
                for (int i = cardWeight1; i <= cardWeight10; i++) {
                    sum += i;
                }
                all = 0;
                for (Card card : cards) {
                    all += card.getWeights();
                }
                if (sum == all) {
                    status = 10;
                }

                // 判定双顺
                sum = 0;
                for (int i = cardWeight1; i <= cardWeight10; i++) {
                    sum += i;
                }
                all = 0;
                for (Card card : cards) {
                    all += card.getWeights();
                }
                all /= 2;
                if (sum == all) {
                    status = 10;
                }

                // 判定飞机-带翅膀-对
                if (cardWeight1 == cardWeight2
                        && cardWeight2 == cardWeight3
                        && cardWeight4 == cardWeight5
                        && cardWeight5 == cardWeight6
                        && cardWeight7 == cardWeight8
                        && cardWeight9 == cardWeight10) {
                    status = 14;
                }

                break;
            case 11:
                cardWeight1 = cards.get(0).getWeights();
                int cardWeight11 = cards.get(10).getWeights();

                // 判定单顺
                sum = 0;
                for (int i = cardWeight1; i <= cardWeight11; i++) {
                    sum += i;
                }
                all = 0;
                for (Card card : cards) {
                    all += card.getWeights();
                }
                if (sum == all) {
                    status = 10;
                }

                break;
            case 12:
                cardWeight1 = cards.get(0).getWeights();
                int cardWeight12 = cards.get(11).getWeights();

                // 判定单顺
                sum = 0;
                for (int i = cardWeight1; i <= cardWeight12; i++) {
                    sum += i;
                }
                all = 0;
                for (Card card : cards) {
                    all += card.getWeights();
                }
                if (sum == all) {
                    status = 10;
                }

                // 判定双顺
                sum = 0;
                for (int i = cardWeight1; i <= cardWeight12; i++) {
                    sum += i;
                }
                all = 0;
                for (Card card : cards) {
                    all += card.getWeights();
                }
                all /= 2;
                if (sum == all) {
                    status = 10;
                }

                // 判定三顺
                sum = 0;
                for (int i = cardWeight1; i <= cardWeight12; i++) {
                    sum += i;
                }
                all = 0;
                for (Card card : cards) {
                    all += card.getWeights();
                }
                all /= 3;
                if (sum == all) {
                    status = 12;
                }

                break;
            case 14:
                cardWeight1 = cards.get(0).getWeights();
                int cardWeight14 = cards.get(13).getWeights();

                // 判定双顺
                sum = 0;
                for (int i = cardWeight1; i <= cardWeight14; i++) {
                    sum += i;
                }
                all = 0;
                for (Card card : cards) {
                    all += card.getWeights();
                }
                all /= 2;
                if (sum == all) {
                    status = 11;
                }

                break;
            case 15:
                cardWeight1 = cards.get(0).getWeights();
                int cardWeight15 = cards.get(14).getWeights();

                // 判定三顺
                sum = 0;
                for (int i = cardWeight1; i <= cardWeight15; i++) {
                    sum += i;
                }
                all = 0;
                for (Card card : cards) {
                    all += card.getWeights();
                }
                all /= 3;
                if (sum == all) {
                    status = 12;
                }

                break;
            case 16:
                cardWeight1 = cards.get(0).getWeights();
                int cardWeight16 = cards.get(15).getWeights();

                // 判定双顺
                sum = 0;
                for (int i = cardWeight1; i <= cardWeight16; i++) {
                    sum += i;
                }
                all = 0;
                for (Card card : cards) {
                    all += card.getWeights();
                }
                all /= 2;
                if (sum == all) {
                    status = 11;
                }

                break;
            case 18:
                cardWeight1 = cards.get(0).getWeights();
                int cardWeight18 = cards.get(17).getWeights();

                // 判定双顺
                sum = 0;
                for (int i = cardWeight1; i <= cardWeight18; i++) {
                    sum += i;
                }
                all = 0;
                for (Card card : cards) {
                    all += card.getWeights();
                }
                all /= 2;
                if (sum == all) {
                    status = 11;
                }

                // 判定三顺
                sum = 0;
                for (int i = cardWeight1; i <= cardWeight18; i++) {
                    sum += i;
                }
                all = 0;
                for (Card card : cards) {
                    all += card.getWeights();
                }
                all /= 3;
                if (sum == all) {
                    status = 12;
                }

                break;
            case 20:
                cardWeight1 = cards.get(0).getWeights();
                int cardWeight20 = cards.get(19).getWeights();

                // 判定双顺
                sum = 0;
                for (int i = cardWeight1; i <= cardWeight20; i++) {
                    sum += i;
                }
                all = 0;
                for (Card card : cards) {
                    all += card.getWeights();
                }
                all /= 2;
                if (sum == all) {
                    status = 11;
                }

                break;
            default:
                System.out.println("牌数有误，请重新输入");
                break;
        }

        return status;
    }

    /*
     * 校验新出牌区牌数组与旧出牌区牌数组相比是否合法
     * 上一组牌的类别及对比逻辑
     * -1：不合法
     * 1：火箭（双王）<最大>
     * 2：炸弹（aaaa）<第二大>
     * 3：单牌（a）<同级别相互比较>
     * 4：对牌（aa）<同级别相互比较>
     * 5：三牌（aaa）<同级别相互比较>
     * 6：三带一（aaab）<同级别相互比较>
     * 7：三带一对（aaabb）<同级别相互比较>
     * 8：四带二（aaaabc）<同级别相互比较>
     * 9：四带二对（aaaabbcc）<同级别相互比较>
     * 10：单顺[至少5个连续的单不包括2和王]（abcde）<同级别同牌数相互比较>
     * 11：双顺[至少3个连续的对不包括2和王]（aabbcc）<同级别同牌数相互比较>
     * 12：三顺-飞机-不带翅膀[至少2个连续的三张不包括2和王]（aaabbb）<同级别相互比较>
     * 13：飞机-带翅膀-单（aaabbbcd）<同级别相互比较>
     * 14：飞机-带翅膀-对（aaabbbccdd）<同级别相互比较>
     *
     * @param oldPlayArea
     * @param newPlayArea
     * @return
     */
    public static boolean isNewPlayCards(PlayArea oldPlayArea, PlayArea newPlayArea) {

        // 如果上一次出牌者是-1，那么任何人都可以出任何组合的牌
        if (oldPlayArea.getPid()==-1) {
            return true;
        }

        // 如果上一次出牌者和这一次出牌者相同，那么任何组合的牌都可以
        if (oldPlayArea.getPid()==newPlayArea.getPid()) {
            return true;
        }

        // 如果这一次出牌是火箭
        if (newPlayArea.getStatus()==1) {
           return true;
        }

        // 如果这一次出牌是炸弹，且上一次出牌不是双王
        if (newPlayArea.getStatus() == 2 && oldPlayArea.getStatus()!=1) {
            if (oldPlayArea.getStatus() != 2) {
                return true;
            }
            if (newPlayArea.getCards().get(0).getWeights() > oldPlayArea.getCards().get(0).getWeights()) {
                return true;
            }
        }

        // 其他情况需要对比前后两次出牌类别相同
        if (newPlayArea.getStatus() != oldPlayArea.getStatus()) {
            return false;
        }

        if (oldPlayArea.getStatus()==10 || oldPlayArea.getStatus()==11 || oldPlayArea.getStatus()==12) {
            // 顺牌比较策略
            if (newPlayArea.getCards().size()==oldPlayArea.getCards().size()) {
                if (newPlayArea.getCards().get(0).getWeights() > oldPlayArea.getCards().get(0).getWeights()) {
                    return true;
                }
            }
        } else {
            // 非顺牌比较策略
            if (newPlayArea.getCards().get(0).getWeights() > oldPlayArea.getCards().get(0).getWeights()) {
                return true;
            }
        }

        return false;
    }

}
