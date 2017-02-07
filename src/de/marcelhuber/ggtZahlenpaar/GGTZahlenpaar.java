/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.marcelhuber.ggtZahlenpaar;

import java.util.ArrayList;

/**
 *
 * @author Marcel
 */
public class GGTZahlenpaar {

    // Diese Klasse ist natürlich ein wenig merkwürdig, definiert sie
    // doch nur Objekte, deren interssanten Attribute zunächst die zwei Zahlen 
    // z1 und z2 sind (in dem Sinn ist das Wort "Paar" oben zu verstehen), um 
    // dann auf diesen Objekten den Erweiterten Euklidischen Algorithmus für 
    // diese anzuwerfen, um die anderen 3 Attribute sinnvoll zu füllen
    int z1, z2;      // die zwei Zahlen, von denen der ggT berechnet werden soll
    int x1, y1, ggT; // Ergebnisse der Berechnung: Es ist ggT = ggT(z1,z2);
                     // x1 und y1 sind nach dem Euklidischen Algorithmus
                     // so, dass x1 * z1 + y1 * z2 = ggT.
    boolean euclidCalculated = false;

    GGTZahlenpaar() {
        this(0);
        System.out.println("Für ein zahlenfreies Objekt benutzen wir "
                + "automatisch Initialisierung mit den Zahlen " + z1
                + " und " + z2);
    }

    GGTZahlenpaar(int a) {
        this(a, a);
    }

    GGTZahlenpaar(int a, int b) {
        z1 = a;
        z2 = b;
        x1 = 1;
        y1 = 0;
        ggT = 0;
    }

    public static void main(String[] args) {
        GGTZahlenpaar zahlenPaar = new GGTZahlenpaar(-21, -15);
        if (!zahlenPaar.getValueOfeuclidCalculated()) {
            System.out.println("!!!Das Objekt wurde noch nicht mit dem "
                    + "euklidischen Algorithmus behandelt!!!\n");
        }
        zahlenPaar.goCreateGGTObject(zahlenPaar);
          if (zahlenPaar.getValueOfeuclidCalculated()) 
            System.out.println("\nDas Objekt wurde nun mit dem euklidischen"
                    + "Algorithmus behandelt!!");
    }

    void goCreateGGTObject(GGTZahlenpaar zahlenPaar) {
        int[] ausgabefeld = new int[3];
        ausgabefeld = zahlenPaar.calculatorErweiterterEuklidischerAlgorithmus();
        /* bei dieser Implementierung des Erweiterten Euklidischen Algorithmus
           kann der ggT auch negativ sein - der ggT zweier ganzer Zahlen a und b 
           ist definiert als eine Zahl g so, dass sowohl a | g als auch b | g 
           gilt, und wenn t eine Zahl ist mit t | a und t | b, so muss schon
           t | g folgen. Ist nun g so wie oben beschrieben und wir nehmen an,
           dass auch g' so wäre, dass für alle ganzen Zahlen t mit t | a und 
           t | b schon t | g' folgte. Insbesondere gilt g | a und g | b, also
           folgte dann g | g'. Andererseits gilt auch g' | a und g' | b, und 
           weil g ja auch die ggT-Eigenschaft bzgl. a und b hat, folgt dann
           g' | g. Also nur |g| = |g'|. Der ggT zweier ganzer Zahlen ist im 
           Ring der ganzen Zahlen nur eindeutig bis auf eine Einheit (EInheiten
           sind dort die Elemente aus {-1, 1}).
         */
        System.out.println("Hinweis: Falls der ggT eigentlich < 0 wäre, so "
                + "multiplizieren wir die ganze Gleichung mit -1 und ersetzen\n"
                + "den ggT und die entsprechenden Vorfaktoren halt mit dem "
                + "(-1)-fachen Wert, der als Originalwert berechnet worden ist.");
        if (ausgabefeld[2] < 0) {
            for (int k = 0; k < ausgabefeld.length; k++) {
                ausgabefeld[k] *= -1;
            }
        }
        String Z1 = zahlenPaar.negint2String(z1);
        String Z2 = zahlenPaar.negint2String(z2);
        int m1 = ausgabefeld[0] * z1;
        int m2 = ausgabefeld[1] * z2;

        String M2 = zahlenPaar.negint2String(m2);
        String AF1 = zahlenPaar.negint2String(ausgabefeld[1]);

        System.out.println("Es gilt " + ausgabefeld[0] + " * " + Z1 + " + "
                + AF1 + " * " + Z2 + " = " + m1 + " + " + M2
                + " = ggT(" + z1 + ", " + z2 + ") = " + ausgabefeld[2] + ".");
        System.out.println("\nOriginalwerte: ");
        System.out.print("x1 = " + zahlenPaar.getValueOfx1());
        System.out.print(", y1 = " + zahlenPaar.getValueOfy1());
        System.out.println(", ggT = " + zahlenPaar.getValueOfggT());

    }

    /**
     * @param args the command line arguments
     */
    public int[] calculatorErweiterterEuklidischerAlgorithmus() {
        euclidCalculated = true;
        int a = z1;
        int b = z2;
        int tauschHelfer;
        int[] rueckgabefeld = new int[3];
        int[] xVorf = new int[2];
        int[] yVorf = new int[2];
        int q;
        xVorf[0] = 1;
        xVorf[1] = 0;
        yVorf[0] = 0;
        yVorf[1] = 1;
        ggT = a;
        rueckgabefeld[0] = xVorf[0];
        rueckgabefeld[1] = yVorf[0];
        if (b != 0) {
            while (!(a % b == 0)) {
                q = a / b;
                tauschHelfer = b;
                b = a % b;
                a = tauschHelfer;
                tauschHelfer = xVorf[1];
                xVorf[1] = xVorf[0] - q * xVorf[1];
                xVorf[0] = tauschHelfer;
                tauschHelfer = yVorf[1];
                yVorf[1] = yVorf[0] - q * yVorf[1];
                yVorf[0] = tauschHelfer;
            }
            ggT = b;
            rueckgabefeld[0] = xVorf[1];
            rueckgabefeld[1] = yVorf[1];
        }

        rueckgabefeld[2] = ggT;

        x1 = xVorf[1];
        y1 = yVorf[1];
        return rueckgabefeld;
    }

    String intarray2String(int[] feld) {
        String stringFeld = "";
        int i;
        for (i = 0; i < feld.length - 1; ++i) {
            stringFeld = stringFeld + feld[i] + ",";
        }
        stringFeld = stringFeld + i;
        return stringFeld;
    }

    String negint2String(int z1) {
        String returnString = "" + z1;
        if (z1 < 0) {
            returnString = "(" + z1 + ")";
        }
        return returnString;
    }

    int getValueOfx1() {
        return x1;
    }

    int getValueOfy1() {
        return y1;
    }

    int getValueOfggT() {
        return ggT;
    }

    boolean getValueOfeuclidCalculated() {
        return euclidCalculated;
    }
}
