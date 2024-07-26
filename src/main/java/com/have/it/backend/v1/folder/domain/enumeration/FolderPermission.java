package com.have.it.backend.v1.folder.domain.enumeration;

import java.util.stream.IntStream;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FolderPermission {
    READ(1),
    WRITE(2),
    MOVE(4);

    private final Integer status;

    public static FolderPermission valueOf(int status) {
        return switch (status) {
            case 1 -> FolderPermission.READ;
            case 2 -> FolderPermission.WRITE;
            case 4 -> FolderPermission.MOVE;
            default -> throw new AssertionError();
        };
    }

    public static int getMaxPermission() {
        return IntStream.of(
                        FolderPermission.READ.status,
                        FolderPermission.WRITE.status,
                        FolderPermission.MOVE.status)
                .sum();
    }
}
