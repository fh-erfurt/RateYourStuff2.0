package de.fourzerofournotfound.rateyourstuff.rays.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * Pageable Service
 * <p>This Services provides a method to create a pageable which is used by almost all controllers</p>
 */
@Service("pageableService")
public class PageableService {
    /**
     * Returns a pageable object with the given settings
     * @param orderBy   string of the attributed that should be ordered
     * @param order     the order, can be asc for ascending or desc for descending
     * @param page      the current page
     * @param size      the number of objects per page
     * @return          the configured Pageable
     */
    public Pageable createPageable (String orderBy, String order, int page, int size) {
        Pageable pageable;
        if(!orderBy.equals("")) {
            Sort sort;
            if(order.equalsIgnoreCase("asc")) {
                sort = Sort.by(Sort.Direction.ASC, orderBy);
            } else {
                sort = Sort.by(Sort.Direction.DESC, orderBy);
            }
            pageable = PageRequest.of(page, size, sort);
        } else {
            pageable = PageRequest.of(page, size);
        }
        return pageable;
    }
}
