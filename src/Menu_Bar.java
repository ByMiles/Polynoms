import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class Menu_Bar extends JPanel
{
    private Menu_Button[] buttons;
    private ActionListener ae;
    private GUI gui;
    private int menus;

    Menu_Bar(String[] texts, GUI gui)
    {
        this.gui = gui;
        menus = texts.length;
        this.setLayout(new GridLayout(texts.length, 1, 3, 3));
        this.setBorder(BorderFactory.createEmptyBorder(3, 5, 3, 5));
        this.setBackground(new Color(102,255,157));

        buttons = new Menu_Button[texts.length];
        create_ae(texts.length);
        for (int i = 0; i < texts.length; i++)
        {
            buttons[i] = new Menu_Button(texts[i], ae);
            this.add(buttons[i]);
        }
    }

    private void create_ae(int length)
    {
        this.ae = e -> {
            for (int i = 0; i < length; i++)
            {
                if(e.getSource() == buttons[i])
                {
                    gui.action(i);
                    change_buttons(i);
                }
            }
        };
    }

    private void change_buttons(int selection)
    {
        for (int i = 0; i < buttons.length; i++)
        {
            if(i == selection)
            {
                buttons[i].setSelection(true);
            }
            else
                buttons[i].setSelection(false);
        }
    }

    int getMenus()
    {
        return menus;
    }

    void enableButton(int enabled)
    {
        buttons[enabled].setActive(true);
    }

    void disableButton(int disabled)
    {
        buttons[disabled].setActive(false);
    }


}
