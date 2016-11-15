package GUI;

import polyAdmin.Collection;
import GUI.list.*;
import GUI.math.*;
import javax.swing.*;
import java.awt.*;

/**
 * @author Miles Lorenz (S0556515) <br>
 * @version 1.0 <br>
 * - provides the frame for the hole programm<br>
 * - includes the Menu_Bar menu_bar, which handles the menu<br>
 * - includes the JPanel action_panel, which handles the actual submenu<br>
 * - creates the Collection collection, which is holder of all polynomials
 * and operations<br>
 * - framewidth and frameheight need to be handed over<br>
 */
public class GUI extends JFrame {
    private Collection collection;
    private JPanel action_panel;
    private Menu_Bar menu_bar;

    /**
     * menu_bar and action_panel becomes initialised<br>
     * frame becomes designed
     * <p>
     * @param width  framewidth
     * @param height frameheight
     */
    public GUI(int width, int height) {
        collection = new Collection();

        this.setTitle("Working with polynomials by Miles ;-)");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setSize(new Dimension(width, height));
        this.setLocationRelativeTo(null);
        this.getContentPane().setLayout(new BorderLayout());

        menu_bar = new Menu_Bar(new String[]{"anzeigen", "hinzufügen", "bearbeiten", "entfernen", "Addition", "Subtraktion", "Multiplikation", "Division", "Funktionswert", "Nullstelle", "Ableitung", "Graph"}, this);
        JScrollPane menu_scroller = new JScrollPane(menu_bar, ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED, ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.getContentPane().add(menu_scroller, BorderLayout.LINE_START);

        action_panel = new JPanel();
        action_panel.setBackground(Color.WHITE);
        this.getContentPane().add(action_panel, BorderLayout.CENTER);

        disableButtons();

        this.setVisible(true);
    }

    /**
     * creates the selected submenu in the action_panel
     *
     * @param selection indicates the selected submenu
     */
    public void action(int selection) {
        this.getContentPane().remove(action_panel);
        action_panel = new JPanel(new GridLayout(1, 1));
        this.add(action_panel);
        this.setVisible(true);

        switch (selection) {
            case 0:
                action_panel.add(new List_List(collection));
                break;
            case 1:
                action_panel.add(new List_Add(collection, this));
                break;
            case 2:
                action_panel.add(new List_Edit(collection));
                break;
            case 3:
                action_panel.add(new List_Delete(collection, this));
                break;
            case 4:
                action_panel.add(new Math_Addition(collection, this));
                break;
            case 5:
                action_panel.add(new Math_Subtraction(collection, this));
                break;
            case 6:
                action_panel.add(new Math_Multiplication(collection, this));
                break;
            case 7:
                action_panel.add(new Math_Division(collection, this));
                break;
            case 8:
                action_panel.add(new Math_Value_at_Position(collection));
                break;
            case 9:
                action_panel.add(new Math_Root(collection));
                break;
            case 10:
                action_panel.add(new Math_Derivative(collection, this));
                break;
            case 11:
                action_panel.add(new Math_Draw(collection, action_panel.getWidth(), action_panel.getHeight()));
                break;
            default:
                action_panel.add(new List_List(collection));
        }
        this.getContentPane().add(action_panel);
        this.getContentPane().revalidate();
        this.setVisible(true);
    }

    /**
     * @return returns the Collection collection
     */
    Collection getCollection() {
        return this.collection;
    }

    /**
     * enables the Menu_Buttons, which needs at least one
     * saved polynomial
     */
    public void enableButtons() {
        for (int i = 2; i < menu_bar.getMenus(); i++) {
            menu_bar.enableButton(i);
        }
    }

    /**
     * disables the Menu_Buttons, which needs at least one
     * saved polynomial
     */
    public void disableButtons() {
        for (int i = 2; i < menu_bar.getMenus(); i++) {
            menu_bar.disableButton(i);
        }
        action(0);
    }
}
