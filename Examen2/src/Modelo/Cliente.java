/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Vista.VentanaChatDifusion;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Carolina
 */
public class Cliente extends Thread{
    Socket s;
    VentanaChatDifusion v;
    private String leido;
    Cifrado cifrado;
    String letra="-d";
    String [] valor= new String[1];

    public Cliente(Socket s, VentanaChatDifusion v) {
        
        this.s = s;
        this.v = v;
        cifrado= new Cifrado();
        System.out.println(s);
    }
   
 
    @Override
    public void run(){
        while(true){
            DataInputStream in = null;
            try {
                in = new DataInputStream(s.getInputStream());
                leido= in.readUTF();
                valor[0]="-d";
                cifrado.cifrarDescifrar(valor);
                v.setText(leido);
            } //fin del while
            catch (IOException ex) {
                Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
            } 
        }
    }
    
    public void enviar() throws IOException{
        DataOutputStream out;
        out = new DataOutputStream(s.getOutputStream());
        valor[0]="-c";
        cifrado.cifrarDescifrar(valor);
        out.writeUTF(v.getText());
        v.setText(v.getText());
    
    }
 
}
