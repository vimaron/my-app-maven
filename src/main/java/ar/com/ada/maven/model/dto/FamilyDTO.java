package ar.com.ada.maven.model.dto;


import java.util.Objects;

public class FamilyDTO {
    private int id;
    private String name;

    public FamilyDTO(){}

    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}

    public FamilyDTO(int id, String name){
        this.id=id;
        this.name=name;
    }

    @Override
    public String toString() {
        return "FamilyDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FamilyDTO familyDTO = (FamilyDTO) o;
        return id == familyDTO.id &&
                Objects.equals(name, familyDTO.name);
    }

}
