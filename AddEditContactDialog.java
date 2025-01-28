import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddEditContactDialog extends JDialog {
    private JTextField nameField;
    private JTextField phoneField;
    private JButton saveButton;
    private Contact contact;
    private ContactBookController controller;

    public AddEditContactDialog(Contact contact, ContactBookController controller) {
        this.controller = controller;
        this.contact = contact;

        setTitle(contact == null ? "Add Contact" : "Edit Contact");
        setSize(300, 200);
        setLayout(new GridLayout(3, 2));

        nameField = new JTextField(contact == null ? "" : contact.getName());
        phoneField = new JTextField(contact == null ? "" : contact.getPhoneNumber());

        saveButton = new JButton("Save");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveContact();
            }
        });

        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Phone Number:"));
        add(phoneField);
        add(new JLabel(""));
        add(saveButton);

        setVisible(true);
    }

    private void saveContact() {
        if (contact == null) {
            contact = new Contact(nameField.getText(), phoneField.getText());
            controller.addContact(contact);
        } else {
            contact.setName(nameField.getText());
            contact.setPhoneNumber(phoneField.getText());
            controller.updateContact(contact);
        }
        dispose();
    }
}
