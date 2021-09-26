package ru.matveykenya;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AutoProvider extends Thread {
    private final int countCars;
    protected final static int INTERVAL_MAKER = 1500; // периодичность выпуска авто
    private final List<Cars> listCars = new ArrayList<>();
    Random random = new Random();

    public AutoProvider(int countAuto) {
        this.countCars = countAuto;
        setListCars();
    }

    private void setListCars(){
        for (int i =0; i < countCars; i++) {
            listCars.add(Cars.values()[random.nextInt(Cars.values().length)]);
        }
    }

    public String getNextCar() {
        return isNextCar() ? listCars.remove(0).toString() : null;
    }

    public boolean isNextCar() {
        return listCars.size() > 0;
    }

    private enum Cars {
        Toyota,
        BMW,
        LADA,
        Reno,
        Jeep
    }
}
