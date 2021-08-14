package de.fourzerofournotfound.rateyourstuff.rays.services.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.CollectionDto;
import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.ReducedCollectionDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.User;
import de.fourzerofournotfound.rateyourstuff.rays.models.errors.UserNotFoundException;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Collection;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;

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

    public Collection addReferencesToCollection(Collection collection) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(collection.getUserMappingId());
        if(user.isPresent()) {
            collection.setUser(user.get());
            return collection;
        }
        throw new UserNotFoundException("There is no user with id " + collection.getUserMappingId());
    }

    public CollectionDto convertToDto(Collection collection) {
        CollectionDto collectionDto = modelMapper.map(collection, CollectionDto.class);
        collectionDto.setMedia(collection.getMedia().stream().map(mediaService::convertToDto).collect(Collectors.toList()));

        return collectionDto;
    }

    public ReducedCollectionDto convertToReducedDto(Collection collection) {
        return modelMapper.map(collection, ReducedCollectionDto.class);
    }
}
