package ro.tuc.tp.Controller;
import java.lang.*;
import java.util.*;

public class Fisier {
    private Formatter x;

    public void openFile(){
        try{
            x = new Formatter("Rezultat.txt");

        }catch(Exception e){
            System.out.println("Error in file");
        }
    }
    public void addText(String s){
        x.format("%s\n",s);
    }
    public void closeFile(){
        x.close();
    }
}
