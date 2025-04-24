package org.application;

import java.util.List;

public class Course {

    private String name;
    private List<String> organization;
    private List<String> skills;
    private String level;

    public Course(String name, List<String> organization, List<String> skills, String level){
        this.name=name;
        this.organization=organization;
        this.skills=skills;
        this.level=level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getOrganization() {
        return organization;
    }

    public void setOrganization(List<String> organization) {
        this.organization = organization;
    }

    public List<String> getSkills() {
        return skills;
    }

    public void setSkills(List<String> skills) {
        this.skills = skills;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
