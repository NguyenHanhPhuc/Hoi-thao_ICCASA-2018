package com.test.until;


import java.io.*;
import java.util.HashMap;
import java.util.HashSet;

public class DataStorage
{
    private static File file;

    static {
        String path =".data";
        file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized static void setData(int ID,int type)
            throws IOException
    {

        HashMap<String,HashSet<Integer>> data = null;
        try {
            FileInputStream fis =
                    new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            data = (HashMap<String, HashSet<Integer>>) ois.readObject();
        } catch (Exception e) {
        }
        if (data == null){
            data = new HashMap<>();
            data.put("cus_id",new HashSet<>());
            data.put("account_id",new HashSet<>());
            data.put("cus_id_account",new HashSet<>());
            data.put("account_id",new HashSet<>());
        }
        if (type == 1){
            data.get("cus_id").add(ID);
        }else if (type ==2){
            data.get("account_id").add(ID);
        }else return;

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(data);
        oos.close();

    }

    public static HashSet<Integer> getData(int type) throws IOException, ClassNotFoundException
    {
        HashMap<String,HashSet<Integer>> data;
        FileInputStream fis =
                new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream(fis);
        data = (HashMap<String, HashSet<Integer>>) ois.readObject();
        if (data == null) return null;
        if (type == 1){
            return data.get("cus_id");
        }else if (type == 2){
            return data.get("account_id");
        }
        return null;
    }

    public synchronized static void deleteData(int ID,int type)
            throws IOException
    {

        HashMap<String,HashSet<Integer>> data = null;
        try {
            FileInputStream fis =
                    new FileInputStream(file);
            ObjectInputStream ois = new ObjectInputStream(fis);
            data = (HashMap<String, HashSet<Integer>>) ois.readObject();
        } catch (Exception e) {
        }
        if (data == null){
            return;
        }
        if (type == 1){
            data.get("cus_id").remove(new Integer(ID));
        }else if (type ==2){
            data.get("account_id").remove(new Integer(ID));
        }else return;

        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(data);
        oos.close();

    }




    public static void main(String args[]) throws IOException, ClassNotFoundException {

        System.out.println("asasf");
    }
}