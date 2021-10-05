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
            return dotenv.get("DEV_TOKEN");
        } else {
            return dotenv.get("BOT_TOKEN");
        }
    }
}