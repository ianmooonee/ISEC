package pt.isec.pa.four_in_a_row.model.memento;

import java.io.*;

public class Memento {
    byte[] snapshot;

    public Memento(Object obj) {
        try (ByteArrayOutputStream baos =
                     new ByteArrayOutputStream();
             ObjectOutputStream oos =
                     new ObjectOutputStream(baos)) {
            oos.writeObject(obj);
            snapshot = baos.toByteArray();
        } catch (Exception e) { snapshot = null; }
    }

    public Object getSnapshot() {
        if (snapshot == null) return null;
        try (ByteArrayInputStream bais =
                     new ByteArrayInputStream(snapshot);
             ObjectInputStream ois =
                     new ObjectInputStream(bais)) {
            return ois.readObject();
        } catch (Exception e) { return null; }
    }
}
