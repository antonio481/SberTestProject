package SberIT;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

class ConfProperties {
    private static FileInputStream fileInputStream;
    private static Properties PROPERTIES;

    static {
        try {
            fileInputStream = new FileInputStream("src/test/resources/conf.properties");
            PROPERTIES = new Properties();
            PROPERTIES.load(fileInputStream);
        } catch (IOException e) {
            e.printStackTrace();

        } finally {
            if (fileInputStream != null)
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
    }


    static String getProperty(String key) {
        return PROPERTIES.getProperty(key);
    }
}
