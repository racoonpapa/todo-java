package todo;

import adapters.LocalDateAdapter;
import adapters.PriorityAdapter;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class TodoList {
    private final ArrayList<TodoItem> items;

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(TodoItem.Priority.class, new PriorityAdapter())
            .create();

    public static TodoList fromJson(String json) {
        try {
            Type todoListType = new TypeToken<ArrayList<TodoItem>>() {}.getType();
            ArrayList<TodoItem> items = gson.fromJson(json, todoListType);
            return new TodoList(items);
        } catch(Exception e) {
            return null;
        }
    }

    public String toJson() {
        Type todoListType = new TypeToken<ArrayList<TodoItem>>() {}.getType();
        return gson.toJson(items, todoListType);
    }

    public TodoList() {
        items = new ArrayList<>();
    }

    public TodoList(ArrayList<TodoItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return items.stream().map(TodoItem::toString).collect(Collectors.joining("\n"));
    }
}
