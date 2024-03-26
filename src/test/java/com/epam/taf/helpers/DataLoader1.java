package com.epam.taf.helpers;
import java.io.File;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.apache.log4j.Logger;

public class DataLoader1 {

    private static final Logger LOGGER = Logger.getLogger(DataLoader1.class.getName());
    public static TestData returnObj() {
        ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
        TestData obj = null;
        try {
            File file = new File("src/main/resources/TestData.yml");
            obj = mapper.readValue(file, TestData.class);

        } catch (Exception e) {
            LOGGER.error("Exception occurred while reading YML file");
            e.printStackTrace();
        }

        return obj;
    }

}
