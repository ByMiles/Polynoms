import javax.swing.*;
import java.awt.*;

class Math_Draw extends JPanel
{
    Collection collection;
    private int height_x;
    private int width_x;
    private int[][]values;
    private double[]value;
    private Menu_Dropdown dropdown;
    private int selected;

    Math_Draw(Collection collection, int width, int height)
    {
        this.width_x = width-10;
        this.height_x = (int)(height*0.76);
        this.collection = collection;
        this.setBackground(new Color(204, 229, 255));
        this.setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        values = new int[400][2];
        value = new double[400];
        selected = -1;

        JPanel[] content = new JPanel[4];
        content[0] = new JPanel(new GridLayout(2,1));
        this.add(content[0], BorderLayout.PAGE_START);

        content[1] = new JPanel();
        content[1].setBackground(new Color(204, 229, 255));
        JLabel title_label = new JLabel("Graph eines Polynoms");
        title_label.setHorizontalAlignment(JLabel.CENTER);
        title_label.setVerticalAlignment(JLabel.CENTER);
        title_label.setHorizontalTextPosition(JLabel.CENTER);
        title_label.setVerticalTextPosition(JLabel.CENTER);
        title_label.setFont(new Font("Serif", Font.BOLD, 40));
        title_label.setForeground(new Color(0, 51, 102));
        content[1].add(title_label);
        content[0].add(content[1]);

        content[2] = new JPanel();
        content[2].setBackground(new Color(204, 229, 255));
        dropdown = new Menu_Dropdown(collection.getAsArray());
        content[2].add(dropdown);
        content[0].add(content[2]);

        getValues();

        content[3] = new JPanel()
        {
            public void paintComponent(Graphics g)
            {
                if(dropdown.getSelectedIndex() != selected)
                {
                    getValues();
                    try
                    {
                        Thread.sleep(100);
                    }
                    catch (Exception ignored){}
                    selected = dropdown.getSelectedIndex();
                }
                g.setColor(Color.white);
                g.fillRect(0 ,0 , width_x, height_x);
                for (int i = 0; i < values.length-1; i++)
                {
                    g.setColor(Color.black);
                    g.drawLine(values[i][0], values[i][1], values[i+1][0], values[i+1][1]);
                }
                repaint();
            }
        };
        this.add(content[3], BorderLayout.CENTER);
        this.revalidate();
    }

    private void getValues()
    {
        for (int i = 0; i < value.length; i++)
        {
            value[i] = collection.valueAt(dropdown.getSelectedIndex(), String.valueOf((i-200)/10.));
        }

        double max = value[0];
        double min = value[0];
        int zero_x = width_x/2;
        int zero_y = height_x/2;

        for (int i = 1; i < value.length; i++)
        {
            if(value[i] > max)
                max = value[i];
            if(value[i] < min)
                min = value[i];
        }

        double range = (max - min);
        double factor;
        if(range == 0)
            factor = 1;
        else
            factor = 400./(max - min);

        System.out.println(min + " " + max + " " + range + " " + factor);

        for (int i = 0; i < values.length; i++)
        {
            values[i][0] = i - 200 + zero_x;
            values[i][1] = (zero_y-200) + (int)((max - value[i])*factor);
            System.out.println(value[i] + " " + values[i][0] + " " + values[i][1]);
        }


    }
}
