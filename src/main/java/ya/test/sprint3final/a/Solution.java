package ya.test.sprint3final.a;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * https://contest.yandex.ru/contest/23815/run-report/69200295/
 *
 * -- ПРИНЦИП РАБОТЫ --
 * Алгоритм "Поиск в сломанном массиве" на самом деле является алгооритмом
 * "Поиск в отсортированном и сдвинутом массиве" описание решения было взято из канала
 * https://www.youtube.com/watch?v=Upd0XWIyBcg и убраны некоторые неточности при проверке выхода из цикла.
 * При реализации алгоритма используются те же принципы, что и при реалзиации алгоритма бинарного поиска.
 * Так же как и при бинарном поиске весь отрезок разбивается на две части при этом получаются два отрезка
 * один из которых полностью отсортирован, и для поиска в нем можно использовать алгоритма бинарного
 * поиска. А второй отрезок бедут содержать как отсортированную часть, так и не отсортированную.
 * Основной сложностью является то как понять какая из частей является отсортированной, а какая нет.
 * На самом деле если мы например возьмем массив 1 2 3 4 5 6 7 и сдвинем его допустим на 3 элемента
 * то получится следующий массив 5 6 7 1 2 3 4 5, в таком случае допустим мы разрезаем по цифре 3.
 * Тогда получим две отрезка 5 6 7 1 2 и 3 4 5, если самый крайний левый элемент отрезка меньше
 * чем самый крайних правый элемент, например 3 4 5, 3 < 5, то это значит, что отрезок отсортирован.
 * Если самый первый элемент больше самого последнего, то это значит что отрезок не отсортирован,
 * например 5 6 7 1 2, 5 > 2.
 * Мы можем сравнить искомое значение только с элементами отсортированного отрезка.
 * Далее необходимо найти в каком из отрезков находится искомое значение k (если оно там вобще есть).
 * В звисимости от этого мы будем сдвигать границы поиска.
 *
 * -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 * Не понятно, что надо доказать в этом разделе и самое главное как?
 *
 * -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 * На каждом этапе мы уменьшаем последовательность в которой ищем элемент вдвое поэтому сложность
 * данного алгоритма равно O(logN)
 *
 * -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 * При работе данного алгоритма никакой дополнительной памяти не выделяется. Работа ведется все время с
 * оригинальным массивом из n элементов, поэтому пространсвенная сложность данного алгоритма O(n)
 */
public class Solution {

    public static int brokenSearch(int[] arr, int k) {

        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            int midValue = arr[mid];
            if (midValue == k) {
                return mid;
            }

            //Нам нужно понять какая из частей левая или правая является отсортированной
            int leftValue = arr[left];
            if (leftValue <= midValue) {
                //левая часть отсортированная
                //Возможны два случая
                // lv <= k < mv
                // mv < k

                //Теперь надо найти в какой части находится искомое значение
                if (leftValue <= k && k < midValue) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                //правая часть отсортированная
                //Возможны два случая
                // mv < k <= rv
                // k < mv

                if (midValue < k && k <= arr[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    private static void test() {
        int[] arr = {19, 21, 100, 101, 1, 4, 5, 7, 12};
        assert 6 == brokenSearch(arr, 5);
    }
/*
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int ignore = readInt(reader);
            int needToFind = readInt(reader);
            int[] whereToSearch = readIntArray(reader);

            writer.write(String.valueOf(brokenSearch(whereToSearch, needToFind)));

//            int[] arr = {19, 21, 100, 101, 1, 4, 5, 7, 12};
//            assert 6 == brokenSearch(arr, 5);
        }
    }*/

/*
    private static int readInt(BufferedReader reader) throws IOException {
        return Integer.parseInt(reader.readLine());
    }

    private static int[] readIntArray(BufferedReader reader) throws IOException {

        ArrayList<Integer> tmp = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(reader.readLine());
        while (st.hasMoreTokens()) {
            int i = Integer.parseInt(st.nextToken());
            tmp.add(i);
        }

        final int[] result = new int[tmp.size()];
        int i = 0;

        for (Integer integer : tmp) {
            result[i] = (integer == null ? 0 : integer);
            i++;
        }
        return result;
    }*/
}