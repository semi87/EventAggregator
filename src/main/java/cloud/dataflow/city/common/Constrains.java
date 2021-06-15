package cloud.dataflow.city.common;

import java.nio.file.Path;
import java.nio.file.Paths;

public class Constrains {
    public static final Path resourcePath = Paths.get("src", "main", "resources");
    public static final Path avscDirPath = Paths.get(Constrains.resourcePath.toString(), "avsc");
}
