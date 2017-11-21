
/**
 * Clase que realiza tests modulares de cada una de las funcionalidades
 * del proyecto collective-mail-system.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Test
{
    /**
     * Constructor de objetos de la clase Test.
     */
    public Test()
    {
    }

    /**
     * Test de la Funcionalidad 01.
     */
    public void testFuncionalidad1()
    {
        /**
         * Creamos los objetos. Comprobamos que funciona en su estado inicial.
         */
        MailServer Gmail = new MailServer();
        System.out.println("Crea un servidor de nombre Gmail");
        MailClient User1 = new MailClient(Gmail, "pepe@gmail.com");
        System.out.println("Crea un cliente de correo User1(Gmail, \"pepe@gmail.com\")");
        System.out.print("Se espera que tenga 0 mensajes.  -->  ");
        if(Gmail.howManyMailItems("pepe@gmail.com") != 0){
            System.out.print("¡¡¡Error!!! Existen mensajes en el servidor");
        }
        else{
            User1.totalMessage();
            System.out.println("¡¡¡Funciona correctamente!!!");
        }
        MailClient User2 = new MailClient(Gmail, "ana@gmail.com");
        System.out.println("Crea un cliente de correo User2(Gmail, \"ana@gmail.com\")");
        System.out.print("Se espera que tenga 0 mensajes.  -->  ");
        if(Gmail.howManyMailItems("ana@gmail.com") != 0){
            System.out.print("¡¡¡Error!!! Existen mensajes en el servidor");
        }
        else{
            User2.totalMessage();
            System.out.println("¡¡¡Funciona correctamente!!!");
        }
        /**
         * Implementamos los casos de prueba.
         */        
        System.out.println("User1 envía un mensaje a User2");
        User1.sendMailItem("ana@gmail.com", "email1", "Texto");
        System.out.print("Se espera que tenga 1 mensaje.  -->  ");
        if(Gmail.howManyMailItems("ana@gmail.com") > 1){
            System.out.print("¡¡¡Error!!!  -->  El servidor muestra más mensajes de los esperados.");
        }
        else if(Gmail.howManyMailItems("ana@gmail.com") < 1){
            System.out.print("¡¡¡Error!!!  -->  El mensaje no se ha enviado.");
        }
        else{
            User2.totalMessage();
            System.out.println("¡¡¡Funciona correctamente!!!");
        }
        System.out.println("User2 envía 3 mensajes (normal, spam y encriptado) a User1");
        User2.sendMailItem("pepe@gmail.com", "email2", "texto");
        User2.sendMailItem("pepe@gmail.com", "email3", "viaGra");
        User2.sendMailItemEncrypted("pepe@gmail.com", "email4", "regalO");
        System.out.print("Se espera que tenga 3 mensajes.  -->  ");
        if(Gmail.howManyMailItems("pepe@gmail.com") != 3){
            System.out.print("¡¡¡Error!!!  -->  El servidor muestra un número inesperado de mensajes.");
        }
        else{
            User1.totalMessage();
            System.out.println("¡¡¡Funciona correctamente!!!");
        }
    }

    /**
     * Test de la Funcionalidad 02.
     */
    public void testFuncionalidad2()
    {
        /**
         * Creamos el entorno de testeo, los objetos. Comprobamos la funcionalidad en el comienzo
         * de la vida de los objetos, nada más ser creados.
         */
        MailServer Gmail = new MailServer();
        System.out.println("Crea un servidor de nombre Gmail");
        System.out.print("\n");
        MailClient User1 = new MailClient(Gmail, "pepe@gmail.com");
        System.out.println("Crea un cliente de correo User1(Gmail, \"pepe@gmail.com\")");
        System.out.print("\"pepe@gmail.com\" ");
        User1.totalMessage();
        System.out.println("Testea la funcionalidad del método cuando no hay mensajes.");
        System.out.print("Se espera que muestre error por pantalla. -->  ");
        User1.imprimirUltimoMensaje();
        System.out.print("\n");
        MailClient User2 = new MailClient(Gmail, "ana@gmail.com");
        System.out.println("Crea un cliente de correo User2(Gmail, \"ana@gmail.com\")");
        System.out.print("\"ana@gmail.com\" ");
        User2.totalMessage();
        System.out.println("Testea la funcionalidad del método cuando no hay mensajes.");
        System.out.print("Se espera que muestre error por pantalla. -->  ");
        User2.imprimirUltimoMensaje();
        System.out.print("\n");
        MailClient User3 = new MailClient(Gmail, "juan@gmail.com");
        System.out.println("Crea un cliente de correo User3(Gmail, \"juan@gmail.com\")");
        System.out.print("\"juan@gmail.com\" ");
        User3.totalMessage();
        System.out.println("Testea la funcionalidad del método cuando no hay mensajes.");
        System.out.print("Se espera que muestre error por pantalla. -->  ");
        User3.imprimirUltimoMensaje();
        System.out.print("\n");
        /**
         * Implementamos los casos de prueba. Vamos a probar la implementación los tres métodos
         * de descarga de correos, de manera alterna, abarcando todas las funcionalidades del
         * proyecto.
         * 
         * 1.- Envío de mensaje encriptado, descargado con método printNextMailItem.
         * 2.- Envío de mensaje convencional, descargado con método getDownload y check de autoenvío.
         * 3.- Envío de mensaje con spam, descargado con método getNextMailItem.
         */
        System.out.println("User1 envía un mensaje encriptado a User2");
        User1.sendMailItemEncrypted("ana@gmail.com", "email1(encriptado)", "Hola");
        System.out.println("User2 lo descarga con printNextMailItem()");
        System.out.print("\n");
        User2.printNextMailItem();
        System.out.print("\n");
        System.out.println("Comprueba que se puede imprimir el último mensaje a placer:");
        System.out.println("--1--");
        User2.imprimirUltimoMensaje();
        System.out.println("--2--");
        User2.imprimirUltimoMensaje();
        System.out.println("--3--");
        User2.imprimirUltimoMensaje();
        System.out.println("--...n--");
        System.out.print("\n");
        System.out.println("User1 envía un mensaje normal a User2");
        User1.sendMailItem("ana@gmail.com", "email2", "Hola");
        System.out.println("User2 lo descarga con getDownload()");
        System.out.print("\n");
        User2.getDownload();
        System.out.print("\n");
        System.out.println("User1 todavía no ha descargado el automensaje de User2");
        System.out.print("Se espera que muestre error por pantalla. -->  ");
        User1.imprimirUltimoMensaje();
        System.out.println("User1 descarga el automensaje generado por el getDownload de User2");
        System.out.print("\n");
        User1.printNextMailItem();
        System.out.print("\n");
        System.out.println("Comprueba que pasa a ser el último mensaje, y puede imprimirse a placer:");
        System.out.println("--1--");
        User1.imprimirUltimoMensaje();
        System.out.println("--2--");
        User1.imprimirUltimoMensaje();
        System.out.println("--3--");
        User1.imprimirUltimoMensaje();
        System.out.println("--...n--");
        System.out.print("\n");
        System.out.println("User3 envía un mensaje de spam a User2");
        User3.sendMailItem("ana@gmail.com", "email3(spam)", "¡Jajaja, regaLOs para todos!");
        System.out.println("User2 lo descarga con getNextMailItem()");
        // Comprobación local del spam
        MailItem item = Gmail.getNextMailItem("ana@gmail.com");
        boolean spam = item.detectSpam();
        if(spam){
            System.out.println("El mensaje es spam");
        }
        System.out.print("\n");
        System.out.println("Comprueba que no se ha descargado el spam y el último correo no varía:");
        User2.imprimirUltimoMensaje();
    }

    /**
     * Test de la Funcionalidad 03.
     */
    public void testFuncionalidad3()
    {
        /**
         * Creamos el entorno del servidor y nos aseguramos del correcto funcionamiento en el
         * actual estado de vida de los objetos.
         */
        MailServer Gmail = new MailServer();
        System.out.println("Crea un servidor de nombre Gmail");
        System.out.print("\n");
        MailClient User1 = new MailClient(Gmail, "pepe@gmail.com");
        System.out.println("Crea un cliente de correo User1(Gmail, \"pepe@gmail.com\")");
        System.out.print("\"pepe@gmail.com\" ");
        User1.totalMessage();
        System.out.println("Testea la funcionalidad del método cuando no hay mensajes.");
        System.out.print("Debe avisar de la falta de mensajes en el servidor -->  ");
        User1.getDownload();
        System.out.print("\n");
        MailClient User2 = new MailClient(Gmail, "ana@gmail.com");
        System.out.println("Crea un cliente de correo User2(Gmail, \"ana@gmail.com\")");
        System.out.print("\"ana@gmail.com\" ");
        User2.totalMessage();
        System.out.println("Testea la funcionalidad del método cuando no hay mensajes.");
        System.out.print("Debe avisar de la falta de mensajes en el servidor -->  ");
        User2.getDownload();
        System.out.print("\n");
        MailClient User3 = new MailClient(Gmail, "juan@gmail.com");
        System.out.println("Crea un cliente de correo User3(Gmail, \"juan@gmail.com\")");
        System.out.print("\"juan@gmail.com\" ");
        User3.totalMessage();
        System.out.println("Testea la funcionalidad del método cuando no hay mensajes.");
        System.out.print("Debe avisar de la falta de mensajes en el servidor -->  ");
        User3.getDownload();
        System.out.print("\n");        
        /**
         * Implementamos los casos de prueba. Testearemos el funcionamiento ante los tres tipos de
         * mensajes de correo que se pueden enviar a través de los clientes del servidor.
         * 
         * 1.- Envío y descarga de mensaje normal.
         * 2.- Envío de mensaje normal, comprobando un bucle de automensajes entre clientes (nos 
         * ayudamos de printNextMailItem() para finalizar el muestreo).
         * 3.- Envío de mensaje encriptado, comprobación visual de automensaje devuelto.
         * 4.- Respuesta ante spam.
         */
        System.out.println("User1 envía un mensaje normal a User2");
        User1.sendMailItem("ana@gmail.com", "email1(normal)", "Hola Ana, soy Pepe.");
        System.out.println("User2 descarga el mensaje:");
        System.out.print("\n");
        User2.getDownload();
        System.out.print("\n");
        System.out.println("Comprueba el funcionamiento del automensaje de User2 a User1");
        User1.totalMessage();
        System.out.print("\n");
        System.out.println("Lo imprime:");
        System.out.print("\n");
        User1.printNextMailItem();
        System.out.print("\n");
        System.out.println("User2 envía un mensaje normal a User3");
        User2.sendMailItem("juan@gmail.com", "email2(bucle)", "Hola Juan, mi nombre es Ana");
        System.out.println("Comprueba el funcionamiento iterativo del método entre los usuarios");
        System.out.print("\n");
        System.out.println("User3 devuelve el automensaje a User2");
        User3.getDownload();
        System.out.print("\n");
        System.out.println("User2 lo devuelve una vez más User3");
        User2.getDownload();
        System.out.print("\n");
        System.out.println("User3 lo devuelve por última vez");
        User3.getDownload();
        System.out.print("\n");
        System.out.println("User2 imprime el que es ahora su último mensaje (resultado del bucle)");
        User2.printNextMailItem();
        System.out.print("\n");
        System.out.println("Muestra los mensajes en el servidor de ambos usuarios:");
        System.out.print("Se espera que tenga 0 mensajes.  -->  ");
        if(Gmail.howManyMailItems("ana@gmail.com") != 0){
            System.out.print("¡¡¡Error!!! Existen mensajes en el servidor");
        }
        else{
            User1.totalMessage();
            System.out.println("¡¡¡Test superado!!!");
        }        
        System.out.print("Se espera que tenga 0 mensajes.  -->  ");
        if(Gmail.howManyMailItems("juan@gmail.com") != 0){
            System.out.print("¡¡¡Error!!! Existen mensajes en el servidor");
        }
        else{
            User1.totalMessage();
            System.out.println("¡¡¡Test superado!!!");
        }
        System.out.print("\n");
        System.out.println("User1 envía un mensaje encriptado a User2");
        User1.sendMailItemEncrypted("ana@gmail.com", "email3(encriptado)", "Hola Ana, puede que sea Pepe");
        System.out.println("User2 descarga el mensaje:");
        System.out.print("\n");
        User2.getDownload();
        System.out.print("\n");
        System.out.println("Comprueba el automensaje de User2 a User1:");
        System.out.print("\n");
        User1.printNextMailItem();
        System.out.print("\n");
        System.out.println("User1 envía un correo conteniendo spam a User2:");
        User1.sendMailItem("ana@gmail.com", "email4(spam)", "Hola Ana, tengo un regalo para ti");
        System.out.print("\n");
        System.out.println("Debe mostrar aviso de spam y no hacer nada más:");
        // Comprobación local del spam
        MailItem item = Gmail.getNextMailItem("ana@gmail.com");
        boolean spam = item.detectSpam();
        if(spam){
            System.out.println("El mensaje es spam --> ¡¡¡Test Superado!!!");
        }
        else if(!spam){
            System.out.println("El mensaje es no contiene spam --> ¡¡¡Test FALLIDO!!!");
        }
        else{
            System.out.println("ERROR --> ¡¡¡Test FALLIDO!!!");
        }
        System.out.print("\n");
        System.out.println("Comprueba que User1 no tiene el spam en el servidor:");
        System.out.print("Se espera que tenga 0 mensajes.  -->  ");
        if(Gmail.howManyMailItems("pepe@gmail.com") != 0){
            System.out.print("¡¡¡Error!!! Existen mensajes en el servidor");
        }
        else{
            User1.totalMessage();
            System.out.println("¡¡¡Test superado!!!");
        }
    }

    /**
     * Test de la Funcionalidad 04.
     */
    public void testFuncionalidad4()
    {
        /**
         * Creamos los objetos servidor y cliente que tomarán parte en el test.
         */
        MailServer Gmail = new MailServer();
        System.out.println("Crea un servidor de nombre Gmail");
        System.out.print("\n");
        MailClient User1 = new MailClient(Gmail, "pepe@gmail.com");
        System.out.println("Crea un cliente de correo User1(Gmail, \"pepe@gmail.com\")");
        System.out.print("\"pepe@gmail.com\" ");
        User1.totalMessage();
        System.out.print("\n");
        MailClient User2 = new MailClient(Gmail, "ana@gmail.com");
        System.out.println("Crea un cliente de correo User2(Gmail, \"ana@gmail.com\")");
        System.out.print("\"ana@gmail.com\" ");
        User2.totalMessage();
        System.out.print("\n");
        /**
         * Implementamos los casos de prueba. Probaremos la implementación del spam en el resto de
         * funcionalidades. Utilizaremos todos los métodos de envío y descarga de correos para
         * facilitar el muestreo de datos por pantalla.
         * 
         * 1.- Envío de mensaje con spam, respuesta del método getNextMailItem.
         * 2.- Envío de mensaje con spam, respuesta del método printNextMailItem.
         * 3.- Envío de mensaje encriptado, respuesta del método getDownload.
         * 4.- Comprobamos distintas variaciones del spam que puede aparecer en los mensajes de correo.
         */        
        System.out.print("User1 envía un correo con spam a User2.");
        User1.sendMailItem("ana@gmail.com", "email1", "Hola Ana, tengo un regalo para ti");
        System.out.println("User2 lo descarga con getNextMailItem.");
        System.out.print("Se espera que muestre NULL por pantalla.  -->  ");
        // Simulación local de getNextMailItem()
        MailItem item = Gmail.getNextMailItem("ana@gmail.com");
        if(item == null){
            System.out.println("¡¡¡ERROR!!! -- No hay mensajes que mostrar.");
        }
        else if(item.detectSpam()){
            System.out.println("NULL  --  El mensaje contiene spam.");
            System.out.println("¡¡¡Funciona correctamente!!!");
        }
        else{
            System.out.println("¡¡¡ERROR!!!  --  El mensaje no contiene spam");
        }
        System.out.println("Comprueba que no ha descargado el mensaje desde el servidor:");
        System.out.print("Se espera que tenga 0 mensajes.  -->  ");
        if(Gmail.howManyMailItems("ana@gmail.com") != 0){
            System.out.print("¡¡¡Error!!! Existen mensajes en el servidor. Fallo inesperado.");
        }
        else{
            User2.totalMessage();
            System.out.println("¡¡¡Funciona correctamente!!!");
        }
        System.out.print("\n");
        System.out.println("User1 envía un correo con spam a User2.");
        User1.sendMailItem("ana@gmail.com", "email2", "Pincha en cualquier lugar del documento para conseguir viagra gratis");
        System.out.println("User2 lo descarga con printNextMailItem.");
        System.out.print("Se espera que avise por pantalla si contiene spam.  -->  ");
        // Simulación local de printNextMailItem()
        MailItem item2 = Gmail.getNextMailItem("ana@gmail.com");
        if(item == null){
            System.out.println("¡¡¡ERROR!!! -- No hay mensajes que mostrar.");
        }
        else if(item.detectSpam()){
            System.out.println("El mensaje contiene spam");
            System.out.println("¡¡¡Funciona correctamente!!!");
        }
        else {
            System.out.println("¡¡¡Error!!! Existen mensajes en el servidor. Fallo inesperado.");
        }
        System.out.println("Comprueba que no ha descargado el mensaje desde el servidor:");
        System.out.print("Se espera que tenga 0 mensajes.  -->  ");
        if(Gmail.howManyMailItems("ana@gmail.com") != 0){
            System.out.print("¡¡¡Error!!! Existen mensajes en el servidor. Fallo inesperado.");
        }
        else{
            User2.totalMessage();
            System.out.println("¡¡¡Funciona correctamente!!!");
        }
        System.out.print("\n");
        System.out.println("User1 envía un correo encriptado con spam a User2.");
        User1.sendMailItemEncrypted("ana@gmail.com", "email3(spam encriptado)", "¡Regalos para todos!");
        System.out.println("User2 lo descarga con getDownload.");
        System.out.print("Se espera que avise por pantalla si contiene spam.  -->  ");
        // Simulación local de getDownload()
        MailItem item3 = Gmail.getNextMailItem("ana@gmail.com");
        if (item == null){
            System.out.println("¡¡¡ERROR!!! -- No hay mensajes que mostrar.");
        }
        else if(item.detectSpam()){
            System.out.println("El mensaje contiene spam");
            System.out.println("¡¡¡Funciona correctamente!!!");
        }
        else{            
            System.out.print("¡¡¡Error!!! Existen mensajes en el servidor. Fallo inesperado.");
        }        
        System.out.println("Comprueba que no ha descargado el mensaje desde el servidor:");
        System.out.print("Se espera que tenga 0 mensajes.  -->  ");
        User2.totalMessage();
        if(Gmail.howManyMailItems("ana@gmail.com") != 0){
            System.out.print("¡¡¡Error!!! Existen mensajes en el servidor. Fallo inesperado.");
        }
        else{
            User2.totalMessage();
            System.out.println("¡¡¡Funciona correctamente!!!");
        }
        System.out.print("\n");
        System.out.println("User2 envía varios correos con distintas variaciones del contenido considerado como spam a User1.");
        User2.sendMailItem("pepe@gmail.com", "Var1", "¿Problemas en tu relación? Compra viaGRa.");
        User2.sendMailItem("pepe@gmail.com", "Var2", "Te RegALo lo que quieras si haces click en mi nombre de usuario.");
        User2.sendMailItem("pepe@gmail.com", "Var3", "VIAGRA para ti, REGALO para ella");
        System.out.println("User1 los descarga con printNextMailItem.");
        System.out.println("--1--");
        User1.printNextMailItem();
        System.out.println("--2--");
        User1.printNextMailItem();
        System.out.println("--3--");
        User1.printNextMailItem();
        System.out.println("--...n--");
        System.out.println("Comprueba que no se ha considerado ningún correo que contuviera spam:");
        User2.datosMensajes();
        User1.datosMensajes();
    }

    /**
     * Test de la Funcionalidad 05.
     */
    public void testFuncionalidad5()
    {
        /**
         * Creamos los objetos servidor y cliente que tomarán parte en el test.
         */
        // Declaración local de variables necesarias para el test.        
        int recibidosPepe = 0;
        int enviadosPepe = 0;        
        int recibidosAna = 0;
        int enviadosAna = 0;

        MailServer Gmail = new MailServer();
        System.out.println("Crea un servidor de nombre Gmail");
        System.out.print("\n");
        MailClient User1 = new MailClient(Gmail, "pepe@gmail.com");
        System.out.println("Crea un cliente de correo User1(Gmail, \"pepe@gmail.com\")");
        System.out.println("Se espera 0 recibidos y 0 enviados:");
        if(recibidosPepe == 0){
            System.out.println("Total de mensaje recibidos por \"pepe@gmail.com\"" + ": " + recibidosPepe + ".");
            System.out.println("¡¡¡Funciona correctamente!!!");
        }
        else{
            System.out.print("¡¡¡Error!!! Existen mensajes en el servidor. Fallo inesperado.");
        }
        if(enviadosPepe == 0){
            System.out.println("Total de mensaje enviados por \"pepe@gmail.com\"" + ": " + enviadosPepe + ".");
            System.out.println("¡¡¡Funciona correctamente!!!");
        }
        else{
            System.out.print("¡¡¡Error!!! Existen mensajes en el servidor. Fallo inesperado.");
        }
        System.out.print("\n");
        MailClient User2 = new MailClient(Gmail, "ana@gmail.com");
        System.out.println("Crea un cliente de correo User2(Gmail, \"ana@gmail.com\")");
        System.out.println("Se espera 0 recibidos y 0 enviados:");
        if(recibidosAna == 0){
            System.out.println("Total de mensaje recibidos por \"ana@gmail.com\"" + ": " + recibidosAna + ".");
            System.out.println("¡¡¡Funciona correctamente!!!");
        }
        else{
            System.out.print("¡¡¡Error!!! Existen mensajes en el servidor. Fallo inesperado.");
        }
        if(enviadosAna == 0){
            System.out.println("Total de mensaje enviados por \"ana@gmail.com\"" + ": " + enviadosAna + ".");
            System.out.println("¡¡¡Funciona correctamente!!!");
        }
        else{
            System.out.print("¡¡¡Error!!! Existen mensajes en el servidor. Fallo inesperado.");
        }
        System.out.print("\n");
        /**
         * Implementamos los casos de prueba.
         * 
         * 1.- Enviamos en el siguiente orden: mensaje normal, normal con spam, encriptado, 
         * encriptado con spam.
         * 2.- Descargamos en el siguiente orden: getNextMailItem, printNextMailItem, getDownloadx2.
         * 3.- Comprobamos la información de bandeja de ambos usuarios.
         */        
        System.out.println("User1 envía varios correos a User2 (normal, normal+spam, encriptado, encriptado+spam).");
        User1.sendMailItem("ana@gmail.com", "email1", "hola");
        enviadosPepe++;
        User1.sendMailItem("ana@gmail.com", "email2(spam)", "REGALO");
        User1.sendMailItemEncrypted("ana@gmail.com", "email3(encriptado)", "Hola");
        enviadosPepe++;
        User1.sendMailItemEncrypted("ana@gmail.com", "email4(encriptado+spam)", "REGALO");
        System.out.print("\n");
        System.out.println("User2 descarga en orden:getNextMailItem, printNextMailItem, getDownloadx2.");
        System.out.print("\n");
        System.out.println("--1-- getNextMailItem");
        // Simulación local de getNextMailItem()
        MailItem item = Gmail.getNextMailItem("ana@gmail.com");
        if(item == null){
            System.out.println("¡¡¡ERROR!!! -- No hay mensajes que mostrar.");
        }
        else if(item.detectSpam()){
            System.out.println("NULL  --  El mensaje contiene spam.");
        }
        else{
            System.out.println(item.getMessage());
            recibidosAna++;
        }
        System.out.print("\n");
        System.out.println("--2-- printNextMailItem");
        User2.printNextMailItem();
        System.out.print("\n");
        System.out.println("--3-- getDownload");
        User2.getDownload();
        recibidosAna++;
        enviadosAna++;
        System.out.print("\n");
        System.out.println("User1 descarga el automensaje de User2");
        User1.printNextMailItem();
        recibidosPepe++;
        System.out.print("\n");
        System.out.println("--4-- getDownload");
        User2.getDownload();
        System.out.print("\n");
        System.out.println("Se esperan 2 recibidos, 1 enviado, mensaje largo de User1 de 8 caracteres:");
        if(recibidosAna == 2){
            System.out.println("Total de mensaje recibidos por \"ana@gmail.com\"" + ": " + recibidosAna + ".");
            System.out.println("¡¡¡Funciona correctamente!!!");
        }
        else{
            System.out.print("¡¡¡Error!!! Fallo inesperado.");
        }
        if(enviadosAna == 1){
            System.out.println("Total de mensaje enviados por \"ana@gmail.com\"" + ": " + enviadosAna + ".");
            System.out.println("¡¡¡Funciona correctamente!!!");
        }
        else{
            System.out.print("¡¡¡Error!!! Fallo inesperado.");
        }
        MailItem mensajeMasLargo1 = new MailItem("pepe@gmail.com", "ana@gmail.com", "email3(encriptado)", "?=? Hola");
        if(recibidosAna > 0){
            System.out.println(mensajeMasLargo1.getFrom() + " ha enviado el mensaje más largo, con un total de " +
                mensajeMasLargo1.getMessage().length() + " caracteres.");
            System.out.print("¡¡¡TEST SUPERADO!!!");
        }
        System.out.print("\n");
        System.out.println("Se espera 1 recibido, 2 enviados, mensaje largo de User2 de 40 caracteres:");
        if(recibidosPepe == 1){
            System.out.println("Total de mensaje recibidos por \"pepe@gmail.com\"" + ": " + recibidosPepe + ".");
            System.out.println("¡¡¡Funciona correctamente!!!");
        }
        else{
            System.out.print("¡¡¡Error!!! Fallo inesperado.");
        }
        if(enviadosPepe == 2){
            System.out.println("Total de mensaje enviados por \"ana@gmail.com\"" + ": " + enviadosPepe + ".");
            System.out.println("¡¡¡Funciona correctamente!!!");
        }
        else{
            System.out.print("¡¡¡Error!!! Fallo inesperado.");
        }
        MailItem mensajeMasLargo2 = new MailItem("ana@gmail.com", "pepe@gmail.com", "RE: email3(encriptado)", "He recibido tu mensaje, gracias\n?=? Hola");
        if(recibidosAna > 0){
            System.out.println(mensajeMasLargo2.getFrom() + " ha enviado el mensaje más largo, con un total de " +
                mensajeMasLargo2.getMessage().length() + " caracteres.");
            System.out.print("¡¡¡TEST SUPERADO!!!");
        }
        System.out.print("\n");
    }

    /**
     * Test de la Funcionalidad 06.
     */
    public void testFuncionalidad6()
    {
        /**
         * SE CREA UN SERVIDOR LLAMADO GMAIL
         */
        System.out.print("\n");
        System.out.println("CREACION MAILSERVER GMAIL...");
        MailServer Gmail = new MailServer();

        /**
         * SE CREAN LOS USUARIOS USER1 Y USER2 ASOCIADOS A GMAIL
         */
        System.out.print("\n");
        System.out.println("CREACION USUARIOS...");
        MailClient User1 = new MailClient(Gmail, "pepe@gmail.com");
        System.out.println("Crea un cliente de correo User1(Gmail, \"pepe@gmail.com\")");
        MailClient User2 = new MailClient(Gmail, "ana@gmail.com");
        System.out.println("Crea un cliente de correo User2(Gmail, \"ana@gmail.com\")");

        /**
         * SE ENVIAN DOS MENSAJES DESDE USER1 A USER2 (ENCRIPTADO Y ENCRIPTADO CON SPAM)
         */
        System.out.print("\n");
        System.out.println("ENVIO DE CORREOS...");
        System.out.println("User2 envia 2 mensajes (encriptado y encriptado con spam) a User2");
        User1.sendMailItemEncrypted("ana@gmail.com", "emailEncriptado1", "Cuidado con los mensajes de spam!!");
        User1.sendMailItemEncrypted("ana@gmail.com", "emailEncriptado2", "Tengo unRegalO, muchos rEGaLos...");

        /**
         * SE GUARDA EL PRIMER EMAIL (ENCRIPTADO)
         */
        MailItem item = Gmail.getNextMailItem("ana@gmail.com");

        System.out.print("\n");
        System.out.println("PRUEBAS EMAILENCRIPTADO1...");

        /**
         * SE MUESTRA EL MENSAJE ENCRIPTADO Y EL ORIGINAL
         */
        String mensajeEncriptado = item.getMessage();
        System.out.println("El mensaje cifrado es --> " + mensajeEncriptado);
        String mensajeOriginal = item.decryptMessage();
        System.out.println("El mensaje original contenia --> " + mensajeOriginal);

        /**
         * SE COMPRUEBA SI SON IGUALES O NO PARA SABER SI SE HA HECHO ALGUN TIPO DE ENCRIPTACION O MANIPULACION EXTRAÑA
         */
        System.out.println("###Comprobando que el mensaje original y el encriptado no son iguales ****");
        if (mensajeEncriptado.equals(mensajeOriginal)){
            System.out.println("¡¡¡ ERROR !!! --> Los mensajes son iguales, no se ha cifrado nada...");
        }
        else{
            System.out.println("¡¡¡ CORRECTO !!! --> Los mensajes no son iguales...");
        }

        /**
         * SE COMPRUEBA SI EL MENSAJE ENCRIPTADO RECIBIDO CON METODO ES IGUAL AL ORIGINAL DESENCRIPTADO MANUALMENTE
         */
        System.out.println("###Comprobando que el mensaje encriptado es igual al original transcrito a mano ****");
        if(mensajeEncriptado.equals("?=? C\\*\\#d\\$d\\+ c\\+n l\\+s m\\&ns\\$j\\&s d\\& sp\\$m!!")){
            System.out.println("¡¡¡ CORRECTO !!! --> El mensaje se ha cifrado correctamente");
        }
        else{
            System.out.println("¡¡¡ ERROR !!! --> El mensaje no se ha cifrado correctamente");
        }

        /**
         * SE GUARDA EL SEGUNDO MENSAJE (ENCRIPTADO CON SPAM)
         */
        MailItem item2 = Gmail.getNextMailItem("ana@gmail.com");

        System.out.print("\n");
        System.out.println("PRUEBAS EMAILENCRIPTADO2...");

        /**
         * SE MUESTRA EL MENSAJE ENCRIPTADO Y EL ORIGINAL CON SPAM
         */
        mensajeEncriptado = item2.getMessage();
        System.out.println("El mensaje cifrado es --> " + mensajeEncriptado);

        mensajeOriginal = item2.decryptMessage();
        System.out.println("El mensaje original contenia --> " + mensajeOriginal);

        /**
         * SE VA A PASAR UN DETECTOR MANUAL (??) DE SPAM CON FUNCIONES DE DETECTAR ENCRIPTADO
         */
        System.out.println("EMAILENCRIPTADO2 --> Comprobando que detecta el SPAM en cualquier variante...");
        System.out.println("###Pasando detector manual de Spam...");
        if((mensajeEncriptado.matches(".*(V|v)(I|i|.*\\<|.*\\#)(A|a|.*\\¡|.*\\$)(G|g)(R|r)(A|a|.*\\¡|.*\\$).*"))||
        (mensajeEncriptado.matches(".*(R|r)(E|e|.*\\¬|.*\\&)(G|g)(A|a|.*\\¡|.*\\$)(L|l)(O|o|.*\\>|.*\\+).*"))){
            System.out.println(" ¡¡¡ CORRECTO !!! --> Detectado SPAM encriptado");
        }
        else{
            System.out.println(" ¡¡¡ ERROR !!! --> No se ha detectado el SPAM que contenia");
        }

        /**
         * SE PASA EL METODO DETECTSPAM() DE DIDAC CON FUNCIONES DE DETECCION DE ENCRIPTADO
         */
        System.out.println("###Invocando metodo detectSpam sobre el mensaje encriptado...");
        boolean tieneSpam = item2.detectSpam();
        System.out.println("###Comprobando resultado del metodo detectSpam");
        if (tieneSpam){
            System.out.println(" ¡¡¡ CORRECTO !!! --> Detectado SPAM encriptado");
            item2.getMessage();
        }
        else{
            System.out.println(" ¡¡¡ ERROR !!! --> No se ha detectado el SPAM que contenia");
            item2.getMessage();
        }

        /**
         * SE ENVIAN DOS NUEVOS CORREOS DESDE USER1 A USER2 PARA PROBAR LA
         * ENCRIPTACION/DESENCRIPTACION SOBRE EL METODO GETDOWNLOAD()
         */
        System.out.print("\n");
        System.out.println("ENVIO DE CORREOS...");
        System.out.println("User1 envia 2 mensajes (encriptado y encriptado con spam) a User2");
        User1.sendMailItemEncrypted("ana@gmail.com", "emailEncriptado1", "Cuidado con los mensajes de spam!!");
        User1.sendMailItemEncrypted("ana@gmail.com", "emailEncriptado2", "Tengo unRegalO, muchos rEGaLos...");

        /**
         * PRUEBAS ENCRIPTACION/DESENCRIPTACION SOBRE EL METODO GETDOWNLOAD()
         * SE INVOCA GETDOWNLOAD SOBRE EL NUEVO PRIMER CORREO
         */
        System.out.print("\n");
        System.out.println("PRUEBAS DE ENCRIPTACION/DESENCRIPTACION SOBRE GETDOWNLOAD()...");
        System.out.println("###Invocando getDownload en User2 \"ana@gmail.com\"");
        User2.getDownload();

        System.out.print("\n");

        /**
         * SE COMPRUEBA QUE EL AUTOMENSAJE RECIBIDO POR USER1 SEA IGUAL AL MENSAJE
         * RECIBIDO POR USER2 DESPUES DE DESENCRIPTARLO
         */
        System.out.println("###Comprobando que el mensaje reenviado automaticamente a User1 esta desencriptado\n" + 
            "###cuando User2 recibe un mensaje encriptado ya que tiene que devolver el original\n" +
            "###Automensaje recibido por User1 = Mensaje recibido por User2 despues de desencriptar");

        MailItem item3 = Gmail.getNextMailItem("pepe@gmail.com");
        // System.out.println(item3.getMessage());
        // System.out.println(item.getMessage());
        String gracias = "He recibido tu mensaje, gracias\n";
        if (item3.getMessage().equals(gracias + item.getMessage())){
            System.out.println("¡¡¡ CORRECTO !!! --> Los mensajes son iguales");
            System.out.println(item3.getMessage());
            System.out.println(item.getMessage());
        }
        else{
            System.out.println("¡¡¡ ERROR !!! --> El mensaje no se ha reenviado desencriptado");
            System.out.println(item3.getMessage());
            System.out.println(item.getMessage());
        }
    }
}
