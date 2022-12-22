import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.DocumentFilter;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;

public class GameParamsWindow extends JDialog {
    JComboBox<Level> cbDifficulty;
    JLabel lblDifficulty,lblCols,lblRows,lblMines;
    JNumericTextField txtCols,txtRows,txtMines;
    JButton btnStart;
    JPanel pnlCommon,pnlBasic,pnlCustomize;
    JFrame frmParent;
    boolean bCancelled=false;
    
    public class JNumericTextField extends JTextField {
        
        JNumericTextField(int nMaxLength)
        {
            setPreferredSize(new Dimension(40,20));
            AbstractDocument doc=(AbstractDocument) getDocument();
            addFocusListener(new FocusAdapter() {
                    public void focusGained(FocusEvent e) {
                        SwingUtilities.invokeLater(new Runnable() {
                            public void run() {
                                ((JNumericTextField)e.getSource()).selectAll();
                            }
                        });
                    }
                });
            doc.setDocumentFilter(new DocumentFilter() {
                public void replace(FilterBypass fb, int offset, int len,
                        String str, AttributeSet a) throws BadLocationException {
                        // JNumericTextField allows only digits
                        String sNumericOnly=str.replaceAll("[^0-9]", "");
                        super.replace(fb, offset,len, sNumericOnly ,a);
                        // restrict text length
                        if(doc.getLength()>nMaxLength) doc.replace(0, doc.getLength(), doc.getText(0, doc.getLength()).substring(0,nMaxLength),a);
                }
            });
        }
        
        public int getNumber()
        {
            // get integer from JNumericTextField instance
            if(getText().isEmpty()) return 0;
            return Integer.parseInt(getText());
        }
        
        public void setNumber(int num)
        {
            setText(Integer.toString(num));
        }
    }
    
    public void setCustomValuesAllowed(boolean bFlag)
    {
        //(dis)allow setting custom values
        txtCols.setEditable(bFlag);
        txtRows.setEditable(bFlag);
        txtMines.setEditable(bFlag);
    }
    
    public GameParamsWindow()
    {
        initSettingsUI();
        setModal(true); // this is a dialog window
    }
    
    Level showDialog(JFrame frmParent)
    {
        Level selectedLevel;
        bCancelled=false;
        this.frmParent=frmParent;
        setLocationRelativeTo(this.frmParent);
        cbDifficulty.setSelectedItem(cbDifficulty.getSelectedItem()); // refresh text fields
        setVisible(true);
        if(bCancelled) { return null; } // dialog was closed with "X" button
        selectedLevel=((Level)cbDifficulty.getSelectedItem()); // get selected level
        selectedLevel.setParameters(txtCols.getNumber(), txtRows.getNumber(), txtMines.getNumber()); // update level info
        return selectedLevel;
    }
    
    private void initSettingsUI()
    {
        setTitle("New Game");
        setResizable(false);
        pnlCommon=new JPanel();
        pnlCommon.setLayout(new BoxLayout(pnlCommon, BoxLayout.Y_AXIS));
        pnlBasic = new JPanel(new FlowLayout());
        pnlCustomize = new JPanel(new FlowLayout());
        lblDifficulty= new JLabel("Difficulty level");
        cbDifficulty = new JComboBox<Level>();
        txtCols = new JNumericTextField(2); // number of cols
        txtRows = new JNumericTextField(2); // number of rows
        txtMines = new JNumericTextField(3); // number of mines
        cbDifficulty.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                Level selectedLevel=(Level)((JComboBox<Level>)e.getSource()).getSelectedItem();
                txtCols.setNumber(selectedLevel.nCols);
                txtRows.setNumber(selectedLevel.nRows);
                txtMines.setNumber(selectedLevel.nTotalMines);
                setCustomValuesAllowed(selectedLevel.sName=="Custom");
            }
        });
        for(Level lvl: Level.arrPredefinedLevels) cbDifficulty.addItem(lvl); // push predefined levels to combobox
        lblCols = new JLabel("Columns:");
        lblRows = new JLabel("Rows:");
        lblMines = new JLabel("Mines:");
        btnStart = new JButton("Start");
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false); // "Start" button is pressed - dialog should be closed
                // Now parent window will get the selected level and we are ready to start the game
            }
        }); 
        pnlBasic.add(lblDifficulty);
        pnlBasic.add(cbDifficulty);
        pnlBasic.add(btnStart);
        pnlCustomize.add(lblCols);
        pnlCustomize.add(txtCols);
        pnlCustomize.add(lblRows);
        pnlCustomize.add(txtRows);
        pnlCustomize.add(lblMines);
        pnlCustomize.add(txtMines);
        pnlCommon.add(pnlBasic);
        pnlCommon.add(pnlCustomize);
        add(pnlCommon);
        pack();
        getRootPane().setDefaultButton(btnStart);
        addWindowListener( new WindowAdapter() {
              @Override
              public void windowClosing(WindowEvent we) {
                bCancelled=true; // "X" button was clicked
                // Dialog is cancelled and game won't be started
              }
              
              @Override
              public void windowActivated(WindowEvent we)
              {
                // if dialog is activated - make it's parent visible too
                  frmParent.setVisible(true); 
              }
            } );
    }
}