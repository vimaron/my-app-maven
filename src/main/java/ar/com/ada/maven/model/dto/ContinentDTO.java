package ar.com.ada.maven.model.dto;

import java.util.Objects;

public class ContinentDTO {
    private int id;
    private String name;

    public ContinentDTO(int id, String name){
        this.id = id;
        this.name = name;
    }
    public ContinentDTO(int id){
        this.id=id;
    }
    public ContinentDTO(String name){
        this.name = name;
    }

    public int getId(){return id;}
    public void setId(int id){this.id = id;}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    @Override
    public String toString() {
        return "ContinentDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ContinentDTO that = (ContinentDTO) o;
        return id == that.id &&
                Objects.equals(name, that.name);
    }

}
