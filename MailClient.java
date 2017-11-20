/**
 * A class to model a simple email client. The client is run by a
 * particular user, and sends and retrieves mail via a particular server.
 * 
 * @author David J. Barnes and Michael KÃ¶lling
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
    // El último mensaje más largo recibido por el usuario.
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
     * Método que muestra por pantalla el número de correos electrónicos que tiene un usuario
     * en el servidor. -- Funcionalidad 01 (Lorena Alonso)
     */
    public void totalMessage()
    {
        System.out.println("Tiene estos mensajes: " + server.howManyMailItems(user));
    }

    /**
     * Método añadido que permite imprimir por pantalla el último mensaje
     * tantas veces como se desee (Funcionalidad 02 - Aitor Martínez)
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
     * Método que cuando se invoque permita descargar del servidor el siguiente mensaje del usuario
     * y responda automáticamente al emisor con una frase indicando que hemos recibido su correo y
     * dándole las gracias. Si no hay ningún mensaje para el usuario el método no hace nada 
     * e informa de la situación por pantalla.(Funcionalidad 03 - Diego Almonte)
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
     * Método que muetra por pantalla el total de mensajes recibidos y enviados por un usuario. 
     * También muestra la dirección de correo del usuario que envió el último mensaje más largo y el
     * número de caracteres del mismo. (Funcionalidad 05 - Aitor Díez)
     */
    public void datosMensajes(){
        System.out.println("Total de mensaje recibidos por "+ user + ": " + recibidos + ".");
        System.out.println("Total de mensaje enviados por "+ user + ": " + enviados + ".");
        if(recibidos > 0){
            System.out.println(mensajeMasLargo.getFrom() + " ha enviado el mensaje más largo, con un total de " +
                mensajeMasLargo.getMessage().length() + " caracteres.");
        }
    }

    /**
     * Método que permite enviar los mensajes encriptados, reemplazando las vocales del texto por
     * símbolos determinados. Todo mensaje encriptado se caracteriza por comenzar por la cadena "?=?"
     * 
     * Funcionalidad 06 - Cristian Martínez
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
