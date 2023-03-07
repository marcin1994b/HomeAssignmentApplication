package com.example.homeassignmentapp.cloud

import com.example.homeassignmentapp.model.Repositories
import retrofit2.Response

fun Response<Repositories>.isErrorState() = this.code() in (400..599)