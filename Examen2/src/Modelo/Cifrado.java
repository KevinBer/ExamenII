/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.*;
import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;

/**
 *
 * @author kevin bermudez
 */
public class Cifrado{
    String comando1 = "-c";
    String comando2 = "-d";
    int leidos;
    int fin_archivo;
    
    public void cifrarDescifrar(String [] valor)
    {
        if ((comando1.equals(valor[0]))||(comando2.equals(valor[0]))){    
    
        try{
            InputStreamReader leer_clave = new InputStreamReader(System.in);
            BufferedReader buff_clave = new BufferedReader(leer_clave);
            System.out.print("Escriba una clave: ");
            String clave = buff_clave.readLine();
      
        try{
            SecretKeyFactory skf = SecretKeyFactory.getInstance("DES");
            DESKeySpec kspec = new DESKeySpec(clave.getBytes());
            SecretKey ks = skf.generateSecret(kspec);
      
        try{
            Cipher cifrado = Cipher.getInstance("DES");
            if (comando1.equals(valor[0])){
                cifrado.init(Cipher.ENCRYPT_MODE, ks);
            }
            if (comando2.equals(valor[0])){
                cifrado.init(Cipher.DECRYPT_MODE, ks);
            }

            InputStream archivo = new FileInputStream( valor[1] );
            OutputStream fich_out = new FileOutputStream ( valor[2] );
         
            byte[] buffer = new byte[1024];
            byte[] bloque_cifrado;
            String textoCifrado = new String();
            fin_archivo = -1;
            leidos = archivo.read(buffer);
         
            while(leidos != fin_archivo) {
                bloque_cifrado = cifrado.update(buffer,0,leidos);
                textoCifrado = textoCifrado + new String(bloque_cifrado,"ISO-8859-1"); 
                leidos = archivo.read(buffer);          
            }
          
            archivo.close();
         
            bloque_cifrado = cifrado.doFinal();
            textoCifrado = textoCifrado + new String(bloque_cifrado,"ISO-8859-1");
                     
            fich_out.write(textoCifrado.getBytes("ISO-8859-1"));
         
            }
            
            catch(javax.crypto.NoSuchPaddingException nspe) {} 
            catch(javax.crypto.IllegalBlockSizeException ibse) {}
            catch(javax.crypto.BadPaddingException bpe) {}
        }
         
        catch(java.security.InvalidKeyException ike) {}
        catch(java.security.spec.InvalidKeySpecException ikse) {}
        catch(java.security.NoSuchAlgorithmException nsae) {}
        }

        catch(java.io.IOException ioex) {}
        }
    }
}
