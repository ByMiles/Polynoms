import javax.swing.*;
import java.awt.*;

class Menu_Dropdown extends JComboBox
{
    Menu_Dropdown(Polynom[] array)
    {
        super(array);
        this.setFont(new Font("Serif",Font.PLAIN , 20));
        this.setBackground(new Color(204, 229, 255));

    }
    Menu_Dropdown(String[] array)
    {
        super(array);
        this.setFont(new Font("Serif",Font.PLAIN , 20));
        this.setBackground(new Color(204, 229, 255));
    }
}

