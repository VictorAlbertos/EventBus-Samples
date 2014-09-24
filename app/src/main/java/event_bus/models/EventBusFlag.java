package event_bus.models;

public class EventBusFlag {

    private Class _receiver;
    private String _message;
    private Object _content;

    public EventBusFlag(String message) {
        _message = message;
    }

    public EventBusFlag setFilterReceiverClass(Class receiver) {
        _receiver = receiver;
        return this;
    }

    public boolean isEventFor(Object candidate) {
        if (_receiver == null) return true;
        return _receiver == candidate.getClass();
    }

    public boolean isReallyEventFor(Object candidate) {
        if (_receiver == null) return false;
        return _receiver == candidate.getClass();
    }

    public String getMessage() {
        return _message;
    }

    public EventBusFlag addContent(Object content) {
        _content = content;
        return this;
    }

    public Object getContent() {
        return _content;
    }
}
