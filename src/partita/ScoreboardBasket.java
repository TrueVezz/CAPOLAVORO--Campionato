/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partita;

/**
 *
 * @author Patrick
 */
public class ScoreboardBasket extends Scoreboard{

    public ScoreboardBasket(Squadra homeTeam, Squadra awayTeam) {
        super(homeTeam, awayTeam);
    }
    
    @Override
    public void vittoria(Squadra team){
        team.setPunteggio(team.getPunteggio() + 2);
    }
    
    @Override
    public void terminaPartita() {
        if(this.getHomeCounter().getCounter() > this.getAwayCounter().getCounter()){
            this.vittoria(homeTeam);
        }
        else
            this.vittoria(awayTeam);
    }

}
