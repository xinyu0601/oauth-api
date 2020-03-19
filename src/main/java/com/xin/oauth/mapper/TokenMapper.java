package com.xin.oauth.mapper;

import com.xin.oauth.models.entity.TokenEntity;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;


/**
 * @author xinyu.huang02
 * @date 2020-02-29 16:42
 * @class NeedToken Mapper
 */
@Repository
public interface TokenMapper {

    @Insert("INSERT INTO token(access_token, refresh_token, app_key, app_secret, expires_in, grant_type, scope) VALUES(#{accessToken}, #{refreshToken}, " +
            "#{appKey}, #{appSecret}, #{expiresIn}, #{grantType}, #{scope})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(TokenEntity tokenEntity);


    @Select("SELECT id, access_token, refresh_token, app_key, app_secret, expires_in, grant_type," +
            "scope FROM token WHERE refresh_token=#{refreshToken}")
    @Results({
            @Result(column = "access_token", property = "accessToken"),
            @Result(column = "refresh_token", property = "refreshToken"),
            @Result(column = "app_key", property = "appKey"),
            @Result(column = "app_secret", property = "appSecret"),
            @Result(column = "expires_in", property = "expiresIn"),
            @Result(column = "grant_type", property = "grantType"),
            @Result(column = "scope", property = "scope"),
    })
    TokenEntity selectByRefreshToken(String refreshToken);


    @Select("SELECT id, access_token, refresh_token, app_key, app_secret, expires_in, grant_type," +
            " scope FROM token WHERE app_key=#{appKey} AND app_secret=#{appSecret}")
    @Results({
            @Result(column = "access_token", property = "accessToken"),
            @Result(column = "refresh_token", property = "refreshToken"),
            @Result(column = "app_key", property = "appKey"),
            @Result(column = "app_secret", property = "appSecret"),
            @Result(column = "expires_in", property = "expiresIn"),
            @Result(column = "grant_type", property = "grantType"),
            @Result(column = "scope", property = "scope"),
    })
    TokenEntity selectByAppKeyAndAppSecret(String appKey, String appSecret);


    @Select("SELECT id, access_token, refresh_token, app_key, app_secret, expires_in, grant_type," +
            " scope FROM token WHERE access_token=#{accessToken}")
    @Results({
            @Result(column = "access_token", property = "accessToken"),
            @Result(column = "refresh_token", property = "refreshToken"),
            @Result(column = "app_key", property = "appKey"),
            @Result(column = "app_secret", property = "appSecret"),
            @Result(column = "expires_in", property = "expiresIn"),
            @Result(column = "grant_type", property = "grantType"),
            @Result(column = "scope", property = "scope"),
    })
    TokenEntity selectByAccessToken(String accessToken);


    @Select("SELECT id, access_token, refresh_token, app_key, app_secret, expires_in, grant_type," +
            " scope FROM token WHERE id=#{tokenId}")
    @Results({
            @Result(column = "access_token", property = "accessToken"),
            @Result(column = "refresh_token", property = "refreshToken"),
            @Result(column = "app_key", property = "appKey"),
            @Result(column = "app_secret", property = "appSecret"),
            @Result(column = "expires_in", property = "expiresIn"),
            @Result(column = "grant_type", property = "grantType"),
            @Result(column = "scope", property = "scope"),
    })
    TokenEntity selectByTokenId(Long tokenId);


    @Delete("DELETE FROM token WHERE id=#{tokenId}")
    void deleteByTokenId(Long tokenId);

}

