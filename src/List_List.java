import javax.swing.*;
import java.awt.*;

public class List_List extends List_Panel
{

    List_List(Collection collection)
    {
        super(collection, "Gespeicherte Polynome");

        if(collection.getLength() == 0)
        {
            this.add_stuff(1, new Menu_Label("Liste leer"));
        }
        else
        {
            Menu_Label[] poly_labels = new Menu_Label[collection.getLength()];

            for (int i = 0; i < collection.getLength(); i++)
            {
                poly_labels[i] = new Menu_Label(collection.getText(i));
                this.add_stuff(i+1, poly_labels[i]);
            }
        }
    }
}
