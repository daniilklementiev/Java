package step.learning.basics;

import java.util.*;

public class HomeworkReadline {
    private final Map<String, String> headers;
    public HomeworkReadline() {
        headers = new HashMap<>();
        headers.put("Hello", "Привіт");
        headers.put("World", "Світ");
        headers.put("Java", "Джава");
        headers.put("Dictionary", "Словник");
        headers.put("Homework", "Домашня робота");
    }
    public void run() {
        System.out.println("HomeworkReadline");
        //На базі колекцій реалізувати "словник" - користувач вводить слово,
        //видається його переклад або повідомлення про відсутність слова.
        //Перелік слів закласти статично, не оновлювати з клавіатури.

        // Dictionary
        Menu();
    }

    public void Menu() {
        System.out.println("Dictionary menu");
        System.out.println("1. Add word");
        System.out.println("2. Remove word");
        System.out.println("3. Translate word");
        System.out.println("4. Exit");
        System.out.print("Enter choice: ");
        Scanner kbScanner = new Scanner(System.in);
        int choice = kbScanner.nextInt();
        switch (choice) {
            case 1:
                AddWord();
                break;
            case 2:
                RemoveWord();
                break;
            case 3:
                TranslateWord();
                break;
            case 4:
                System.exit(0);
                break;
            default:
                System.out.println("Wrong choice");
        }
    }

    public void TranslateWord() {
        while(true) {
            System.out.println("Enter word: (type `exit` to exit)");
            Scanner kbScanner = new Scanner(System.in);
            String word = kbScanner.next();
            if (word.equals("exit")) {
                break;
            }
            if (headers.containsKey(word)) {
                System.out.print("Translation: ");
                System.out.println(headers.get(word));
            } else {
                System.out.println("Word not found");
            }
            System.out.println("--------------------");
            Menu();
            break;
        }
    }

    public void AddWord() {
        System.out.print("Enter word: ");
        Scanner kbScanner = new Scanner(System.in);
        String word = kbScanner.next();
        if(word.isEmpty()) {
            System.out.println("Word is empty");
            Menu();
            return;
        }
        else if(headers.containsKey(word)) {
            System.out.println("Word already exists");
            Menu();
            return;
        }

        System.out.print("Enter translation: ");
        String translation = kbScanner.next();
        headers.put(word, translation);
        System.out.printf("New word is: %s - %s\n", word, translation);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------");
        Menu();
    }

    public void RemoveWord() {
        System.out.print("Enter word: ");
        Scanner kbScanner = new Scanner(System.in);
        String word = kbScanner.next();
        if(word.isEmpty()) {
            System.out.println("Word is empty");
            Menu();
            return;
        }
        else if(!headers.containsKey(word)) {
            System.out.println("Word not found");
            Menu();
            return;
        }
        headers.remove(word);
        System.out.printf("Word %s removed\n", word);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("--------------------");
        Menu();
    }

}
