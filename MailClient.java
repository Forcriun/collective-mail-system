/**
 * A class to model a simple email client. The client is run by a
 * particular user, and sends and retrieves mail via a particular server.
 * 
 * @author David J. Barnes and Michael KÃ¶lling
 * @version 2011.07.31
 */
public class MailClient
{
    // El servidor usado para recibir y enviar los mensajes.
    private MailServer server;
    // El usuario que utiliza este cliente.
    private String user;
    // El último mensaje recibido.
    private MailItem ultimoEmail;
    // Total de mensajes recibidos.
    private int recibidos;
    // Total de mensajes enviados.
    private int enviados;
    // El último mensaje más largo recibido por el usuario.
    private MailItem mensajeMasLargo;

    /**
     * Constructor de la clase MailClient.
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
     * Devuelve el siguiente correo electrónico (si lo hay) del usuario. Si el
     * mensaje contiene spam no lo descarga y devuelve null
     * 
     * Implementa F02, F04 y F05
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
     * Descarga e imprime el siguiente correo electrónico (si lo hay) del usuario.
     * Si contiene spam no lo descarga y avisa por pantalla.
     * 
     * Implementa F02, F04 y F05
     */
    public void printNextMailItem()
    {
        MailItem item = server.getNextMailItem(user);
        if(item == null){
            System.out.println("No new mail.");
        }
        else if(item.detectSpam()){
            System.out.println("El mensaje es spam"); // F04
        }
        else{
            ultimoEmail = item; // F02
            item.print();
            // F05
            recibidos++;
            if(item.getMessage().length() >= mensajeMasLargo.getMessage().length()){
                mensajeMasLargo = item;
            }
        }
    }

    /**
     * Envía un mensaje al destinatario indicado a través del servidor adjunto.
     * 
     * @param to El destinatario previsto.
     * @param subject El asunto del mensaje.
     * @param message El texto del mensaje a ser enviado.
     */
    public void sendMailItem(String to,String subject, String message)
    {
        MailItem item = new MailItem(user, to, subject, message);
        server.post(item);
        if(!item.detectSpam()){
            enviados++; // F04
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
     * Método añadido que permite imprimir por pantalla el último mensaje (si lo hay)
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
     * 
     * Considera el spam y el encriptado/desencriptado de mensaje y automensaje.
     */
    public void getDownload(){
        String gracias = "He recibido tu mensaje, gracias\n";
        MailItem item = server.getNextMailItem(user);
        if (item == null){
            System.out.println("No hay ningun mensaje");
        }
        else{
            if(item.detectSpam()){
                System.out.println("El mensaje es spam"); // F04
            }
            else{
                // F06
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
                // F05
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
        MailItem item = new MailItem(user, to, subject, message);
        //Encripta el mensaje
        item.encryptMessage();
        server.post(item);
        if(!item.detectSpam()){
            enviados++;
        }
    }
}
