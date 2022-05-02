package renderer;

import org.junit.jupiter.api.Test;

import primitives.Color;

import static java.awt.Color.*;

/**
 * Test for ImageWriterTest, printing image one color with a grid
 */
public class ImageWriterTest {

	/**
	 * printing a simple image of one color with a grid
	 */
	@Test
	void testWriteToImage() {
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
	}

}
