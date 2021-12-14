package one.jkr.de.jkrsoftware.aws.s3.filemanagement.library.aws.s3.file.management.system.application.ports.in.delete.files;

import lombok.NonNull;

public interface AwsS3DeleteUseCase {

    // ToDo: verify File-Deletion and return an Success, if successfully deleted.
    void deleteFile(@NonNull DeleteFileCommand command);

}
