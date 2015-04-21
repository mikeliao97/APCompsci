/*
 * Name: Qin Liao
 * Date: Jan 25th
 * Time Spent: 20 minutes
 * Reflection: This lab was not that hard. It was basically an extension
 * of the person lab that we had to implement student, teacher...
 * The only trouble I had was implementing the second constructor for
 * the chick class. I used Math.random and the modulus function to get
 * values of 1 and 0, to determine whether the chick was going to make
 * two different types of sounds. Also this lab was a very informative
 * one because I got the chance to brush up on my super constructor
 * by reading lesson 9 again. So overall, I really liked this lab
 * and I learned alot from this lab. Nothing else was really difficult
 * at all.
 * 
 * 
 * 
 * 
 * 
 */

import java.util.ArrayList;

public class FarmDriver{

  public static void main(String[] args){
    Farm driver = new Farm();
    driver.animalSounds();

   
  }
}

interface Animal{	
	public String getSound();
	public String getType();
	
}

/****************************
 * 
 * Cow Class
 *
 *****************************/
class Cow implements Animal{
	  private String myType;
	  private String mySound;

	  Cow(){
	    myType = "cow";
	    mySound = "moo";
	  }

	  public String getSound(){
	    return mySound; 
	  }

	  public String getType(){
	    return myType; 
	  }
}

/****************************
 * 
 * Pig Class
 *
 *****************************/
class Pig implements Animal{
	  private String myType;
	  private String mySound;

	  Pig(){
	    myType = "pig";
	    mySound = "oink!";
	  }

	  public String getSound(){
	    return mySound; 
	  }

	  public String getType(){
	    return myType; 
	  }
}
/****************************
 * 
 * Chick Class
 *
 *****************************/
class Chick implements Animal{
	  private String myType;
	  private String mySound;

	  Chick(){
	    myType = "Chick";
	    mySound = "Cheep";
	  }
	  
	  //put true, if the user wants two sounds
	  Chick(boolean twoSounds){
		    myType = "Chick";
		    
		    int rand = (int) ((Math.random() * 10) % 2);   //This gets you either 1 or 0
		    
		    if(rand == 1){		    
		    mySound = "Cheep";
		    }else{
		    	mySound = "Cluck"; 
		    }
		    
	  }

	  public String getSound(){
	    return mySound; 
	  }

	  public String getType(){
	    return myType; 
	    
	  }
}

/****************************
 * 
 * Named Cow Class
 *
 *****************************/
class NamedCow extends Cow implements Animal{
	
	  private String name;
	  
	  NamedCow(String name){
		  super();
		  this.name = name; 
	  }
	  
	  public String getName(){
		  return name;
	  }
	  public void setName(String name){
		  this.name = name;
	  }

}


/****************************
 * 
 * Farm Class
 *
 *****************************/
class Farm
{
   private ArrayList <Animal> myFarm;

   public Farm() {
      myFarm = new ArrayList <Animal>();
      myFarm.add(new Cow());
      myFarm.add(new Chick());
      myFarm.add(new Pig());
      myFarm.add(new NamedCow("Elsie"));
   }

   public void animalSounds(){
      Animal temp;
      for(int i = 0; i < myFarm.size(); i++){
         temp = myFarm.get(i);
         System.out.println(temp.getType() + " goes " + temp.getSound());
      }

      NamedCow named = (NamedCow)myFarm.get(3);
      System.out.println(named.getName());
   }
}