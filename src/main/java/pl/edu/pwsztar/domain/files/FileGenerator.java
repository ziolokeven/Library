package pl.edu.pwsztar.domain.files;

import org.springframework.core.io.InputStreamResource;
import pl.edu.pwsztar.domain.dto.FileDto;

import java.io.IOException;

public interface FileGenerator {
    InputStreamResource toTxt(FileDto fileDto) throws IOException;
}
