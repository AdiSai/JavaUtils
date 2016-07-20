package com.adithyasairam.javautils.simplelogger;

import com.adithyasairam.javautils.helpers.templates.Builder;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Adi on 8/9/2015.
 */
public class Logger {
    private static Logger instance;
    private static Log log;
    private static ArrayList<LogEntry> logEntries;
    private static Config config;

    public static class LoggerFactory implements Builder<Logger> {
        private static Config config = new BaseConfig();

        public LoggerFactory config(Config c) {
            this.config = c;
            return this;
        }

        @Override public Logger build() { return new Logger(config); }
    }

    public static abstract class Config {
        public String LOG_FILE_NAME = LOG_FILE_NAME(); /* LOG file name */
        public File LOG_FILE_PATH = LOG_FILE_PATH(); /* Path for saving the LOG File */
        public String LOG_STREAM_URL = LOG_STREAM_URL(); /* URL to Stream the LOG to */
        public int MAX_LOG_SIZE = MAX_LOG_SIZE(); /* Maximum LOG Size in MB */
        public int MIN_LOGGING_LEVEL = MIN_LOGGING_LEVEL(); /* Minimum Logging Level */
        public boolean APPEND_TO_FILE = APPEND_TO_FILE(); /* Whether or not the FileWriter should append when writing to the LOG file */

        public abstract String LOG_FILE_NAME();
        public abstract File LOG_FILE_PATH();
        public abstract String LOG_STREAM_URL();
        public abstract int MAX_LOG_SIZE();
        public abstract int MIN_LOGGING_LEVEL();
        public abstract boolean APPEND_TO_FILE();
    }

    public static class BaseConfig extends Config {
        @Override
        public String LOG_FILE_NAME() { return "LOG.log"; }

        @Override
        public File LOG_FILE_PATH() { return new File(""); }

        @Override
        public String LOG_STREAM_URL() { return null; }

        @Override
        public int MAX_LOG_SIZE() { return 10; }

        @Override
        public int MIN_LOGGING_LEVEL() { return Logger.LogLevels.ERROR; }

        @Override
        public boolean APPEND_TO_FILE() { return true; }
    }

    private Logger(Config c) {
        config = c;
        instance = this;
        log = new Log();
        logEntries = new ArrayList<LogEntry>();
    }

    public static final class LogLevels {
        public final static int NOTHING = 6;
        public final static int FATAL = 5;
        public final static int ERROR = 4;
        public final static int WARNING = 3;
        public final static int INFO = 2;
        public final static int DEBUG = 1;
        public final static int ALL = 0;

        public static boolean acceptableLoggerLevels(int level) {
            return (level == LogLevels.FATAL || level == LogLevels.ERROR ||
                    level == LogLevels.WARNING || level == LogLevels.INFO ||
                    level == LogLevels.DEBUG ||
                    level == LogLevels.NOTHING || level == LogLevels.ALL);
        }
    }

    private class Log {
        public void Fatal(String text, Class source) {
            LogEntry logEntry = new LogEntry(text, LogLevels.FATAL, source);
            addToLog(logEntry);
        }

        public void Error(String text, Class source) {
            LogEntry logEntry = new LogEntry(text, LogLevels.ERROR, source);
            addToLog(logEntry);
        }

        public void Warning(String text, Class source) {
            LogEntry logEntry = new LogEntry(text, LogLevels.WARNING, source);
            addToLog(logEntry);
        }

        public void Info(String text, Class source) {
            LogEntry logEntry = new LogEntry(text, LogLevels.INFO, source);
            addToLog(logEntry);
        }

        public void Debug(String text, Class source) {
            LogEntry logEntry = new LogEntry(text, LogLevels.DEBUG, source);
            addToLog(logEntry);
        }
    }

    public class LogEntry {
        public int level;
        public String message;
        public String sourceClass;
        public String date;

        public LogEntry(String text, int lvl, Class source) {
            message = text;
            level = lvl;
            sourceClass = source.getCanonicalName();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
            date = simpleDateFormat.format(new Date());
        }

        @Override
        public String toString() { return "Level " + level + " : " + date + " - " + message + " called from class " + sourceClass + "."; }
    }

    public Logger getInstance() { return instance; }

    public void Log(int level, String text, Class source) {
        if (!LogLevels.acceptableLoggerLevels(level)) { throw new IllegalArgumentException("Unsupported logging level"); }
        switch (level) {
            case 1: log.Debug(text, source); break;
            case 2: log.Info(text, source); break;
            case 3: log.Warning(text, source); break;
            case 4: log.Error(text, source); break;
            case 5: log.Fatal(text, source); break;
        }
    }

    private void addToLog(LogEntry logEntry) { logEntries.add(logEntry); }

    public String processLogEntries() {
        String data = "";
        for (LogEntry logEntry : logEntries) { data += logEntry.toString() + "\n"; }
        return data;
    }

    public String processLogEntries(int start, int end) {
        String data = "";
        for (int i  = start; i < end; i++) { data += logEntries.get(i).toString() + "\n"; }
        return data;
    }

    public ArrayList<LogEntry> getLogEntries() { return logEntries; }

    public boolean writeLogToFile() {
        File logFile = new File(config.LOG_FILE_PATH.getAbsolutePath(), config.LOG_FILE_NAME);
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(logFile, config.APPEND_TO_FILE));
            long max =  config.MAX_LOG_SIZE * 1000000;
            if (logFile.length() > max) { writeBytes(logFile, bufferedWriter, max); }
            else {
                bufferedWriter.write(processLogEntries());
            }
            bufferedWriter.close();
            return true;
        }
        catch (Exception e) { e.printStackTrace(); return false; }
    }

    private void writeBytes(File file, BufferedWriter bufferedWriter, long maxBytes) {
        try {
            byte[] bytes = FileUtils.readFileToByteArray(file);
            int i = 0;
            while (i < maxBytes) {
                bufferedWriter.write(Byte.toString(bytes[i]));
                i++;
            }
            bufferedWriter.close();
        }
        catch (Exception e) { e.printStackTrace(); }
    }
}