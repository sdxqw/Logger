package io.github.sdxqw.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Logger {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_GREEN = "\u001B[32m";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    private static void log(LogLevel level, Object message) {
        String color = "";
        switch (level) {
            case DEBUG:
                color = ANSI_BLUE;
                break;
            case INFO:
                color = ANSI_GREEN;
                break;
            case WARN:
                color = ANSI_YELLOW;
                break;
            case ERROR:
                color = ANSI_RED;
                break;
        }
        LocalDateTime now = LocalDateTime.now();
        String logLevel = String.format("%s", level.name());
        String logMessage = String.format("[%s] [%s] > %s%s%s", now.format(formatter), logLevel, color, message, ANSI_RESET);
        System.out.println(logMessage);
        logToFile(logMessage);
    }


    private static void log(LogLevel level, String format, Object... args) {
        String message = String.format(format, args);
        log(level, message);
    }

    private static void logToFile(String logMessage) {
        try (PrintWriter writer = new PrintWriter(new FileWriter("log.txt", true))) {
            writer.println(stripANSI(logMessage));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String stripANSI(String text) {
        return text.replaceAll("\u001B\\[[;\\d]*m", "");
    }

    public static void debug(String format, Object... message) {
        log(LogLevel.DEBUG, format, message);
    }

    public static void debug(Object message) {
        log(LogLevel.DEBUG, message);
    }

    public static void info(String format, Object... message) {
        log(LogLevel.INFO, format, message);
    }

    public static void info(Object message) {
        log(LogLevel.INFO, message);
    }

    public static void warn(String format, Object... message) {
        log(LogLevel.WARN, format, message);
    }

    public static void warn(Object message) {
        log(LogLevel.WARN, message);
    }

    public static void error(String format, Object... message) {
        log(LogLevel.ERROR, format, message);
    }

    public static void error(Object message) {
        log(LogLevel.ERROR, message);
    }
}

