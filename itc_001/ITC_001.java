package itc_001;

import Clases.AnalizadorLexico;
import Clases.Token;
import java.util.List;
/*
Adrian Guaraqui Rangel
Ángel Rodrigo Araujo Ayala
Kedryck Sergio Noperi Vasquez
Gildardo Aarat Zapién Cortés
 */
public class ITC_001 {

    public static void main(String[] args) {
        List<Token> tokens = AnalizadorLexico.analizarArchivo();
        for(Token t: tokens) {
            System.out.println(t);
        }
    }
    
}
