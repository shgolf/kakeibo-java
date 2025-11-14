package kakeibo.repository;

import kakeibo.domain.Transaction;

import java.util.List;

/**
 * 家計簿データのリポジトリインターフェース
 * データ層の抽象化により、実装を切り替え可能にする
 */
public interface LedgerRepository {
    /**
     * 取引を保存します
     * @param transaction 保存する取引
     */
    void save(Transaction transaction);

    /**
     * すべての取引を取得します
     * @return すべての取引のリスト
     */
    List<Transaction> findAll();

    /**
     * すべての取引を削除します
     */
    void deleteAll();
}

