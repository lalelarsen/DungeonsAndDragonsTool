/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUIS.BoardControllerGui;
import GUIS.BoardPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javax.imageio.ImageIO;

/**
 *
 * @author frederik.larsen
 */
public class MainController extends Observable {

    int columns;

    public void OpenBoard(String path, String Rows, String Columns) {
        BoardController BC = new BoardController();
        new BoardControllerGui(BC).setVisible(true);
        File img = new File(path);
        BufferedImage BGPic = null;
        //Get Image from path
        try {
            BGPic = ImageIO.read(img);
        } catch (IOException e) {
            System.out.println("The image was not loaded.");
            BGPic = new BufferedImage(800, 600, BufferedImage.TYPE_INT_RGB);
        }

        if (Rows.equals("") || Columns.equals("")) {
            BC.columns = 25;
            BoardPanel BP = new BoardPanel(BGPic.getWidth(), BGPic.getHeight(), BGPic, BC);
            BC.addObserver(BP);
        } else {
            try {
                int iRows = Integer.parseInt(Rows);
                int iColumns = Integer.parseInt(Columns);
                BC.columns = iColumns;
                BoardPanel BP = new BoardPanel(BGPic.getWidth(), BGPic.getHeight(), BGPic, iRows, iColumns, BC);
                BC.addObserver(BP);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

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

}
