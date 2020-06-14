package pl.edu.pwsztar.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.edu.pwsztar.domain.dto.BookDto;
import pl.edu.pwsztar.domain.dto.FileDto;
import pl.edu.pwsztar.domain.files.FileGenerator;
import pl.edu.pwsztar.domain.mapper.FileMapper;
import pl.edu.pwsztar.service.LibraryService;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value="/api")
public class FileApiController {

    private static final Logger LOGGER = LoggerFactory.getLogger(FileApiController.class);

    private final LibraryService libraryService;
    private final FileMapper fileMapper;
    private final FileGenerator fileGenerator;

    @Autowired
    public FileApiController(LibraryService libraryService, FileMapper fileMapper, FileGenerator fileGenerator) {
        this.libraryService = libraryService;
        this.fileMapper = fileMapper;
        this.fileGenerator = fileGenerator;
    }

    @CrossOrigin
    @GetMapping(value = "/download-txt")
    public ResponseEntity<Resource> downloadTxt() throws IOException {
        LOGGER.info("--- download txt file ---");

        List<BookDto> books = libraryService.findAllByYearDesc();
        FileDto fileDto = fileMapper.convert(books);
        InputStreamResource inputStreamResource = fileGenerator.toTxt(fileDto);

        final MediaType mediaType = MediaType.parseMediaType("application/octet-stream");
        final String headerAttachmentParam = "attachment;filename=" + fileDto.getFullName();

        return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_DISPOSITION, headerAttachmentParam)
            .contentType(mediaType)
            .body(inputStreamResource);
    }
}
