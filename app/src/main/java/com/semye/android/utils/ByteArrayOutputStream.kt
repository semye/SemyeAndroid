package com.semye.android.utils

import java.io.IOException
import java.io.OutputStream
import java.io.UnsupportedEncodingException
import java.util.*

/**
 * This class implements an output stream in which the data is
 * written into a byte array. The buffer automatically grows as data
 * is written to it.
 *
 *
 * The data can be retrieved using `toByteArray()` and
 * `toString()`.
 *
 *
 * Closing a <tt>ByteArrayOutputStream</tt> has no effect. The methods in
 * this class can be called after the stream has been closed without
 * generating an <tt>IOException</tt>.
 *
 *
 * This is an alternative implementation of the java.io.ByteArrayOutputStream
 * class. The original implementation only allocates 32 bytes at the beginning.
 * As this class is designed for heavy duty it starts at 1024 bytes. In contrast
 * to the original it doesn't reallocate the whole memory block but allocates
 * additional buffers. This way no buffers need to be garbage collected and
 * the contents don't have to be copied to the new buffer. This class is
 * designed to behave exactly like the original. The only exception is the
 * deprecated toString(int) method that has been ignored.
 *
 * @author [Jeremias Maerki](mailto:jeremias@apache.org)
 * @author Holger Hoffstatte
 * @version $Id: ByteArrayOutputStream.java 491007 2006-12-29 13:50:34Z scolebourne $
 */
//与java自带的ByteArrayOutputStream的区别是开始允许分配的最大字节是1024,java自带的是32
class ByteArrayOutputStream @JvmOverloads constructor(size: Int = 1024) : OutputStream() {
    /**
     * The list of buffers, which grows and never reduces.
     */
    private val buffers: MutableList<*> = ArrayList<Any?>()

    /**
     * The index of the current buffer.
     */
    private var currentBufferIndex = 0

    /**
     * The total count of bytes in all the filled buffers.
     */
    private var filledBufferSum = 0

    /**
     * The current buffer.
     */
    private var currentBuffer: ByteArray?

    /**
     * The total count of bytes written.
     */
    private var count = 0

    /**
     * Return the appropriate `byte[]` buffer
     * specified by index.
     *
     * @param index the index of the buffer required
     * @return the buffer
     */
    private fun getBuffer(index: Int): ByteArray {
        return buffers[index] as ByteArray
    }

    /**
     * Makes a new buffer available either by allocating
     * a new one or re-cycling an existing one.
     *
     * @param newcount the size of the buffer if one is created
     */
    private fun needNewBuffer(newcount: Int) {
        if (currentBufferIndex < buffers.size - 1) {
            //Recycling old buffer
            filledBufferSum += currentBuffer!!.size
            currentBufferIndex++
            currentBuffer = getBuffer(currentBufferIndex)
        } else {
            //Creating new buffer
            val newBufferSize: Int
            if (currentBuffer == null) {
                newBufferSize = newcount
                filledBufferSum = 0
            } else {
                newBufferSize = Math.max(
                        currentBuffer!!.size shl 1,
                        newcount - filledBufferSum)
                filledBufferSum += currentBuffer!!.size
            }
            currentBufferIndex++
            currentBuffer = ByteArray(newBufferSize)
            buffers.add(currentBuffer)
        }
    }

    /**
     * @see OutputStream.write
     */
    override fun write(b: ByteArray, off: Int, len: Int) {
        if (off < 0
                || off > b.size
                || len < 0
                || off + len > b.size
                || off + len < 0) {
            throw IndexOutOfBoundsException()
        } else if (len == 0) {
            return
        }
        synchronized(this) {
            val newcount = count + len
            var remaining = len
            var inBufferPos = count - filledBufferSum
            while (remaining > 0) {
                val part = Math.min(remaining, currentBuffer!!.size - inBufferPos)
                System.arraycopy(b, off + len - remaining, currentBuffer, inBufferPos, part)
                remaining -= part
                if (remaining > 0) {
                    needNewBuffer(newcount)
                    inBufferPos = 0
                }
            }
            count = newcount
        }
    }

