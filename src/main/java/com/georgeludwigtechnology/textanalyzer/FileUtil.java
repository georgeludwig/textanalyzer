package com.georgeludwigtechnology.textanalyzer;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    public static final String FILE_SEPERATOR = System.getProperty("file.separator");

    public FileUtil() {
    }

    public static File backupFile(File originalFile, File backupDir) throws Exception {
        backupDir.mkdirs();
        String originalFileName = originalFile.getName();
        String backupfileName = backupDir.getAbsolutePath() + FILE_SEPERATOR + originalFileName + "." + System.currentTimeMillis();
        File backupFile = new File(backupfileName);
        OutputStream os = new FileOutputStream(backupFile);
        InputStream is = new FileInputStream(originalFile);

        while(is.available() > 0) {
            os.write(is.read());
        }

        os.flush();
        os.close();
        return backupFile;
    }

    public static boolean removeDirectory(File directory) {
        if (directory == null) {
            return false;
        } else if (!directory.exists()) {
            return true;
        } else if (!directory.isDirectory()) {
            return false;
        } else {
            String[] list = directory.list();
            if (list != null) {
                for(int i = 0; i < list.length; ++i) {
                    File entry = new File(directory, list[i]);
                    if (entry.isDirectory()) {
                        if (!removeDirectory(entry)) {
                            return false;
                        }
                    } else if (!entry.delete()) {
                        return false;
                    }
                }
            }

            return directory.delete();
        }
    }

    public static List<String> readFile(String scriptUrl) throws Exception {
        String encoding = System.getProperty("file.encoding");
        return readFile(scriptUrl, encoding);
    }

    public static List<String> readFile(String scriptUrl, String encoding) throws Exception {
        new ArrayList();
        List ret;
        if (scriptUrl.startsWith("classpath://")) {
            String s = scriptUrl.substring(12);
            ret = readClassPathFile(s, encoding);
        } else {
            ret = readUrlFile(scriptUrl, encoding);
        }

        return ret;
    }

    public static List<String> readClassPathFile(String script, String encoding) throws Exception {
        List<String> ret = new ArrayList();
        InputStream is = FileUtil.class.getResourceAsStream(script);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is, encoding));

        String line;
        while((line = reader.readLine()) != null) {
            ret.add(line);
        }

        return ret;
    }

    public static List<String> readClassPathFile(String script) throws Exception {
        String encoding = System.getProperty("file.encoding");
        return readClassPathFile(script, encoding);
    }

    public static List<String> readUrlFile(String scriptUrl) throws Exception {
        String encoding = System.getProperty("file.encoding");
        return readUrlFile(scriptUrl, encoding);
    }

    public static List<String> readUrlFile(String scriptUrl, String encoding) throws Exception {
        List<String> ret = new ArrayList();
        URL url = new URL(scriptUrl);
        URLConnection connection = url.openConnection();
        DataInputStream in = new DataInputStream(connection.getInputStream());
        BufferedReader reader = new BufferedReader(new InputStreamReader(in, encoding));

        String line;
        while((line = reader.readLine()) != null) {
            ret.add(line);
        }

        return ret;
    }
}

