package com.base.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Collections;
import java.util.List;

public class FileUtil {

    public static List<String> readAllLines(String path) {

        Path filePath = Paths.get(path);

        List<String> lines = Collections.emptyList();
        if (!filePath.toFile().exists()) {
            return lines;
        }

        try {
            lines = Files.readAllLines(filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return lines;
    }

    /**
     * 存在则覆盖
     */
    public static void defaultCopy(Path source, Path target) {
        copy(source, target, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * 存在则覆盖
     */
    public static void defaultCopy(String sourcePath, String targetPath) {
        copy(Paths.get(sourcePath), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
    }

    public static void copy(Path source, Path target, StandardCopyOption standardCopyOption) {
        try {
            Files.copy(source, target, standardCopyOption);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 文件已存在则覆盖
     */
    public static void defaultMove(Path source, Path target) {
        move(source, target, StandardCopyOption.REPLACE_EXISTING);
    }

    /**
     * 文件已存在则覆盖
     */
    public static void defaultMove(String sourcePath, String targetPath) {
        move(Paths.get(sourcePath), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
    }

    public static void move(Path source, Path target, StandardCopyOption standardCopyOption) {


        try {
            Files.move(source, target, standardCopyOption);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
