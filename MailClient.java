/**
 * A class to model a simple email client. The client is run by a
 * particular user, and sends and retrieves mail via a particular server.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MailClient
{
    // The server used for sending and receiving.
    private MailServer server;
    // The user running this client.
    private String user;
    // The last received message.
    private MailItem ultimoEmail;

    /**
     * Create a mail client run by user and attached to the given server.
     */
    public MailClient(MailServer server, String user)
    {
        this.server = server;
        this.user = user;
    }

    /**
     * Return the next mail item (if any) for this user.
     */
    public MailItem getNextMailItem()
    {
        MailItem item = server.getNextMailItem(user);
        ultimoEmail = item;
        return item;
    }

    /**
     * Print the next mail item (if any) for this user to the text 
     * terminal.
     */
    public void printNextMailItem()
    {
        MailItem item = server.getNextMailItem(user);
        if(item == null) {
            System.out.println("No new mail.");
        }
        else {
            ultimoEmail = item;
            item.print();
        }
    }

    /**
     * Send the given message to the given recipient via
     * the attached mail server.
     * @param to The intended recipient.
     * @param message The text of the message to be sent.
     */
    public void sendMailItem(String to,String subject, String message)
    {
        MailItem item = new MailItem(user, to, subject, message);
        server.post(item);
    }

    /**
     * M�todo que muestra por pantalla el n�mero de correos electr�nicos que tiene un usuario
     * en el servidor. -- Funcionalidad 01 (Lorena Alonso)
     */
    public void totalMessage()
    {
        System.out.println("Tiene estos mensajes: " + server.howManyMailItems(user));
    }

    /**
     * M�todo a�adido que permite imprimir por pantalla el �ltimo mensaje
     * tantas veces como se desee (Funcionalidad 02 - Aitor Mart�nez)
     */
    public void imprimirUltimoMensaje()
    {
        if(ultimoEmail == null) {
            System.out.println("Error");
        }
        else{ 
            ultimoEmail.print();  
        }
    }

    /**
     * M�todo que cuando se invoque permita descargar del servidor el siguiente mensaje del usuario
     * y responda autom�ticamente al emisor con una frase indicando que hemos recibido su correo y
     * d�ndole las gracias. Si no hay ning�n mensaje para el usuario el m�todo no hace nada 
     * e informa de la situaci�n por pantalla.(Funcionalidad 03 - Diego Almonte)
     */
    public void getDownload()
    {
        MailItem item = server.getNextMailItem(user);
        if (item == null){
            System.out.println("No hay ningun mensaje");
        }
        else
        {
            String gracias = "He recibido tu mensaje, gracias\n" + item.getMessage();
            String asuntoOriginal = "Re: " + item.getSubject();
            sendMailItem(item.getFrom(), asuntoOriginal, gracias);
            ultimoEmail = item;
            item.print();            
        }
    }
}
