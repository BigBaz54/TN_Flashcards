package eu.telecomnancy.io.json;

import eu.telecomnancy.model.compact.Compact;

public abstract class JsonFormatter<T> {
    protected T model;
    protected Compact<T> compact;
    protected boolean pretty = false;

    public void setPretty(boolean pretty) {
        this.pretty = pretty;
    }

    public JsonFormatter(T model) {
        this.model = model;
        this.compact = null;
    }

    public void setModel(T model) {
        this.model = model;
        this.compact = null;
    }

    protected abstract void compactModel();

    public abstract String toJson();

    public abstract void fromJson(String json, T model);
}
