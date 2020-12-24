import java.util.*;

public class Oasis {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int x = scanner.nextInt();
        int y = scanner.nextInt();

        Person.setTemple(x,y);

        List<Person> people = new ArrayList<Person>();

        for(int i=0; i<n; i++){
            int x_ = scanner.nextInt();
            int y_ = scanner.nextInt();
            String name_ = scanner.next();

            people.add(new Person(x_,y_,name_));
        }

        //Comparator<Person> comparator = Comparator.comparing( p -> p.distance2From(x,y));
        //Collections.sort(people,comparator);

        Collections.sort(people);

        for(Person person: people){
            System.out.println(person.name+":"+person.distance2From(x,y));
        }
    }
}

class Person implements Comparable<Person>{
    public static int templeX;
    public static int templeY;

    public final int x;
    public final int y;
    public final String name;

    public Person(int x, int y, String name){
        this.x= x;
        this.y = y;
        this.name = name;
    }

    public long distance2From(int x,int y){
        long delX = this.x - x;
        long delY = this.y - y;
        return delX*delX + delY*delY;
    }

    public static void setTemple(int x, int y){
        templeX = x;
        templeY = y;
    }

    public int compareTo(Person other){
        return Long.compare(distance2From(Person.templeX,Person.templeY) ,
                            other.distance2From(Person.templeX,Person.templeY));
    }
}