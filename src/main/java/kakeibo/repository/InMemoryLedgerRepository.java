package kakeibo.repository;

import kakeibo.domain.Transaction;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * メモリ上に取引データを保持するリポジトリ実装
 * とりあえず動作確認用。アプリを終了するとデータは消える。
 * 
 * TODO: 後で JsonLedgerRepository を追加してファイル永続化に対応予定
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

