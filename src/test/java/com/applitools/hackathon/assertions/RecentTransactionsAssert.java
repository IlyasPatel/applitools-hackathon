package com.applitools.hackathon.assertions;

import com.applitools.hackathon.domain.TransactionsTable;
import com.google.common.collect.Ordering;
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class RecentTransactionsAssert extends AbstractAssert<RecentTransactionsAssert, List<TransactionsTable>> {

    public RecentTransactionsAssert(List<TransactionsTable> actual) {
        super(actual, RecentTransactionsAssert.class);
    }

    public static RecentTransactionsAssert assertThat(List<TransactionsTable> actual) {
        return new RecentTransactionsAssert(actual);
    }

    public void amountsColumnIsInAscendingOrder() {
        List<Float> amounts = extractAmountColumn();

        StringBuilder msg = getActualAmountsForErrorMessage();

        Assertions.assertThat(isSorted(amounts))
                .as("Recent transactions amount column is not in ascending order. \n\n Actual %s", msg)
                .isTrue();
    }

    public void isIntact(List<TransactionsTable> originalTable) {
        List<TransactionsTable> arrangedTable = this.actual;

        for (TransactionsTable transactionsTable : originalTable) {
            int i = arrangedTable.indexOf(transactionsTable);
            TransactionsTable actualTableRow = arrangedTable.get(i);

            Assertions.assertThat(actualTableRow)
                    .as("Row with %s", transactionsTable.toString())
                    .isEqualTo(transactionsTable);
        }
    }

    private StringBuilder getActualAmountsForErrorMessage() {
        StringBuilder stringBuilder = new StringBuilder();

        for (TransactionsTable transactionsTable : this.actual) {
            String amount = transactionsTable.getAmount();

            stringBuilder.append(amount);
            stringBuilder.append(", ");
        }
        return stringBuilder;
    }

    private List<Float> extractAmountColumn() {
        List<Float> amounts = new ArrayList<>();

        for (TransactionsTable recentTransactionsDatum : actual) {
            float amount = getNumberFrom(recentTransactionsDatum.getAmount());

            amounts.add(amount);
        }
        return amounts;
    }

    private float getNumberFrom(String stringAmount) {
        String amount = stringAmount.replaceAll("[,USD, \\\" \\\"]", "");
        return Float.valueOf(amount);
    }

    private boolean isSorted(List<Float> listOfValues) {
        return Ordering.<Float> natural().isOrdered(listOfValues);
    }
}
