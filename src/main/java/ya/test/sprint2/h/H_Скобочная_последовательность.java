package ya.test.sprint2.h;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;

/**
 * Вот какую задачу Тимофей предложил на собеседовании одному из кандидатов. Если вы с ней ещё не сталкивались, то
 * наверняка столкнётесь –— она довольно популярная.
 * <p>
 * Дана скобочная последовательность. Нужно определить, правильная ли она.
 * <p>
 * Будем придерживаться такого определения:
 * <p>
 * пустая строка —– правильная скобочная последовательность; правильная скобочная последовательность, взятая в скобки
 * одного типа, –— правильная скобочная последовательность; правильная скобочная последовательность с приписанной слева
 * или справа правильной скобочной последовательностью —– тоже правильная. На вход подаётся последовательность из скобок
 * трёх видов: [], (), {}. Напишите функцию is_correct_bracket_seq, которая принимает на вход скобочную
 * последовательность и возвращает True, если последовательность правильная, а иначе False.
 * <p>
 * Формат ввода На вход подаётся одна строка, содержащая скобочную последовательность. Скобки записаны подряд, без
 * пробелов.
 * <p>
 * Пример 1 Ввод {[()]} Вывод True Пример 2 Ввод () Вывод True
 */
public class H_Скобочная_последовательность {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            String str = reader.readLine();

            H_Скобочная_последовательность sequence = new H_Скобочная_последовательность();
//            writer.write(sequence.is_correct_bracket_seq(str));
            writer.write(sequence.is_correct_bracket_seq_optimized(str));
            writer.flush();
        }
    }

    private String is_correct_bracket_seq_optimized(String str) {
        LinkedList<Character> stack = new LinkedList<>();

        for (int i = 0; i < str.length(); i++) {
            char nextChar = str.charAt(i);
            if (nextChar == '(') {
                stack.push(')');
            } else if (nextChar== '[') {
                stack.push(']');
            } else if (nextChar == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.peek() != nextChar) {
                return "False";
            } else {
                stack.pop();
            }
        }

        if (stack.isEmpty()) {
            return "True";
        } else {
            return "False";
        }
    }

    private String is_correct_bracket_seq(String str) {
        LinkedList<Character> stack = new LinkedList<>();
        Character br;

        for (int i = 0; i < str.length(); i++) {
            br = stack.peek();
            char nextChar = str.charAt(i);

            if (br != null && isOpenChar(br) && !isOpenChar(nextChar) && isSameTypeChar(br, nextChar)) {
                stack.pop();
//            } else if (br == null && !isOpenChar(nextChar)) {
//                return "False";
            } else {
                stack.push(nextChar);
            }
        }

        if (stack.isEmpty()) {
            return "True";
        } else {
            return "False";
        }
    }

    private boolean isSameTypeChar(char left, char right) {
        if (left == '[' && right == ']' || left == ']' && right == '[') {
            return true;
        }

        if (left == '(' && right == ')' || left == ')' && right == '(') {
            return true;
        }

        if (left == '{' && right == '}' || left == '}' && right == '{') {
            return true;
        }

        return false;
    }

    private boolean isOpenChar(char nextChar) {
        return nextChar == '[' || nextChar == '(' || nextChar == '{';
    }
}
