# 家計簿アプリ 要件定義書

## 1. 概要
本アプリケーションは、日々の支出や収入を記録し、月別集計・カテゴリ別集計などの基本的な家計管理ができる Java アプリである。
将来的に GUI（JavaFX）や Web アプリ（Spring Boot）への拡張を見据え、3層構造（Presentation / Service / Repository）を採用する。

---

## 2. 使用技術
- Java 21 (Corretto)
- 標準ライブラリ
- JSON 保存（将来的に導入：Gson or Jackson）
- エディタ: Cursor
- バージョン管理: GitHub

---

## 3. アーキテクチャ
本アプリケーションは以下の 3 層構造で構成される。

### 3.1 Presentation 層
- ユーザーとのインタラクションを担当
- 初期実装：CLI（コンソール UI）
- 将来：GUI（JavaFX）を追加可能

### 3.2 Service 層
- ビジネスロジックを担当
- 記録の追加、月次集計、カテゴリ別集計などの処理

### 3.3 Repository 層
- データの永続化を担当
- 初期実装：メモリ上での保存（InMemory）
- 将来：JSON 保存（JsonLedgerRepository）を追加予定

---

## 4. 基本機能（MVP）
### 4.1 記録の追加
- 収支の記録を入力し保存できる
- 入力項目：
  - 日付（YYYY-MM-DD）
  - 金額
  - 種類（収入 / 支出）
  - カテゴリ（自由入力、後で追加可能）
  - メモ（任意）

### 4.2 記録の一覧表示
- 保存された全ての記録を一覧形式で表示

### 4.3 月次集計
- 指定した年月の収支合計を表示
- 合計金額は「収入 − 支出」で算出

---

## 5. 将来的な実装項目（拡張要件）
### 5.1 カテゴリ管理
- カテゴリ一覧を管理
- カテゴリの追加・編集

### 5.2 JSON 永続化
- Repository を JSON 保存版に差し替え可能にする

### 5.3 カテゴリ別集計
- 特定カテゴリの月次・年間集計

### 5.4 GUI（JavaFX）化
- ボタン・入力フォームを用いた直感的なUI
- CLIと共存可能な構造にする

### 5.5 グラフ表示（オプション）
- Python 連携 or JavaFX Charts を利用して支出推移グラフを表示

---

## 6. ディレクトリ構成（想定）
src/main/java/kakeibo/
├ presentation/
│ └ cli/
│ └ Main.java
├ domain/
│ └ Transaction.java
├ service/
│ └ LedgerService.java
└ repository/
├ LedgerRepository.java
├ InMemoryLedgerRepository.java
└ JsonLedgerRepository.java (予定)

---

## 7. 動作環境
- Java 21 がインストールされていること
- ターミナルからコンパイル・実行可能

---

## 8. 実行方法
### 8.1 コンパイル
javac -d out $(find src/main/java -name "*.java")

### 8.2 実行
java -cp out kakeibo.presentation.cli.Main

---

## 9. 開発方針
- MVP（最小機能）を CLI でまず完成させる
- その後、Repository を JSON 保存に差し替え
- GUI 化（JavaFX）はプロジェクト後半で実施
- Cursor による AI 駆動開発でコード生成・リファクタリングを支援する

