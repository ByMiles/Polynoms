package GUI.list;

import GUI.GUI;
import GUI.Math_Panel;
import polyAdmin.Collection;
import GUI.menu.*;

import javax.swing.*;
import java.awt.*;


public class List_Add extends Math_Panel
{
    private Collection collection;
    private GUI gui;
    private JPanel exchangeable, poly_panel;
    private Menu_Input[] poly_fields;
    private double[] poly_array;
    private Menu_Label confirm_label;


    public List_Add(Collection collection, GUI gui)
    {
        super("Ein Polynomial hinzufügen");
        this.collection = collection;
        this.gui = gui;

        Menu_Input grade_field = new Menu_Input();
        this.add_stuff(1, grade_field);

        this.add_stuff(2, new Menu_Button("Grad", e -> setPolypanel(grade_field.getText())));

        exchangeable = new JPanel(new GridLayout(1,1));
        this.add_with_scroller(4, exchangeable);

        this.add_stuff(5, new Menu_Button("hinzufügen", e -> setPolyarray()));

        confirm_label = new Menu_Label("");
        this.add_stuff(6, confirm_label);

        setPolypanel("3");

    }

    private void setPolypanel(String text)
    {
        int number;
        try
        {
            number = Integer.parseInt(text);
        }
        catch (NumberFormatException e){return;}

        if(number < 0 || number > 20)
            return;

        try
        {
            exchangeable.remove(poly_panel);
        }
        catch (Exception ignored){}

        poly_panel = new JPanel();
        poly_panel.setBackground(new Color(204, 229, 255));
        poly_fields = new Menu_Input[number+1];
        Menu_Label[] poly_labels = new Menu_Label[number + 1];
        poly_array = new double[number+1];

        for (int i = number; i >= 0 ; i--)
        {
            poly_fields[i] = new Menu_Input();
            poly_panel.add(poly_fields[i]);

            if(i == 0)
                text = "";
            else if(i == 1)
                text = "x + ";
            else
                text = "<html>x<sup>" + i + "</sup> + </html>";

            poly_labels[i] = new Menu_Label(text);
            poly_panel.add(poly_labels[i]);

        }
        exchangeable.add(poly_panel);
        exchangeable.revalidate();
    }

    private void setPolyarray()
    {
        int sum = 0;
        for (int i = 0; i < poly_array.length; i++)
        {
            try
            {
                poly_array[i] = Double.parseDouble(poly_fields[i].getText());
            }
            catch(NumberFormatException e){poly_array[i] = 0;}
            sum += Math.abs(poly_array[i]);
        }
        if(sum == 0)
            confirm_label.setText("ungültig");
        else
        {
            collection.addPolynom(collection.createPolynom(poly_array));
            confirm_label.setText(collection.getText(collection.getLength() - 1));

            if (collection.getLength() == 1)
            {
                gui.enableButtons();
            }
        }
    }
}
