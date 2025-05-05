/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partita;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;

/**
 *
 * @author Patrick
 */
public class Campionato implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private ArrayList <Squadra> squadre;
    
    public Campionato() {
        this.squadre = new ArrayList<>();
    }

    public void aggiungiSquadra(Squadra squadra){
        squadre.add(squadra);
    }
    
    public Squadra cercaSquadraConNome(String nome) {
       for(Squadra s : squadre){
           if(s.getName().equals(nome)) return s;
       }
       return null;
    }
    
    public int rimuoviSquadra(String nome) {
        if(cercaSquadraConNome(nome) == null) return -1; 
        
        squadre.remove(cercaSquadraConNome(nome));
        return 0;
    }
    
    public String stampaSquadre() {
        String stringa = "";
        for(Squadra s : squadre) {
            stringa += s.toString() + "\n";
        }
        
        return stringa;
    }
    
    private void ordinaPunteggi() {
        this.squadre.sort(Comparator.comparingInt(Squadra::getPunteggio).reversed());
    }  
    
    public String StampaClassifica() {
        ordinaPunteggi();
        String stringa = "";
        int i = 1;
        for(Squadra s : squadre) {
            stringa += i + ") " + s.getName() + " " + s.getPunteggio() + " pt\n";
            i++;
        }
        return stringa;
    }
    
    public void stampaClassificaFile() {
        try {
            FileWriter writer = new FileWriter("classifica.txt");
            ordinaPunteggi();
            
            int posizione = 1;
            for (Squadra squadra : squadre) {
                writer.write(posizione + ") " + squadra.getName()
                        + " - Punti: " + squadra.getPunteggio() + "\n");
                posizione++;
            }

            writer.close();

        } catch (IOException e) {
            System.out.println("Errore durante la scrittura del file: " + e.getMessage());
        }
    }
    
    public void salvaCampionato(String fileName) {
        try {
            FileWriter scrittore = new FileWriter(fileName);
            ordinaPunteggi();
           
            for(Squadra s : squadre){
                scrittore.write(s.toStringFile());
            }
            
            scrittore.close();
        } catch (IOException e) {
            System.out.println("Errore durante la scrittura del file: " + e.getMessage());
        }
    }
    
    public void importaCampionato(String fileName) {
        try{
            BufferedReader lettore = new BufferedReader(new FileReader(fileName));
            
            String line;
            
            while ((line = lettore.readLine()) != null) {
                String[] campi = line.split(";");

                // Verifica che ci siano abbastanza campi
                if (campi.length >= 4) {
                    String name = campi[0];
                    String location = campi[1];
                    String color = campi[2];
                    int punteggio = Integer.parseInt(campi[3]);

                    squadre.add(new Squadra(name, location, color, punteggio));
                }
        }  
            
        } catch (IOException e) {
            System.out.println("Errore durante la lettura del file: " + e.getMessage());
        }
    }
    
    public void salvaSuFile(String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(this); // Scrive l'intero oggetto Campionato
            System.out.println("Campionato salvato correttamente nel file: " + fileName);
        } catch (IOException e) {
            System.out.println("Errore durante il salvataggio: " + e.getMessage());
        }
    }


    public static Campionato caricaDaFile(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            return (Campionato) in.readObject(); // Legge l'oggetto Campionato
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Errore durante il caricamento: " + e.getMessage());
            return null;
        }
    }

}
