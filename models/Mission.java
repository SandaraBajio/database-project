package models;

public class Mission {
    private int id;
    private String name;
    private String type;
    private String location;
    private String status;
    private String occurrenceDate;
    private String operationType;
    private int volunteersNeeded;

    // Constructor with parameters for adding a new mission
    public Mission(String name, String type, String location, String status, 
                   String occurrenceDate, String operationType, int volunteersNeeded) {
        this.name = name;
        this.type = type;
        this.location = location;
        this.status = status;
        this.occurrenceDate = occurrenceDate;
        this.operationType = operationType;
        this.volunteersNeeded = volunteersNeeded;
    }

    // Constructor with id parameter for updating an existing mission
    public Mission(int id, String name, String type, String location, String status, 
                   String occurrenceDate, String operationType, int volunteersNeeded) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.location = location;
        this.status = status;
        this.occurrenceDate = occurrenceDate;
        this.operationType = operationType;
        this.volunteersNeeded = volunteersNeeded;
    }

    public Mission() {
    }

    // Getters and Setters for all attributes
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getOccurrenceDate() { return occurrenceDate; }
    public void setOccurrenceDate(String occurrenceDate) { this.occurrenceDate = occurrenceDate; }

    public String getOperationType() { return operationType; }
    public void setOperationType(String operationType) { this.operationType = operationType; }

    public int getVolunteersNeeded() { return volunteersNeeded; }
    public void setVolunteersNeeded(int volunteersNeeded) { this.volunteersNeeded = volunteersNeeded; }

    @Override
    public String toString() {
        return "Mission{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", location='" + location + '\'' +
                ", status='" + status + '\'' +
                ", occurrenceDate='" + occurrenceDate + '\'' +
                ", operationType='" + operationType + '\'' +
                ", volunteersNeeded=" + volunteersNeeded +
                '}';
    }
}
