package udemy.Services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PdfConvertService {

    public byte[] convertPdfToBinary(String path){
        path.replaceAll("\\\\","/");
        Path pdfPath = Paths.get(path);
        byte[] pdfBytes = new byte[0];
        try {
            pdfBytes = Files.readAllBytes(pdfPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pdfBytes;
    }

    public void convertBinaryToPdf(String path, String name,byte[] bytes){
        String filePath = path+"/"+name;
        filePath.replaceAll("\\\\","/");
        File file = new File(filePath);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(bytes);
            fos.close();
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
