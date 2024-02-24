package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;
import java.util.List;

//proxy para o Drawing - funciona como wrapper
public class DrawingManager{
    public static final String PROP_TOOLS="_tools_";
    public static final String PROP_FIGURES="_figures_";

    Drawing data; //context no TP
    PropertyChangeSupport pcs; //regista e notifica listeners

    public DrawingManager(){
        data=new Drawing();
        pcs=new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(String property, PropertyChangeListener listener){
        pcs.addPropertyChangeListener(property, listener);
    }

    public double getR() {
        return data.getR();
    }
    public double getG() {
        return data.getG();
    }
    public double getB() {
        return data.getB();
    }

    public void setRGB(double r, double g, double b) {
        data.setRGB(r, g, b);
        pcs.firePropertyChange(PROP_TOOLS, null, null);
    }

    public Figure.FigureType getCurrentType() {
        return data.getCurrentType();
    }

    public void setCurrentType(Figure.FigureType currentType) {
        data.setCurrentType(currentType);
        pcs.firePropertyChange(PROP_TOOLS,null ,null);
    }

    public void createFigure(double x, double y) {
        data.createFigure(x, y);
        pcs.firePropertyChange(PROP_FIGURES, null, null);
    }

    public void updateCurrentFigure(double x, double y) {
        data.updateCurrentFigure(x, y);
        pcs.firePropertyChange(PROP_FIGURES, null, null);
    }

    public void finishCurrentFigure(double x, double y) {
        data.finishCurrentFigure(x, y);
        pcs.firePropertyChange(PROP_FIGURES, null, null);
    }

    public Figure getCurrentFigure() {
        return data.getCurrentFigure();
    }

    public List<Figure> getList() {
        return data.getList();
    }

    public void clearAll() {
        data.clearAll();
        pcs.firePropertyChange(PROP_FIGURES, null, null);
    }

    public void removeLast() {
        data.removeLast();
        pcs.firePropertyChange(PROP_FIGURES, null, null);
    }

    public void remove(int itemIndex){
        data.remove(itemIndex);
        pcs.firePropertyChange(PROP_FIGURES,null,null);
    }

    public void init(){
        data.init();
        pcs.firePropertyChange(PROP_FIGURES,null,null);
    }

    public boolean load(File file){
        try(ObjectInputStream ois=new ObjectInputStream(new FileInputStream(file))){
            data=(Drawing)ois.readObject();
        }catch (Exception e){
            return false;
        }

        pcs.firePropertyChange(PROP_FIGURES, null, null);
        pcs.firePropertyChange(PROP_TOOLS, null, null);

        return true;
    }

    public boolean save(File file){
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(data);
        }catch (Exception e){
            return false;
        }

        return true;
    }
}
