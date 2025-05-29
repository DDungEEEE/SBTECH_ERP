package com.sbtech.erp.matching.service;

import com.sbtech.erp.matching.adapter.out.persistence.repository.MatchingCandidateRepository;
import com.sbtech.erp.matching.adapter.out.persistence.repository.OrderJpaRepository;
import com.sbtech.erp.matching.adapter.out.persistence.repository.StoreJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MatchingService {
    private final MatchingCandidateRepository matchingCandidateRepository;
    private final OrderJpaRepository orderJpaRepository;
    private final StoreJpaRepository storeJpaRepository;

    public void findAndSaveCandidateStores(Long orderId, double customerLat, double customerLon) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("주문 없음"));

        List<StoreEntity> stores = storeRepository.findAllActiveStores();

        // 거리 계산
        List<StoreCandidate> sortedCandidates = stores.stream()
                .map(store -> new StoreCandidate(
                        store.getId(),
                        DistanceUtil.calculateHaversine(customerLat, customerLon,
                                store.getLatitude(), store.getLongitude())))
                .sorted(Comparator.comparingDouble(StoreCandidate::distance))
                .limit(5)  // 5개만
                .toList();

        // 후보 저장
        for (StoreCandidate c : sortedCandidates) {
            MatchingCandidateEntity entity = new MatchingCandidateEntity();
            entity.setOrder(order);
            entity.setStoreId(c.storeId());
            entity.setDistance(c.distance());
            entity.setStatus(CandidateStatus.REQUESTED);
            entity.setRequestedAt(LocalDateTime.now());
            matchingCandidateRepository.save(entity);
        }
}
