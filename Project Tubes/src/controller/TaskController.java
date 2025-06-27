package controller;

import model.Task;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.io.*;
import java.util.List;
import java.util.stream.Collectors;

public class TaskController {
    private final File taskFile = new File("tasks.txt");

    public ObservableList<Task> loadTasks() {
        ObservableList<Task> tasks = FXCollections.observableArrayList();
        try (BufferedReader reader = new BufferedReader(new FileReader(taskFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 5) {
                    Task task = new Task(parts[0], parts[1], parts[2], parts[3], Boolean.parseBoolean(parts[4]));
                    tasks.add(task);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasks;
    }

    public void saveTask(Task task) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(taskFile, true))) {
            writer.write(String.join("|", task.getName(), task.getGivenDate(), task.getDeadline(), task.getDescription(), String.valueOf(task.isDone())));
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Task> getPendingTasks() {
        return loadTasks().stream()
                .filter(task -> !task.isDone())
                .collect(Collectors.toList());
    }

    public void saveAllTasks(List<Task> tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(taskFile))) {
            for (Task task : tasks) {
                writer.write(String.join("|", task.getName(), task.getGivenDate(), task.getDeadline(), task.getDescription(), String.valueOf(task.isDone())));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
