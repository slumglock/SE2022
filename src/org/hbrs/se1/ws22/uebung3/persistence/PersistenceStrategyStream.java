package org.hbrs.se1.ws22.uebung3.persistence;

import java.util.List;
import java.io.*;

public class PersistenceStrategyStream<Member> implements PersistenceStrategy<Member> {

    // URL of file, in which the objects are stored
    FileOutputStream fos = null;
    ObjectOutputStream oos = null;
    FileInputStream fis = null;
    ObjectInputStream ois = null;

    String file = "file.txt.txt";

    // Backdoor method used only for testing purposes, if the location should be changed in a Unit-Test
    // Example: Location is a directory (Streams do not like directories, so try this out ;-)!
    public void setLocation(String location) {
        this.file = location;
    }

    @Override
    /**
     * Method for opening the connection to a stream (here: Input- and Output-Stream)
     * In case of having problems while opening the streams, leave the code in methods load
     * and save
     */
    public void openConnection() throws PersistenceException {
        try {
            fos = new FileOutputStream(file);
            oos = new ObjectOutputStream(fos);
            fis = new FileInputStream(file);
            ois = new ObjectInputStream(fis);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    /**
     * Method for closing the connections to a stream
     */
    public void closeConnection() throws PersistenceException {
        try {
            fos.close();
            oos.close();
            fis.close();
            ois.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    @Override
    /**
     * Method for saving a list of Member-objects to a disk (HDD)
     */
    public void save(List<Member> member) throws PersistenceException {
        openConnection();

        try {
            oos.writeObject(member);
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            closeConnection();

        }

    }

    @Override
    /**
     * Method for loading a list of Member-objects from a disk (HDD)
     * Some coding examples come for free :-)
     * Take also a look at the import statements above ;-!
     */
    public List<Member> load() throws PersistenceException  {
        // Some Coding hints ;-)

        // ObjectInputStream ois = null;
        // FileInputStream fis = null;
        // List<...> newListe =  null;
        //
        openConnection();
        // Initiating the Stream (can also be moved to method openConnection()... ;-)
        try {
            fis = new FileInputStream( " a location to a file" );
            ois = new ObjectInputStream(fis);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<Member> newListe = null;
        // fis = new FileInputStream( " a location to a file" );
        // Tipp: Use a directory (ends with "/") to implement a negative test case ;-)
        // ois = new ObjectInputStream(fis);

        // Reading and extracting the list (try .. catch ommitted here)
        Object obj = null;
        try {
            obj = ois.readObject();

            if (obj instanceof List<?>) {
                newListe = (List) obj;
            }
            return newListe;
        }
        catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
        }
        finally {
            closeConnection();
        }
        return null;
        // Object obj = ois.readObject();

        // if (obj instanceof List<?>) {
        //       newListe = (List) obj;
        // return newListe

        // and finally close the streams (guess where this could be...?)
    }
}
