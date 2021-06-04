package com.guzx.section4;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @author Guzx
 * @version 1.0
 * @date 2021/6/4 9:58
 * @describe
 */
public class Thread_LockFreeVector<E> {
    private static final boolean debug = false;
    private static final int N_BUCKET = 30;
    private static final int FIRST_BUCKET_SIZE = 8;
    private final AtomicReferenceArray<AtomicReferenceArray<E>> buckets;
    private AtomicReference<Thread_LockFreeVector.Descriptor<E>> descriptor;
    private static final int zeroNumFirst = Integer.numberOfLeadingZeros(8);

    public Thread_LockFreeVector() {
        this.buckets = new AtomicReferenceArray<AtomicReferenceArray<E>>(N_BUCKET);
        buckets.set(0, new AtomicReferenceArray<E>(FIRST_BUCKET_SIZE));
        descriptor = new AtomicReference<Descriptor<E>>(new Descriptor<E>(0, null));
    }

    public Thread_LockFreeVector(AtomicReferenceArray<AtomicReferenceArray<E>> buckets) {
        this.buckets = buckets;
    }

    public void push_back(E e) {
        Descriptor<E> desc;
        Descriptor<E> newd;

        do {
            desc = descriptor.get();
            desc.completeWrite();
            int pos = desc.size + FIRST_BUCKET_SIZE;
            int zeroNumPos = Integer.numberOfLeadingZeros(pos);
            int bucketInd = zeroNumFirst - zeroNumPos;
            if (buckets.get(bucketInd) == null) {
                int newLen = 2 * buckets.get(bucketInd - 1).length();
                if (debug) {
                    System.out.println("new Length is:" + newLen);
                }
                buckets.compareAndSet(bucketInd, null, new AtomicReferenceArray<E>(newLen));
            }
            int idx = (0x80000000 >>> zeroNumPos) ^ pos;
            newd = new Descriptor<E>(desc.size, new WriteDescriptor<E>(buckets.get(bucketInd), idx, null, e));
        } while (!descriptor.compareAndSet(desc, newd));

        descriptor.get().completeWrite();
    }

    public E get(int index) {
        int pos = index + FIRST_BUCKET_SIZE;
        int zeroNumPos = Integer.numberOfLeadingZeros(pos);
        int bucketId = zeroNumFirst - zeroNumPos;
        int idx = (0x80000000 >>> zeroNumPos) ^ pos;
        return buckets.get(bucketId).get(idx);
    }

    static class Descriptor<E> {
        public int size;
        volatile WriteDescriptor<E> writeDescriptor;

        public Descriptor(int size, WriteDescriptor<E> writeDescriptor) {
            this.size = size;
            this.writeDescriptor = writeDescriptor;
        }

        public void completeWrite() {
            WriteDescriptor<E> tempDescriptor = writeDescriptor;
            if (tempDescriptor != null) {
                tempDescriptor.doIt();
                writeDescriptor = null;
            }
        }
    }

    static class WriteDescriptor<E> {
        public E oldV;
        public E newV;
        public AtomicReferenceArray<E> addr;
        public int addr_ind;

        public WriteDescriptor(AtomicReferenceArray<E> addr, int addr_ind, E oldV, E newV) {
            this.addr = addr;
            this.addr_ind = addr_ind;
            this.oldV = oldV;
            this.newV = newV;
        }

        public void doIt() {
            addr.compareAndSet(addr_ind, oldV, newV);
        }
    }
}
