package com.applitools.hackathon.utils;

import org.openqa.selenium.os.ExecutableFinder;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DriverServiceExecutable {

    public static DriverServiceExecutableBuilder called(String exeName) {
        return new DriverServiceExecutableBuilder(exeName);
    }

    public static class DriverServiceExecutableBuilder {

        private final String exeName;

        public DriverServiceExecutableBuilder(String exeName) {
            this.exeName = exeName;
        }

        public File asAFile() {

            Path binaryPath = asAPath();

            return binaryPath != null ? binaryPath.toFile() : null;
        }

        public Path asAPath() {

            String pathOnFilesystem = new ExecutableFinder().find(exeName);

            return Paths.get(pathOnFilesystem);
        }
    }
}
