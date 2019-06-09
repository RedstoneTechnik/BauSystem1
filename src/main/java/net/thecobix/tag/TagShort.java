package net.thecobix.tag;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TagShort extends Tag {
    
    public static final TagShort BASE_OBJECT = new TagShort("base", (short) 0);

    public TagShort(String name, short value)
    {
        super(name);
        this.value = new Short(value);
    }

    @Override
    protected byte[] write() throws IOException
    {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(buffer);
        writeVarInt(dos, 0x05);
        writeString(dos, this.name, StandardCharsets.UTF_8);
        dos.writeShort((short) this.value);
        return buffer.toByteArray();
    }

    @Override
    protected void read(byte[] data) throws IOException
    {
        ByteArrayInputStream buffer = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(buffer);
        if(readVarInt(dis) != 0x05)
            throw new IllegalStateException("Data is not representing the type TagShort");
        this.name = readString(dis);
        this.value = new Short(dis.readShort());
    }
    
    public Short getValue() {
        return (Short) this.value;
    }
    
    public void setValue(short value) {
        this.value = new Short(value);
    }

}
