package game;
import java.io.PrintWriter;

import javax.swing.JOptionPane;

public class ServerWriter extends NetworkHandler implements Runnable {

    
    private GameState gs;
    private PrintWriter pw;

    
    ServerWriter(GameState gs, PrintWriter pw){
        this.gs = gs;
        this.pw = pw;
    }
    
    @Override
    public void run() {
        
        while(true) {
            pw.print(new NetworkPacket(gs.getVx(),gs.getVy(),gs.getVw(),
                    gs.getAngle(), gs.getAltitude(), null, gs.getFuel()).getPacket());
            pw.flush();
            try {
                //Don't continuously send for performance reasons.
                Thread.sleep(100);
            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog(null, e.getMessage() + " Simulation is now closing");
                e.printStackTrace();
                System.exit(0);            }
                }
        
        
    }

}
