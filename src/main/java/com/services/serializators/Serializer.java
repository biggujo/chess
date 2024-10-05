package com.services.serializators;

import java.io.IOException;
import java.io.Serializable;

public interface Serializer {
    void save(Serializable object) throws IOException;
}
