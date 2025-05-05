/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package partita;

/**
 *
 * @author Patrick
 */
public class Contatore {

    private int counter = 0;

    public void increaseContatore(int num) {
        counter += num;
    }

    public boolean decreaseContatore(int num) {
        if (this.counter - num > 0) {
            this.counter -= num;
            return true;
        } else {
            return false;
        }
    }

    public int getCounter() {
        return this.counter;
    }

    public void resetCounter() {
        this.counter = 0;
    }
}
