package proit;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Str {

    public static void main(String[] args) {
        var list = List.of(
                "  Эта модель	помогает нейросети запоминать правила языка",
                "выбирать подходящие слова и связывать их	 по смыслу",
                "   Также эта нейросеть	 помогает превращать введённый текст  "
        );

        Map<String, Long> map = list.stream()
                .map(s -> s.split("\\s"))
                .flatMap(Arrays::stream)
                .filter(s1 -> !s1.isBlank())
                .collect(Collectors.toMap(
                        String::toLowerCase,
                        value -> 1L,
                        (exist, replacement) -> ++exist)
                );

        System.out.println(map);
    }

}
