package graphics;
import java.util.*;

public abstract class AbstractModelListener{

    private ArrayList<ModelListener> listeners;

    public AbstractModelListener(){
        listeners = new ArrayList<>();
    }

    public void addListener(ModelListener e){
        listeners.add(e);
    }

    public void removeListener(ModelListener e){
        listeners.remove(e);
    }

    protected void fireChange(){
        for(ModelListener e: listeners){
            e.modelChange(this);
        }
    }
}
