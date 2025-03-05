package online.beautyskin.beauty.entity.request;

import java.util.List;

public class OrderRequest {

    List<OrderDetailsRequest> details;

    public List<OrderDetailsRequest> getDetails() {
        return details;
    }

    public void setDetails(List<OrderDetailsRequest> details) {
        this.details = details;
    }
}
