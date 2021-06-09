package models;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author 84038001
 */
import java.io.File;
import net.sourceforge.tess4j.*;

public final class imageOCR {

    private imageOCR() {
    }

    public static String scanImage(File file) {
        ITesseract instance = new Tesseract();

        try {
            return instance.doOCR(file);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }

}
