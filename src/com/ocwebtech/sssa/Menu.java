package com.ocwebtech.sssa;

//functionality to track location of the mouse
import org.lwjgl.input.Mouse;

//slick
import org.newdawn.slick.state.*;
import org.newdawn.slick.*;

public class Menu extends BasicGameState{ //make class a state

    //Declare variables
    Image playNow;
    Image exitGame;
    String instructions;
    Music music;
    
    //CONSTRUCTOR
    public Menu(int state){
        
    }
    
    //GET ID METHOD.. returns the id of the state
    @Override
    public int getID() {
        return 0; //The menu state id is 0. Play is 1.
    }

    //UPDATE METHOD.. updates the images shown on the screen
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput(); //input from the user
        
        //position of the mouse
        int xpos = Mouse.getX();
        int ypos = Mouse.getY();
        
        //Play button
        if((xpos > 100 && xpos < 261) && (ypos > 185 && ypos < 260)){
            if(input.isMouseButtonDown(0)){ //if left mouse button is clicked.
                sbg.enterState(1); //enter state with id of 1(play state).
            }
        }
        
        //Exit button
        if(( xpos > 116 && xpos < 215) && (ypos > 107 && ypos < 155)){
            if(input.isMouseButtonDown(0)){
                System.exit(0); //exit with no errors
            }
        }
    }

    //RENDER METHOD.. draws stuff on the screen
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        playNow.draw(100, 100);
        exitGame.draw(100, 200);
        grphcs.drawString(instructions, 100, 50);
    }

    //INIT METHOD.. initializer
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        playNow = new Image("res/startButton.png");
        exitGame = new Image("res/quitButton.png");
        instructions = "Instructions: Find the hidden door in the wall.";
        
        //Add Music
        music = new Music("res/mainSound.wav");
        music.loop();
    }
    
    
}
