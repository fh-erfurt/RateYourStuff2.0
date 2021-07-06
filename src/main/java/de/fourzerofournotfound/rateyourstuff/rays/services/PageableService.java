package de.fourzerofournotfound.rateyourstuff.rays.services;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service("ps")
public class PageableService {
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
