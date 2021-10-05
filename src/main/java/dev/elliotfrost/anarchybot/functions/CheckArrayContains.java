package dev.elliotfrost.anarchybot.functions;

import java.util.List;

public class CheckArrayContains {
    public boolean Checker(List<String> args, List<String> nono) {
        boolean there = false;
        for (String x : args) {
            if (nono.contains(x)) {
                there = true;
                break;
            }
        }
        return there;
    }
}
