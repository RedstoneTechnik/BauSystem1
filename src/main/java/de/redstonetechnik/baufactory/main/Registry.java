package de.redstonetechnik.baufactory.main;

import java.lang.reflect.Field;
import java.util.HashMap;

import net.thecobix.tag.Tag;
import net.thecobix.tag.TagCompound;

public class Registry<T extends CobixTagFormatSupported> extends CobixTagFormatSupported {

    Class<T> clazz;
    
    Field idfield;
    
    public Registry(Class<T> clazz1, String name) {
	this.clazz = clazz1;
	this.name = name;
	try {
	    this.idfield = clazz.getDeclaredField("regId");
	} catch (NoSuchFieldException | SecurityException e) {
	    throw new IllegalArgumentException("The class that is extending CobixTagFormatSupported must have a field named regId", e);
	}
    }
    
    private HashMap<Integer, T> entries = new HashMap<>();
    private String name;
    
    public int register(T object) {
	int id = getFreeID();
	if(id == -1) throw new RuntimeException("Registry limit of "+Integer.MAX_VALUE+" entries exceeded.");
	if(idfield.isAccessible()) {
	    try {
		idfield.set(object, id);
	    } catch (IllegalArgumentException | IllegalAccessException e) {
		e.printStackTrace();
	    }
	} else {
	    idfield.setAccessible(true);
	    try {
		idfield.set(object, id);
	    } catch (IllegalArgumentException | IllegalAccessException e) {
		e.printStackTrace();
	    }
	    idfield.setAccessible(false);
	}
	entries.put(id, object);
	return id;
    }
    
    public T get(int id) {
	return entries.get(id);
    }
    
    public HashMap<Integer, T> getAll() {
	return entries;
    }
    
    private int getFreeID() {
	for (int i = 0; i < Integer.MAX_VALUE; i++) {
	    if(entries.get(i) == null) return i;
	}
	return -1;
    }

    @Override
    public TagCompound save() {
	TagCompound out = new TagCompound(name);
	for(Integer id : entries.keySet()) {
	    out.put(entries.get(id).save());
	}
	return out;
    }

    @Override
    public void load(TagCompound tc) {
	if(tc == null) return;
	for(Tag t : tc) {
	    if(t instanceof TagCompound == false) {
		continue;
	    }
	    try {
		int id = Integer.parseInt(t.getName());
		T obj = clazz.newInstance();
		obj.load((TagCompound) t);
		if(idfield.isAccessible()) {
		    try {
			idfield.set(obj, id);
		    } catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		    }
		} else {
		    idfield.setAccessible(true);
		    try {
			idfield.set(obj, id);
		    } catch (IllegalArgumentException | IllegalAccessException e) {
			e.printStackTrace();
		    }
		    idfield.setAccessible(false);
		}
		entries.put(id, obj);
	    } catch(Exception e) {
		e.printStackTrace();
	    }
	}
    }
    
    public void unregister(int id) {
	entries.remove(id);
    }
    
    public int getID(T obj) {
	for(Integer id : entries.keySet()) {
	    if(obj.equals(entries.get(id))) return id;
	}
	return -1;
    }
    
}
