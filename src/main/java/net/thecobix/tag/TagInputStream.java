package net.thecobix.tag;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.MappedByteBuffer;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.zip.GZIPInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

public class TagInputStream extends InputStream {

    private InputStream wrapped;
    
    public static final int NONE = 0;
    public static final int GZIP = 1;
    @Deprecated
    public static final int ZIP = 2;
    public static final int BASE64 = 3;

    private boolean base = false;
    private boolean zip = false;
    private boolean entry = false;
    
    public TagInputStream(InputStream in, int compression)
    {
        if(compression == GZIP) {
            try
            {
                this.wrapped = new GZIPInputStream(in);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return;
        } else if(compression == ZIP) {
            throw new RuntimeException("ZIP reading not supported yet.");
        } else if(compression == BASE64) {
            this.base = true;
        }
        this.wrapped = in;
    }
    
    @Override
    public int read() throws IOException
    {
        return this.wrapped.read();
    }
    
    @Override
    public int read(byte[] b, int off, int len) throws IOException
    {
        return this.wrapped.read(b, off, len);
    }

    @Override
    public void close() throws IOException
    {
        this.wrapped.close();
    }
    
    @Override
    public int available() throws IOException
    {
        return this.wrapped.available();
    }
    
    @Override
    public long skip(long n) throws IOException
    {
        return this.wrapped.skip(n);
    }
    
    @Override
    public boolean markSupported()
    {
        return this.wrapped.markSupported();
    }
    
    @Override
    public synchronized void mark(int readlimit)
    {
        this.wrapped.mark(readlimit);
    }
    
    @Override
    public synchronized void reset() throws IOException
    {
        this.wrapped.reset();
    }
    
    @Override
    public int read(byte[] b) throws IOException
    {
        return this.wrapped.read(b);
    }
    
    public List<Tag> readTags() throws IOException {
        ArrayList<Tag> out = new ArrayList<>();
        byte[] data = readFully(this.wrapped, -1, true);
        if(this.base) {
            data = Base64.getDecoder().decode(data);
        }
        ArrayList<Entry<Class<? extends Tag>, byte[]>> mapped = splitData(data);
        for(Entry<Class<? extends Tag>, byte[]> e : mapped) {
            Class clazz = e.getKey();
            if(clazz.getSimpleName().equals("TagString")) {
                TagString ts = new TagString("", "");
                ts.read(e.getValue());
                out.add(ts);
            } else if(clazz.getSimpleName().equals("TagInt")) {
                TagInt ti = new TagInt("", 0);
                ti.read(e.getValue());
                out.add(ti);
            } else if(clazz.getSimpleName().equals("TagDouble")) {
                TagDouble ti = new TagDouble("", 0);
                ti.read(e.getValue());
                out.add(ti);
            } else if(clazz.getSimpleName().equals("TagLong")) {
                TagLong ti = new TagLong("", 0);
                ti.read(e.getValue());
                out.add(ti);
            } else if(clazz.getSimpleName().equals("TagShort")) {
                TagShort ti = new TagShort("", (short) 0);
                ti.read(e.getValue());
                out.add(ti);
            } else if(clazz.getSimpleName().equals("TagFloat")) {
                TagFloat ti = new TagFloat("", 0);
                ti.read(e.getValue());
                out.add(ti);
            } else if(clazz.getSimpleName().equals("TagCompound")) {
                TagCompound ti = new TagCompound("");
                ti.read(e.getValue());
                out.add(ti);
            } else if(clazz.getSimpleName().equals("TagSerializeableObject")) {
                TagSerializeableObject ti = new TagSerializeableObject("", 0);
                ti.read(e.getValue());
                out.add(ti);
            } else if(clazz.getSimpleName().equals("TagByteArray")) {
                TagByteArray ti = new TagByteArray("", new byte[] {});
                ti.read(e.getValue());
                out.add(ti);
            } else {
                if(Extension.extensions.size() != 0) {
                    Tag shoutie = null;
                    for(Extension ex : Extension.extensions) {
                        Tag honey = ex.readTag(clazz, e.getValue());
                        if(honey != null) {
                            shoutie = honey;
                        }
                    }
                    out.add(shoutie);
                } else {
                    System.out.println("[ERROR] Unknown tag with classname "+clazz.getSimpleName());
                }
            }
        }
        return out;
    }
    
    public static List<Tag> readTags(byte[] data) throws IOException {
        ArrayList<Tag> out = new ArrayList<>();
        ArrayList<Entry<Class<? extends Tag>, byte[]>> mapped = splitData(data);
        for(Entry<Class<? extends Tag>, byte[]> e : mapped) {
            Class clazz = e.getKey();
            if(clazz.getSimpleName().equals("TagString")) {
                TagString ts = new TagString("", "");
                ts.read(e.getValue());
                out.add(ts);
            } else if(clazz.getSimpleName().equals("TagInt")) {
                TagInt ti = new TagInt("", 0);
                ti.read(e.getValue());
                out.add(ti);
            } else if(clazz.getSimpleName().equals("TagDouble")) {
                TagDouble ti = new TagDouble("", 0);
                ti.read(e.getValue());
                out.add(ti);
            } else if(clazz.getSimpleName().equals("TagLong")) {
                TagLong ti = new TagLong("", 0);
                ti.read(e.getValue());
                out.add(ti);
            } else if(clazz.getSimpleName().equals("TagShort")) {
                TagShort ti = new TagShort("", (short) 0);
                ti.read(e.getValue());
                out.add(ti);
            } else if(clazz.getSimpleName().equals("TagFloat")) {
                TagFloat ti = new TagFloat("", 0);
                ti.read(e.getValue());
                out.add(ti);
            } else if(clazz.getSimpleName().equals("TagCompound")) {
                TagCompound ti = new TagCompound("");
                ti.read(e.getValue());
                out.add(ti);
            } else if(clazz.getSimpleName().equals("TagSerializeableObject")) {
                TagSerializeableObject ti = new TagSerializeableObject("", 0);
                ti.read(e.getValue());
                out.add(ti);
            } else if(clazz.getSimpleName().equals("TagByteArray")) {
                TagByteArray ti = new TagByteArray("", new byte[] {});
                ti.read(e.getValue());
                out.add(ti);
            } else {
                if(Extension.extensions.size() != 0) {
                    Tag shoutie = null;
                    for(Extension ex : Extension.extensions) {
                        Tag honey = ex.readTag(clazz, e.getValue());
                        if(honey != null) {
                            shoutie = honey;
                        }
                    }
                    out.add(shoutie);
                } else {
                    System.out.println("[ERROR] Unknown tag with classname "+clazz.getSimpleName());
                }
            }
        }
        return out;
    }
    
    private static byte[] readFully(InputStream is, int length, boolean readAll)
            throws IOException {
        byte[] output = {};
        if (length == -1) length = Integer.MAX_VALUE;
        int pos = 0;
        while (pos < length) {
            int bytesToRead;
            if (pos >= output.length) { // Only expand when there's no room
                bytesToRead = Math.min(length - pos, output.length + 1024);
                if (output.length < pos + bytesToRead) {
                    output = Arrays.copyOf(output, pos + bytesToRead);
                }
            } else {
                bytesToRead = output.length - pos;
            }
            int cc = is.read(output, pos, bytesToRead);
            if (cc < 0) {
                if (readAll && length != Integer.MAX_VALUE) {
                    throw new EOFException("Detect premature EOF");
                } else {
                    if (output.length != pos) {
                        output = Arrays.copyOf(output, pos);
                    }
                    break;
                }
            }
            pos += cc;
        }
        return output;
    }
    
    static ArrayList<Entry<Class<? extends Tag>, byte[]>> splitData(byte[] data) throws IOException  {
        TagCompound tc = new TagCompound("dummy");
        ByteArrayInputStream bis = new ByteArrayInputStream(data);
        DataInputStream dis = new DataInputStream(bis);
        ArrayList<Entry<Class<? extends Tag>, byte[]>> out = new ArrayList<>();
        while(bis.available() != 0) {
            try {
                int start = data.length - bis.available();
                int id = tc.readVarInt(dis);
                tc.readString(dis);
                if(id == 0x01) {
                    tc.readString(dis);
                    int end = data.length - bis.available();
                    out.add(new AbstractMap.SimpleEntry(TagString.class, subSequence(data, start, end)));
                } else if(id == 0x02) {
                    tc.readVarInt(dis);
                    int end = data.length - bis.available();
                    out.add(new AbstractMap.SimpleEntry(TagInt.class, subSequence(data, start, end)));
                } else if(id == 0x03) {
                    tc.readByteArray(dis);
                    int end = data.length - bis.available();
                    out.add(new AbstractMap.SimpleEntry(TagDouble.class, subSequence(data, start, end)));
                } else if(id == 0x04) {
                    tc.readByteArray(dis);
                    int end = data.length - bis.available();
                    out.add(new AbstractMap.SimpleEntry(TagLong.class, subSequence(data, start, end)));
                } else if(id == 0x05) {
                    dis.readShort();
                    int end = data.length - bis.available();
                    out.add(new AbstractMap.SimpleEntry(TagShort.class, subSequence(data, start, end)));
                } else if(id == 0x06) {
                    dis.readFloat();
                    int end = data.length - bis.available();
                    out.add(new AbstractMap.SimpleEntry(TagFloat.class, subSequence(data, start, end)));
                } else if(id == 0x07) {
                    tc.readByteArray(dis);
                    int end = data.length - bis.available();
                    out.add(new AbstractMap.SimpleEntry(TagCompound.class, subSequence(data, start, end)));
                } else if(id == 0x08) {
                    tc.readByteArray(dis);
                    int end = data.length - bis.available();
                    out.add(new AbstractMap.SimpleEntry(TagSerializeableObject.class, subSequence(data, start, end)));
                } else if(id == 0x09) {
                    tc.readByteArray(dis);
                    int end = data.length - bis.available();
                    out.add(new AbstractMap.SimpleEntry(TagByteArray.class, subSequence(data, start, end)));
                } else {
                    if(Extension.extensions.size() != 0) {
                        Entry<Class<? extends Tag>, byte[]> et = null;
                        for(Extension e : Extension.extensions) {
                            Entry<Class<? extends Tag>, byte[]> tet = e.splitData(dis, id, data, start);
                            if(tet != null) {
                                et = tet;
                            }
                        }
                        if(et != null) {
                            out.add(et);
                        } else {
                            System.out.println("[ERROR] Unknown tag with id "+id);
                        }
                    } else {
                        System.out.println("[ERROR] Unknown tag with id "+id);
                    }
                }
            } catch(Exception e) {
                e.printStackTrace();
                break;
            }
        }
        return out;
    }
    
    private static byte[] subSequence(byte[] fully, int start, int end) {
        byte[] out = new byte[(end - start)+2];
        int ind = -1;
        for(int i = 0; i < fully.length; i++)
        {
            if(i >= start && i <= end) {
                ind ++;
                out[ind] = fully[i];
            }
        }
        return out;
    }
    
}
