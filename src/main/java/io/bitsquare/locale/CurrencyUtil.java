package io.bitsquare.locale;

import java.text.NumberFormat;
import java.util.*;

public class CurrencyUtil
{

    public static List<Currency> getAllCurrencies()
    {
        final ArrayList<Currency> mainCurrencies = new ArrayList<>();
        mainCurrencies.add(Currency.getInstance("USD"));
        mainCurrencies.add(Currency.getInstance("EUR"));
        mainCurrencies.add(Currency.getInstance("CNY"));
        mainCurrencies.add(Currency.getInstance("RUB"));
        mainCurrencies.add(Currency.getInstance("JPY"));
        mainCurrencies.add(Currency.getInstance("GBP"));
        mainCurrencies.add(Currency.getInstance("CAD"));
        mainCurrencies.add(Currency.getInstance("AUD"));
        mainCurrencies.add(Currency.getInstance("CHF"));
        mainCurrencies.add(Currency.getInstance("CNY"));

        Set<Currency> allCurrenciesSet = Currency.getAvailableCurrencies();

        allCurrenciesSet.removeAll(mainCurrencies);
        final List<Currency> allCurrenciesList = new ArrayList<>(allCurrenciesSet);
        allCurrenciesList.sort((a, b) -> a.getCurrencyCode().compareTo(b.getCurrencyCode()));

        final List<Currency> resultList = new ArrayList<>(mainCurrencies);
        resultList.addAll(allCurrenciesList);

        Currency defaultCurrency = Currency.getInstance(Locale.getDefault());
        resultList.remove(defaultCurrency);
        resultList.add(0, defaultCurrency);

        return resultList;
    }


    public static Currency getDefaultCurrency()
    {
        return NumberFormat.getNumberInstance(Locale.getDefault()).getCurrency();
    }
}
