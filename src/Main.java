import edu.princeton.cs.stdlib.StdDraw;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        //Se establece la configuracion de la pantalla y varaibles a usar
        double min = -1.0;
        double max = 1.0;
        StdDraw.setXscale(min,max);
        StdDraw.setYscale(min,max);
        StdDraw.enableDoubleBuffering();
        StdDraw.setTitle("Salvapantallas Mystify");

        double[] x0 = new double[6];
        double[] x1 = new double[6];
        double[] y0 = new double[6];
        double[] y1 = new double[6];

        x0[0] = posRandom(min,max);
        x1[0] = posRandom(min,max);
        y0[0] = posRandom(min,max);
        y1[0] = posRandom(min,max);
        //Vectores de velocidad para cada punta de la linea
        double vx = 0.012, vx2 = 0.012;
        double vy = 0.032, vy2 = 0.032;

        while (true){
            StdDraw.clear();
            //Se trazan las lineas
            for (int i = 0; i < 6; i++) {
                StdDraw.setPenColor(cambiarColor(i));
                if (i==0){
                    StdDraw.line(x0[i], y0[i], x1[i], y1[i]);
                }else {
                    StdDraw.line(x0[i], y0[i], x1[i], y1[i]);
                }
            }

            // Actualizar valores del arreglo
            for (int i = 5; i >= 1; i--) {
                x0[i] = x0[i-1];
                y0[i] = y0[i-1];
                x1[i] = x1[i-1];
                y1[i] = y1[i-1];
            }

            //Mover primera linea
            x0[0] += vx;
            y0[0] += vy;
            x1[0] += vx2;
            y1[0] += vy2;

            //Registro de colisiones
            if (Math.abs(x0[0] + vx) > 1.0){
                vx = -vx;
            }
            if (Math.abs(y0[0] + vy) > 1.0){
                vy = -vy;
            }
            if (Math.abs(x1[0] + vx2) > 1.0){
                vx2 = -vx2;
            }
            if (Math.abs(y1[0] + vy2) > 1.0){
                vy2 = -vy2;
            }

            StdDraw.show();
            StdDraw.pause(40);
        }
    }

    /**
     * Subprograma usado para elegir el color de las lineas segun su posicion con respecto las otras lineas
     * @param i - Se entrega el numero de la linea
     * @return  - Se devuelve el color de la linea
     */
    public static Color cambiarColor(int i){
        Color[] lista = {Color.red, Color.blue, Color.green, Color.yellow, Color.pink, Color.CYAN};
        return lista[i];
    }

    /**
     * Subprograma usado para generar una posición al azar a los extremos de la primera linea
     * @param min - longitud minima del lienzo
     * @param max - longitud maxima del lienzo
     * @return    - Se devuelve la posición generada al azar del extremo
     */
    public static double posRandom(double min, double max){
        return min + (max - min) * Math.random();
    }
}
