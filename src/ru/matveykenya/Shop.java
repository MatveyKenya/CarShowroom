package ru.matveykenya;

public class Shop {
    private final AutoProvider autoProvider;
    private final int TIME_PROCESS = 2000;
    private boolean isCar = false;
    private String car = "";

    public Shop(int totalCars) {
        this.autoProvider = new AutoProvider(totalCars);
    }

    public synchronized void buyCar() {
        while (autoProvider.isNextCar()){
            System.out.println(Thread.currentThread().getName() + " прибыл в салон");
            try {
                while (!isCar){
                    System.out.println("Машин пока нет! " + Thread.currentThread().getName() + " ожидает...");
                    wait();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //  Процесс покупки.
            System.out.println(Thread.currentThread().getName() + " покупает " + car);
            isCar = false;
            try {
                Thread.sleep(TIME_PROCESS);
            } catch (InterruptedException e) {}
            System.out.println(Thread.currentThread().getName() + " поехал на новеньком " + car);
            try {
                Thread.sleep(TIME_PROCESS);
            } catch (InterruptedException e) {}
        }
    }

    public synchronized void putCar() {
        while (autoProvider.isNextCar()){
            if (!isCar){
                car = autoProvider.getNextCar();
                isCar = true;
                System.out.println("\nПроизводитель " + car + " выпустил 1 авто\n");
                notifyAll();
            }
            try {
                wait(AutoProvider.INTERVAL_MAKER);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("\n-----Поставщик сообщает, что Авто больше не будет!-----");
        Thread.currentThread().interrupt();
    }
}
