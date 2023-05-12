package yandex.y1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 Написать аналог enum на примере какого-нибудь конкретного перечислимого списка, например, списка валют или дней недели.
 Представьте, что у вас отобрали ключевое слово "enum" и предлагается выкрутиться из этой ситуации. Можно использовать IDE.
 Нужно:
 - иметь фиксированный список значений, задаваемый на этапе компиляции
 - иметь строгую типизацию значений
 - уметь безопасно сравнивать значения по ==
 - получать все значения
 - иметь неизменяемый ordinal (порядковый номер с 0 в порядке объявления значений)
 - получать значения по ordinal
 - иметь название значения, совпадающее с названием поля для значения
 - искать по имени
 - самое важное требование, которое следует удовлетворять в процессе всей реализации
   - расставить как можно меньшее количество граблей для разработчика, который в будущем будет добавлять новые значения
 */
public final class Currency {
    private final static List<Currency> values = new ArrayList<>(3); // todo: magic numbers

    public final static Currency RUB = new Currency("RUB");
    public final static Currency USD = new Currency("USD");
    public final static Currency EUR = new Currency("EUR");

    private final String name;
    private final int ordinal;

    private static int maxOrdinal = 0;

    public Currency(String name) {
        this.name = name;
        this.ordinal = maxOrdinal++;

        values.add(this);
    }

    public String getName() {
        return name;
    }

    public int getOrdinal() {
        return ordinal;
    }

    public static List<Currency> getValues() {
        return Collections.unmodifiableList(values);
    }

    public static Currency getByOrdinal(int ordinal) {
        for (Currency currency : values) {
            if (currency.getOrdinal() == ordinal) {
                return currency;
            }
        }
        return null;
    }

    public static Currency getByName(String name) {
        for (Currency currency : values) {
            if (currency.getName().equals(name)) {
                return currency;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String name1 = USD.getName();
        List<Currency> values1 = getValues();
//        System.out.println(name1);
//        System.out.println(USD.getOrdinal());

        System.out.println(values1);

    }
}
