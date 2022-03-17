import java.util.Scanner;

public class JocDeLaVida {
    Scanner e = new Scanner(System.in);
    int x, y;
    int celula;

    public static void main(String[] args) {
        JocDeLaVida p = new JocDeLaVida();
        p.principal();
    }

    public void principal() {

        boolean acabar = false;
        System.out.println("Diposita el número de fileres del tauler (x): ");
        x=e.nextInt();
        System.out.println("Diposita el número de columnes del tauler (y): ");
        y=e.nextInt();
        int[][] tauler = new int[x][y];

        do {
            System.out.println("Selecciona una opció: ");
            System.out.println("1. Dipositar cèl·lula viva manualment : ");
            System.out.println("2. Dipositar cèl·lula viva automaticament: ");
            System.out.println("3. Imprimir tauler: ");
            System.out.println("4. Sortir. ");
            int opcio = e.nextInt();
            switch (opcio){
                case 1:
                    colocarCelulaManual(celula,tauler);
                    break;
                case 2:
                    colocarCelulaAuto(celula,tauler);
                    break;
                case 3:
                    System.out.println(" ");
                    ImprimirTauler(tauler);
                    System.out.println(" ");
                    break;
                case 4:
                    acabar=true;
                    break;
            }
        }while(!acabar);
    }

    public void ImprimirTauler(int[][]tauler) {//IMPRIMMIR
        for (int x = 0; x < tauler.length; x++) {
            for (int y = 0; y < tauler[x].length;y++) {
                System.out.print(tauler[x][y]);
            }
            System.out.println();
        }
    }

    public void colocarCelulaManual (int celula, int [][]tauler){//MANUAL
        System.out.println("Selecciona una fila (max."+ x+ "):");//Seleccionar fila
        x = e.nextInt();
        x = (x-1);
        System.out.println("Selecciona una columna (max."+ y+ "):");//Seleccionar colummna
        y = e.nextInt();
        y = (y-1);
        if (x > tauler.length || y > tauler.length){
            System.out.println("Estas fora dels marges, selecciona una altre fila i columna dintre dels marges");//Comprobar fila/columna
        }
        else if (x <= tauler.length && y <= tauler.length ) {//comprobar si hi ha un numero
            if (tauler[x][y]>0){
                System.out.println("Ja hi ha un numero en aquesta casella! Selecciona una altre!");

            }else if (tauler[x][y]==0){//dipositar numero
                celula = 1;
                tauler[x][y]= celula;
            }
        }
    }

    public void colocarCelulaAuto (int celula, int [][]tauler){//AUTO

        x = (int)(Math.random()* tauler.length);
        y = (int)(Math.random()* tauler[x].length);
        celula = 1;
        tauler[x][y]=celula;
    }



}
