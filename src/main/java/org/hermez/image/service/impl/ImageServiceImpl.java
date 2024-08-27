package org.hermez.image.service.impl;

import org.hermez.image.dto.RegisterImageRequest;
import org.hermez.image.exception.ImageProcessingException;
import org.hermez.image.mapper.ImageMapper;
import org.hermez.image.model.Image;
import org.hermez.image.service.ImageService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * ImageService의 구현체로, 이미지를 저장하고 관리하는 역할을 합니다.
 *
 * 이 클래스는 이미지를 파일 시스템에 저장하고, 데이터베이스에 이미지 정보를 저장합니다.
 *
 * @author 김혁진
 */
@Service
public class ImageServiceImpl implements ImageService {

    private final ImageMapper imageMapper;
    private final ServletContext servletContext;

    /**
     * ImageServiceImpl의 생성자입니다.
     *
     * @param imageMapper 이미지 매퍼 객체
     * @param servletContext 서블릿 컨텍스트 객체
     */
    public ImageServiceImpl(ImageMapper imageMapper, ServletContext servletContext) {
        this.imageMapper = imageMapper;
        this.servletContext = servletContext;
    }

    /**
     * {@inheritDoc}
     *
     * 이미지를 저장소에 저장하고 관련 정보를 데이터베이스에 기록합니다.
     *
     * @param registerImageRequest 이미지 등록 요청 객체
     * @throws ImageProcessingException 이미지 저장 중 오류가 발생한 경우
     */
    @Override
    public void saveImage(RegisterImageRequest registerImageRequest) throws IOException {
        MultipartFile file = registerImageRequest.getMultipartFile();
        if (file.isEmpty()) {
            Image image = new Image(
                    registerImageRequest.getEntityId(),
                    registerImageRequest.getEntityType()
            );
            imageMapper.insertImage(image);
            return;
        }

        String originalName = file.getOriginalFilename();

        if (!isAllowedExtension(originalName)) {
            throw new ImageProcessingException("지원되지 않는 확장자입니다. jpg, png만 허용됩니다.");
        }

        try {
            String encodedOriginalName = URLEncoder.encode(originalName, StandardCharsets.UTF_8.toString());
            encodedOriginalName = encodedOriginalName.replaceAll("\\+", "%20");
            String saveName = System.currentTimeMillis() + "_" + encodedOriginalName;

            String targetDir = servletContext.getRealPath("/")
                    + "resources" + File.separator + "images";
            File directory = new File(targetDir);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            String filePath = targetDir + File.separator + saveName;
            File destinationFile = new File(filePath);

            file.transferTo(destinationFile);

            Image image = new Image(
                    registerImageRequest.getEntityId(),
                    registerImageRequest.getEntityType(),
                    originalName,
                    saveName,
                    filePath
            );
            imageMapper.insertImage(image);
        } catch (IOException e) {
            throw new ImageProcessingException("이미지 저장 중 오류가 발생했습니다.", e);
        }
    }


    /**
     * 주어진 파일 이름의 확장자가 허용된 형식인지를 확인합니다.
     *
     * @param fileName 확인할 파일 이름
     * @return 허용된 확장자인 경우 true, 그렇지 않은 경우 false
     */
    private boolean isAllowedExtension(String fileName) {
        String extension = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
        return extension.equals("jpg") || extension.equals("jpeg") || extension.equals("png");
    }

    public String getSaveImage(int entityId, String entityType) {
        return imageMapper.selectImageById(entityId,entityType);
    }
}
