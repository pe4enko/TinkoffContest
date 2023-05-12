package ya.test.sprint4final;

import static java.lang.Integer.parseInt;
import static java.util.Comparator.reverseOrder;
import static java.util.Map.Entry.comparingByKey;
import static java.util.Map.Entry.comparingByValue;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * https://contest.yandex.ru/contest/24414/run-report/69344689/
 * -- ПРИНЦИП РАБОТЫ --
 * Для начала мы строим поисковый индекс, в котором для каждого слова заполняем в каком документе
 * оно встречается и сколько раз. На втором этапе мы берем поисковый запрос разбиваем его на слова
 * и для каждого слова из запроса определяем (используя ранее построенный поисковый индекс),
 * кол-во возможных совпадений данного слова в документах из поискового индекса.
 * Так делаем для каждого слова из поисковго запроса. Кол-во совпадений суммируется для каждого
 * уникального слова из запроса. Итоговое значение и будет соответствовать релевантности документа
 * поисковому запросу.
 * На последнем этапе мы просто сортируем значения по релевантности и выводим до 5 индексов
 * документов с максимальной получившейся релевантностью.
 *
 *  -- ДОКАЗАТЕЛЬСТВО КОРРЕКТНОСТИ --
 *  Так как Тимофей пишет поисковую систему, то основное ее назначение помогать пользователям
 *  находить нужные данные с большой скоростью. При построении индекса перебираем все слова
 *  входящие в документ и кладем их в поисковый индекс, можно однозначно сказать, что если
 *  мы ищем слово из поискового запроса, но оно не встречается ни в одном из просмотренных документов
 *  то и при обращении к индексу его там не окажется. Если слово все таки находится в индексе, то мы
 *  проверяем кол-во раз которое встречается данное слово в предложении. Чем больше встречается слово
 *  тем более релевантен доумент поисковому запросу. Далее проверяем все оставшиеся слова из запроса.
 *  Чем больше в итоге окажется вхождений слов в документы тем более релевантным будет документ.
 *  Наша цель найти самые релевантные документы. Так как мы уже посчитали релевантности всех
 *  документов, то мы с легкостью можем найти первые 5, просто отсортировав их по релевантности.
 *
 *  -- ВРЕМЕННАЯ СЛОЖНОСТЬ --
 *  Сложность будет состоять из:
 *
 *  1) Сложности построения поискового индекса.
 *  Она зависит от кол-ва документов и от кол-ва слов в них.
 *  Допустим, что у нас ВСЕГО n слов во всех документах. Тогда при построении индекса:
 *  1.1) нам необходимо проверить нет ли уже в индексе карты с данным словом, такая проверка будет
 *  выполняться столько же раз сколько всего слов, т.е. сложность данных действий равна O(n);
 *  1.2) для каждого нового слова мы кладем карту с номером документа и кол-вом сколько раз встречается новое слово
 *  Наихудший случай - когда все слова разные. И сложность в таком случае будет равно O(n);
 *  1.3) для каждого документа проверяем встречалось ли уже данное слово в документе.
 *  Можно предположить, что кол-во документов в худшем случае будет равно n, если все документы
 *  состоят из одного слова. Тогда сложность O(n).
 *  В обоих случая встречалось слово ранее или нет, необходимо обновить счетчик и это так же займет O(n)
 *  ИТОГО сложность построения индекса равно O(n).
 *
 *  2) Вычисление релевантности документов, поисковому запросу.
 *  Для каждого запроса вычисляется отдельно, т.е. будет зависеть от кол-во поисковых запросов и от кол-ва
 *  слов в каждом запросе.
 *  Разберем стоимость одного поискового запроса.
 *  Допустим, что в запрсое оказалось всего m слов.
 *  Для каждого слова необходимо проверить его уникальность. Сложность проверки всех слов равна O(m).
 *  Далее получение по каждому слову карты сколько раз слово встречается в рассматриваемом предложении
 *  из поискового индекса, равно O(m) + O(n)
 *  Далее вычисляем релевантность и кладем в карту релевантностей документов O(n)
 *  ИТОГО получается O((O(m) + O(n)) * кол-во поисковых запросов).
 *
 *  3) Сортировка по релевантности занимает n * logN, используется
 *
 *  Все вместе по идее будет максимально значение из сложностей этих операций
 *  т.е. O((O(m) + O(n)) * кол-во поисковых запросов).
 *
 *
 *  -- ПРОСТРАНСТВЕННАЯ СЛОЖНОСТЬ --
 *  Пространственная сложность индекса:
 *  Допустим, что у нас ВСЕГО n слов во всех документах, тогда пространственная сложность
 *  поискового индекса так же будет O(n).
 *
 *  Пространственная сложность одного запросоа:
 *  Допустим, что у нас m слов в одном запросе, тогда:
 *  1) необходимо хранить все запрашиваемые слова для проверки уникальнсти,
 *  т.е. пространсвенная сложность в этом случае будет равно O(m);
 *  2) необходимо хранить для каждого документ его релевантность O(d),
 *  где d - кол-во документов в индексе.
 *  ИТОГО: пространственная сложность всех запросов, будет равна O((O(m) + O(d)) * кол-во запросов)
 *  Важно отметить, что каждый поисковый запрос обрабатывается последовательно и
 *  пространственная сложность в момент времени будет равно, пространственной сложность только
 *  одного запроса, т.е. O(O(m) + O(d))
 */
