package GUI.menu;

import polyAdmin.Polynomial;
import javax.swing.*;
import java.awt.*;

public class Menu_Dropdown extends JComboBox
{
    public Menu_Dropdown(Polynomial[] array)
    {
        super(array);
        this.setFont(new Font("Serif",Font.PLAIN , 20));
        this.setBackground(new Color(204, 229, 255));

    }
    public Menu_Dropdown(String[] array)
    {
        super(array);
        this.setFont(new Font("Serif",Font.PLAIN , 20));
        this.setBackground(new Color(204, 229, 255));
    }
}

