import java.awt.*;
import java.awt.event.*;
import java.util.Collections;
import javax.swing.*;
import javax.swing.border.*;

public class InputDialog extends JDialog {

    private JTextField tfName;
    private JTextField tfScore;
    private JLabel lbName;
    private JLabel lbScore;
    private JButton btnAdd;
    private JButton btnCancel;

    public InputDialog(Frame parent) {
        super(parent, "Entry", true);
        //
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints cs = new GridBagConstraints();

        cs.fill = GridBagConstraints.HORIZONTAL;

        lbName = new JLabel("Name: ");
        cs.gridx = 0;
        cs.gridy = 0;
        cs.gridwidth = 1;
        panel.add(lbName, cs);

        tfName = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 0;
        cs.gridwidth = 2;
        panel.add(tfName, cs);

        lbScore = new JLabel("Score: ");
        cs.gridx = 0;
        cs.gridy = 1;
        cs.gridwidth = 1;
        panel.add(lbScore, cs);

        tfScore = new JTextField(20);
        cs.gridx = 1;
        cs.gridy = 1;
        cs.gridwidth = 2;
        panel.add(tfScore, cs);
        panel.setBorder(new LineBorder(Color.GRAY));

        btnAdd = new JButton("Add");

        btnAdd.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                String Name = tfName.getText();
                String Score = tfScore.getText();
                Pair new_pair = new Pair(Name, Integer.valueOf(Score));
                ScoreBoard.data.add(new_pair);
                System.out.println("button " + ScoreBoard.data.size());
                Collections.sort(ScoreBoard.data, new compare());
                if(ScoreBoard.btn.getToolTipText()=="Decreasing Order")
                {
                    Collections.reverse(ScoreBoard.data);
                }
                ScoreBoard.updateTable(ScoreBoard.data);
            }
        });
        btnCancel = new JButton("Cancel");
        btnCancel.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        JPanel bp = new JPanel();
        bp.add(btnAdd);
        bp.add(btnCancel);

        getContentPane().add(panel, BorderLayout.CENTER);
        getContentPane().add(bp, BorderLayout.PAGE_END);

        pack();
        setResizable(true);
        setLocationRelativeTo(parent);
    }

}