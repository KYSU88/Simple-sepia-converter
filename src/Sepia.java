import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Sepia {
    public static void main(String[] args)throws IOException {
        BufferedImage img = null; // hold the image
        File f = null; // hold the image file path

        // read image
        try {
            f = new File("D:\\Image\\zeenat.jpg");
            img = ImageIO.read(f);
        }catch(IOException e){
            System.out.println(e);
        }
        
        // get image width and height
        int width = img.getWidth();
        int height = img.getHeight();

        //convert to sepia
        for(int y =0; y < height; y++){
            for(int x=0; x < width; x++){
                int p = img.getRGB(x,y);  // get the value of the pixel
                int a = (p>>24)&0xff; // alpha value
                int r = (p>>16)&0xff; // red value
                int g = (p>>8)&0xff; // green value
                int b = p&0xff; // blue value

                //calculate tr, tg, tb
                int tr = (int)(0.393*r + 0.769*g + 0.189*b);
                int tg = (int)(0.349*r + 0.686*g + 0.168*b);
                int tb = (int)(0.272*r + 0.534*g + 0.131*b);

                // check condition
                if(tr > 255){
                    r = 255;
                }else{
                    r = tr;
                }

                if(tg > 255){
                    g = 255;
                }else{
                    g =tg;
                }

                if(tb > 255){
                    b = 255;
                }else{
                    b = tb;
                }

                // find the new pixel value and set it to the pixel at co-ordinate(x,y)
                p = (a<<24) | (r<<16) | (g<<8) | b;
                img.setRGB(x, y, p);
            }
        }

        // write image
        try {
            f = new File("D:\\Image\\Output.jpg");
            ImageIO.write(img, "jpg", f);
        }catch(IOException e){
            System.out.println(e);
        }
    }

}
