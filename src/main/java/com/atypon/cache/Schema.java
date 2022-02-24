package com.atypon.cache;

import java.util.HashMap;

public class Schema implements Block{
    private final HashMap<String, Type> schemaTypes;

    public Schema(HashMap<String, Type> schemaData){
        this.schemaTypes = schemaData;
    }
    @Override
    public Object getBlockData() {
        return schemaTypes;
    }
    public Type getType(String typeName){
        if(containsType(typeName))
            return schemaTypes.get(typeName);
        return null;
    }
    public boolean containsType(String typeName){
        return schemaTypes.containsKey(typeName);
    }
}
