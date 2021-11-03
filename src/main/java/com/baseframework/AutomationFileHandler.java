package com.baseframework;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AutomationFileHandler {

    public static void jsonResponseToFile(String filepath, byte[] responseAsByteArray) throws IOException {

        File getResponseFile = new File(filepath);
        getResponseFile.createNewFile();

        OutputStream os = new FileOutputStream(getResponseFile);

        os.write(responseAsByteArray);
        os.close();
    }

    public static Map<String, String> hashmapFromFile(String filePath) throws IOException {

        Map<String, String> map = new HashMap<String, String>();
        BufferedReader br = null;

        try {
            File file = new File(filePath);
            br = new BufferedReader(new FileReader(file));
            String line = null;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0].trim();
                String number = parts[1].trim();
                if (!name.equals("") && !number.equals("")) {
                    map.put(name, number);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                br.close();
            }
        }
        return map;
    }

    public static String[] arrayFromFile(String filePath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        List<String> lines = new ArrayList<String>();

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        bufferedReader.close();

        String[] data = lines.toArray(new String[]{});
        return data;
    }

}