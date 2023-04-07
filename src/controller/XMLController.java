package controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.ArrayList;

public class XMLController { 

    private XStream xStream = new XStream(new StaxDriver());

    protected void saveToXML(ArrayList<?> objectList, String fileName) {

        String sxml = xStream.toXML(objectList);
        FileOutputStream fileOutput;
        try {

            byte[] bytes = sxml.getBytes("UTF-8");
            fileOutput = new FileOutputStream("src" + File.separator + "database" + File.separator + fileName);
            fileOutput.write(bytes);
            fileOutput.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    protected ArrayList<?> loadFroamXML(ArrayList<?> objectList, String fileName) {

        xStream.addPermission(AnyTypePermission.ANY); // Fix forbidden class exception
        FileInputStream fileInput;
        try {

            fileInput = new FileInputStream("src" + File.separator + "database" + File.separator + fileName);

            String sxml = "";
            int isi;
            char c;
            while ((isi = fileInput.read()) != -1) {
                c = (char) isi;
                sxml += c;
            }

            objectList = (ArrayList<?>) xStream.fromXML(sxml);
            fileInput.close();
            return objectList;

        } catch (Exception e) {
            System.out.println(e.getMessage());
            return objectList;
        }
    }

}
