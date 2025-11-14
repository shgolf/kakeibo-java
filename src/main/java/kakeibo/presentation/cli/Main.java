package kakeibo.presentation.cli;

import kakeibo.domain.Transaction;
import kakeibo.repository.InMemoryLedgerRepository;
import kakeibo.repository.LedgerRepository;
import kakeibo.service.LedgerService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

/**
 * 家計簿アプリケーションのメインクラス（CLI版）
 */
public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static LedgerService ledgerService;

    public static void main(String[] args) {
        // リポジトリとサービスの初期化
        LedgerRepository repository = new InMemoryLedgerRepository();
        ledgerService = new LedgerService(repository);

        System.out.println("=== 家計簿アプリケーション ===");

        boolean running = true;
        while (running) {
            showMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addTransaction();
                    break;
                case "2":
                    viewAllTransactions();
                    break;
                case "3":
                    viewBalance();
                    break;
                case "0":
                    running = false;
                    System.out.println("終了します。");
                    break;
                default:
                    System.out.println("無効な選択です。もう一度入力してください。");
            }
            System.out.println();
        }

        scanner.close();
    }

    private static void showMenu() {
        System.out.println("\n--- メニュー ---");
        System.out.println("1. 取引を追加");
        System.out.println("2. 全取引を表示");
        System.out.println("3. 残高を表示");
        System.out.println("0. 終了");
        System.out.print("選択してください: ");
    }

    private static void addTransaction() {
        System.out.print("説明: ");
        String description = scanner.nextLine().trim();

        System.out.print("金額（収入は正、支出は負）: ");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("無効な金額です。");
            return;
        }

        System.out.print("カテゴリ: ");
        String category = scanner.nextLine().trim();

        Transaction transaction = new Transaction(description, amount, category, LocalDate.now());
        ledgerService.addTransaction(transaction);
        System.out.println("取引を追加しました。");
    }

    private static void viewAllTransactions() {
        List<Transaction> transactions = ledgerService.getAllTransactions();
        if (transactions.isEmpty()) {
            System.out.println("取引がありません。");
            return;
        }

        System.out.println("\n--- 全取引 ---");
        for (Transaction t : transactions) {
            System.out.println(t);
        }
    }

    private static void viewBalance() {
        double balance = ledgerService.calculateBalance();
        System.out.printf("現在の残高: %.2f円\n", balance);
    }
}

