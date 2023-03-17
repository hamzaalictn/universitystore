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

        // Get a list of all files in the source directory
        List<File> files = Files.walk(sourceDirectory)
                .filter(Files::isRegularFile)
                .map(Path::toFile)
                .toList();

        // Copy each file to the report directory with the current date in the file name
        for (File file : files) {
            Path sourcePath = file.toPath();
            Path destinationPath = reportDirectory.resolve(sourceDirectory.relativize(sourcePath));
            Path destinationParentPath = destinationPath.getParent();
            String destinationFileName = destinationPath.getFileName().toString();
            int lastDotIndex = destinationFileName.lastIndexOf('.');
            String destinationFileExtension = "";
            if (lastDotIndex != -1) {
                destinationFileExtension = destinationFileName.substring(lastDotIndex);
                destinationFileName = destinationFileName.substring(0, lastDotIndex);
            }
            destinationFileName += "_" + dateString + destinationFileExtension;
            Path updatedDestinationPath = destinationParentPath.resolve(destinationFileName);
            Files.createDirectories(destinationParentPath);
            Files.copy(sourcePath, updatedDestinationPath);
        }

        System.out.println("Cucumber report copied successfully to report directory!");
    }
}
