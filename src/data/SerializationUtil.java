/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import Exceptions.BadSaveDataException;
import Exceptions.NoSavedGameException;
import com.sun.org.apache.xml.internal.security.exceptions.Base64DecodingException;
import com.sun.org.apache.xml.internal.security.utils.Base64;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ellen
 */
public class SerializationUtil {
    
    public static Object deserialize(String filename) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(filename);
            BufferedInputStream bis = new BufferedInputStream(fis);
            Object obj;
            ObjectInputStream ois = new ObjectInputStream(bis);
            obj = ois.readObject();
            return obj;
        } catch (FileNotFoundException ex) {
            throw new NoSavedGameException("Save file not found.");
        } catch (IOException | ClassNotFoundException ex) {
            throw new BadSaveDataException("Bad save data");
        }
        finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(SerializationUtil.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    
    }
    
    public static void serialize(Object object, String filename) {
        try {
            FileOutputStream fos = new FileOutputStream(filename);
            BufferedOutputStream bos = new BufferedOutputStream(fos);
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            oos.close();
        } catch (FileNotFoundException ex) {
            //todo
        } catch (IOException ex) {
            Logger.getLogger(SerializationUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static String serializeToString(Object object) {
        try {
            ByteOutputStream bos = new ByteOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(object);
            oos.flush();
            return Base64.encode(bos.toByteArray());
        } catch (IOException ex) {
            throw new BadSaveDataException("Bad save data");
        }
    }
    
    public static Object deserializeFromString(String serializedObject) {
        try {
            Object object;
            ByteArrayInputStream bis = new ByteArrayInputStream(Base64.decode(serializedObject.getBytes()));
            ObjectInputStream ois = new ObjectInputStream(bis);
            object = ois.readObject();
            return object;
        } catch (IOException ex) {
            throw new NoSavedGameException("Save data not found.");
        } catch (ClassNotFoundException | Base64DecodingException ex) {
            throw new BadSaveDataException("Bad save data");
        }
    }
}
