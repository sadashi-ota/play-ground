package com.sadashi.playground.infra.api.json

import com.squareup.moshi.Json

data class GitHubUserDetail(
    @Json(name = "login") val login: String,
    @Json(name = "id") val id: Int,
    @Json(name = "node_id") val nodeId: String,
    @Json(name = "avatar_url") val avatarUrl: String,
    @Json(name = "html_url") val htmlUrl: String,
    @Json(name = "type") val type: String,
    @Json(name = "site_admin") val siteAdmin: Boolean,
    @Json(name = "name") val name: String?,
    @Json(name = "company") val company: String?,
    @Json(name = "blog") val blog: String,
    @Json(name = "location") val location: String?,
    @Json(name = "email") val email: String?,
    @Json(name = "hireable") val hireAble: Boolean?,
    @Json(name = "bio") val bio: String?,
    @Json(name = "public_repos") val publicRepos: Int,
    @Json(name = "public_gists") val publicGists: Int,
    @Json(name = "followers") val followers: Int,
    @Json(name = "following") val following: Int,
    @Json(name = "created_at") val createdAt: String,
    @Json(name = "updated_at") val updatedAt: String
)
