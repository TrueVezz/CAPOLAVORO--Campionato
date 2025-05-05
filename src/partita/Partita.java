/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package partita;

import java.util.Scanner;

/**
 *
 * @author Patrick
 */
public class Partita {

    public static void main(String[] args) {
        System.out.println("Benvenuto nel campionato!!\n\n");
        Scanner input = new Scanner(System.in);
        Campionato c = Campionato.caricaDaFile("campionato.ser");
        
        if(c == null) {
            System.out.println("creazione di un nuovo campionato");
            c = new Campionato();
        }
        
////        Campionato c = new Campionato();

        int scelta1 = 0;
        String nome, squadraHome, squadraAway;
        
//        c.importaCampionato("campionato.csv");
        
        while (scelta1 != 8) {
            System.out.println(printMenu());
            scelta1 = input.nextInt();
            input.nextLine();

            switch (scelta1) {
                case 1 -> c.aggiungiSquadra(createTeam(input));
               
                case 2 -> {
                    System.out.println("inserire il nome della squadra da rimuovere");
                    nome = input.nextLine();
                    if (c.rimuoviSquadra(nome) == -1) {
                        System.out.println("squadra non trovata");
                    } else {
                        System.out.println("squadra rimossa");
                    }
                }
                case 3 -> {
                    System.out.println("inserire il nome della squadra da cercare");
                    nome = input.nextLine();
                    if (c.cercaSquadraConNome(nome) == null) {
                        System.out.println("squadra non trovata");
                    } else {
                        System.out.println("trovata squadra " + c.cercaSquadraConNome(nome).getName());
                    }
                }
                case 4 -> System.out.println(c.stampaSquadre());
                
                case 5 -> {
                    //parte interfaccia grafica
                    avviaPartitaCalcio(input, c);
                    System.out.println(printMenu()); //NON so perchè dopo aver eseguito la funzione non stampa nemmeno questo menu
                }
                
                case 6 -> System.out.println(c.StampaClassifica());
                
                case 7 -> {
                    System.out.println("Classifica stampata sul file");
                    c.stampaClassificaFile();
                }
                case 8 -> System.out.println("uscita ...");
                
                default -> System.out.println("Comando non valido");
            }
        }
        
//        c.salvaCampionato("campionato.csv");

        c.salvaSuFile("campionato.ser");

    }

    public static String printMenu() {
        return """
               1) aggiungi squadra
               2) rimuovi squadra
               3) cerca squadra
               4) stampa elenco squadre 
               5) avvia partita
               6) Stampa classifica
               7) Stampa classifica nel file
               8) esci
               """;
    }

    public static String printMenuPartitaCalcio() {
        return """
               1) gol squadra 1
               2) gol squadra 2
               3) stampa punteggi
               4) annulla gol squadra 1
               5) annulla gol squadra 2
               6) azzera i punteggi
               7) termina la partita
               """;
    }

    public static String printMenuPartitaBasket() {
        return """
               1)	Canestro 1 punto squadra 1
               2)	Canestro 2 punti squadra 1
               3)	Canestro 3 punti squadra 1
               4)	Canestro 1 punto squadra 2
               5)	Canestro 2 punti squadra 2
               6)	Canestro 3 punti squadra 2
               7)	stampa punteggi
               8)	Togli canestro 1 punto squadra 1
               9)	Togli canestro 2 punti squadra 1
               10)	Togli canestro 3 punti squadra 1
               11)	Togli canestro 1 punto squadra 2
               12)	Togli canestro 2 punti squadra 2
               13)	Togli canestro 3 punti squadra 2
               14)	azzera i punteggi
               15)	termina la partita
               """;
    }
    
    public static Squadra createTeam(Scanner input) {
        System.out.println("Nome squadra: ");
        String name = input.nextLine();
        System.out.println("Sede squadra: ");
        String location = input.nextLine();
        System.out.println("Colori squadra: ");
        String colors = input.nextLine();

        return new Squadra(name, location, colors);
    }
    
    public static void avviaPartitaCalcio(Scanner input, Campionato c) {
        System.out.println("nome squadra di casa: ");
        String squadraHome = input.nextLine();
        System.out.println("nome squadra ospite: ");
        String squadraAway = input.nextLine();
        
        Squadra homeTeam = c.cercaSquadraConNome(squadraHome);
        Squadra awayTeam = c.cercaSquadraConNome(squadraAway);

        if (homeTeam == null || awayTeam == null) {
            System.out.println("Una o entrambe le squadre non esistono.");
            return;
        }
        
        Scoreboard s = new Scoreboard(homeTeam, awayTeam);
       // MyFrm myfrm = new MyFrm(s, squadraHome, squadraAway);
        ChatFrm frm = new ChatFrm(s, squadraHome, squadraAway);

//        int scelta2 = 0;
//        
//        while (scelta2 != 7) {
//            System.out.println(printMenuPartitaCalcio());
//            scelta2 = input.nextInt();
//            input.nextLine();
//            
//            switch (scelta2) {
//                case 1 -> s.getHomeCounter().increaseContatore(1);
//                case 2 -> s.getAwayCounter().increaseContatore(1);
//                case 3 -> System.out.println(s.printScoreboard());
//                case 4 -> s.getHomeCounter().decreaseContatore(1);
//                case 5 -> s.getAwayCounter().decreaseContatore(1);
//                case 6 -> {
//                    s.getAwayCounter().resetCounter();
//                    s.getHomeCounter().resetCounter();
//                }
//            }
//        }
//        s.terminaPartita();
    }
    
    
    public static void avviaPartitaBasket(Scanner input, Campionato c) {
        System.out.println("nome squadra di casa: ");
        String squadraHome = input.nextLine();
        System.out.println("nome squadra ospite: ");
        String squadraAway = input.nextLine();
        
        Squadra homeTeam = c.cercaSquadraConNome(squadraHome);
        Squadra awayTeam = c.cercaSquadraConNome(squadraAway);

        if (homeTeam == null || awayTeam == null) {
            System.out.println("Una o entrambe le squadre non esistono.");
            return;
        }
        
        Scoreboard s = new ScoreboardBasket(homeTeam, awayTeam);
        int scelta2 = 0;
        
        while (scelta2 != 15) {
            System.out.println(printMenuPartitaBasket());
            scelta2 = input.nextInt();
            input.nextLine();
            
            switch (scelta2) {
                case 1 -> s.getHomeCounter().increaseContatore(1);
                case 2 -> s.getHomeCounter().increaseContatore(2);
                case 3 -> s.getHomeCounter().increaseContatore(3);
                case 4 -> s.getAwayCounter().increaseContatore(1);
                case 5 -> s.getAwayCounter().increaseContatore(2);
                case 6 -> s.getAwayCounter().increaseContatore(3);
                case 7 -> System.out.println(s.printScoreboard());
                case 8 -> s.getHomeCounter().decreaseContatore(1);
                case 9 -> s.getHomeCounter().decreaseContatore(2);
                case 10 -> s.getHomeCounter().decreaseContatore(3);
                case 11 -> s.getAwayCounter().decreaseContatore(1);
                case 12 -> s.getAwayCounter().decreaseContatore(2);
                case 13 -> s.getAwayCounter().decreaseContatore(3);
                case 14 -> {
                    s.getAwayCounter().resetCounter();
                    s.getHomeCounter().resetCounter();
                }
            }
        }
        s.terminaPartita();
    }
}
