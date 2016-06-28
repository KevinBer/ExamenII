/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.PrincipalDifusion;
import Vista.VentanaChatDifusion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.Socket;

/**
 *
 * @author Carolina
 */
public class ControladorPrincipalDifusion  implements ActionListener  {
    
    PrincipalDifusion principal;
    VentanaChatDifusion chat;

    public ControladorPrincipalDifusion(PrincipalDifusion principal) {
        this.principal = principal;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Crear nuevo chat")){
            chat = new VentanaChatDifusion();
            chat.setVisible(true);
        }
    }
}
