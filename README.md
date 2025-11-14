# kakeibo-java
個人練習用

```
kakeibo-java/
src/main/java/kakeibo/
  ├ presentation/
  │   └ cli/
  │       └ Main.java              ← 今のメイン（UI）
  ├ domain/
  │   └ Transaction.java           ← お金の出入りの「モデル」
  ├ service/
  │   └ LedgerService.java         ← ビジネスロジック
  ├ repository/
  │ ├ LedgerRepository.java      ← データ層のインターフェース
  │ └ InMemoryLedgerRepository.java  ← とりあえずメモリ実装
  │   // 後で JsonLedgerRepository を追加予定
  ├ README.md
  └ .gitignore
```
