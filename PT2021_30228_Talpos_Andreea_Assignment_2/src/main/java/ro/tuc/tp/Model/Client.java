package ro.tuc.tp.Model;
public class Client implements Comparable<Client> {
    private int clientID;
    private int arrivalTime;
    private int processingTime;
    private boolean atQueue=false;
    public Client( int clientID, int arrivalTime, int processingTime){
        this.clientID = clientID;
        this.arrivalTime = arrivalTime;
        this.processingTime = processingTime;
    }

    public void setServiceTime(int serviceTime) {
        this.processingTime = serviceTime;
    }
    public int getClientID() {
        return clientID;
    }
    public int getArrivalTime() {
        return arrivalTime;
    }
    public int getServiceTime() {
        return processingTime;
    }

    public void setAtQueue(boolean atQueue) {
        this.atQueue = atQueue;
    }
    public String toString(){
        return "("+ this.getClientID() + " , " + this.getArrivalTime()+" , " + this.getServiceTime()+")";
    }
    public boolean isAtQueue() {
        return atQueue;
    }
    @Override
    public int compareTo(Client c){
        return this.getArrivalTime()-c.getArrivalTime();
    }
}
