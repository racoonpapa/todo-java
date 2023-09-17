package todo;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class TodoItem {
    public enum Priority {
        None(0), Low(1), Medium(2), High(3);

        private final int value;

        Priority(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static Priority from(int value) {
            for(Priority priority : Priority.values()) if(priority.value == value) return priority;
            throw new IllegalArgumentException("invalid integer value");
        }
    }

    public String id;
    public boolean done;
    public String content;

    @SerializedName("due_date")
    public LocalDate dueDate = null;
    public Priority priority = Priority.None;

    public TodoItem(String content) {
        this.content = content;
    }
    public TodoItem withDueDate(LocalDate dueDate) { this.dueDate = dueDate; return this; }
    public TodoItem withPriority(Priority priority) { this.priority = priority; return this; }
    public TodoItem setContent(String content) { this.content = content; return this; }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(String.format("[%c] %s", done?'x':' ', content));

        if(priority != Priority.None) builder.append(String.format(" [%s]", priority));
        if(dueDate != null) builder.append(String.format(" (%s)", dueDate));

        return builder.toString();
    }
}
