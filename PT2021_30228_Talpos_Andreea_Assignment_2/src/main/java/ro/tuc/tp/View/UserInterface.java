package ro.tuc.tp.View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.PrintWriter;

public class UserInterface extends JFrame {
    private JLabel title;
    private JLabel input;

    private JLabel minArrivalTime;
    private JLabel maxArrivalTime;
    private JLabel minServiceTime;
    private JLabel maxServiceTime;
    private JTextField arrivalMin;
    private JTextField arrivalMax;
    private JTextField serviceMin;
    private JTextField serviceMax;

    private JLabel currentTime;
    private JLabel pHour;
    private JTextField currTime;
    private JTextField peakHour;



    private PrintWriter writer;
    private JLabel noOfQueues;
    private JLabel noOfClients;
    private JTextField nrQueues;
    private JTextField nrClients;

    private JLabel simulationTime;
    private JTextField simTime;
    private JLabel avgWaiting;
    private JTextField averageWaiting;
    private JLabel avgServiceTime;
    private JTextField averageServiceTime;

    private JTextArea logsField;
    private JLabel logsLabel;
    private JScrollPane logScrollPane;

    private JLabel selectStrategy;
    private JButton startSimulation;
    private JButton shortestTime;
    private JButton shortestQueue;

    public UserInterface(){
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setBounds(200,200,800,700);
        this.getContentPane().setLayout(null);

        Font bigFont = new Font("Times New Roman", Font.PLAIN, 18);
        Font bigBold = new Font("Times New Roman", Font.BOLD, 20);
        Font hugeFont = new Font("Times New Roman", Font.PLAIN, 30);
        title = new JLabel("Queues Simulator");
        title.setFont(hugeFont);
        title.setBounds(280,10,300,35);
        getContentPane().add(title);

        input = new JLabel("Introduce:");
        input.setFont(bigBold);
        input.setBounds(25,40,200,35);
        getContentPane().add(input);

        noOfClients = new JLabel("No. clients: ");
        noOfClients.setFont(bigFont);
        noOfClients.setBounds(30,80, 100,20);
        getContentPane().add(noOfClients);

        nrClients = new JTextField();
        nrClients.setBounds(120,80,50,20);
        nrClients.setFont(bigFont);
        getContentPane().add(nrClients);

        noOfQueues = new JLabel("No. queues: ");
        noOfQueues.setFont(bigFont);
        noOfQueues.setBounds(30,110, 100,20);
        getContentPane().add(noOfQueues);

        nrQueues = new JTextField();
        nrQueues.setBounds(120,110,50,20);
        nrQueues.setFont(bigFont);
        getContentPane().add(nrQueues);

        minArrivalTime = new JLabel("Min arrival time: ");
        minArrivalTime.setFont(bigFont);
        minArrivalTime.setBounds(30,140, 150,20);
        getContentPane().add(minArrivalTime);

        arrivalMin = new JTextField();
        arrivalMin.setBounds(160,140,35,20);
        arrivalMin.setFont(bigFont);
        getContentPane().add(arrivalMin);

        maxArrivalTime = new JLabel("Max arrival time: ");
        maxArrivalTime.setFont(bigFont);
        maxArrivalTime.setBounds(30,170, 150,20);
        getContentPane().add(maxArrivalTime);

        arrivalMax = new JTextField();
        arrivalMax.setBounds(160,170,35,20);
        arrivalMax.setFont(bigFont);
        getContentPane().add(arrivalMax);

        minServiceTime = new JLabel("Min service time: ");
        minServiceTime.setFont(bigFont);
        minServiceTime.setBounds(30,200, 150,20);
        getContentPane().add(minServiceTime);

        serviceMin = new JTextField();
        serviceMin.setBounds(160,200,35,20);
        serviceMin.setFont(bigFont);
        getContentPane().add(serviceMin);

        maxServiceTime = new JLabel("Max service time: ");
        maxServiceTime.setFont(bigFont);
        maxServiceTime.setBounds(30,230, 150,20);
        getContentPane().add(maxServiceTime);

        serviceMax = new JTextField();
        serviceMax.setBounds(160,230,35,20);
        serviceMax.setFont(bigFont);
        getContentPane().add(serviceMax);

        simulationTime = new JLabel("Simulation interval: ");
        simulationTime.setFont(bigFont);
        simulationTime.setBounds(30,260, 170,20);
        getContentPane().add(simulationTime);

        simTime = new JTextField();
        simTime.setBounds(170,260,35,20);
        simTime.setFont(bigFont);
        getContentPane().add(simTime);

        selectStrategy = new JLabel("Select strategy:");
        selectStrategy.setFont(bigBold);
        selectStrategy.setBounds(50,300,200,20);
        getContentPane().add(selectStrategy);

        shortestTime = new JButton("Shortest time");
        shortestTime.setFont(bigFont);
        shortestTime.setBounds(120,350,200, 50);
        getContentPane().add(shortestTime);

        shortestQueue = new JButton("Shortest queue");
        shortestQueue.setFont(bigFont);
        shortestQueue.setBounds(120,400,200, 50);
        getContentPane().add(shortestQueue);

        startSimulation = new JButton("Start");
        startSimulation.setFont(hugeFont);
        startSimulation.setBounds(300,500,120, 70);

        getContentPane().add(startSimulation);

        currentTime = new JLabel("Current time:");
        currentTime.setFont(bigFont);
        currentTime.setBounds(370,350,200,20);
        getContentPane().add(currentTime);

        currTime = new JTextField();
        currTime.setEditable(false);
        currTime.setFont(bigFont);
        currTime.setBounds(550,350,50,20);
        getContentPane().add(currTime);

        pHour = new JLabel("Peak hour:");
        pHour.setFont(bigFont);
        pHour.setBounds(370,370,200,20);
        getContentPane().add(pHour);

        peakHour = new JTextField();
        peakHour.setEditable(false);
        peakHour.setFont(bigFont);
        peakHour.setBounds(550,370,50,20);
        getContentPane().add(peakHour);

        avgServiceTime = new JLabel("Average service time:");
        avgServiceTime.setFont(bigFont);
        avgServiceTime.setBounds(370,390,200,20);
        getContentPane().add(avgServiceTime);

        averageServiceTime = new JTextField();
        averageServiceTime .setEditable(false);
        averageServiceTime .setFont(bigFont);
        averageServiceTime .setBounds(550,390,50,20);
        getContentPane().add(averageServiceTime );

        avgWaiting = new JLabel("Average waiting time:");
        avgWaiting.setFont(bigFont);
        avgWaiting.setBounds(370,410,200,20);
        getContentPane().add(avgWaiting);

        averageWaiting = new JTextField();
        averageWaiting .setEditable(false);
        averageWaiting .setFont(bigFont);
        averageWaiting .setBounds(550,410,50,20);
        getContentPane().add(averageWaiting );

        logScrollPane = new JScrollPane();
        logScrollPane.setBounds(215, 90, 500, 200);
        logsField = new JTextArea();
        logsField.setEditable(false);
        logsField.setFont(bigFont);
        logScrollPane.setViewportView(logsField);
        getContentPane().add(logScrollPane);

        logsField.setText("");

        logsLabel = new JLabel("Logs : ");
        logsLabel.setFont(bigFont);
        logsLabel.setBounds(215, 60, 100, 30);
        getContentPane().add(logsLabel);

    }
    public void setCurrentTime(String time){
        currTime.setText(time);
    }
    public void setPeakHour(String hour){
        peakHour.setText(hour);
    }
    public void setAverageServiceTime(String hour){
        averageServiceTime.setText(hour);
    }
    public void setAverageSWaitingTime(String hour){
        averageWaiting.setText(hour);
    }
    public void setLogs(String text){
        logsField.append(text+"\n");
    }
    public String getNoOfClients(){
        return nrClients.getText();
    }
    public String getNoOfQueues(){
        return nrQueues.getText();
    }
    public String getMinArrivalTime(){
        return arrivalMin.getText();
    }
    public String getMaxArrivalTime(){
        return arrivalMax.getText();
    }
    public String getMinServiceTime(){
        return serviceMin.getText();
    }
    public String getMaxServiceTime(){
        return serviceMax.getText();
    }
    public String getSimulationTime(){
        return simTime.getText();
    }
    public void addStartButtonActionListener(final ActionListener actionListener){
        startSimulation.addActionListener(actionListener);
    }
    public void addShortestTimeButtonActionListener(final ActionListener actionListener){
        shortestTime.addActionListener(actionListener);
    }
    public void addShortestQueueButtonActionListener(final ActionListener actionListener){
        shortestQueue.addActionListener(actionListener);
    }
}
