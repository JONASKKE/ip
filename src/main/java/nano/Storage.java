package nano;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Stores and loads Nano tasks from a file.
 */
public class Storage {
    private final Path filePath;

    /**
     * Creates a storage object using the specified file path.
     *
     * @param filePath path of the file used to store tasks.
     */
    public Storage(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    /**
     * Loads all tasks from the data file.
     *
     * @return The tasks loaded from the data file.
     * @throws NanoException If the data file cannot be read.
     */
    public ArrayList<Task> load() throws NanoException {
        ArrayList<Task> tasks = new ArrayList<>();

        if (!Files.exists(filePath)) {
            return tasks;
        }

        try {
            List<String> lines = Files.readAllLines(filePath);

            for (String line : lines) {
                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] parts = line.split(" \\| ", -1);

                String type = parts[0];
                boolean isDone = parts[1].equals("1");
                String description = parts[2];

                Task task;

                if (type.equals("T")) {
                    task = new Todo(description);

                } else if (type.equals("D")) {
                    String by = parts[3];
                    task = new Deadline(description, by);

                } else if (type.equals("E")) {
                    String from = parts[3];
                    String to = parts[4];
                    task = new Event(description, from, to);

                } else {
                    throw new NanoException("Invalid task type in data file.");
                }

                if (isDone) {
                    task.markDone();
                }

                tasks.add(task);
            }

        } catch (IOException e) {
            throw new NanoException("Unable to load tasks.");
        }

        return tasks;
    }

    /**
     * Saves the given tasks to the data file.
     *
     * @param tasks tasks to save.
     * @throws NanoException if the tasks cannot be saved.
     */
    public void save(ArrayList<Task> tasks) throws NanoException {
        try {
            Path parent = filePath.getParent();

            if (parent != null) {
                Files.createDirectories(parent);
            }

            ArrayList<String> lines = new ArrayList<>();

            for (Task task : tasks) {
                lines.add(task.toStorageString());
            }

            Files.write(filePath, lines);

        } catch (IOException e) {
            throw new NanoException("Unable to save tasks.");
        }
    }
}