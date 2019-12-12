package udemy.Controllers;

import udemy.persistance.Ipsen3ProjectDAO;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

public class Ipsen3ProjectController {

    private Ipsen3ProjectDAO ipsen3ProjectDAO;
    private String path =  System.getProperty("user.home") + "\\Desktop\\files\\";

    public Ipsen3ProjectController(Ipsen3ProjectDAO ipsen3ProjectDAO) {
        this.ipsen3ProjectDAO = ipsen3ProjectDAO;
    }


    public byte[] getProjectFile(int id) {
        System.out.println("files in database: " + ipsen3ProjectDAO.getProjectFile(id));
        return saveFile(ipsen3ProjectDAO.getProjectFile(id));
    }

    public byte[] saveFile(String file){
        InputStream isInput = null;
        System.out.println("file location: " + path+file+".png");
        try {
                isInput = new FileInputStream(path+file+".png");
                BufferedImage inputStreamImage = ImageIO.read(isInput);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ImageIO.write(inputStreamImage,"png", new File(path+"test"));
                ImageIO.write(inputStreamImage,"png", baos);
                isInput.close();
                return baos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
