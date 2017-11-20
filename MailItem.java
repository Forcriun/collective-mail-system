/**
 * A class to model a simple mail item. The item has sender and recipient
 * addresses and a message string.
 * 
 * @author David J. Barnes and Michael KÃ¶lling
 * @version 2011.07.31
 */
public class MailItem
{
    // The sender of the item.
    private String from;
    // The intended recipient.
    private String to;
    // The subject of the message.
    private String subject;
    // The text of the message.
    private String message;

    /**
     * Create a mail item from sender to the given recipient,
     * containing the given message with its corresponding subject.
     * @param from The sender of this item.
     * @param to The intended recipient of this item.
     * @param subject The subject of the message to be sent.
     * @param message The text of the message to be sent.
     */
    public MailItem(String from, String to, String subject, String message)
    {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    /**
     * @return The sender of this message.
     */
    public String getFrom()
    {
        return from;
    }

    /**
     * @return The intended recipient of this message.
     */
    public String getTo()
    {
        return to;
    }
    
    /**
     * @return The subject of this message.
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * @return The text of the message.
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * Print this mail message to the text terminal.
     */
    public void print()
    {
        System.out.println("From: " + from);
        System.out.println("To: " + to);
        System.out.println("Message: " + message);
    }
    
    /**
     * Método que permite detectar la presencia de los términos considerados como spam
     * ("viagra" y "regalo"), en cualquiera de sus posibles variantes, en los mensajes de correo.
     * 
     * Funcionalidad 04 - Dídac Fernández
     */
    public boolean detectSpam()
    {
        boolean spam = false;
        if((message.matches(".*(V|v)(I|i)(A|a|)(G|g)(R|r)(A|a).*"))||
        (message.matches(".*(R|r)(E|e)(G|g)(A|a)(L|l)(O|o).*"))){
            spam = true;
        }
        return spam;
    }
}
