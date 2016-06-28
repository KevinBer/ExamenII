/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;


import Modelo.ClienteDifusion;
import Vista.VentanaChatDifusion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carolina
 */
public class ControladorChatDifusion implements ActionListener{
    VentanaChatDifusion chat;
    Socket s;
    ClienteDifusion cliente;

    public ControladorChatDifusion(VentanaChatDifusion chat) {
        this.chat = chat;
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getActionCommand().equals("conectar")){
        System.out.println("conectando");
        try {
            //s tiene que ser nulo para que pueda ser 
            if(s == null){
           s = new Socket(chat.getHost(),chat.getPort());
            cliente = new ClienteDifusion(s, chat);
            cliente.start();
            }    
        } catch (IOException ex) {
            Logger.getLogger(ControladorChatDifusion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
           
    }//fin del conectar
    if(e.getActionCommand().equals("enviar")){
        System.out.println("enviando");
        try { 
           if(cliente != null){
           cliente.enviar(); 
           chat.limpiar();
           }
        } catch (IOException ex) {
            Logger.getLogger(ControladorChatDifusion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//fin de enviar
    
    }//fin del performed
    
}
