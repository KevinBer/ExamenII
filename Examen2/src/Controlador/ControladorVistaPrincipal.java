/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Vista.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author Carolina
 */
public class ControladorVistaPrincipal implements ActionListener  {

    VistaPrincipal vistaPrincipal; 

    public ControladorVistaPrincipal(VistaPrincipal vistaPrincipal) {
        this.vistaPrincipal = vistaPrincipal;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
         if(e.getActionCommand().equalsIgnoreCase("Privado")){
             System.out.println("funciona");
        }
        if(e.getActionCommand().equalsIgnoreCase("Difusión")){
           System.out.println("funciona");
        }
         if(e.getActionCommand().equalsIgnoreCase("Chat grupal")){
           System.out.println("funciona");
        }
        if(e.getActionCommand().equalsIgnoreCase("Salir")){
           System.out.println("funciona");
        }
    }
    
}
