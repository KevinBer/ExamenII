/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carolina
 */
public class ServidorDifusion extends Thread {
    private ServerSocket serverSocket;
    HiloDifusion h;
    ArrayList <HiloDifusion> cliente;
    int pos;
    Lock lock;

    public ServidorDifusion(int port){
       cliente = new ArrayList<>();
       pos=0;
       lock = new Lock();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException ex) {
            Logger.getLogger(ServidorDifusion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
     public void run(){
      while(true){
          try {
              Socket socket = serverSocket.accept();
              //String nombre, Socket socket, Servidor servidor
              h = new HiloDifusion("hilo"+ pos, socket, this);
              h.start();
              pos++;
              cliente.add(h);
          } catch (IOException ex) {
              Logger.getLogger(ServidorDifusion.class.getName()).log(Level.SEVERE, null, ex);
          }
      }
    }//finn del run
    
    public void escribirTodos(String mensaje, String nombre) throws IOException{
        try {
            HiloDifusion h1;
            lock.lock();
            for(int i=0; i<cliente.size(); i++){
               h1 = cliente.get(i);
               h1.escribir(mensaje, nombre);
            }
            lock.unlock();
        } catch (InterruptedException ex) {
            Logger.getLogger(ServidorDifusion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
      public static void main(String[] args) {
        ServidorDifusion server = new ServidorDifusion(9020);
        server.start();
        try {
            server.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(ServidorDifusion.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
}
