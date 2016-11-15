package GUI.math;

import GUI.Math_Panel;
import GUI.menu.*;
import polyAdmin.Collection;

public class Math_Value_at_Position extends Math_Panel
{
    private Collection collection;

    private Menu_Dropdown dropdown;

    private Menu_Label result_label, horner_label;
    private Menu_Input field;

    public Math_Value_at_Position(Collection collection)
    {
        super("Funktionswert der Stelle X");
        this.collection = collection;

        dropdown = new Menu_Dropdown(this.collection.getAsArray());
        this.add_stuff(1, dropdown);

        this.add_stuff(2, new Menu_Label("x-Wert:"));

        field = new Menu_Input();
        this.add_stuff(3, field);

        this.add_stuff(4, new Menu_Button("Berechnen", e ->
        {
            result_label.setText(String.valueOf(Math.round(this.collection.valueAt(dropdown.getSelectedIndex(), field.getText())*1000.)/1000.));
            horner_label.setText(String.valueOf(Math.round(this.collection.valueAt_Horner(dropdown.getSelectedIndex(), field.getText())*1000.)/1000.));
        }));

        this.split(5);
        this.add_stuff(5, new Menu_Label("\"Normal\":"));
        this.add_stuff(5, new Menu_Label("Nach Horner:"));

        this.split(6);
        result_label = new Menu_Label("");
        horner_label = new Menu_Label("");
        this.add_stuff(6, result_label);
        this.add_stuff(6, horner_label);
    }
}
