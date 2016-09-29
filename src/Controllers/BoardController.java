/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Objects.BCharacter;
import Objects.Cell;
import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;

/**
 *
 * @author frederik.larsen
 */
public class BoardController extends Observable {

    public List<Point> ClickedCells = new ArrayList<Point>();
    public ArrayList<ArrayList<Cell>> AllUnits = new ArrayList<ArrayList<Cell>>();
    public ArrayList<Cell> Units = new ArrayList<Cell>();
    public ArrayList<Cell> InvisUnits = new ArrayList<Cell>();
    public ArrayList<BCharacter> Characters = new ArrayList<BCharacter>();

    int columns;
    public Color CurrUnitColor = Color.GREEN;

    public BoardController() {
        AllUnits.add(Units);
        AllUnits.add(InvisUnits);

    }

    public void RemoveUnitByIndex(int i) {
        for (int j = 0; j < AllUnits.size(); j++) {
            ArrayList<Cell> currList = AllUnits.get(j);
            for (int k = 0; k < currList.size(); k++) {
                Cell curr = currList.get(k);
                if (curr.getPlace() == i) {
                    currList.remove(curr);
                }
            }
        }
    }

    public Cell FindUnitByIndex(int i) {
        for (int j = 0; j < AllUnits.size(); j++) {
            ArrayList<Cell> currList = AllUnits.get(j);
            for (int k = 0; k < currList.size(); k++) {
                Cell curr = currList.get(k);
                if (curr.getPlace() == i) {
                    return curr;
                }
            }
        }
        return null;
    }

    public void addCharacter(String name, int intiativ) {
        BCharacter c = new BCharacter(name, intiativ);
        for (int i = 0; i < Characters.size(); i++) {
            BCharacter curr = Characters.get(i);
            if (curr.getName().equals(c.getName())) {
                curr.setIniativ(intiativ);
                Collections.sort(Characters);
                updateList();
                return;
            }
        }
        Characters.add(c);
        Collections.sort(Characters);
        updateList();
    }
    
    public boolean containsCharacterByName(String name){
        for (int i = 0; i < Characters.size() ; i++) {
            if(Characters.get(i).getName().equals(name)){
                return true;
            }
        }
        return false;
    }

    public void SetRangeRing(String text) {

        int x = Integer.parseInt(text) + 1;
        int y = 0;
        int err = 0;

        ArrayList<Integer> list = new ArrayList<Integer>();

        while (x >= y) {
            list.add(x + this.columns * y);
            list.add(y + this.columns * x);
            list.add(-x + this.columns * y);
            list.add(-y + this.columns * x);
            list.add(-x - this.columns * y);
            list.add(-y - this.columns * x);
            list.add(x - this.columns * y);
            list.add(y - this.columns * x);
//            System.out.print("(" + (x0 + y) + "," + ( y0 + x) + ")");
//            System.out.print("(" + (x0 - x) + "," + ( y0 + y) + ")");
//            System.out.print("(" + (x0 - y) + "," + ( y0 + x) + ")");
//            System.out.print("(" + (x0 - x) + "," + ( y0 - y) + ")");
//            System.out.print("(" + (x0 - y) + "," + ( y0 - x) + ")");
//            System.out.print("(" + (x0 + x) + "," + ( y0 - y) + ")");
//            System.out.print("(" + (x0 + y) + "," + ( y0 - x) + ")");
            y += 1;
            err += 1 + 2 * y;
            if (2 * (err - x) + 1 > 0) {
                x -= 1;
                err += 1 - 2 * x;
            }
            y++;
        }
        String str = "Ring";
        Object[] obj = {str, list};
        setChanged();
        notifyObservers(obj);
    }

    public void reset() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        String str = "";
        Object[] obj = {str, list};
        list.add(0);
        setChanged();
        notifyObservers(obj);
    }

    public void changeCurrUnitColor() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        String str = "Color Change";
        Object[] obj = {str, list};
        setChanged();
        notifyObservers(obj);
    }

    public void showUnitsCheck() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        String str = "Units Check";
        Object[] obj = {str, list};
        setChanged();
        notifyObservers(obj);
    }

    public void showInvisUnitsCheck() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        String str = "Invis Units Check";
        Object[] obj = {str, list};
        setChanged();
        notifyObservers(obj);
    }

    public void setInvisUnitsCheck() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        String str = "Set Invis Units Check";
        Object[] obj = {str, list};
        setChanged();
        notifyObservers(obj);
    }

    public void closeWindows() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        String str = "Close";
        Object[] obj = {str, list};
        setChanged();
        notifyObservers(obj);
    }

    public void updateList() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < Characters.size(); i++) {
            list.add(Characters.get(i).toString());
        }
        String str = "Update Character List";
        Object[] obj = {str, list};
        setChanged();
        notifyObservers(obj);
    }

}
