package bsu.edu.cs222;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.*;

public class ImageParserTest {
    /*public static void main(String[] args) throws IOException {
        FileInputStream inputStream = null;


        try{
            BufferedImage img = ImageIO.read(inputStream);


            File outputFile = new File("\"C:\\Users\\dariu\\Downloads\\Sofa.jpg\"");


            ImageIO.write(img, "png", outputFile);
        }catch(IOException e){
            System.out.println("Error");
        }
        }*/
    @Test
    public void testConvertInputStreamToImage() {
        InputStream inputStream = getClass().getResourceAsStream("/sofa.jpeg");
        assertNotNull(inputStream, "InputStream should not be null");

//        BufferedImage image = imageConvert.convertInputStreamToImage(inputStream);
//        assertNotNull(image, " BufferedImage should not be null");

    }
}

