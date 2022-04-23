package renderer;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Color;

import static java.awt.Color.*;

public class ImageWriterTest {
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
