package com.javarush.task.task26.task2612;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* 
Весь мир играет комедию
*/
public class Solution {
    private Lock lock = new ReentrantLock();

    public void someMethod() {
        try {
            lock.lock();
            if (lock.tryLock()) ifLockIsFree();
            else ifLockIsBusy();//implement logic here, use the lock field
        } catch (Exception e) {
            e.printStackTrace();
            lock.unlock();
        }
    }

    public void ifLockIsFree() {
    }

    public void ifLockIsBusy() {
    }
}
