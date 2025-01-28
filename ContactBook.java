import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ContactBook {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JTable contactTable;
    private DefaultTableModel tableModel;
    private ArrayList<Contact> contacts;

    public ContactBook() {
        contacts = new ArrayList<>();
        frame = new JFrame("Contact Book");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createHomeScreen(), "Home");
        mainPanel.add(createAddContactScreen(), "AddContact");
        mainPanel.add(createEditContactScreen(), "EditContact");
        mainPanel.add(createSearchScreen(), "Search");

        frame.add(mainPanel);
        frame.setVisible(true);
    }

    private JPanel createHomeScreen() {
        JPanel homePanel = new JPanel(new BorderLayout());
    
        JPanel titleBar = new JPanel(new BorderLayout());
        titleBar.setBackground(new Color(0, 102, 204));  
        titleBar.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));  
    
        JLabel titleLabel = new JLabel("Contact Book App", SwingConstants.LEFT);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE); 
        titleBar.add(titleLabel, BorderLayout.WEST);
    
        ImageIcon icon = new ImageIcon("images/cont.png"); 
        Image img = icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH); 
        icon = new ImageIcon(img);
        JLabel imageLabel = new JLabel(icon);
        titleBar.add(imageLabel, BorderLayout.EAST);
    
        homePanel.add(titleBar, BorderLayout.NORTH);
    
        JLabel noContactsLabel = new JLabel("No contacts available.", SwingConstants.CENTER);
        noContactsLabel.setForeground(Color.RED);
        noContactsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
    
        tableModel = new DefaultTableModel(new String[]{"Name", "Phone", "Email"}, 0);
        contactTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(contactTable);
    
        if (contacts.isEmpty()) {
            homePanel.add(noContactsLabel, BorderLayout.CENTER);
        } else {
            homePanel.add(scrollPane, BorderLayout.CENTER);
        }
    
        JPanel buttonPanel = new JPanel();
        
        JButton addButton = createStyledButton("Add Contact");
        JButton editButton = createStyledButton("Edit Contact");
        JButton searchButton = createStyledButton("Search Contact");
    
        addButton.addActionListener(e -> cardLayout.show(mainPanel, "AddContact"));
        editButton.addActionListener(e -> openEditContact());
        searchButton.addActionListener(e -> cardLayout.show(mainPanel, "Search"));
    
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(searchButton);
    
        homePanel.add(buttonPanel, BorderLayout.SOUTH);
    
        return homePanel;
    }
    
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.PLAIN, 16));
        button.setBackground(new Color(0, 150, 136)); 
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); 

        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 120, 100)); 
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(new Color(0, 150, 136));
            }
        });

        return button;
    }

    


    private JPanel createAddContactScreen() {
        JPanel addPanel = new JPanel();
        addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS)); 
    
        JLabel titleLabel = new JLabel("Add New Contact");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleLabel.setForeground(new Color(0, 102, 204));  
        addPanel.add(titleLabel);
        addPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
    
        JPanel namePanel = createFieldPanel("Name:");
        JTextField nameField = (JTextField) namePanel.getComponent(1);
        addPanel.add(namePanel);
    
        JPanel phonePanel = createFieldPanel("Phone:");
        JTextField phoneField = (JTextField) phonePanel.getComponent(1);
        addPanel.add(phonePanel);
    
        JPanel emailPanel = createFieldPanel("Email:");
        JTextField emailField = (JTextField) emailPanel.getComponent(1);
        addPanel.add(emailPanel);
    
        addPanel.add(Box.createRigidArea(new Dimension(0, 20))); 
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10)); 
        JButton saveButton = createStyledButton("Save");
        JButton backButton = createStyledButton("Back");
    
        saveButton.addActionListener(e -> {
            String name = nameField.getText();
            String phone = phoneField.getText();
            String email = emailField.getText();
    
            if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                contacts.add(new Contact(name, phone, email));
                tableModel.addRow(new Object[]{name, phone, email});
                nameField.setText("");
                phoneField.setText("");
                emailField.setText("");
                cardLayout.show(mainPanel, "Home");
            } else {
                JOptionPane.showMessageDialog(frame, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    
        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));
    
        buttonPanel.add(saveButton);
        buttonPanel.add(backButton);
    
        addPanel.add(buttonPanel);
    
        return addPanel;
    }
    
    private JPanel createFieldPanel(String labelText) {
        JPanel fieldPanel = new JPanel();
        fieldPanel.setLayout(new FlowLayout(FlowLayout.LEFT)); 
    
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        label.setPreferredSize(new Dimension(100, 30)); 
        label.setForeground(new Color(0, 102, 204));  
    
        JTextField textField = new JTextField(20);  
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        textField.setPreferredSize(new Dimension(250, 30)); 
        textField.setBorder(BorderFactory.createLineBorder(new Color(0, 102, 204), 2));  
    
        fieldPanel.add(label);
        fieldPanel.add(textField);
    
        return fieldPanel;
    }
    
   
    private JPanel createEditContactScreen() {
        JPanel editPanel = new JPanel(new GridLayout(5, 2, 10, 10));

        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone:");
        JTextField phoneField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JButton saveButton = new JButton("Save");
        JButton deleteButton = new JButton("Delete");
        JButton backButton = new JButton("Back");

        saveButton.addActionListener(e -> {
            int selectedRow = contactTable.getSelectedRow();
            if (selectedRow != -1) {
                String name = nameField.getText();
                String phone = phoneField.getText();
                String email = emailField.getText();

                if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                    contacts.set(selectedRow, new Contact(name, phone, email));
                    tableModel.setValueAt(name, selectedRow, 0);
                    tableModel.setValueAt(phone, selectedRow, 1);
                    tableModel.setValueAt(email, selectedRow, 2);
                    cardLayout.show(mainPanel, "Home");
                } else {
                    JOptionPane.showMessageDialog(frame, "Please fill all fields.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        deleteButton.addActionListener(e -> {
            int selectedRow = contactTable.getSelectedRow();
            if (selectedRow != -1) {
                contacts.remove(selectedRow);
                tableModel.removeRow(selectedRow);
                cardLayout.show(mainPanel, "Home");
            }
        });

        backButton.addActionListener(e -> cardLayout.show(mainPanel, "Home"));

        editPanel.add(nameLabel);
        editPanel.add(nameField);
        editPanel.add(phoneLabel);
        editPanel.add(phoneField);
        editPanel.add(emailLabel);
        editPanel.add(emailField);
        editPanel.add(saveButton);
        editPanel.add(deleteButton);
        editPanel.add(backButton);

        return editPanel;
    }

    private JPanel createSearchScreen() {
        JPanel searchPanel = new JPanel(new BorderLayout());

        JTextField searchField = new JTextField();
        JButton searchButton = new JButton("Search");

        searchButton.addActionListener(e -> {
            String query = searchField.getText().toLowerCase();
            tableModel.setRowCount(0);

            for (Contact contact : contacts) {
                if (contact.getName().toLowerCase().contains(query) || contact.getPhone().contains(query)) {
                    tableModel.addRow(new Object[] { contact.getName(), contact.getPhone(), contact.getEmail() });
                }
            }
        });

        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> {
            searchField.setText("");
            refreshContactTable();
            cardLayout.show(mainPanel, "Home");
        });

        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(searchField, BorderLayout.CENTER);
        topPanel.add(searchButton, BorderLayout.EAST);

        searchPanel.add(topPanel, BorderLayout.NORTH);
        searchPanel.add(new JScrollPane(contactTable), BorderLayout.CENTER);
        searchPanel.add(backButton, BorderLayout.SOUTH);

        return searchPanel;
    }

    private void openEditContact() {
        int selectedRow = contactTable.getSelectedRow();
        if (selectedRow != -1) {
            Contact contact = contacts.get(selectedRow);
            JTextField nameField = (JTextField) ((JPanel) mainPanel.getComponent(2)).getComponent(1);
            JTextField phoneField = (JTextField) ((JPanel) mainPanel.getComponent(2)).getComponent(3);
            JTextField emailField = (JTextField) ((JPanel) mainPanel.getComponent(2)).getComponent(5);

            nameField.setText(contact.getName());
            phoneField.setText(contact.getPhone());
            emailField.setText(contact.getEmail());
            cardLayout.show(mainPanel, "EditContact");
        } else {
            JOptionPane.showMessageDialog(frame, "Please select a contact to edit.", "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    private void refreshContactTable() {
        tableModel.setRowCount(0);
        for (Contact contact : contacts) {
            tableModel.addRow(new Object[] { contact.getName(), contact.getPhone(), contact.getEmail() });
        }
    }

    public static void main(String[] args) {
        new ContactBook();
    }

    private static class Contact {
        private String name;
        private String phone;
        private String email;

        public Contact(String name, String phone, String email) {
            this.name = name;
            this.phone = phone;
            this.email = email;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }
    }

    public void setVisible(boolean b) {
    }
}
