package ar.com.ada.maven.model.dto;

import java.util.Objects;

public class ZooDTO {
    private int id;
    private String name;
    private String size;
    private double budget;
    private CityDTO city;


    public ZooDTO(){}

    public int getId(){return id;}
    public void setId(int id){this.id=id;}
    public String getName(){return name;}
    public void setName(String name){this.name=name;}
    public String getSize(){return size;}
    public void setSize(String size){this.size=size;}
    public double getBudget(){return budget;}
    public void setBudget(double budget){this.budget=budget;}


    @Override
    public String toString() {
        return "ZooDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size='" + size + '\'' +
                ", budget=" + budget +'}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ZooDTO zooDTO = (ZooDTO) o;
        return id == zooDTO.id &&
                Double.compare(zooDTO.budget, budget) == 0 &&
                Objects.equals(name, zooDTO.name) &&
                Objects.equals(size, zooDTO.size);
    }

}
