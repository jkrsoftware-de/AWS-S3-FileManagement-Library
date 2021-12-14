package one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.download.files;

import lombok.NonNull;

import java.io.File;
import java.net.URL;
import java.util.Optional;

public interface AwsS3DownloadUseCase {

    Optional<File> downloadFile(@NonNull DownloadFileCommand command);

    URL createPresignedDownloadUrl(@NonNull CreatePresignedDownloadUrlCommand command);

}
