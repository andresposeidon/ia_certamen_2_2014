package modelo;

/**
 *
 * @author Andrés Garcés
 */
public class laberinto {

    int[][] laberinto;

    public laberinto() {
        this.laberinto = new int[][]{{1, 1, 1, 1, 1, 1, 1, 1},
                                     {0, 0, 0, 1, 0, 1, 0, 1},
                                     {1, 0, 0, 1, 0, 0, 0, 1},
                                     {1, 0, 0, 0, 0, 1, 0, 0},
                                     {1, 1, 1, 1, 1, 1, 1, 1}};

        /*this.laberinto = new int[][]{ {1, 1, 1, 1, 1},
         {0, 0, 1, 0, 0}, 
         {1, 0, 1, 0, 1},
         {1, 0, 0, 0, 1},
         {1, 0, 1, 0, 1}, 
         {1, 0, 0, 0, 1},
         {1, 1, 1, 1, 1}};*/

    }

    public int[][] getLaberinto() {
        return laberinto;
    }

    public void setLaberinto(int x, int y) {
        for (int i = 0; i < laberinto.length; i++) {
            for (int j = 0; j < laberinto[0].length; j++) {  
                if(laberinto[i][j]==8){
                    laberinto[i][j]=0;
                }
            }
        }
        this.laberinto[x][y] = 8;
        
    }
    public void setObjetoTipo(int x,int y,int tipo){
        this.laberinto[x][y] = tipo;
    }

    public void print() {
        for (int i = 0; i < laberinto.length; i++) {

            for (int j = 0; j < laberinto[0].length; j++) {
                if (laberinto[i][j] == 1) {
                        System.out.print("- ");
                } else if (laberinto[i][j] == 0) {
                    System.out.print("  ");
                } else if (laberinto[i][j] == 8) {
                    System.out.print("® ");
                } else if(laberinto[i][j] ==2){
                    System.out.print("* ");
                }else{
                    System.out.print(laberinto[i][j] + " ");
                }

            }
            System.out.print("\n");
        }
        System.out.print("\n");
    }
}
