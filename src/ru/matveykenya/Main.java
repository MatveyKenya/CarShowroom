package ru.matveykenya;

import java.util.TreeMap;

public class Main {
    final static int INTERVAL_MAKER = 2000; // периодичность выпуска авто
    final static int TOTAL_CAR = 10;

    public static void main(String[] args) {
        final Shop shop = new Shop(TOTAL_CAR);



        //  Идем за покупками
        Runnable shopping = shop::buyCar;
        new Thread(null, shopping, "Покупатель 1").start();
        new Thread(null, shopping, "Покупатель 2").start();
        new Thread(null, shopping, "Покупатель 3").start();

        new Thread(null, shop::putCar, "Автопром").start();

    }
}
