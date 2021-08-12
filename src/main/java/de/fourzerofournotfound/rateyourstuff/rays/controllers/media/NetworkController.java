package de.fourzerofournotfound.rateyourstuff.rays.controllers.media;

import de.fourzerofournotfound.rateyourstuff.rays.dtos.media.NetworkDto;
import de.fourzerofournotfound.rateyourstuff.rays.models.media.Network;
import de.fourzerofournotfound.rateyourstuff.rays.repositories.media.NetworkRepository;
import de.fourzerofournotfound.rateyourstuff.rays.services.PageableService;
import de.fourzerofournotfound.rateyourstuff.rays.services.media.NetworkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
