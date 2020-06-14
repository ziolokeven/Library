package pl.edu.pwsztar.domain.files;

import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;
import pl.edu.pwsztar.domain.dto.FileDto;

import java.io.*;

@Component
public class FileTxtGeneratorImpl implements FileGenerator {

    @Override
    public InputStreamResource toTxt(FileDto fileDto) throws IOException {
        File file = File.createTempFile(fileDto.getName(), "." + fileDto.getExtension());
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
        bufferedWriter.write(fileDto.getContent());

        bufferedWriter.close();
        fileOutputStream.flush();
        fileOutputStream.close();

        return new InputStreamResource(new FileInputStream(file));
    }
}
