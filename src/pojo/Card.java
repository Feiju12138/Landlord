package pojo;

/**
 * 纸牌对象
 */
public class Card {

    /*
    * color：花色
    *   1：红桃♥️
    *   2：方片♦️
    *   3：黑桃♠️
    *   4：梅花♣️
    *   5：红色🤡大王
    *   6：黑色🤡小王
    * number：点数
    *   A，2，3，4，5，6，7，8，9，10，J，Q，K，Joker
    * weights：权重（点数-权值）
    *   3-1, 4-2, 5-3, 6-4, 7-5, 8-6, 9-7, 10-8, J-9, Q-10, K-11, A-12, 2-13，14（小王），15（大王）
    */
    private Integer color;
    private String number;
    private Integer weights;

    public Card(Integer color, String number) {
        this.color = color;
        this.number = number;
        if (number.equals("A")) {
            this.weights = 12;
        } else if (number.equals("2")) {
            this.weights = 13;
        } else if (number.equals("J")) {
            this.weights = 9;
        } else if (number.equals("Q")) {
            this.weights = 10;
        } else if (number.equals("K")) {
            this.weights = 11;
        } else if (number.equals("Joker")) {
            if (this.color==5) {
                this.weights = 15;
            } else if (this.color==6) {
                this.weights = 14;
            }
        } else {
            this.weights = Integer.parseInt(this.number)-2;
        }
    }

    public Integer getColor() {
        return color;
    }

    public String getNumber() {
        return number;
    }

    public Integer getWeights() {
        return weights;
    }

    @Override
    public String toString() {
        String str = "";
        if (color == 5) {
            str = "大王";
        } else if (color == 6) {
            str = "小王";
        } else if (color == 1) {
            str = "♥️"+number;
        } else if (color == 2) {
            str ="♦️️"+number;
        } else if (color == 3) {
            str = "♠️️"+number;
        } else if (color == 4) {
            str = "♣️️"+number;
        }
        return str;
    }

    @Override
    public boolean equals(Object o) {
        Card c = (Card)o;
        if (c.getColor()==this.color && c.getNumber().equals(this.number)) {
            return true;
        }
        return false;
    }
}
