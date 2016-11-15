package GUI.list;

import GUI.Math_Panel;
import GUI.menu.*;
import polyAdmin.Collection;

import javax.swing.*;
import java.awt.*;

public class List_Edit extends Math_Panel
{
    private Collection collection;
    private Menu_Dropdown dropdown;

    private JPanel exchanable, poly_panel;
    private Menu_Input[] poly_fields;
    private double[] poly_array;
    private Menu_Label confirm_label;

    private boolean selected;

    public List_Edit(Collection collection)
    {
        super("Ein Polynomial bearbeiten");
        this.collection = collection;
        selected = false;

        dropdown = new Menu_Dropdown(collection.getAsArray());
        this.add_stuff(1, dropdown);

        Menu_Input grade_field = new Menu_Input();
        this.add_stuff(2, grade_field);

        this.add_stuff(3, new Menu_Button("Grad", e -> {
            if(collection.getLength()>0)
                setPolypanel(dropdown.getSelectedIndex() ,grade_field.getText());
        }));

        exchanable = new JPanel(new GridLayout(1,1));
        exchanable.setBackground(new Color(204, 229, 255));
        this.add_with_scroller(4, exchanable);

        this.split(5);
        this.add_stuff(5, new Menu_Button("hinzufügen", e ->
        {
            if(selected)
                setPolyarray();
        }));
        this.add_stuff(5, new Menu_Button("ersetzen", e ->
        {
            if(selected)
                setPolyarray(dropdown.getSelectedIndex());
        }));

        confirm_label = new Menu_Label("");
        this.add_stuff(6, confirm_label);
    }

    private void setPolypanel(int position, String text)
    {
        int number;
        try
        {
            number = Integer.parseInt(text);
        }
        catch (NumberFormatException e){number = collection.getPolynom(position).getLength()-1;}

        if(number < 0 || number > 20)
            number = collection.getPolynom(position).getLength()-1;

        try
        {
            this.exchanable.remove(poly_panel);
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
            if(i <= collection.getPolynom(position).getLength()-1)
                poly_fields[i].setText(String.valueOf(collection.getPolynom(position).getValue(i)));
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

        exchanable.add(poly_panel);
        exchanable.revalidate();
        selected = true;
    }

    private void setPolyarray()
    {
        for (int i = 0; i < poly_array.length; i++)
        {
            try
            {
                poly_array[i] = Double.parseDouble(poly_fields[i].getText());
            }
            catch(NumberFormatException e){poly_array[i] = 0;}
        }
        collection.addPolynom(collection.createPolynom(poly_array));

        System.out.println(collection.getText(collection.getLength()-1));

        confirm_label.setText(collection.getText(collection.getLength()-1));
    }

    private void setPolyarray(int position)
    {
        int sum = 0;
        for (int i = 0; i < poly_array.length; i++)
        {
            try
            {
                poly_array[i] = Double.parseDouble(poly_fields[i].getText());
            }
            catch(NumberFormatException e){poly_array[i] = 0;}
            sum+=Math.abs(poly_array[i]);
        }
        if(sum == 0)
            confirm_label.setText("unglültig");
        else
        {
            collection.deletePolynom(position);

            collection.addPolynom(position, collection.createPolynom(poly_array));

            confirm_label.setText(collection.getText(position));
        }
    }
}
