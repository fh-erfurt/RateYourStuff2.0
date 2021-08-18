package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.CollectionDto;
import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.ReducedCollectionDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.UserNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Collection;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Medium;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Collection Service
 * <p>This Service is used to provide methods to the {@link de.fourzerofournotfound.rateyourstuff.rays.controllers.media.CollectionController CollectionController}</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@Service
public class CollectionService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final MediaService mediaService;

    @Autowired
    public CollectionService(UserRepository userRepository,
                             ModelMapper modelMapper,
                             MediaService mediaService) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.mediaService = mediaService;
    }

    /**
     * Adds a reference to the user that should be marked as creator to the collection
     *
     * @param collection    The collection that should be modified
     * @param userMappingId The id of the user that should be added to the colelction
     * @return the modified collection with the valid user reference
     * @throws UserNotFoundException if the user has not been found
     */
    public Collection addReferencesToCollection(Collection collection, Long userMappingId) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(userMappingId);
        if (user.isPresent()) {
            collection.setUser(user.get());
            return collection;
        }
        throw new UserNotFoundException("There is no user with id " + collection.getUserMappingId());
    }

    /**
     * Converts a single collection entity to a collection DTO
     *
     * @param collection the collection that should be converted
     * @return the converted collection
     */
    public CollectionDto convertToDto(Collection collection) {
        CollectionDto collectionDto = modelMapper.map(collection, CollectionDto.class);
        collectionDto.setMedia(collection.getMedia().stream().map(mediaService::convertToDto).collect(Collectors.toList()));

        return collectionDto;
    }

    /**
     * Converts a single collection to a reduced collection DTO
     *
     * @param collection the collection that should be converted
     * @return the converted collection
     */
    public ReducedCollectionDto convertToReducedDto(Collection collection) {
        return modelMapper.map(collection, ReducedCollectionDto.class);
    }

    /**
     * Removes all collections from a given list that include a certain medium
     *
     * @param collections the list of collections that should be checked
     * @param mediumId    the id of the medium which should be searched
     * @return the filtered collection list
     */
    public List<Collection> removeCollectionsWithMediaId(List<Collection> collections, Long mediumId) {
        return collections
                .stream().filter(collection -> !checkIfMediaIdIsInMediaList(collection.getMedia(), mediumId))
                .collect(Collectors.toList());
    }

    /**
     * Checks if the given medium is inside a given medium list
     *
     * @param media    the list of media that should be searched for the medium
     * @param mediumId the id of the medium that should be searched
     * @return true, if the collection contains the medium
     */
    private boolean checkIfMediaIdIsInMediaList(Set<Medium> media, Long mediumId) {
        return media.stream().anyMatch(medium -> medium.getId().equals(mediumId));
    }
}
