package com.offcial.dao;


import com.offcial.dto.HouseMessageDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 房屋信息dao
 *
 * @author Zza
 * @date 2017-10-18 16:55
 */
public interface HouseMessageDao extends JpaRepository<HouseMessageDto, Long> {

    /**
     * 查询据当天一周内的所有房屋信息
     *
     * @param timeS 当前时间
     * @param timeE 一周前时间
     * @return
     */
    List<HouseMessageDto> findAllByTimeIsBetweenOrderByTimeDesc(String timeS, String timeE);

    /**
     * 数据库插入
     *
     * @param houseMessageDto
     * @return
     */
    @Override
    HouseMessageDto save(HouseMessageDto houseMessageDto);

}
