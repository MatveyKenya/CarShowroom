package ru.matveykenya;

public class Main {
    final static int TOTAL_CAR = 10;

    public static void main(String[] args) throws InterruptedException {
        final Shop shop = new Shop(TOTAL_CAR);

        //  Идем за покупками
        Runnable shopping = shop::buyCar;
        Thread bayer1 = new Thread(null, shopping, "Покупатель_1");
        bayer1.setDaemon(true);
        bayer1.start();
        Thread bayer2 = new Thread(null, shopping, "Покупатель_2");
        bayer2.setDaemon(true);
        bayer2.start();
        Thread bayer3 = new Thread(null, shopping, "Покупатель_3");
        bayer3.setDaemon(true);
        bayer3.start();
        Thread bayer4 = new Thread(null, shopping, "Покупатель_4");
        bayer4.setDaemon(true);
        bayer4.start();

        new Thread(null, shop::putCar, "Автопром").start();
    }
}
