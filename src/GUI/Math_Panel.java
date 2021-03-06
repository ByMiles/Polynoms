package GUI;

import GUI.menu.*;
import javax.swing.*;
import java.awt.*;

public class Math_Panel extends JPanel
{
    private JPanel[] content;
    private Menu_Label result_label;

    public Math_Panel(String title)
    {
        this.setBackground(new Color(204, 229, 255));
        this.setLayout(new GridLayout(7, 1, 5, 5));
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        content = new JPanel[7];

        for (int i = 0; i < content.length; i++)
        {
            content[i] = new JPanel();
            content[i].setBackground(new Color(204, 229, 255));
            this.add(content[i]);
        }

        JLabel title_label = new JLabel(title);
        title_label.setHorizontalAlignment(JLabel.CENTER);
        title_label.setVerticalAlignment(JLabel.CENTER);
        title_label.setHorizontalTextPosition(JLabel.CENTER);
        title_label.setVerticalTextPosition(JLabel.CENTER);
        title_label.setFont(new Font("Serif", Font.BOLD, 40));
        title_label.setForeground(new Color(0, 51, 102));

        content[0].add(title_label);
    }

    protected void add_output()
    {
        result_label = new Menu_Label("");
        content[5].add(result_label);
    }

    protected void add_with_scroller(int row, JPanel stuff)
    {
        content[row].setLayout(new GridLayout(1,1));
        content[row].add(new JScrollPane(stuff));
    }

    protected void output(String text)
    {
        result_label.setText(text);
    }

    public boolean check_output()
    {
        return !(result_label.getText().equals("") || result_label.getText().substring(0,7).equals("<html>0"));
    }

    public void add_stuff(int row, Menu_Input stuff)
    {
        content[row].add(stuff);
    }

    public void add_stuff(int row, Menu_Label stuff)
    {
        content[row].add(stuff);
    }

    public void add_stuff(int row, Menu_Button stuff)
    {
        content[row].add(stuff);
    }

    public void add_stuff(int row, Menu_Dropdown stuff)
    {
        content[row].add(new JScrollPane(stuff));
    }

    public void add_stuff(int row, JPanel stuff) {content[row].add(stuff); }

    public void split(int row)
    {
        content[row].setLayout(new GridLayout(1, 2, 15, 0));
    }
}
