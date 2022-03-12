package ro.tuc.tp.Logic;

import ro.tuc.tp.Model.Client;
import ro.tuc.tp.Model.Queue;

import java.util.concurrent.BlockingQueue;

public class ConcreteStrategyQueue implements Strategy {
    @Override
    public void addClient(BlockingQueue<Queue> queues, Client c){
        int minSize=9999;
        Queue bestQueue= new Queue();
        for(Queue q: queues){
            if(q.getSize()<minSize){
                minSize = q.getSize();
                bestQueue = q;
            }
        }
        bestQueue.addClient(c);
    }
}
