import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.Timer;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class GamePanel extends JPanel
{
    final int ONE_SECOND=1000; // 1 s = 1000 ms
    JPanel pnlProgress;
    JLabel lblTime,lblMines; // elapsed time in seconds and mines count
    JButton btnRestart; // "Restart" button
    GameGrid myGame;
    ActionListener updateCurrentTime = new ActionListener() {
          public void actionPerformed(ActionEvent evt) {
              int nCurrentTime=Integer.parseInt(lblTime.getText());
              if(nCurrentTime>=999) { tmGame.stop(); return; }
              lblTime.setText(String.format("%03d", nCurrentTime+1)); // increase time counter by one
          }
      };
    Timer tmGame = new Timer(ONE_SECOND, updateCurrentTime); // game timer
    
    boolean bFirstClick = true; // mines are planted after first click
    
    MouseAdapter userClickHandler = new MouseAdapter() {
        public void mousePressed(MouseEvent me) { 
            int x = me.getX();
            int y = me.getY();
            Cell requestedCell = myGame.getCellFromCoords(x,y); // get cell under mouse cursor
            if(SwingUtilities.isLeftMouseButton(me) && requestedCell.state==Cell.State.COVERED) // open cell on left-click
            {   
                if(bFirstClick) { // first click is always safe (impossible to lose)
                    myGame.plantMines(requestedCell);
                    tmGame.start();
                    bFirstClick=false;
                }
                myGame.clickCell(requestedCell);
                if(myGame.status!=Game.GameStatus.RUNNING) endGame(myGame.status); // check game result
            }
            else if(SwingUtilities.isRightMouseButton(me)) myGame.setFlag(requestedCell); // set or remove flag on right-click
            invalidate();
            repaint();
        }
    };
    
    public void startGame(Level lvl, boolean bRestartExisting)
    {
        tmGame.stop();
        if(bRestartExisting) {
            myGame.restartGame();
            tmGame.start();
        }
        else {
            if(myGame!=null) remove(myGame);
            myGame=new GameGrid(lvl.nCols, lvl.nRows, lvl.nTotalMines);
            add(myGame);
            bFirstClick=true;
        }
        btnRestart.setText("Restart");
        if(myGame.getMouseListeners().length==0) myGame.addMouseListener(userClickHandler); // enable mouse input if it's disabled
        lblMines.setText(String.format("%03d", myGame.nMines));
        lblTime.setText("000");
        myGame.invalidate();
        myGame.repaint();
    }
    
    public void endGame(Game.GameStatus state)
    {
        tmGame.stop();
        if(state==Game.GameStatus.VICTORY) btnRestart.setText("You Win!");
        else btnRestart.setText("You Lose!");
        myGame.removeMouseListener(userClickHandler); // disable mouse input
    }
    
    public GamePanel()
    {   
        this.setLayout(new BoxLayout(this,BoxLayout.Y_AXIS));
        lblTime=new JLabel("000");
        lblMines=new JLabel("000");
        btnRestart=new JButton("Restart");
        pnlProgress=new JPanel();
        pnlProgress.setLayout(new BoxLayout(pnlProgress, BoxLayout.X_AXIS));
        pnlProgress.add(lblTime);
        pnlProgress.add(Box.createGlue());
        pnlProgress.add(btnRestart);
        btnRestart.addActionListener(new ActionListener() { 
              public void actionPerformed(ActionEvent e) { 
                   startGame(null, true); // restart existing game
                  } 
                });
        pnlProgress.add(Box.createGlue());
        pnlProgress.add(lblMines);
        add(pnlProgress);
        startGame(Level.arrPredefinedLevels[0],false); // "Beginner" level is default
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);  
    }  
}