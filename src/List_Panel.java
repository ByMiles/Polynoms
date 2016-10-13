import javax.swing.*;
import java.awt.*;


class List_Panel extends JPanel
{
    private JPanel content[];

    List_Panel(Collection collection, String title)
    {
        this.setLayout(new GridLayout(1,1));
        int rows;
        if(collection.getLength() < 5)
            rows = 7;
        else
            rows = collection.getLength()+2;

        JPanel panel = new JPanel(new GridLayout(rows, 1, 3, 3));
        panel.setBackground(new Color(204, 229, 255));

        content = new JPanel[rows];

        for (int i = 0; i < content.length; i++)
        {
            content[i] = new JPanel();
            content[i].setBackground(new Color(204, 229, 255));
            panel.add(content[i]);
        }

        JLabel title_label = new JLabel(title);
        title_label.setHorizontalAlignment(JLabel.CENTER);
        title_label.setVerticalAlignment(JLabel.CENTER);
        title_label.setHorizontalTextPosition(JLabel.CENTER);
        title_label.setVerticalTextPosition(JLabel.CENTER);
        title_label.setFont(new Font("Serif", Font.BOLD, 40));
        title_label.setForeground(new Color(0, 51, 102));

        content[0].add(title_label);

        this.add(new JScrollPane(panel));
    }

    void add_stuff(int row, Menu_Label stuff)
    {
        content[row].add(stuff);
    }

    void add_stuff(int row, Menu_Button stuff)
    {
        content[row].add(stuff);
    }
}
