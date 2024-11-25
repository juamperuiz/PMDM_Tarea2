package com.ruizrabadanjuanpedro.pmdm_tarea2;

/**
 * Clase que contiene los datos de un personaje
 * @author Juampe Ruiz
 */
public class CharacterData {

    private final String image;
    private final String name;
    private final String role;
    private final String description;
    private final String skill;

    /**
     * Método para inicializar un personaje y sus datos
     * @param image image Ruta en el repositorio externo de la imagen del personaje
     * @param name Nombre del personaje
     * @param role Rol del personaje
     * @param description Descripción del personaje
     * @param skill Habilidad del personaje
     */
    public CharacterData(String image, String name, String role, String description, String skill) {
        this.image = image;
        this.name = name;
        this.role = role;
        this.description = description;
        this.skill = skill;
    }

    /**
     * Devuelve la ruta de la imagen de un personaje
     * @return String Ruta de la imagen
     */
    public String getImage() {
        return image;
    }

    /**
     * Devuelve el nombre de un personaje
     * @return String Nombre del personaje
     */
    public String getName() {
        return name;
    }

    /**
     * Devuelve el rol de un personaje
     * @return String Rol del personaje
     */
    public String getRole() {
        return role;
    }

    /**
     * Devuelve la descripción de un personaje
     * @return String Descripción del personaje
     */
    public String getDescription() {
        return description;
    }

    /**
     * Devuelve la habilidad de un personaje
     * @return String Habilidad del personaje
     */
    public String getSkill() {
        return skill;
    }

}

