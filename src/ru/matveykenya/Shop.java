package ru.matveykenya;

public class Shop {
    private final AutoProvider autoProvider;
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
                    System.out.println("Продавец: Машин пока нет! " + Thread.currentThread().getName() + " ждите пока...");
                    wait();
                }
                //  Процесс покупки.
                System.out.println(Thread.currentThread().getName() + " покупает " + car);
                isCar = false;
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " поехал на новеньком " + car);
        }
        System.out.println(Thread.currentThread().getName() + " пошел домой пешком");
        Thread.currentThread().interrupt();
    }

    public synchronized void putCar() {
        while (autoProvider.isNextCar()){
            if (!isCar){
                car = autoProvider.getNextCar();
                isCar = true;
                System.out.println("Производитель " + car + " выпустил 1 авто");
                notify();
                System.out.println("нотифай");
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("цикл putCar " + autoProvider.isNextCar() + " " + isCar);
        }
        System.out.println("Поставщик сообщает, что Авто больше не будет!");
        Thread.currentThread().interrupt();
    }
}
