package net.thecobix.tag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class TagCompound extends Tag implements Iterable<Tag> {
    
    public static final TagCompound BASE_OBJECT = new TagCompound("base");
    
    public TagCompound(String name)
    {
        super(name);
        this.value = new ArrayList<Tag>();
    }

    @Override
    protected byte[] write() throws IOException
    {
        ArrayList<Tag> tags = (ArrayList<Tag>) this.value;
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buffer);
        writeVarInt(dos, 0x07);
        writeString(dos, this.name, StandardCharsets.UTF_8);
        ArrayList<Byte> bytes = new ArrayList<>();
        for(Tag t : tags) {
            byte[] all = t.write();
            for(int i = 0; i < all.length; i++)
            {
                bytes.add(all[i]);
            }
        }
        Byte[] ba = bytes.toArray(new Byte[bytes.size()]);
        writeVarInt(dos, ba.length);
        dos.write(toPrimitives(ba));
        return buffer.toByteArray();
    }
    
    byte[] toPrimitives(Byte[] oBytes)
    {
        byte[] bytes = new byte[oBytes.length];

        for(int i = 0; i < oBytes.length; i++) {
            bytes[i] = oBytes[i];
        }

        return bytes;
    }
    
    byte[] addElement(byte[] a, byte e) {
        a  = Arrays.copyOf(a, a.length + 1);
        a[a.length - 1] = e;
        return a;
    }

    @Override
    protected void read(byte[] data) throws IOException
    {
        ByteArrayInputStream buffer = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(buffer);
        if(readVarInt(dis) != 0x07)
            throw new IllegalStateException("Data is not representing the type TagCompound");
        this.name = readString(dis);
        byte[] ba = readByteArray(dis);
        this.value = (ArrayList<Tag>) TagInputStream.readTags(ba);
    }
    
    public TagString getTagString(String name) {
        ArrayList<Tag> tags = (ArrayList<Tag>) this.value;
        for(Tag t : tags) {
            if(t instanceof TagString) {
                if(t.getName().equals(name)) {
                    return (TagString) t;
                }
            }
        }
        return null;
    }
    
    public TagInt getTagInt(String name) {
        ArrayList<Tag> tags = (ArrayList<Tag>) this.value;
        for(Tag t : tags) {
            if(t instanceof TagInt) {
                if(t.getName().equals(name)) {
                    return (TagInt) t;
                }
            }
        }
        return null;
    }

    public TagDouble getTagDouble(String name) {
        ArrayList<Tag> tags = (ArrayList<Tag>) this.value;
        for(Tag t : tags) {
            if(t instanceof TagDouble) {
                if(t.getName().equals(name)) {
                    return (TagDouble) t;
                }
            }
        }
        return null;
    }
    
    public TagShort getTagShort(String name) {
        ArrayList<Tag> tags = (ArrayList<Tag>) this.value;
        for(Tag t : tags) {
            if(t instanceof TagShort) {
                if(t.getName().equals(name)) {
                    return (TagShort) t;
                }
            }
        }
        return null;
    }
    
    public TagLong getTagLong(String name) {
        ArrayList<Tag> tags = (ArrayList<Tag>) this.value;
        for(Tag t : tags) {
            if(t instanceof TagLong) {
                if(t.getName().equals(name)) {
                    return (TagLong) t;
                }
            }
        }
        return null;
    }
    
    public TagFloat getTagFloat(String name) {
        ArrayList<Tag> tags = (ArrayList<Tag>) this.value;
        for(Tag t : tags) {
            if(t instanceof TagFloat) {
                if(t.getName().equals(name)) {
                    return (TagFloat) t;
                }
            }
        }
        return null;
    }
    
    public TagByteArray getTagByteArray(String name) {
        ArrayList<Tag> tags = (ArrayList<Tag>) this.value;
        for(Tag t : tags) {
            if(t instanceof TagByteArray) {
                if(t.getName().equals(name)) {
                    return (TagByteArray) t;
                }
            }
        }
        return null;
    }
    
    public TagCompound getTagCompound(String name) {
        ArrayList<Tag> tags = (ArrayList<Tag>) this.value;
        for(Tag t : tags) {
            if(t instanceof TagCompound) {
                if(t.getName().equals(name)) {
                    return (TagCompound) t;
                }
            }
        }
        return null;
    }
    
    public TagSerializeableObject getTagSerializeableObject(String name) {
        ArrayList<Tag> tags = (ArrayList<Tag>) this.value;
        for(Tag t : tags) {
            if(t instanceof TagSerializeableObject) {
                if(t.getName().equals(name)) {
                    return (TagSerializeableObject) t;
                }
            }
        }
        return null;
    }
    
    public Tag getTag(String name) {
        ArrayList<Tag> tags = (ArrayList<Tag>) this.value;
        for(Tag t : tags) {
             if(t.getName().equals(name)) {
                 return t;
             }
        }
        return null;
    }
    
    public void put(Tag tag) {
        ArrayList<Tag> tags = (ArrayList<Tag>) this.value;
        Tag t;
        if((t = getTag(tag.getName())) != null) {
            ((ArrayList<Tag>)this.value).remove(t);
        }
        tags.add(tag);
        this.value = tags;
    }
    
    public void putString(String name, String value) {
        put(new TagString(name, value));
    }
    
    public void putDouble(String name, Double value) {
        put(new TagDouble(name, value));
    }
    
    public void putInt(String name, Integer value) {
        put(new TagInt(name, value));
    }
    
    public void putLong(String name, Long value) {
        put(new TagLong(name, value));
    }
    
    public void putShort(String name, Short value) {
        put(new TagShort(name, value));
    }
    
    public void putFloat(String name, Float value) {
        put(new TagFloat(name, value));
    }
    
    public void putFloat(String name, byte[] value) {
        put(new TagByteArray(name, value));
    }
    
    public void putSerializeableObject(String name, Serializable value) {
        put(new TagSerializeableObject(name, value));
    }

    @Override
    public Iterator<Tag> iterator()
    {
        ArrayList<Tag> tags = (ArrayList<Tag>) this.value;
        return new Iterator<Tag>() {

            int index = -1;
            
            @Override
            public boolean hasNext()
            {
                int vIndex = index;
                vIndex ++;
                try {
                    tags.get(vIndex);
                    return true;
                } catch(Exception e) {
                    return false;
                }
            }

            @Override
            public Tag next()
            {
                index ++;
                return tags.get(index);
            }
        };
    }
    
    public <T> T getTag(String name, T type) {
        ArrayList<Tag> tags = (ArrayList<Tag>) this.value;
        for(Tag t : tags) {
             if(t.getName().equals(name) && type.getClass().getName().equals(t.getClass().getName())) {
                 return (T) t;
             }
        }
        return null;
    }
    
}
