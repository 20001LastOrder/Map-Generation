// A file system object in the file system
abstract sig FSObject { }

// File system objects must be either directories or files.
sig File, Dir extends FSObject { }

// A File System
sig FileSystem {
  root: Dir,
  live: set FSObject,
  contents: Dir lone-> FSObject,
  parent: FSObject ->lone Dir
}{
  // root has no parent
  no root.parent
  // live objects are those reachable from the root
  live = root.*contents
  // contents only defined on live objects
  contents in live->live
  // parent is the inverse of contents
  parent = ~contents
}

run xrx1s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run xrx2s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run xrx3s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rxx4s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rxx5s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx6s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx7s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx8s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx9s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx10s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r11s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx12s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx13s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx14s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx15s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx16s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx17s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx18s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx19s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx20s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx21s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx22s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx23s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx24s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx25s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx26s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx27s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx28s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx29s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run rx30s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r1s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r2s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r3s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r4s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r5s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r6s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r7s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r8s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r9s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r10s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r11s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r12s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r13s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r14s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r15s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r16s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r17s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r18s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r19s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r20s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r21s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r22s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r23s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r24s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r25s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r26s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r27s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r28s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r29s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r30s5 {} for exactly 1 FileSystem, exactly 4 FSObject
run r1s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r2s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r3s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r4s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r5s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r6s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r7s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r8s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r9s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r10s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r11s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r12s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r13s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r14s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r15s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r16s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r17s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r18s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r19s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r20s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r21s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r22s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r23s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r24s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r25s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r26s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r27s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r28s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r29s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r30s10 {} for exactly 1 FileSystem, exactly 9 FSObject
run r1s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r2s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r3s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r4s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r5s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r6s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r7s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r8s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r9s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r10s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r11s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r12s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r13s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r14s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r15s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r16s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r17s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r18s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r19s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r20s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r21s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r22s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r23s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r24s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r25s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r26s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r27s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r28s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r29s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r30s15 {} for exactly 1 FileSystem, exactly 14 FSObject
run r1s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r2s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r3s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r4s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r5s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r6s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r7s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r8s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r9s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r10s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r11s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r12s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r13s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r14s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r15s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r16s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r17s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r18s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r19s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r20s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r21s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r22s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r23s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r24s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r25s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r26s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r27s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r28s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r29s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r30s20 {} for exactly 1 FileSystem, exactly 19 FSObject
run r1s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r2s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r3s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r4s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r5s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r6s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r7s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r8s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r9s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r10s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r11s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r12s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r13s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r14s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r15s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r16s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r17s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r18s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r19s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r20s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r21s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r22s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r23s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r24s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r25s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r26s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r27s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r28s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r29s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r30s25 {} for exactly 1 FileSystem, exactly 24 FSObject
run r1s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r2s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r3s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r4s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r5s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r6s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r7s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r8s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r9s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r10s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r11s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r12s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r13s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r14s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r15s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r16s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r17s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r18s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r19s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r20s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r21s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r22s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r23s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r24s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r25s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r26s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r27s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r28s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r29s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r30s30 {} for exactly 1 FileSystem, exactly 29 FSObject
run r1s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r2s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r3s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r4s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r5s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r6s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r7s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r8s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r9s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r10s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r11s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r12s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r13s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r14s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r15s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r16s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r17s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r18s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r19s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r20s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r21s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r22s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r23s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r24s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r25s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r26s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r27s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r28s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r29s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r30s35 {} for exactly 1 FileSystem, exactly 34 FSObject
run r1s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r2s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r3s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r4s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r5s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r6s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r7s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r8s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r9s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r10s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r11s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r12s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r13s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r14s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r15s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r16s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r17s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r18s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r19s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r20s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r21s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r22s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r23s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r24s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r25s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r26s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r27s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r28s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r29s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r30s40 {} for exactly 1 FileSystem, exactly 39 FSObject
run r1s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r2s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r3s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r4s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r5s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r6s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r7s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r8s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r9s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r10s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r11s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r12s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r13s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r14s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r15s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r16s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r17s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r18s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r19s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r20s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r21s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r22s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r23s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r24s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r25s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r26s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r27s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r28s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r29s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r30s45 {} for exactly 1 FileSystem, exactly 44 FSObject
run r1s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r2s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r3s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r4s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r5s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r6s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r7s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r8s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r9s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r10s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r11s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r12s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r13s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r14s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r15s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r16s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r17s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r18s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r19s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r20s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r21s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r22s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r23s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r24s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r25s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r26s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r27s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r28s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r29s50 {} for exactly 1 FileSystem, exactly 49 FSObject
run r30s50 {} for exactly 1 FileSystem, exactly 49 FSObject
