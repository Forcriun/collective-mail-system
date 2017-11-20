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
    // Total de mensajes recibidos.
    private int recibidos;
    // Total de mensajes enviados.
    private int enviados;
    // El �ltimo mensaje m�s largo recibido por el usuario.
    private MailItem mensajeMasLargo;

    /**
     * Create a mail client run by user and attached to the given server.
     */
    public MailClient(MailServer server, String user)
    {
        this.server = server;
        this.user = user;
        this.recibidos = 0;
        this.enviados = 0;
        this.mensajeMasLargo = new MailItem("","","","");
    }

    /**
     * Return the next mail item (if any) for this user.
     */
    public MailItem getNextMailItem()
    {
        MailItem item = server.getNextMailItem(user);
        if(item == null){
            System.out.println("No new mail.");
        }
        else if(item.detectSpam()){
            item = null;
        }
        else{
            if(item.getMessage().length() >= mensajeMasLargo.getMessage().length()){
                mensajeMasLargo = item;
            }
            ultimoEmail = item;
            recibidos++;
        }
        return item;
    }

    /**
     * Print the next mail item (if any) for this user to the text 
     * terminal.
     */
    public void printNextMailItem()
    {
        MailItem item = server.getNextMailItem(user);
        if(item == null){
            System.out.println("No new mail.");
        }
        else if(item.detectSpam()){
            System.out.println("El mensaje es spam");
        }
        else{
            ultimoEmail = item;
            item.print();
            recibidos++;
            if(item.getMessage().length() >= mensajeMasLargo.getMessage().length()){
                mensajeMasLargo = item;
            }
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
        if(!item.detectSpam()){
            enviados++;
        }
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
    public void getDownload(){
        String gracias = "He recibido tu mensaje, gracias\n ";
        MailItem item = server.getNextMailItem(user);
        if (item == null){
            System.out.println("No hay ningun mensaje");
        }
        else{
            if(item.detectSpam()){
                System.out.println("El mensaje es spam");
            }
            else{
                if(item.getMessage().length() >=3){
                    String message = item.decryptMessage();
                    gracias = gracias + message;
                }
                else{
                    gracias = gracias + item.getMessage();
                }
                String asuntoOriginal = "Re: " + item.getSubject();
                sendMailItem(item.getFrom(), asuntoOriginal, gracias);
                ultimoEmail = item;
                item.print();
                recibidos++;
                if(item.getMessage().length() >= mensajeMasLargo.getMessage().length()){
                    mensajeMasLargo = item;
                }
            }
        }
    }

    /**
     * M�todo que muetra por pantalla el total de mensajes recibidos y enviados por un usuario. 
     * Tambi�n muestra la direcci�n de correo del usuario que envi� el �ltimo mensaje m�s largo y el
     * n�mero de caracteres del mismo. (Funcionalidad 05 - Aitor D�ez)
     */
    public void datosMensajes(){
        System.out.println("Total de mensaje recibidos por "+ user + ": " + recibidos + ".");
        System.out.println("Total de mensaje enviados por "+ user + ": " + enviados + ".");
        if(recibidos > 0){
            System.out.println(mensajeMasLargo.getFrom() + " ha enviado el mensaje m�s largo, con un total de " +
                mensajeMasLargo.getMessage().length() + " caracteres.");
        }
    }

    /**
     * M�todo que permite enviar los mensajes encriptados, reemplazando las vocales del texto por
     * s�mbolos determinados. Todo mensaje encriptado se caracteriza por comenzar por la cadena "?=?"
     * 
     * Funcionalidad 06 - Cristian Mart�nez
     */
    public void sendMailItemEncrypted(String to, String subject, String message){
        //Creates a new email object
        MailItem item = new MailItem(user, to, subject, message);
        //Encrypts the message
        item.encryptMessage();
        //Sends it
        server.post(item);
        if(!item.detectSpam()){
            enviados++;
        }
    }
}
