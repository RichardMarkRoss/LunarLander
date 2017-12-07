    /**
     * CIS 120 Game HW
     * (c) University of Pennsylvania
     * @version 2.1, Apr 2017
     */

    import java.awt.*;
    import java.awt.event.*;
    import javax.swing.*;

    /**
     * Canvas
     * 
     * This class holds the primary game logic for how different objects interact with one another. Take
     * time to understand how the timer interacts with the different methods and how it repaints the GUI
     * on every tick().
     */
@SuppressWarnings("serial")
public class Canvas extends JPanel {

  

        public boolean playing = false; // whether the game is running 

        // Game constants
        public static final int CANVAS_WIDTH = 500;
        public static final int CANVAS_HEIGHT = 500;
        public static final int SQUARE_VELOCITY = 4;

        // Update interval for timer, in milliseconds
        public static final int INTERVAL = 16;

        //not private because I need coutner to work in reset, and in the keypress listerner class
        protected int i =0 ;
        private int j =0;
        
        //create the physics world
        //TODO:Do I need to pass this anything else?
        //Important to be final bc I'm passing it around to the error classes
        private final LunarModel lm = new LunarModel();
                
        private GameState gs;
        private JPanel tc;
        
        //TODO: change the status thing to just be a field of gs
        //Jpanel for display of info from model displayed in canvas
        public Canvas(GameState gs, JPanel tc) {
            
            this.gs = gs;
            this.tc = tc;
            // creates border around the court area, JComponent method
            setBorder(BorderFactory.createLineBorder(Color.BLACK));

            // The timer is an object which triggers an action periodically with the given INTERVAL. We
            // register an ActionListener with this timer, whose actionPerformed() method is called each
            // time the timer triggers. We define a helper method called tick() that actually does
            // everything that should be done in a single timestep.
            Timer timer = new Timer(INTERVAL, new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    tick();
                }
            });
            // MAKE SURE TO START THE TIMER!
            timer.start();

            // Enable keyboard focus on the court area.
            // When this component has the keyboard focus, key events are handled by its key listener.
            setFocusable(true);

            // This key listener allows the square to move as long as an arrow key is pressed, by
            // changing the square's velocity accordingly. (The tick method below actually moves the
            // square.)
            addKeyListener(new KeyAdapter() {

                //TODO: Change j and i to +/- constants so that can change rate of throttle increase
                
                public void keyPressed(KeyEvent e) {
                     if (e.getKeyCode() == KeyEvent.VK_UP) {
                         
                         j=0;
                         lm.throttle(i);
                         i++;

                     }
                     if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                         
                         i=0;
                         lm.throttle(j);
                         j--;

                     }
                     //Full throttle momentarily
                     if (e.getKeyCode() == KeyEvent.VK_F) {
                         
                         
                         lm.throttle(20);

                     }
                     //TODO: Add "Abort" key that sets full throttle until you manually bring it down/ shut it off
                     if (e.getKeyCode() == KeyEvent.VK_LEFT) {

                         
                         lm.thrustL();

                     }
                     if (e.getKeyCode() == KeyEvent.VK_RIGHT) {

                         
                         lm.thrustR();

                     }
                     
             
                }

                public void keyReleased(KeyEvent e) {

                    if (e.getKeyCode() == KeyEvent.VK_F) {
                        
                        lm.throttle(0);

                    }

                }
            });

        }

        /**
         * (Re-)set the game to its initial state.
         */
        public void reset() {
            
            //TODO:Reset
            /*square = new Square(CANVAS_WIDTH, CANVAS_HEIGHT, Color.BLACK);
            poison = new Poison(CANVAS_WIDTH, CANVAS_HEIGHT);
            snitch = new Circle(CANVAS_WIDTH, CANVAS_HEIGHT, Color.YELLOW);*/
            
            //OR just make a new one each time...this looks cleaner to me
            lm.reset();
            
            i =0;
            j =0;
            playing = true;

            // Make sure that this component has the keyboard focus
            requestFocusInWindow();
        }

        /**
         * This method is called every time the timer defined in the constructor triggers.
         */
        //TODO:I made this private.....
        private void tick() {
            if (playing) {
                /*// advance the square and snitch in their current direction.
                square.move();
                snitch.move();

                // make the snitch bounce off walls...
                snitch.bounce(snitch.hitWall());
                // ...and the mushroom
                snitch.bounce(snitch.hitObj(poison));

                // check for the game end conditions
                if (square.intersects(poison)) {
                    playing = false;
                    status.setText("You lose!");
                } else if (square.intersects(snitch)) {
                    playing = false;
                    status.setText("You win!");
                }
*/
                
                // update the display
                //TODO:This is a demo of what some type of network listener should do
                
                //TODO:Before or after move? probs doesnt matter.
                //error.causeFailure(lm);

                //TODO:refactor where the timer is and ticking. ie maybe make timer at top level
                //and pass as arg to both canvas and telempanel
                
                //if theres an error in state, cause the error
                //TODO:WILL NEED TO CHANGE THAT ERROR FIELD TO A QUEUE so that he can send multiple errors at time
                //paint before removing error from que, may as well repaint all here
                
                //TODO:moving paing up here doesnt fix anything, so can move back down later.
                //Need to actually fix logic of getting error from queue.
                
                tc.repaint(); //Repaint the display panel
                repaint();
                
                gs.setAltitude(lm.getAltitude());
                
                //TODO:change get error to not force waiting......annoying because in netwrork I need it 
                //to wait but here it doesnt. Rethink.
                
                Error err = gs.getErrorAttempt();
                //System.out.println(err);
                if (err != null) {
                    err.causeFailure(lm, gs);
                    gs.setErrorsDone(err);
                    //System.out.println("here");
                    

                }
                
                
                
                //update the game state velocities
                
                //TODO:commented out just for debugging networking, only game thread will ever really set
                //so not need to be synced. This is just an issue for client.
                //ok/ switch to errors.
                gs.setVx(lm.getVx());                
                gs.setVy(lm.getVy());
                gs.setVw(lm.getVw());
                
                
                
                
                //System.out.println(gs.getVy());

                lm.move();
             //System.out.println(lm.getPy());
                //System.out.println(lm.getVy());
//System.out.println(lm.getAttX());
//System.out.println(lm.getAttY());
//System.out.println(lem.getLinearVelocity());

                //super.repaint();

                
                if (lm.isCollided()) {
                    
                    //TODO:Add logic for velocity of colossion with collision, can unit test that
                    //TODO:DO something more exciting, i.e. "you lose" (Have seperate class for "Game State"
                    reset();
                }
            }
        }
        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
//            snitch.draw(g);
            //TODO:make color better....should I make this whole thing a seperate class, or 
            //just draw all in canvas directly like this?

           // TODO: DRAW BASED ON SHAPE, not body (use constant that sets both)
            //TODO: THis is for now the logic for drawing the lunar lander FIX THIS
            g.fillRect(lm.getPx(), 
                    lm.getPy(), 10, 10);
            //System.out.println(lm.getAngle());
            g.drawLine(lm.getPx(), lm.getPy(), (int)Math.round(lm.getPx()+30*Math.sin(lm.getAngle())), (int)Math.round(lm.getPy()+30*Math.cos(lm.getAngle())));
            //TODO: Draw the surface when its more complicated
            g.drawLine(0, Canvas.CANVAS_HEIGHT-10, Canvas.CANVAS_WIDTH, Canvas.CANVAS_HEIGHT-10);

        }
        @Override
        public Dimension getPreferredSize() {
            return new Dimension(CANVAS_WIDTH, CANVAS_HEIGHT);
        }



    }
    

