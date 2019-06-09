package net.thecobix.tag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

public class TagDouble extends Tag {
    
    public static final TagDouble BASE_OBJECT = new TagDouble("base", 0);

    public TagDouble(String name, double value)
    {
        super(name);
        this.value = new Double(value);
    }

    @Override
    protected byte[] write() throws IOException
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buffer);
        writeVarInt(dos, 0x03);
        writeString(dos, this.name, StandardCharsets.UTF_8);
        byte[] vl = toByteArray(getValue());
        writeVarInt(dos, vl.length);
        dos.write(vl);
        return buffer.toByteArray();
    }
    
    private byte[] toByteArray(double d) {
        byte[] bytes = new byte[8];
        ByteBuffer.wrap(bytes).putDouble(d);
        return bytes;
    }
    
    private double toDouble(byte[] b) {
        return ByteBuffer.wrap(b).getDouble();
    }

    @Override
    protected void read(byte[] data) throws IOException
    {
        ByteArrayInputStream buffer = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(buffer);
        if(readVarInt(dis) != 0x03)
            throw new IllegalStateException("Data is not representing the type TagDouble");
        this.name = readString(dis);
        byte[] ba = readByteArray(dis);
        this.value = new Double(toDouble(ba));
    }
    
    public Double getValue() {
        return (Double) this.value;
    }
    
    public void setValue(double value) {
        this.value = new Double(value);
    }

}
