package com.example.demo.nsc.repository;

import com.example.demo.nsc.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    boolean existsByVehicleNo(String vehicleNo);

    @Query("SELECT v from Vehicle v WHERE v.available = 'yes'")
    List<Vehicle> findByAvailableTrue();

    @Query("SELECT v from Vehicle v WHERE v.available = 'yes' AND v.vehicleCategory=:vehicleCategory")
    List<Vehicle> findByAvailableTrueAndVehicleCategory(@Param("vehicleCategory") String vehicleCategory);
}

















//        List<Vehicle> apiDataList = apiDataRepository.findByRetryCountLessThan(3);
//        List<Vehicle> apiDataList1 = new ArrayList<>();
//        for(Vehicle data : apiDataList)
//        {
//            if(data.getRetryTime() == null)
//            {
//                apiDataList1 = apiDataRepository.findDataForRetryTimeIsNull();
//                System.out.println(apiDataList1);
//                thirdPartyAPI(data);
//            }
//            else if(data.getRetryTime() != null)
//            {
//                apiDataList1 = apiDataRepository.findDataForRetryTimeIsNotNull();
//                System.out.println(apiDataList1);
//                thirdPartyAPI(data);
//            }
//        }
//        return apiDataList;


//    List<Vehicle> findByRetryCountLessThan(int count);
//
//    @Query("SELECT a FROM Vehicle a WHERE TIMESTAMPDIFF(MINUTE, a.rechargeTime, CURRENT_TIMESTAMP()) < 5")
//    List<Vehicle> findDataForRetryTimeIsNull();
//
//    @Query("SELECT a FROM Vehicle a WHERE TIMESTAMPDIFF(MINUTE, a.retryTime, a.rechargeTime) < 5")
//    List<Vehicle> findDataForRetryTimeIsNotNull();
