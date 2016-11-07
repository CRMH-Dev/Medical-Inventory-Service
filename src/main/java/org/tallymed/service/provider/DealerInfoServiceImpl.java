package org.tallymed.service.provider;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tallymed.service.dao.DealerInfoDao;
import org.tallymed.service.model.DealerInfo;

@Service("dealerInfoService")
public class DealerInfoServiceImpl implements DealerInfoService {

	@Autowired
	private DealerInfoDao dealerInfoDao;
	
	@Override
	public List<DealerInfo> findDealerInfo(DealerInfo dealerInfo) {
		return dealerInfoDao.findDealerInfoByName(dealerInfo);
	}

	@Override
	public void save(DealerInfo dealerInfo) {
		dealerInfoDao.saveDealerInfo(dealerInfo);
	}

	@Override
	public void delete(DealerInfo dealerInfo) {
		dealerInfoDao.deleteDealerInfo(dealerInfo);
	}

	@Override
	public void update(DealerInfo dealerInfo) {
		dealerInfoDao.updateDealerInfo(dealerInfo);
	}

	public DealerInfoDao getDealerInfoDao() {
		return dealerInfoDao;
	}

	public void setDealerInfoDao(DealerInfoDao dealerInfoDao) {
		this.dealerInfoDao = dealerInfoDao;
	}

	@Override
	public List<DealerInfo> findAll(DealerInfo dealerInfo) {
		return dealerInfoDao.findAllDealer(dealerInfo);
	}

	@Override
	public List<DealerInfo> findDealerInfoByName(DealerInfo dealerInfo) {
		return dealerInfoDao.findDealerInfoByName(dealerInfo);
	}
	
}
