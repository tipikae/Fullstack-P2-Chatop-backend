package com.tipikae.chatop.services.storage;

import com.tipikae.chatop.exceptions.storage.FileNotFoundException;
import com.tipikae.chatop.exceptions.storage.StorageException;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * Storage service interface.
 */
public interface IStorageService {

    /**
     * Store a file.
     * @param file MultipartFile object.
     * @return Stored file path String.
     * @throws StorageException thrown when a storage exception occurred.
     */
    String store(MultipartFile file) throws StorageException;

    /**
     * Load a file.
     * @param filename File name.
     * @return File as Resource
     * @throws FileNotFoundException thrown when a file is not found.
     * @throws StorageException thrown when a resource error occurred.
     */
    Resource load(String filename) throws FileNotFoundException, StorageException;
}
