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
    
    /**
     * Actions this window can perform.
     *
     * @author Nicholas Rogé
     */
    public static class Actions{
        /**
         * Opens the game screen.
         */
        public static final int DISPLAY_GAME = 101;
    }
    
    /** Field required for object serialization. */
    private static final long serialVersionUID = -311561610124875501L;
    
    
    /*Begin Constructors*/
    /**
     * Sets up the window.
     */
    public MainWindow(){
        this.registerWindowAction(Actions.DISPLAY_GAME,new DisplayGameAction());
        
        this.setWindowFullscreen(true);
        
        this.performAction(Actions.DISPLAY_GAME);
    }
    /*End Constructors*/
}
