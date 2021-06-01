package utils;

import java.util.HashMap;
import java.util.Map;

public class StatusMapping {

    public Map<Integer, String> statusMappingMap;

    public StatusMapping() {
        statusMappingMap = new HashMap<>();
        statusMappingMap.put(-1, "不合法");
        statusMappingMap.put(0, "空出牌区");
        statusMappingMap.put(1, "火箭🚀");
        statusMappingMap.put(2, "炸弹💣");
        statusMappingMap.put(3, "单牌");
        statusMappingMap.put(4, "对牌");
        statusMappingMap.put(5, "三牌");
        statusMappingMap.put(6, "三带一");
        statusMappingMap.put(7, "三带一对");
        statusMappingMap.put(8, "四带二");
        statusMappingMap.put(9, "四带二对");
        statusMappingMap.put(10, "单顺");
        statusMappingMap.put(11, "双顺");
        statusMappingMap.put(12, "三顺");
        statusMappingMap.put(13, "飞机✈️单翅膀");
        statusMappingMap.put(14, "飞机✈️对翅膀");
    }

}
