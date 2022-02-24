package com.atypon.cache;

import com.atypon.FileReader;
import org.json.simple.JSONObject;

import java.util.*;

public class Type {
    private final HashMap<String, JSONObject> objects = new HashMap<>();
    private final Deque<String> LRUObject = new LinkedList<>();
    private final String schemaName;
    private final String typeName;

    public Type(String schemaName, String typeName) {
        this.schemaName = schemaName;
        this.typeName = typeName;
    }
    public HashMap<String, JSONObject> getObjects(){
        return objects;
    }
    public JSONObject getObject(String id){
        if(objects.containsKey(id)) {
            moveObjectToFirst(id);
            return objects.get(id);
        }
        JSONObject object = getObjectIfExist(id);
        if(object != null){
            addObject(id,object);
            return object;
        }
        return null;
    }
    private void moveObjectToFirst(String id){
        synchronized (LRUObject) {
            LRUObject.remove();
            LRUObject.addFirst(id);
        }
    }
    private JSONObject getObjectIfExist(String id){
        FileReader dataReader = FileReader.getInstance("/database");
        HashMap<String, JSONObject> allObjects =  dataReader.getAllFilesDataInDirectory(schemaName+"/"+typeName);
        if(allObjects.containsKey(id)) {
            return allObjects.get(id);
        }
        return null;
    }
    public void addObject(String id, JSONObject object){
        synchronized (objects){
            synchronized (LRUObject){
                if( !LRUObject.isEmpty()){
                    String leastUsedObject = LRUObject.removeLast();
                    objects.remove(leastUsedObject);
                }
                LRUObject.addFirst(id);
                objects.put(id,object);
            }
        }
    }

}
