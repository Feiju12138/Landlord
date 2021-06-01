package play;

import pojo.Card;
import pojo.Deck;
import pojo.PlayArea;
import pojo.Player;
import pojo.SpecialCard;
import utils.CheckCard;
import utils.SortCard;
import utils.StatusMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * name 斗地主游戏
 * version 0.1 beta
 */
public class Play {

    public static void main(String[] args) {

        // 创建一个牌堆
        Deck deck = new Deck();
        System.out.println("启始总牌堆：" + deck);

        // 创建三个玩家
        List<Player> players = new ArrayList<>();
        players.add(new Player(0));
        players.add(new Player(1));
        players.add(new Player(2));

        // 抽出三张牌作为地主的特殊牌
        SpecialCard specialCard = new SpecialCard();
        for (int i = 0; i < 3; i++) {
            Card out = deck.out();
            specialCard.addCard(out);
        }
        System.out.println("地主的三张特殊牌" + specialCard);

//        System.out.println("当前牌堆：" + deck);

        // 为每名玩家分配起始手牌
        for (Player player : players) {
            for (int i = 0; i < 17; i++) {
                Card out = deck.out();
                player.addCard(out);
            }
        }

        // 抢地主------debug

        // 标注每个玩家的身份------debug
        players.get(0).setStatus(0);
        players.get(1).setStatus(1);
        players.get(2).setStatus(1);

        // 遍历玩家，找出身份为地主的玩家，将特殊牌添加给地主
        for (Player player : players) {
            if (player.getStatus() == 0) {
                for (Card card : specialCard.getCards()) {
                    player.addCard(card);
                }
                break;
            }
        }

        // 排序所有玩家手牌
        for (Player player : players) {
            List<Card> cards = player.getCards();
            List<Card> cardsSort = SortCard.sortForPlayer(cards);
            player.setCards(cardsSort);
        }

        // 展示所有玩家的身份及手牌------debug
        for (Player player : players) {
            String playerStatus = player.getStatus()==0?"地主":"平民";
            System.out.println("身份：" + playerStatus);
            System.out.println("手牌：" + player.getCards());
        }

        // 初始化一个空出牌区
        PlayArea playArea = new PlayArea();
        playArea.setStatus(0);

        // 初始化玩家指针
        int pid = 0;

        // 创建键盘输入对象
        Scanner scanner = new Scanner(System.in);

        // 创建一个获胜玩家
        Player winner = null;

        // 创建类别映射类对象
        StatusMapping statusMapping = new StatusMapping();

        while (true) {

            Player player = null;

            // 从编号为0的玩家开始出牌
            for (Player p : players) {
                if (p.getId() == pid) {
                    player = p;
                }
            }

            System.out.println("----------------------------------");
            System.out.println("当前出牌区：");
            System.out.println("上一次出牌玩家：" + playArea.getPid());
            System.out.println("类别：" + statusMapping.statusMappingMap.get(playArea.getStatus()));
            System.out.println("牌数组：" + playArea.getCards());
            System.out.println("----------------------------------");
            System.out.println("当前玩家：");
            System.out.println("编号：" + player.getId());
            String playerStatus = player.getStatus() == 0 ? "地主" : "平民";
            System.out.println("身份：" + playerStatus);
            System.out.println("手牌" + player.getCards());
            System.out.println("----------------------------------");

            while (true) {

                System.out.println("请输入想要出的牌的编号（多张牌用一个空格` `隔开，不出牌则留空）");
                String line = scanner.nextLine(); // 接收控制台输入的字符串
                String[] strs = line.split(" "); // 将接收到的字符串根据空格分割

                // 判断是否不出牌
                if (line == null || line.equals("")) {
                    System.out.println("您没有出牌");
                    break;
                }

                // 判断输入长度是否超过实际长度
                if (strs.length > player.getCards().size()) {
                    System.out.println("您输入的数字总个数超过了手牌总数，请重新输入");
                    continue;
                }

                // 将字符串数组转化为int类型数组用于存放索引
                int[] indexs = new int[strs.length];
                for (int i = 0; i < indexs.length; i++) {
                    indexs[i] = Integer.parseInt(strs[i]);
                }

                // 设置一个跳出循环的开关
                boolean key = false;

                // 判断数字是否超过下标
                for (int index : indexs) {
                    if (index > player.getCards().size() - 1) {
                        System.out.println("您输入的数字中的某个数字超过了手牌总数，请重新输入");
                        key = true;
                    }
                }
                if (key) {
                    continue;
                }

                // 重新闭合
                key = false;

                // 判断输入的数值是否重复
                Arrays.sort(indexs);
                for (int i = 0; i < indexs.length - 1; i++) {
                    if (indexs[i] == indexs[i + 1]) {
                        System.out.println("您输入的数字包含重复数字，请重新输入");
                        key = true;
                    }
                }
                if (key) {
                    continue;
                }

                // 重新闭合
                key = false;

                List<Card> cards = player.listCards(indexs); // 根据索引数组拿出牌对象集合
                PlayArea newPlayArea = new PlayArea(); // 创建一个新的出牌区
                newPlayArea.setCards(cards); // 把拿出来的牌集合放入新出牌区
                int newPlayAreaStatus = CheckCard.isPlayCards(newPlayArea); // 判断新出牌区是否合法

                // 如果新出牌区不合法
                if (newPlayAreaStatus == -1) {
                    System.out.println("您出的牌不符合出牌规定，请按照斗地主规则重新出牌");
                    continue;
                }

                newPlayArea.setPid(player.getId()); // 设置新出牌区玩家编号
                newPlayArea.setStatus(newPlayAreaStatus); // 设置新出牌区的状态

                // 展示刚刚出的牌------debug
                System.out.println("您刚刚出的牌是：" + newPlayArea.getCards());

                // 判断新出牌区是否可以替换旧出牌区
                if (!CheckCard.isNewPlayCards(playArea, newPlayArea)) {
                    System.out.println("您出的牌比上家小，请重新选择更大的牌");
                    continue;
                }

                playArea = newPlayArea; // 新出牌区替换旧出牌区
                player.removeCards(indexs); // 移除新出牌区所使用的牌
                break;

            }

            if (player.getCards().size() == 0) {
                winner = player;
                break;
            }

            // 当前玩家出牌结束，游戏未结束，移交出牌玩家
            if (pid == 2) {
                pid = 0;
            } else {
                pid++;
            }

        }

        System.out.println("================================");
        System.out.println("游戏结束");
        System.out.println("玩家 " + winner.getId() + " 已出完牌");
        System.out.println((winner.getStatus() == 0 ? "地主" : "平民") + "胜利!");
        System.out.println("================================");


    }

}
