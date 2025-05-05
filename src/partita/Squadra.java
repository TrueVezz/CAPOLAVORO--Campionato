/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partita;

import java.io.Serializable;

/**
 *
 * @author Patrick
 */
public class Squadra implements Serializable {
    private String name;
    private String location;
    private String color;
    private int punteggio = 0;
    
    public Squadra(String name, String location, String color) {
        this.name = name;
        this.location = location;
        this.color = color;
    }
    
    public Squadra(String name, String location, String color, int punteggio) {
        this.name = name;
        this.location = location;
        this.color = color;
        this.punteggio = punteggio;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public String getColor() {
        return color;
    }

    public void setPunteggio(int punteggio) {
        this.punteggio = punteggio;
    }

    public int getPunteggio() {
        return punteggio;
    }
    
    
    
    @Override
    public String toString() {
        return name + ", " + location + ", " + color + ", punteggio=" + punteggio;
    }
    
    public String toStringFile() {
        return name + ";" + location + ";" + color + ";" + punteggio + ";\n";
    }
    
}
