import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.util.Collections;

public class ScoreBoard
{

    JFrame f = new JFrame();
    static ArrayList<Pair> data = new ArrayList<>();
    static DefaultTableModel model = new DefaultTableModel();
    static JTable table=new JTable(model);
    JTextField tfFilter =new JTextField();
    JButton btnFilter=new JButton("Filter");
    static JButton btn=new JButton("Score   ");

    static void updateTable(ArrayList<Pair> arr) {
        /*if(btn.getToolTipText()=="Decreasing Order")
        {
            Collections.reverse(arr);
        }*/
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        if(arr.size()>0) {
            int rk = 1;
            model.addRow(new Object[]{String.valueOf(1), arr.get(0).x, arr.get(0).y});
            for (int i = 1; i < arr.size(); i++) {
                if (arr.get(i).y != arr.get(i - 1).y) {
                    rk++;
                }
                model.addRow(new Object[]{String.valueOf(rk), arr.get(i).x, arr.get(i).y});
            }
        }
    }
    static ArrayList<Pair> getarr(int score)
    {
        ArrayList<Pair> arr=new ArrayList<>();
        for(int i=0;i<data.size();i++)
        {
            if(data.get(i).y<score)
            {
                break;
            }
            arr.add(data.get(i));
        }
        return arr;
    }
    static ArrayList<Pair> getarr2(int score)
    {
        ArrayList<Pair> arr=new ArrayList<>();
        for(int i=0;i<data.size();i++)
        {
            if(data.get(i).y>=score)
            {
                break;
            }
            arr.add(data.get(i));
        }
        return arr;
    }
    ScoreBoard()
    {
        btn.setToolTipText("Increasing Order");
        Action actionn = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                //TableCellListener.cellEdit(e);
            }
        };
        new TableCellListener(table, actionn);
        f.setTitle("Scoreboard");
        JButton dbt=new JButton("Add");
        model.addColumn("Rank");
        model.addColumn("Name");
        model.addColumn("Score");
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(0, 40, 400, 250);
        tfFilter.setBounds(230,0,80,25);
        btnFilter.setBounds(310 ,0,90,25);
        dbt.setBounds(0,0,90,25);
        //table.
        dbt.addActionListener(
                new ActionListener(){
                    public void actionPerformed(ActionEvent e) {
                        InputDialog loginDlg = new InputDialog(f);
                        loginDlg.setVisible(true);
                    }
                });
        btnFilter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(data.size()==0)
                    return;
                if(btn.getToolTipText()=="Increasing Order")
                updateTable(getarr(Integer.valueOf(tfFilter.getText())));
                else updateTable(getarr2(Integer.valueOf(tfFilter.getText())));
            }
        });
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                Collections.reverse(data);
                updateTable(data);
                if(btn.getToolTipText()=="Increasing Order")
                {
                    btn.setToolTipText("Decreasing Order");
                }
                else
                {
                    btn.setToolTipText("Increasing Order");
                }
            }
        });

        //System.out.println("header " + table.getTableHeader());
        table.getTableHeader().setLayout(null);
        table.getTableHeader().add(btn).setBounds(350,0,20,20);
        f.add(sp);
        f.add(dbt);
        f.add(btnFilter);
        f.add(tfFilter);
        f.setSize(400, 400);
        f.setLayout(null);
        f.setVisible(true);
    }
    public static void main(String args[])
    {
        new ScoreBoard();
    }
}