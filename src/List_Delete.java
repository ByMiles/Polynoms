import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

class List_Delete extends JPanel
{
    private Collection collection;
    private JScrollPane scroller;
    private JPanel content;
    private Menu_Button[] poly_buttons;
    private Menu_Label empty_label;

    List_Delete(Collection collection, GUI gui)
    {
        this.collection = collection;
        this.setBackground(Color.DARK_GRAY);
        this.setLayout(new GridLayout(1,1));

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

        if(this.collection.getLength() == 0)
        {
            empty_label = new Menu_Label("Liste leer");
            this.add(empty_label);
        }
        else
        {
            content = new JPanel(new GridLayout(this.collection.getLength(), 1, 3, 3));
            content.setBackground(Color.blue);
            scroller = new JScrollPane(content);
            poly_buttons = new Menu_Button[this.collection.getLength()];

            for (int i = 0; i < this.collection.getLength(); i++)
            {
                poly_buttons[i] = new Menu_Button(collection.getText(i), al);
                    content.add(poly_buttons[i]);
            }

            this.add(scroller);
        }
    }
}
