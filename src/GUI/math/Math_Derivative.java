package GUI.math;

import GUI.GUI;
import GUI.Math_Panel;
import GUI.menu.*;
import polyAdmin.Collection;
import polyAdmin.Polynomial;


public class Math_Derivative extends Math_Panel
{
    private GUI gui;

    private Menu_Input field;

    private Polynomial result;

    public Math_Derivative(Collection collection, GUI gui)
    {
        super("Bestimmen einer Ableitung");
        this.gui = gui;

        Menu_Dropdown dropdown = new Menu_Dropdown(collection.getAsArray());

        this.add_stuff(1, dropdown);

        field = new Menu_Input();
        this.add_stuff(3, field);

        this.add_stuff(3, new Menu_Label(". Ableitung"));

        this.add_stuff(4, new Menu_Button("   =   ", e ->
        {
            result = collection.derivative(dropdown.getSelectedIndex(), field.getText());
            this.output(result.toString());
        }));

        this.add_output();

        this.add_stuff(6, new Menu_Button("Speichern", e ->
        {
            if(this.check_output())
            {
                collection.addPolynom(result);
                this.gui.action(10);
            }
        }));
    }
}
