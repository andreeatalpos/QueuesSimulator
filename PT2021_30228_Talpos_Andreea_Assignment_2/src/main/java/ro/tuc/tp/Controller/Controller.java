package ro.tuc.tp.Controller;

import ro.tuc.tp.Logic.Scheduler;
import ro.tuc.tp.Logic.SelectionPolicy;
import ro.tuc.tp.Model.Client;
import ro.tuc.tp.View.UserInterface;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Controller implements Runnable {
    private UserInterface userInterface;
    private int arrivalMin;
    private int arrivalMax;
    private int serviceMin;
    private int serviceMax;
    private int nrQueues;
    private AtomicInteger time;
    private int simulationInterval;
    private SelectionPolicy selectionPolicy;
    private Scheduler scheduler;
    private int peakHour = 0;
    private int maxClientsEver = 0;
    private boolean isRunning;
    private Thread controller;
    private BlockingQueue<Client> clients;
    private List<Client> listaClienti;

    private int nrClients = 0;
    private int totalServiceTime = 0;
    private Fisier result;

    public void start() {
        userInterface = new UserInterface();
        userInterface.setVisible(true);
        startSimulation();
        controller = new Thread(this);
        listaClienti = new ArrayList<Client>();
        this.isRunning = true;

    }

    public void stop() {
        isRunning = false;
    }

    public void generateNRandomClients() {
        Random rand = new Random();
        for (int i = 1; i <= nrClients; i++) {
            Client c = new Client(i, arrivalMin + rand.nextInt(arrivalMax - arrivalMin + 1), serviceMin + rand.nextInt(serviceMax - serviceMin + 1));
            listaClienti.add(c);
        }
        Collections.sort(listaClienti);
        clients = new LinkedBlockingQueue<>();
        clients.addAll(listaClienti);


    }

    public String toString2(int time) {
        String rez = "Current time: " + time + "\n";
        rez += "Waiting clients: " + "\n";
        if (listaClienti.size() != 0)
            for (Client c : listaClienti)
                if (!c.isAtQueue())
                    rez += c.toString() + " ";
        rez += "\n" + scheduler.toString() + "\n";
        return rez;
    }

    public boolean checkInput() {
        try {
            int nrClients = Integer.parseInt(userInterface.getNoOfClients());
            int nrQueues = Integer.parseInt(userInterface.getNoOfQueues());
            int arrivalMin = Integer.parseInt(userInterface.getMinArrivalTime());
            int arrivalMax = Integer.parseInt(userInterface.getMaxArrivalTime());
            int serviceMin = Integer.parseInt(userInterface.getMinServiceTime());
            int serviceMax = Integer.parseInt(userInterface.getMaxServiceTime());
            int simulationInterval = Integer.parseInt(userInterface.getSimulationTime());
            if (nrClients > 0 && nrQueues > 0 && arrivalMin > 0 && arrivalMax > 0 && serviceMin > 0 && serviceMax > 0 && (serviceMax + arrivalMax) <= simulationInterval && arrivalMin < arrivalMax && serviceMin < serviceMax) {
                setData(nrClients, nrQueues, arrivalMin, arrivalMax, serviceMin, serviceMax, simulationInterval);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid input!!");
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Invalid Input!!");
            return false;
        }
    }

    public void setData(int nrClients, int nrQueues, int arrivalMin, int arrivalMax, int serviceMin, int serviceMax, int simulationInterval) {
        this.arrivalMin = arrivalMin;
        this.arrivalMax = arrivalMax;
        this.serviceMin = serviceMin;
        this.serviceMax = serviceMax;
        this.nrQueues = nrQueues;
        this.simulationInterval = simulationInterval;
        this.nrClients = nrClients;
    }

    public void startSimulation() {
        userInterface.addShortestQueueButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectionPolicy = SelectionPolicy.SHORTEST_QUEUE;
            }
        });
        userInterface.addShortestTimeButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectionPolicy = SelectionPolicy.SHORTEST_TIME;
            }
        });
        userInterface.addStartButtonActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkInput() == true) {
                    scheduler = new Scheduler(nrQueues, nrClients, selectionPolicy);
                    scheduler.changeStrategy(selectionPolicy);
                    generateNRandomClients();
                    controller.start();
                }
            }
        });
    }

    public void run() {
        result = new Fisier();
        result.openFile();
        time = new AtomicInteger(0);


        Iterator<Client> iterator = listaClienti.iterator();
        Client c = iterator.next();
        while (time.intValue() < simulationInterval) {
                while (c.getArrivalTime() == time.intValue()) {
                    totalServiceTime += c.getServiceTime();
                    scheduler.dispatchClient(c);
                    clients.remove(c);

                    if (iterator.hasNext())
                        c = iterator.next();
                    else break;
                }
                scheduler.avgWaiting();
            result.addText(toString2(time.intValue()));
            System.out.println(toString2(time.intValue()));
            userInterface.setLogs(toString2(time.intValue()));
            if(scheduler.getMaxClientsEver()>maxClientsEver){
                maxClientsEver=scheduler.getMaxClientsEver();
                peakHour=time.intValue();
            }
            time.addAndGet(1);
            userInterface.setCurrentTime(time + "");
                scheduler.checkEmpty();
                if (scheduler.isEmpty&& clients.isEmpty()) {
                    break;
                }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            }
            System.out.println(("Average service time: " + (float) totalServiceTime/nrClients));
            userInterface.setAverageServiceTime((float) totalServiceTime/nrClients+"");
            System.out.println("Peak hour: " + peakHour);
            userInterface.setPeakHour(peakHour + "");
            userInterface.setAverageSWaitingTime(scheduler.avgWaitTime()+"");
            result.closeFile();
            System.out.println("Simulation is over!");
        }
        public int getTime(){
        return time.intValue();
        }
    }





