import javax.swing.*;
import java.awt.*;

public class SplashScreen extends JFrame {
    public SplashScreen() {
        setTitle("Contact Book App");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setUndecorated(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.setBackground(new Color(65, 65, 50));

        JLabel imageLabel = new JLabel();
        ImageIcon icon = new ImageIcon(new ImageIcon("images/contact.png").getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH));
        imageLabel.setIcon(icon);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        panel.add(imageLabel, BorderLayout.NORTH);

        JLabel title = new JLabel("Contact Book App", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 30));
        title.setForeground(Color.WHITE);
        panel.add(title, BorderLayout.CENTER);

        JLabel loading = new JLabel("Please wait...", JLabel.CENTER);
        loading.setFont(new Font("Arial", Font.BOLD, 16));
        loading.setForeground(Color.LIGHT_GRAY);
        panel.add(loading, BorderLayout.SOUTH);

        add(panel);

        new Timer(893000, e -> {
            ((Timer) e.getSource()).stop();
            dispose(); 
            ContactBook app = new ContactBook();
            app.setVisible(true); 
        }).start();

        setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(SplashScreen::new);
    }
}

