import todo.TodoList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) {
        try {
            String content = Files.readString(Paths.get("todo.json"));
            TodoList list = TodoList.fromJson(content);
            if(list != null) {
                System.out.println(list);
            }
        } catch(IOException e) {
            System.out.println("failed to read todo.json");
        }
    }
}
