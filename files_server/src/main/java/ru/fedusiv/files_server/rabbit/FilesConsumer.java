package ru.fedusiv.files_server.rabbit;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface FilesConsumer {

    void consume() throws IOException, TimeoutException;

}
