package com.ruizrabadanjuanpedro.pmdm_tarea2;

public class CharacterData {

    private final String image;
    private final String name;
    private final String role;
    private final String description;
    private final String skill;

    public CharacterData(String image, String name, String role, String description, String skill) {
        this.image = image;
        this.name = name;
        this.role = role;
        this.description = description;
        this.skill = skill;
    }

    public String getImage() {
        return image;
    }
    public String getName() {
        return name;
    }
    public String getRole() {
        return role;
    }
    public String getDescription() {
        return description;
    }
    public String getSkill() {
        return skill;
    }
}

