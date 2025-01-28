import java.util.ArrayList;

public class ContactBookController {
    private ArrayList<Contact> contacts;
    private ContactBookFrame contactBookFrame;

    public ContactBookController() {
        contacts = new ArrayList<>();
        contactBookFrame = new ContactBookFrame(this);
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
        contactBookFrame.updateContactList(contacts);
    }

    public void updateContact(Contact contact) {
        int index = contacts.indexOf(contact);
        if (index != -1) {
            contacts.set(index, contact);
            contactBookFrame.updateContactList(contacts);
        }
    }

    public ArrayList<Contact> getContacts() {
        return contacts;
    }
}
