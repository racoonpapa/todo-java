package todo;

import adapters.LocalDateAdapter;
import adapters.PriorityAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

public class TodoList {
    private final HashMap<String, TodoItem> items = new HashMap<>();

    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
            .registerTypeAdapter(TodoItem.Priority.class, new PriorityAdapter())
            .create();

    public static TodoList fromJson(String json) {
        try {
            TodoList newList = new TodoList();
            Collection<TodoItem> list = gson.fromJson(json, new TypeToken<Collection<TodoItem>>(){}.getType());
            for(TodoItem item: list) newList.items.put(item.itemId, item);

            return newList;
        } catch(Exception e) {
            return null;
        }
    }

    public String toJson() {
        return gson.toJson(items.values(), new TypeToken<Collection<TodoItem>>() {}.getType());
    }

    @Override
    public String toString() {
        return items.values().stream().map(TodoItem::toString).collect(Collectors.joining("\n"));
    }
}
