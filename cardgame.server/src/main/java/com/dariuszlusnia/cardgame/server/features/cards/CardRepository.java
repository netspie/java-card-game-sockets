package com.dariuszlusnia.cardgame.server.features.cards;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public final class CardRepository {
    private final String directory;

    public CardRepository(String directory) {
        this.directory = directory;
    }

    public boolean add(Card card) {
        try {
            var data = "id=" + card.Id + ";";
            data += "name=" + card.Name + ";";
            data += "attack=" + card.Attack + ";";
            data += "speed=" + card.Speed + ";";
            data += "health=" + card.Health;

            var dir = getOrCreateDirectory();
            var file = new File(dir, card.Id + ".txt");
            if (file.exists() && file.isFile()) {
                return false;
            }

            try (var writer = new FileWriter(file)) {
                writer.write(data);
                return true;
            }
        } catch (IOException e) {
            System.out.println("Error adding card: " + e.getMessage());
            return false;
        }
    }

    public boolean update(Card card) {
        try {
            var dir = getOrCreateDirectory();
            var file = new File(dir, card.Id + ".txt");
            if (!file.exists() || !file.isFile()) {
                return false;
            }

            if (!file.delete()) {
                return false;
            }

            return add(card);
        } catch (Exception e) {
            System.out.println("Error updating card: " + e.getMessage());
            return false;
        }
    }

    public Optional<List<Card>> getAll() {
        var dir = getOrCreateDirectory();
        var files = dir.listFiles();
        if (files == null) return Optional.empty();

        return Optional.of(Arrays.stream(files)
                .map(this::readCardFromFile)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .toList());
    }

    public Optional<Card> get(String id) {
        var dir = getOrCreateDirectory();
        var file = new File(dir, id + ".txt");
        if (!file.exists() || !file.isFile()) {
            return Optional.empty();
        }

        return readCardFromFile(file);
    }

    public boolean delete(String id) {
        var dir = getOrCreateDirectory();
        var file = new File(dir, id + ".txt");
        if (!file.exists() || !file.isFile()) {
            return false;
        }

        return file.delete();
    }

    private File getOrCreateDirectory() {
        var dir = new File(this.directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dir;
    }

    private Optional<Card> readCardFromFile(File file) {
        try {
            var data = Files.readString(file.toPath());
            var fields = data.split(";");

            String id = "";
            String name = "";
            int attack = 0;
            int speed = 0;
            int health = 0;

            for (var field : fields) {
                var fieldKV = field.split("=");
                if (fieldKV.length < 2) continue;

                switch (fieldKV[0]) {
                    case "id" -> id = fieldKV[1];
                    case "name" -> name = fieldKV[1];
                    case "attack" -> attack = Integer.parseInt(fieldKV[1]);
                    case "speed" -> speed = Integer.parseInt(fieldKV[1]);
                    case "health" -> health = Integer.parseInt(fieldKV[1]);
                }
            }

            return Optional.of(new Card(id, name, attack, speed, health));

        } catch (IOException | NumberFormatException e) {
            System.out.println("Error reading file: " + file.getName() + " - " + e.getMessage());
            return Optional.empty();
        }
    }
}
