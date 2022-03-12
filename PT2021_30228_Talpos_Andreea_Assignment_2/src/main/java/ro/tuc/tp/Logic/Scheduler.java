package ro.tuc.tp.Logic;

import ro.tuc.tp.Model.Client;
import ro.tuc.tp.Model.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Scheduler {
    private BlockingQueue<Queue> queues;
    private int maxNoQueues;
    public boolean isEmpty = false;
    private int maxClientsEver=0;
    private int maxClientsPerQueue;
    private Strategy strategy;
    private float waitingPeriod=0;
    private SelectionPolicy policy;
    public Scheduler(int maxNoQueues, int maxClientsPerQueue, SelectionPolicy policy){
        queues = new LinkedBlockingQueue<>();
        this.policy  = policy;
        this.maxClientsPerQueue = maxClientsPerQueue;
        this.maxNoQueues = maxNoQueues;
        for(int i=0; i<maxNoQueues; i++){
            Queue queue = new Queue();
            try {
                queues.put(queue);
                queue.setQueueID(i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void changeStrategy(SelectionPolicy policy){
        if(policy == SelectionPolicy.SHORTEST_QUEUE){
            strategy = new ConcreteStrategyQueue();
        }
        if(policy == SelectionPolicy.SHORTEST_TIME){
            strategy = new ConcreteStrategyTime();
        }
    }
    public void dispatchClient(Client c){
        strategy.addClient(queues, c);
        c.setAtQueue(true);
    }
    public int getMaxClientsEver(){
        int sumSize=0;
        BlockingQueue<Queue> currentQueues = queues;
        for(Queue q: currentQueues)
            sumSize+=q.getSize();
        if(sumSize>maxClientsEver)
           maxClientsEver = sumSize;
        return maxClientsEver;
    }
    public String toString(){
        String rez="";
        for(Queue q: this.queues){
            rez+=q.toString();
            for(Client c: q.getClients())
                rez+=c.toString();
            rez+="\n";
        }
        return rez+"\n";
    }
    public float avgWaitTime(){
        int i=0;
        for(Queue q: queues) {
            i++;
            waitingPeriod+=q.getAvgWaiting();
            q.stop();
        }
        return waitingPeriod/i;
    }
    public void avgWaiting(){
        for(Queue q : queues){
            q.avgWaiting();
            }
    }

    public void checkEmpty(){
        boolean isEmpty=true;
        for(Queue q : queues){
            if(!q.getClients().isEmpty()){
                isEmpty=false;
            }
        }

        this.isEmpty=isEmpty;
    }
}
