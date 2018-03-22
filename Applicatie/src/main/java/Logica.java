/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

/**
 *
 * @author van Selst
 */
public class Logica {

    static void readFile(String bestand) {
        try {
            BufferedReader infile = new BufferedReader(new FileReader(bestand));
            String line;
            while ((line = infile.readLine()) != null) {
                String[] splitline = line.split("\n", -1);
                //Sequentie seqObject = new Sequentie(splitline[1]);
            }
        } catch (IOException | NumberFormatException | ArrayIndexOutOfBoundsException exc) {
            System.out.println("Het gekozen bestand kan niet gelezen worden");
        } catch (Exception exc) {
            System.out.println("Er is een onbekende fout opgetreden");
        }
    }
    
    static void BLAST(){
        
    }


    
}
