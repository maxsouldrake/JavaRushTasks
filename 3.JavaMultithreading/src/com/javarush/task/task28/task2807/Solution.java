package com.javarush.task.task28.task2807;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/* 
Знакомство с ThreadPoolExecutor
*/
public class Solution {
    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();

        for(int i = 1; i <= 10; i++)
        {
            int num = i;
            queue.add(new Runnable() {
                public void run()
                {
                    doExpensiveOperation(num);// тут мы загружаем что-то тяжелое из интернета.
                }
            });
        }

        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 5, 1000, TimeUnit.MILLISECONDS, queue);
        executor.prestartAllCoreThreads();
        executor.shutdown();
        executor.awaitTermination(5, TimeUnit.SECONDS);
        //Add your code here

        /* output example
pool-1-thread-2, localId=2
pool-1-thread-3, localId=3
pool-1-thread-1, localId=1
pool-1-thread-3, localId=5
pool-1-thread-2, localId=4
pool-1-thread-3, localId=7
pool-1-thread-1, localId=6
pool-1-thread-3, localId=9
pool-1-thread-2, localId=8
pool-1-thread-1, localId=10
         */
    }

    private static void doExpensiveOperation(int localId) {
        System.out.println(Thread.currentThread().getName() + ", localId=" + localId);
    }
}