public class SearchSystem {

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out))) {

            int n = parseInt(reader.readLine());
            Map<String, Map<Integer, Integer>> searchIndex = createSearchIndex(reader, n);

            int m = parseInt(reader.readLine());

            for (int i = 0; i < m; i++) {
                Map<Integer, Integer> relevant = computeRelevance(reader, searchIndex);

                printRelevants(writer, sortRelevance(relevant));
            }

            writer.flush();
        }
    }

    /**
     * Слово, хранится в предложении с номером, столько то раз.
     */
    private static Map<String, Map<Integer, Integer>> createSearchIndex(BufferedReader reader, int n)
            throws IOException {

        //Map<Word, Map<DocNumber, WordCount>>
        Map<String, Map<Integer, Integer>> searchIndex = new HashMap<>();

        //Перебираем предложения
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            while (st.hasMoreTokens()) {
                String word = st.nextToken();

                //Map<DocNumber, WordCount>
                Map<Integer, Integer> sentenseNumberToWordCount = searchIndex.get(word);
                if (sentenseNumberToWordCount == null) {
                    sentenseNumberToWordCount = new HashMap<>();
                    searchIndex.put(word, sentenseNumberToWordCount);
                }

                Integer currentWordCountInThisSentense = sentenseNumberToWordCount.get(i);
                if (currentWordCountInThisSentense == null) {
                    sentenseNumberToWordCount.put(i, 1);
                } else {
                    sentenseNumberToWordCount.put(i, ++currentWordCountInThisSentense);
                }
            }
        }

        return searchIndex;
    }

    private static Map<Integer, Integer> computeRelevance(BufferedReader reader,
            Map<String, Map<Integer, Integer>> searchIndex) throws IOException {
        //Номер предложения и насколько оно релевантно для текущего поиска
        Map<Integer, Integer> relevant = new HashMap<>();
        Set<String> uniqCheck = new HashSet<>();

        StringTokenizer st = new StringTokenizer(reader.readLine());
        while (st.hasMoreTokens()) {
            String word = st.nextToken();

            //Map<DocNumber, WordCount>
            Map<Integer, Integer> sentenseNumberToWordCount = searchIndex.get(word);

            //Слово не встречается в предложениях
            if (sentenseNumberToWordCount == null) {
                continue;
            }

            if (!uniqCheck.add(word)) {
                //для каждого уникального слова из запроса берётся число его вхождений в документ
                continue;
            }

            for (Entry<Integer, Integer> entry : sentenseNumberToWordCount.entrySet()) {
                Integer sentense = entry.getKey();
                //сколько раз слово word встречается в этом предложении
                Integer wordCount = entry.getValue();

                relevant.put(sentense, relevant.getOrDefault(sentense, 0) + wordCount);
            }
        }
        return relevant;
    }

    private static Set<Entry<Integer, Integer>> sortRelevance(Map<Integer, Integer> relevant) {
        Comparator<Entry<Integer, Integer>> valueComparator = comparingByValue(reverseOrder());
        Comparator<Entry<Integer, Integer>> keyComparator = comparingByKey();

        Set<Entry<Integer, Integer>> sortedRelevant = new TreeSet<>(
                valueComparator.thenComparing(keyComparator)
        );

        sortedRelevant.addAll(relevant.entrySet());

        return sortedRelevant;
    }

    private static void printRelevants(
            BufferedWriter writer,
            Set<Entry<Integer, Integer>> sortedRelevants) throws IOException {

        int k = 0;

        for (Entry<Integer, Integer> entry : sortedRelevants) {
            writer.write(entry.getKey() + 1 + " ");
            k++;
            if (k == 5) {
                break;
            }
        }

        writer.write("\n");
    }
}
