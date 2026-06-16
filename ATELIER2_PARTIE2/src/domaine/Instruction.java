package domaine;

import java.time.Duration;

public class Instruction {

    private String description;
    private Duration dureeEnMinutes;

    public Instruction (String description, int dureeEnMinutes){
        this.description = description;
        this.dureeEnMinutes = Duration.ofMinutes(dureeEnMinutes);
    }

    public Duration getDureeEnMinutes() {
        return dureeEnMinutes;
    }

    public void setDureeEnMinutes(Duration dureeEnMinutes) {
        this.dureeEnMinutes = dureeEnMinutes;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        long heures = dureeEnMinutes.toHours();
        long minutes = dureeEnMinutes.toMinutes() % 60;
        String temps = String.format("%02d:%02d" , heures , minutes);
        return "(" + temps + ")" + " " + description;
    }
}
