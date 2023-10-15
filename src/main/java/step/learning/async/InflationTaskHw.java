package step.learning.async;

import java.util.concurrent.*;

public class InflationTaskHw {
    public void run() throws ExecutionException, InterruptedException {
        int numThreads = 6;
        ExecutorService executorService = Executors.newFixedThreadPool(numThreads);

        double initialAmount = 100.0;
        double annualInflationRate = 0.10;

        int months = 12;

        SharedResource sharedResource = new SharedResource(initialAmount);

        Callable<Void> inflationCalculatorTask = () -> {
            for (int month = 1; month <= months; month++) {
                double currentAmount = sharedResource.getCurrentAmount();
                currentAmount += currentAmount * annualInflationRate / 12;
                sharedResource.setCurrentAmount(currentAmount);
                System.out.println("Місяць " + month + ": " + currentAmount);
            }
            return null;
        };

        Future<Void> result = executorService.submit(inflationCalculatorTask);

        result.get();

        executorService.shutdown();

        System.out.println("Ітогова сума після " + months + " місяців: " + sharedResource.getCurrentAmount());
    }
    static class SharedResource {
        private double currentAmount;

        public SharedResource(double initialAmount) {
            this.currentAmount = initialAmount;
        }

        public synchronized double getCurrentAmount() {
            return currentAmount;
        }

        public synchronized void setCurrentAmount(double currentAmount) {
            this.currentAmount = currentAmount;
        }
    }
}


