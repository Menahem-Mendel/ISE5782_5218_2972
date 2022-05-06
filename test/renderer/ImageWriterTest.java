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
		int nx = imageWriter.getNx();
		int ny = imageWriter.getNy();

		for (int i = 0; i < nx; ++i)
			for (int j = 0; j < ny; ++j)
				if (i % delta != 0 && j % delta != 0)
					imageWriter.writePixel(i, j, new Color(YELLOW));
				else
					imageWriter.writePixel(i, j, new Color(RED));

		imageWriter.writeToImage();
	}
}
