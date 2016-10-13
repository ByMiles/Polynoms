import javax.swing.*;
import java.awt.*;

class Menu_dropdown extends JComboBox
{
    Menu_dropdown(Polynom[] array)
    {
        super(array);
        this.setFont(new Font("Serif",Font.PLAIN , 20));
        this.setBackground(new Color(204, 229, 255));

    }
    Menu_dropdown(String[] array)
    {
        super(array);
        this.setFont(new Font("Serif",Font.PLAIN , 20));
        this.setBackground(new Color(204, 229, 255));
    }
}

