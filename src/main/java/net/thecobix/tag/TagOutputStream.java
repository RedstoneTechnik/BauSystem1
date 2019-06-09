package net.thecobix.tag;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.List;
import java.util.zip.GZIPOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.print.attribute.standard.Compression;

public class TagOutputStream extends OutputStream {

    private OutputStream wrapped;
    
    public static final int NONE = 0;
    public static final int GZIP = 1;
    public static final int ZIP = 2;
    public static final int BASE64 = 3;
    
    private boolean base = false;
    private boolean zip = false;
    private boolean entry = false;
    
    public TagOutputStream(OutputStream out, int compression)
    {
        if(compression == GZIP) {
            try
            {
                this.wrapped = new GZIPOutputStream(out);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return;
        } else if(compression == ZIP) {
            this.wrapped = new ZipOutputStream(out);
            this.zip = true;
            return;
        } else if(compression == BASE64) {
            this.base = true;
        }
        this.wrapped = out;
    }

    @Override
    public void write(int b) throws IOException
    {
        this.wrapped.write(b);
    }
    
    public void write(Tag tag) throws IOException {
        byte[] ba;
        if(this.base) {
            ba = Base64.getEncoder().encode(tag.write());
        } else {
            ba = tag.write();
        }
        if(this.zip) {
            ZipOutputStream zout = (ZipOutputStream) this.wrapped;
            if(entry == false) {
                ZipEntry ze = new ZipEntry("content");
                zout.putNextEntry(ze);
            }
        }
        this.wrapped.write(ba);
    }
    
    public void write(List<Tag> tags) throws IOException {
        for(Tag t : tags) {
            write(t);
        }
    }
    
    public void write(List<Tag> tags, boolean organizeInCompound) throws IOException {
        if(organizeInCompound) {
            TagCompound tc = new TagCompound("root");
            for(Tag t : tags) {
                tc.put(t);
            }
            write(tc);
            return;
        }
        for(Tag t : tags) {
            write(t);
        }
    }
    
    @Override
    public void write(byte[] b) throws IOException
    {
        this.wrapped.write(b);
    }
    
    @Override
    public void write(byte[] b, int off, int len) throws IOException
    {
        this.wrapped.write(b, off, len);
    }
    
    @Override
    public void flush() throws IOException
    {
        this.wrapped.flush();;
    }
    
    @Override
    public void close() throws IOException
    {
        if(this.zip) {
            ZipOutputStream zout = (ZipOutputStream) this.wrapped;
            zout.closeEntry();
        }
        this.wrapped.close();
    }
    
}
