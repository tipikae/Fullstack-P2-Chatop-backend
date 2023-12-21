package com.tipikae.chatop.services.storage;

import com.tipikae.chatop.exceptions.storage.FileNotFoundException;
import com.tipikae.chatop.exceptions.storage.StorageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Storage service.
 */
@Service
public class StorageService implements IStorageService {

    @Value("${spring.mvc.servlet.path}")
    private String prefix;

    private final String endpoint = "/rentals";

    private final Path rootLocation;

    /**
     * Constructor.
     * @param location Storage location.
     * @throws StorageException thrown when the storage location name is empty.
     */
    public StorageService(@Value("${spring.servlet.multipart.location}") String location) throws StorageException {
        if (location.trim().isEmpty()) {
            throw new StorageException("File upload location can not be empty.");
        }
        this.rootLocation = Paths.get(location);
    }

    /**
     * Store a file.
     *
     * @param file MultipartFile object.
     * @return Stored file path String.
     * @throws StorageException thrown when an error occurred during storage.
     */
    @Override
    public String store(MultipartFile file) throws StorageException {
        try (InputStream is = file.getInputStream()) {
            if (file.isEmpty()) {
                throw new StorageException("Failed to store empty file.");
            }

            Path destinationFile =
                    rootLocation.resolve(Paths.get(file.getOriginalFilename()));

            Files.copy(is, destinationFile, StandardCopyOption.REPLACE_EXISTING);

            return ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path(prefix + endpoint + "/" + rootLocation.toString() + "/")
                    .path(destinationFile.getFileName().toString())
                    .toUriString();
        } catch (Exception e) {
            throw new StorageException(String.format("Failed to store file: %s", e.getMessage()));
        }
    }

    /**
     * Load a file.
     *
     * @param filename File name.
     * @return File as Resource
     * @throws FileNotFoundException thrown when a file is not found.
     * @throws StorageException thrown when a resource error occurred.
     */
    @Override
    public Resource load(String filename) throws FileNotFoundException, StorageException {
        try {
            Path file = rootLocation.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new FileNotFoundException(String.format("File with filename=%s is not found.", filename));
            }
        } catch (Exception e) {
            throw new StorageException(String.format("File with filename=%s is not readable", filename));
        }
    }
}
