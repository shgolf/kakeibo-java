package kakeibo.domain;

import java.time.LocalDate;
import java.util.Objects;

/**
 * お金の出入りを表すモデルクラス
 */
public class Transaction {
    private final String description;
    private final double amount;
    private final String category;
    private final LocalDate date;

    /**
     * Transactionを作成します
     * @param description 説明
     * @param amount 金額（正の値は収入、負の値は支出）
     * @param category カテゴリ
     * @param date 日付
     */
    public Transaction(String description, double amount, String category, LocalDate date) {
        this.description = Objects.requireNonNull(description, "description must not be null");
        this.amount = amount;
        this.category = Objects.requireNonNull(category, "category must not be null");
        this.date = Objects.requireNonNull(date, "date must not be null");
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

    public String getCategory() {
        return category;
    }

    public LocalDate getDate() {
        return date;
    }

    /**
     * 収入かどうかを判定します
     * @return 収入の場合true
     */
    public boolean isIncome() {
        return amount > 0;
    }

    /**
     * 支出かどうかを判定します
     * @return 支出の場合true
     */
    public boolean isExpense() {
        return amount < 0;
    }

    @Override
    public String toString() {
        return String.format("%s | %s | %.2f円 | %s",
                date,
                category,
                amount,
                description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Double.compare(that.amount, amount) == 0 &&
                Objects.equals(description, that.description) &&
                Objects.equals(category, that.category) &&
                Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description, amount, category, date);
    }
}

