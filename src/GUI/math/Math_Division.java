package GUI.math;

import GUI.GUI;
import GUI.menu.*;
import GUI.Math_Panel;
import polyAdmin.Collection;
import polyAdmin.Polynomial;


public class Math_Division extends Math_Panel
{
    private GUI gui;
    private Polynomial result;

    private Menu_Input input;

    public Math_Division(Collection collection, GUI gui)
    {
        super("Division durch (x-a)");
        this.gui = gui;

       Menu_Dropdown dropdown = new Menu_Dropdown(collection.getAsArray());
        this.add_stuff(1, dropdown);

        this.add_stuff(2, new Menu_Label("/"));

        this.add_stuff(3, new Menu_Label("( x  "));

        Menu_Dropdown plusminus = new Menu_Dropdown(new String[]{" + ", " - "});
        this.add_stuff(3, plusminus);

        input = new Menu_Input();
        this.add_stuff(3, input);

        this.add_stuff(3, new Menu_Label(" )"));

        this.add_stuff(4, new Menu_Button("   =   ", e ->
        {
            String text = "";
            if(plusminus.getSelectedIndex() == 1)
            {
                text += "-";
            }

            text += input.getText();
            result = collection.divisionHorner(dropdown.getSelectedIndex(), text);

            text = (result.toString().substring(0, result.toString().length()- 7) + " + " + result.remainderToString() + "</html>");
            this.output(text);
        }));

        this.add_output();

        this.add_stuff(6, new Menu_Button("Speichern", e ->
        {
            if(this.check_output())
            {
                collection.addPolynom(result);
                this.gui.action(7);
            }
        }));
    }
}
