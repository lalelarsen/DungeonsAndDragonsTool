/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 *
 * @author frederik.larsen
 */
public class BCharacter implements Comparable{
    String name;
    int iniativ;

    public BCharacter(String name, int iniativ) {
        this.name = name;
        this.iniativ = iniativ;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIniativ() {
        return iniativ;
    }

    public void setIniativ(int iniativ) {
        this.iniativ = iniativ;
    }

    @Override
    public String toString() {
        return iniativ + "/" + name;
    }

    @Override
    public int compareTo(Object o) {
        BCharacter BC = (BCharacter)o;
        return BC.iniativ - this.iniativ;
    }
    
    
    
}
