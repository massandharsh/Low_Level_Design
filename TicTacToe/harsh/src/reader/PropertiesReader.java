package reader;

import java.io.*;
import java.util.Properties;

public class PropertiesReader {
    public static final String PATH = "application.properties";
    public static final InputStream input;
    public static final Properties props = new Properties();
    static {
        try {
            File file = new File(PropertiesReader.class.getClassLoader().getResource(PATH).getFile());
            input = new FileInputStream(file);
            props.load(input);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static String readProps(String property) {
        return props.getProperty(property);
    }
}
