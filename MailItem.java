/**
 * A class to model a simple mail item. The item has sender and recipient
 * addresses and a message string.
 * 
 * @author David J. Barnes and Michael KÃ¶lling
 * @version 2011.07.31
 */
public class MailItem
{
    // El emisor del mensaje.
    private String from;
    // El receptor del mensaje.
    private String to;
    // El asunto del mensaje.
    private String subject;
    // El texto del mensaje.
    private String message;

    /**
     * Constructor de la claseo MailItem.
     * 
     * @param from El emisor del correo.
     * @param to El receptor del correo.
     * @param subject El asunto del mensaje.
     * @param message El texto del mensaje.
     */
    public MailItem(String from, String to, String subject, String message)
    {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.message = message;
    }

    /**
     * @return Devuelve el emisor.
     */
    public String getFrom()
    {
        return from;
    }

    /**
     * @return Decuelve el destinatario del mensaje.
     */
    public String getTo()
    {
        return to;
    }

    /**
     * @return Devuelve el asunto del mensaje.
     */
    public String getSubject()
    {
        return subject;
    }

    /**
     * @return Devuelve el texto del mensaje.
     */
    public String getMessage()
    {
        return message;
    }

    /**
     * Imprime el mensaje de correo por pantalla.
     * 
     * Implementa encriptación y desencriptación (F06)
     */
    public void print()
    {
        System.out.println("From: " + from);
        System.out.println("To: " + to);
        System.out.println("Subject: " + subject);
        if (message.length() < 3){
            System.out.println("Message: " + message);
        }
        else{
            decryptMessage();
            System.out.println("Message: " + message);
        }
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
        if((message.matches(".*(V|v)(I|i|.*\\<|.*\\#)(A|a|.*\\¡|.*\\$)(G|g)(R|r)(A|a|.*\\¡|.*\\$).*"))||
        (message.matches(".*(R|r)(E|e|.*\\¬|.*\\&)(G|g)(A|a|.*\\¡|.*\\$)(L|l)(O|o|.*\\>|.*\\+).*"))){
            spam = true;
        }
        return spam;
    }

    /**
     * Módulo de ENCRIPTADO -- F06 -- Cristian Martínez
     */
    public void encryptMessage(){
        String vowels[] = {"A", "a", "E", "e", "I", "i", "O", "o", "U", "u"};
        String vowelsEncrypted[] = {"\\¡", "\\$", "\\¬", "\\&", "\\<", "\\#", "\\>", "\\+", "\"", "\\*"};
        for (int i = 0; i < vowels.length; i++){
            message = message.replace(vowels[i], vowelsEncrypted[i]);
        }
        message = "?=? " + message;
    }

    /**
     * Módulo de DESENCRIPTADO -- F06 -- Cristian Martínez
     */
    public String decryptMessage(){
        if (message.substring(0,3).equals("?=?")){
            String vowels[] = {"A", "a", "E", "e", "I", "i", "O", "o", "U", "u"};
            String vowelsEncrypted[] = {"\\¡", "\\$", "\\¬", "\\&", "\\<", "\\#", "\\>", "\\+", "\"", "\\*"};
            for (int i = 0; i < vowels.length; i++){
                message = message.replace(vowelsEncrypted[i], vowels[i]);
            }
        }
        return message;
    }
}
