import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ContactBookFrame {
    private JFrame frame;
    private ContactListPanel contactListPanel;
    private ContactBookController controller;

    public ContactBookFrame(ContactBookController controller) {
        this.controller = controller;
        
        frame = new JFrame("Contact Book");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLayout(new BorderLayout());

        contactListPanel = new ContactListPanel(controller);
        frame.add(contactListPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public void updateContactList(ArrayList<Contact> contacts) {
        contactListPanel.updateContactList(contacts);
    }
}
