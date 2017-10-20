package com.offcial.dao;


import com.offcial.dto.TokenDto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 将Accessage_Token保存起来
 *
 * @author hufei
 * @email hufei@ycmedia.cn
 * @date 2017-10-19 16:06
 */
public interface AccessTokenDao extends JpaRepository<TokenDto, Long> {

    /**
     * 存储accessToken
     *
     * @param tokenDto
     * @return
     */
    @Override
    TokenDto save(TokenDto tokenDto);

    /**
     * 获取最新一条access
     *
     * @return
     */
    @Override
    List<TokenDto> findAll();

    /**
     * 获取最新一条token
     *
     * @return
     */
    TokenDto findTopByOrderByTimeDesc();


}
