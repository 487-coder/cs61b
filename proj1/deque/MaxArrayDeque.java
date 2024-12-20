package deque;
import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T>{
    private Comparator<T> c;
    public MaxArrayDeque(Comparator<T> c){
        super();
        this.c = c;

    }
    public T max(){
        if (this.isEmpty()){
            return null;
        }
        T  max = this.get(0);
        for (int i  = 1; i < this.size(); i++){
            if (this.c.compare(max, this.get(i)) < 0){
                max = this.get(i);
            }

        }
        return max;
    }
    public T max(Comparator<T> c){
        if (this.isEmpty()){
            return null;
        }
        T  max = this.get(0);
        for (int i  = 1; i < this.size(); i++){
            if (c.compare(max, this.get(i)) < 0){
                max = this.get(i);
            }

        }
        return max;
    }
}
