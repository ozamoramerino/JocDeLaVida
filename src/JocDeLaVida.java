import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Random;

public class JocDeLaVida {
    Scanner e=new Scanner(System.in);
    Random random = new Random();
    //NORMES
    int minVeins= 2;
    int maxVeins=3;
    int reviure=3;
    int sobrepoblacio=4;
    int sol=1;
    //INICI
    { System.out.println("Diposita el número de fileres del tauler (f/fila): "); }
    int f = e.nextInt();
    { System.out.println("Diposita el número de columnes del tauler (c/columna): "); }
    int c = e.nextInt();
    { System.out.println("Diposita quantes generacions vols(vegades que s'imprimirà el tauler)? ");}
    int generacions =e.nextInt();
    //VARIABLES
    char tauler[][] = new char [f][c];
    char tauler2[][] = new char [f][c];
    int contgeneracions=0;
    boolean acabar=false;

    public static void main(String[] args) {
        JocDeLaVida a = new JocDeLaVida();
        a.principal();
    }

    public void principal() {
        do {
            System.out.println("[Selecciona una opció: ]");
            System.out.println("1.- Dipositar cèl·lula viva manualment: ");
            System.out.println("2.- Dipositar cèl·lula viva automaticament: ");
            System.out.println("3.- Sortir. ");
            int opcio = e.nextInt();
            switch (opcio){
                case 1:
                    crearMapa();
                    colocarCelulaManual(f,c);
                    while (contgeneracions<generacions){
                        //Evolucionem.
                        EvolucioTauler();
                        //Mostrem generacio.
                        generacions();
                        //Copiem el tauler evolucionat a l'inicial.
                        EvolucioTauler2();
                        //Buidem tauler evolucionat.
                        buidarArray(tauler2);
                        contgeneracions++;
                    }
                    System.out.println(" ");
                    acabar=true;
                    break;
                case 2:
                    crearMapa();
                    colocarCelulaAuto(f,c);
                    while (contgeneracions<generacions){
                        //Evolucionem.
                        EvolucioTauler();
                        //Mostrem generacio.
                        generacions();
                        //Copiem el tauler evolucionat a l'inicial.
                        EvolucioTauler2();
                        //Buidem tauler evolucionat.
                        buidarArray(tauler2);
                        contgeneracions++;
                    }
                    System.out.println(" ");
                    acabar=true;
                    break;
                case 3:
                    acabar=true;
                    break;
            }
        }while(!acabar);
    }

    public void crearMapa() {//Inicialitzar Tauler i tauler2
        for(int fila=0; fila<tauler.length;fila++) {
            for(int col = 0; col<tauler[fila].length;col++) {
                tauler[fila][col] = '□';
            }
        }
        for(int fila=0; fila<tauler2.length;fila++) {
            for(int col = 0; col<tauler2[fila].length;col++) {
                tauler2[fila][col] = '□';
            }
        }
    }

    public void colocarCelulaAuto(int f, int c) {
        //Colocar Automatic
        System.out.println("Diposita el nombre de colonies que vols: ");
        int colonies = e.nextInt();
        int tamanycolonia = 0;
        for (int cont = 0; cont < colonies; cont++) {
            int posfila = random.nextInt((tauler.length - 1) - 1) + 1;
            int poscolumna = random.nextInt((tauler[0].length - 1) - 1) + 1;
            do {
                int posfi;
                int poscol;
                posfi = random.nextInt((posfila + 4) - (posfila - 4)) + (posfila - 4);
                while((posfi < 1) || (posfi > tauler.length - 1)) {
                    posfi = random.nextInt((posfila + 4) - (posfila - 4)) + (posfila - 4);
                }
                poscol = random.nextInt((poscolumna + 4) - (poscolumna - 4)) + (poscolumna - 4);
                while((poscol < 1) || (poscol > tauler.length - 1)) {
                    poscol = random.nextInt((poscolumna + 4) - (poscolumna - 4)) + (poscolumna - 4);
                }
                tauler[posfi][poscol] = '■';
                tamanycolonia++;
            }
            while (tamanycolonia < 5);
            tamanycolonia = 0;
        }
    }

    public void colocarCelulaManual(int f, int c) {
        //Colocar manual
        int z=0;
        System.out.println("Diposita quantes cel·les vols dipositar? ");
        int celes=e.nextInt();
        while (z < celes){
            System.out.println("Diposita una fila: ");
            int fila=e.nextInt();
            while (fila>=f) {
                System.out.println("Error! Estas fora dels limits de les files. ");
                fila=e.nextInt();
            }
            System.out.println("Diposita una columna: ");
            int columna=e.nextInt();
            while (columna>=c) {
                System.out.println("Error! Estas fora dels limits de les columnes. ");
                columna=e.nextInt();
            }
            if (tauler[fila][columna]=='□') {
                tauler[fila][columna]='■';
            }
            else if (tauler[fila][columna]!='□'){
                System.out.println("Error");
            }
            z++;
        }
    }

    public int comprovaVeins(int f, int c) {//Comprova posicio veins
        int veins = 0;
        if(tauler[f-1][c-1] == '■') {//diagonal abaix esquerra
            veins++;
        }
        if(tauler[f-1][c] == '■') {//abaix
            veins++;
        }
        if(tauler[f-1][c+1] == '■') {//diagonal abaix dreta
            veins++;
        }
        if(tauler[f][c+1] == '■') {//dreta
            veins++;
        }
        if(tauler[f+1][c+1] == '■') {//diagonal adalt dreta
            veins++;
        }
        if(tauler[f+1][c] == '■') {//adalt
            veins++;
        }
        if(tauler[f+1][c-1] == '■') {//diagonal adalt esquerra
            veins++;
        }
        if(tauler[f][c-1] == '■') {//esquerra
            veins++;
        }
        return veins;
    }

    public void EvolucioTauler() {//Evolucionar el tauler amb les diferents normes del joc
        for (int x=1;x<f-1;x++) {
            for (int y=1;y<c-1;y++) {

                if (tauler[x][y] == '■') {//min2 max3
                    if (comprovaVeins(x,y) == minVeins || comprovaVeins(x,y) == maxVeins){
                        tauler2[x][y] = '■';
                    }
                    else{//soletat
                        tauler2[x][y] = '□';
                    }
                }
                if ((tauler[x][y] == '□') && (comprovaVeins(x,y) == reviure)) {//revivir
                    tauler2[x][y] = '■';
                }
                if ((tauler[x][y] == '■') && (comprovaVeins(x,y) == sobrepoblacio)) {//sobrepoblacion
                    tauler2[x][y] = '□';
                }
            }
        }
    }

    public void buidarArray (char [][] array) {//buidar Array
        for (int x = 0; x < tauler.length; x++) {
            for (int y = 0; y < tauler[x].length; y++) {
                array[x][y] = '□';
            }
        }
    }

    public void EvolucioTauler2 () {//Evoluciona el tauler2 imprimint els canvis del tauler1
        for (int i = 0; i < tauler2.length; i++) {
            for (int j = 0; j < tauler2[i].length; j++) {
                tauler[i][j] = tauler2[i][j];
            }
        }
    }

    public void generacions() {//bucle per carregar les generacions i imprimir els taulers a determinada velocitat
        try {
            TimeUnit.SECONDS.sleep(1);//congelar el seguiment de la compilacio durant x segons
        } catch (InterruptedException ie) {
            System.err.format("IOException: %s%n", e);//en cas d'error avisara
        }
        System.out.println(Arrays.deepToString(tauler).replace("],", "],\n"));//marges
        System.out.println();
    }
}
//OZM

























//mzm (EasterEgg)