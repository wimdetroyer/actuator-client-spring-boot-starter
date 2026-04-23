package com.wimdetroyer.actuator.endpoints.threaddump;

import java.util.List;

/**
 * Response from the threaddump endpoint (JSON format).
 */
public record ThreadDumpResponse(
        List<ThreadInfo> threads
) {
    public record ThreadInfo(
            String threadName,
            long threadId,
            long blockedTime,
            long blockedCount,
            long waitedTime,
            long waitedCount,
            String lockName,
            long lockOwnerId,
            String lockOwnerName,
            boolean daemon,
            boolean inNative,
            boolean suspended,
            ThreadState threadState,
            int priority,
            List<StackFrame> stackTrace,
            List<LockedMonitor> lockedMonitors,
            List<LockInfo> lockedSynchronizers,
            LockInfo lockInfo
    ) {}

    public record StackFrame(
            String classLoaderName,
            String moduleName,
            String moduleVersion,
            String methodName,
            String fileName,
            int lineNumber,
            String className,
            boolean nativeMethod
    ) {}

    public record LockedMonitor(
            String className,
            int identityHashCode,
            int lockedStackDepth,
            StackFrame lockedStackFrame
    ) {}

    public record LockInfo(
            String className,
            int identityHashCode
    ) {}
}
