package framework.entity;

import javax.persistence.*;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private int gameState;
    
    @Column
    private String name;
    
    @Column
    private String room;
    
    public int getGameState(){
    	return gameState;
    }
    
    public void setGameState(int gameState){
		this.gameState += gameState;
	}
    
    
    public String getName(){
    	return name;
    }
    
    public void setName(String name){
		this.name = name;
	}
    
    public String getRoom(){
    	return room;
    }
    
    public void setRoom(String room){
		this.room = room;
	}
    
    
    
}
