package com.baseframework;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;


public class AutomationLog {

    private static AutomationLog automationLogInstance = null;

    private static FileHandler fh;

    private final Logger logger = Logger.getLogger(AutomationLog.class.getSimpleName());

    AutomationLog() {

        logger.setUseParentHandlers(false);
        LogFormatter formatter = new LogFormatter();
        ConsoleHandler handler = new ConsoleHandler();

        handler.setFormatter(formatter);
        logger.addHandler(handler);

        try {
            Date date = new Date();
            long timeStamp = date.getTime();
            fh = new FileHandler(AutomationConfiguration.getConfigurationValueForProperty("Logfile") + "//" + timeStamp + ".log");

        } catch (Exception e) {

            System.out.println(e.getMessage());

        }

        logger.addHandler(fh);
        fh.setFormatter(formatter);

        logger.info(handler.toString());

    }

    public static AutomationLog getAutomationLog() {
        if (automationLogInstance == null) {
            automationLogInstance = new AutomationLog();
        }
        return automationLogInstance;
    }

    private static Logger getLogger() {
        return AutomationLog.getAutomationLog().logger;
    }

    public static void info(String message) {
        String logMessageFormat = formatMessageWithInvocationInformation(message);
        getLogger().info(logMessageFormat);

    }

    private static String formatMessageWithInvocationInformation(String message) {

        int stackTraceDepth = 3;
        String className;
        className = Thread.currentThread().getStackTrace()[stackTraceDepth].getClassName();
        String methodName = Thread.currentThread().getStackTrace()[stackTraceDepth].getMethodName();
        String simpleClassName = className.substring(className.lastIndexOf(".") + 1);
        return "[ " + simpleClassName + " : " + methodName + " ] " + message;
    }

    static class LogFormatter extends Formatter {

        private final DateFormat df = new SimpleDateFormat(
                AutomationConfiguration.getConfigurationValueForProperty("dateformat-logging"));

        private final String applicationName = AutomationConfiguration
                .getConfigurationValueForProperty("applicationName");

        public String format(LogRecord record) {
            StringBuilder builder = new StringBuilder(1000);
            builder.append(df.format(new Date(record.getMillis()))).append(" - ");
            builder.append("[").append(applicationName).append("] - ");
            builder.append("[").append(record.getLevel()).append("] - ");
            builder.append(formatMessage(record));
            builder.append("\n");
            return builder.toString();
        }
        /*
               public static void startTestCase(String testCaseName) {
                   String logMessageFormat = formatMessageWithInvocationInformation("Begin Test case : " + testCaseName);
                   getLogger().info(logMessageFormat);
               }

               public static void endTestCase(String testCaseName) throws Exception {
                   String logMessageFormat = formatMessageWithInvocationInformation("End Test case : " + testCaseName + "\n");
                   getLogger().info(logMessageFormat);

               }

              public static void warn(String message) {
                   String logMessageFormat = formatMessageWithInvocationInformation(message);
                   getLogger().warning(logMessageFormat);
               }

               public static void error(String message) {
                   String logMessageFormat = formatMessageWithInvocationInformation(message);
                   getLogger().severe(logMessageFormat);
               }

               public static void fatal(String message) {
                   String logMessageFormat = formatMessageWithInvocationInformation(message);
                   getLogger().severe(logMessageFormat);
               } */
        public String getHead(Handler h) {
            return super.getHead(h);
        }

        public String getTail(Handler h) {
            return super.getTail(h);
        }
    }
}
