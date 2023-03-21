package com.universitystore.runners;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class CucumberReportCopier {

    public static void main(String[] args) throws IOException {

        // Specify the paths of the source and destination directories
        Path sourceDirectory = Paths.get("path/to/source/directory");

        // Get the current date and format it as "yyyyMMdd"
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String dateString = dateFormatter.format(currentDate);

        // Create the report directory with the current date in the folder name
        String reportDirectoryName = "report_" + dateString;
        Path reportDirectory = sourceDirectory.getParent().resolve(reportDirectoryName);
        Files.createDirectories(reportDirectory);

        List<File> files = Files.walk(sourceDirectory)
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .toList();


        System.out.println("Cucumber report copied successfully to report directory!");
    }
}
