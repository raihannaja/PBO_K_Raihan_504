package controller;

import model.Schedule;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ScheduleController {
    private static final File file = new File("schedules.txt");

    public static List<Schedule> loadSchedules() {
        List<Schedule> list = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 4) {
                    list.add(new Schedule(parts[0], parts[1], parts[2], parts[3]));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static void saveSchedules(List<Schedule> list) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Schedule s : list) {
                writer.write(String.join("|", s.getMatkul(), s.getHari(), s.getJam(), s.getRuangan()));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}