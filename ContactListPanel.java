import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ContactListPanel extends JPanel {
    private DefaultListModel<String> contactListModel;
    private JList<String> contactList;
    private ContactBookController controller;

    public ContactListPanel(ContactBookController controller) {
        this.controller = controller;

        setLayout(new BorderLayout());
        
        contactListModel = new DefaultListModel<>();
        contactList = new JList<>(contactListModel);
        JScrollPane scrollPane = new JScrollPane(contactList);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Contact");
        addButton.addActionListener(e -> openAddContactDialog());
        buttonPanel.add(addButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    public void updateContactList(ArrayList<Contact> contacts) {
        contactListModel.clear();
        for (Contact contact : contacts) {
            contactListModel.addElement(contact.getName());
        }
    }

    private void openAddContactDialog() {
        new AddEditContactDialog(null, controller);
    }
}
