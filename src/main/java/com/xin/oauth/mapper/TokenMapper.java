package com.xin.oauth.mapper;

import com.xin.oauth.models.entity.TokenEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;


/**
 * @author xinyu.huang02
 * @date 2020-02-29 16:42
 * @class Token Mapper
 */
@Repository
public interface TokenMapper {

    @Insert("INSERT INTO token(access_token, refresh_token, app_key, app_secret, expires_in, grant_type, scope) VALUES(#{accessToken}, #{refreshToken}, " +
            "#{appKey}, #{appSecret}, #{expiresIn}, #{grantType}, #{scope})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(TokenEntity tokenEntity);


    @Select("SELECT access_token, refresh_token, app_key, app_secret, expires_in, grant_type, " +
            "scope FROM token WHERE refresh_token=#{refreshToken}")
    TokenEntity selectByRefreshToken(String refreshToken);


    @Select("SELECT access_token, refresh_token, app_key, app_secret, expires_in, grant_type," +
            " scope FROM token WHERE app_key=#{appKey} AND app_secret=#{appSecret}")
    TokenEntity selectByAppKeyAndAppSecret(String appKey, String appSecret);


    @Select("SELECT access_token, refresh_token, app_key, app_secret, expires_in, grant_type," +
            " scope FROM token WHERE access_token=#{accessToken}")
    TokenEntity selectByAccessToken(String accessToken);


    @Select("SELECT access_token, refresh_token, app_key, app_secret, expires_in, grant_type," +
            " scope FROM token WHERE id=#{id}")
    TokenEntity selectByTokenId(String tokenId);

}

