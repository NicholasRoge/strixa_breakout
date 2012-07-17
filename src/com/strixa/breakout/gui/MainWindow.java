/**
 * File:  MainWindow.java
 * Date of Creation:  Jul 16, 2012
 */
package com.strixa.breakout.gui;

import com.strixa.breakout.gui.panel.GamePanel;
import com.strixa.gui.StrixaWindow;

/**
 * TODO:  Write Class Description
 *
 * @author Nicholas Rogé
 */
public class MainWindow extends StrixaWindow{
    private class DisplayGameAction implements WindowAction{
        @Override public boolean performAction(Object data){
            MainWindow.this.setActivePanel(new GamePanel(MainWindow.this));  //GamePanel is where all the action happens
            
            return true;
        }
    }
    
    public static class Actions{
        public static final int DISPLAY_GAME = 101;
    }
    
    /*Begin Constructors*/
    public MainWindow(){
        this.registerWindowAction(Actions.DISPLAY_GAME,new DisplayGameAction());
        
        this.setWindowFullscreen(true);
        
        this.performAction(Actions.DISPLAY_GAME);
    }
    /*End Constructors*/
}
