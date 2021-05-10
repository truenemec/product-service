package com.demo.productservice.payments.component.stream.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.BufferedInputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

@Getter
public class BufferedInputStreamReader implements Closeable {
    private static final int BYTE_BUFFER_SIZE = 4096;
    @Getter(AccessLevel.NONE)
    private final InputStream inputStream;

    private final ByteBuffer byteBuffer = ByteBuffer.allocate(BYTE_BUFFER_SIZE);
    private int offset;
    private int lineNumber = 0;

    public BufferedInputStreamReader(InputStream inputStream){
        this.inputStream = new BufferedInputStream(inputStream);
    }
    public long skip(long n) throws IOException {
        offset += n;
        return inputStream.skip(n);
    }
    public String readLine() throws IOException {
        int val;
        byteBuffer.clear();
        while ((val = inputStream.read()) != -1){
            offset++;
            if(val == '\r'){
                continue;
            }
            if(val == '\n'){
                lineNumber++;
                break;
            }
            byteBuffer.put((byte)val);
        }
        if(val == -1){
            if(byteBuffer.position() == 0){
                return null;
            } else{
                lineNumber++;
            }

        }
        byteBuffer.flip();
        return StandardCharsets.UTF_8.decode(byteBuffer).toString();
    }

    @Override
    public void close() throws IOException {
        inputStream.close();
    }
}
