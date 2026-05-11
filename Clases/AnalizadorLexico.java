package Clases;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
/*
Adrian Guaraqui Rangel
Ángel Rodrigo Araujo Ayala
Kedryck Sergio Noperi Vasquez
Gildardo Aarat Zapién Cortés
 */
public class AnalizadorLexico {

    private static final List<String> PALABRAS_RESERVADAS = Arrays.asList(
            "int", "float", "double", "char", "if", "else", "while", "for", "return", "break"
    );

    private static List<Token> listaTokens = new ArrayList<>();
    public static List<Token> analizarArchivo() {
        listaTokens.clear();
        try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filtro = new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt");
            chooser.setFileFilter(filtro);
            chooser.setAcceptAllFileFilterUsed(false);
            int opcion = chooser.showOpenDialog(null);
            if (opcion == JFileChooser.APPROVE_OPTION) {
                File archivo = chooser.getSelectedFile();
                BufferedReader br = new BufferedReader(new FileReader(archivo));
                String linea;
                while ((linea = br.readLine()) != null) {
                    linea = linea.replaceAll("([;(){}=+\\-*/<>])", " $1 ");
                    String[] tokens = linea.split("\\s+");
                    for (String token : tokens) {
                        clasificarToken(token);
                    }
                }
                br.close();
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
        return listaTokens;
    }

    private static void clasificarToken(String token) {
        if (token.isEmpty()) return;
        if (PALABRAS_RESERVADAS.contains(token)) {
            listaTokens.add(new Token("Palabras Reservadas", token));
        } else if (token.matches("[a-zA-Z_][a-zA-Z0-9_]*")) {
            listaTokens.add(new Token("Identificadores/palabras", token));
        } else if (token.matches("\\d+(\\.\\d+)?")) {
            listaTokens.add(new Token("Numeros", token));
        } else if (token.matches("==|!=|<=|>=|[+\\-*/=<>]")) {
            listaTokens.add(new Token("Operador", token));
        } else if (token.matches("[;(){}:]")) {
            listaTokens.add(new Token("Simbolos", token));
        } else {
            listaTokens.add(new Token("Otros", token));
        }
    }
}