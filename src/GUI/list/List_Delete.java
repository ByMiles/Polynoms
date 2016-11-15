package GUI.list;


import GUI.GUI;
import GUI.menu.*;
import GUI.List_Panel;
import polyAdmin.Collection;

import java.awt.event.ActionListener;

public class List_Delete extends List_Panel
{
    private Menu_Button[] poly_buttons;

    public List_Delete(Collection collection, GUI gui)
    {
        super(collection, "Polynome löschen");

        ActionListener al = e -> {
            for (int i = 0; i < poly_buttons.length; i++)
            {
                if(e.getSource() == poly_buttons[i])
                {
                    collection.deletePolynom(i);
                    if(collection.getLength() > 0)
                        gui.action(3);
                    else
                        gui.disableButtons();
                }
            }
        };

        if(collection.getLength() == 0)
        {
            this.add_stuff(1, new Menu_Label("Liste leer"));
        }
        else
        {
            poly_buttons = new Menu_Button[collection.getLength()];

            for (int i = 0; i < collection.getLength(); i++)
            {
                poly_buttons[i] = new Menu_Button(collection.getText(i), al);
                this.add_stuff(i+1, poly_buttons[i]);
            }
        }
    }
}
