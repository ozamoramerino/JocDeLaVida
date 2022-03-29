package packages;

import java.util.Arrays;
import java.util.Scanner;


public class Vida {

    Scanner e = new Scanner(System.in);

    int files;
    int columnes;
    char [][] M;
    char [][] M1;
    int vives;
    int filesvives;
    int columnesvives;
    int sobreviure1;
    int sobreviure2;
    int reviure;
    boolean play = true;

    public static void main(String[] args)
    {
        Vida v = new Vida();
        v.Principal();

    }

    public void Principal() {

        Demanar_Normes();
        Demanar_Mostrar_Dimensio();


        e.nextLine();

        while (play) {
            play = false;

            for (int f = 1; f < M.length -1; f++)
            {
                for(int c = 1; c < M.length -1; c++ )
                {
                    Evolucionar(f, c, Calcularveins(f, c));
                }
            }

            Copia();
        }

        System.out.println("SE ACABÓ");
    }
    public void Demanar_Mostrar_Dimensio()
    {

        System.out.println("Entrar el numero de Files: ");
        files = e.nextInt();

        System.out.println("Entrar el numero de Columnes: ");
        columnes = e.nextInt();

        M1 = new char [files+2][columnes+2];
        M = new char [files+2][columnes+2];


        System.out.println("Entrar el numero de quadrants vius: ");
        vives = e.nextInt();



        for (int f = 1; f < M.length -1; f++)
        {
            for(int c = 1; c < M.length -1; c++ )
            {
                M[f][c] = '■';
                M1[f][c] = '■';

            }
        }

        for(int x = 0; x < vives; x++) {
            System.out.println("A quina fila? ");
            filesvives = e.nextInt();
            System.out.println("A quina columna? ");
            columnesvives = e.nextInt();
            M[filesvives+1][columnesvives+1] = '□';

        }


        System.out.println(Arrays.deepToString(M).replace("],", "],\n"));

        System.out.println();

        System.out.println("Prem la tecla Enter per continuar l'evolució ");
        e.nextLine();


    }

    public void Evolucionar(int f, int c, int calcularvives) {


        if (M[f][c] == '□') {

            if (calcularvives == sobreviure1 || calcularvives == sobreviure2) {
                M1[f][c] = '□';
            } else {
                M1[f][c] = '■';
            }

        } else {
            if(calcularvives == reviure) {
                M1[f][c] = '□';
            }
        }
    }


    public int Calcularveins(int f, int c) {

        int contarvives = 0;
        if(M[f-1][c-1] == '□') {
            contarvives++;
        }
        if(M[f-1][c] == '□') {
            contarvives++;
        }
        if(M[f-1][c+1] == '□') {
            contarvives++;
        }
        if(M[f][c+1] == '□') {
            contarvives++;
        }
        if(M[f+1][c+1] == '□') {
            contarvives++;
        }
        if(M[f+1][c] == '□') {
            contarvives++;
        }
        if(M[f+1][c-1] == '□') {
            contarvives++;
        }
        if(M[f][c-1] == '□') {
            contarvives++;
        }
        return contarvives;
    }

    public void Demanar_Normes() {

        System.out.println("Començem introduint les normes ");
        System.out.println("Insereix entre quins dos nombres vols que sobreviveixi la cel·lula: ");
        System.out.println("Numero més petit: ");
        sobreviure1 = e.nextInt();

        System.out.println("Numero més gran: ");
        sobreviure2 = e.nextInt();

        System.out.println("Seguirem introduint el numero de cel·lules vives que necessita tenir al costat la cel·lula per reviure ");
        System.out.println("Introduieix el numero: ");
        reviure = e.nextInt();
    }

    public void Copia() {



        for (int f = 1; f < M.length -1; f++)
        {
            for(int c = 1; c < M.length -1; c++ )
            {
                if (M1[f][c] == '□') {
                    play = true;
                }
                M[f][c] = M1[f][c];

            }
        }

        System.out.println(Arrays.deepToString(M).replace("],", "],\n"));

        System.out.println();

        System.out.println("Prem la tecla Enter per continuar l'evolució ");
        e.nextLine();
    }}

