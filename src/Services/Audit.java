package Services;

import java.io.*;
import java.time.*;
import java.time.format.*;

public class Audit {
    private static Audit instance = null;
    private static String path = "audit.csv";

    private Audit() {}

    public static Audit getInstance() {
        if (instance == null) {
            instance = new Audit();
        }
        return instance;
    }

    public void log(String update) {
        try (PrintWriter manager = new PrintWriter(new FileWriter(path, true))) {
            String tm = LocalDateTime.now().
                        format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            manager.println(update + " at " + tm);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
