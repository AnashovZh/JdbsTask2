package org.example.peakSoft.model;

public class JobBuilder {
    private Long id;
    private String position;
    private String profession;
    private String description;
    private int experience;

    public JobBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public JobBuilder setPosition(String position) {
        this.position = position;
        return this;
    }

    public JobBuilder setProfession(String profession) {
        this.profession = profession;
        return this;
    }

    public JobBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public JobBuilder setExperience(int experience) {
        this.experience = experience;
        return this;
    }

    public Job createJob() {
        return new Job(id, position, profession, description, experience);
    }
}