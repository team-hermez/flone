package org.hermez.image.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Image {
    private int entityId;
    private String entityType;
    private String originalName;
    private String saveName;
    private String filePath;

    public Image(int entityId, String entityType) {
        this.entityId = entityId;
        this.entityType = entityType;
    }

    public Image(int entityId, String entityType, String originalName, String saveName, String filePath) {
        this.entityId = entityId;
        this.entityType = entityType;
        this.originalName = originalName;
        this.saveName = saveName;
        this.filePath = filePath;
    }
}
