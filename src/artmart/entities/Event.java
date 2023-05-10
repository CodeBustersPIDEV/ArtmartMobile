package artmart.entities;


public class Event {

    private int eventid;
    private String name;
    private String location;
    private String type;
    private String description;
    private double entryfee;
    private int capacity;
    private String startdate;
    private String endate;
    private int user;
    private String image;
    private String status;

    public Event() {
    }

    public Event(int eventid, int user, String name, String location, String type, String description, double entryfee, int capacity, String startdate, String endate, String image, String status) {
        this.eventid = eventid;
        this.user = user;
        this.name = name;
        this.location = location;
        this.type = type;
        this.description = description;
        this.entryfee = entryfee;
        this.capacity = capacity;
        this.startdate = startdate;
        this.endate = endate;
        this.image = image;
        this.status = status;
    }

    public Event(int user, String name, String location, String type, String description, double entryfee, int capacity, String startdate, String endate, String image, String status) {
        this.user = user;
        this.name = name;
        this.location = location;
        this.type = type;
        this.description = description;
        this.entryfee = entryfee;
        this.capacity = capacity;
        this.startdate = startdate;
        this.endate = endate;
        this.image = image;
        this.status = status;
    }

    public Event(int user, String name, String location, String type, String description, double entryfee, int capacity, String startdate, String endate, String image) {
        this.user = user;
        this.name = name;
        this.location = location;
        this.type = type;
        this.description = description;
        this.entryfee = entryfee;
        this.capacity = capacity;
        this.startdate = startdate;
        this.endate = endate;        
        this.image = image;

    }

    public Event(String name, String location, String type, String description, double entryfee, int capacity, String startdate, String endate, String status) {
        this.name = name;
        this.location = location;
        this.type = type;
        this.description = description;
        this.entryfee = entryfee;
        this.capacity = capacity;
        this.startdate = startdate;
        this.endate = endate;
        this.status = status;
    }
    
    public int getEventid() {
        return eventid;
    }

    public void setEventid(int eventid) {
        this.eventid = eventid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getEntryfee() {
        return entryfee;
    }

    public void setEntryfee(double entryfee) {
        this.entryfee = entryfee;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return endate;
    }

    public void setEnddate(String endate) {
        this.endate = endate;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

//    @Override
//    public String toString() {
//        return name;
//    }

    @Override
    public String toString() {
        return "Event{" + "eventid=" + eventid + ", name=" + name + ", location=" + location + ", type=" + type + ", description=" + description + ", entryfee=" + entryfee + ", capacity=" + capacity + ", startdate=" + startdate + ", endate=" + endate + ", user=" + user + ", image=" + image + ", status=" + status + '}';
    }
}
