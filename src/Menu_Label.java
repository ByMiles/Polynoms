import javax.swing.*;
import java.awt.*;

class Menu_Label extends JLabel
{
    Menu_Label(String text)
    {
        this.setText(text);
        this.setHorizontalAlignment(JLabel.CENTER);
        this.setVerticalAlignment(JLabel.CENTER);
        this.setFont(new Font("Serif", Font.BOLD, 24));
    }
}
