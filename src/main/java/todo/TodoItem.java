package todo;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

public class TodoItem {
    public enum Priority {
        None(0, "None"), Low(1, "Low"), Medium(2, "Medium"), High(3, "High");

        private final String text;
        private final int value;

        Priority(int value, String text) {
            this.text = text;
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return text;
        }

        public static Priority from(int value) {
            for(Priority priority : Priority.values()) {
                if(priority.value == value) {
                    return priority;
                }
            }
            throw new IllegalArgumentException("invalid integer value");
        }
    }

    @SerializedName("id")
    public String itemId;
    public boolean done;
    public String content;

    @SerializedName("due_date")
    public LocalDate dueDate = null;
    public Priority priority = Priority.None;

    public TodoItem(String content) {
        this.content = content;
    }
    public TodoItem withDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
        return this;
    }
    public TodoItem withPriority(Priority priority) {
        this.priority = priority;
        return this;
    }
    public TodoItem setContent(String content) {
        this.content = content;
        return this;
    }

    @Override
    public String toString() {
        String ret = String.format("[%c] %s", done?'x':' ', content);

        if(priority != Priority.None) {
            ret = ret + String.format(" [%s]", priority);
        }

        if(dueDate != null) {
            ret = ret + String.format(" (%s)", dueDate);
        }

        return ret;
    }
}
