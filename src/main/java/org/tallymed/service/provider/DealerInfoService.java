package org.tallymed.service.provider;

import java.util.List;

import org.tallymed.service.model.DealerInfo;

public interface DealerInfoService {
	public List<DealerInfo> findDealerInfo(DealerInfo dealerInfo);
	public void save(DealerInfo dealerInfo);
	public void delete(DealerInfo dealerInfo);
	public void update(DealerInfo dealerInfo);
	public List<DealerInfo> findAll(DealerInfo dealerInfo);
}
