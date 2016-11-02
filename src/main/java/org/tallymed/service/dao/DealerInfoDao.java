package org.tallymed.service.dao;

import java.util.List;

import org.tallymed.service.model.DealerInfo;

public interface DealerInfoDao {
	public void saveDealerInfo (DealerInfo dealerInfo);
	public void deleteDealerInfo (DealerInfo dealerInfo);
	public void updateDealerInfo (DealerInfo dealerInfo);
	public List<DealerInfo> findDealerInfoByName(DealerInfo dealerInfo);
	public List<DealerInfo> findAllDealer(DealerInfo dealerInfo);
}
