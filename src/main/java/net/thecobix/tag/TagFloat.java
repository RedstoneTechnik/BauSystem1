package net.thecobix.tag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TagFloat extends Tag {
    
    public static final TagFloat BASE_OBJECT = new TagFloat("base", 0);

    public TagFloat(String name, float value)
    {
        super(name);
        this.value = new Float(value);
    }

    @Override
    protected byte[] write() throws IOException
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buffer);
        writeVarInt(dos, 0x06);
        writeString(dos, this.name, StandardCharsets.UTF_8);
        dos.writeFloat((float) this.value);
        return buffer.toByteArray();
    }

    @Override
    protected void read(byte[] data) throws IOException
    {
        ByteArrayInputStream buffer = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(buffer);
        if(readVarInt(dis) != 0x06)
            throw new IllegalStateException("Data is not representing the type TagFloat");
        this.name = readString(dis);
        this.value = new Float(dis.readFloat());
    }
    
    public Float getValue() {
        return (Float) this.value;
    }

    public void setValue(float value) {
        this.value = new Float(value);
    }
    
}
