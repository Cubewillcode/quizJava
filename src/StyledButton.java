import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;

public class StyledButton extends JButton {
    public StyledButton(String buttonText, String actionCommand, ActionListener actionListener) {
        super(buttonText);
        setFont(new Font("Berlin Sans FB Demi", Font.BOLD, 24));
        setBackground(Color.decode("#003366"));
        setForeground(Color.WHITE);
        setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.decode("#003366")),
                BorderFactory.createEmptyBorder(0, 0, 0, 0)
        ));
        setBorder(BorderFactory.createCompoundBorder(
                getBorder(),
                BorderFactory.createMatteBorder(3, 3, 10, 10, Color.decode("#000066"))
        ));
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                setBackground(Color.decode("#3366ff"));
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                setBackground(Color.decode("#003366"));
            }
        });
        setActionCommand(actionCommand);
        addActionListener(actionListener);
    }
}
