package com.sadashi.playground.infra.api

import com.sadashi.playground.infra.api.json.GitHubUserDetail
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface UserApiClient {
    @GET("users/{username}")
    fun getUserDetail(
        @Path("username") userName: String
    ): Single<GitHubUserDetail>
}
