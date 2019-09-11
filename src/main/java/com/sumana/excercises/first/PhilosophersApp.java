package com.sumana.excercises.first;

import com.sumana.excercises.utils.ThreadColor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

public class PhilosophersApp {
    public static void main(String[] args) {
        //Reference for the 5 forks on the table, each philosopher can access to 2 different forks at the time
        Fork fork1 = new Fork(1);
        Fork fork2 = new Fork(2);
        Fork fork3 = new Fork(3);
        Fork fork4 = new Fork(4);
        Fork fork5 = new Fork(5);

//        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //hp can access to for
        HungryPhilosopher hungryPhilosopher1 = new HungryPhilosopher(fork1, fork5, ThreadColor.ANSI_CYAN,1);
        HungryPhilosopher hungryPhilosopher2 = new HungryPhilosopher(fork2, fork1, ThreadColor.ANSI_PURPLE,2);
        HungryPhilosopher hungryPhilosopher3 = new HungryPhilosopher(fork3, fork2, ThreadColor.ANSI_YELLOW,3);
        HungryPhilosopher hungryPhilosopher4 = new HungryPhilosopher(fork4, fork3, ThreadColor.ANSI_GREEN,4);
        HungryPhilosopher hungryPhilosopher5 = new HungryPhilosopher(fork5, fork4, ThreadColor.ANSI_RED,5);

        new Thread(hungryPhilosopher1).start();
        new Thread(hungryPhilosopher2).start();
        new Thread(hungryPhilosopher3).start();
        new Thread(hungryPhilosopher4).start();
        new Thread(hungryPhilosopher5).start();

        /*executorService.execute(hungryPhilosopher1);
        executorService.execute(hungryPhilosopher2);
        executorService.execute(hungryPhilosopher3);
        executorService.execute(hungryPhilosopher4);
        executorService.execute(hungryPhilosopher5);*/
    }
}

class Fork {
    private ReentrantLock lock = new ReentrantLock();
    private String eaterName;
    private int forkNumber;

    public Fork(int forkNumber) {
        this.forkNumber = forkNumber;
    }

    public void useFork(int eatingPastaTime, String color, int philosopherNumber) {
        lock.lock();
        try {
            eaterName = color;
            System.out.println(color + "Philosopher " + philosopherNumber + " using fork #" + forkNumber);
            Thread.sleep(eatingPastaTime);
        } catch (InterruptedException e) {
            System.err.println("Fork got interrupted by another philosopher!");
        } finally {
            lock.unlock();
        }
    }

    public boolean onUse() {
        return lock.isLocked();
    }
}

class HungryPhilosopher implements Runnable {
    private boolean hungry = true;
    private Fork leftFork;
    private Fork rightFork;
    private String color;
    private int philosopherNumber;

    private final Random random = new Random();

    public HungryPhilosopher(Fork leftFork, Fork rightFork, String color, int philosopherNumber) {
        this.leftFork = leftFork;
        this.rightFork = rightFork;
        this.color = color;
        this.philosopherNumber = philosopherNumber;
    }

    @Override
    public void run() {
        while (hungry) {
            if (!rightFork.onUse() && !leftFork.onUse()) {
                Integer eatingPastaTime = random.nextInt(500);
                rightFork.useFork(eatingPastaTime, color, philosopherNumber);
                leftFork.useFork(eatingPastaTime, color, philosopherNumber);
                hungry = false;
            }
        }

        System.out.println(color + "Philosopher " + philosopherNumber + " have already eat!!");
    }
}
