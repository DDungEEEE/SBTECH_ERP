package com.sbtech.erp.matching.service;

import com.sbtech.erp.matching.adapter.out.dto.LocationInfo;
import com.sbtech.erp.matching.adapter.out.persistence.entity.StoreEntity;
import com.sbtech.erp.matching.adapter.out.persistence.repository.StoreJpaRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RequiredArgsConstructor
@Service
public class StoreService {

    private final StoreJpaRepository storeJpaRepository;
    private final LocationService locationService;

    @PostConstruct
    @Transactional
    public void insertTestStores() {
        createStore("스타벅스 강남점", "서울 강남구 강남대로 390");
        createStore("이디야 커피 신촌점", "서울 서대문구 연세로 12");
        createStore("투썸플레이스 시청점", "서울 중구 세종대로 110");
    }

    public StoreEntity createStore(String name, String address){
        LocationInfo locationByAddress = locationService.getLocationByAddress(address);
        return StoreEntity.builder()
                .address(address)
                .latitude(new BigDecimal(Double.parseDouble(locationByAddress.lat())).setScale(6, RoundingMode.HALF_UP).doubleValue())
                .longitude(new BigDecimal(Double.parseDouble(locationByAddress.lng())).setScale(6, RoundingMode.HALF_UP).doubleValue())
                .name(name)
                .isActive(true)
                .build();


    }
}
