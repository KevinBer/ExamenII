/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.System.in;
import static java.lang.System.out;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carolina
 */
public class HiloDifusion extends Thread {
    private Thread t;
    private String nombre, leido;
    Socket socket;
    ServidorDifusion servidor;

    public HiloDifusion(String nombre, Socket socket, ServidorDifusion servidor) {
        this.nombre = nombre;
        this.socket = socket;
        this.servidor = servidor;
    }

    
    public void escribir(String mensaje,String nombre) throws IOException{
        DataOutputStream out;
        if(!nombre.equals(this.nombre)) {  
            out = new DataOutputStream(socket.getOutputStream());
            out.writeUTF(mensaje); 
    }} 
    
    public void leer()throws IOException{
        System.out.println("Just connected to " + socket.getRemoteSocketAddress());
        DataInputStream in = new DataInputStream(socket.getInputStream());
        leido= in.readUTF();
        servidor.escribirTodos(leido,nombre);
    
    }
    
     public void run(){
         while(true){
            try {

                this.leer();

            } catch (IOException ex) {
                //Logger.getLogger(Hilo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }
}
