package dev.elliotfrost.anarchybot;

import io.github.cdimascio.dotenv.Dotenv;

import java.util.Objects;

public class Config {

    private static final Dotenv dotenv = Dotenv.load();

    public static String get(String key) {
        return dotenv.get(key.toUpperCase());
    }

    public static String getPrefix() {
        if (Objects.equals(dotenv.get("DEV"), "true")) {
            return dotenv.get("PREFIX") + dotenv.get("PREFIX");
        } else {
            return dotenv.get("PREFIX");
        }
    }
    public static String getToken() {
        if (Objects.equals(dotenv.get("DEV"), "true")) {
            return dotenv.get("DEV-TOKEN");
        } else {
            return dotenv.get("BOT-TOKEN");
        }
    }
    public static String getSugg() {
        if (Objects.equals(dotenv.get("DEV"), "true")) {
            return dotenv.get("DEV-SUGGESTIONS-CHANNEL");
        } else {
            return dotenv.get("BOT-SUGGESTIONS-CHANNEL");
        }
    }
    public static String getSupp() {
        if (Objects.equals(dotenv.get("DEV"), "true")) {
            return dotenv.get("DEV-SUPPORT-CAT");
        } else {
            return dotenv.get("BOT-SUPPORT-CAT");
        }
    }
    public static String getStatus() {
        if (Objects.equals(dotenv.get("DEV"), "true")) {
            return dotenv.get("DEV-STATUS-CHANNEL");
        } else {
            return dotenv.get("STATUS-CHANNEL");
        }
    }
}