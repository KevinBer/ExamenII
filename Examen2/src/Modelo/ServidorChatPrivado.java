/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carolina
 */
public class ServidorChatPrivado extends Thread {
    private ServerSocket serverSocket;
    private HiloPrivado h;
    String nombre;
    ArrayList <HiloPrivado> cliente;
    HashMap<String, ArrayList <HiloPrivado>> hash;
    Lock lock;

    public ServidorChatPrivado(int port){
       cliente = new ArrayList<>();
       hash = new HashMap<>();
       hash.put("difusion", cliente);
       lock = new Lock();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(ServidorChatPrivado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
     public void run(){
      while(true){
          try {
              Socket socket = serverSocket.accept();
              DataInputStream in = null;
              in = new DataInputStream(socket.getInputStream());
              in.readUTF();
              //String nombre, Socket socket, Servidor servidor
              h = new HiloPrivado(nombre, socket, this);
              h.start();
          } catch (IOException ex) {
              Logger.getLogger(ServidorChatPrivado.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    }//finn del run
    
    public void escribirTodos(String mensaje, String nombre) throws IOException{
        try {
            HiloPrivado h1;
            lock.lock();
            for(int i=0; i<hash.size(); i++){
               h1 = cliente.get(i);
               h1.escribir(mensaje, nombre);
            }
            lock.unlock();
        } catch (InterruptedException ex) {
            Logger.getLogger(ServidorChatPrivado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
      public static void main(String[] args) {
        ServidorChatPrivado server = new ServidorChatPrivado(9020);
        server.start();
        try {
            server.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ServidorChatPrivado.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
