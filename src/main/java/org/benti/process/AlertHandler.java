package org.benti.process;

import org.benti.log.SimpleLogger;
import org.benti.process.finder.IProcessFinder;
import org.benti.process.finder.ProcessFinderFactory;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AlertHandler {

    private final int targetLevel;
    private final Pattern pattern;
    private final String character;
    private static final Pattern CHARACTER_NAME_PATTERN = Pattern.compile("Name: \\[(.+?)]");
    private final IProcessFinder processFinder = ProcessFinderFactory.getProcessFinder();

    public AlertHandler(int targetLevel, String detectionRegex, String character) {
        this.targetLevel = targetLevel;
        this.character = character;
        this.pattern = Pattern.compile(detectionRegex);
    }

    public boolean hasAlert() {
        List<Process> processes = processFinder.getProcessesByTitle("HeroesMU", false);
        for (Process process : processes) {
            if (this.character != null && !this.character.isEmpty()) {
                Matcher characterMatcher = CHARACTER_NAME_PATTERN.matcher(process.getTitle());
                if (characterMatcher.find()) {
                    String characterMatch = characterMatcher.group(1);
                    if (!characterMatch.equals(this.character)) {
                        continue;
                    }
                } else {
                    continue;
                }
            }
            Matcher titleMatcher = pattern.matcher(process.getTitle());
            if (titleMatcher.find()) {
                String value = titleMatcher.group(1);
                try {
                    return Integer.parseInt(value) >= this.targetLevel;
                } catch (NumberFormatException e) {
                    SimpleLogger.log("Error parsing group expression: " + value);
                }
            }
            return false;
        }
        return false;
    }

    public String getAlert() {
        return this.character != null && !this.character.isEmpty() ? "PJ " + this.character + " listo para reset!" : "Reset listo!";
    }
}
