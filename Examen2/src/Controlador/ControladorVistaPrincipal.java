/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.PrincipalDifusion;
import Vista.VentanaChatDifusion;
import Vista.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Carolina
 */
public class ControladorVistaPrincipal implements ActionListener  {

    VistaPrincipal vistaPrincipal; 
    PrincipalDifusion pd;
    ControladorPrincipalDifusion controladorPrincipalDifusion;
    VentanaChatDifusion chat;

    public ControladorVistaPrincipal(VistaPrincipal vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getActionCommand().equalsIgnoreCase("Privado")){
             System.out.println("funciona");
        }
        if(e.getActionCommand().equalsIgnoreCase("Difusión")){
           controladorPrincipalDifusion = new ControladorPrincipalDifusion(pd);
            VentanaChatDifusion chat;
        }
         if(e.getActionCommand().equalsIgnoreCase("Chat grupal")){
           System.out.println("funciona");
        }
        if(e.getActionCommand().equalsIgnoreCase("Salir")){
           System.exit(0);
        }
    }
    
}
