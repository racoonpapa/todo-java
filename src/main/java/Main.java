import todo.TodoList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            long startTime, endTime;
            double elapsedFrom, elapsedTo;

            String content = Files.readString(Paths.get("todo.json"));
            startTime = System.nanoTime();
            TodoList list = TodoList.fromJson(content);
            endTime = System.nanoTime();
            elapsedFrom = (endTime - startTime) * 0.000000001;

            if(list != null) {
                list.Print();

                startTime = System.nanoTime();
                String newJson = list.toJson();
                endTime = System.nanoTime();
                elapsedTo = (endTime - startTime) * 0.000000001;

                System.out.printf("fromJson - elapsed: %f seconds, toJson - elapsed: %f seconds\n", elapsedFrom, elapsedTo);
            }
        } catch(IOException e) {
            System.out.println("failed to read todo.json");
        }
    }
}
