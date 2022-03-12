package ro.tuc.tp.Logic;

import ro.tuc.tp.Model.Client;
import ro.tuc.tp.Model.Queue;

import java.util.concurrent.BlockingQueue;

public interface Strategy {
    public void addClient(BlockingQueue<Queue> queues, Client c);
}
