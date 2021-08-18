package de.fourzerofournotfound.rateyourstuff.rays.controllers.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.NetworkDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Network;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.NetworkRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>This Controller provides basic REST Interfaces to interact with Network entities from the database</p>
 *
 * @author Christoph Frischmuth
 * @author John Klippstein
 * @author Mickey Knop
 * @author Robin Beck
 */
@RestController
@RequestMapping("/rest/networks")
public class NetworkController {

    private final NetworkRepository networkRepository;
    private final PageableService pageableService;
    private final NetworkService networkService;

    @Autowired
    public NetworkController(NetworkRepository repository, PageableService pageableService, NetworkService networkService) {
        this.networkRepository = repository;
        this.pageableService = pageableService;
        this.networkService = networkService;
    }

    /**
     * This Method returns all Networks from the database
     *
     * @param page    the current page (optional)
     * @param size    the number of items per page
     * @param orderBy the attributed that should be ordered
     * @param order   the order (asc, desc)
     * @return a list of NetworkDTOs
     */
    @GetMapping("/all")
    ResponseEntity<List<NetworkDto>> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "50") int size,
            @RequestParam(defaultValue = "") String orderBy,
            @RequestParam(defaultValue = "asc") String order
    ) {
        Pageable pageable = pageableService.createPageable(orderBy, order, page, size);
        List<Network> networks = this.networkRepository.findAll(pageable).getContent();

        return ResponseEntity.ok(networks.stream()
                .map(networkService::convertToDto)
                .collect(Collectors.toList()));
    }
}
