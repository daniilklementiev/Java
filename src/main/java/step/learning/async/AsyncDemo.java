package step.learning.async;

import java.util.Locale;

public class AsyncDemo {
    private double sum;
    private final Object sumLocker = new Object();
    private int activeThreadsCount;
    private final Object atcLocker = new Object();

    // HW
    private static final int NUM_THREADS = 10;
    private static final String[] digits = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private static StringBuilder pandigitalNumber = new StringBuilder();
    private static int threadsFinished = 0;

    public void run() {
        /*
         *   Д.З. Засобами багатопоточності реалізувати задачу формування
         *    pandigital числа, яке складається з усіх цифр 0-9. Порядок
         *    цифр ролі не грає.
         *    Запустити 10 потоків, кожен з яких здійснює затримку на 1с
         *    після чого додає свою цифру до спільного
         *    "числа" (String) та виводить проміжний результат:
         *      додано 7, результат 1287
         *    Передбачити усі необхідні заходи синхронізації.
         *    У кінці виводиться підсумок один раз
         *     ---------
         *     Результат 1287034659
         */
        for (int i = 0; i < NUM_THREADS; i++) {
            final int threadNumber = i;
            Thread thread = new Thread(() -> {
                try {
                    Thread.sleep(1000); // Затримка на 1 секунду
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (pandigitalNumber) {
                    pandigitalNumber.append(digits[threadNumber]);
                    System.out.println("Додано " + digits[threadNumber] + ", результат " + pandigitalNumber.toString());
                    threadsFinished++;
                    if (threadsFinished == NUM_THREADS) {
                        System.out.println("Результат " + pandigitalNumber.toString());
                    }
                }
            });
            thread.start();
        }
    }

    public void run4() {
        int months = 12;
        Thread[] threads = new Thread[months];
        sum = 100;
        activeThreadsCount = months;
        for (int i = 0; i < months; i++) {
            threads[i] = new Thread(new MonthRate(i + 1));
            threads[i].start();
        }
//        try {
//            for (int i = 0; i < 12; i++) {
//
//                threads[i].join();
//
//            }
//        }
//        catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//        System.out.println("Annual: " + sum);
    }

    class MonthRate implements Runnable{
        int month;
        public MonthRate(int month) {
            this.month = month;
        }

        @Override
        public void run() {
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException ex) {
//                System.err.println(ex.getMessage());
//            }

            double percent = 10.0;
            double localSum;
            double factor = (1.0 + percent / 100.0);
            synchronized (sumLocker){
                localSum =
                sum = sum * factor;
            }
            System.out.printf(Locale.US, "Month: %02d, percent: %.1f, sum: %.2f %n", this.month, percent, localSum);

            // Решение №2 - поток определяет является ли он последним
            boolean isLast;
            synchronized (atcLocker){
                activeThreadsCount--;
                isLast = activeThreadsCount == 0;
            }
            try { Thread.sleep(1); }
            catch (InterruptedException ex) { System.err.println(ex.getMessage()); }
            if(isLast) {
                System.out.println("Annual: " + sum);
            }
        }
    }
    public void run2() {
        // АПИ банка выдает проценты месячной инфляции за каждый месяц, для их получения необходимы отдельные запросы, необходимо реализовать их
        // в ассинхронном режиме. Определить годовую инфляцию

        int numMonths = 12; // количество месяцев
        double[] monthlyInflation = new double[numMonths]; // массив инфляций по месяцам
        Thread[] threads = new Thread[numMonths]; // потоки для каждого месяца

        for (int month = 0; month < numMonths; month++) {
            int currentMonth = month;
            threads[month] = new Thread(() -> {
                double inflation = Math.random() * 10;
                System.out.println("Month " + (currentMonth + 1) + ": " + inflation + "%");
                monthlyInflation[currentMonth] = inflation;
            });
        }

        for (Thread thread : threads) {
            thread.start();
        }

        for (Thread thread : threads) {
            try {
                thread.join();
            } catch (InterruptedException ex) {
                ex.getMessage();
            }
        }

        double annualInflation = 0;
        for (double inflationRate : monthlyInflation) {
            annualInflation += inflationRate;
        }
        System.out.println("Annual inf: " + annualInflation + "%");
    }
    public void run1() {
        System.out.println( "Demo" );
        Thread thread1 = new Thread(
                () -> {
                    System.out.println( "Thread1 starts" );
                    try {
                        Thread.sleep( 1000 );
                    }
                    catch ( InterruptedException ex ) {
                        System.err.println( ex.getMessage() );
                    }
                    System.out.println( "Thread1 finishes" );
                }
        );
        thread1.start();
        try {
            thread1.join();
        }
        catch ( InterruptedException ex ) {
            System.err.println( ex.getMessage() );
        }

        System.out.println("Demo finishes");
    }
}
