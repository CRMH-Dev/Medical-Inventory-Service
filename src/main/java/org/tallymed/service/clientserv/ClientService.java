package org.tallymed.service.clientserv;

import org.springframework.http.ResponseEntity;
import org.tallymed.service.clientserv.op.DealerOperation;
import org.tallymed.service.clientserv.op.LoginOperation;
import org.tallymed.service.clientserv.op.ProductInventoryOperation;

public interface ClientService {
	public ResponseEntity<LoginOperation> loginOperation(LoginOperation loginOperation);
	public ResponseEntity<ProductInventoryOperation> inventoryOperation(ProductInventoryOperation productInventoryOperation);
	public ResponseEntity<DealerOperation> dealerOperation(DealerOperation dealerOperation);
}
