/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partita;

/**
 *
 * @author Patrick
 */
public class Scoreboard {
    Squadra homeTeam;
    Squadra awayTeam;
    private Contatore homeCounter;
    private Contatore awayCounter;

    public Scoreboard(Squadra homeTeam, Squadra awayTeam) {
        this.homeCounter = new Contatore();
        this.awayCounter = new Contatore();
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }
    
    public String printScoreboard() {
        return homeTeam.getName() + " vs " + awayTeam.getName() + "\nPunteggio: " + homeCounter.getCounter() + " - " + awayCounter.getCounter();
    }

    public Contatore getHomeCounter() {
        return homeCounter;
    }
    
    public Contatore getAwayCounter() {
        return awayCounter;
    }
    
    public void vittoria(Squadra team){
        team.setPunteggio(team.getPunteggio() + 3);
    }
    
    public void pareggio(Squadra team){
        team.setPunteggio(team.getPunteggio() + 1);
    }
    
    public void azzeraPunteggio(Squadra team){
        team.setPunteggio(0);
    }

    public int getPunteggio(Squadra team) {
        return team.getPunteggio();
    }

       
    public void terminaPartita() {
        
        if (this.homeCounter.getCounter() == this.awayCounter.getCounter()) {
            this.pareggio(homeTeam);
            this.pareggio(awayTeam);
            System.out.println("partita finita in pareggio \n");
        } else if (this.homeCounter.getCounter() > this.awayCounter.getCounter()) {
            System.out.println("ha vinto la squadra: " + this.homeTeam.getName() + "\n");
            this.vittoria(homeTeam);
        } else {
            System.out.println("ha vinto la squadra: " + this.awayTeam.getName() + "\n");
            this.vittoria(awayTeam);
        }
    }
}
