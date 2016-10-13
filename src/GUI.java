import javax.swing.*;
import java.awt.*;

class GUI extends JFrame
{
    private Collection collection;

    private JPanel action_panel;

    Menu_Bar menu_bar;
    
    GUI(int width, int height)
    {
        collection = new Collection();

        this.setTitle("Working with Polynoms by Miles ;-)");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(new Dimension(width, height));
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(new BorderLayout());

        menu_bar = new Menu_Bar(new String[]{"anzeigen", "hinzufügen", "bearbeiten", "entfernen", "Funktionswert", "Addition", "Subtraktion", "Multiplikation", "Division", "Nullstelle", "Ableitung"}, this);
        JScrollPane menu_scroller = new JScrollPane(menu_bar, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.getContentPane().add(menu_scroller, BorderLayout.LINE_START);
        
        action_panel = new JPanel();
        action_panel.setBackground(Color.WHITE);
        this.getContentPane().add(action_panel, BorderLayout.CENTER);

        disableButtons();
        
        this.setVisible(true);
    }
    

    
    void action(int selection)
    {
        this.getContentPane().remove(action_panel);
        action_panel = new JPanel(new GridLayout(1, 1));

        switch (selection)
        {
            case 0: action_panel.add(new List_List(collection));
                break;
            case 1: action_panel.add(new List_Add(collection, this));
                break;
            case 2: action_panel.add(new List_Edit(collection));
                break;
            case 3: action_panel.add(new List_Delete(collection,this));
                break;
            case 4: action_panel.add(new Math_Value_at_Position(collection));
                break;
            case 5: action_panel.add(new Math_Addition(collection, this));
                break;
            case 6: action_panel.add(new Math_Subtraction(collection, this));
                break;
            case 7: action_panel.add(new Math_Multiplication(collection, this));
                break;
            case 8: action_panel.add(new Math_Division(collection, this));
                break;
            case 9: action_panel.add(new Math_Root(collection));
                break;
            case 10: action_panel.add(new Math_Derivative(collection, this));
                break;
            default: action_panel.add(new List_List(collection));
        }
        this.getContentPane().add(action_panel);
        this.getContentPane().revalidate();
    }

    Collection getCollection()
    {
        return this.collection;
    }

    void enableButtons()
    {
        for (int i = 2; i < menu_bar.getMenus(); i++)
        {
            menu_bar.enableButton(i);
        }
    }

    void disableButtons()
    {
        for (int i = 2; i < menu_bar.getMenus(); i++)
        {
            menu_bar.disableButton(i);
        }
        action(0);
    }

}
