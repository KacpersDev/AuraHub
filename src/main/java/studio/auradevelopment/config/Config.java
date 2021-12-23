package studio.auradevelopment.config;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import studio.auradevelopment.Hub;

import java.io.File;
import java.io.IOException;

public class Config {

    public Config(File file, FileConfiguration configuration, String name){

        if (!(file.exists())) {
            file.getParentFile().mkdir();
            Hub.getInstance().saveResource(name, false);
        }

        try {
            configuration.load(file);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }
}
