package GUI.math;

import GUI.GUI;
import GUI.menu.*;
import GUI.Math_Panel;
import polyAdmin.Collection;
import polyAdmin.Polynomial;

public class Math_Addition extends Math_Panel
{
    private GUI gui;
    private Polynomial result;


    public Math_Addition(Collection collection, GUI gui)
    {
        super("Addition zweier Polynome");
        this.gui = gui;

        Menu_Dropdown[] dropdown = new Menu_Dropdown[2];

        dropdown[0] = new Menu_Dropdown(collection.getAsArray());
        this.add_stuff(1, dropdown[0]);

        this.add_stuff(2, new Menu_Label("+"));

        dropdown[1] = new Menu_Dropdown(collection.getAsArray());

        this.add_stuff(3, dropdown[1]);

        this.add_stuff(4, new Menu_Button("   =   ", e ->
        {
            result = collection.addition(dropdown[0].getSelectedIndex(), dropdown[1].getSelectedIndex());
            this.output(result.toString());
        }));

        this.add_output();

        this.add_stuff(6, new Menu_Button("Speichern", e ->
        {
            if(this.check_output())
            {
                collection.addPolynom(result);
                this.gui.action(4);
            }
        }));
    }
}
