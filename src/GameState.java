//TODO:Is this the right way to structure it?
//And put everything togethre in the Game class
//TODO: Or create a seperate class for each actual panel
public class GameState {

    private double fuel;
    private boolean crashed;
    //true if player is playing as simsup
    private boolean simSup;
    private double timeLeft;
    
    //TODO:weird to do it like this, but kind of good because then my display panels get all data from her erather than from
    //the canvas..
    
    private float altitude;
    private float Vx;
    private float Vy;
    private float Vw;
    
    GameState(){
        //TODO:Set to initial values

    }
    
    //Encapsualted ok because its a primitive
    public double getFuel() {
        return fuel;
    }

    public float getVx() {
        // TODO Auto-generated method stub
        return Vx;
    }
    
    public float getVy() {
        // TODO Auto-generated method stub
        return Vy;
    }
    
    public float getVw() {
        // TODO Auto-generated method stub
        return Vw;
    }

    public void setVx(float Vx) {
        // TODO Auto-generated method stub
        this.Vx = Vx;
        
    }
    public void setVy(float Vy) {
        // TODO Auto-generated method stub
        this.Vy = Vy;
        
    }

    public void setVw(float Vw) {
        // TODO Auto-generated method stub
        this.Vw = Vw;
    }
    
    
    
    
}
