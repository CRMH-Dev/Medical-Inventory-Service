package org.tallymed.service.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.tallymed.service.dao.DealerInfoDao;
import org.tallymed.service.model.DealerInfo;

@Repository("dealerInfoDao")
public class DealerInfoDaoImpl extends GenericDaoImpl<DealerInfo, Integer> implements DealerInfoDao {

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void saveDealerInfo(DealerInfo dealerInfo) {
		saveOrUpdate(dealerInfo);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void deleteDealerInfo(DealerInfo dealerInfo) {
		remove(dealerInfo);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public void updateDealerInfo(DealerInfo dealerInfo) {
		update(dealerInfo);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<DealerInfo> findDealerInfoByName(DealerInfo dealerInfo) {
		Query query = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery("DealerInfo.findByName")
				.setParameter("dealerName", dealerInfo.getDealerName());
		return findByNamedQuery(query);
	}

	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW)
	public List<DealerInfo> findAllDealer(DealerInfo dealerInfo) {
		Query query = getSessionFactory()
				.getCurrentSession()
				.getNamedQuery("DealerInfo.findAll");
		return findByNamedQuery(query);
		
	}

}
