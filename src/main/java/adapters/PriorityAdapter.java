package adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import todo.TodoItem;

import java.io.IOException;

public class PriorityAdapter extends TypeAdapter<TodoItem.Priority> {
    @Override
    public void write(JsonWriter jsonWriter, TodoItem.Priority priority) throws IOException {
        if(priority == null) {
            jsonWriter.nullValue();
        } else {
            jsonWriter.value(priority.getValue());
        }
    }

    @Override
    public TodoItem.Priority read(JsonReader jsonReader) throws IOException {
        return TodoItem.Priority.from(jsonReader.nextInt());
    }
}
