package com.services.serializators;

import java.io.IOException;

public interface Deserializer {
    Object load() throws IOException, ClassNotFoundException;
}
