package pl.edu.pwsztar.domain.dto;

import java.io.Serializable;

public class FileDto implements Serializable {
    private String name;
    private String extension;
    private String content;

    public FileDto() {
    }

    private FileDto(Builder builder) {
        name = builder.name;
        extension = builder.extension;
        content = builder.content;
    }

    public String getName() {
        return name;
    }

    public String getExtension() {
        return extension;
    }

    public String getContent() {
        return content;
    }

    public String getFullName() {
        return this.name + "." + this.getExtension();
    }

    public static final class Builder {
        private String name;
        private String extension;
        private String content;

        public Builder() {
        }

        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder extension(String extension) {
            this.extension = extension;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public FileDto build() {
            return new FileDto(this);
        }
    }
}
