import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import java.awt.FlowLayout;
import java.awt.Image;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

public class MainWindow {

    private JFrame frame;
    GamePanel pnlGameUI=new GamePanel();
    GameParamsWindow dlgNewGame = new GameParamsWindow();
    static Image appIcon;
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    appIcon=ImageIO.read(getClass().getResource("images/appIcon.png"));
                    MainWindow window = new MainWindow();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public MainWindow() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame("Minesweeper");
        frame.setIconImage(appIcon);
        frame.setBounds(100, 100, 450, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setResizable(false);
        JMenuBar tbMenu = new JMenuBar();
        tbMenu.setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
        JMenu mnuGame = new JMenu ("Game");
        JMenu mnuAbout = new JMenu ("About");
        JMenuItem itemNewGame = new JMenuItem("New Game");
        JMenuItem itemExit = new JMenuItem("Exit");
        itemNewGame.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                Level lvlChosen = dlgNewGame.showDialog(frame);
                if(lvlChosen!=null) // if dialog was just closed with "X" button - do nothing
                {
                    pnlGameUI.startGame(lvlChosen, false); // start a new game (level was set inside the dialog)
                    frame.pack();
                }
            }
        });
        itemExit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                System.exit(0);
            }
        });
        mnuAbout.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent me) {   
                JOptionPane.showMessageDialog(frame,"Minesweeper 1.0, 2021",
                        "About",JOptionPane.INFORMATION_MESSAGE,new ImageIcon(appIcon));
            }
        });
        mnuGame.add(itemNewGame);
        mnuGame.add(itemExit);
        tbMenu.add(mnuGame);
        tbMenu.add(mnuAbout);
        frame.getContentPane().add(tbMenu);
        frame.getContentPane().add(pnlGameUI);
        frame.pack();
    }

}
