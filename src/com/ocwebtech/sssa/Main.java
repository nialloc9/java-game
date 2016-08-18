package com.ocwebtech.sssa;

import java.awt.Menu;
import org.newdawn.slick.*;
import org.lwjgl.*;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Main extends StateBasedGame{
    
    public static String gamename = "Super Simple Super Awesome";
    
    //reference numbers for state
    public static final int menu = 0;
    public static final int play = 1;

    //MAIN METHOD
    public static void main(String[] args) {
        AppGameContainer appgc;
        
        try{
            appgc = new AppGameContainer(new Main(gamename));
            //set window size
            appgc.setDisplayMode(640, 360, false);
            appgc.start();
        }catch(SlickException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void initStatesList(GameContainer gc) throws SlickException {
        //initialize the states
        this.getState(menu).init(gc, this);
        this.getState(play).init(gc, this);
        
        //enter menu state when game loads
        this.enterState(menu);
    }

    public Main(String gamename) {
        super(gamename);
        
        //add states
        this.addState(new com.ocwebtech.sssa.Menu(menu));
        this.addState(new com.ocwebtech.sssa.Play(play)); 
    }

    
    
    
}
