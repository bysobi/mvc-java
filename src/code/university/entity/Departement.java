package code.university.entity;

import java.sql.Timestamp;

public class Departement {
    public int id;
    public int parent_id;
    public String name;
    public boolean enabled;
    public Timestamp created_at;
    public Timestamp updated_at;

    public Departement(){
    }
    
    public Departement(Integer id, Integer parent_id, String name) {
	this.id = id;
	this.parent_id = parent_id;
	this.name = name;

    }
    public Integer getId(){
	return this.id;
    }

    public Integer getParentId(){
	return this.parent_id;
    }

    public String getName(){
	return this.name;
    }

    public void setId(Integer data){
	this.id = data;
    }

    public void setParentId(Integer data){
	this.parent_id = data;
    }

    public void setName(String data){
	this.name = data;
    }
}
