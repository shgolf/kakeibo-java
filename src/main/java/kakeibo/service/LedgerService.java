package kakeibo.service;

import kakeibo.domain.Transaction;
import kakeibo.repository.LedgerRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 家計簿のビジネスロジックを提供するサービスクラス
 */
public class LedgerService {
    private final LedgerRepository repository;

    public LedgerService(LedgerRepository repository) {
        this.repository = repository;
    }

    /**
     * 取引を追加します
     * @param transaction 追加する取引
     */
    public void addTransaction(Transaction transaction) {
        repository.save(transaction);
    }

    /**
     * すべての取引を取得します
     * @return すべての取引のリスト
     */
    public List<Transaction> getAllTransactions() {
        return repository.findAll();
    }

    /**
     * 指定した日付範囲の取引を取得します
     * @param startDate 開始日
     * @param endDate 終了日
     * @return 指定範囲の取引のリスト
     */
    public List<Transaction> getTransactionsByDateRange(LocalDate startDate, LocalDate endDate) {
        return repository.findAll().stream()
                .filter(t -> !t.getDate().isBefore(startDate) && !t.getDate().isAfter(endDate))
                .collect(Collectors.toList());
    }

    /**
     * カテゴリ別の取引を取得します
     * @param category カテゴリ名
     * @return 指定カテゴリの取引のリスト
     */
    public List<Transaction> getTransactionsByCategory(String category) {
        return repository.findAll().stream()
                .filter(t -> t.getCategory().equals(category))
                .collect(Collectors.toList());
    }

    /**
     * 現在の残高を計算します（全期間の収支合計）
     * @return 残高
     */
    public double calculateBalance() {
        return repository.findAll().stream()
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    /**
     * 指定期間の収入合計を計算します
     * @param startDate 開始日
     * @param endDate 終了日
     * @return 収入合計
     */
    public double calculateTotalIncome(LocalDate startDate, LocalDate endDate) {
        return getTransactionsByDateRange(startDate, endDate).stream()
                .filter(Transaction::isIncome)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    /**
     * 指定期間の支出合計を計算します
     * @param startDate 開始日
     * @param endDate 終了日
     * @return 支出合計（負の値）
     */
    public double calculateTotalExpense(LocalDate startDate, LocalDate endDate) {
        return getTransactionsByDateRange(startDate, endDate).stream()
                .filter(Transaction::isExpense)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    /**
     * カテゴリ別の支出を集計します
     * @return カテゴリごとの支出額のマップ
     */
    public Map<String, Double> calculateExpenseByCategory() {
        return repository.findAll().stream()
                .filter(Transaction::isExpense)
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.summingDouble(Transaction::getAmount)
                ));
    }

    /**
     * カテゴリ別の収入を集計します
     * @return カテゴリごとの収入額のマップ
     */
    public Map<String, Double> calculateIncomeByCategory() {
        return repository.findAll().stream()
                .filter(Transaction::isIncome)
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.summingDouble(Transaction::getAmount)
                ));
    }
}

