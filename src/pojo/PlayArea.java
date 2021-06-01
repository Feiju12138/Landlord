package pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 出牌区对象
 */
public class PlayArea {

    /*
     * cards：当前出牌区最后一次出的牌
     * status：这组排的类别
     *  -1：不合法
     *   0：空出牌区
     *   1：火箭（双王）
     *   2：炸弹（aaaa）
     *   3：单牌（a）
     *   4：对牌（aa）
     *   5：三牌（aaa）
     *   6：三带一（aaab）
     *   7：三带一对（aaabb）
     *   8：四带二（aaaabc）
     *   9：四带二对（aaaabbcc）
     *  10：单顺（abcde）
     *  11：双顺（aabbccdd）
     *  12：三顺-飞机-不带翅膀（aaabbb）
     *  13：飞机-带翅膀-单（aaabbbcd）
     *  14：飞机-带翅膀-对（aaabbbccdd）
     * pid：上一次出牌玩家的编号
     *  -1：没有上一次出牌的玩家
     * player：上一次出牌的玩家信息
     *
     */
    private List<Card> cards;
    private Integer status;
//    private Integer pid;
    private Player player;

    public PlayArea() {
        cards = new ArrayList<>();
        status = -1;
//        pid = -1;
        player = null;
    }

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