    /**
     * @see OutputStream.write
     */
    @Synchronized
    override fun write(b: Int) {
        var inBufferPos = count - filledBufferSum
        if (inBufferPos == currentBuffer!!.size) {
            needNewBuffer(count + 1)
            inBufferPos = 0
        }
        currentBuffer!![inBufferPos] = b.toByte()
        count++
    }

    /**
     * @see java.io.ByteArrayOutputStream.size
     */
    @Synchronized
    fun size(): Int {
        return count
    }

    /**
     * Closing a <tt>ByteArrayOutputStream</tt> has no effect. The methods in
     * this class can be called after the stream has been closed without
     * generating an <tt>IOException</tt>.
     *
     * @throws IOException never (this method should not declare this exception
     * but it has to now due to backwards compatability)
     */
    @Throws(IOException::class)
    override fun close() {
        //nop
    }

    /**
     * @see java.io.ByteArrayOutputStream.reset
     */
    @Synchronized
    fun reset() {
        count = 0
        filledBufferSum = 0
        currentBufferIndex = 0
        currentBuffer = getBuffer(currentBufferIndex)
    }

    /**
     * Writes the entire contents of this byte stream to the
     * specified output stream.
     *
     * @param out the output stream to write to
     * @throws IOException if an I/O error occurs, such as if the stream is closed
     * @see java.io.ByteArrayOutputStream.writeTo
     */
    @Synchronized
    @Throws(IOException::class)
    fun writeTo(out: OutputStream) {
        var remaining = count
        for (i in buffers.indices) {
            val buf = getBuffer(i)
            val c = Math.min(buf.size, remaining)
            out.write(buf, 0, c)
            remaining -= c
            if (remaining == 0) {
                break
            }
        }
    }

    /**
     * Gets the curent contents of this byte stream as a byte array.
     * The result is independent of this stream.
     *
     * @return the current contents of this output stream, as a byte array
     * @see java.io.ByteArrayOutputStream.toByteArray
     */
    @Synchronized
    fun toByteArray(): ByteArray {
        var remaining = count
        if (remaining == 0) {
            return EMPTY_BYTE_ARRAY
        }
        val newbuf = ByteArray(remaining)
        var pos = 0
        for (i in buffers.indices) {
            val buf = getBuffer(i)
            val c = Math.min(buf.size, remaining)
            System.arraycopy(buf, 0, newbuf, pos, c)
            pos += c
            remaining -= c
            if (remaining == 0) {
                break
            }
        }
        return newbuf
    }

    /**
     * Gets the curent contents of this byte stream as a string.
     *
     * @see java.io.ByteArrayOutputStream.toString
     */
    override fun toString(): String {
        return String(toByteArray())
    }

    /**
     * Gets the curent contents of this byte stream as a string
     * using the specified encoding.
     *
     * @param enc the name of the character encoding
     * @return the string converted from the byte array
     * @throws UnsupportedEncodingException if the encoding is not supported
     * @see java.io.ByteArrayOutputStream.toString
     */
    @Throws(UnsupportedEncodingException::class)
    fun toString(enc: String): String {
        return String(toByteArray(), enc)
    }

    companion object {
        /**
         * A singleton empty byte array.
         */
        private val EMPTY_BYTE_ARRAY = ByteArray(0)
    }
    /**
     * Creates a new byte array output stream, with a buffer capacity of
     * the specified size, in bytes.
     *
     * @param size the initial size
     * @throws IllegalArgumentException if size is negative
     */
    /**
     * Creates a new byte array output stream. The buffer capacity is
     * initially 1024 bytes, though its size increases if necessary.
     */
    init {
        require(size >= 0) { "Negative initial size: $size" }
        needNewBuffer(size)
    }
}