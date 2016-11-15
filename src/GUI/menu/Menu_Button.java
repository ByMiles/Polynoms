package GUI.menu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Menu_Button extends JButton
{
    boolean state[];
    ActionListener ae;

    public Menu_Button(String text, ActionListener ae)
    {
        this.ae = ae;
        this.addActionListener(this.ae);
        this.setText(text);
        this.setBackground(new Color(153,204, 255));
        this.setVerticalAlignment(JButton.CENTER);
        this.setHorizontalAlignment(JButton.CENTER);
        this.setFont(new Font("Serif", Font.BOLD, 24));

        state = new boolean[]{true, false, true};
    }

    void setState(boolean active, boolean selected)
    {
        state[0] = active;
        state[1] = selected;
        checkState();
    }

    public void setSelection(boolean selection)
    {
        state[1] = selection;
        checkState();
    }

    public void setActive(boolean active)
    {
        state[0] = active;
        checkState();
    }

    void checkState()
    {
        if(state[0])
        {
            if(!state[2])
            {
                this.addActionListener(ae);
                state[2] = true;
            }

            if(state[1])
                this.setBackground(new Color(51, 153, 255));
            else
                this.setBackground(new Color(153,204,255));
        }
        else
        {
            if(state[2])
            {
                this.removeActionListener(ae);
                state[2] = false;
            }
            this.setBackground(new Color(192, 192, 192));
        }
    }
}
