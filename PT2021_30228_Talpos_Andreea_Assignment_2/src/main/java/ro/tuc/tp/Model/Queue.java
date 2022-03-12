package ro.tuc.tp.Model;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Queue implements Runnable {
    private BlockingQueue<Client> clients;
    AtomicInteger waitingPeriod;
    private  int queueID=0;
    private int avgWaiting=0;
    private int size=0;
    private Thread queueThread;
    private boolean isRunning;
    private boolean active=false;

    public Queue(){
        clients = new PriorityBlockingQueue<>();
        waitingPeriod=new AtomicInteger(0);
        queueThread = new Thread(this);
        isRunning = false;
        start();
    }
    public void addClient(Client newClient){
        try {
            size++;
            clients.put(newClient);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitingPeriod.addAndGet(newClient.getServiceTime());
    }
    public void run()
    {
        while(isRunning) {
            while (!clients.isEmpty()) {
                AtomicInteger timp = new AtomicInteger(0);
                timp.addAndGet(clients.peek().getServiceTime());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                timp.addAndGet(-1);
                if (timp.intValue()== 0){
                    System.out.println("Client " + clients.peek().getClientID() + " left!");
                    clients.poll();
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }else {
                    clients.peek().setServiceTime(timp.intValue());
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            }
        }
    public BlockingQueue<Client> getClients() {
        return clients;
    }
    public int getWaitingPeriod() {
        return waitingPeriod.intValue();
    }

    public void avgWaiting(){
        if(clients.size()!=0)
            avgWaiting++;
    }
public float getAvgWaiting(){
        return avgWaiting;
}
    public int getSize(){
        return size;
    }
    public String toString(){
        String rez = "Coada "+ this.getQueueID()+":";
        if(this.clients.isEmpty())
            rez+="Closed";
        return rez;
    }
    public void start(){
        isRunning = true;
        queueThread.start();
    }
    public void stop(){
        isRunning=false;
    }

    public int getQueueID(){
        return queueID;
    }
    public void setQueueID(int id){
        queueID = id;
    }

}

