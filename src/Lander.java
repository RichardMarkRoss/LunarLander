import org.jbox2d.common.Vec2;

public class Lander {

//TODO:Add javadocs

    //acceleration due to gravity
    //TODO: put constants all in 1 place and make public, because its eally shared by everything.?
    //Dont want to accidently end up with 2 g's
    private static final int g = 10;
    
    //time between each interval
    private static final int TIME = 0;
    private static final int MAX_THROTTLE = 100;
    private static final int MIN_THROTTLE = 0;
    
    private static final int INITIAL_Vx = 0;
    //TODO: should be how fast it's originally coming down, will need to increase
    private static final int INITIAL_Vy = 0;
    private final int INITIAL_Vw = 0;
    
    private static final int INITIAL_Ax = 0;
    private static final int INITIAL_Ay = g;
    private static final int INITIAL_Aw = 0;
    private static final int MAX_Ax = 100;
    private static final int t =0;
    private Vec2 v = new Vec2(1,2);
    //TODO: I purposesly chose to ignore style so x looks like subscript
    
    
    private int Px;
    private int Py;
    private int Pw;
    
    private int Vx;
    private int Vy;
    private int Vw;
    
    private int Ax;
    private int Ay;
    private int Aw;
    
   
            
    //TODO:lets you create lander at specific position
    public Lander(int px, int py, int pw) {
        this.Px = px;
        this.Py = py;
        this.Pw = pw;
        
        
        this.Vx = INITIAL_Vx;
        this.Vy = INITIAL_Vy;
        this.Vw = INITIAL_Vw;

        this.Ax = INITIAL_Ax;
        this.Ay = INITIAL_Ay;
        this.Aw = INITIAL_Aw;
        
    }
    
    //TODO:how to update velocity
    
//TODO:will I actually use all of these? Double check as game goes on.
    public int getPx() {
        return Px;
    }
    public int getPy() {
        return Py;
    }    
    public int getPw() {
        return Pw;
    }
    
    public void setPx(int px) {
        this.Px = px;
    }
    public void setPy(int py) {
        this.Py = py;
    }
    public void setPw(int pw) {
        this.Pw = pw;
    }
    
    public int getVx() {
        return Vx;
    }
    public int getVy() {
        return Vy;
    }
    public int getVw() {
        return Vw;

    }

    public void setVx(int vx) {
        this.Vx = vx;
    } 
    public void setVy(int vy) {
        this.Vy = vy;
        
    }
    public void setVw(int vw) {
        this.Vw = vw;
    }
    
    public int getAx() {
        return Ax;
    }
    public int getAy() {
        return Ay;
    }
    public int getAw() {
        return Aw;

    }
    
    public void setAx(int ax) {
        this.Ax = ax;
    } 
    public void setAy(int ay) {
        this.Ay = ay;
        
    }
    public void setAw(int aw) {
        this.Aw = aw;
    }
    
    
    public void throttleUp(int throt) {
        if (throt > MAX_THROTTLE) {
            
        }
    }
    
    public void throttleDown(int throt) {
        
    }
    
    public void move() {
        
    }
    
    
}
