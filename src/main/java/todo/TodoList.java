package todo;

import adapters.LocalDateAdapter;
import adapters.PriorityAdapter;
import com.google.gson.*;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
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
            TodoList list = new TodoList();

            JsonArray jsonArray = JsonParser.parseString(json).getAsJsonArray();
            for(JsonElement element: jsonArray) {
                TodoItem item = gson.fromJson(element, TodoItem.class);
                list.items.put(item.itemId, item);
            }

            return list;
        } catch(Exception e) {
            return null;
        }
    }

    public String toJson() {
        Type todoListType = new TypeToken<Collection<TodoItem>>() {}.getType();
        return gson.toJson(items.values(), todoListType);
    }

    @Override
    public String toString() {
        return items.values().stream().map(TodoItem::toString).collect(Collectors.joining("\n"));
    }
}
