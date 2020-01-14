package ar.com.ada.maven.model.dto;

public class AnimalHasZooDTO {
    private int id;
    private AnimalDTO animal;
    private ZooDTO zoo;


    public AnimalHasZooDTO(){}

    public int getId(){return id;}
    public void setId(int id){this.id=id;}

    @Override
    public String toString() {
        return "AnimalHasZooDTO{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalHasZooDTO that = (AnimalHasZooDTO) o;
        return id == that.id;
    }

}
