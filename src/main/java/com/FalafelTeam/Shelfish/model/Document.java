package com.FalafelTeam.Shelfish.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;
import java.util.Queue;

@Entity
public class Document {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private String type;
    private boolean availability; //надо поставить чтобы по дефолту был false
    private int availableCopies;
    private int edition;
    private String publisher;
    private String editor;
    private Date publicationDate;
    private boolean isBestseller;
    private Queue<User> queue;
    private int queueCount;


    public int getId(){
        return this.id;
    }

    public void setId(int id){
        this.id=id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getDescription(){
        return this.description;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getType(){
        return this.type;
    }

    public void setType(String type){
        this.type=type;
    }

    public boolean isAvailable(){
        return this.availability;
    }
    // не писал сеттер для availability потому что она становится true когда availableCopies > 0

    public int getAvailableCopies(){
        return availableCopies;
    }

    public void setAvailableCopies(int copies) throws Exception {
        if(copies>=0)
            this.availableCopies = copies;  // скорее всего выпилим этот метод из  финальной версии, так как
        else    // по факту нужен только инкремент и декеремент
            throw new Exception("Cannon be less than 0 copies");
    }

    public void incrementAvailableCopies(){
        if(!isAvailable()){
            this.availability=true;
        }
        this.availableCopies++;
    }

    public void decrementAvailableCopies() throws Exception {
        if(!isAvailable()){
            throw new Exception("Cannot be less than 0");
        }
        else {
            if (getAvailableCopies() == 1) {
                this.availability = false;
            }
            this.availableCopies--;
        }
    }

    public int getEdition(){
        return this.edition;
    }

    public void setEdition(int edition){
        this.edition = edition;
    }

    public String getPublisher(){
        return publisher;
    }

    public void setPublisher(String publisher){
        this.publisher=publisher;
    }

    public String getEditor(){
        return this.editor;
    }

    public void setEditor(String editor){
        this.editor=editor;
    }

    public Date getPublicationDate(){
        return this.publicationDate;
    }
    public void setPublicationDate(Date date){
        this.publicationDate = date;
    }

    public boolean isBestseller(){
        return isBestseller;
    }

    public void SetBestseller(boolean isBestseller){
        this.isBestseller = isBestseller;
    }

    public void addToQueue(User user){
        queue.add(user);
        queueCount++;
    }

    public void removeFromQueue() throws Exception { //удаляет без возвращения юзера
        if(queueCount>0) {
            queue.remove();
            queueCount--;
        }
        else throw new Exception("No users in a queue");
    }

    public User returnFromQueue() throws Exception { //удаляет с возвращением юзера
        if(queueCount>0) {
            queueCount--;
            return queue.remove();
        }
        else throw new Exception("No users in a queue");
    }

    public User peekQueue(){
        return queue.peek();
    }

    public int getQueueCount(){
        return queueCount;
    }
}

