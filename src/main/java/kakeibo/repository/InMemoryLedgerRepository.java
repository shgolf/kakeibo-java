package kakeibo.repository;

import kakeibo.domain.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * メモリ上に取引データを保持するリポジトリ実装
 * <p>
 * アプリケーション終了時にデータは失われます。
 * 永続化が必要な場合は、{@code JsonLedgerRepository} など別の実装を使用してください。
 * </p>
 */
public class InMemoryLedgerRepository implements LedgerRepository {
    private final List<Transaction> transactions = new ArrayList<>();

    @Override
    public void save(Transaction transaction) {
        transactions.add(transaction);
    }

    @Override
    public List<Transaction> findAll() {
        // 内部リストの変更を防ぐため、コピーを返す
        return Collections.unmodifiableList(new ArrayList<>(transactions));
    }

    @Override
    public void deleteAll() {
        transactions.clear();
    }
}

