package pojo;

import java.util.ArrayList;
import java.util.List;

/**
 * 玩家对象
 */
public class Player {

    /*
    * id：玩家编号
    * cards：当前手牌
    * status：状态标注
    *   0：地主
    *   1：平民
    */
    private Integer id;
    private List<Card> cards;
    private Integer status;

    public Player(Integer id) {
        this.id = id;
        cards = new ArrayList<>();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    // 添加手牌
    public void addCard(Card card) {
        cards.add(card);
    }

    // 拿出一组手牌
    public List<Card> listCards(int[] indexs) {
        List<Card> cards = new ArrayList<>();
        for (int index : indexs) {
            cards.add(this.getCards().get(index));
        }
        return cards;
    }

    // 移除一组手牌
    public void removeCards(int[] indexs) {
        List<Card> cardsForRemove = new ArrayList<>();
        for (int index : indexs) {
            cardsForRemove.add(this.getCards().get(index));
        }
        for (Card cardForRemove : cardsForRemove) {
            this.getCards().remove(cardForRemove);
        }
    }

}
