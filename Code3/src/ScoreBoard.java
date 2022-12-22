import java.awt.*;
import javax.swing.*;
import java.io.*;

public class ScoreBoard extends JFrame {

    private JLabel set_score_guest;
    private JLabel set_score_home;
    private PointButton point_guest;
    private PointButton point_home;
    private String guest_name;
    private String home_name;

    public ScoreBoard() {
    	guest_name = JOptionPane.showInputDialog("Enter your name.");
		home_name = JOptionPane.showInputDialog("Enter your name.");
        Container cp = getContentPane();
        cp.setLayout(new GridLayout(4,1));
        JPanel p1 = new JPanel(new FlowLayout());
        p1.add(new JLabel(guest_name));
        p1.add(new JLabel(":"));
        p1.add(new JLabel(home_name));
        cp.add(p1);
        JPanel p2 = new JPanel(new FlowLayout());
        set_score_guest = new JLabel("0");
        p2.add(set_score_guest);
        p2.add(new JLabel(":"));
        set_score_home = new JLabel("0");
        p2.add(set_score_home);
        cp.add(p2);
        JPanel p3 = new JPanel(new FlowLayout());
        point_guest = new PointButton(this);
        p3.add(point_guest);
        point_home = new PointButton(this);
        p3.add(point_home);
        cp.add(p3);
        JPanel p4 = new JPanel(new FlowLayout());
        p4.add(new NewButton(this));
        p4.add(new ExitButton());
        cp.add(p4);
        begin();
        setTitle("Badminton");
        setSize(200,160);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
    
    
    /* begin - 새 경기를 기록할 수 있도록 점수 버튼을 모두 0으로 세팅하고,  
     *         세트 스코어도 0 : 0 으로 세팅한다.
     */
    public void begin() {
        if (matchOver()) {
            set_score_guest.setText("0");
            set_score_home.setText("0");
            point_guest.setText("0");
            point_home.setText("0");
            new ScoreBoard();
        }
    }

    /* matchOver - 경기가 끝났는지 판정해준다. 둘 중 하나가 2세트를 먼저 따면 승패가 결정되어 경기가 끝난다.
     * @returns true - 경기가 끝났음, false - 경기가 끝나지 않았음
     */
    public boolean matchOver() {
        int guest = Integer.parseInt(set_score_guest.getText());
        int home = Integer.parseInt(set_score_home.getText());
        return guest == 2 || home == 2;
    }
    
    /* update - 스코어보드를 갱신한다.
     */
    public void update() {
        int guest = Integer.parseInt(point_guest.getText());
        int home = Integer.parseInt(point_home.getText());
        
        if (guest >= 10 && home >= 10) {
        	if (guest == home + 2) {
        		point_guest.setText("0");
                point_home.setText("0");
                updateSetScore(set_score_guest);
        	} else if (guest + 2 == home) {
        		point_guest.setText("0");
                point_home.setText("0");
                updateSetScore(set_score_home);
        	}
        } else {
        	if (guest == 11) {
            	point_guest.setText("0");
                point_home.setText("0");
                updateSetScore(set_score_guest);
            }
            else if (home == 11) {
            	point_guest.setText("0");
                point_home.setText("0");
                updateSetScore(set_score_home);
            }
        }
        
    }
    
    /* updateSetScore - 승리한 선수의 세트 스코어를 1 증가시킨다.
     * @param winner - 승리한 선수의 JLabel 객체 
     */
    private void updateSetScore(JLabel winner) {
        int new_set_score = Integer.parseInt(winner.getText()) + 1;
        winner.setText(Integer.toString(new_set_score));
        
        if (matchOver()) {
        	point_guest.setText("");
        	point_home.setText("");
        	
        	File file = new File("result.csv");
        	if (!file.exists()) {
        		try {
        			file.createNewFile();
        		}
        		catch (IOException e) {}
        	}
        	
        	try {
        		FileWriter filewriter = new FileWriter(file, true);
            	BufferedWriter writer = new BufferedWriter(filewriter);
            	
            	writer.write(guest_name + "," + home_name + "," + set_score_guest.getText() + "," + set_score_home.getText() + "\n");
            	writer.close();
        	}
        	catch (IOException e) {
        		e.printStackTrace();
        	}
        }
    }
    
    public String guest_name() {
		return guest_name;
	}
	
	public String home_name() {
		return home_name;
	}

}