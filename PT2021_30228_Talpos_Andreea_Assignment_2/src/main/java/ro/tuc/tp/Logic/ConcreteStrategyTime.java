package ro.tuc.tp.Logic;

import ro.tuc.tp.Model.Client;
import ro.tuc.tp.Model.Queue;

import java.util.concurrent.BlockingQueue;

public class ConcreteStrategyTime implements Strategy {
    @Override
    public void addClient(BlockingQueue<Queue> queues, Client c) {
                int minTime = 9999;
                Queue bestQueue = new Queue();
                for (Queue q : queues) {
                    if (q.getWaitingPeriod() < minTime) {
                        bestQueue = q;
                        minTime = q.getWaitingPeriod();
                    }
                }
                bestQueue.addClient(c);
    }
}
