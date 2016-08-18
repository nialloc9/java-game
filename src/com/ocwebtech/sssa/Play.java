package com.ocwebtech.sssa;


//functionality to track location of the mouse
import org.lwjgl.input.Mouse;

//slick
import org.newdawn.slick.state.*;
import org.newdawn.slick.*;

public class Play extends BasicGameState{
    
    Animation guy, movingUp, movingDown, movingLeft, movingRight;
    Image map;
    
    //music
    Music music;
    
    boolean quit = false;
    boolean winner = false;
    
    int[] duration = {200, 200};
    
    //guy start position
    float guyPositionX = 0;
    float guyPositionY = 0;
    
    //move(shift) the map so that when guy moves.
    float shiftX = guyPositionX + 320;
    float shiftY = guyPositionY + 160;
    
    //CONSTRUCTOR
    public Play(int state){
        
    }
    
    //GET ID METHOD.. gets the id of the state
    @Override
    public int getID() {
        return 1;
    }
    
    //UPDATE METHOD.. updates the images on the state
    @Override
    public void update(GameContainer gc, StateBasedGame sbg, int i) throws SlickException {
        Input input = gc.getInput(); //get user input
        
        //if user pushes up
        if(input.isKeyDown(Input.KEY_UP)){
            guy = movingUp;
            guyPositionY += i * .1f; //moves the map. i is the number of milliseconds between calls to the update method.
            
            //collision detection
            if(guyPositionY>79){
                guyPositionY -= i * .1f; //stop guy.
            }
            if((guyPositionX < -919) && (guyPositionY < -22) && guyPositionY > -78){
                winner = true;
            }
            
            else{
                winner = false;
                quit = false;
            }
        }
        
        //if user pushes down
        if(input.isKeyDown(Input.KEY_DOWN)){
            guy = movingDown;
            guyPositionY -= i * .1f; 
            
            //collision detection
            if(guyPositionY<-447){
                guyPositionY += i * .1f; //stop guy.
            }
            if((guyPositionX < -919) && (guyPositionY < -22) && guyPositionY > -78){
                winner = true;
            }
            
            else{
                winner = false;
                quit = false;
            }
        }
        
        //if user pushes left
        if(input.isKeyDown(Input.KEY_LEFT)){
            guy = movingLeft;
            guyPositionX += i * .1f;
            
            //collision detection
            if(guyPositionX>243){
                guyPositionX -= i * .1f; //stop guy
            }
            
            if((guyPositionX < -919) && (guyPositionY < -22) && guyPositionY > -78){
                winner = true;
            }
            
            else{
                winner = false;
                quit = false;
            }
        }
        
        //if user pushes right
        if(input.isKeyDown(Input.KEY_RIGHT)){
            guy = movingRight;
            guyPositionX -= i * .1f;
            
            //collision detection
            if(guyPositionX<-920){
                guyPositionX += i * .1f; //stop guy
            }
            if((guyPositionX < -919) && (guyPositionY < -22) && guyPositionY > -78){
                winner = true;
            }
            
            else{
                winner = false;
                quit = false;
            }
        }
        
        //if user presses escape
        if(input.isKeyDown(Input.KEY_ESCAPE)){
            quit = true;
        }
        
        //if the options are on the screen
        if(quit == true){
            
            //if user presses R
            if(input.isKeyDown(Input.KEY_R)){
                quit = false;
            }
            
            //if user presses M
            if(input.isKeyDown(Input.KEY_M)){
                sbg.enterState(0); //go to main menu
            }
            
            //if user presses Q
            if(input.isKeyDown(Input.KEY_Q)){
                System.exit(0); //exit with no errors
            }
        }
    }
    
    //RENDER METHOD.. renders stuff to the screen
    @Override
    public void render(GameContainer gc, StateBasedGame sbg, Graphics grphcs) throws SlickException {
        map.draw(guyPositionX, guyPositionY);
        guy.draw(shiftX, shiftY);
        grphcs.drawString("Guy's X: "+guyPositionX + " Guy's Y: " + guyPositionY, 10, 50);
        
        //if quit is true draw options to screen
        if(quit == true){
            grphcs.drawString("Resume (R)", 250, 100);
            grphcs.drawString("Main Menu (M)", 250, 150);
            grphcs.drawString("Quit Game (Q)", 250, 200);
            
            //id quit is false clear the options from the screen
            if(quit == false){
                grphcs.clear();
            }
        }
        
        //if user finds the hidden door
        if(winner == true){
            grphcs.drawString("Found it! I win!", shiftX +50, shiftY - 20);
            quit = true;
            
            if(winner == false){
                quit = false;
            }
        }
        
    }
    
    //INIT METHOD.. initializer
    @Override
    public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
        map = new Image("res/background.png");
        
        //image arrays for animation. start image and end image.
        Image[] walkUp = {new Image("res/guyBack.png"), new Image("res/guyBack.png")};
        Image[] walkDown = {new Image("res/guyFront.png"), new Image("res/guyFront.png")};
        Image[] walkLeft = {new Image("res/guyLeft.png"), new Image("res/guyRight.png")};
        Image[] walkRight = {new Image("res/guyRight.png"), new Image("res/guyRight.png")};
        
        //create animations
        movingUp = new Animation(walkUp, duration, false); 
        movingDown = new Animation(walkDown, duration, false);
        movingLeft = new Animation(walkLeft, duration, false);
        movingRight = new Animation(walkRight, duration, false);
        
        //assign guy to default animation when game starts
        guy = movingDown;
    }
    
}
