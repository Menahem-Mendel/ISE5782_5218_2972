
/**
 * name: mendel gelfand    id: 342795218   mail: mendelgel@gmail.com
 * name: josef wolf        id: 317732972   mail: yossiwolf@hotmail.com
 */
import primitives.*;
import renderer.ImageWriter;
import primitives.Color;

import static java.awt.Color.*;

/**
 * Test program for the 1st stage
 *
 * @author Dan Zilberstein
 */
public final class Main {

    /**
     * Main program to tests initial functionality of the 1st stage
     * 
     * @param args irrelevant here
     */
    public static void main(String[] args) {
        ImageWriter imageWriter = new ImageWriter("testImage", 800, 500);
        int delta = 50;

        for (int i = 0; i < imageWriter.getNx(); i++)
            for (int j = 0; j < imageWriter.getNy(); j++)
                imageWriter.writePixel(i, j, new Color(YELLOW));

        for (int i = 0; i < imageWriter.getNx(); i += delta)
            for (int j = 0; j < imageWriter.getNy(); j++)
                imageWriter.writePixel(i, j, new Color(RED));

        for (int i = 0; i < imageWriter.getNx(); i++)
            for (int j = 0; j < imageWriter.getNy(); j += delta)
                imageWriter.writePixel(i, j, new Color(RED));

        imageWriter.writeToImage();
        System.out.println();
    }
}
