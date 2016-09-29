/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import GUIS.MainGui;
import GUIS.BoardControllerGui;

/**
 *
 * @author frederik.larsen
 */
public class Runner {

    public static void main(String[] args) {
        MainController MC = new MainController();
        new MainGui(MC).setVisible(true);
        
    }
}
