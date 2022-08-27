import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String animalString = "[\n" +
                "        {\"name\":\"shark\", \"habitat\":\"ocean\"},\n" +
                "        {\"name\":\"bear\", \"habitat\":\"land\"},\n" +
                "        {\"name\":\"moose\", \"habitat\":\"land\"},\n" +
                "        {\"name\":\"frog\", \"habitat\":\"swamp\"},\n" +
                "        {\"name\":\"jelly fish\", \"habitat\":\"ocean\"},\n" +
                "        {\"name\":\"heron\", \"habitat\":\"swamp\"},\n" +
                "        {\"name\":\"whale\", \"habitat\":\"ocean\"}\n" +
                "    ]";

        String capitalString = "{\n" +
                "    \"USA\":\"Washington\",\n" +
                "    \"Japan\":\"Tokyo\",\n" +
                "    \"Thailand\":\"Bangkok\",\n" +
                "    \"UK\":\"London\",\n" +
                "    \"Australia\":\"Canberra\",\n" +
                "    \"Denmark\":\"Copenhagen\",\n" +
                "    \"Egypt\":\"Cairo\",\n" +
                "    \"Vietnam\":\"Hanoi\",\n" +
                "    \"Italy\":\"Rome\",\n" +
                "    \"Brazil\":\"Brazilia\"\n" +
                "}";

        //設定string要轉成的型別
        Type mapTokenType = new TypeToken<Map<String, String>>() {
        }.getType();
        Map<String, String> capitalMap = new Gson().fromJson(capitalString, mapTokenType);

        mapTokenType = new TypeToken<List<Map<String, String>>>() {
        }.getType();
        List<Map<String, String>> animalMap = new Gson().fromJson(animalString, mapTokenType);

        Map<String, List<String>> animals = new HashMap<>();
        animalMap.forEach(item -> {
            //判斷是否有以新增此 habitat
            if (animals.containsKey(item.get("habitat"))) {
                animals.get(item.get("habitat")).add(item.get("name"));
            } else {
                animals.put(item.get("habitat"), new ArrayList<>() {{
                    add(item.get("name"));
                }});
            }
        });

        animals.forEach((key, value) -> System.out.println(key + " : "
                + value.toString().replaceAll("\\[", " ").replace("]", "")));

        System.out.println("=====================  第二題");

        for (String i : capitalMap.keySet()) {
            System.out.println(i + " : " + capitalMap.get(i));
        }

    }
}